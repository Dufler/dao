package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.FeatureDao;
import it.ltc.database.dao.common.utente.FeatureUtentiDao;
import it.ltc.database.model.centrale.Feature;
import it.ltc.database.model.utente.FeatureUtenti;
import it.ltc.database.sincronizza.TableSync;

public class FeatureSync extends TableSync<Feature, FeatureUtenti> {
	
	private final FeatureDao daoMaster;
	private final FeatureUtentiDao daoSlave;
	
	public FeatureSync(String puMaster, String puSlave) {
		daoMaster = new FeatureDao(puMaster);
		daoSlave = new FeatureUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, Feature> getMasterEntities() {
		HashMap<Object, Feature> masterEntities = new HashMap<>();
		List<Feature> entities = daoMaster.trovaTutte();
		for (Feature feature : entities)
			masterEntities.put(feature.getFeatureid(), feature);
		return masterEntities;
	}

	@Override
	protected HashMap<Object, FeatureUtenti> getSlaveEntities() {
		HashMap<Object, FeatureUtenti> slaveEntities = new HashMap<>();
		List<FeatureUtenti> entities = daoSlave.trovaTutti();
		for (FeatureUtenti feature : entities)
			slaveEntities.put(feature.getFeatureid(), feature);
		return slaveEntities;
	}

	@Override
	protected boolean updateSlave(Feature master, FeatureUtenti slave) {
		slave.setNome(master.getNome());
		slave.setVersione(master.getVersione());
		boolean update = daoSlave.aggiorna(slave) != null;
		return update;
	}

	@Override
	protected boolean insertSlave(Feature master) {
		FeatureUtenti slave = new FeatureUtenti();
		slave.setFeatureid(master.getFeatureid());
		slave.setNome(master.getNome());
		slave.setVersione(master.getVersione());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(FeatureUtenti slave) {
		boolean delete = daoSlave.elimina(slave) != null;
		return delete;
	}	
	
}

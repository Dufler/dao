package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.UtenteFeatureDao;
import it.ltc.database.dao.common.utente.UtenteFeatureUtentiDao;
import it.ltc.database.model.centrale.UtenteFeaturesJoin;
import it.ltc.database.model.utente.UtenteFeaturesJoinUtenti;
import it.ltc.database.sincronizza.TableSync;

public class UtenteFeatureSync extends TableSync<UtenteFeaturesJoin, UtenteFeaturesJoinUtenti> {
	
	private final UtenteFeatureDao daoMaster;
	private final UtenteFeatureUtentiDao daoSlave;
	
	public UtenteFeatureSync(String puMaster, String puSlave) {
		daoMaster = new UtenteFeatureDao(puMaster);
		daoSlave = new UtenteFeatureUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, UtenteFeaturesJoin> getMasterEntities() {
		HashMap<Object, UtenteFeaturesJoin> masters = new HashMap<>();
		List<UtenteFeaturesJoin> features = daoMaster.trovaTutti();
		for (UtenteFeaturesJoin feature : features)
			masters.put(feature.getUtente() + feature.getFeature(), feature);
		return masters;
	}

	@Override
	protected HashMap<Object, UtenteFeaturesJoinUtenti> getSlaveEntities() {
		HashMap<Object, UtenteFeaturesJoinUtenti> slaves = new HashMap<>();
		List<UtenteFeaturesJoinUtenti> features = daoSlave.trovaTutti();
		for (UtenteFeaturesJoinUtenti feature : features)
			slaves.put(feature.getUtente() + feature.getFeature(), feature);
		return slaves;
	}

	@Override
	protected boolean updateSlave(UtenteFeaturesJoin master, UtenteFeaturesJoinUtenti slave) {
		//DO NOTHING!
		return true;
	}

	@Override
	protected boolean insertSlave(UtenteFeaturesJoin master) {
		UtenteFeaturesJoinUtenti slave = new UtenteFeaturesJoinUtenti();
		slave.setFeature(master.getFeature());
		slave.setUtente(master.getUtente());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(UtenteFeaturesJoinUtenti slave) {
		boolean delete = daoSlave.elimina(slave.getUtente(), slave.getFeature()) != null;
		return delete;
	}

}

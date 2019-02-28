package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.SedeDao;
import it.ltc.database.dao.common.utente.SedeUtentiDao;
import it.ltc.database.model.centrale.Sede;
import it.ltc.database.model.utente.SedeUtenti;
import it.ltc.database.sincronizza.TableSync;

public class SedeSync extends TableSync<Sede, SedeUtenti> {
	
	private final SedeDao daoMaster;
	private final SedeUtentiDao daoSlave;
	
	public SedeSync(String puMaster, String puSlave) {
		daoMaster = new SedeDao(puMaster);
		daoSlave = new SedeUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, Sede> getMasterEntities() {
		HashMap<Object, Sede> masters = new HashMap<>();
		List<Sede> sedi = daoMaster.trovaTutte();
		for (Sede sede : sedi)
			masters.put(sede.getId(), sede);
		return masters;
	}

	@Override
	protected HashMap<Object, SedeUtenti> getSlaveEntities() {
		HashMap<Object, SedeUtenti> slaves = new HashMap<>();
		List<SedeUtenti> sedi = daoSlave.trovaTutti();
		for (SedeUtenti sede : sedi)
			slaves.put(sede.getId(), sede);
		return slaves;
	}

	@Override
	protected boolean updateSlave(Sede master, SedeUtenti slave) {
		slave.setSede(master.getSede());
		boolean update = daoSlave.aggiorna(slave) != null;
		return update;
	}

	@Override
	protected boolean insertSlave(Sede master) {
		SedeUtenti slave = new SedeUtenti();
		slave.setId(master.getId());
		slave.setSede(master.getSede());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(SedeUtenti slave) {
		boolean delete = daoSlave.elimina(slave) != null;
		return delete;
	}

}

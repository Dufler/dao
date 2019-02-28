package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.UtenteSediDao;
import it.ltc.database.dao.common.utente.UtenteSediUtentiDao;
import it.ltc.database.model.centrale.UtenteSedeJoin;
import it.ltc.database.model.utente.UtenteSedeJoinUtenti;
import it.ltc.database.sincronizza.TableSync;

public class UtentiSediSync extends TableSync<UtenteSedeJoin, UtenteSedeJoinUtenti> {
	
	private final UtenteSediDao daoMaster;
	private final UtenteSediUtentiDao daoSlave;
	
	public UtentiSediSync(String puMaster, String puSlave) {
		daoMaster = new UtenteSediDao(puMaster);
		daoSlave = new UtenteSediUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, UtenteSedeJoin> getMasterEntities() {
		HashMap<Object, UtenteSedeJoin> masters = new HashMap<>();
		List<UtenteSedeJoin> sedi = daoMaster.trovaTutti();
		for (UtenteSedeJoin sede : sedi)
			masters.put(sede.getUtente() + sede.getIdSede(), sede);
		return masters;
	}

	@Override
	protected HashMap<Object, UtenteSedeJoinUtenti> getSlaveEntities() {
		HashMap<Object, UtenteSedeJoinUtenti> slaves = new HashMap<>();
		List<UtenteSedeJoinUtenti> sedi = daoSlave.trovaTutti();
		for (UtenteSedeJoinUtenti sede : sedi)
			slaves.put(sede.getUtente() + sede.getIdSede(), sede);
		return slaves;
	}

	@Override
	protected boolean updateSlave(UtenteSedeJoin master, UtenteSedeJoinUtenti slave) {
		//DO NOTHING!
		return true;
	}

	@Override
	protected boolean insertSlave(UtenteSedeJoin master) {
		UtenteSedeJoinUtenti slave = new UtenteSedeJoinUtenti();
		slave.setIdSede(master.getIdSede());
		slave.setUtente(master.getUtente());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(UtenteSedeJoinUtenti slave) {
		boolean delete = daoSlave.elimina(slave.getUtente(), slave.getIdSede()) != null;
		return delete;
	}

}

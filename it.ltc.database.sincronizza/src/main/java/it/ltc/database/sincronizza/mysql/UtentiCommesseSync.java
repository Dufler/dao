package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.UtenteCommesseDao;
import it.ltc.database.dao.common.utente.UtenteCommesseUtentiDao;
import it.ltc.database.model.centrale.UtenteCommessaJoin;
import it.ltc.database.model.utente.UtenteCommessaJoinUtenti;
import it.ltc.database.sincronizza.TableSync;

public class UtentiCommesseSync extends TableSync<UtenteCommessaJoin, UtenteCommessaJoinUtenti> {
	
	private final UtenteCommesseDao daoMaster;
	private final UtenteCommesseUtentiDao daoSlave;
	
	public UtentiCommesseSync(String puMaster, String puSlave) {
		daoMaster = new UtenteCommesseDao(puMaster);
		daoSlave = new UtenteCommesseUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, UtenteCommessaJoin> getMasterEntities() {
		HashMap<Object, UtenteCommessaJoin> masters = new HashMap<>();
		List<UtenteCommessaJoin> commesse = daoMaster.trovaTutti();
		for (UtenteCommessaJoin commessa : commesse)
			masters.put(commessa.getUtente() + commessa.getIdCommessa(), commessa);
		return masters;
	}

	@Override
	protected HashMap<Object, UtenteCommessaJoinUtenti> getSlaveEntities() {
		HashMap<Object, UtenteCommessaJoinUtenti> slaves = new HashMap<>();
		List<UtenteCommessaJoinUtenti> commesse = daoSlave.trovaTutti();
		for (UtenteCommessaJoinUtenti commessa : commesse)
			slaves.put(commessa.getUtente() + commessa.getIdCommessa(), commessa);
		return slaves;
	}

	@Override
	protected boolean updateSlave(UtenteCommessaJoin master, UtenteCommessaJoinUtenti slave) {
		//DO NOTHING!
		return true;
	}

	@Override
	protected boolean insertSlave(UtenteCommessaJoin master) {
		UtenteCommessaJoinUtenti slave = new UtenteCommessaJoinUtenti();
		slave.setIdCommessa(master.getIdCommessa());
		slave.setUtente(master.getUtente());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(UtenteCommessaJoinUtenti slave) {
		boolean delete = daoSlave.elimina(slave.getUtente(), slave.getIdCommessa()) != null;
		return delete;
	}

}

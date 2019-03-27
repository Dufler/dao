package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.UtenteDao;
import it.ltc.database.dao.utente.UtenteUtentiDao;
import it.ltc.database.model.centrale.Utente;
import it.ltc.database.model.utente.UtenteUtenti;
import it.ltc.database.sincronizza.TableSync;

public class UtentiSync extends TableSync<Utente, UtenteUtenti> {
	
	private final UtenteDao daoMaster;
	private final UtenteUtentiDao daoSlave;
	
	public UtentiSync(String puMaster, String puSlave) {
		daoMaster = new UtenteDao(puMaster);
		daoSlave = new UtenteUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, Utente> getMasterEntities() {
		HashMap<Object, Utente> masters = new HashMap<>();
		List<Utente> utenti = daoMaster.trovaTutti(false);
		for (Utente utente : utenti)
			masters.put(utente.getUsername(), utente);
		return masters;
	}

	@Override
	protected HashMap<Object, UtenteUtenti> getSlaveEntities() {
		HashMap<Object, UtenteUtenti> slaves = new HashMap<>();
		List<UtenteUtenti> utenti = daoSlave.trovaTutti(false);
		for (UtenteUtenti utente : utenti)
			slaves.put(utente.getUsername(), utente);
		return slaves;
	}

	@Override
	protected boolean updateSlave(Utente master, UtenteUtenti slave) {
		slave.setCognome(master.getCognome());
		slave.setEmail(master.getEmail());
		slave.setNome(master.getNome());
		slave.setPassword(master.getPassword());
		slave.setRisorsaTemporanea(master.getRisorsaTemporanea());
		slave.setScadenzaRisorsa(master.getScadenzaRisorsa());
		boolean update = daoSlave.aggiorna(slave) != null;
		return update;
	}

	@Override
	protected boolean insertSlave(Utente master) {
		UtenteUtenti slave = new UtenteUtenti();
		slave.setUsername(master.getUsername());
		slave.setCognome(master.getCognome());
		slave.setEmail(master.getEmail());
		slave.setNome(master.getNome());
		slave.setPassword(master.getPassword());
		slave.setRisorsaTemporanea(master.getRisorsaTemporanea());
		slave.setScadenzaRisorsa(master.getScadenzaRisorsa());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(UtenteUtenti slave) {
		boolean delete = daoSlave.elimina(slave) != null;
		return delete;
	}

}

package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.CommessaDao;
import it.ltc.database.dao.utente.CommessaUtentiDao;
import it.ltc.database.model.centrale.Commessa;
import it.ltc.database.model.utente.CommessaUtenti;
import it.ltc.database.sincronizza.TableSync;

public class CommesseSync extends TableSync<Commessa, CommessaUtenti> {
	
	private final CommessaDao daoMaster;
	private final CommessaUtentiDao daoSlave;
	
	public CommesseSync(String puMaster, String puSlave) {
		daoMaster = new CommessaDao(puMaster);
		daoSlave = new CommessaUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, Commessa> getMasterEntities() {
		HashMap<Object, Commessa> masters = new HashMap<>();
		List<Commessa> commesse = daoMaster.trovaTutte();
		for (Commessa commessa : commesse)
			masters.put(commessa.getId(), commessa);
		return masters;
	}

	@Override
	protected HashMap<Object, CommessaUtenti> getSlaveEntities() {
		HashMap<Object, CommessaUtenti> slaves = new HashMap<>();
		List<CommessaUtenti> commesse = daoSlave.trovaTutti();
		for (CommessaUtenti commessa : commesse)
			slaves.put(commessa.getId(), commessa);
		return slaves;
	}

	@Override
	protected boolean updateSlave(Commessa master, CommessaUtenti slave) {
		slave.setDescrizione(master.getDescrizione());
		slave.setIdSede(master.getIdSede());
		slave.setLegacy(master.isLegacy());
		slave.setNome(master.getNome());
		slave.setNomeRisorsa(master.getNomeRisorsa());
		boolean update = daoSlave.aggiorna(slave) != null;
		return update;
	}

	@Override
	protected boolean insertSlave(Commessa master) {
		CommessaUtenti slave = new CommessaUtenti();
		slave.setId(master.getId());
		slave.setDescrizione(master.getDescrizione());
		slave.setIdSede(master.getIdSede());
		slave.setLegacy(master.isLegacy());
		slave.setNome(master.getNome());
		slave.setNomeRisorsa(master.getNomeRisorsa());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(CommessaUtenti slave) {
		boolean delete = daoSlave.elimina(slave) != null;
		return delete;
	}

}

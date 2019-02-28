package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.UtentePermessiDao;
import it.ltc.database.dao.common.utente.UtentePermessiUtentiDao;
import it.ltc.database.model.centrale.UtentePermessiJoin;
import it.ltc.database.model.utente.UtentePermessiJoinUtenti;
import it.ltc.database.sincronizza.TableSync;

public class UtentiPermessiSync extends TableSync<UtentePermessiJoin, UtentePermessiJoinUtenti> {

	private final UtentePermessiDao daoMaster;
	private final UtentePermessiUtentiDao daoSlave;
	
	public UtentiPermessiSync(String puMaster, String puSlave) {
		daoMaster = new UtentePermessiDao(puMaster);
		daoSlave = new UtentePermessiUtentiDao(puSlave);
	}
	
	@Override
	protected HashMap<Object, UtentePermessiJoin> getMasterEntities() {
		HashMap<Object, UtentePermessiJoin> masters = new HashMap<>();
		List<UtentePermessiJoin> permessi = daoMaster.trovaTutti();
		for (UtentePermessiJoin permesso : permessi)
			masters.put(permesso.getUtente() + permesso.getIdPermesso(), permesso);
		return masters;
	}

	@Override
	protected HashMap<Object, UtentePermessiJoinUtenti> getSlaveEntities() {
		HashMap<Object, UtentePermessiJoinUtenti> slaves = new HashMap<>();
		List<UtentePermessiJoinUtenti> permessi = daoSlave.trovaTutti();
		for (UtentePermessiJoinUtenti permesso : permessi)
			slaves.put(permesso.getUtente() + permesso.getIdPermesso(), permesso);
		return slaves;
	}

	@Override
	protected boolean updateSlave(UtentePermessiJoin master, UtentePermessiJoinUtenti slave) {
		//DO NOTHING!
		return true;
	}

	@Override
	protected boolean insertSlave(UtentePermessiJoin master) {
		UtentePermessiJoinUtenti slave = new UtentePermessiJoinUtenti();
		slave.setIdPermesso(master.getIdPermesso());
		slave.setUtente(master.getUtente());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(UtentePermessiJoinUtenti slave) {
		boolean delete = daoSlave.elimina(slave.getUtente(), slave.getIdPermesso()) != null;
		return delete;
	}

}

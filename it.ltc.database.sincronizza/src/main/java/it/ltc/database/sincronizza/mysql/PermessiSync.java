package it.ltc.database.sincronizza.mysql;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.common.PermessoDao;
import it.ltc.database.dao.common.utente.PermessoUtentiDao;
import it.ltc.database.model.centrale.Permesso;
import it.ltc.database.model.utente.PermessoUtenti;
import it.ltc.database.sincronizza.TableSync;

public class PermessiSync extends TableSync<Permesso, PermessoUtenti> {
	
	private final PermessoDao daoMaster;
	private final PermessoUtentiDao daoSlave;
	
	public PermessiSync(String puMaster, String puSlave) {
		daoMaster = new PermessoDao(puMaster);
		daoSlave = new PermessoUtentiDao(puSlave);
	}

	@Override
	protected HashMap<Object, Permesso> getMasterEntities() {
		HashMap<Object, Permesso> masters = new HashMap<>();
		List<Permesso> permessi = daoMaster.trovaTutti();
		for (Permesso permesso : permessi)
			masters.put(permesso.getId(), permesso);
		return masters;
	}

	@Override
	protected HashMap<Object, PermessoUtenti> getSlaveEntities() {
		HashMap<Object, PermessoUtenti> slaves = new HashMap<>();
		List<PermessoUtenti> permessi = daoSlave.trovaTutti();
		for (PermessoUtenti permesso : permessi)
			slaves.put(permesso.getId(), permesso);
		return slaves;
	}

	@Override
	protected boolean updateSlave(Permesso master, PermessoUtenti slave) {
		slave.setCategoria(master.getCategoria());
		slave.setIdPadre(master.getIdPadre());
		slave.setNome(master.getNome());
		boolean update = daoSlave.aggiorna(slave) != null;
		return update;
	}

	@Override
	protected boolean insertSlave(Permesso master) {
		PermessoUtenti slave = new PermessoUtenti();
		slave.setId(master.getId());
		slave.setCategoria(master.getCategoria());
		slave.setIdPadre(master.getIdPadre());
		slave.setNome(master.getNome());
		boolean insert = daoSlave.inserisci(slave) != null;
		return insert;
	}

	@Override
	protected boolean deleteSlave(PermessoUtenti slave) {
		boolean delete = daoSlave.elimina(slave) != null;
		return delete;
	}

}

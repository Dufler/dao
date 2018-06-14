package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Cliente;

public class ClienteDao extends CRUDDao<Cliente> {

	public ClienteDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public ClienteDao(String persistenceUnit) {
		super(persistenceUnit, Cliente.class);
	}

	public List<Cliente> trovaTutti() {
		List<Cliente> entities = findAll();
		return entities;
	}

	public Cliente trovaDaID(int id) {
		Cliente entity = findByID(id);
		return entity;
	}

	public Cliente inserisci(Cliente cliente) {
		Cliente entity = insert(cliente);
		return entity;
	}

	public Cliente aggiorna(Cliente cliente) {
		Cliente entity = update(cliente, cliente.getId());
		return entity;
	}

	public Cliente elimina(Cliente cliente) {
		Cliente entity = delete(cliente.getId());
		return entity;
	}

	protected void updateValues(Cliente oldEntity, Cliente entity) {
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setPartitaIva(entity.getPartitaIva());
		oldEntity.setRagioneSociale(entity.getRagioneSociale());
		oldEntity.setCodice(entity.getCodice());
	}

}


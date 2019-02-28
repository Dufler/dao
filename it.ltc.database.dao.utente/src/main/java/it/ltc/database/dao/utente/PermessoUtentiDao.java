package it.ltc.database.dao.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.PermessoUtenti;

public class PermessoUtentiDao extends CRUDDao<PermessoUtenti> {

	public PermessoUtentiDao() {
		this(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME);
	}
	
	public PermessoUtentiDao(String persistenceUnit) {
		super(persistenceUnit, PermessoUtenti.class);
	}
	
	public List<PermessoUtenti> trovaTutti() {
		List<PermessoUtenti> entities = findAll();
		return entities;
	}
	
	public PermessoUtenti trovaDaID(int id) {
		PermessoUtenti entity = findByID(id);
		return entity;
	}
	
	public PermessoUtenti inserisci(PermessoUtenti permesso) {
		PermessoUtenti entity = insert(permesso);
		return entity;
	}
	
	public PermessoUtenti aggiorna(PermessoUtenti permesso) {
		PermessoUtenti entity = update(permesso, permesso.getId());
		return entity;
	}
	
	public PermessoUtenti elimina(PermessoUtenti permesso) {
		PermessoUtenti entity = delete(permesso.getId());
		return entity;
	}

	@Override
	protected void updateValues(PermessoUtenti oldEntity, PermessoUtenti entity) {
		oldEntity.setCategoria(entity.getCategoria());
		oldEntity.setIdPadre(entity.getIdPadre());
		oldEntity.setNome(entity.getNome());
	}

}

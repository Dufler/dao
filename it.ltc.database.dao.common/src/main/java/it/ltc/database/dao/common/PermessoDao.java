package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.Permesso;

/**
 * Dao per la gestione dei permessi.
 * @author Damiano
 *
 */
public class PermessoDao extends CRUDDao<Permesso> {

	public PermessoDao() {
		this(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME);
	}
	
	public PermessoDao(String persistenceUnit) {
		super(persistenceUnit, Permesso.class);
	}
	
	public Permesso trovaDaID(int id) {
		Permesso sede = findByID(id);
		return sede;
	}
	
	public List<Permesso> trovaTutti() {
		List<Permesso> list = findAll();
		return list;
	}
	
	public Permesso inserisci(Permesso permesso) {
		Permesso entity = insert(permesso);
		return entity;
	}
	
	public Permesso aggiorna(Permesso permesso) {
		Permesso entity = update(permesso, permesso.getId());
		return entity;
	}
	
	public Permesso elimina(Permesso permesso) {
		Permesso entity = delete(permesso.getId());
		return entity;
	}

	@Override
	protected void updateValues(Permesso oldEntity, Permesso entity) {
		oldEntity.setCategoria(entity.getCategoria());
		oldEntity.setIdPadre(entity.getIdPadre());
		oldEntity.setNome(entity.getNome());
	}

}

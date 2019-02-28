package it.ltc.database.dao.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.SedeUtenti;

public class SedeUtentiDao extends CRUDDao<SedeUtenti> {

	public SedeUtentiDao() {
		this(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME);
	}
	
	public SedeUtentiDao(String persistenceUnit) {
		super(persistenceUnit, SedeUtenti.class);
	}
	
	public List<SedeUtenti> trovaTutti() {
		List<SedeUtenti> entities = findAll();
		return entities;
	}
	
	public SedeUtenti trovaDaID(int id) {
		SedeUtenti entity = findByID(id);
		return entity;
	}
	
	public SedeUtenti inserisci(SedeUtenti sede) {
		SedeUtenti entity = insert(sede);
		return entity;
	}
	
	public SedeUtenti aggiorna(SedeUtenti sede) {
		SedeUtenti entity = update(sede, sede.getId());
		return entity;
	}
	
	public SedeUtenti elimina(SedeUtenti sede) {
		SedeUtenti entity = delete(sede.getId());
		return entity;
	}

	@Override
	protected void updateValues(SedeUtenti oldEntity, SedeUtenti entity) {
		oldEntity.setSede(entity.getSede());		
	}

}

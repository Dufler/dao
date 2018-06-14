package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Operatore;

public class OperatoreDao extends CRUDDao<Operatore> {
	
	public OperatoreDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public OperatoreDao(String persistenceUnit) {
		super(persistenceUnit, Operatore.class);
	}

	public List<Operatore> trovaTutti() {
		List<Operatore> list = findAll();
		return list;
	}

	public Operatore trova(String username) {
		Operatore entity = findByID(username);
		return entity;
	}

	public Operatore inserisci(Operatore operatore) {
		Operatore entity = insert(operatore);
		return entity;
	}

	public Operatore aggiorna(Operatore operatore) {
		Operatore entity = update(operatore, operatore.getUsername());
		return entity;
	}

	public Operatore elimina(Operatore operatore) {
		Operatore entity = delete(operatore.getUsername());
		return entity;
	}

	protected void updateValues(Operatore oldEntity, Operatore entity) {
		oldEntity.setAssociazione(entity.getAssociazione());		
	}

}
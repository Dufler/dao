package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.Corriere;

public class CorriereDao extends ReadOnlyDao<Corriere> {

	public CorriereDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CorriereDao(String persistenceUnit) {
		super(persistenceUnit, Corriere.class);
	}
	
	public Corriere trovaDaID(int id) {
		Corriere corriere = findByID(id);
		return corriere;
	}
	
	public List<Corriere> trovaTutti() {
		List<Corriere> entities = findAll();
		return entities;
	}

}

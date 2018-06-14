package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.CorriereServizio;

public class CorriereServizioDao extends ReadOnlyDao<CorriereServizio> {

	public CorriereServizioDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CorriereServizioDao(String persistenceUnit) {
		super(persistenceUnit, CorriereServizio.class);
	}
	
	public List<CorriereServizio> trovaTutti() {
		List<CorriereServizio> list = findAll();
		return list;
	}

}

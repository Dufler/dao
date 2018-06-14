package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.Sede;

public class SedeDao extends ReadOnlyDao<Sede> {

	public SedeDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public SedeDao(String persistenceUnit) {
		super(persistenceUnit, Sede.class);
	}
	
	public Sede trovaDaID(int id) {
		Sede sede = findByID(id);
		return sede;
	}
	
	public List<Sede> trovaTutte() {
		List<Sede> entities = findAll();
		return entities;
	}

}

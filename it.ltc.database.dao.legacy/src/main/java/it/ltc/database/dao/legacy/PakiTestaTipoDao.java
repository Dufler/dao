package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.PakiTestaTipo;

public class PakiTestaTipoDao extends ReadOnlyDao<PakiTestaTipo> {

	public PakiTestaTipoDao(String persistenceUnit) {
		super(persistenceUnit, PakiTestaTipo.class);
	}
	
	public PakiTestaTipo trovaDaCodice(String codice) {
		PakiTestaTipo entity = findByID(codice);
		return entity;
	}
	
	public List<PakiTestaTipo> trovaTutti() {
		List<PakiTestaTipo> entities = findAll();
		return entities;
	}

}

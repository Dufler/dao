package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.TestataOrdiniTipo;

public class TestataOrdiniTipoDao extends ReadOnlyDao<TestataOrdiniTipo> {

	public TestataOrdiniTipoDao(String persistenceUnit) {
		super(persistenceUnit, TestataOrdiniTipo.class);
	}
	
	public TestataOrdiniTipo trovaTipoDaCodice(String codice) {
		TestataOrdiniTipo entity = findOnlyOneEqualTo("codice", codice);
		return entity;
	}
	
	public List<TestataOrdiniTipo> trovaTutti() {
		List<TestataOrdiniTipo> entities = findAll();
		return entities;
	}

}

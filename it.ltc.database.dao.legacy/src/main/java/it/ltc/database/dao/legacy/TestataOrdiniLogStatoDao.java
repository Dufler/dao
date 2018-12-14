package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.TestataOrdiniLogStato;

public class TestataOrdiniLogStatoDao extends ReadOnlyDao<TestataOrdiniLogStato> {

	public TestataOrdiniLogStatoDao(String persistenceUnit) {
		super(persistenceUnit, TestataOrdiniLogStato.class);
	}
	
	public List<TestataOrdiniLogStato> trovaStati(int idTestata) {
		List<TestataOrdiniLogStato> entities = findAllEqualTo("idTestaSped", idTestata);
		return entities;
	}

}

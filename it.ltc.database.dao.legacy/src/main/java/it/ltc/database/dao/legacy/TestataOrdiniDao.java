package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.TestataOrdini;

public class TestataOrdiniDao extends CRUDDao<TestataOrdini> {

	public TestataOrdiniDao(String persistenceUnit) {
		super(persistenceUnit, TestataOrdini.class);
	}
	
	public TestataOrdini trovaDaID(int id) {
		TestataOrdini entity = findByID(id);
		return entity;
	}
	
	public TestataOrdini trovaDaRiferimento(String riferimento) {
		TestataOrdini entity = findFirstOneEqualTo("rifOrdineCli", riferimento);
		return entity;
	}

	@Override
	protected void updateValues(TestataOrdini oldEntity, TestataOrdini entity) {
		// TODO Auto-generated method stub
		
	}

}

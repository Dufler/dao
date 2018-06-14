package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ArtiBar;

public class ArtibarDao extends CRUDDao<ArtiBar> {

	public ArtibarDao(String persistenceUnit) {
		super(persistenceUnit, ArtiBar.class);
	}
	
	public ArtiBar trovaDaSKU(String sku) {
        ArtiBar articolo = findFirstOneEqualTo("codiceArticolo", sku);
		return articolo;
	}

	@Override
	protected void updateValues(ArtiBar oldEntity, ArtiBar entity) {
		// TODO Auto-generated method stub
		
	}

}

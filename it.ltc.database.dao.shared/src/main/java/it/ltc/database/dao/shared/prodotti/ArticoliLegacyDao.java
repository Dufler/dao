package it.ltc.database.dao.shared.prodotti;

import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.model.shared.dao.IProdottoDaoBase;

public class ArticoliLegacyDao extends ArticoliDao implements IProdottoDaoBase<Articoli> {

	public ArticoliLegacyDao(String persistenceUnit) {
		super(persistenceUnit);
	}

}

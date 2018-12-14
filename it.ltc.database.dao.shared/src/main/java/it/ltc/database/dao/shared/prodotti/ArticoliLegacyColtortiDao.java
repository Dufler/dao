package it.ltc.database.dao.shared.prodotti;

import it.ltc.database.dao.legacy.coltorti.ArticoliColtortiDao;
import it.ltc.database.model.legacy.coltorti.ArticoliColtorti;
import it.ltc.model.shared.dao.IProdottoDaoBase;

public class ArticoliLegacyColtortiDao extends ArticoliColtortiDao implements IProdottoDaoBase<ArticoliColtorti> {

	public ArticoliLegacyColtortiDao(String persistenceUnit) {
		super(persistenceUnit);
	}

}

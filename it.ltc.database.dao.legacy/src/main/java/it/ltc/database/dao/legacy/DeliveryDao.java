package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.Delivery;

public class DeliveryDao extends CRUDDao<Delivery> {

	public DeliveryDao(String persistenceUnit) {
		super(persistenceUnit, Delivery.class);
	}
	
	public Delivery trovaDaID(int id) {
		Delivery entity = findByID(id);
		return entity;
	}

	@Override
	protected void updateValues(Delivery oldEntity, Delivery entity) {
		oldEntity.setCorriere(entity.getCorriere());
		oldEntity.setTotalePeso(entity.getTotalePeso());
		oldEntity.setTotaleSpedizioni(entity.getTotaleSpedizioni());
		oldEntity.setTotaleVolume(entity.getTotaleVolume());
		oldEntity.setUtente(entity.getUtente());
	}

	public List<Delivery> trovaDaFiltro(List<CondizioneWhere> condizioni, int i, String orderColumn, boolean b) {
		List<Delivery> entities = findAll(condizioni, 100, orderColumn, b);
		return entities;
	}

}

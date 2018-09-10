package it.ltc.database.dao.legacy.bundle;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.bundle.CasseKIT;

public class CasseKitDao extends CRUDDao<CasseKIT> {

	public CasseKitDao(String persistenceUnit) {
		super(persistenceUnit, CasseKIT.class);
	}
	
	public CasseKIT inserisci(CasseKIT kit) {
		CasseKIT entity = insert(kit);
		return entity;
	}
	
	public CasseKIT aggiorna(CasseKIT kit) {
		CasseKIT entity = update(kit, kit.getId());
		return entity;
	}
	
	public CasseKIT elimina(CasseKIT kit) {
		CasseKIT entity = delete(kit.getId());
		return entity;
	}
	
	public CasseKIT trovaDaID(int id) {
		CasseKIT entity = findByID(id);
		return entity;
	}
	
	public CasseKIT trovaDaSkuBundleEProdotto(String bundle, String prodotto) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("skuBundle", bundle));
		conditions.add(new CondizioneWhere("skuProdotto", prodotto));
		CasseKIT entity = findJustOne(conditions);
		return entity;
	}
	
	public List<CasseKIT> trovaDaSKUBundle(String sku) {
		List<CasseKIT> entitites = findAllEqualTo("skuBundle", sku);
		return entitites;
	}

	@Override
	protected void updateValues(CasseKIT oldEntity, CasseKIT entity) {
		oldEntity.setQuantitaProdotto(entity.getQuantitaProdotto());
		oldEntity.setSkuBundle(entity.getSkuBundle());
		oldEntity.setSkuProdotto(entity.getSkuProdotto());
	}

}

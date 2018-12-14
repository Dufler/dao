package it.ltc.database.dao.legacy.bundle;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.bundle.Casse;

public class CasseDao extends CRUDDao<Casse> {

	public CasseDao(String persistenceUnit) {
		super(persistenceUnit, Casse.class);
	}
	
	public Casse inserisci(Casse kit) {
		Casse entity = insert(kit);
		return entity;
	}
	
	public Casse aggiorna(Casse kit) {
		Casse entity = update(kit, kit.getId());
		return entity;
	}
	
	public Casse elimina(Casse kit) {
		Casse entity = delete(kit.getId());
		return entity;
	}
	
	public List<Casse> trovaTutti() {
		List<Casse> entitites = findAll();
		return entitites;
	}
	
	public Casse trovaDaID(int id) {
		Casse entity = findByID(id);
		return entity;
	}
	
	public Casse trovaDaSkuBundleEProdotto(String bundle, String prodotto) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("skuBundle", bundle));
		conditions.add(new CondizioneWhere("skuProdotto", prodotto));
		Casse entity = findJustOne(conditions);
		return entity;
	}
	
	public List<Casse> trovaDaSKUBundle(String sku) {
		List<Casse> entitites = findAllEqualTo("skuBundle", sku);
		return entitites;
	}

	@Override
	protected void updateValues(Casse oldEntity, Casse entity) {
		oldEntity.setQuantitaProdotto(entity.getQuantitaProdotto());
		oldEntity.setSkuBundle(entity.getSkuBundle());
		oldEntity.setSkuProdotto(entity.getSkuProdotto());
	}

}

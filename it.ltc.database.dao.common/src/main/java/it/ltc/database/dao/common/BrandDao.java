package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Brand;

public class BrandDao extends CRUDDao<Brand> {

	public BrandDao(String persistenceUnit) {
		super(persistenceUnit, Brand.class);
	}
	
	public Brand trovaDaID(int id) {
		Brand entity = findByID(id);
		return entity;
	}
	
	public Brand trovaDaNome(String nome) {
		Brand entity = findOnlyOneEqualTo("nome", nome);
		return entity;
	}
	
	public Brand inserisci(Brand brand) {
		Brand entity = insert(brand);
		return entity;
	}
	
	public Brand aggiorna(Brand brand) {
		Brand entity = update(brand, brand.getId());
		return entity;
	}
	
	public Brand elimina(Brand brand) {
		Brand entity = delete(brand.getId());
		return entity;
	}

	@Override
	protected void updateValues(Brand oldEntity, Brand entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setNome(entity.getNome());
	}

}

package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ColliPack;

public class ColliPackDao extends CRUDDao<ColliPack> {

	public ColliPackDao(String persistenceUnit) {
		super(persistenceUnit, ColliPack.class);
	}

	@Override
	protected void updateValues(ColliPack oldEntity, ColliPack entity) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ColliPack> inserisci(List<ColliPack> collipacks) {
		List<ColliPack> entities = insert(collipacks);
		return entities;
	}
	
	public List<ColliPack> elimina(List<ColliPack> entities) {
		List<Object> keys = new LinkedList<>();
		for (ColliPack entity : entities)
			keys.add(entity.getIdColliPack());
		List<ColliPack> removedEntities = delete(keys);
		return removedEntities;
	}
	
	public ColliPack trovaDaID(int id) {
		ColliPack entity = findByID(id);
		return entity;
	}
	
	public List<ColliPack> trovaProdottiNelCarico(int idCarico) {
		List<ColliPack> entities = findAllEqualTo("idTestaPaki", idCarico);
		return entities;
	}
	
	public List<ColliPack> trovaProdottiNelCollo(String keyColloCar) {
		List<ColliPack> entities = findAllEqualTo("nrIdColloPk", keyColloCar);
		return entities;
	}
	
	public boolean isProdottoPresenteInMagazzino(String sku) {
		ColliPack entity = findFirstOneEqualTo("codArtStr", sku);
        boolean presente = entity != null;
		return presente;
	}


}

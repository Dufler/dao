package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.seriale.ColliPackConSeriale;

public class ColliPackSerialiDao extends CRUDDao<ColliPackConSeriale> {

	public ColliPackSerialiDao(String persistenceUnit) {
		super(persistenceUnit, ColliPackConSeriale.class);
	}

	@Override
	protected void updateValues(ColliPackConSeriale oldEntity, ColliPackConSeriale entity) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ColliPackConSeriale> inserisci(List<ColliPackConSeriale> collipacks) {
		List<ColliPackConSeriale> entities = insert(collipacks);
		return entities;
	}
	
	public List<ColliPackConSeriale> elimina(List<ColliPackConSeriale> entities) {
		List<Object> keys = new LinkedList<>();
		for (ColliPackConSeriale entity : entities)
			keys.add(entity.getIdColliPack());
		List<ColliPackConSeriale> removedEntities = delete(keys);
		return removedEntities;
	}
	
	public List<ColliPackConSeriale> trovaProdottiNelCarico(int idCarico) {
		List<ColliPackConSeriale> entities = findAllEqualTo("idTestaPaki", idCarico);
		return entities;
	}
	
	public boolean isProdottoPresenteInMagazzino(String sku) {
		ColliPackConSeriale entity = findOnlyOneEqualTo("codArtStr", sku);
        boolean presente = entity != null;
		return presente;
	}

}

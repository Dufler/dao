package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Feature;

public class FeatureDao extends CRUDDao<Feature> {
	
	public FeatureDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public FeatureDao(String persistenceUnit) {
		super(persistenceUnit, Feature.class);
	}
	
	public Feature trovaDaNome(String nome) {
		Feature feature = findByID(nome);
		return feature;
	}
	
	public List<Feature> trovaTutte() {
		List<Feature> entities = findAll();
		return entities;
	}
	
	public Feature inserisci(Feature funzione) {
		Feature entity = insert(funzione);
		return entity;
	}
	
	public Feature aggiorna(Feature funzione) {
		Feature entity = update(funzione, funzione.getPerspectiveid());
		return entity;
	}
	
	public Feature elimina(Feature funzione) {
		Feature entity = delete(funzione.getPerspectiveid());
		return entity;
	}

	@Override
	protected void updateValues(Feature oldEntity, Feature entity) {
		oldEntity.setIcona(entity.getIcona());
		oldEntity.setNome(entity.getNome());
		oldEntity.setPermessoid(entity.getPermessoid());
		oldEntity.setVersione(entity.getVersione());
		oldEntity.setFeatureid(entity.getFeatureid());
		oldEntity.setColore(entity.getColore());
	}

}

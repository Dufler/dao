package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.Feature;

public class FeatureDao extends ReadOnlyDao<Feature> {
	
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

}

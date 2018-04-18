package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Feature;

public class FeatureDao extends Dao {
	
	private static FeatureDao instance;

	private FeatureDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static FeatureDao getInstance() {
		if (null == instance) {
			instance = new FeatureDao();
		}
		return instance;
	}
	
	public Feature findByNome(String nome) {
		Feature feature = em.find(Feature.class, nome);
		return feature;
	}
	
	public List<Feature> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Feature> criteria = cb.createQuery(Feature.class);
        Root<Feature> member = criteria.from(Feature.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

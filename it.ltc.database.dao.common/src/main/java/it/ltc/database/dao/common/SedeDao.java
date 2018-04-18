package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Sede;

public class SedeDao extends Dao {
	
	private static SedeDao instance;

	private SedeDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static SedeDao getInstance() {
		if (null == instance) {
			instance = new SedeDao();
		}
		return instance;
	}
	
	public Sede findByID(int id) {
		Sede sede = em.find(Sede.class, id);
		return sede;
	}
	
	public List<Sede> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sede> criteria = cb.createQuery(Sede.class);
        Root<Sede> member = criteria.from(Sede.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

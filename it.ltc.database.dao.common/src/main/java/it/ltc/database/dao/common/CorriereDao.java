package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Corriere;

public class CorriereDao extends Dao {

	private static CorriereDao instance;

	private CorriereDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static CorriereDao getInstance() {
		if (null == instance) {
			instance = new CorriereDao();
		}
		return instance;
	}
	
	public Corriere findByID(int id) {
		Corriere corriere = em.find(Corriere.class, id);
		return corriere;
	}
	
	public List<Corriere> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Corriere> criteria = cb.createQuery(Corriere.class);
        Root<Corriere> member = criteria.from(Corriere.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

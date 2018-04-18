package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.TrackingStato;

public class TrackingStatoDao extends Dao {
	
	private static TrackingStatoDao instance;

	private TrackingStatoDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static TrackingStatoDao getInstance() {
		if (null == instance) {
			instance = new TrackingStatoDao();
		}
		return instance;
	}
	
	public TrackingStato findByID(String codice) {
		TrackingStato stato = em.find(TrackingStato.class, codice);
		return stato;
	}
	
	public List<TrackingStato> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrackingStato> criteria = cb.createQuery(TrackingStato.class);
        Root<TrackingStato> member = criteria.from(TrackingStato.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

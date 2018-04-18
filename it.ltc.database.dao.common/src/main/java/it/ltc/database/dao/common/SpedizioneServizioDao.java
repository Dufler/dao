package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.SpedizioneServizio;

public class SpedizioneServizioDao extends Dao {
	
	private static SpedizioneServizioDao instance;

	private SpedizioneServizioDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static SpedizioneServizioDao getInstance() {
		if (null == instance) {
			instance = new SpedizioneServizioDao();
		}
		return instance;
	}
	
	public SpedizioneServizio findByID(String codice) {
		SpedizioneServizio servizio = em.find(SpedizioneServizio.class, codice);
		return servizio;
	}
	
	public List<SpedizioneServizio> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpedizioneServizio> criteria = cb.createQuery(SpedizioneServizio.class);
        Root<SpedizioneServizio> member = criteria.from(SpedizioneServizio.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

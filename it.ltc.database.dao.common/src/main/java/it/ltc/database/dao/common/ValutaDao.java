package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Valuta;

public class ValutaDao extends Dao {
	
	private static ValutaDao instance;

	private ValutaDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static ValutaDao getInstance() {
		if (null == instance) {
			instance = new ValutaDao();
		}
		return instance;
	}
	
	public Valuta findByCodice(String codice) {
		Valuta valuta = em.find(Valuta.class, codice);
		return valuta;
	}
	
	public List<Valuta> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Valuta> criteria = cb.createQuery(Valuta.class);
        Root<Valuta> member = criteria.from(Valuta.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

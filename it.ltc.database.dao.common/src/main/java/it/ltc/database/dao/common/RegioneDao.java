package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Regione;

public class RegioneDao extends Dao {
	
	private static RegioneDao instance;

	private RegioneDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static RegioneDao getInstance() {
		if (null == instance) {
			instance = new RegioneDao();
		}
		return instance;
	}
	
	/**
	 * Restituisce la regione con la sigla specificata utilizzando il datasource di default.
	 * La sigla è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La regione corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Regione findBySigla(String sigla) {
		Regione regione = em.find(Regione.class, sigla);
		return regione;
	}
	
	/**
	 * Restituisce la lista di tutte le regioni esistenti utilizzando il datasource di default.
	 * @return La lista di tutte le regioni.
	 */
	public List<Regione> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Regione> criteria = cb.createQuery(Regione.class);
        Root<Regione> member = criteria.from(Regione.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

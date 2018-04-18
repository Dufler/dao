package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Provincia;

/**
 * Dao per le province usabile da tutte le applicazioni web.
 * Usa il datasource di default per accedere ai dati.
 * @author Damiano
 *
 */
public class ProvinciaDao extends Dao {
	
	private static ProvinciaDao instance;

	private ProvinciaDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static ProvinciaDao getInstance() {
		if (null == instance) {
			instance = new ProvinciaDao();
		}
		return instance;
	}
	
	/**
	 * Restituisce la provincia con la sigla specificata utilizzando il datasource di default.
	 * La sigla è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La provincia corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Provincia findBySigla(String sigla) {
		Provincia provincia = em.find(Provincia.class, sigla);
		return provincia;
	}
	
	/**
	 * Restituisce la lista di tutte le province esistenti utilizzando il datasource di default.
	 * @return La lista di tutte le province.
	 */
	public List<Provincia> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Provincia> criteria = cb.createQuery(Provincia.class);
        Root<Provincia> member = criteria.from(Provincia.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

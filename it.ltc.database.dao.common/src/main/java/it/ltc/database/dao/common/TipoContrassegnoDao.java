package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.SpedizioneContrassegnoTipo;

public class TipoContrassegnoDao extends Dao {
	
	private static TipoContrassegnoDao instance;

	private TipoContrassegnoDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static TipoContrassegnoDao getInstance() {
		if (null == instance) {
			instance = new TipoContrassegnoDao();
		}
		return instance;
	}
	
	/**
	 * Restituisce il tipo di contrassegno con il codice specificato utilizzando il datasource di default.
	 * Il codice è la <code>Primary Key</code> per la tabella.
	 * @param codice
	 * @return Il tipo di contrassegno corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public SpedizioneContrassegnoTipo findByCodice(String codice) {
		SpedizioneContrassegnoTipo tipo = em.find(SpedizioneContrassegnoTipo.class, codice);
		return tipo;
	}

	/**
	 * Restituisce la lista di tutti i tipi di contrassegno esistenti utilizzando il datasource di default.
	 * @return La lista di tutti i tipi di contrassegno.
	 */
	public List<SpedizioneContrassegnoTipo> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpedizioneContrassegnoTipo> criteria = cb.createQuery(SpedizioneContrassegnoTipo.class);
        Root<SpedizioneContrassegnoTipo> member = criteria.from(SpedizioneContrassegnoTipo.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

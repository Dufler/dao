package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Nazione;

/**
 * Dao per le nazioni usabile da tutte le applicazioni web.
 * Usa il datasource di default per accedere ai dati.
 * @author Damiano
 *
 */
public class NazioneDao extends Dao {

	private static NazioneDao instance;

	private NazioneDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static NazioneDao getInstance() {
		if (null == instance) {
			instance = new NazioneDao();
		}
		return instance;
	}
	
	/**
	 * Restituisce la nazione con il codice ISO3 specificato utilizzando il datasource di default.
	 * Il codice ISO3 è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La nazione corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Nazione findByCodiceISO3(String iso3) {
		Nazione nazione = em.find(Nazione.class, iso3);
		return nazione;
	}
	
	/**
	 * Restituisce la lista di tutte le nazioni esistenti utilizzando il datasource di default.
	 * @return La lista di tutte le nazioni.
	 */
	public List<Nazione> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Nazione> criteria = cb.createQuery(Nazione.class);
        Root<Nazione> member = criteria.from(Nazione.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}
	
}

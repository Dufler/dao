package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.Nazione;

/**
 * Dao per le nazioni usabile da tutte le applicazioni web.
 * Usa il datasource di default per accedere ai dati.
 * @author Damiano
 *
 */
public class NazioneDao extends ReadOnlyDao<Nazione> {

	public NazioneDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public NazioneDao(String persistenceUnit) {
		super(persistenceUnit, Nazione.class);
	}
	
	/**
	 * Restituisce la nazione con il codice ISO3 specificato utilizzando il datasource di default.
	 * Il codice ISO3 è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La nazione corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Nazione trovaDaCodiceISO3(String iso3) {
		Nazione nazione = findByID(iso3);
		return nazione;
	}
	
	/**
	 * Restituisce la lista di tutte le nazioni esistenti utilizzando il datasource di default.
	 * @return La lista di tutte le nazioni.
	 */
	public List<Nazione> trovaTutte() {
		List<Nazione> entities = findAll();
		entities.sort(null); //Le ordino per nome.
        return entities;
	}
	
}

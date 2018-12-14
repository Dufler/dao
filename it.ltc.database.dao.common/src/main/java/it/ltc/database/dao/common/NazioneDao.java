package it.ltc.database.dao.common;

import java.util.HashMap;
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
	
	private final HashMap<String, Nazione> mappaISO3;
	private final HashMap<String, Nazione> mappaISO2;

	public NazioneDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public NazioneDao(String persistenceUnit) {
		super(persistenceUnit, Nazione.class);
		mappaISO2 = new HashMap<>();
		mappaISO3 = new HashMap<>();
	}
	
	private Nazione aggiungiMappe(Nazione nazione) {
		if (nazione != null) {
			mappaISO2.put(nazione.getCodiceIsoDue(), nazione);
			mappaISO3.put(nazione.getCodiceIsoTre(), nazione);
		}
		return nazione;
	}
	
	/**
	 * Restituisce la nazione con il codice ISO3 specificato utilizzando il datasource di default.
	 * Il codice ISO3 è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La nazione corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Nazione trovaDaCodiceISO3(String iso3) {
		Nazione nazione = mappaISO3.containsKey(iso3) ? mappaISO3.get(iso3) : aggiungiMappe(findByID(iso3));
		return nazione;
	}
	
	/**
	 * Restituisce la nazione con il codice ISO2 specificato utilizzando il datasource di default.
	 * Il codice ISO2 non è la <code>Primary Key</code> per la tabella ma è comunque univoco.
	 * @param sigla
	 * @return La nazione corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Nazione trovaDaCodiceISO2(String iso2) {
		Nazione nazione = mappaISO2.containsKey(iso2) ? mappaISO2.get(iso2) : aggiungiMappe(findOnlyOneEqualTo("codiceIsoDue", iso2));
		return nazione;
	}
	
	public Nazione trovaDaNome(String nome) {
		Nazione nazione = findOnlyOneEqualTo("nome", nome);
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

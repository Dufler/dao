package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.SpedizioneContrassegnoTipo;

public class TipoContrassegnoDao extends ReadOnlyDao<SpedizioneContrassegnoTipo> {

	public TipoContrassegnoDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public TipoContrassegnoDao(String persistenceUnit) {
		super(persistenceUnit, SpedizioneContrassegnoTipo.class);
	}
	
	/**
	 * Restituisce il tipo di contrassegno con il codice specificato utilizzando il datasource di default.
	 * Il codice è la <code>Primary Key</code> per la tabella.
	 * @param codice
	 * @return Il tipo di contrassegno corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public SpedizioneContrassegnoTipo trovaDaCodice(String codice) {
		SpedizioneContrassegnoTipo tipo = findByID(codice);
		return tipo;
	}

	/**
	 * Restituisce la lista di tutti i tipi di contrassegno esistenti utilizzando il datasource di default.
	 * @return La lista di tutti i tipi di contrassegno.
	 */
	public List<SpedizioneContrassegnoTipo> trovaTutti() {
		List<SpedizioneContrassegnoTipo> entities = findAll();
		return entities;
	}

}

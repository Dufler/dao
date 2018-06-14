package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.SpedizioneServizio;

public class SpedizioneServizioDao extends ReadOnlyDao<SpedizioneServizio> {

	public SpedizioneServizioDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public SpedizioneServizioDao(String persistenceUnit) {
		super(persistenceUnit, SpedizioneServizio.class);
	}
	
	public SpedizioneServizio trovaDaCodice(String codice) {
		SpedizioneServizio servizio = findByID(codice);
		return servizio;
	}
	
	public List<SpedizioneServizio> trovaTutti() {
		List<SpedizioneServizio> entities = findAll();
		return entities;
	}

}

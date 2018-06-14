package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.TrackingStato;

public class TrackingStatoDao extends ReadOnlyDao<TrackingStato> {

	public TrackingStatoDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public TrackingStatoDao(String persistenceUnit) {
		super(persistenceUnit, TrackingStato.class);
	}
	
	public TrackingStato trovaDaCodice(String codice) {
		TrackingStato stato = findByID(codice);
		return stato;
	}
	
	public List<TrackingStato> trovaTutti() {
		List<TrackingStato> entities = findAll();
		return entities;
	}

}

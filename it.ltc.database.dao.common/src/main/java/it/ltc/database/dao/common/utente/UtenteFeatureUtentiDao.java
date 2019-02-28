package it.ltc.database.dao.common.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.UtenteFeaturesJoinUtenti;
import it.ltc.database.model.utente.UtenteFeaturesJoinPKUtenti;

public class UtenteFeatureUtentiDao extends CRUDDao<UtenteFeaturesJoinUtenti> {

	public UtenteFeatureUtentiDao(String persistenceUnit) {
		super(persistenceUnit, UtenteFeaturesJoinUtenti.class);
	}
	
	public List<UtenteFeaturesJoinUtenti> trovaTutti() {
		List<UtenteFeaturesJoinUtenti> entities = findAll();
		return entities;
	}
	
	public List<UtenteFeaturesJoinUtenti> trovaDaUtente(String username) {
		List<UtenteFeaturesJoinUtenti> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtenteFeaturesJoinUtenti inserisci(UtenteFeaturesJoinUtenti join) {
		UtenteFeaturesJoinUtenti entity = insert(join);
		return entity;
	}
	
	public UtenteFeaturesJoinUtenti elimina(String utente, String feature) {
		UtenteFeaturesJoinPKUtenti pk = getPK(utente, feature);
		UtenteFeaturesJoinUtenti entity = delete(pk);
		return entity;
	}
	
	public UtenteFeaturesJoinPKUtenti getPK(String utente, String feature) {
		UtenteFeaturesJoinPKUtenti pk = new UtenteFeaturesJoinPKUtenti();
		pk.setUtente(utente);
		pk.setFeature(feature);
		return pk;
	}

	@Override
	protected void updateValues(UtenteFeaturesJoinUtenti oldEntity, UtenteFeaturesJoinUtenti entity) {
		//DO NOTHING!
	}

}

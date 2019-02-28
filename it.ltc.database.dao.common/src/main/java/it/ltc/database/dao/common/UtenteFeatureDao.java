package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.UtenteFeaturesJoin;
import it.ltc.database.model.centrale.UtenteFeaturesJoinPK;

public class UtenteFeatureDao extends CRUDDao<UtenteFeaturesJoin> {

	public UtenteFeatureDao(String persistenceUnit) {
		super(persistenceUnit, UtenteFeaturesJoin.class);
	}
	
	public List<UtenteFeaturesJoin> trovaTutti() {
		List<UtenteFeaturesJoin> entities = findAll();
		return entities;
	}
	
	public List<UtenteFeaturesJoin> trovaDaUtente(String username) {
		List<UtenteFeaturesJoin> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtenteFeaturesJoin inserisci(UtenteFeaturesJoin join) {
		UtenteFeaturesJoin entity = insert(join);
		return entity;
	}
	
	public UtenteFeaturesJoin elimina(String utente, String feature) {
		UtenteFeaturesJoinPK pk = getPK(utente, feature);
		UtenteFeaturesJoin entity = delete(pk);
		return entity;
	}
	
	public UtenteFeaturesJoinPK getPK(String utente, String feature) {
		UtenteFeaturesJoinPK pk = new UtenteFeaturesJoinPK();
		pk.setUtente(utente);
		pk.setFeature(feature);
		return pk;
	}

	@Override
	protected void updateValues(UtenteFeaturesJoin oldEntity, UtenteFeaturesJoin entity) {
		//DO NOTHING!
	}

}

package it.ltc.database.dao.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.FeatureUtenti;

public class FeatureUtentiDao extends CRUDDao<FeatureUtenti> {
	
	public FeatureUtentiDao() {
		this(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME);
	}

	public FeatureUtentiDao(String persistenceUnit) {
		super(persistenceUnit, FeatureUtenti.class);
	}
	
	public List<FeatureUtenti> trovaTutti() {
		List<FeatureUtenti> entities = findAll();
		return entities;
	}
	
	public FeatureUtenti trovaDaID(String feature) {
		FeatureUtenti entity = findByID(feature);
		return entity;
	}
	
	public FeatureUtenti inserisci(FeatureUtenti feature) {
		FeatureUtenti entity = insert(feature);
		return entity;
	}
	
	public FeatureUtenti aggiorna(FeatureUtenti feature) {
		FeatureUtenti entity = update(feature, feature.getFeatureid());
		return entity;
	}
	
	public FeatureUtenti elimina(FeatureUtenti feature) {
		FeatureUtenti entity = delete(feature.getFeatureid());
		return entity;
	}

	@Override
	protected void updateValues(FeatureUtenti oldEntity, FeatureUtenti entity) {
		oldEntity.setNome(entity.getNome());
		oldEntity.setVersione(entity.getVersione());
	}

}

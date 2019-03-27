package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Stagioni;

public class StagioniDao extends CRUDDao<Stagioni> {

	public StagioniDao(String persistenceUnit) {
		super(persistenceUnit, Stagioni.class);
	}
	
	public Stagioni trovaDaID(int id) {
		Stagioni entity = findByID(id);
		return entity;
	}
	
	public Stagioni trovaDaCodice(String codice) {
		Stagioni entity = findFirstOneEqualTo("codice", codice);
		return entity;
	}
	
	public Stagioni trovaDaDescrizione(String descrizione) {
		Stagioni entity = findFirstOneEqualTo("descrizione", descrizione);
		return entity;
	}
	
	public List<Stagioni> trovaTutti() {
		List<Stagioni> entities = findAll();
		return entities;
	}
	
	public Stagioni inserisci(Stagioni stagione) {
		Stagioni entity = insert(stagione);
		return entity;
	}
	
	public Stagioni aggiorna(Stagioni stagione) {
		Stagioni entity = update(stagione, stagione.getIdStagione());
		return entity;
	}
	
	public Stagioni elimina(Stagioni stagione) {
		Stagioni entity = delete(stagione.getIdStagione());
		return entity;
	}

	@Override
	protected void updateValues(Stagioni oldEntity, Stagioni entity) {
		oldEntity.setCodice(entity.getCodice());
		oldEntity.setDescrizione(entity.getDescrizione());
	}

}

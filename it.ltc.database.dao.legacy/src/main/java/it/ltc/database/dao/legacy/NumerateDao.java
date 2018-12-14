package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.NumerataLegacy;

public class NumerateDao extends CRUDDao<NumerataLegacy> {

	public NumerateDao(String persistenceUnit) {
		super(persistenceUnit, NumerataLegacy.class);
	}
	
	public List<NumerataLegacy> trovaTutte() {
		List<NumerataLegacy> entities = findAll();
		return entities;
	}
	
	public NumerataLegacy trovaDaID(int id) {
		NumerataLegacy entity = findByID(id);
		return entity;
	}
	
	public NumerataLegacy trovaDaCodice(String codice) {
		NumerataLegacy entity = findOnlyOneEqualTo("codice", codice);
		return entity;
	}
	
	public NumerataLegacy inserisci(NumerataLegacy numerata) {
		NumerataLegacy entity = insert(numerata);
		return entity;
	}
	
	public NumerataLegacy aggiorna(NumerataLegacy numerata) {
		NumerataLegacy entity = update(numerata, numerata.getIdNumerata());
		return entity;
	}
	
	public NumerataLegacy elimina(NumerataLegacy numerata) {
		NumerataLegacy entity = delete(numerata.getIdNumerata());
		return entity;
	}

	@Override
	protected void updateValues(NumerataLegacy oldEntity, NumerataLegacy entity) {
		oldEntity.setCodice(entity.getCodice());
		oldEntity.setTaglia1(entity.getTaglia1());
	}

}

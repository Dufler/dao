package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Numerata;

public class NumerateDao extends CRUDDao<Numerata> {

	public NumerateDao(String persistenceUnit) {
		super(persistenceUnit, Numerata.class);
	}
	
	public Numerata trovaDaID(int id) {
		Numerata entity = findByID(id);
		return entity;
	}
	
	public Numerata trovaDaCodice(String codice) {
		Numerata entity = findOnlyOneEqualTo("codice", codice);
		return entity;
	}
	
	public Numerata inserisci(Numerata numerata) {
		Numerata entity = insert(numerata);
		return entity;
	}
	
	public Numerata aggiorna(Numerata numerata) {
		Numerata entity = update(numerata, numerata.getIdNumerata());
		return entity;
	}
	
	public Numerata elimina(Numerata numerata) {
		Numerata entity = delete(numerata.getIdNumerata());
		return entity;
	}

	@Override
	protected void updateValues(Numerata oldEntity, Numerata entity) {
		oldEntity.setCodice(entity.getCodice());
		oldEntity.setTaglia1(entity.getTaglia1());
	}

}

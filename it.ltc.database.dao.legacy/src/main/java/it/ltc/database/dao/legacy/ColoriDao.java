package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Colori;

public class ColoriDao extends CRUDDao<Colori> {

	public ColoriDao(String persistenceUnit) {
		super(persistenceUnit, Colori.class);
	}
	
	public Colori trovaDaCodice(String codice) {
		Colori entity = findByID(codice);
		return entity;
	}
	
	public Colori inserisci(Colori colore) {
		Colori entity = insert(colore);
		return entity;
	}
	
	public Colori aggiorna(Colori colore) {
		Colori entity = update(colore, colore.getCodice());
		return entity;
	}
	
	public Colori elimina(Colori colore) {
		Colori entity = delete(colore.getCodice());
		return entity;
	}

	@Override
	protected void updateValues(Colori oldEntity, Colori entity) {
		oldEntity.setDescrizione(entity.getDescrizione());		
	}

}

package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Corrieri;

public class CorrieriDao extends CRUDDao<Corrieri> {

	public CorrieriDao(String persistenceUnit) {
		super(persistenceUnit, Corrieri.class);
	}
	
	public Corrieri trovaDaID(int id) {
		Corrieri entity = findByID(id);
		return entity;
	}
	
	public Corrieri trovaDaCodiceCliente(String codice) {
		Corrieri entity = findFirstOneEqualTo("codiceCliente", codice);
		return entity;
	}
	
	public Corrieri inserisci(Corrieri corriere) {
		Corrieri entity = insert(corriere);
		return entity;
	}
	
	public Corrieri aggiorna(Corrieri corriere) {
		Corrieri entity = update(corriere, corriere.getIdCorriere());
		return entity;
	}
	
	public Corrieri elimina(Corrieri corriere) {
		Corrieri entity = delete(corriere.getIdCorriere());
		return entity;
	}

	@Override
	protected void updateValues(Corrieri oldEntity, Corrieri entity) {
		oldEntity.setCodice(entity.getCodice());
		oldEntity.setCodiceCliente(entity.getCodiceCliente());
		//oldEntity.setCodiceTariffa(entity.getCodiceTariffa());
		oldEntity.setDescrizione(entity.getDescrizione());
		//oldEntity.setPuntoOperatorePartenza(entity.getPuntoOperatorePartenza());
	}

}

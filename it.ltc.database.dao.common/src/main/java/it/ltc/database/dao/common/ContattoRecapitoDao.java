package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.CrmContattoRecapiti;

public class ContattoRecapitoDao extends CRUDDao<CrmContattoRecapiti> {

	public ContattoRecapitoDao(String persistenceUnit) {
		super(persistenceUnit, CrmContattoRecapiti.class);
	}
	
	public CrmContattoRecapiti trovaDaID(int id) {
		CrmContattoRecapiti entity = findByID(id);
		return entity;
	}
	
	public CrmContattoRecapiti inserisci(CrmContattoRecapiti recapito) {
		CrmContattoRecapiti entity = insert(recapito);
		return entity;
	}
	
	public CrmContattoRecapiti aggiorna(CrmContattoRecapiti recapito) {
		CrmContattoRecapiti entity = update(recapito, recapito.getId());
		return entity;
	}
	
	public CrmContattoRecapiti elimina(CrmContattoRecapiti recapito) {
		CrmContattoRecapiti entity = delete(recapito.getId());
		return entity;
	}

	@Override
	protected void updateValues(CrmContattoRecapiti oldEntity, CrmContattoRecapiti entity) {
		oldEntity.setContatto(entity.getContatto());
		oldEntity.setRecapito(entity.getRecapito());
		oldEntity.setTipo(entity.getTipo());
	}

}

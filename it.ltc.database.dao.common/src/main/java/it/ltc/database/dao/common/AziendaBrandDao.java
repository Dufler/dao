package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.AziendaBrand;
import it.ltc.database.model.centrale.AziendaBrandPK;

public class AziendaBrandDao extends CRUDDao<AziendaBrand> {

	public AziendaBrandDao(String persistenceUnit) {
		super(persistenceUnit, AziendaBrand.class);
	}
	
	public AziendaBrand inserisci(AziendaBrand associazione) {
		AziendaBrand entity = insert(associazione);
		return entity;
	}
	
	public AziendaBrand elimina(AziendaBrand associazione) {
		AziendaBrand entity = delete(new AziendaBrandPK(associazione));
		return entity;
	}

	@Override
	protected void updateValues(AziendaBrand oldEntity, AziendaBrand entity) {
		throw new UnsupportedOperationException("E' impossibile aggiornare questa entity.");		
	}

	public AziendaBrand trovaDaID(int idAzienda, int idBrand) {
		AziendaBrandPK pk = new AziendaBrandPK();
		pk.setAzienda(idAzienda);
		pk.setBrand(idBrand);
		return findByID(pk);
	}

}

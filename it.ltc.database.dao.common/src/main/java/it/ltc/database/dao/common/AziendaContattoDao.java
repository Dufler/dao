package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.AziendaContatti;
import it.ltc.database.model.centrale.AziendaContattiPK;

public class AziendaContattoDao extends CRUDDao<AziendaContatti> {
	
	public AziendaContattoDao(String persistenceUnit) {
		super(persistenceUnit, AziendaContatti.class);
	}
	
	public AziendaContatti inserisci(AziendaContatti associazione) {
		AziendaContatti entity = insert(associazione);
		return entity;
	}
	
	public AziendaContatti elimina(AziendaContatti associazione) {
		AziendaContatti entity = delete(new AziendaContattiPK(associazione));
		return entity;
	}

	@Override
	protected void updateValues(AziendaContatti oldEntity, AziendaContatti entity) {
		throw new UnsupportedOperationException("E' impossibile aggiornare questa entity.");		
	}

	public AziendaContatti trovaDaID(int idAzienda, int idContatto) {
		AziendaContattiPK pk = new AziendaContattiPK();
		pk.setAzienda(idAzienda);
		pk.setContatto(idContatto);
		return findByID(pk);
	}

}

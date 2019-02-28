package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.UtenteCommessaJoin;
import it.ltc.database.model.centrale.UtenteCommessaJoinPK;

public class UtenteCommesseDao extends CRUDDao<UtenteCommessaJoin> {

	public UtenteCommesseDao(String persistenceUnit) {
		super(persistenceUnit, UtenteCommessaJoin.class);
	}
	
	public List<UtenteCommessaJoin> trovaTutti() {
		List<UtenteCommessaJoin> entities = findAll();
		return entities;
	}
	
	public List<UtenteCommessaJoin> trovaDaUtente(String username) {
		List<UtenteCommessaJoin> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtenteCommessaJoin inserisci(UtenteCommessaJoin join) {
		UtenteCommessaJoin entity = insert(join);
		return entity;
	}
	
	public UtenteCommessaJoin elimina(String utente, int commessa) {
		UtenteCommessaJoinPK pk = getPK(utente, commessa);
		UtenteCommessaJoin entity = delete(pk);
		return entity;
	}
	
	public UtenteCommessaJoinPK getPK(String utente, int commessa) {
		UtenteCommessaJoinPK pk = new UtenteCommessaJoinPK();
		pk.setUtente(utente);
		pk.setIdCommessa(commessa);
		return pk;
	}

	@Override
	protected void updateValues(UtenteCommessaJoin oldEntity, UtenteCommessaJoin entity) {
		//DO NOTHING!
	}

}

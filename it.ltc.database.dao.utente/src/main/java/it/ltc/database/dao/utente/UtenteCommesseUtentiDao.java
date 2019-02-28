package it.ltc.database.dao.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.UtenteCommessaJoinUtenti;
import it.ltc.database.model.utente.UtenteCommessaJoinPKUtenti;

public class UtenteCommesseUtentiDao extends CRUDDao<UtenteCommessaJoinUtenti> {

	public UtenteCommesseUtentiDao(String persistenceUnit) {
		super(persistenceUnit, UtenteCommessaJoinUtenti.class);
	}
	
	public List<UtenteCommessaJoinUtenti> trovaTutti() {
		List<UtenteCommessaJoinUtenti> entities = findAll();
		return entities;
	}
	
	public List<UtenteCommessaJoinUtenti> trovaDaUtente(String username) {
		List<UtenteCommessaJoinUtenti> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtenteCommessaJoinUtenti inserisci(UtenteCommessaJoinUtenti join) {
		UtenteCommessaJoinUtenti entity = insert(join);
		return entity;
	}
	
	public UtenteCommessaJoinUtenti elimina(String utente, int commessa) {
		UtenteCommessaJoinPKUtenti pk = getPK(utente, commessa);
		UtenteCommessaJoinUtenti entity = delete(pk);
		return entity;
	}
	
	public UtenteCommessaJoinPKUtenti getPK(String utente, int commessa) {
		UtenteCommessaJoinPKUtenti pk = new UtenteCommessaJoinPKUtenti();
		pk.setUtente(utente);
		pk.setIdCommessa(commessa);
		return pk;
	}

	@Override
	protected void updateValues(UtenteCommessaJoinUtenti oldEntity, UtenteCommessaJoinUtenti entity) {
		//DO NOTHING!
	}

}

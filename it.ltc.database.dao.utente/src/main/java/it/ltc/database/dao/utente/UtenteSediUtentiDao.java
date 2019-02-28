package it.ltc.database.dao.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.UtenteSedeJoinUtenti;
import it.ltc.database.model.utente.UtenteSedeJoinPKUtenti;

public class UtenteSediUtentiDao extends CRUDDao<UtenteSedeJoinUtenti> {

	public UtenteSediUtentiDao(String persistenceUnit) {
		super(persistenceUnit, UtenteSedeJoinUtenti.class);
	}
	
	public List<UtenteSedeJoinUtenti> trovaTutti() {
		List<UtenteSedeJoinUtenti> entities = findAll();
		return entities;
	}
	
	public List<UtenteSedeJoinUtenti> trovaDaUtente(String username) {
		List<UtenteSedeJoinUtenti> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtenteSedeJoinUtenti inserisci(UtenteSedeJoinUtenti join) {
		UtenteSedeJoinUtenti entity = insert(join);
		return entity;
	}
	
	public UtenteSedeJoinUtenti elimina(String utente, int sede) {
		UtenteSedeJoinPKUtenti pk = getPK(utente, sede);
		UtenteSedeJoinUtenti entity = delete(pk);
		return entity;
	}
	
	public UtenteSedeJoinPKUtenti getPK(String utente, int sede) {
		UtenteSedeJoinPKUtenti pk = new UtenteSedeJoinPKUtenti();
		pk.setUtente(utente);
		pk.setIdSede(sede);
		return pk;
	}

	@Override
	protected void updateValues(UtenteSedeJoinUtenti oldEntity, UtenteSedeJoinUtenti entity) {
		//DO NOTHING!
	}

}

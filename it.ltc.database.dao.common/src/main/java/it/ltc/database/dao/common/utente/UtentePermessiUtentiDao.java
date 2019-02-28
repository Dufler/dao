package it.ltc.database.dao.common.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.UtentePermessiJoinPKUtenti;
import it.ltc.database.model.utente.UtentePermessiJoinUtenti;

public class UtentePermessiUtentiDao extends CRUDDao<UtentePermessiJoinUtenti> {
	
	public UtentePermessiUtentiDao(String persistenceUnit) {
		super(persistenceUnit, UtentePermessiJoinUtenti.class);
	}
	
	public List<UtentePermessiJoinUtenti> trovaTutti() {
		List<UtentePermessiJoinUtenti> entities = findAll();
		return entities;
	}
	
	public List<UtentePermessiJoinUtenti> trovaDaUtente(String username) {
		List<UtentePermessiJoinUtenti> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtentePermessiJoinUtenti inserisci(UtentePermessiJoinUtenti join) {
		UtentePermessiJoinUtenti entity = insert(join);
		return entity;
	}
	
	public UtentePermessiJoinUtenti elimina(String utente, int permesso) {
		UtentePermessiJoinPKUtenti pk = getPK(utente, permesso);
		UtentePermessiJoinUtenti entity = delete(pk);
		return entity;
	}
	
	public UtentePermessiJoinPKUtenti getPK(String utente, int permesso) {
		UtentePermessiJoinPKUtenti pk = new UtentePermessiJoinPKUtenti();
		pk.setUtente(utente);
		pk.setIdPermesso(permesso);
		return pk;
	}

	@Override
	protected void updateValues(UtentePermessiJoinUtenti oldEntity, UtentePermessiJoinUtenti entity) {
		//DO NOTHING!		
	}

}

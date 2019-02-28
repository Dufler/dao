package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.UtentePermessiJoin;
import it.ltc.database.model.centrale.UtentePermessiJoinPK;

public class UtentePermessiDao extends CRUDDao<UtentePermessiJoin> {
	
	public UtentePermessiDao(String persistenceUnit) {
		super(persistenceUnit, UtentePermessiJoin.class);
	}
	
	public List<UtentePermessiJoin> trovaTutti() {
		List<UtentePermessiJoin> entities = findAll();
		return entities;
	}
	
	public List<UtentePermessiJoin> trovaDaUtente(String username) {
		List<UtentePermessiJoin> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtentePermessiJoin inserisci(UtentePermessiJoin join) {
		UtentePermessiJoin entity = insert(join);
		return entity;
	}
	
	public UtentePermessiJoin elimina(String utente, int permesso) {
		UtentePermessiJoinPK pk = getPK(utente, permesso);
		UtentePermessiJoin entity = delete(pk);
		return entity;
	}
	
	public UtentePermessiJoinPK getPK(String utente, int permesso) {
		UtentePermessiJoinPK pk = new UtentePermessiJoinPK();
		pk.setUtente(utente);
		pk.setIdPermesso(permesso);
		return pk;
	}

	@Override
	protected void updateValues(UtentePermessiJoin oldEntity, UtentePermessiJoin entity) {
		//DO NOTHING!		
	}

}

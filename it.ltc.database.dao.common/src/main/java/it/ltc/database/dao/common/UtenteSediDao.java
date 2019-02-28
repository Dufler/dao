package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.UtenteSedeJoin;
import it.ltc.database.model.centrale.UtenteSedeJoinPK;

public class UtenteSediDao extends CRUDDao<UtenteSedeJoin> {

	public UtenteSediDao(String persistenceUnit) {
		super(persistenceUnit, UtenteSedeJoin.class);
	}
	
	public List<UtenteSedeJoin> trovaTutti() {
		List<UtenteSedeJoin> entities = findAll();
		return entities;
	}
	
	public List<UtenteSedeJoin> trovaDaUtente(String username) {
		List<UtenteSedeJoin> entities = findAllEqualTo("utente", username);
		return entities;
	}
	
	public UtenteSedeJoin inserisci(UtenteSedeJoin join) {
		UtenteSedeJoin entity = insert(join);
		return entity;
	}
	
	public UtenteSedeJoin elimina(String utente, int sede) {
		UtenteSedeJoinPK pk = getPK(utente, sede);
		UtenteSedeJoin entity = delete(pk);
		return entity;
	}
	
	public UtenteSedeJoinPK getPK(String utente, int sede) {
		UtenteSedeJoinPK pk = new UtenteSedeJoinPK();
		pk.setUtente(utente);
		pk.setIdSede(sede);
		return pk;
	}

	@Override
	protected void updateValues(UtenteSedeJoin oldEntity, UtenteSedeJoin entity) {
		//DO NOTHING!
	}

}

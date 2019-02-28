package it.ltc.database.dao.common.utente;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.CommessaUtenti;

public class CommessaUtentiDao extends CRUDDao<CommessaUtenti> {
	
	public CommessaUtentiDao() {
		this(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME);
	}

	public CommessaUtentiDao(String persistenceUnit) {
		super(persistenceUnit, CommessaUtenti.class);
	}
	
	public List<CommessaUtenti> trovaTutti() {
		List<CommessaUtenti> entities = findAll();
		return entities;
	}
	
	public CommessaUtenti trovaDaID(int id) {
		CommessaUtenti entity = findByID(id);
		return entity;
	}
	
	public CommessaUtenti trovaDaRisorsa(String risorsa) {
		CommessaUtenti entity = findOnlyOneEqualTo("nomeRisorsa", risorsa);
		return entity;
	}
	
	public CommessaUtenti inserisci(CommessaUtenti commessa) {
		CommessaUtenti entity = insert(commessa);
		return entity;
	}
	
	public CommessaUtenti aggiorna(CommessaUtenti commessa) {
		CommessaUtenti entity = update(commessa, commessa.getId());
		return entity;
	}
	
	public CommessaUtenti elimina(CommessaUtenti commessa) {
		CommessaUtenti entity = delete(commessa.getId());
		return entity;
	}

	@Override
	protected void updateValues(CommessaUtenti oldEntity, CommessaUtenti entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setIdSede(entity.getIdSede());
		oldEntity.setLegacy(entity.isLegacy());
		oldEntity.setNome(entity.getNome());
		oldEntity.setNomeRisorsa(entity.getNomeRisorsa());
	}

}

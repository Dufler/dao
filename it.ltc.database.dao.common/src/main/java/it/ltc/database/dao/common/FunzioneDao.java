package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Funzione;

public class FunzioneDao extends CRUDDao<Funzione> {
	
	public FunzioneDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public FunzioneDao(String persistenceUnit) {
		super(persistenceUnit, Funzione.class);
	}
	
	public Funzione trovaDaNome(String nome) {
		Funzione entity = findByID(nome);
		return entity;
	}
	
	public List<Funzione> trovaTutte() {
		List<Funzione> entities = findAll();
		return entities;
	}
	
	public Funzione inserisci(Funzione funzione) {
		Funzione entity = insert(funzione);
		return entity;
	}
	
	public Funzione aggiorna(Funzione funzione) {
		Funzione entity = update(funzione, funzione.getPartid());
		return entity;
	}
	
	public Funzione elimina(Funzione funzione) {
		Funzione entity = delete(funzione.getPartid());
		return entity;
	}

	@Override
	protected void updateValues(Funzione oldEntity, Funzione entity) {
		oldEntity.setFeature(entity.getFeature());
		oldEntity.setIcona(entity.getIcona());
		oldEntity.setNome(entity.getNome());
		oldEntity.setPermessoid(entity.getPermessoid());
	}

}

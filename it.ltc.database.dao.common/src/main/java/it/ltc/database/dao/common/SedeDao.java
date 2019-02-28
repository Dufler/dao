package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Sede;

public class SedeDao extends CRUDDao<Sede> {

	public SedeDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public SedeDao(String persistenceUnit) {
		super(persistenceUnit, Sede.class);
	}
	
	public Sede trovaDaID(int id) {
		Sede sede = findByID(id);
		return sede;
	}
	
	public List<Sede> trovaTutte() {
		List<Sede> entities = findAll();
		return entities;
	}
	
	public Sede inserisci(Sede sede) {
		Sede entity = insert(sede);
		return entity;
	}
	
	public Sede aggiorna(Sede sede) {
		Sede entity = update(sede, sede.getId());
		return entity;
	}
	
	public Sede elimina(Sede sede) {
		Sede entity = delete(sede.getId());
		return entity;
	}

	@Override
	protected void updateValues(Sede oldEntity, Sede entity) {
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setIndirizzoWeb(entity.getIndirizzoWeb());
		oldEntity.setPrefissoCollo(entity.getPrefissoCollo());
		oldEntity.setSede(entity.getSede());
	}

}

package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.RighiPrelievo;

public class RighiPrelievoDao extends CRUDDao<RighiPrelievo> {

	public RighiPrelievoDao(String persistenceUnit) {
		super(persistenceUnit, RighiPrelievo.class);
	}

	@Override
	protected void updateValues(RighiPrelievo oldEntity, RighiPrelievo entity) {
		oldEntity.setIdUniArticolo(entity.getIdUniArticolo());
		oldEntity.setKeyColloCar(entity.getKeyColloCar());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setOperatore(entity.getOperatore());
		oldEntity.setQtaPrelevata(entity.getQtaPrelevata());		
	}
	
	public List<RighiPrelievo> trovaDaNumeroLista(String numeroLista) {
		List<RighiPrelievo> entities = findAllEqualTo("nrLista", numeroLista);
		return entities;
	}

}

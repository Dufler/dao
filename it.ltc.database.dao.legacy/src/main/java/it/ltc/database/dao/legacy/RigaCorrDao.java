package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.RigaCorr;

public class RigaCorrDao extends CRUDDao<RigaCorr> {

	public RigaCorrDao(String persistenceUnit) {
		super(persistenceUnit, RigaCorr.class);
	}
	
	public RigaCorr trovaDaID(int id) {
		RigaCorr entity = findByID(id);
		return entity;
	}
	
	public List<RigaCorr> trovaDaNumeroSpedizione(int numeroSpedizione) {
		List<RigaCorr> entities = findAllEqualTo("nrSpedi", numeroSpedizione);
		return entities;
	}
	
	public List<RigaCorr> trovaDaNumeroLista(String numeroLista) {
		List<RigaCorr> entities = findAllEqualTo("nrLista", numeroLista);
		return entities;
	}

	@Override
	protected void updateValues(RigaCorr oldEntity, RigaCorr entity) {
		oldEntity.setCodMittente(entity.getCodMittente());
		oldEntity.setCodRaggruppamento(entity.getCodRaggruppamento());
		oldEntity.setFormato(entity.getFormato());
		oldEntity.setNrCollo(entity.getNrCollo());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrSpedi(entity.getNrSpedi());
		oldEntity.setPeso(entity.getPeso());
		oldEntity.setPezzi(entity.getPezzi());
		oldEntity.setStringaBartolini(entity.getStringaBartolini());
		oldEntity.setVolume(entity.getVolume());
	}

}

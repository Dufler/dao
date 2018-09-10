package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.ColliImballo;

public class ColliImballoDao extends CRUDDao<ColliImballo> {

	public ColliImballoDao(String persistenceUnit) {
		super(persistenceUnit, ColliImballo.class);
	}
	
	public List<ColliImballo> trovaDaNumeroLista(String numeroLista) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("nrLista", numeroLista));
		condizioni.add(new CondizioneWhere("stato", "CH"));
		condizioni.add(new CondizioneWhere("cancellato", "NO"));
		List<ColliImballo> entities = findAll(condizioni);
		return entities;
	}
	
	public ColliImballo trovaDaNumeroCollo(String numeroCollo) {
		ColliImballo entity = findOnlyOneEqualTo("keyColloSpe", numeroCollo);
		return entity;
	}

	@Override
	protected void updateValues(ColliImballo oldEntity, ColliImballo entity) {
		oldEntity.setBarCodeImb(entity.getBarCodeImb());
		oldEntity.setCancellato(entity.getCancellato());
		oldEntity.setCodFormato(entity.getCodFormato());
		oldEntity.setKeyColloSpe(entity.getKeyColloSpe());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setPesoKg(entity.getPesoKg());
		oldEntity.setPezziCollo(entity.getPezziCollo());
		oldEntity.setStato(entity.getStato());
		oldEntity.setVolume(entity.getVolume());
	}

}

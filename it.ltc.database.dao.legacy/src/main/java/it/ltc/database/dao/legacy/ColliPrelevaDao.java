package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ColliPreleva;

public class ColliPrelevaDao extends CRUDDao<ColliPreleva> {

	public ColliPrelevaDao(String persistenceUnit) {
		super(persistenceUnit, ColliPreleva.class);
	}
	
	public List<ColliPreleva> trovaDaNumeroLista(String numeroLista) {
		List<ColliPreleva> entities = findAllEqualTo("nrLista", numeroLista);
		return entities;
	}
	
	public List<ColliPreleva> trovaDaRiferimentoCliente(String riferimento) {
		List<ColliPreleva> entities = findAllEqualTo("poNumber", riferimento);
		return entities;
	}
	
	public ColliPreleva trovaDaNumeroCollo(String numeroCollo) {
		ColliPreleva entity = findFirstOneEqualTo("keyColloPre", numeroCollo);
		return entity;
	}

	@Override
	protected void updateValues(ColliPreleva oldEntity, ColliPreleva entity) {
		oldEntity.setBarcodeCorriere(entity.getBarcodeCorriere());
		oldEntity.setCodiceCorriere(entity.getCodiceCorriere());
		oldEntity.setDataDistinta(entity.getDataDistinta());
		oldEntity.setKeyColloPre(entity.getKeyColloPre());
		oldEntity.setNrColloCliente(entity.getNrColloCliente());
		oldEntity.setNrDistinta(entity.getNrDistinta());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setVet1(entity.getVet1());
		oldEntity.setVet2(entity.getVet2());
		oldEntity.setPoNumber(entity.getPoNumber());
		if (entity.getSpedito() != null)
			oldEntity.setSpedito(entity.getSpedito());
	}

	public ColliPreleva inserisci(ColliPreleva collo) {
		ColliPreleva entity = insert(collo);
		return entity;
	}

	public ColliPreleva aggiorna(ColliPreleva collo) {
		ColliPreleva entity = update(collo, collo.getIdColliPreleva());
		return entity;
	}
	
	public ColliPreleva elimina(ColliPreleva collo) {
		ColliPreleva entity = delete(collo.getIdColliPreleva());
		return entity;
	}

}

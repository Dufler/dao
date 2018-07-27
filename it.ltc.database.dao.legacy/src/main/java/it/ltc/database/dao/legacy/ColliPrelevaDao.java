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

	@Override
	protected void updateValues(ColliPreleva oldEntity, ColliPreleva entity) {
		oldEntity.setBarcodeCorriere(entity.getBarcodeCorriere());
		oldEntity.setCodiceCorriere(entity.getCodiceCorriere());
		oldEntity.setDataDistinta(entity.getDataDistinta());
		oldEntity.setKeyColloPre(entity.getKeyColloPre());
		oldEntity.setNrColloCliente(entity.getNrColloCliente());
		oldEntity.setNrDistinta(entity.getNrDistinta());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setSpedito(entity.getSpedito());
		oldEntity.setVet1(entity.getVet1());
		oldEntity.setVet2(entity.getVet2());
	}

}

package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.RighiImballo;

public class RighiImballoDao extends CRUDDao<RighiImballo> {

	public RighiImballoDao(String persistenceUnit) {
		super(persistenceUnit, RighiImballo.class);
	}
	
	public List<RighiImballo> trovaDaNumeroLista(String numeroLista) {
		List<RighiImballo> entities = findAllEqualTo("nrLista", numeroLista);
		return entities;
	}

	@Override
	protected void updateValues(RighiImballo oldEntity, RighiImballo entity) {
		oldEntity.setCodBarre(entity.getCodBarre());
		oldEntity.setCodiceArticolo(entity.getCodiceArticolo());
		oldEntity.setDataImba(entity.getDataImba());
		oldEntity.setIdUniArticolo(entity.getIdUniArticolo());
		oldEntity.setIdUnicoCollo(entity.getIdUnicoCollo());
		oldEntity.setKeyColloSpe(entity.getKeyColloSpe());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrOrdine(entity.getNrOrdine());
		oldEntity.setNrRifCliente(entity.getNrRifCliente());
		oldEntity.setNrRigoOrdine(entity.getNrRigoOrdine());
		oldEntity.setNumerata(entity.getNumerata());
		oldEntity.setOperatore(entity.getOperatore());
		oldEntity.setOraImba(entity.getOraImba());
		oldEntity.setPezzicassa(entity.getPezzicassa());
		oldEntity.setPONumber(entity.getPONumber());
		oldEntity.setPosTaglia(entity.getPosTaglia());
		oldEntity.setPostazione(entity.getPostazione());
		oldEntity.setProgCollo(entity.getProgCollo());
		oldEntity.setQtaImballata(entity.getQtaImballata());
		oldEntity.setSeriale(entity.getSeriale());
		oldEntity.setTipoRif(entity.getTipoRif());
	}

}

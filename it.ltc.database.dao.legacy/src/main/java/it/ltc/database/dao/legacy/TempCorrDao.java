package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.TempCorr;

public class TempCorrDao extends CRUDDao<TempCorr> {

	public TempCorrDao(String persistenceUnit) {
		super(persistenceUnit, TempCorr.class);
	}

	@Override
	protected void updateValues(TempCorr oldEntity, TempCorr entity) {
		oldEntity.setCodCliente(entity.getCodCliente());
		oldEntity.setCodcorriere(entity.getCodcorriere());
		oldEntity.setDataDocu(entity.getDataDocu());
		oldEntity.setDivisa(entity.getDivisa());
		oldEntity.setGenerato(entity.getGenerato());
		oldEntity.setNote(entity.getNote());
		oldEntity.setNrColli(entity.getNrColli());
		oldEntity.setNrDoc(entity.getNrDoc());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrOrdine(entity.getNrOrdine());
		oldEntity.setPesoKg(entity.getPesoKg());
		oldEntity.setTipoDocu(entity.getTipoDocu());
		oldEntity.setTipoIncasso(entity.getTipoIncasso());
		oldEntity.setValContra(entity.getValContra());
	}

	public TempCorr trovaDaNumeroLista(String numeroLista) {
		TempCorr entity = findOnlyOneEqualTo("nrLista", numeroLista);
		return entity;
	}
	
	public TempCorr inserisci(TempCorr datiSpedizione) {
		TempCorr entity = insert(datiSpedizione);
		return entity;
	}

	public TempCorr aggiorna(TempCorr datiSpedizione) {
		TempCorr entity = update(datiSpedizione, datiSpedizione.getIdTempCor());
		return entity;
	}
	
	public TempCorr elimina(TempCorr datiSpedizione) {
		TempCorr entity = delete(datiSpedizione.getIdTempCor());
		return entity;
	}

}

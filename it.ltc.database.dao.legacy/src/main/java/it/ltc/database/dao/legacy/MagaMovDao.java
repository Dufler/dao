package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.MagaMov;

public class MagaMovDao extends CRUDDao<MagaMov> {

	public MagaMovDao(String persistenceUnit) {
		super(persistenceUnit, MagaMov.class);
	}

	@Override
	protected void updateValues(MagaMov oldEntity, MagaMov entity) {
		oldEntity.setCancellato(entity.getCancellato());
		oldEntity.setCausale(entity.getCausale());
		oldEntity.setCodMaga(entity.getCodMaga());
		oldEntity.setDisponibilemov(entity.getDisponibilemov());
		oldEntity.setDocCat(entity.getDocCat());
		oldEntity.setDocData(entity.getDocData());
		oldEntity.setDocNote(entity.getDocNote());
		oldEntity.setDocNr(entity.getDocNr());
		oldEntity.setDocTipo(entity.getDocTipo());
		oldEntity.setEsistenzamov(entity.getEsistenzamov());
		oldEntity.setIdMagaMov(entity.getIdMagaMov());
		oldEntity.setIdUniArticolo(entity.getIdUniArticolo());
		oldEntity.setImpegnatomov(entity.getImpegnatomov());
		oldEntity.setIncTotali(entity.getIncTotali());
		oldEntity.setKeyCollo(entity.getKeyCollo());
		oldEntity.setQuantita(entity.getQuantita());
		oldEntity.setSegno(entity.getSegno());
		oldEntity.setSegnoDis(entity.getSegnoDis());
		oldEntity.setSegnoEsi(entity.getSegnoEsi());
		oldEntity.setSegnoImp(entity.getSegnoImp());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setTrasmesso(entity.getTrasmesso());
		oldEntity.setUtente(entity.getUtente());
	}

	public List<MagaMov> trovaMovimentiCarico(String riferimentoCarico) {
		List<MagaMov> entities = findAllEqualTo("docNr", riferimentoCarico);
		return entities;
	}

}

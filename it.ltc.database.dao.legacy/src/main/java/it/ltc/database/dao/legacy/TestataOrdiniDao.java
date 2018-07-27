package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.TestataOrdini;

public class TestataOrdiniDao extends CRUDDao<TestataOrdini> {

	public TestataOrdiniDao(String persistenceUnit) {
		super(persistenceUnit, TestataOrdini.class);
	}
	
	public TestataOrdini trovaDaID(int id) {
		TestataOrdini entity = findByID(id);
		return entity;
	}
	
	public TestataOrdini trovaDaRiferimento(String riferimento) {
		TestataOrdini entity = findOnlyOneEqualTo("rifOrdineCli", riferimento);
		return entity;
	}
	
	public List<TestataOrdini> trovaDaStato(String stato) {
		List<TestataOrdini> entities = findAllEqualTo("stato", stato);
		return entities;
	}
	
	public TestataOrdini inserisci(TestataOrdini ordine) {
		TestataOrdini entity = insert(ordine);
		return entity;
	}
	
	public TestataOrdini aggiorna(TestataOrdini ordine) {
		TestataOrdini entity = update(ordine, ordine.getIdTestaSped());
		return entity;
	}
	
	public TestataOrdini elimina(TestataOrdini ordine) {
		TestataOrdini entity = delete(ordine.getIdTestaSped());
		return entity;
	}

	@Override
	protected void updateValues(TestataOrdini oldEntity, TestataOrdini entity) {
		oldEntity.setAnnodoc(entity.getAnnodoc());
		oldEntity.setAnnoOrdine(entity.getAnnoOrdine());
		oldEntity.setCodCliente(entity.getCodCliente());
		oldEntity.setCodCorriere(entity.getCodCorriere());
		oldEntity.setCodiceClienteCorriere(entity.getCodiceClienteCorriere());
		oldEntity.setCorriere(entity.getCorriere());
		oldEntity.setDataArrivoFile(entity.getDataArrivoFile());
		oldEntity.setDataConsegna(entity.getDataConsegna());
		oldEntity.setDataDoc(entity.getDataDoc());
		oldEntity.setDataOrdine(entity.getDataOrdine());
		oldEntity.setFlag1(entity.getFlag1());
		oldEntity.setIdDestina(entity.getIdDestina());
		oldEntity.setIdMittente(entity.getIdMittente());
		oldEntity.setIdTestaCorr(entity.getIdTestaCorr());
		oldEntity.setNomeFileArrivo(entity.getNomeFileArrivo());
		oldEntity.setNote(entity.getNote());
		oldEntity.setNrDoc(entity.getNrDoc());
		oldEntity.setNrLetteraVettura(entity.getNrLetteraVettura());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrListaArrivato(entity.getNrListaArrivato());
		oldEntity.setNrOrdine(entity.getNrOrdine());
		oldEntity.setOperatore(entity.getOperatore());
		oldEntity.setPercAssegnata(entity.getPercAssegnata());
		oldEntity.setPriorita(entity.getPriorita());
		oldEntity.setQtaAssegnata(entity.getQtaAssegnata());
		oldEntity.setQtaimballata(entity.getQtaimballata());
		oldEntity.setQtaTotaleSpedire(entity.getQtaTotaleSpedire());
		oldEntity.setRagstampe(entity.getRagstampe());
		oldEntity.setRifOrdineCli(entity.getRifOrdineCli());
		oldEntity.setSessioneLavoro(entity.getSessioneLavoro());
		oldEntity.setStato(entity.getStato());
		oldEntity.setStatoUbicazione(entity.getStatoUbicazione());
		oldEntity.setTipoDoc(entity.getTipoDoc());
		oldEntity.setTipoIncasso(entity.getTipoIncasso());
		oldEntity.setTipoOrdine(entity.getTipoOrdine());
		oldEntity.setTipoTrasporto(entity.getTipoTrasporto());
		oldEntity.setValContrassegno(entity.getValContrassegno());
		oldEntity.setValoreDoganale(entity.getValoreDoganale());
	}

}

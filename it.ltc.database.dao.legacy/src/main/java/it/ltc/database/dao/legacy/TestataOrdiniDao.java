package it.ltc.database.dao.legacy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.dao.CondizioneWhere.Condizione;
import it.ltc.database.dao.CondizioneWhere.Operatore;
import it.ltc.database.model.legacy.TestataOrdini;

public class TestataOrdiniDao extends CRUDDao<TestataOrdini> {
	
	protected final HashMap<Integer, TestataOrdini> mappaPerID;
	protected final HashMap<String, TestataOrdini> mappaPerLista;
	protected final HashMap<String, TestataOrdini> mappaPerRiferimento;

	public TestataOrdiniDao(String persistenceUnit) {
		super(persistenceUnit, TestataOrdini.class);
		mappaPerID = new HashMap<>();
		mappaPerLista = new HashMap<>();
		mappaPerRiferimento = new HashMap<>();
	}
	
	protected TestataOrdini inserisciInMappe(TestataOrdini entity) {
		if (entity != null) {
			mappaPerID.put(entity.getIdTestaSped(), entity);
			mappaPerLista.put(entity.getNrLista(), entity);
			mappaPerRiferimento.put(entity.getRifOrdineCli(), entity);
		}
		return entity;
	}
	
	protected void rimuoveDaMappe(TestataOrdini entity) {
		if (entity != null) {
			mappaPerID.remove(entity.getIdTestaSped());
			mappaPerLista.remove(entity.getNrLista());
			mappaPerRiferimento.remove(entity.getRifOrdineCli());
		}		
	}
	
	public List<TestataOrdini> trovaTestateSenzaTotali() {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("qtaimballata", 0, Operatore.LESSER_OR_EQUAL, Condizione.AND));
		List<TestataOrdini> entities = findAll(conditions);
		return entities;
	}
	
	public TestataOrdini trovaDaID(int id) {	
		TestataOrdini entity = mappaPerID.containsKey(id) ? mappaPerID.get(id): inserisciInMappe(findByID(id));
		return entity;
	}
	
	public TestataOrdini trovaDaNumeroLista(String nrLista) {
		TestataOrdini entity = mappaPerLista.containsKey(nrLista) ? mappaPerLista.get(nrLista) : inserisciInMappe(findOnlyOneEqualTo("nrLista", nrLista));
		return entity;
	}
	
	public TestataOrdini trovaDaRiferimento(String riferimento) {
		TestataOrdini entity = mappaPerRiferimento.containsKey(riferimento) ? mappaPerRiferimento.get(riferimento) : inserisciInMappe(findOnlyOneEqualTo("rifOrdineCli", riferimento));
		return entity;
	}
	
	public List<TestataOrdini> trovaDaStato(String stato) {
		List<TestataOrdini> entities = findAllEqualTo("stato", stato);
		return entities;
	}
	
	public List<TestataOrdini> trovaDaSpedizione(int idSpedizione) {
		List<TestataOrdini> entities = findAllEqualTo("idTestaCorr", idSpedizione);
		return entities;
	}
	
	/**
	 * Restituisce una lista di ordini che non hanno un assegnazione completa.<br>
	 * Condizioni per la selezione: Stato: IMPO, Quantità assegnata < Quantità totale.
	 * @return
	 */
	public List<TestataOrdini> trovaOrdiniDaAssegnare() {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TestataOrdini> criteria = cb.createQuery(TestataOrdini.class);
		Root<TestataOrdini> member = criteria.from(TestataOrdini.class);
		Predicate condizioneStato = cb.equal(member.get("Stato"), "IMPO");
		Predicate condizionePezziAssegnati = cb.lessThan(member.get("qtaAssegnata"), member.get("qtaTotaleSpedire"));
		criteria.select(member).where(cb.and(condizioneStato, condizionePezziAssegnati));
		List<TestataOrdini> list = em.createQuery(criteria).getResultList();
		em.close();
		return list;
	}
	
	public TestataOrdini inserisci(TestataOrdini ordine) {
		TestataOrdini entity = insert(ordine);
		inserisciInMappe(entity);
		return entity;
	}
	
	public TestataOrdini aggiorna(TestataOrdini ordine) {
		TestataOrdini entity = update(ordine, ordine.getIdTestaSped());
		inserisciInMappe(entity);
		return entity;
	}
	
	public TestataOrdini elimina(TestataOrdini ordine) {
		TestataOrdini entity = delete(ordine.getIdTestaSped());
		rimuoveDaMappe(entity);
		return entity;
	}

	@Override
	protected void updateValues(TestataOrdini oldEntity, TestataOrdini entity) {
		oldEntity.setAnnodoc(entity.getAnnodoc());
		oldEntity.setAnnoOrdine(entity.getAnnoOrdine());
		oldEntity.setCodCliente(entity.getCodCliente());
		oldEntity.setDataArrivoFile(entity.getDataArrivoFile());
		oldEntity.setDataConsegna(entity.getDataConsegna());
		oldEntity.setDataDoc(entity.getDataDoc());
		oldEntity.setDataOrdine(entity.getDataOrdine());
		oldEntity.setIdDestina(entity.getIdDestina());
		oldEntity.setIdMittente(entity.getIdMittente());
		oldEntity.setNote(entity.getNote());
		oldEntity.setNrDoc(entity.getNrDoc());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrListaArrivato(entity.getNrListaArrivato());
		oldEntity.setNrOrdine(entity.getNrOrdine());
		oldEntity.setOperatore(entity.getOperatore());
		oldEntity.setPriorita(entity.getPriorita());
		oldEntity.setQtaAssegnata(entity.getQtaAssegnata());
		oldEntity.setQtaimballata(entity.getQtaimballata());
		oldEntity.setQtaTotaleSpedire(entity.getQtaTotaleSpedire());
		oldEntity.setRifOrdineCli(entity.getRifOrdineCli());
		oldEntity.setStato(entity.getStato());
		oldEntity.setTipoDoc(entity.getTipoDoc());
		oldEntity.setTipoOrdine(entity.getTipoOrdine());
		//Campi da aggiornare solo se hanno un valore.
		if (isValorizzato(entity.getRagioneSocialeDestinatario()))
			oldEntity.setRagioneSocialeDestinatario(entity.getRagioneSocialeDestinatario());
		if (isValorizzato(entity.getNomeFileArrivo()))
			oldEntity.setNomeFileArrivo(entity.getNomeFileArrivo());
		if (isValorizzato(entity.getFlag1()))
			oldEntity.setFlag1(entity.getFlag1());
		if (entity.getIdTestaCorr() > 0)
			oldEntity.setIdTestaCorr(entity.getIdTestaCorr());
		if (isValorizzato(entity.getCodCorriere()))
			oldEntity.setCodCorriere(entity.getCodCorriere());
		if (isValorizzato(entity.getCodiceClienteCorriere()))
			oldEntity.setCodiceClienteCorriere(entity.getCodiceClienteCorriere());
		if (isValorizzato(entity.getCorriere()))
			oldEntity.setCorriere(entity.getCorriere());
		if (entity.getPercAssegnata() >= 0)
			oldEntity.setPercAssegnata(entity.getPercAssegnata());
		if (isValorizzato(entity.getNrLetteraVettura()))
			oldEntity.setNrLetteraVettura(entity.getNrLetteraVettura());
		if (isValorizzato(entity.getRagstampe()))
			oldEntity.setRagstampe(entity.getRagstampe());
		if (isValorizzato(entity.getSessioneLavoro()))
			oldEntity.setSessioneLavoro(entity.getSessioneLavoro());
		if (isValorizzato(entity.getStatoUbicazione()))
			oldEntity.setStatoUbicazione(entity.getStatoUbicazione());
		if (isValorizzato(entity.getTipoIncasso()))
			oldEntity.setTipoIncasso(entity.getTipoIncasso());
		if (isValorizzato(entity.getTipoTrasporto()))
			oldEntity.setTipoTrasporto(entity.getTipoTrasporto());
		if (entity.getValContrassegno() != null)
			oldEntity.setValContrassegno(entity.getValContrassegno());
		if (entity.getValoreDoganale() != null)
			oldEntity.setValoreDoganale(entity.getValoreDoganale());
	}

}

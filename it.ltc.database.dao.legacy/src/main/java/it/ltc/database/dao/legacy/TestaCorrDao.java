package it.ltc.database.dao.legacy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.TestaCorr;

public class TestaCorrDao extends CRUDDao<TestaCorr> {
	
	private static final Logger logger = Logger.getLogger(TestaCorrDao.class);

	public TestaCorrDao(String persistenceUnit) {
		super(persistenceUnit, TestaCorr.class);
	}
	
	public int getProgressivoSpedizioneTestaCorr() {
		EntityManager em = getManager();
		int progressivo = getProgressivoSpedizioneTestaCorr(em);
		return progressivo;
	}
	
	protected static synchronized int getProgressivoSpedizioneTestaCorr(EntityManager em) {
		int progressivo;
		try {
			progressivo = em.createNamedQuery("TestaCorr.progressivoSpedizione", Integer.class).getSingleResult() + 1;
		} catch (Exception e) {
			progressivo = 1;
		} finally {
			em.close();
		}
		return progressivo;
	}
	
	public TestaCorr inserisci(TestaCorr spedizione) {
		TestaCorr entity = insert(spedizione);
		return entity;
	}
	
	public TestaCorr aggiorna(TestaCorr spedizione) {
		TestaCorr entity = update(spedizione, spedizione.getIdTestaCor());
		return entity;
	}
	
	public TestaCorr elimina(TestaCorr spedizione) {
		TestaCorr entity = delete(spedizione.getIdTestaCor());
		return entity;
	}

	@Override
	protected void updateValues(TestaCorr oldEntity, TestaCorr entity) {
		oldEntity.setAnnoSpe(entity.getAnnoSpe());
		oldEntity.setCap(entity.getCap());
		oldEntity.setCapMitt(entity.getCapMitt());
		oldEntity.setCodBolla(entity.getCodBolla());
		oldEntity.setCodMittente(entity.getCodMittente());
		oldEntity.setContrassegno(entity.getContrassegno());
		oldEntity.setCorriere(entity.getCorriere());
		oldEntity.setDataConsegna(entity.getDataConsegna());
		oldEntity.setDataGenerazione(entity.getDataGenerazione());
		oldEntity.setDataSpe(entity.getDataSpe());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setLocalita(entity.getLocalita());
		oldEntity.setMittenteAlfa(entity.getMittenteAlfa());
		oldEntity.setMittenteNum(entity.getMittenteNum());
		oldEntity.setNazione(entity.getNazione());
		oldEntity.setNazioneMitt(entity.getNazioneMitt());
		oldEntity.setNomeFileCor(entity.getNomeFileCor());
		oldEntity.setNote1(entity.getNote1());
		oldEntity.setNote2(entity.getNote2());
		oldEntity.setNrColli(entity.getNrColli());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrSerie(entity.getNrSerie());
		oldEntity.setNrSpedi(entity.getNrSpedi());
		oldEntity.setPeso(entity.getPeso());
		oldEntity.setPezzi(entity.getPezzi());
		oldEntity.setProvincia(entity.getProvincia());
		oldEntity.setRagSocDest(entity.getRagSocDest());
		oldEntity.setRagSocEst(entity.getRagSocEst());
		oldEntity.setRagSocMitt(entity.getRagSocMitt());
		oldEntity.setServizio(entity.getServizio());
		oldEntity.setTipoIncasso(entity.getTipoIncasso());
		oldEntity.setTrasmesso(entity.getTrasmesso());
		oldEntity.setValoreMerce(entity.getValoreMerce());
		oldEntity.setValutaIncasso(entity.getValutaIncasso());
		oldEntity.setVolume(entity.getVolume());
	}
	
	public TestaCorr trovaDaID(int id) {
		TestaCorr entity = findByID(id);
		return entity;
	}
	
	public List<TestaCorr> getSpedizioniForza(int idPartenza, int anno) {
		List<TestaCorr> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<TestaCorr> criteria = cb.createQuery(c);
	        Root<TestaCorr> member = criteria.from(c);
	        Predicate condizioneAnno = cb.equal(member.get("annoSpe"), anno);
	        Predicate condizioneTrasmesso = cb.equal(member.get("trasmesso"), 1);
	        Predicate condizioneID = cb.greaterThanOrEqualTo(member.get("idTestaCor"), idPartenza);
	        Predicate condizioneGLS = cb.like(member.get("nomeFileCor"), "LTCGLS_LTCGL%");
	        criteria.select(member).where(cb.and(condizioneAnno, condizioneTrasmesso, condizioneID, condizioneGLS));
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e);
			lista = null;
		} finally {
			em.close();
		}
		return lista;
	}

	public List<TestaCorr> trovaTutte() {
		List<TestaCorr> entities = findAll();
		return entities;
	}

}

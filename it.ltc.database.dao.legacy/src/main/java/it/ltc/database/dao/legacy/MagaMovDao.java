package it.ltc.database.dao.legacy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.PakiTesta;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.database.model.legacy.model.CausaliMovimento;

public class MagaMovDao extends CRUDDao<MagaMov> {
	
	private static final Logger logger = Logger.getLogger(MagaMovDao.class);

	public MagaMovDao(String persistenceUnit) {
		super(persistenceUnit, MagaMov.class);
	}
	
	public MagaMov inserisci(MagaMov movimento) {
		MagaMov entity = insert(movimento);
		return entity;
	}
	
	public MagaMov aggiorna(MagaMov movimento) {
		MagaMov entity = update(movimento, movimento.getIdMagaMov());
		return entity;
	}
	
	public MagaMov elimina(MagaMov movimento) {
		MagaMov entity = delete(movimento.getIdMagaMov());
		return entity;
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
//		oldEntity.setKeyCollo(entity.getKeyCollo());
		oldEntity.setQuantita(entity.getQuantita());
		oldEntity.setSegno(entity.getSegno());
		oldEntity.setSegnoDis(entity.getSegnoDis());
		oldEntity.setSegnoEsi(entity.getSegnoEsi());
		oldEntity.setSegnoImp(entity.getSegnoImp());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setTrasmesso(entity.getTrasmesso());
		oldEntity.setUtente(entity.getUtente());
	}

	public List<MagaMov> trovaMovimentiCarico(PakiTesta carico) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("docNr", carico.getNrPaki()));
		conditions.add(new CondizioneWhere("IDdocum", carico.getIdTestaPaki()));
		List<MagaMov> entities = findAll(conditions);
		return entities;
	}
	
	public List<MagaMov> trovaMovimentiOrdine(TestataOrdini ordine) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("docNr", ordine.getNrLista()));
		conditions.add(new CondizioneWhere("IDdocum", ordine.getIdTestaSped()));
		List<MagaMov> entities = findAll(conditions);
		return entities;
	}
	
	public List<MagaMov> trovaMovimentiProdotto(String idUnivocoArticolo) {
		List<MagaMov> entities = findAllEqualTo("idUniArticolo", idUnivocoArticolo);
		return entities;
	}
	
	public List<MagaMov> trovaMovimentiNonComunicati() {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("trasmesso", "NO"));
		condizioni.add(new CondizioneWhere("cancellato", "NO"));
		List<MagaMov> entities = findAll(condizioni);
		return entities;
	}
	
	public List<MagaMov> trovaMovimentiNonComunicatiPerCausale(String causale) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("causale", causale));
		condizioni.add(new CondizioneWhere("trasmesso", "NO"));
		condizioni.add(new CondizioneWhere("cancellato", "NO"));
		List<MagaMov> entities = findAll(condizioni);
		return entities;
	}
	
	public boolean setStatoTrasmissione(List<MagaMov> movimenti, boolean trasmesso) {
		boolean update;
		String stato = trasmesso ? "SI" : "NO";
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			for (MagaMov movimento : movimenti) {
				movimento.setTrasmesso(stato);
				em.merge(movimento);
			}
			t.commit();
			update = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			update = false;
			if (t != null && t.isActive())
				t.rollback();
		}
		return update;
	}
	
	public MagaMov trovaDaID(int id) {
		MagaMov entity = findByID(id);
		return entity;
	}
	
	public MagaMov getNuovoMovimento(CausaliMovimento causale, String riferimentoDocumento, int idDocumento, Date dataDocumento, MagaSd saldo, String codiceUnivocoArticolo, String magazzino, int quantità) {
		MagaMov movimento = new MagaMov();
		movimento.setCausale(causale.name());
		movimento.setDocNr(riferimentoDocumento);
		movimento.setDocData(dataDocumento);
		movimento.setIDdocum(idDocumento);
		movimento.setDocCat(causale.getCategoriaDocumento());
		movimento.setDocNote(causale.getDescrizione());
		movimento.setDocTipo(causale.getTipoDocumento());
		movimento.setCodMaga(magazzino);
		movimento.setDisponibilemov(saldo.getDisponibile());
		movimento.setEsistenzamov(saldo.getEsistenza());
		movimento.setImpegnatomov(saldo.getImpegnato());
		movimento.setSegno(causale.getSegnoEsistenza());
		movimento.setSegnoDis(causale.getSegnoDisponibile());
		movimento.setSegnoEsi(causale.getSegnoEsistenza());
		movimento.setSegnoImp(causale.getSegnoImpegnato());
		movimento.setIdUniArticolo(codiceUnivocoArticolo);
		movimento.setIncTotali(causale.getIncrementoTotali());
		movimento.setTipo(causale.getTipoMovimento());
		movimento.setQuantita(quantità);
		movimento.setUtente(utente);
		return movimento;
	}

}

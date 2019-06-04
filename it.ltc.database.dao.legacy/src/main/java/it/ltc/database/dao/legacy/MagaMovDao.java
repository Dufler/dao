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
	
	public MagaMov getNuovoMovimentoChiusuraCarico(PakiTesta carico,  MagaSd saldo, int quantità) {
		return getNuovoMovimento(CausaliMovimento.CARICO, carico.getNrPaki(), carico.getIdTestaPaki(), new Date(), saldo, quantità, carico.getRagSocFor());
	}
	
	public MagaMov getNuovoMovimentoImpegnoOrdine(TestataOrdini ordine,  MagaSd saldo, int quantità) {
		String note = ordine.getRifOrdineCli();
		String destinatario = ordine.getRagioneSocialeDestinatario();
		if (destinatario != null && destinatario.isEmpty())
			note += " per " + destinatario;
		return getNuovoMovimento(CausaliMovimento.IMPEGNO, ordine.getNrLista(), ordine.getIdTestaSped(), new Date(), saldo, quantità, note);
	}
	
	public MagaMov getNuovoMovimento(CausaliMovimento causale, String riferimentoDocumento, int idDocumento, Date dataDocumento, MagaSd saldo, int quantità, String note) {
		return getNuovoMovimento(causale.getCausaleDefault(), causale, riferimentoDocumento, idDocumento, dataDocumento, saldo, quantità, note);
	}
	
	public MagaMov getNuovoMovimento(String causaleLegacy, CausaliMovimento causale, String riferimentoDocumento, int idDocumento, Date dataDocumento, MagaSd saldo, int quantità, String note) {
		MagaMov movimento = new MagaMov();
		movimento.setCausale(causaleLegacy);
		movimento.setDocNr(riferimentoDocumento);
		movimento.setDocData(dataDocumento);
		movimento.setIDdocum(idDocumento);
		movimento.setDocCat(causale.getCategoriaDocumento());
		if (note == null || note.isEmpty())
			note = causale.getDescrizione();
		if (note != null && note.length() > 50)
			note = note.substring(0, 50);
		movimento.setDocNote(note);
		movimento.setDocTipo(causale.getTipoDocumento());
		movimento.setCodMaga(saldo.getCodMaga());
		movimento.setDisponibilemov(saldo.getDisponibile());
		movimento.setEsistenzamov(saldo.getEsistenza());
		movimento.setImpegnatomov(saldo.getImpegnato());
		movimento.setSegno(causale.getSegnoEsistenza());
		movimento.setSegnoDis(causale.getSegnoDisponibile());
		movimento.setSegnoEsi(causale.getSegnoEsistenza());
		movimento.setSegnoImp(causale.getSegnoImpegnato());
		movimento.setIdUniArticolo(saldo.getIdUniArticolo());
		movimento.setIncTotali(causale.getIncrementoTotali());
		movimento.setTipo(causale.getTipoMovimento());
		movimento.setQuantita(quantità);
		movimento.setUtente(utente);
		return movimento;
	}

}

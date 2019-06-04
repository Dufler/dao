package it.ltc.database.dao.ordini;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ColliImballoDao;
import it.ltc.database.dao.legacy.FornitoreDao;
import it.ltc.database.dao.legacy.MagaMovDao;
import it.ltc.database.dao.legacy.MagaSdDao;
import it.ltc.database.dao.legacy.MagazzinoDao;
import it.ltc.database.dao.legacy.RighiImballoDao;
import it.ltc.database.dao.legacy.RighiOrdineDao;
import it.ltc.database.dao.legacy.RighiUbicPreDao;
import it.ltc.database.dao.legacy.StagioniDao;
import it.ltc.database.dao.legacy.TestataOrdiniDao;
import it.ltc.database.dao.legacy.FornitoreDao.TipoFornitori;
import it.ltc.database.model.legacy.ColliImballo;
import it.ltc.database.model.legacy.ColliPack;
import it.ltc.database.model.legacy.Fornitori;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.PakiArticolo;
import it.ltc.database.model.legacy.PakiTesta;
import it.ltc.database.model.legacy.RighiImballo;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.Righiubicpre;
import it.ltc.database.model.legacy.Stagioni;
import it.ltc.database.model.legacy.TestataOrdini;

public class ManagerResetOrdine extends Dao {

	private static final Logger logger = Logger.getLogger(ManagerResetOrdine.class);
	
	public enum Reset { IMBALLO, ASSEGNAZIONE_RIPOSIZIONA, ASSEGNAZIONE_NUOVOCARICO, IMPORTAZIONE }
	
	public static final String CODICE_TIPO_CARICO_ANNULLAMENTO_ORDINE = "ANN_ORD";
	
	protected final TestataOrdiniDao daoTestate;
	protected final RighiOrdineDao daoRighe;
	protected final MagaMovDao daoMovimenti;
	protected final MagaSdDao daoSaldi;
	protected final RighiImballoDao daoRigheImballate;
	protected final ColliImballoDao daoColliImballati;
	protected final RighiUbicPreDao daoRigheAssegnazione;
	protected final MagazzinoDao daoMagazzini;
	protected final FornitoreDao daoFornitori;
	protected final StagioniDao daoStagioni;
	
	public ManagerResetOrdine(String persistenceUnit) {
		super(persistenceUnit);
		daoTestate = new TestataOrdiniDao(persistenceUnit);
		daoRighe = new RighiOrdineDao(persistenceUnit);
		daoMovimenti = new MagaMovDao(persistenceUnit);
		daoSaldi = new MagaSdDao(persistenceUnit);
		daoRigheImballate = new RighiImballoDao(persistenceUnit);
		daoColliImballati = new ColliImballoDao(persistenceUnit);
		daoRigheAssegnazione = new RighiUbicPreDao(persistenceUnit);
		daoMagazzini = new MagazzinoDao(persistenceUnit);
		daoFornitori = new FornitoreDao(persistenceUnit);
		daoStagioni = new StagioniDao(persistenceUnit);
	}
	
	public boolean resetta(int idOrdine, Reset tipoReset) {
		TestataOrdini ordine = daoTestate.trovaDaID(idOrdine);
		if (ordine == null)
			throw new ResetOrdineException("Impossibile trovare l'ordine specificato. (ID: " + idOrdine + ")");
		return resetta(ordine, tipoReset);
	}
	
	public boolean resetta(String numeroLista, Reset tipoReset) {
		TestataOrdini ordine = daoTestate.trovaDaNumeroLista(numeroLista);
		if (ordine == null)
			throw new ResetOrdineException("Impossibile trovare l'ordine specificato. (Lista: " + numeroLista + ")");
		return resetta(ordine, tipoReset);
	}
	
	protected boolean resetta(TestataOrdini ordine, Reset tipoReset) {
		boolean reset;
		switch (tipoReset) {
			case ASSEGNAZIONE_RIPOSIZIONA : reset = resettaAssegnazioneConRiposizionamento(ordine); break;
			case ASSEGNAZIONE_NUOVOCARICO : reset = resettaAssegnazioneConNuovoCarico(ordine); break;
			case IMBALLO : reset = resettaImballo(ordine); break;
			case IMPORTAZIONE : reset = resettaImportazione(ordine); break;
			default : reset = false;
		}
		return reset;
	}
	
	protected boolean resettaAssegnazioneConNuovoCarico(TestataOrdini ordine) {
		logger.info("Avvio il reset dell'assegnazione e creo un nuovo carico.");
		boolean reset;
		//Trovo le righe d'ordine da resettare
		List<RighiOrdine> righe = daoRighe.trovaRigheDaIDOrdine(ordine.getIdTestaSped());
		//check sull'ordine
		checkResetAssegnazione(ordine, righe);
		//Trovo le righe dell'assegnazione
		List<Righiubicpre> righeAssegnazione = daoRigheAssegnazione.trovaDaLista(ordine.getNrLista());
		//Creo un carico a partire da cosa è stato assegnato
		PakiTesta testataCarico;
		List<PakiArticolo> righeCarico = new LinkedList<>();
		int progressivoRigaCarico = 1;
		int totalePezzi = 0;
		for (RighiOrdine riga : righe) {
			if (riga.getQtaAssegnata() > 0) {
				PakiArticolo rigaCarico = new PakiArticolo();
				rigaCarico.setCodArtStr(riga.getCodiceArticolo());
				rigaCarico.setCodBarre(riga.getCodBarre());
				rigaCarico.setCodUnicoArt(riga.getIdUnicoArt());
				rigaCarico.setIdArticolo(riga.getIdArticolo());
				rigaCarico.setIdPakiTesta(0);
				rigaCarico.setMagazzinoltc(riga.getMagazzino());
				rigaCarico.setMagazzino(daoMagazzini.trovaDaCodiceLTC(riga.getMagazzino()).getMagaCliente());
				rigaCarico.setNote("Inserita da annullamento ordine " + ordine.getRifOrdineCli());
				rigaCarico.setQtaPaki(riga.getQtaAssegnata());
				rigaCarico.setRigaPacki(progressivoRigaCarico);
				righeCarico.add(rigaCarico);
				progressivoRigaCarico++;
			} else {
				logger.info("La riga ID " + riga.getIdRigoOrdine() + " non ha pezzi assegnati.");
			}
		}
		if (totalePezzi > 0) {
			//Determino gli elementi necessari
			Fornitori fornitore = daoFornitori.trovaDaTipo(TipoFornitori.INTERNO);
			if (fornitore == null)
				throw new RuntimeException("Non ci sono fornitori con tipo INTERNO da poter usare per creare il carico.");
			List<Stagioni> stagioni = daoStagioni.trovaTutti();
			if (stagioni == null || stagioni.isEmpty())
				throw new RuntimeException("Non ci sono stagioni da poter usare per creare il carico.");
			Stagioni stagione = stagioni.get(stagioni.size() - 1);
			//Inserisco le info di testata
			testataCarico = new PakiTesta();
			testataCarico.setCodFornitore(fornitore.getCodiceFornitore());
			testataCarico.setDataPaki(new Date());
			testataCarico.setFlussoDichiarato("NO");
			testataCarico.setIdFornitore(fornitore.getIdFornitore());
			testataCarico.setNote("Inserito da annullamento ordine " + ordine.getRifOrdineCli());
			testataCarico.setNrPaki(ordine.getRifOrdineCli());
			testataCarico.setQtaTotAto(totalePezzi);
			testataCarico.setRagSocFor(fornitore.getRagSoc());
			testataCarico.setStagione(stagione.getCodice());
			testataCarico.setTipoCarico(CODICE_TIPO_CARICO_ANNULLAMENTO_ORDINE);
			testataCarico.setTipoDoc("DDT");
			testataCarico.setTipodocumento(CODICE_TIPO_CARICO_ANNULLAMENTO_ORDINE);
		} else {
			logger.info("Non è necessario creare un carico, non ci sono pezzi assegnati.");
			testataCarico = null;
		}
		//Comincio a scrivere la modifiche
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			//aggiusto i campi della testata
			resetCampiAssegnazioneTestata(ordine);
			em.merge(ordine);
			//aggiusto i campi delle righe
			for (RighiOrdine riga : righe) {
				resetCampiAssegnazioneRiga(riga);
				em.merge(riga);
			}
			//inserisco il carico, se necessario.
			if (testataCarico != null) {
				em.persist(testataCarico);
				for (PakiArticolo riga : righeCarico) {
					riga.setIdPakiTesta(testataCarico.getIdTestaPaki());
					em.persist(riga);
				}
			}
			//elimino le righe d'assegnazione
			for (Righiubicpre riga : righeAssegnazione) {
				em.remove(em.contains(riga) ? riga : em.find(Righiubicpre.class, riga.getIdubica()));
			}
			t.commit();
			reset = true;
		} catch (Exception e) {
			reset = false;
			logger.error(e.getMessage(), e);
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return reset;
	}
	
	protected boolean resettaAssegnazioneConRiposizionamento(TestataOrdini ordine) {
		logger.info("Avvio il reset dell'assegnazione e riposiziono i prodotti.");
		boolean reset;
		//Trovo le righe d'ordine da resettare
		List<RighiOrdine> righe = daoRighe.trovaRigheDaIDOrdine(ordine.getIdTestaSped());
		//check sull'ordine
		checkResetAssegnazione(ordine, righe);
		//Trovo le righe dell'assegnazione
		List<Righiubicpre> righeAssegnazione = daoRigheAssegnazione.trovaDaLista(ordine.getNrLista());
		//Comincio a scrivere la modifiche
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			//aggiusto i campi della testata
			resetCampiAssegnazioneTestata(ordine);
			em.merge(ordine);
			//aggiusto i campi delle righe
			for (RighiOrdine riga : righe) {
				resetCampiAssegnazioneRiga(riga);
				em.merge(riga);
			}
			//aggiusto i collipack toccati dalle righe d'assegnazione e le elimino.
			for (Righiubicpre riga : righeAssegnazione) {
				//se era un'assegnazione a prelievo vado a liberare il collipack.
				if (riga.getTipoAssegnazione().equals("PRELIEVO")) {
					ColliPack prodotto = em.find(ColliPack.class, riga.getIDcollipack());
					if (prodotto == null) throw new ResetOrdineException("Collipack assegnato non trovato. (ID collipack: " + riga.getIDcollipack() + ", ID Righiubicpre: " + riga.getIdubica() + ")");
					prodotto.setQtaimpegnata(prodotto.getQtaimpegnata() - riga.getQuantita());
					if (prodotto.getFlagimp().equals("S"))
						prodotto.setFlagimp("N");
					em.merge(prodotto);
				}				
				em.remove(em.contains(riga) ? riga : em.find(Righiubicpre.class, riga.getIdubica()));
			}
			t.commit();
			reset = true;
		} catch (Exception e) {
			reset = false;
			logger.error(e.getMessage(), e);
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return reset;
	}
	
	protected void resetCampiAssegnazioneTestata(TestataOrdini ordine) {
		ordine.setStato("IMPO");
		ordine.setQtaAssegnata(0);
		ordine.setPercAssegnata(0);
		ordine.setStampato("NO");
		ordine.setFlag1(" ");
		ordine.setStatoUbicazione("UP");
		ordine.setSessioneLavoro("");
	}
	
	protected void resetCampiAssegnazioneRiga(RighiOrdine riga) {
		riga.setArea("");
		riga.setCorsia("");
		riga.setScaffale("");
		riga.setPiano("");
		riga.setBox("");
		riga.setKeyUbiPre("");
		riga.setQtaAssegnata(0);
		riga.setQtadaubicare(riga.getQtaSpedizione());
		riga.setNrcollo("0");
	}
	
	protected void checkResetAssegnazione(TestataOrdini ordine, List<RighiOrdine> righe) {
		switch (ordine.getStato()) {
			case "IMPO" : case "ASSE" : break;
			default : throw new ResetOrdineException("Lo stato dell'ordine non permette di resettare l'assegnazione. (Stato: " + ordine.getStato() + ")");
		}
		if (ordine.getQtaimballata() > 0 || ordine.getQtaprelevata() > 0) {
			throw new ResetOrdineException("L'imballo dell'ordine è già cominciato e non è possibile annullarne l'assegnazione.");
		}
		for (RighiOrdine riga : righe) {
			if (riga.getQtaImballata() > 0)
				throw new ResetOrdineException("L'imballo dell'ordine è già cominciato e non è possibile annullarne l'assegnazione.");
		}
	}
	
	protected boolean resettaImballo(TestataOrdini ordine) {
		logger.info("Avvio il reset dell'imballo");
		boolean reset;
		//check sullo stato
		checkResetImballo(ordine);
		//Recupero i movimenti associati all'ordine.
		List<MagaMov> movimentiDaCancellare = recuperaMovimenti(ordine);
		//cerco e correggo i saldi correlati all'ordine.
		Collection<MagaSd> saldiDaAggiornare = correggiSaldi(movimentiDaCancellare, Reset.IMBALLO);
		//Trovo i colli e le righe dell'imballo da eliminare.
		List<RighiImballo> righeImballate = daoRigheImballate.trovaDaNumeroLista(ordine.getNrLista());
		List<ColliImballo> colliImballati = daoColliImballati.trovaDaNumeroLista(ordine.getNrLista());
		//Trovo le righe d'ordine da resettare
		List<RighiOrdine> righe = daoRighe.trovaRigheDaIDOrdine(ordine.getIdTestaSped());
		//Comincio a scrivere la modifiche
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			//aggiusto i campi della testata
			ordine.setStato("INIB");
			ordine.setGenMovUscita("NO");
			ordine.setDataGeneraUscita(null);
			ordine.setQtaimballata(0);
			ordine.setQtaprelevata(0);
			ordine.setTotaleColli(0);
			ordine.setPesoTotale(0.0);
			ordine.setVolumetot(0.0);
			ordine.setImballato("NO");
			em.merge(ordine);
			//aggiusto i campi sulle righe
			for (RighiOrdine riga : righe) {
				riga.setQtaImballata(0);
				riga.setQtaPrelevata(0);
				riga.setDatagenmov(null);
				em.merge(riga);
			}
			//elimino i colli imballati
			for (ColliImballo collo : colliImballati) {
				em.remove(em.contains(collo) ? collo : em.find(ColliImballo.class, collo.getIdColliImballo()));
			}
			//elimino le righe imballate
			for (RighiImballo riga : righeImballate) {
				em.remove(em.contains(riga) ? riga : em.find(RighiImballo.class, riga.getIdRighiImballo()));
			}
			//elimino i movimenti
			for (MagaMov movimento : movimentiDaCancellare) {
				em.remove(em.contains(movimento) ? movimento : em.find(MagaMov.class, movimento.getIdMagaMov()));
			}
			//aggiorno i saldi
			for (MagaSd saldo : saldiDaAggiornare) {
				em.merge(saldo);
			}
			t.commit();
			reset = true;
		} catch (Exception e) {
			reset = false;
			logger.error(e.getMessage(), e);
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return reset;
	}
	
	protected void checkResetImballo(TestataOrdini ordine) {
		switch (ordine.getStato()) {
			case "INIB" : case "DIIB" : case "COIB" : case "ELAB" : case "INSP" : case "SPED" : break;
			default : throw new ResetOrdineException("Lo stato dell'ordine non permette di resettare l'imballo. (Stato: " + ordine.getStato() + ")");
		}
	}
	
	protected boolean resettaImportazione(TestataOrdini ordine) {
		boolean reset;
		//Trovo le righe d'ordine da controllare
		List<RighiOrdine> righe = daoRighe.trovaRigheDaIDOrdine(ordine.getIdTestaSped());
		//check sull'importazione
		checkResetImportazione(ordine, righe);
		//Recupero i movimenti associati all'ordine.
		List<MagaMov> movimentiDaCancellare = recuperaMovimenti(ordine);
		//cerco e correggo i saldi correlati all'ordine.
		Collection<MagaSd> saldiDaAggiornare = correggiSaldi(movimentiDaCancellare, Reset.IMPORTAZIONE);
		//Comincio a scrivere la modifiche
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			//aggiusto i campi della testata
			ordine.setStato("INSE");
			em.merge(ordine);
			//elimino i movimenti
			for (MagaMov movimento : movimentiDaCancellare) {
				em.remove(em.contains(movimento) ? movimento : em.find(MagaMov.class, movimento.getIdMagaMov()));
			}
			//aggiorno i saldi
			for (MagaSd saldo : saldiDaAggiornare) {
				em.merge(saldo);
			}
			t.commit();
			reset = true;
		} catch (Exception e) {
			reset = false;
			logger.error(e.getMessage(), e);
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return reset;
	}
	
	protected void checkResetImportazione(TestataOrdini ordine, List<RighiOrdine> righe) {
		switch (ordine.getStato()) {
			case "IMPO" : break;
			default : throw new ResetOrdineException("Lo stato dell'ordine non permette di resettare l'importazione. (Stato: " + ordine.getStato() + ")");
		}
		if (ordine.getQtaAssegnata() > 0) {
			throw new ResetOrdineException("L'assegnazione dell'ordine è già avviata e non è possibile annullarne l'importazione.");
		}
		for (RighiOrdine riga : righe) {
			if (riga.getQtaAssegnata() > 0)
				throw new ResetOrdineException("L'assegnazione dell'ordine è già avviata e non è possibile annullarne l'importazione.");
		}
	}
	
	protected List<MagaMov> recuperaMovimenti(TestataOrdini ordine) {
		List<MagaMov> movimenti = daoMovimenti.trovaMovimentiOrdine(ordine);
		return movimenti;
	}
	
	protected Collection<MagaSd> correggiSaldi(List<MagaMov> movimenti, Reset tipoReset) {
		HashMap<String, MagaSd> saldi = new HashMap<>();
		for (MagaMov movimento : movimenti) {
			String key = movimento.getCodMaga() + "-" + movimento.getIdUniArticolo();
			if (!saldi.containsKey(key)) {
				MagaSd saldo = daoSaldi.trovaDaArticoloEMagazzino(movimento.getIdUniArticolo(), movimento.getCodMaga());
				if (saldo == null)
					throw new ResetOrdineException("Impossibile trovare il saldo specificato. (Magazzino: " + movimento.getCodMaga() + ", ID Univoco articolo" + movimento.getIdUniArticolo() + ")");
				saldi.put(key, saldo);
			}
			MagaSd saldo = saldi.get(key);
			switch (tipoReset) {
				case IMBALLO : annullaImballo(saldo, movimento); break;
				case IMPORTAZIONE : annullaImpegno(saldo, movimento); break;
				default : throw new ResetOrdineException("Impossibile correggere i saldi al di fuori dell'imballo o dell'impegno!");
			}
		}
		return saldi.values();
	}
	
	protected void annullaImballo(MagaSd saldo, MagaMov movimento) {
		if (movimento.getCausale().equals("IBO")) {
			saldo.setEsistenza(saldo.getEsistenza() + movimento.getQuantita());
			saldo.setImpegnato(saldo.getImpegnato() + movimento.getQuantita());
		}
	}
	
	protected void annullaImpegno(MagaSd saldo, MagaMov movimento) {
		if (movimento.getCausale().equals("IOS")) {
			saldo.setDisponibile(saldo.getDisponibile() + movimento.getQuantita());
			saldo.setImpegnato(saldo.getImpegnato() - movimento.getQuantita());
		}
	}

}

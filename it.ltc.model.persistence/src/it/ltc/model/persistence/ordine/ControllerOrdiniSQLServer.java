package it.ltc.model.persistence.ordine;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ArtibarDao;
import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.dao.legacy.DestinatariDao;
import it.ltc.database.dao.legacy.MagaSdDao;
import it.ltc.database.dao.legacy.MagazzinoDao;
import it.ltc.database.model.legacy.ArtiBar;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.database.model.legacy.Destinatari;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.Magazzini;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.indirizzo.MIndirizzo;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.ordine.Corriere;
import it.ltc.model.interfaces.ordine.MContrassegno;
import it.ltc.model.interfaces.ordine.MOrdine;
import it.ltc.model.interfaces.ordine.ProdottoOrdinato;
import it.ltc.model.interfaces.ordine.TipoIDProdotto;

public class ControllerOrdiniSQLServer extends Dao implements IControllerModel<MOrdine> {

	private static final Logger logger = Logger.getLogger(ControllerOrdiniSQLServer.class);

	private final SimpleDateFormat sdf;
	
	protected final HashMap<String, String> mappaIdentificazioneArticoli;
	protected final HashMap<String, Articoli> mappaArticoliPerIDUnivoco;
	protected final HashMap<String, ArtiBar> mappaBarcodePerIDUnivoco;
	
	protected final ArticoliDao daoArticoli;
	protected final ArtibarDao daoBarcode;
	protected final DestinatariDao daoDestinatari;
	protected final MagaSdDao daoSaldi;
	protected final MagazzinoDao daoMagazzini;

	public ControllerOrdiniSQLServer(String persistenceUnit) {
		super(persistenceUnit);
		mappaIdentificazioneArticoli = new HashMap<>();
		mappaArticoliPerIDUnivoco = new HashMap<>();
		mappaBarcodePerIDUnivoco = new HashMap<>();
		sdf = new SimpleDateFormat("yyMMddHHmmss");
		daoArticoli = new ArticoliDao(persistenceUnit);
		daoBarcode = new ArtibarDao(persistenceUnit);
		daoDestinatari = new DestinatariDao(persistenceUnit);
		daoSaldi = new MagaSdDao(persistenceUnit);
		daoMagazzini = new MagazzinoDao(persistenceUnit);
		logger.info("(Legacy) Controller creato.");
	}

	@Override
	public void valida(MOrdine ordine) throws ModelValidationException {
		logger.info("(Legacy) Comincio la validazione.");
		// Verifica sullo stesso riferimento
		TestataOrdini match = trovaOrdineDaRiferimento(ordine.getRiferimentoOrdine());
		if (match != null)
			throw new ModelValidationException("(Legacy) E' gia' stato inserito un ordine con lo stesso riferimento. (" + ordine.getRiferimentoOrdine() + ")");
		// Verifica sull'esistanza dei prodotti
		if (ordine.getProdotti().isEmpty())
			throw new ModelValidationException("(Legacy) E' necessario inserire almeno un prodotto nell'ordine.");
		//Controllo i magazzini
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			checkMagazzino(prodotto);
		}
		//Ripulisco le mappe
		mappaIdentificazioneArticoli.clear();
		mappaArticoliPerIDUnivoco.clear();
		mappaBarcodePerIDUnivoco.clear();
		TipoIDProdotto tipo = TipoIDProdotto.valueOf(ordine.getTipoIdentificazioneProdotti());
		switch (tipo) {
			case BARCODE: checkPerBarcode(ordine); break;
			case CHIAVE: checkPerChiave(ordine); break;
			case MODELLOTAGLIA:	checkPerModelloTaglia(ordine); break;
		}
		logger.info("(Legacy) Validazione ordine terminata correttamente.");
	}

	public TestataOrdini trovaOrdineDaRiferimento(String riferimento) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TestataOrdini> criteria = cb.createQuery(TestataOrdini.class);
		Root<TestataOrdini> member = criteria.from(TestataOrdini.class);
		criteria.select(member).where(cb.equal(member.get("rifOrdineCli"), riferimento));
		List<TestataOrdini> list = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		TestataOrdini ordine = list.size() == 1 ? list.get(0) : null;
		return ordine;
	}
	
	private void checkMagazzino(ProdottoOrdinato prodotto) {
		Magazzini magazzino = daoMagazzini.trovaDaCodificaCliente(prodotto.getMagazzinoCliente());
		//Guardo se l'ho trovato, se così non fosse provo con la codifica LTC
		if (magazzino == null) {
			magazzino = daoMagazzini.trovaDaCodiceLTC(prodotto.getMagazzinoLTC());
			if (magazzino == null)
				throw new ModelValidationException("Il magazzino indicato non esiste. (magazzino cliente: " + prodotto.getMagazzinoCliente() + ", magazzino LTC: " + prodotto.getMagazzinoLTC() + ", riga: " + prodotto.getNumeroRiga());
		}
		//Aggiorno i campi in base al magazzino trovato.
		prodotto.setMagazzinoCliente(magazzino.getMagaCliente());
		prodotto.setMagazzinoLTC(magazzino.getCodiceMag());
	}
	
	private void checkPerModelloTaglia(MOrdine ordine) throws ModelValidationException {
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			String key = prodotto.getCodicemodello() + prodotto.getTaglia();
			if (!mappaIdentificazioneArticoli.containsKey(key)) {
				Articoli articolo = daoArticoli.trovaDaModelloETaglia(prodotto.getCodicemodello(), prodotto.getTaglia());
				if (articolo == null)
					throw new ModelValidationException("(Legacy) La combinazione modello-taglia: '" + prodotto.getCodicemodello() + "-"	+ prodotto.getTaglia() + "' non è stato trovata. L'ordine non sarà importato.");
				ArtiBar barcode = daoBarcode.trovaDaIDUnivoco(articolo.getIdUniArticolo());
				if (barcode == null)
					throw new ModelValidationException("(Legacy) nessun barcode collegato alla anagrafica ricavata dalla combinazione modello-taglia: '" + prodotto.getCodicemodello() + "-"	+ prodotto.getTaglia() + ", ID Univoco: '" + articolo.getIdUniArticolo() + "'");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
				mappaBarcodePerIDUnivoco.put(articolo.getIdUniArticolo(), barcode);
			}
			prodotto.setChiavelegacy(mappaIdentificazioneArticoli.get(key));
		}	
	}

	private void checkPerChiave(MOrdine ordine) throws ModelValidationException {
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			String key = prodotto.getChiave();
			if (!mappaIdentificazioneArticoli.containsKey(key)) {
				Articoli articolo = daoArticoli.trovaDaSKU(key);
				if (articolo == null)
					throw new ModelValidationException("(Legacy) La chiave: '" + key + "' non è stato trovata. Il carico non sarà importato.");
				ArtiBar barcode = daoBarcode.trovaDaIDUnivoco(articolo.getIdUniArticolo());
				if (barcode == null)
					throw new ModelValidationException("(Legacy) nessun barcode collegato alla anagrafica ricavata dalla chiave: '" + key + "', ID Univoco: '" + articolo.getIdUniArticolo() + "'");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
				mappaBarcodePerIDUnivoco.put(articolo.getIdUniArticolo(), barcode);
			}
			prodotto.setChiavelegacy(mappaIdentificazioneArticoli.get(key));
		}		
	}

	protected void checkPerBarcode(MOrdine ordine) throws ModelValidationException {
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			String key = prodotto.getBarcode();
			if (!mappaIdentificazioneArticoli.containsKey(key)) {
				Articoli articolo = daoArticoli.trovaDaBarcode(key);
				if (articolo == null)
					throw new ModelValidationException("(Legacy) Il barcode '" + key + "' non è stato trovato. Il carico non sarà importato.");
				ArtiBar barcode = daoBarcode.trovaDaBarcode(key);
				if (barcode == null)
					throw new ModelValidationException("(Legacy) Il barcode '" + key + "' non è stato trovato. Il carico non sarà importato.");
				if (articolo.getIdUniArticolo() == null || barcode.getIdUniArticolo() == null || !articolo.getIdUniArticolo().equals(barcode.getIdUniArticolo()))
					throw new ModelValidationException("(Legacy) Non è stata trovata corrispondenza tra Articoli e Artibar per l'ID Univoco tramite il barcode: '" + key + "'.");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
				mappaBarcodePerIDUnivoco.put(articolo.getIdUniArticolo(), barcode);
			}
			prodotto.setChiavelegacy(mappaIdentificazioneArticoli.get(key));
		}
	}

	public Destinatari ottieniDestinatario(MIndirizzo destinatario) throws ModelPersistenceException {
		// Eseguo un controllo sulla ragione sociale
		String ragioneSociale1 = destinatario.getRagionesociale();
		String ragioneSociale2 = "";
		// Se è più lunga di 70 vado a tagliare la parte eccedente.
		if (ragioneSociale1.length() > 70) {
			ragioneSociale1 = ragioneSociale1.substring(0, 70);
		}
		// Se è più lunga di 35 la divido in 2
		if (ragioneSociale1.length() > 35) {
			ragioneSociale2 = ragioneSociale1.substring(35);
			ragioneSociale1 = ragioneSociale1.substring(0, 35);
		}
		// Eseguo una ricerca per vedere se è già presente.
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Destinatari> criteria = cb.createQuery(Destinatari.class);
		Root<Destinatari> member = criteria.from(Destinatari.class);
		Predicate condizioneRagioneSociale = cb.equal(member.get("ragSoc1"), ragioneSociale1);
		Predicate condizioneCap = cb.equal(member.get("cap"), destinatario.getCap());
		Predicate condizioneIndirizzo = cb.equal(member.get("indirizzo"), destinatario.getIndirizzo());
		Predicate condizioneLocalita = cb.equal(member.get("localita"), destinatario.getLocalita());
		Predicate condizioneNazione = cb.equal(member.get("codIso"), destinatario.getNazione());
		criteria.select(member).where(cb.and(condizioneRagioneSociale, condizioneCap, condizioneIndirizzo,
				condizioneLocalita, condizioneNazione));
		List<Destinatari> list = em.createQuery(criteria).setMaxResults(1).getResultList();
		// Se ho trovato corrispondenza lo restituisco, altrimenti lo inserisco
		Destinatari entity;
		if (list.isEmpty()) {
			entity = new Destinatari();
			entity.setCap(destinatario.getCap());
			entity.setCodIso(destinatario.getNazione());
			entity.setCodNaz(destinatario.getNazione());
			entity.setEmail(destinatario.getEmail());
			entity.setIndirizzo(destinatario.getIndirizzo());
			entity.setLocalita(destinatario.getLocalita());
			entity.setNazione(destinatario.getNazione());
			entity.setProvincia(destinatario.getProvincia());
			entity.setRagSoc1(ragioneSociale1);
			entity.setRagSoc2(ragioneSociale2);
			entity.setTel(destinatario.getTelefono());
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				em.persist(entity);
				t.commit();
				logger.info("Inserito nuovo destinatario: " + entity);
			} catch (Exception e) {
				logger.error(e);
				if (t != null && t.isActive())
					t.rollback();
				entity = null;
			} finally {
				em.close();
			}
		} else {
			entity = list.get(0);
			em.close();
		}
		if (entity == null)  throw new ModelPersistenceException("Impossibile recuperare il destinatario.");
		return entity;
	}

	protected TestataOrdini ottieniTestata(MOrdine ordine, Destinatari destinatario) throws ModelPersistenceException {
		// Generazione valori
		String data = sdf.format(new Date());
		int nrListaArrivato = 0; //FIXME - Qua dovrebbe essere un autoincrement. Es. Integer.parseInt(data);
//		String nrLista = data.substring(0, 2);
		String riferimento = ordine.getRiferimentoOrdine();
//		if (riferimento.length() > 14)
//			nrLista += riferimento.substring(0, 14);
//		else {
//			nrLista += riferimento;
//			for (int i = 2; i < 16 - riferimento.length(); i++) {
//				nrLista += "0";
//			}
//		}
		Date dataOrdine = ordine.getDataOrdine();
		String nomeFileArrivato = "INWS" + data + ".SER";
		String note = ordine.getNote();
		if (note == null || note.isEmpty()) {
			note = " ";
		}
		// Testata
		TestataOrdini testata = new TestataOrdini();
//		testata.setNrLista(nrLista);
//		testata.setRagstampe(nrLista);
		testata.setPriorita(ordine.getPriorita());
		testata.setNrListaArrivato(nrListaArrivato);
		testata.setRifOrdineCli(riferimento);
		testata.setNrOrdine(riferimento);
		testata.setDataOrdine(new Timestamp(dataOrdine.getTime()));
		
		testata.setIdDestina(destinatario.getIdDestina());
		testata.setCodCliente(destinatario.getCodDestina());
		
		testata.setStato("IMPO");
		testata.setQtaTotaleSpedire(ordine.getQuantitaTotaleDaSpedire());
		testata.setOperatore("SERVIZIO");
		testata.setNomeFileArrivo(nomeFileArrivato);
		testata.setTipoOrdine(ordine.getTipo());
		testata.setNote(note);
		testata.setValoreDoganale(ordine.getValoreDoganale());
		testata.setCorriere(ordine.getCorriere());
		testata.setCodCorriere(ordine.getCorriere());
		testata.setCodiceClienteCorriere(ordine.getCodiceCorriere());
		testata.setTipoTrasporto(ordine.getServizioCorriere());
		
		try {
			Corriere corriere = Corriere.valueOf(ordine.getCorriere());
			testata.setCorriere(corriere.getNome());
		} catch (Exception e) {
			logger.info("(Legacy) Il corriere " + ordine.getCorriere() + " non è stato trovato. L'ordine verra comunque importato.");
		}
		//Documento
		String nrDoc = ordine.getRiferimentoDocumento();
		String tipoDocumento = ordine.getTipoDocumento();
		if (nrDoc != null && !nrDoc.isEmpty()) {
			testata.setNrDoc(nrDoc);
			testata.setDataDoc(new Timestamp(ordine.getDataDocumento() != null ? ordine.getDataDocumento().getTime() : dataOrdine.getTime()));
			testata.setTipoDoc(tipoDocumento == null || tipoDocumento.isEmpty() ? "ORDINE" : tipoDocumento);
		}		
		if (ordine.getDataConsegna() != null) {
			testata.setDataConsegna(new Timestamp(ordine.getDataConsegna().getTime()));
		}
		//Contrassegno, se presente
		MContrassegno contrassegno = ordine.getContrassegno();
		if (contrassegno != null) {
			testata.setTipoIncasso(contrassegno.getTipo());
			testata.setValContrassegno(contrassegno.getValore());
		} else {
			testata.setTipoIncasso(" ");
			testata.setValContrassegno(0.0);
		}
		return testata;
	}
	
	public Articoli ottieniArticoloDaIDUnivoco(String idUnivoco) {
		return mappaArticoliPerIDUnivoco.get(idUnivoco);
	}
	
	public ArtiBar ottieniBarcodeDaIDUnivoco(String idUnivoco) {
		return mappaBarcodePerIDUnivoco.get(idUnivoco);
	}
	
	public MagaSd ottieniSaldoDaIDUnivocoEMagazzino(String idUnivoco, String magazzino) {
		MagaSd saldo = daoSaldi.trovaDaArticoloEMagazzino(idUnivoco, magazzino);
		return saldo;
	}
	
	protected void ottieniRighe(List<RighiOrdine> righe, TestataOrdini ordine, MOrdine model) {
		for (ProdottoOrdinato prodotto : model.getProdotti()) {
			Articoli match = ottieniArticoloDaIDUnivoco(prodotto.getChiavelegacy());
			ArtiBar matchBarcode = ottieniBarcodeDaIDUnivoco(prodotto.getChiavelegacy());
			if (match != null && matchBarcode != null) {
				RighiOrdine riga = new RighiOrdine();
				riga.setNrLista(ordine.getNrLista());
				riga.setDataOrdine(ordine.getDataOrdine());
				riga.setCodiceArticolo(match.getCodArtStr());
				riga.setTaglia(match.getTaglia());
				riga.setQtaSpedizione(prodotto.getQuantita());
				riga.setDescrizione(match.getDescrizione());
				riga.setNrRigo(prodotto.getNumeroRiga());
				riga.setIdDestina(ordine.getIdDestina());
				riga.setIdUnicoArt(match.getIdUniArticolo());
				riga.setMagazzino(prodotto.getMagazzinoLTC());
				riga.setRagstampe1(ordine.getNrLista());
				riga.setColore(match.getColore());
				riga.setBarraEAN(matchBarcode.getBarraEAN());
				riga.setBarraUPC(matchBarcode.getBarraUPC());
				riga.setPONumber(prodotto.getRiferimentoCliente());
				riga.setNoteCliente(prodotto.getNote());
				riga.setCodBarre(match.getCodBarre());
				riga.setTipoord(ordine.getTipoOrdine());
				inserisciInformazioniAggiuntiveRigo(model, riga);
				righe.add(riga);
			}
		}
	}
	
	protected void ottieniSaldiEMovimenti(List<MagaSd> saldiDaInserire, List<MagaSd> saldiDaAggiornare, List<MagaMov> movimenti, TestataOrdini testata, MOrdine ordine) {
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			int esistenza;
			int disponibilita;
			int impegnato;
			MagaSd matchSaldi = ottieniSaldoDaIDUnivocoEMagazzino(prodotto.getChiavelegacy(), prodotto.getMagazzinoLTC());
			if (matchSaldi != null) {
				esistenza = matchSaldi.getEsistenza();
				disponibilita = matchSaldi.getDisponibile() - prodotto.getQuantita();
				impegnato = matchSaldi.getImpegnato() + prodotto.getQuantita();
				matchSaldi.setDisponibile(disponibilita);
				matchSaldi.setImpegnato(impegnato);
				inserisciInformazioniAggiuntiveSaldi(ordine, matchSaldi);
				saldiDaAggiornare.add(matchSaldi);
			} else {
				esistenza = 0;
				disponibilita = 0 - prodotto.getQuantita();
				impegnato = prodotto.getQuantita();
				MagaSd nuovoSaldo = new MagaSd();
				nuovoSaldo.setCodMaga(prodotto.getMagazzinoLTC());
				nuovoSaldo.setDisponibile(disponibilita);
				nuovoSaldo.setImpegnato(impegnato);
				nuovoSaldo.setEsistenza(0);
				nuovoSaldo.setIdUniArticolo(prodotto.getChiavelegacy());
				inserisciInformazioniAggiuntiveSaldi(ordine, nuovoSaldo);
				saldiDaInserire.add(nuovoSaldo);
			}
			// Movimenti
			MagaMov movimento = new MagaMov();
			movimento.setCausale("IOS");
			movimento.setCodMaga(prodotto.getMagazzinoLTC());
			movimento.setDocData(testata.getDataOrdine());
			movimento.setDataMovMag(testata.getDataOrdine());
			movimento.setOraMovMag(0);
			movimento.setDocCat("O");
			movimento.setDocTipo("ORD");
			movimento.setDocNr(testata.getNrLista());
			movimento.setDocNote("IMPEGNATO DA ORDINE CLIENTE");
			movimento.setQuantita(prodotto.getQuantita());
			movimento.setSegno("+");
			movimento.setTipo("IP");
			movimento.setUtente("WS");
			movimento.setSegnoEsi("N");
			movimento.setSegnoImp("+");
			movimento.setSegnoDis("-");
			movimento.setIncTotali("NO");
			movimento.setEsistenzamov(esistenza);
			movimento.setDisponibilemov(disponibilita);
			movimento.setImpegnatomov(impegnato);
			movimento.setIdUniArticolo(prodotto.getChiavelegacy());
			movimento.setIDdocum(testata.getIdTestaSped());
			inserisciInformazioniAggiuntiveMovimenti(ordine, movimento);
			movimenti.add(movimento);
		}
	}

	@Override
	public MOrdine inserisci(MOrdine ordine) throws ModelPersistenceException {
		// Inserisco o recupero il destinatario dell'ordine.
		Destinatari destinatario = ottieniDestinatario(ordine.getDestinatario());
		// Aggiungo altre info specifiche del cliente, se necessario.
		inserisciInformazioniAggiuntiveIndirizzo(ordine, destinatario);
		//Ottengo le info sull'ordine
		TestataOrdini testata = ottieniTestata(ordine, destinatario);
		//Informazioni aggiuntive specifiche del cliente, se necessario
		inserisciInformazioniAggiuntiveTestata(ordine, testata);
		//Preparo le liste che conterrano le righe, i saldi e i movimenti da scrivere nel DB
		List<RighiOrdine> righe = new LinkedList<>();
		List<MagaSd> saldiDaInserire = new LinkedList<>();
		List<MagaSd> saldiDaAggiornare = new LinkedList<>();
		List<MagaMov> movimenti = new LinkedList<>();
		//Esamino la lista dei prodotti ordinati e riempo le liste corrispondenti
		ottieniRighe(righe, testata, ordine);
		ottieniSaldiEMovimenti(saldiDaInserire, saldiDaAggiornare, movimenti, testata, ordine);
		// Transaction
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		boolean successo;
		try {
			t.begin();
			//Testata e righe
			em.persist(testata);
			for (RighiOrdine riga : righe) {
				riga.setIdTestataOrdine(testata.getIdTestaSped());
				riga.setNrLista(testata.getNrLista());
				em.persist(riga);
			}
			//Saldi e movimenti
			for (MagaSd saldo : saldiDaInserire) {
				em.persist(saldo);
			}
			for (MagaSd saldo : saldiDaAggiornare) {
				MagaSd s = em.find(MagaSd.class, saldo.getIdMagaSd());
				s.setDisponibile(saldo.getDisponibile());
				s.setImpegnato(saldo.getImpegnato());
				em.merge(s);
			}
			for (MagaMov movimento : movimenti) {
				em.persist(movimento);
			}
			t.commit();
			successo = true;
			ordine.setId(testata.getIdTestaSped());
			logger.info("Inserito nuovo ordine: " + testata.getNrLista());
		} catch (Exception e) {
			logger.error(e);
			successo = false;
			ordine = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		if (!successo) {
			String errorMessage = "(Legacy) L'inserimento dell'ordine è fallito: " + testata.getNrLista();
			logger.info(errorMessage);
			throw new ModelPersistenceException(errorMessage);
		}
		return ordine;
	}

	@Override
	public MOrdine modifica(MOrdine ordine) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MOrdine elimina(MOrdine ordine) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Da sovrascrivere nel caso in cui devono essere inserite informazioni
	 * addizionali nella testata
	 * 
	 * @param ordine
	 * @param testata
	 * @return true se si può procedere con la scrittura su DB, false
	 *         altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveTestata(MOrdine ordine, TestataOrdini testata) {
		// DA ESTENDERE.
		return true;
	}

	/**
	 * Da sovrascrivere nel caso in cui devono essere inserite informazioni
	 * addizionali nei righi
	 * 
	 * @param ordine
	 * @param rigo
	 * @return true se si può procedere con la scrittura su DB, false
	 *         altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveRigo(MOrdine ordine, RighiOrdine rigo) {
		// DA ESTENDERE.
		return true;
	}

	/**
	 * Da sovrascrivere nel caso in cui devono essere inserite informazioni
	 * addizionali nell'indirizzo
	 * 
	 * @param ordine
	 * @param indirizzo
	 * @return true se si può procedere con la scrittura su DB, false
	 *         altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveIndirizzo(MOrdine ordine, Destinatari indirizzo) {
		// DA ESTENDERE.
		return true;
	}

	/**
	 * Da sovrascrivere nel caso in cui devono essere inserite informazioni
	 * addizionali nei saldi
	 * 
	 * @param ordine
	 * @param saldi
	 * @return true se si può procedere con la scrittura su DB, false
	 *         altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveSaldi(MOrdine ordine, MagaSd saldi) {
		// DA ESTENDERE.
		return true;
	}

	/**
	 * Da sovrascrivere nel caso in cui devono essere inserite informazioni
	 * addizionali nei movimenti
	 * 
	 * @param ordine
	 * @param movimenti
	 * @return true se si può procedere con la scrittura su DB, false
	 *         altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveMovimenti(MOrdine ordine, MagaMov movimenti) {
		// DA ESTENDERE.
		return true;
	}

}

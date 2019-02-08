package it.ltc.model.persistence.ordine;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.common.NazioneDao;
import it.ltc.database.dao.common.ProvinciaDao;
import it.ltc.database.dao.legacy.ArtibarDao;
import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.dao.legacy.DestinatariDao;
import it.ltc.database.dao.legacy.MagaMovDao;
import it.ltc.database.dao.legacy.MagaSdDao;
import it.ltc.database.dao.legacy.MagazzinoDao;
import it.ltc.database.dao.legacy.MittentiOrdineDao;
import it.ltc.database.dao.legacy.RighiOrdineDao;
import it.ltc.database.dao.legacy.TestataOrdiniDao;
import it.ltc.database.dao.legacy.TestataOrdiniTipoDao;
import it.ltc.database.model.centrale.Nazione;
import it.ltc.database.model.centrale.Provincia;
import it.ltc.database.model.legacy.ArtiBar;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.database.model.legacy.Destinatari;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.Magazzini;
import it.ltc.database.model.legacy.MittentiOrdine;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.database.model.legacy.TestataOrdiniTipo;
import it.ltc.database.model.legacy.model.CausaliMovimento;
import it.ltc.model.interfaces.exception.ModelAlreadyExistentException;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.indirizzo.MIndirizzo;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.ordine.Corriere;
import it.ltc.model.interfaces.ordine.MContrassegno;
import it.ltc.model.interfaces.ordine.MInfoSpedizione;
import it.ltc.model.interfaces.ordine.MOrdine;
import it.ltc.model.interfaces.ordine.ProdottoOrdinato;
import it.ltc.model.interfaces.ordine.TipoIDProdotto;

public class ControllerOrdiniSQLServer extends Dao implements IControllerModel<MOrdine> {

	private static final Logger logger = Logger.getLogger(ControllerOrdiniSQLServer.class);
	
	public static final String REGEX_EMAIL = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	public static final String REGEX_CAP_ITA = "^\\d{5}";

	protected final SimpleDateFormat sdf;
	
	protected final HashMap<String, String> mappaIdentificazioneArticoli;
	protected final HashMap<String, Articoli> mappaArticoliPerIDUnivoco;
	protected final HashMap<String, ArtiBar> mappaBarcodePerIDUnivoco;
	protected final HashMap<String, MagaSd> mappaSaldi;
	
	protected final TestataOrdiniTipoDao daoTipiOrdine;
	protected final TestataOrdiniDao daoOrdini;
	protected final RighiOrdineDao daoRigheOrdine;
	protected final ArticoliDao daoArticoli;
	protected final ArtibarDao daoBarcode;
	protected final DestinatariDao daoDestinatari;
	protected final MittentiOrdineDao daoMittenti;
	protected final MagaSdDao daoSaldi;
	protected final MagaMovDao daoMovimenti;
	protected final MagazzinoDao daoMagazzini;	
	
	protected final ProvinciaDao daoProvince;
	protected final NazioneDao daoNazioni;

	public ControllerOrdiniSQLServer(String persistenceUnit) {
		super(persistenceUnit);
		mappaIdentificazioneArticoli = new HashMap<>();
		mappaArticoliPerIDUnivoco = new HashMap<>();
		mappaBarcodePerIDUnivoco = new HashMap<>();
		mappaSaldi = new HashMap<>();
		sdf = new SimpleDateFormat("yyMMddHHmmss");
		daoTipiOrdine = new TestataOrdiniTipoDao(persistenceUnit);
		daoOrdini = new TestataOrdiniDao(persistenceUnit);
		daoRigheOrdine = new RighiOrdineDao(persistenceUnit);
		daoArticoli = new ArticoliDao(persistenceUnit);
		daoBarcode = new ArtibarDao(persistenceUnit);
		daoDestinatari = new DestinatariDao(persistenceUnit);
		daoMittenti = new MittentiOrdineDao(persistenceUnit);
		daoSaldi = new MagaSdDao(persistenceUnit);
		daoMovimenti = new MagaMovDao(persistenceUnit);
		daoMagazzini = new MagazzinoDao(persistenceUnit);
		daoProvince = new ProvinciaDao("produzione");
		daoNazioni = new NazioneDao("produzione");
		logger.info("(Legacy) Controller creato.");
	}

	@Override
	public void valida(MOrdine ordine) throws ModelValidationException {
		logger.info("(Legacy) Comincio la validazione per l'ordine '" + ordine.getRiferimentoOrdine() + "'");
		checkValiditaCampi(ordine);		
		//Ripulisco le mappe
		mappaIdentificazioneArticoli.clear();
		mappaArticoliPerIDUnivoco.clear();
		mappaBarcodePerIDUnivoco.clear();
		//Eseguo la validazione sui prodotti indicati e popolo le mappe con le info recuperate.
		TipoIDProdotto tipo = ordine.getTipoIdentificazioneProdotti();
		switch (tipo) {
			case BARCODE: checkPerBarcode(ordine); break;
			case CHIAVE: checkPerChiave(ordine); break;
			case MODELLOTAGLIA:	checkPerModelloTaglia(ordine); break;
		}
		logger.info("(Legacy) Validazione ordine terminata correttamente.");
	}
	
	protected void checkValiditaCampi(MOrdine ordine) {
		//Validazione base
		ordine.valida();
		
		//Verifico che il tipo d'ordine sia valido
		TestataOrdiniTipo tipo = daoTipiOrdine.trovaTipoDaCodice(ordine.getTipo());
		if (tipo == null)
			throw new ModelValidationException("E' necessario indicare un tipo di ordine valido. (Ordine: " + ordine.getRiferimentoOrdine() + ", tipo indicato: " + ordine.getTipo() + ")");
		
		// Verifica sullo stesso riferimento
		TestataOrdini match = trovaOrdineDaRiferimento(ordine.getRiferimentoOrdine());
		if (match != null)
			throw new ModelAlreadyExistentException("(Legacy) E' gia' stato inserito un ordine con lo stesso riferimento. (" + ordine.getRiferimentoOrdine() + ")");
		
		//Controllo i magazzini
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			checkMagazzino(prodotto);
		}
		
		MIndirizzo destinatario = ordine.getDestinatario();
		checkValiditaIndirizzo(destinatario, ordine);
		
		MIndirizzo mittente = ordine.getMittente();
		checkValiditaIndirizzo(mittente, ordine);
	}
	
	protected void checkValiditaIndirizzo(MIndirizzo indirizzo, MOrdine ordine) {
		// Controllo dei campi necessari
		String ragioneSociale = indirizzo.getRagioneSociale();
		if (ragioneSociale == null || ragioneSociale.isEmpty())
			throw new ModelValidationException("E' necessario specificare una ragione sociale per l'indirizzo. (" + ordine.getRiferimentoOrdine() + ")");
		else if (ragioneSociale.length() > 50)
			throw new ModelValidationException("La ragione sociale indicata è troppo lunga. (MAX 50 caratteri, " + ordine.getRiferimentoOrdine() + ")");

		String via = indirizzo.getIndirizzo();
		if (via == null || via.isEmpty())
			throw new ModelValidationException("E' necessario specificare una via per l'indirizzo. (" + ordine.getRiferimentoOrdine() + ")");
		else if (via.length() > 250)
			throw new ModelValidationException("L'indirizzo indicato è troppo lungo. (MAX 250 caratteri, " + ordine.getRiferimentoOrdine() + ")");

		String localita = indirizzo.getLocalita();
		if (localita == null || localita.isEmpty())
			throw new ModelValidationException("E' necessario specificare una localita' per l'indirizzo. (" + ordine.getRiferimentoOrdine() + ")");
		else if (localita.length() > 50)
			throw new ModelValidationException("La località indicata è troppo lunga. (MAX 50 caratteri, " + ordine.getRiferimentoOrdine() + ")");

		String provincia = indirizzo.getProvincia();
		if (provincia == null || provincia.isEmpty())
			throw new ModelValidationException("E' necessario specificare una provincia per l'indirizzo (" + ordine.getRiferimentoOrdine() + ")");
		else {
			Provincia p = daoProvince.trovaDaSigla(provincia);
			if (p == null)
				throw new ModelValidationException("E' necessario specificare una provincia esistente per l'indirizzo. (Provincia inserita : " + provincia + " su " + ordine.getRiferimentoOrdine() + ")");
		}

		String nazione = indirizzo.getNazione();
		Nazione n;
		if (nazione == null || nazione.isEmpty()) {
			throw new ModelValidationException("E' necessario specificare un codice nazione ISO per l'indirizzo. (" + ordine.getRiferimentoOrdine() + ")");
		} else {
			switch (nazione.length()) {
				case 2 : n = daoNazioni.trovaDaCodiceISO2(nazione); break;
				case 3 : n = daoNazioni.trovaDaCodiceISO3(nazione); break;
				default : n = null;
			}			
			if (n == null) {
				throw new ModelValidationException("E' necessario specificare un codice nazione ISO esistente per l'indirizzo. (Nazione inserita: " + nazione + " su (" + ordine.getRiferimentoOrdine() + ")");
			} else {
				//Qualunque sia il modo il modo in cui l'ho trovata mi assicuro che venga salvata come ISO 3.
				nazione = n.getCodiceIsoTre();
				indirizzo.setNazione(nazione);
			}				
		}

		String cap = indirizzo.getCap();
		if (cap == null || cap.isEmpty())
			throw new ModelValidationException("E' necessario specificare un cap per l'indirizzo. (" + ordine.getRiferimentoOrdine() + ")");
		else if (n != null) {
			// Controllo del CAP in base alla nazione.
			boolean capValido;
			switch (nazione) {
			case "ITA":
				capValido = cap.matches(REGEX_CAP_ITA);
				break;
			default:
				capValido = cap.length() < 11; // 10 o meno caratteri.
			}
			if (!capValido)
				throw new ModelValidationException("E' necessario specificare un cap valido per l'indirizzo. (Cap inserito: " + cap + " su (" + ordine.getRiferimentoOrdine() + ")");
		}

		// Campi facoltativi
		String telefono = indirizzo.getTelefono();
		if (telefono != null && telefono.length() > 20)
			throw new ModelValidationException("Il numero telefonico specificato e' troppo lungo (MAX 20 Caratteri, " + ordine.getRiferimentoOrdine() + ")");

		String email = indirizzo.getEmail();
		if (email != null) {
//			if (!email.matches(REGEX_EMAIL))
//				logger.warn("L'indirizzo email indicato non e' valido. (" + email + ")");
			if (email.length() > 100)
				throw new ModelValidationException("L'indirizzo email specificato e' troppo lungo (MAX 100 Caratteri, " + ordine.getRiferimentoOrdine() + ")");
		}
	}

	public TestataOrdini trovaOrdineDaRiferimento(String riferimento) {
		TestataOrdini ordine = daoOrdini.trovaDaRiferimento(riferimento);
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
					throw new ModelValidationException("(Legacy) La combinazione modello-taglia: '" + prodotto.getCodicemodello() + "-"	+ prodotto.getTaglia() + "' non è stato trovata. L'ordine " + ordine.getRiferimentoOrdine() + " non sarà importato.");
				List<ArtiBar> barcodes = daoBarcode.trovaDaIDUnivoco(articolo.getIdUniArticolo());
				if (barcodes == null || barcodes.isEmpty())
					throw new ModelValidationException("(Legacy) nessun barcode collegato alla anagrafica ricavata dalla combinazione modello-taglia: '" + prodotto.getCodicemodello() + "-"	+ prodotto.getTaglia() + ", ID Univoco: '" + articolo.getIdUniArticolo() + "'");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
				mappaBarcodePerIDUnivoco.put(articolo.getIdUniArticolo(), barcodes.get(0));
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
					throw new ModelValidationException("(Legacy) La chiave: '" + key + "' non è stato trovata. L'ordine " + ordine.getRiferimentoOrdine() + " non sarà importato.");
				List<ArtiBar> barcodes = daoBarcode.trovaDaIDUnivoco(articolo.getIdUniArticolo());
				if (barcodes == null || barcodes.isEmpty())
					throw new ModelValidationException("(Legacy) nessun barcode collegato alla anagrafica ricavata dalla chiave: '" + key + "', ID Univoco: '" + articolo.getIdUniArticolo() + "'");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
				mappaBarcodePerIDUnivoco.put(articolo.getIdUniArticolo(), barcodes.get(0));
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
					throw new ModelValidationException("(Legacy) Il barcode '" + key + "' non è stato trovato. L'ordine " + ordine.getRiferimentoOrdine() + " non sarà importato.");
				ArtiBar barcode = daoBarcode.trovaDaBarcode(key);
				if (barcode == null)
					throw new ModelValidationException("(Legacy) Il barcode '" + key + "' non è stato trovato. L'ordine " + ordine.getRiferimentoOrdine() + " non sarà importato.");
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
		String ragioneSociale1 = destinatario.getRagioneSociale();
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
		// Se ho trovato corrispondenza lo restituisco, altrimenti lo inserisco
		Destinatari entity = daoDestinatari.trovaIndirizzo(ragioneSociale1, destinatario.getCap(), destinatario.getIndirizzo(), destinatario.getLocalita(), destinatario.getNazione());
		if (entity == null) {
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
			entity = daoDestinatari.inserisci(entity);
			if (entity == null)
				throw new ModelPersistenceException("Impossibile recuperare il destinatario.");
		}
		return entity;
	}
	
	public MittentiOrdine ottieniMittente(MIndirizzo mittente) {
		// Eseguo una ricerca per vedere se è già presente.
		// Se ho trovato corrispondenza lo restituisco, altrimenti lo inserisco
		MittentiOrdine entity = daoMittenti.trovaIndirizzo(mittente.getRagioneSociale(), mittente.getCap(), mittente.getIndirizzo(), mittente.getLocalita(), mittente.getNazione());
		if (entity == null) {
			entity = new MittentiOrdine();
			entity.setCap(mittente.getCap());
			entity.setEmail(mittente.getEmail());
			entity.setIndirizzo(mittente.getIndirizzo());
			entity.setLocalita(mittente.getLocalita());
			entity.setNazione(mittente.getNazione());
			entity.setProvincia(mittente.getProvincia());
			entity.setRagioneSociale(mittente.getRagioneSociale());
			entity.setTelefono(mittente.getTelefono());
			entity = daoMittenti.inserisci(entity);
		}
		return entity;
	}
	
	protected void inserisciInfoSpedizione(MOrdine ordine, TestataOrdini testata) {
		MInfoSpedizione info = ordine.getInfoSpedizione();
		
		testata.setValoreDoganale(info.getValoreDoganale());
		testata.setCorriere(info.getCorriere());
		testata.setCodCorriere(info.getCorriere());
		testata.setCodiceClienteCorriere(info.getCodiceCorriere());
		testata.setTipoTrasporto(info.getServizioCorriere());
		
		try {
			Corriere corriere = Corriere.valueOf(info.getCorriere());
			testata.setCorriere(corriere.getNome());
		} catch (Exception e) {
			logger.info("(Legacy) Il corriere " + info.getCorriere() + " non è stato trovato. L'ordine " + ordine.getRiferimentoOrdine() + " verrà comunque importato.");
		}
		//Contrassegno, se presente
		MContrassegno contrassegno = info.getContrassegno();
		if (contrassegno != null) {
			testata.setTipoIncasso(contrassegno.getTipo().name());
			testata.setValContrassegno(contrassegno.getValore());
		} else {
			testata.setTipoIncasso(" ");
			testata.setValContrassegno(0.0);
		}
	}

	protected TestataOrdini ottieniTestata(MOrdine ordine, Destinatari destinatario, MittentiOrdine mittente) throws ModelPersistenceException {
		// Generazione valori
		GregorianCalendar now = new GregorianCalendar();
		String data = sdf.format(now.getTime());
		int nrListaArrivato = 0; //FIXME - Qua dovrebbe essere un autoincrement. Es. Integer.parseInt(data);
		String riferimento = ordine.getRiferimentoOrdine();
		Date dataOrdine = ordine.getDataOrdine();
		String nomeFileArrivato = "INWS" + data + ".SER";
		String note = ordine.getNote();
		if (note == null || note.isEmpty()) {
			note = " ";
		}
		// Testata
		TestataOrdini testata = new TestataOrdini();
		testata.setPriorita(ordine.getPriorita() != null ? ordine.getPriorita() : 1);
		testata.setNrListaArrivato(nrListaArrivato);
		testata.setRifOrdineCli(riferimento);
		testata.setNrOrdine(riferimento);
		testata.setDataOrdine(new Timestamp(dataOrdine.getTime()));
		testata.setAnnoOrdine(now.get(Calendar.YEAR));
		
		testata.setIdDestina(destinatario.getIdDestina());
		testata.setCodCliente(destinatario.getCodDestina());
		testata.setRagioneSocialeDestinatario(destinatario.getRagSoc1());
		
		testata.setIdMittente(mittente.getIdMittente());
		
		testata.setStato("IMPO");
		testata.setQtaTotaleSpedire(ordine.getQuantitaTotaleDaSpedire());
		testata.setOperatore("SERVIZIO");
		testata.setNomeFileArrivo(nomeFileArrivato);
		testata.setTipoOrdine(ordine.getTipo());
		testata.setNote(note);
		
		//Info sul trasporto (se presenti)
		inserisciInfoSpedizione(ordine, testata);
		
		//Documento
		String nrDoc = ordine.getRiferimentoDocumento();
		String tipoDocumento = ordine.getTipoDocumento();
		if (nrDoc != null && !nrDoc.isEmpty()) {
			testata.setNrDoc(nrDoc);
			testata.setDataDoc(new Timestamp(ordine.getDataDocumento() != null ? ordine.getDataDocumento().getTime() : dataOrdine.getTime()));
			testata.setTipoDoc(tipoDocumento == null || tipoDocumento.isEmpty() ? "ORDINE" : tipoDocumento);
			testata.setAnnodoc(now.get(Calendar.YEAR));
		}		
		if (ordine.getDataConsegna() != null) {
			testata.setDataConsegna(new Timestamp(ordine.getDataConsegna().getTime()));
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
		String keySaldo = idUnivoco + "-" +  magazzino;
		if (!mappaSaldi.containsKey(keySaldo)) {
			MagaSd saldo = daoSaldi.trovaDaArticoloEMagazzino(idUnivoco, magazzino);
			mappaSaldi.put(keySaldo, saldo);
		}
		return mappaSaldi.get(keySaldo);
	}
	
	protected void ottieniRighe(List<RighiOrdine> righe, TestataOrdini ordine, MOrdine model) {
		for (ProdottoOrdinato prodotto : model.getProdotti()) {
			Articoli match = ottieniArticoloDaIDUnivoco(prodotto.getChiavelegacy());
			ArtiBar matchBarcode = ottieniBarcodeDaIDUnivoco(prodotto.getChiavelegacy());
			if (match != null && matchBarcode != null) {
				RighiOrdine riga = new RighiOrdine();
				riga.setIdArticolo(match.getIdArticolo());
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
				riga.setNrOrdine(ordine.getRifOrdineCli());
				inserisciInformazioniAggiuntiveRigo(model, riga);
				righe.add(riga);
			}
		}
	}
	
	protected void ottieniSaldiEMovimenti(List<MagaSd> saldiDaInserire, List<MagaSd> saldiDaAggiornare, List<MagaMov> movimenti, TestataOrdini testata, MOrdine ordine) {
		//Resetto le info sui saldi prima di cominciare.
		mappaSaldi.clear();
		//Per ognuna delle righe d'ordine vado a ottenere il saldo e il movimento corrispondenti.
		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
			//int esistenza;
			int disponibilita;
			int impegnato;
			MagaSd saldo = ottieniSaldoDaIDUnivocoEMagazzino(prodotto.getChiavelegacy(), prodotto.getMagazzinoLTC());
			if (saldo != null) {
				//esistenza = saldo.getEsistenza();
				disponibilita = saldo.getDisponibile() - prodotto.getQuantita();
				impegnato = saldo.getImpegnato() + prodotto.getQuantita();
				saldo.setDisponibile(disponibilita);
				saldo.setImpegnato(impegnato);
				inserisciInformazioniAggiuntiveSaldi(ordine, saldo);
				saldiDaAggiornare.add(saldo);
			} else {
				//esistenza = 0;
				disponibilita = 0 - prodotto.getQuantita();
				impegnato = prodotto.getQuantita();
				saldo = new MagaSd();
				saldo.setCodMaga(prodotto.getMagazzinoLTC());
				saldo.setDisponibile(disponibilita);
				saldo.setImpegnato(impegnato);
				saldo.setEsistenza(0);
				saldo.setIdUniArticolo(prodotto.getChiavelegacy());
				inserisciInformazioniAggiuntiveSaldi(ordine, saldo);
				saldiDaInserire.add(saldo);
			}
			// Movimenti
			MagaMov movimento = daoMovimenti.getNuovoMovimento(CausaliMovimento.IOS, testata.getNrLista(), testata.getIdTestaSped(), testata.getDataOrdine(), saldo, prodotto.getChiavelegacy(), prodotto.getMagazzinoLTC(), prodotto.getQuantita());
			inserisciInformazioniAggiuntiveMovimenti(ordine, movimento);
			movimenti.add(movimento);
		}
	}

	@Override
	public MOrdine inserisci(MOrdine ordine) throws ModelPersistenceException {
		// Inserisco o recupero il destinatario dell'ordine.
		Destinatari destinatario = ottieniDestinatario(ordine.getDestinatario());
		// Aggiungo altre info specifiche del cliente, se necessario.
		inserisciInformazioniAggiuntiveDestinatario(ordine, destinatario);
		// Inserisco o recupero il mittente dell'ordine
		MittentiOrdine mittente = ottieniMittente(ordine.getMittente());
		// Aggiungo altre info specifiche del mittente, se necessario.
		inserisciInformazioniAggiuntiveMittente(ordine, mittente);
		//Ottengo le info sull'ordine
		TestataOrdini testata = ottieniTestata(ordine, destinatario, mittente);
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
				riga.setRagstampe1(testata.getNrLista());
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
				movimento.setDocNr(testata.getNrLista());
				movimento.setIDdocum(testata.getIdTestaSped());
				em.persist(movimento);
			}
			t.commit();
			successo = true;
			ordine.setId(testata.getIdTestaSped());
			logger.info("Inserito nuovo ordine: " + testata.getNrLista());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
	 * @return true se si può procedere con la scrittura su DB, false altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveDestinatario(MOrdine ordine, Destinatari indirizzo) {
		// DA ESTENDERE.
		return true;
	}
	
	/**
	 * Da sovrascrivere nel caso in cui devono essere inserite informazioni
	 * addizionali nell'indirizzo del mittente
	 * 
	 * @param ordine
	 * @param indirizzo
	 * @return true se si può procedere con la scrittura su DB, false altrimenti.
	 */
	public boolean inserisciInformazioniAggiuntiveMittente(MOrdine ordine, MittentiOrdine indirizzo) {
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
	
	/**
	 * Sposta il file selezionato nella cartella specificata.
	 * @param file il file da spostare.
	 * @param pathCartella la cartella di destinazione.
	 */
	protected void spostaFile(File file, String pathCartella) {
		String nomeFile = file.getName();
		File fileDaSpostare = new File(pathCartella + nomeFile);
		boolean spostato = file.renameTo(fileDaSpostare);
		if (spostato) {
			logger.info("Spostato il file '" + nomeFile + "' in '" + pathCartella + "'");
		}
	}

}

package it.ltc.model.persistence.carico;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.dao.legacy.FornitoreDao;
import it.ltc.database.dao.legacy.MagazzinoDao;
import it.ltc.database.dao.legacy.PakiTestaDao;
import it.ltc.database.dao.legacy.PakiTestaTipoDao;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.database.model.legacy.Fornitori;
import it.ltc.database.model.legacy.Magazzini;
import it.ltc.database.model.legacy.PakiArticolo;
import it.ltc.database.model.legacy.PakiTesta;
import it.ltc.model.interfaces.carico.MCarico;
import it.ltc.model.interfaces.carico.MRigaCarico;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.ordine.TipoIDProdotto;

public class ControllerCaricoSQLServer extends Dao implements IControllerModel<MCarico> {
	
	private static final Logger logger = Logger.getLogger(ControllerCaricoSQLServer.class);
	
	private final PakiTestaTipoDao daoTipo;
	protected final PakiTestaDao daoCarichi;
	protected final ArticoliDao daoArticoli;
	protected final FornitoreDao daoFornitori;
	protected final MagazzinoDao daoMagazzini;
	
	protected Fornitori fornitore;
	protected final HashMap<String, String> mappaIdentificazioneArticoli;
	protected final HashMap<String, Articoli> mappaArticoliPerIDUnivoco;
	

	public ControllerCaricoSQLServer(String persistenceUnit) {
		super(persistenceUnit);
		daoTipo = new PakiTestaTipoDao(persistenceUnit);
		daoCarichi = new PakiTestaDao(persistenceUnit);
		daoArticoli = new ArticoliDao(persistenceUnit);
		daoFornitori = new FornitoreDao(persistenceUnit);
		daoMagazzini = new MagazzinoDao(persistenceUnit);
		mappaIdentificazioneArticoli = new HashMap<>();
		mappaArticoliPerIDUnivoco = new HashMap<>();
	}

	@Override
	public void valida(MCarico ingresso) throws ModelValidationException {
		//Controllo il tipo di carico
		checkTipoCarico(ingresso.getTipoDocumento());
		//Controllo sull'unicità del riferimento al carico
		PakiTesta esistente = daoCarichi.trovaDaRiferimento(ingresso.getRiferimento());
		if (esistente != null)
			throw new ModelValidationException("Esiste gia' un carico con lo stesso riferimento. (" + ingresso.getRiferimento() + ")");
		//check sul fornitore
		fornitore = daoFornitori.trovaDaCodice(ingresso.getFornitore());
		if (fornitore == null)
			throw new ModelValidationException("Il fornitore indicato non esiste. ( " + ingresso.getFornitore() + " )");
		//check sugli articoli
		if (ingresso.getProdotti().isEmpty())
			throw new ModelValidationException("(Legacy) E' necessario inserire almeno un prodotto nel carico.");
		//check sui magazzini
		for (MRigaCarico prodotto : ingresso.getProdotti()) {
			checkMagazzino(prodotto);
		}
		//Ripulisco le mappe
		mappaIdentificazioneArticoli.clear();
		mappaArticoliPerIDUnivoco.clear();
		//Eseguo una ricerca specifica dei prodotti in base alla tipologia di identificazione.
		TipoIDProdotto tipo = TipoIDProdotto.valueOf(ingresso.getTipoIdentificazioneProdotti());
		switch (tipo) {
			case BARCODE: checkPerBarcode(ingresso); break;
			case CHIAVE: checkPerChiave(ingresso); break;
			case MODELLOTAGLIA:	checkPerModelloTaglia(ingresso); break;
		}		
		logger.info("(Legacy) Validazione carico terminata correttamente.");
	}
	
	private void checkMagazzino(MRigaCarico prodotto) {
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
	
	private void checkTipoCarico(String codice) {
		if (daoTipo.trovaDaCodice(codice) == null)
			throw new ModelValidationException("Il tipo di carico specificato non esiste. (" + codice + ")");
	}
	
	private void checkPerModelloTaglia(MCarico ingresso) throws ModelValidationException {
		for (MRigaCarico prodotto : ingresso.getProdotti()) {
			String key = prodotto.getCodicemodello() + prodotto.getTaglia();
			if (!mappaIdentificazioneArticoli.containsKey(key)) {
				Articoli articolo = daoArticoli.trovaDaModelloETaglia(prodotto.getCodicemodello(), prodotto.getTaglia());
				if (articolo == null)
					throw new ModelValidationException("(Legacy) La combinazione modello-taglia: '" + prodotto.getCodicemodello() + "-"	+ prodotto.getTaglia() + "' non è stato trovata. L'ordine non sarà importato.");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
			}
			prodotto.setChiavelegacy(mappaIdentificazioneArticoli.get(key));
		}	
	}

	private void checkPerChiave(MCarico ingresso) throws ModelValidationException {
		for (MRigaCarico prodotto : ingresso.getProdotti()) {
			String key = prodotto.getChiave();
			if (!mappaIdentificazioneArticoli.containsKey(key)) {
				Articoli articolo = daoArticoli.trovaDaSKU(key);
				if (articolo == null)
					throw new ModelValidationException("(Legacy) La chiave '" + key + "' non è stato trovata. Il carico non sarà importato.");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
			}
			prodotto.setChiavelegacy(mappaIdentificazioneArticoli.get(key));
		}		
	}

	protected void checkPerBarcode(MCarico ingresso) throws ModelValidationException {
		for (MRigaCarico prodotto : ingresso.getProdotti()) {
			String key = prodotto.getBarcode();
			if (!mappaIdentificazioneArticoli.containsKey(key)) {
				Articoli articolo = daoArticoli.trovaDaBarcode(key);
				if (articolo == null)
					throw new ModelValidationException("(Legacy) Il barcode '" + key + "' non è stato trovato. Il carico non sarà importato.");
				mappaIdentificazioneArticoli.put(key, articolo.getIdUniArticolo());
				mappaArticoliPerIDUnivoco.put(articolo.getIdUniArticolo(), articolo);
			}
			prodotto.setChiavelegacy(mappaIdentificazioneArticoli.get(key));
		}
	}

	@Override
	public MCarico inserisci(MCarico ingresso) throws ModelPersistenceException {
		// Deserializzazione testata
		PakiTesta carico = deserializzaIngresso(ingresso);
		//Deserializzazione righe
		List<PakiArticolo> dettagli = deserializzaDettagli(ingresso);
		// Vado in scrittura
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(carico);
			for (PakiArticolo dettaglio : dettagli) {
				dettaglio.setIdPakiTesta(carico.getIdTestaPaki());
				em.persist(dettaglio);
			}
			t.commit();
			logger.info("Carico inserito!");
			ingresso.setId(carico.getIdTestaPaki());
		} catch (Exception e) {
			logger.error(e);
			ingresso = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return ingresso;
	}
	
	public PakiTesta deserializzaIngresso(MCarico ingresso) throws ModelPersistenceException {
		PakiTesta carico = new PakiTesta();
		if (ingresso != null) {
			//Controllo sul fornitore
			carico.setCodFornitore(fornitore.getCodiceFornitore());
			carico.setIdFornitore(fornitore.getIdFornitore());
			carico.setRagSocFor(fornitore.getRagSoc());
			//Altre info sul carico
			Date dataArrivo = ingresso.getDataArrivo() != null ? ingresso.getDataArrivo() : new Date();
			carico.setDataArrivo(new Timestamp(dataArrivo.getTime()));
			carico.setNrPaki(ingresso.getRiferimento());
			carico.setTipodocumento(ingresso.getTipo());
			carico.setQtaTotAto(ingresso.getPezziStimati());
			carico.setNote(ingresso.getNote());
			//Campi documento - Non vengono passati in update.
			Date dataIngresso = ingresso.getDataDocumento() != null ? ingresso.getDataDocumento() : new Date();
			carico.setDataPaki(new Timestamp(dataIngresso.getTime()));
			carico.setNrDocInterno(ingresso.getRiferimentoDocumento() != null ? ingresso.getRiferimentoDocumento() : ingresso.getRiferimento());
			carico.setTipoDoc(ingresso.getTipoDocumento() != null ? ingresso.getTipoDocumento() : "CARICO");
		}
		return carico;
	}
	
	public List<PakiArticolo> deserializzaDettagli(MCarico ingresso) {
		List<PakiArticolo> dettagli = new LinkedList<>();
		if (ingresso != null) {
			int idCarico = ingresso.getId(); //non usabile in inserimento.
			String riferimento = ingresso.getRiferimento();
			for (MRigaCarico prodotto : ingresso.getProdotti()) {
				PakiArticolo dettaglio = deserializzaDettaglio(prodotto);
				dettaglio.setIdPakiTesta(idCarico);
				dettaglio.setNrOrdineFor(riferimento);
				dettagli.add(dettaglio);
			}
		}
		return dettagli;
	}

	public PakiArticolo deserializzaDettaglio(MRigaCarico riga) throws ModelPersistenceException {
		Articoli prodotto = mappaArticoliPerIDUnivoco.get(riga.getChiavelegacy());
		PakiArticolo dettaglio = new PakiArticolo();
		dettaglio.setIdArticolo(prodotto.getIdArticolo());
		dettaglio.setRigaPacki(riga.getNumeroRiga());
		dettaglio.setCodBarre(prodotto.getIdUniArticolo());
		dettaglio.setCodUnicoArt(prodotto.getIdUniArticolo());
		dettaglio.setCodArtStr(prodotto.getCodArtStr());
		dettaglio.setMagazzino(riga.getMagazzinoCliente());
		dettaglio.setMagazzinoltc(riga.getMagazzinoLTC());
		dettaglio.setQtaPaki(riga.getQuantita());
		dettaglio.setNrDispo(riga.getNote());
		return dettaglio;
	}

	@Override
	public MCarico modifica(MCarico model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MCarico elimina(MCarico model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
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

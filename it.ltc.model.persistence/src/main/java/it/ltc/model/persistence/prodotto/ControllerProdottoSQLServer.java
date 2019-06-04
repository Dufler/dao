package it.ltc.model.persistence.prodotto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ArtibarDao;
import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.dao.legacy.CategoriaMerceologicaLegacyDao;
import it.ltc.database.model.legacy.ArtiBar;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.database.model.legacy.CatMercGruppi;
import it.ltc.model.interfaces.exception.ModelAlreadyExistentException;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.prodotto.MProdotto;

public class ControllerProdottoSQLServer extends Dao implements IControllerModel<MProdotto> {
	
	/**
	 * Modalità d'inserimento anagrafiche.<br>
	 * Come default se un'anagrafica è già presente (stesso SKU o stessa combinazione modello-taglia) viene saltata, con la modalità <code>AGGIUNGI_BARCODE</code> invece viene aggiunto il barcode a quelli esistenti se possibile.
	 * @author Damiano
	 *
	 */
	public enum Mode { DEFAULT, AGGIUNGI_BARCODE }
	
	private static final Logger logger = Logger.getLogger(ControllerProdottoSQLServer.class);
	
	private static final SimpleDateFormat idUnivocoGenerator = new SimpleDateFormat("yyMMddHHmmssSSS");
	
	protected final Mode strategy;
	
	protected final ArticoliDao daoArticoli;
	protected final ArtibarDao daoBarcode;
	protected final CategoriaMerceologicaLegacyDao daoCategoriaMerceologica;
	
	public ControllerProdottoSQLServer(String persistenceUnit) {
		this(persistenceUnit, Mode.DEFAULT);
	}
	
	public ControllerProdottoSQLServer(String persistenceUnit, Mode mode) {
		super(persistenceUnit);
		daoArticoli = new ArticoliDao(persistenceUnit);
		daoBarcode = new ArtibarDao(persistenceUnit);
		daoCategoriaMerceologica = new CategoriaMerceologicaLegacyDao(persistenceUnit);
		strategy = mode;
	}
	
	protected Articoli trovaEsistente(MProdotto prodotto) {
		Articoli match = daoArticoli.trovaDaSKU(prodotto.getChiaveCliente());
		if (match == null) {
			match = daoArticoli.trovaDaModelloETaglia(prodotto.getCodiceModello(), prodotto.getTaglia());
		}
		return match;
	}

	@Override
	public MProdotto inserisci(MProdotto prodotto) throws ModelPersistenceException {
		Articoli articolo = deserializza(prodotto);
		ArtiBar barcodeArticolo = generaBarcodeArticolo(articolo, prodotto);
		//Genero l'ID univoco dell'articolo, se non è già presente
		if (articolo.getIdUniArticolo() == null) {
			String idUnivoco = getIDUnivoco();
			articolo.setIdUniArticolo(idUnivoco);
		}	
		barcodeArticolo.setIdUniArticolo(articolo.getIdUniArticolo());
		//Inserisco
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if (articolo.getIdArticolo() == 0)
				em.persist(articolo);
			em.persist(barcodeArticolo);
			t.commit();
			prodotto.setId(articolo.getIdArticolo());
		} catch (Exception e) {
			logger.error("Articolo: " + prodotto.getChiaveCliente() + " in errore: " + e.getMessage(), e);
			prodotto = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return prodotto;
	}
	
	protected Articoli deserializza(MProdotto prodotto) {
		Articoli articolo = null;
		//se sono in modalità aggiungi barcode controllo se esiste già a sistema
		if (strategy == Mode.AGGIUNGI_BARCODE) {
			articolo = trovaEsistente(prodotto);
		}
		if (articolo == null) {
			articolo = new Articoli();
			articolo.setCodArtStr(prodotto.getChiaveCliente());
			articolo.setModello(prodotto.getCodiceModello());
			articolo.setCodBarre(prodotto.getBarcode());
			articolo.setBarraEAN(prodotto.getBarcode());
			articolo.setTaglia(prodotto.getTaglia());
			articolo.setColore(prodotto.getColore());
			articolo.setDescrizione(prodotto.getDescrizione());
			articolo.setComposizione(prodotto.getComposizione());
			articolo.setLinea(prodotto.getBrand());
			articolo.setCategoria(prodotto.getCategoria());
			articolo.setCatMercGruppo(prodotto.getCategoria());
			articolo.setCatMercDett(prodotto.getSottoCategoria());
			articolo.setMadeIn(prodotto.getMadeIn());
			articolo.setStagione(prodotto.getStagione());
			if (prodotto.getPeso() != null) //null check
				articolo.setArtPeso(prodotto.getPeso().doubleValue());
			articolo.setArtH(prodotto.getH());
			articolo.setArtL(prodotto.getL());
			articolo.setArtZ(prodotto.getZ());
			if (prodotto.getValore() != null) //null check
				articolo.setValVen(prodotto.getValore());
			articolo.setBarraUPC(prodotto.getBarcodeFornitore());
			articolo.setCodArtOld(prodotto.getSkuFornitore());
			articolo.setDescAggiuntiva(prodotto.getDescrizioneAggiuntiva());
			articolo.setNote(prodotto.getNote());
			articolo.setNumerata(prodotto.getNumerata());
			articolo.setUmPos(prodotto.getPosizioneNumerata());
			articolo.setUm(prodotto.getUnitaMisura());
			articolo.setPezziCassa(prodotto.getPezziCassa());
			articolo.setQtaConf(prodotto.getPezziConfezione());
			articolo.setCassa(prodotto.getCassa().name());
			articolo.setTipoCassa(prodotto.getTipoCassa());
			articolo.setUtente(utente);
		}
		return articolo;
	}
	
	protected ArtiBar generaBarcodeArticolo(Articoli articolo, MProdotto prodotto) {
		ArtiBar barcode = new ArtiBar();
		if (strategy == Mode.AGGIUNGI_BARCODE) {
			barcode.setBarraEAN(prodotto.getBarcode());
			barcode.setBarraUPC(prodotto.getBarcodeFornitore());
		} else {
			barcode.setBarraEAN(articolo.getBarraEAN());
			barcode.setBarraUPC(articolo.getBarraUPC());
		}
		barcode.setCodiceArticolo(articolo.getCodArtStr());
		barcode.setIdUniArticolo(articolo.getIdUniArticolo());
		barcode.setTaglia(articolo.getTaglia());
		barcode.setNumerata(articolo.getNumerata());
		barcode.setPosizione(articolo.getUmPos());
		return barcode;
	}
	
	protected static synchronized String getIDUnivoco() {
		Date now = new Date();
		String chiave = idUnivocoGenerator.format(now);
		//Mi fermo un millisecondo per evitare ID duplicati.
		try { Thread.sleep(1); } catch (InterruptedException e) {}
		return chiave;
	}
	
	protected void validaSoloBarcode(MProdotto prodotto) throws ModelValidationException {
		//Controllo barcode
		Articoli checkBarcode = daoArticoli.trovaDaBarcode(prodotto.getBarcode());
		if (checkBarcode != null)
			throw new ModelAlreadyExistentException("(Legacy) E' gia' presente un prodotto con lo stesso barcode. (" + prodotto.getBarcode() + ")");
		ArtiBar checkBarcode2 = daoBarcode.trovaDaBarcode(prodotto.getBarcode());
		if (checkBarcode2 != null)
			throw new ModelAlreadyExistentException("(Legacy) E' gia' presente un prodotto con lo stesso barcode. (" + prodotto.getBarcode() + ")");
	}
	
	protected void validaTutto(MProdotto prodotto) throws ModelValidationException {
		//Controllo sul codice modello e taglia
		Articoli checkModelloTaglia = daoArticoli.trovaDaModelloETaglia(prodotto.getCodiceModello(), prodotto.getTaglia());
		if (checkModelloTaglia != null)
			throw new ModelAlreadyExistentException("(Legacy) E' gia' presente un prodotto con la stessa combinazione codice modello-taglia. (modello: " + prodotto.getCodiceModello() + ", taglia: " + prodotto.getTaglia() + ")");
		//Controllo barcode
		Articoli checkBarcode = daoArticoli.trovaDaBarcode(prodotto.getBarcode());
		if (checkBarcode != null)
			throw new ModelAlreadyExistentException("(Legacy) E' gia' presente un prodotto con lo stesso barcode. (" + prodotto.getBarcode() + ")");
		//Controllo chiave cliente
		Articoli checkChiave = daoArticoli.trovaDaSKU(prodotto.getChiaveCliente());
		if (checkChiave != null)
			throw new ModelAlreadyExistentException("(Legacy) E' gia' presente un prodotto con la stessa chiave identificativa. (" + prodotto.getChiaveCliente() + ")");
	}

	@Override
	public void valida(MProdotto prodotto) throws ModelValidationException {
		if (strategy == Mode.AGGIUNGI_BARCODE) {
			validaSoloBarcode(prodotto);
		} else {
			validaTutto(prodotto);
		}
		//Controllo sulla categoria di fatturazione, solo se presente
		if (prodotto.getCategoria() != null && !prodotto.getCategoria().isEmpty()) {
			CatMercGruppi checkCategoria = daoCategoriaMerceologica.trovaDaCodice(prodotto.getCategoria());
			if (checkCategoria == null)
				throw new ModelValidationException("(Legacy) La categoria merceologica specificata non esiste. (" + prodotto.getCategoria() + ")");
		}		
	}

	@Override
	public MProdotto modifica(MProdotto model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MProdotto elimina(MProdotto model) throws ModelPersistenceException {
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

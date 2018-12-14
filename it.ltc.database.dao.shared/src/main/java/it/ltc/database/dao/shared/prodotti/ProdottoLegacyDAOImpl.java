package it.ltc.database.dao.shared.prodotti;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jboss.logging.Logger;

import it.ltc.database.model.legacy.ArtiBar;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.model.shared.dao.IProdottoDao;
import it.ltc.model.shared.dao.IProdottoDaoBase;
import it.ltc.model.shared.json.cliente.ProdottoJSON;
import it.ltc.services.custom.exception.CustomException;

public class ProdottoLegacyDAOImpl extends ProdottoDaoConVerifiche<Articoli> implements IProdottoDao {
	
	private static final Logger logger = Logger.getLogger("ProdottoLegacyDAOImpl");
	
	protected ArticoliLegacyDao daoArticoli;
	
	protected String utente;

	public ProdottoLegacyDAOImpl(String persistenceUnit, Integer idCommessa) {
		super(persistenceUnit, idCommessa);
		logger.info("Istanziato dao prodotti legacy generico");
	}
	
	protected IProdottoDaoBase<Articoli> getDaoProdotti(String persistenceUnit) {
		daoArticoli = new ArticoliLegacyDao(persistenceUnit); 
		return daoArticoli;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}

	@Override
	public ProdottoJSON inserisci(ProdottoJSON prodotto) {
		//Deserializzo
		Articoli articolo = deserializza(prodotto);
		ArtiBar barcodeArticolo = generaBarcodeArticolo(articolo);
		//Eseguo i controlli
		checkCodArtStrUnicity(articolo.getCodArtStr());
		checkBarcodeUnicity(articolo.getCodBarre());
		checkCategoria(articolo.getCategoria());
		//Genero l'ID univoco dell'articolo
		String idUnivoco = getIDUnivoco();
		articolo.setIdUniArticolo(idUnivoco);
		barcodeArticolo.setIdUniArticolo(idUnivoco);
		//Inserisco
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(articolo);
			em.persist(barcodeArticolo);
			t.commit();
		} catch (Exception e) {
			logger.error(e);
			articolo = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return serializza(articolo);
	}

	protected ArtiBar generaBarcodeArticolo(Articoli articolo) {
		ArtiBar barcode = new ArtiBar();
		barcode.setBarraEAN(articolo.getBarraEAN());
		barcode.setBarraUPC(articolo.getBarraUPC());
		barcode.setCodiceArticolo(articolo.getCodArtStr());
		barcode.setIdUniArticolo(articolo.getIdUniArticolo());
		barcode.setTaglia(articolo.getTaglia());
		barcode.setNumerata(articolo.getNumerata());
		barcode.setPosizione(articolo.getUmPos());
		return barcode;
	}

	@Override
	//@Transactional
	public ProdottoJSON aggiorna(ProdottoJSON prodotto) {
		//Controllo di avere già l'anagrafica basandomi sullo SKU
		Articoli articolo = daoProdotti.trovaDaSKU(prodotto.getChiaveCliente());
		ArtiBar barcode = daoArtibar.trovaDaSKU(prodotto.getChiaveCliente());
		if (articolo == null || barcode == null)
			throw new CustomException("L'articolo che si sta tentando di aggiornare non esiste. Controlla la chiave cliente!");
		//Se esiste controllo che non sia già arrivato in magazzino
		//nel caso in cui sia gia' presente eseguo l'aggiornamento solo per alcuni campi.
		boolean presente = daoColliPack.isProdottoPresenteInMagazzino(prodotto.getChiaveCliente());
		//Recupero la entity
		EntityManager em = getManager();
		articolo = em.find(Articoli.class, articolo.getIdArticolo());
		barcode = em.find(ArtiBar.class, barcode.getIdArtiBar());
		if (!presente) {
			//update sul barcode
			articolo.setCodBarre(prodotto.getBarcode());
			barcode.setBarraEAN(prodotto.getBarcode());
			//update sulla taglia
			articolo.setTaglia(prodotto.getTaglia());
			barcode.setTaglia(prodotto.getTaglia());
			//update su tutto il resto
			articolo.setCategoria(prodotto.getCategoria());
			articolo.setCatMercGruppo(prodotto.getCategoria());
			articolo.setLinea(prodotto.getBrand());
			//null check sugli opzionali
			if (prodotto.getBarcodeFornitore() != null && !prodotto.getBarcodeFornitore().isEmpty())
				articolo.setBarraEAN(prodotto.getBarcodeFornitore());
			if (prodotto.getSkuFornitore() != null && !prodotto.getSkuFornitore().isEmpty())
				articolo.setCodArtOld(prodotto.getSkuFornitore());
			if (prodotto.getMadeIn() != null && !prodotto.getMadeIn().isEmpty())
				articolo.setMadeIn(prodotto.getMadeIn());
			if (prodotto.getStagione() != null && !prodotto.getStagione().isEmpty())
				articolo.setStagione(prodotto.getStagione());
		}
		//Aggiorno tutto il resto
		//null check sugli opzionali.
		if (prodotto.getComposizione() != null && !prodotto.getComposizione().isEmpty())
			articolo.setComposizione(prodotto.getComposizione());
		if (prodotto.getColore() != null && !prodotto.getColore().isEmpty())
			articolo.setColore(prodotto.getColore());
		if (prodotto.getDescrizione() != null && !prodotto.getDescrizione().isEmpty())
			articolo.setDescrizione(prodotto.getDescrizione());
		if (prodotto.getDescrizioneAggiuntiva() != null && !prodotto.getDescrizioneAggiuntiva().isEmpty())
			articolo.setDescAggiuntiva(prodotto.getDescrizioneAggiuntiva());
		if (prodotto.getNote() != null && !prodotto.getNote().isEmpty())
			articolo.setNote(prodotto.getNote());
		if (prodotto.getH() != null)
			articolo.setArtH(prodotto.getH());
		if (prodotto.getL() != null)
			articolo.setArtL(prodotto.getL());
		if (prodotto.getZ() != null)
			articolo.setArtZ(prodotto.getZ());
		if (prodotto.getPeso() != null)
			articolo.setArtPeso(prodotto.getPeso().doubleValue());
		if (prodotto.getValore() != null)
			articolo.setValVen(prodotto.getValore());
		//Update
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.merge(articolo);
			t.commit();
		} catch (Exception e) {
			logger.error(e);
			articolo = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return serializza(articolo);
	}

	@Override
	public ProdottoJSON dismetti(ProdottoJSON prodotto) {
		if (prodotto == null)
			throw new CustomException("L'articolo che si sta tentando di aggiornare non esiste. Controlla la chiave cliente!");
		Articoli articolo = daoProdotti.trovaDaSKU(prodotto.getChiaveCliente());
		if (articolo == null)
			throw new CustomException("L'articolo che si sta tentando di aggiornare non esiste. Controlla la chiave cliente!");
		//Se esiste controllo che non sia presente su carichi o ordini attualmente in lavorazione.
		if (daoPakiArticolo.isProdottoPresenteInCarichi(prodotto.getChiaveCliente()))
			throw new CustomException("Non e' possibile dismettere il prodotto, è attualmente presente in uno o più carichi.");
		if (daoRigheOrdini.isProdottoPresente(prodotto.getChiaveCliente()))
			throw new CustomException("Non e' possibile dismettere il prodotto, è attualmente presente in uno o più ordini.");
		//Se esiste controllo che non sia già arrivato in magazzino
		if (daoColliPack.isProdottoPresenteInMagazzino(prodotto.getChiaveCliente())) {
			throw new CustomException("Non e' possibile dismettere il prodotto, è attualmente presente in magazzino.");
		}
		//Elimino anche il barcode ad esso collegato.
		ArtiBar barcode = daoArtibar.trovaDaSKU(prodotto.getChiaveCliente());
		//Delete
		EntityManager em = getManager();
		articolo = em.find(Articoli.class, articolo.getIdArticolo());
		barcode = em.find(ArtiBar.class, barcode.getIdArtiBar());
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.remove(articolo);
			em.remove(barcode);
			t.commit();
		} catch(Exception e) {
			logger.error(e);
			articolo = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}	
		return serializza(articolo);
	}

	protected Articoli deserializza(ProdottoJSON json) {
		Articoli articolo = new Articoli();
		if (json != null) {
			articolo.setCodArtStr(json.getChiaveCliente());
			articolo.setModello(json.getCodiceModello());
			articolo.setCodBarre(json.getBarcode());
			articolo.setBarraEAN(json.getBarcode());
			articolo.setTaglia(json.getTaglia());
			articolo.setColore(json.getColore());
			articolo.setDescrizione(json.getDescrizione());
			articolo.setComposizione(json.getComposizione());
			articolo.setLinea(json.getBrand());
			articolo.setCategoria(json.getCategoria());
			articolo.setCatMercGruppo(json.getCategoria());
			articolo.setCatMercDett(json.getSottoCategoria());
			articolo.setMadeIn(json.getMadeIn());
			articolo.setStagione(json.getStagione());
			if (json.getPeso() != null) //null check
				articolo.setArtPeso(json.getPeso().doubleValue());
			articolo.setArtH(json.getH());
			articolo.setArtL(json.getL());
			articolo.setArtZ(json.getZ());
			if (json.getValore() != null) //null check
				articolo.setValVen(json.getValore());
			articolo.setBarraUPC(json.getBarcodeFornitore());
			articolo.setCodArtOld(json.getSkuFornitore());
			articolo.setDescAggiuntiva(json.getDescrizioneAggiuntiva());
			articolo.setNote(json.getNote());
			articolo.setTipoCassa(json.getCassa());
			articolo.setPezziCassa(json.getPezziEffettivi());
			articolo.setUtente(utente);
		}
		return articolo;
	}

	protected ProdottoJSON serializza(Articoli articolo) {
		ProdottoJSON json;
		if (articolo != null) {
			json = new ProdottoJSON();
			json.setId(articolo.getIdArticolo());
			//Controllo sul valore per la cassa.
			String cassa = articolo.getCassa();
			if (cassa == null || cassa.isEmpty())
				cassa = "NO";
			else if (!"BUNDLE".equals(cassa)) //FIXME: Fix temporaneo per i clienti che hanno info sul campo "tipoCassa"
				cassa = "STANDARD";
			json.setCassa(cassa);
			json.setChiaveCliente(articolo.getCodArtStr());
			json.setCodiceModello(articolo.getModello());
			json.setBarcode(articolo.getCodBarre());
			json.setTaglia(articolo.getTaglia());
			json.setColore(articolo.getColore());
			json.setDescrizione(articolo.getDescrizione());
			json.setComposizione(articolo.getComposizione());
			json.setBrand(articolo.getLinea());
			json.setCategoria(articolo.getCatMercGruppo());
			json.setSottoCategoria(articolo.getCatMercDett());
			json.setMadeIn(articolo.getMadeIn());
			json.setStagione(articolo.getStagione());
			if (articolo.getArtPeso() != null) //null check
				json.setPeso(articolo.getArtPeso().intValue());
			json.setH(articolo.getArtH());
			json.setL(articolo.getArtL());
			json.setZ(articolo.getArtZ());
			if (articolo.getValVen() != null)
				json.setValore(articolo.getValVen().doubleValue());
			json.setBarcodeFornitore(articolo.getBarraUPC());
			json.setSkuFornitore(articolo.getCodArtOld());
			json.setDescrizioneAggiuntiva(articolo.getDescAggiuntiva());
			json.setNote(articolo.getNote());
			json.setPezziEffettivi(articolo.getPezziCassa());
			json.setCommessa(commessa);
		} else {
			json = null;
		}
		return json;
	}

	@Override
	public ProdottoJSON trovaPerSKU(String sku) {
		Articoli articolo = daoProdotti.trovaDaSKU(sku);
        ProdottoJSON prodotto = articolo == null ? null : serializza(articolo);
		return prodotto;
	}

	@Override
	public ProdottoJSON trovaPerBarcode(String barcode) {
		Articoli articolo = daoProdotti.trovaDaBarcode(barcode);
        ProdottoJSON prodotto = articolo == null ? null : serializza(articolo);
        logger.info("Articolo trovato: " + prodotto);
		return prodotto;
	}

	@Override
	public List<ProdottoJSON> trova(ProdottoJSON filtro) {
		List<Articoli> entities = daoArticoli.trova(filtro.getChiaveCliente(), filtro.getCodiceModello(), filtro.getStagione(), filtro.getDescrizione(), 100);
		List<ProdottoJSON> jsons = new LinkedList<>();
		for (Articoli entity : entities)
			jsons.add(serializza(entity));
		return jsons;
	}

	@Override
	public List<ProdottoJSON> trovaDaUltimaModifica(Date ultimaModifica) {
		List<Articoli> entities = daoArticoli.trovaDaUltimaModifica(ultimaModifica);
		List<ProdottoJSON> jsons = new LinkedList<>();
		for (Articoli entity : entities) {
			ProdottoJSON json = serializza(entity);
			json.setDataUltimaModifica(entity.getDataModifica());
			jsons.add(json);
		}
		return jsons;
	}
	
}

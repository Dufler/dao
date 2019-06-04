package it.ltc.database.dao.shared.prodotti;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jboss.logging.Logger;

import it.ltc.database.model.legacy.ArtiBar;
import it.ltc.database.model.legacy.coltorti.ArticoliColtorti;
import it.ltc.model.shared.dao.IProdottoDao;
import it.ltc.model.shared.dao.IProdottoDaoBase;
import it.ltc.model.shared.json.cliente.ProdottoJSON;
import it.ltc.model.shared.json.cliente.TipoCassa;
import it.ltc.services.custom.exception.CustomException;

public class ProdottoColtortiDAOImpl extends ProdottoDaoConVerifiche<ArticoliColtorti> implements IProdottoDao {
	
	private static final Logger logger = Logger.getLogger("ProdottoColtortiDAOImpl");
	
	protected ArticoliLegacyColtortiDao daoArticoli;
	protected String utente;

	public ProdottoColtortiDAOImpl(String persistenceUnit, Integer idCommessa) {
		super(persistenceUnit, idCommessa);
	}
	
	protected IProdottoDaoBase<ArticoliColtorti> getDaoProdotti(String persistenceUnit) {
		daoArticoli = new ArticoliLegacyColtortiDao(persistenceUnit); 
		return daoArticoli;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}

	@Override
	public ProdottoJSON inserisci(ProdottoJSON prodotto) {
		//Deserializzo
		ArticoliColtorti articolo = deserializza(prodotto);
		ArtiBar barcodeArticolo = generaBarcodeArticolo(articolo);
		//Eseguo i controlli
		checkCodArtStrUnicity(articolo.getCodArtStr());
		checkBarcodeUnicity(articolo.getCodBarre());
		checkCategoria(articolo.getCategoria());
		//Genero l'ID univoco dell'articolo
		String idUnivoco = getIDUnivoco();
		articolo.setIdUniArticolo(idUnivoco);
		barcodeArticolo.setIdUniArticolo(idUnivoco);
		logger.info("nuovo ID univoco per l'articolo: '" + idUnivoco + "'");
		//Inserisco
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(articolo);
			em.persist(barcodeArticolo);
			t.commit();
			logger.info("(Legacy SQL) Prodotto inserito!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			articolo = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return serializza(articolo);
	}

	@Override
	protected ArtiBar generaBarcodeArticolo(ArticoliColtorti articolo) {
		ArtiBar barcode = new ArtiBar();
		barcode.setBarraEAN(articolo.getCodBarre());
		barcode.setBarraUPC(articolo.getBarraEAN());
		barcode.setCodiceArticolo(articolo.getCodArtStr());
		barcode.setIdUniArticolo(articolo.getIdUniArticolo());
		barcode.setTaglia(articolo.getTaglia());
		return barcode;
	}

	@Override
	public ProdottoJSON aggiorna(ProdottoJSON prodotto) {
		//Controllo di avere già l'anagrafica basandomi sullo SKU
		ArticoliColtorti articolo = daoProdotti.trovaDaSKU(prodotto.getChiaveCliente());
		List<ArtiBar> barcodes = daoArtibar.trovaDaSKU(prodotto.getChiaveCliente());
		if (articolo == null || barcodes == null || barcodes.isEmpty())
			throw new CustomException("L'articolo che si sta tentando di aggiornare non esiste. Controlla la chiave cliente!");
		//Se esiste controllo che non sia già arrivato in magazzino
		//nel caso in cui sia gia' presente eseguo l'aggiornamento solo per alcuni campi.
		boolean presente = daoColliPack.isProdottoPresenteInMagazzino(prodotto.getChiaveCliente());
		//Recupero la entity
		EntityManager em = getManager();
		//Update
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if (!presente) {
				//Update sul primo barcode (i successivi sono solo seriali)
				ArtiBar barcode = barcodes.get(0);
				barcode = em.find(ArtiBar.class, barcode.getIdArtiBar());
				barcode.setBarraEAN(prodotto.getBarcode()); 
				barcode.setTaglia(prodotto.getTaglia());
				em.merge(barcode);
				//Update sul prodotto
				articolo = em.find(ArticoliColtorti.class, articolo.getIdArticolo());
				//update sul barcode
				articolo.setCodBarre(prodotto.getBarcode());
				//update sulla taglia
				articolo.setTaglia(prodotto.getTaglia());
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
				articolo.setArtPeso(new Double(prodotto.getPeso()));
			if (prodotto.getValore() != null)
				articolo.setValVen(new Double(prodotto.getValore()));
			em.merge(articolo);
			t.commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
		ArticoliColtorti articolo = daoProdotti.trovaDaSKU(prodotto.getChiaveCliente());
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
		List<ArtiBar> barcodes = daoArtibar.trovaDaSKU(prodotto.getChiaveCliente());
		//Delete
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			articolo = em.find(ArticoliColtorti.class, articolo.getIdArticolo());
			em.remove(articolo);
			for (ArtiBar barcode : barcodes) {
				barcode = em.find(ArtiBar.class, barcode.getIdArtiBar());
				em.remove(barcode);
			}
			t.commit();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			articolo = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}		
		return serializza(articolo);
	}

	public ArticoliColtorti deserializza(ProdottoJSON json) {
		ArticoliColtorti articolo = new ArticoliColtorti();
		if (json != null) {
			articolo.setCodArtStr(json.getChiaveCliente());
			articolo.setModello(json.getCodiceModello());
			articolo.setCodBarre(json.getBarcode());
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
				articolo.setArtPeso(new Double(json.getPeso()));
			articolo.setArtH(json.getH());
			articolo.setArtL(json.getL());
			articolo.setArtZ(json.getZ());
			if (json.getValore() != null) //null check
				articolo.setValVen(new Double(json.getValore()));
			articolo.setBarraEAN(json.getBarcodeFornitore());
			articolo.setCodArtOld(json.getSkuFornitore());
			articolo.setDescAggiuntiva(json.getDescrizioneAggiuntiva());
			articolo.setNote(json.getNote());
//			articolo.setTipoCassa(json.getCassa());
			articolo.setCassa(json.getCassa().name());
			articolo.setUtente(utente);
			logger.info("Articolo deserializzato: " + articolo);
		}
		return articolo;
	}

	public ProdottoJSON serializza(ArticoliColtorti articolo) {
		ProdottoJSON json;
		if (articolo != null) {
			json = new ProdottoJSON();
			json.setId(articolo.getIdArticolo());
			//Controllo sul valore per la cassa.
//			String cassa = articolo.getTipoCassa();
//			if (cassa == null || cassa.isEmpty())
//				cassa = "NO";
//			json.setCassa(cassa);
			json.setCassa(TipoCassa.getTipo(articolo.getCassa()));
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
			json.setBarcodeFornitore(articolo.getBarraEAN());
			json.setSkuFornitore(articolo.getCodArtOld());
			json.setDescrizioneAggiuntiva(articolo.getDescAggiuntiva());
			json.setNote(articolo.getNote());
			json.setParticolarita(articolo.getParticolarita());
			json.setCommessa(commessa);
			json.setDataUltimaModifica(articolo.getDataModifica());
		} else {
			json = null;
		}
		return json;
	}

	@Override
	public ProdottoJSON trovaPerSKU(String sku) {
		ArticoliColtorti articolo = daoProdotti.trovaDaSKU(sku);
        ProdottoJSON prodotto = articolo == null ? null : serializza(articolo);
		return prodotto;
	}

	@Override
	public ProdottoJSON trovaPerBarcode(String barcode) {
		ArticoliColtorti articolo = daoProdotti.trovaDaBarcode(barcode);
        ProdottoJSON prodotto = articolo == null ? null : serializza(articolo);
        logger.info("Articolo trovato: " + prodotto);
		return prodotto;
	}
	
	@Override
	public List<ProdottoJSON> trova(ProdottoJSON filtro) {
		List<ArticoliColtorti> entities = daoArticoli.trova(filtro.getChiaveCliente(), filtro.getCodiceModello(), filtro.getStagione(), filtro.getDescrizione(), 100);
		List<ProdottoJSON> jsons = new LinkedList<>();
		for (ArticoliColtorti entity : entities)
			jsons.add(serializza(entity));
		return jsons;
	}

	@Override
	public List<ProdottoJSON> trovaDaUltimaModifica(Date ultimaModifica) {
		List<ArticoliColtorti> entities = daoArticoli.trovaDaUltimaModifica(ultimaModifica);
		List<ProdottoJSON> jsons = new LinkedList<>();
		for (ArticoliColtorti entity : entities) {
			ProdottoJSON json = serializza(entity);
			json.setDataUltimaModifica(entity.getDataModifica());
			jsons.add(json);
		}
		return jsons;
	}

}

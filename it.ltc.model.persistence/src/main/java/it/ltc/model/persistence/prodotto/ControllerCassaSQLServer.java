package it.ltc.model.persistence.prodotto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.database.model.legacy.bundle.Casse;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.prodotto.MCassa;
import it.ltc.model.interfaces.prodotto.MProdotto;

public class ControllerCassaSQLServer extends Dao implements IControllerModel<MCassa> {
	
	private static final Logger logger = Logger.getLogger(ControllerCassaSQLServer.class);
	
	protected final ArticoliDao daoArticoli;

	public ControllerCassaSQLServer(String persistenceUnit) {
		super(persistenceUnit);
		daoArticoli = new ArticoliDao(persistenceUnit);
	}

	@Override
	public void valida(MCassa model) throws ModelValidationException {
		//Controllo che il prodotto "cassa" esista e sia una cassa.
		MProdotto prodottoCassa = model.getCassa();
		Articoli articoloCassa;
		switch (model.getTipoIdentificazione()) {
			case BARCODE : articoloCassa = daoArticoli.trovaDaBarcode(prodottoCassa.getBarcode()); break;
			case CHIAVE : articoloCassa = daoArticoli.trovaDaSKU(prodottoCassa.getChiaveCliente()); break;
			case MODELLOTAGLIA : articoloCassa = daoArticoli.trovaDaModelloETaglia(prodottoCassa.getCodiceModello(), prodottoCassa.getTaglia()); break;
			default : articoloCassa = null;
		}
		if (articoloCassa == null)
			throw new ModelValidationException("Cassa non trovata (" + prodottoCassa + ")");
		//Memorizzo i riferimenti legacy trovati nel model prodotto.
		prodottoCassa.setId(articoloCassa.getIdArticolo());
		prodottoCassa.setIdUnivocoLegacy(articoloCassa.getIdUniArticolo());
		prodottoCassa.setCodiceModello(articoloCassa.getModello());
		//Controllo che esistano tutti i prodotti all'interno della cassa e non siano casse a loro volta.
		HashMap<MProdotto, Integer> prodotti = model.getProdotti();
		for (MProdotto prodotto : prodotti.keySet()) {
			Articoli articolo;
			switch (model.getTipoIdentificazione()) {
				case BARCODE : articolo = daoArticoli.trovaDaBarcode(prodotto.getBarcode()); break;
				case CHIAVE : articolo = daoArticoli.trovaDaSKU(prodotto.getChiaveCliente()); break;
				case MODELLOTAGLIA : articolo = daoArticoli.trovaDaModelloETaglia(prodotto.getCodiceModello(), prodotto.getTaglia()); break;
				default : articolo = null;
			}
			if (articolo == null)
				throw new ModelValidationException("Articolo non trovato (" + prodotto + ")");
			Integer quantita = prodotti.get(prodotto);
			if (quantita == null || quantita < 1)
				throw new ModelValidationException("Quantità non inserita o non valida. (" + prodotto + ")");
			//Memorizzo i riferimenti legacy trovati nel model prodotto.
			prodotto.setId(articolo.getIdArticolo());
			prodotto.setIdUnivocoLegacy(articolo.getIdUniArticolo());
			prodotto.setCodiceModello(articolo.getModello());
		}	
	}
	
	private List<Casse> getContenutoCassa(MCassa model) {
		HashMap<MProdotto, Integer> prodotti = model.getProdotti();
		List<Casse> contenutoCassa = new LinkedList<>();
		for (MProdotto prodotto : prodotti.keySet()) {
			Casse elementoCassa = new Casse();
			elementoCassa.setModello(prodotto.getCodiceModello());
			elementoCassa.setTipo(model.getTipoCassa().name());
			elementoCassa.setCodiceCassa(model.getCodiceCassa());
//			elementoCassa.setIdUnivocoCassa(model.getCassa().getIdUnivocoLegacy());
//			elementoCassa.setIdUnivocoProdotto(prodotto.getIdUnivocoLegacy());
			elementoCassa.setIdCassa(model.getCassa().getId());
			elementoCassa.setIdProdotto(prodotto.getId());
			elementoCassa.setQuantitaProdotto(prodotti.get(prodotto));
			contenutoCassa.add(elementoCassa);
		}
		return contenutoCassa;
	}

	@Override
	public MCassa inserisci(MCassa model) throws ModelPersistenceException {
		//Elaboro le informazioni sulla cassa
		List<Casse> contenutoCassa = getContenutoCassa(model);
		// Inserisco
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			for (Casse elementoCassa : contenutoCassa)
				em.persist(elementoCassa);
			t.commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			model = null;
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return model;
	}

	@Override
	public MCassa modifica(MCassa model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MCassa elimina(MCassa model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}

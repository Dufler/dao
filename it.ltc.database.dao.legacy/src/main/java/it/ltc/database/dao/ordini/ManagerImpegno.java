package it.ltc.database.dao.ordini;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.MagaMovDao;
import it.ltc.database.dao.legacy.MagaSdDao;
import it.ltc.database.dao.legacy.RighiOrdineDao;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.database.model.legacy.model.CausaliMovimento;

public class ManagerImpegno extends Dao {
	
	private static final Logger logger = Logger.getLogger("ManagerImpegno");
	
	protected final RighiOrdineDao daoRighe;
	protected final MagaSdDao daoSaldi;
	protected final MagaMovDao daoMovimenti;

	public ManagerImpegno(String persistenceUnit) {
		super(persistenceUnit);
		
		daoRighe = new RighiOrdineDao(persistenceUnit);
		daoSaldi = new MagaSdDao(persistenceUnit);
		daoMovimenti = new MagaMovDao(persistenceUnit);		
	}
	
	protected boolean finalizza(TestataOrdini ordine) {
		// Recupero la lista di prodotti richiesti e controllo la disponibilita'
		List<RighiOrdine> prodotti = daoRighe.trovaRigheDaIDOrdine(ordine.getIdTestaSped());
		logger.info("Verranno ora verificate " + prodotti.size() + " righe.");
		//EntityManager em = getManager();
		boolean finalizza;
		List<String> righeNonValide = new LinkedList<>();
		HashMap<String, MagaSd> saldi = new HashMap<>();
		//List<MagaSd> saldi = new LinkedList<>();
		List<MagaMov> movimenti = new LinkedList<>();
		for (RighiOrdine prodotto : prodotti) {
			String keySaldo = prodotto.getIdUnicoArt() + "-" +  prodotto.getMagazzino();
			if (!saldi.containsKey(keySaldo)) {
				MagaSd saldo = daoSaldi.trovaDaArticoloEMagazzino(prodotto.getIdUnicoArt(), prodotto.getMagazzino());
				saldi.put(keySaldo, saldo);
			}
			MagaSd saldo = saldi.get(keySaldo);
			// Controllo ciò che ho trovato:
			// - se la lista è vuota oppure se non ho disponibilita' aggiungo il numero di riga a quelle non valide e boccio la transazione.
			// - altrimenti aggiorno la disponibilita' e l'impegno e genero il movimento di magazzino.
			if (saldo == null || saldo.getDisponibile() < prodotto.getQtaSpedizione()) {
				String message = "La riga " + prodotto.getNrRigo() + " non e' valida. (SKU: '" + prodotto.getCodiceArticolo() + "')";
				String reason = saldo != null ? "la quantita' disponibile è insufficiente (saldo disponibile: " + saldo.getDisponibile() + ", quantità richiesta: " + prodotto.getQtaSpedizione() + ")" : "Non è disponibile a magazzino.";
				righeNonValide.add(message + " : " +  reason);
				logger.warn(message + reason);
			} else {
				int disponibile = saldo.getDisponibile() - prodotto.getQtaSpedizione();
				int impegnato = saldo.getImpegnato() + prodotto.getQtaSpedizione();
				saldo.setDisponibile(disponibile);
				saldo.setImpegnato(impegnato);
				//saldi.add(saldo);
				//MagaMov movimento = getMovimento(saldo, prodotto);
				MagaMov movimento = daoMovimenti.getNuovoMovimento(CausaliMovimento.IOS, ordine.getNrLista(), ordine.getIdTestaSped(), new Date(), saldo, prodotto.getIdUnicoArt(), prodotto.getMagazzino(), prodotto.getQtaSpedizione());
				movimenti.add(movimento);
			}
		}
		// Se non ho trovato problemi aggiorno tutti i saldi e inserisco i movimenti di magazzino
		// Altrimenti chiudo l'entity manager e restituisco la lista delle righe non valide.
		if (!righeNonValide.isEmpty()) {
			EntityManager em = getManager();
			ordine = em.find(TestataOrdini.class, ordine.getIdTestaSped());
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				ordine.setStato("ERRO");
				em.merge(ordine);
				t.commit();
			} catch (Exception e) {
				logger.error(e);
				if (t != null && t.isActive())
					t.rollback();
			} finally {
				em.close();
			}
			StringBuilder sb = new StringBuilder("Assegnazione fallita!");
			for (String row : righeNonValide) {
				sb.append("\r\n");
				sb.append(row);
			}
			logger.error(sb.toString());
			throw new RuntimeException(sb.toString());
		} else {
			EntityManager em = getManager();
			ordine = em.find(TestataOrdini.class, ordine.getIdTestaSped());
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				ordine.setStato("IMPO");
				em.merge(ordine);
				for (MagaSd saldo : saldi.values()) {
					em.merge(saldo);
				}
				for (MagaMov movimento : movimenti) {
					em.persist(movimento);
				}
				t.commit();
				finalizza = true;
			} catch (Exception e) {
				finalizza = false;
				righeNonValide = null;
				logger.error(e);
				if (t != null && t.isActive())
					t.rollback();
			} finally {
				em.close();
			}
		}
		return finalizza;
	}

}

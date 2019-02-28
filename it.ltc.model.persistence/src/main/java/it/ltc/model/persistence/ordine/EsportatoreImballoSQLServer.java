package it.ltc.model.persistence.ordine;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import it.ltc.database.dao.legacy.ColliImballoDao;
import it.ltc.database.dao.legacy.ImballoDao;
import it.ltc.database.dao.legacy.RighiImballoDao;
import it.ltc.database.dao.legacy.RighiOrdineDao;
import it.ltc.database.dao.legacy.TestataOrdiniDao;
import it.ltc.database.model.legacy.ColliImballo;
import it.ltc.database.model.legacy.RighiImballo;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.TestataOrdini;

public abstract class EsportatoreImballoSQLServer {
	
	private static final Logger logger = Logger.getLogger(EsportatoreImballoSQLServer.class);
	
	protected final TestataOrdiniDao daoOrdini;
	protected final RighiOrdineDao daoRighe;
	protected final RighiImballoDao daoImballi;
	protected final ColliImballoDao daoColli;
	protected final ImballoDao daoFormati;
	
	protected final SimpleDateFormat sdf;
	
	public EsportatoreImballoSQLServer(String persistenceUnit) {
		daoOrdini = new TestataOrdiniDao(persistenceUnit);
		daoRighe = new RighiOrdineDao(persistenceUnit);
		daoImballi = new RighiImballoDao(persistenceUnit);
		daoColli = new ColliImballoDao(persistenceUnit);
		daoFormati = new ImballoDao(persistenceUnit);
		sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	}
	
	/**
	 * Esporta le informazioni sugli ordini imballati.
	 */
	public void esportaOrdiniImballati() {
		//Trovo tutti gli ordini imballati e pronti per essere esportati
		List<TestataOrdini> testate = trovaOrdiniImballati();
		if (!testate.isEmpty())
			logger.info("Sono stati trovati " + testate.size() + " ordini pronti per l'esportazione dati sull'imballo.");
		for (TestataOrdini testata : testate) {
			//Recupero le info collegate all'ordine: righe, imballi e colli.
			List<RighiOrdine> righe = trovaRigheOrdine(testata);
			List<RighiImballo> imballi = trovaRigheImballo(testata);
			List<ColliImballo> colli = trovaColliImballo(testata);
			boolean generazione = esportaOrdine(testata, righe, imballi, colli);
			if (generazione) {
				boolean esportazione = aggiornaStatoOrdine(testata);
				if (esportazione)
					logger.info("Generazione imballo completata per l'ordine ID " + testata.getIdTestaSped());
				else
					logger.error("Impossibile aggiornare lo stato di trasmissione imballo dell'ordine ID " + testata.getIdTestaSped());
			}
		}
	}
	
	/**
	 * Aggiorna lo stato dell'ordine per indicare che le informazioni sull'imballo sono state restituite al cliente.<br>
	 * Come default lo stato dell'ordine passa da ELAB a INSP.
	 */
	protected boolean aggiornaStatoOrdine(TestataOrdini testata) {
		testata.setStato("INSP");
		return daoOrdini.aggiorna(testata) != null;
	}

	/**
	 * Esporta le informazioni sull'imballo dell'ordine per il cliente.<br>
	 * Questo metodo va implementato per ogni cliente.
	 */
	protected abstract boolean esportaOrdine(TestataOrdini testata, List<RighiOrdine> righe, List<RighiImballo> imballi, List<ColliImballo> colli);

	/**
	 * Restituisce tutte le info sui colli d'imballo effettuati.
	 * N.B. Protrebbe non essere necessario per alcuni clienti e può essere sovrascritto per migliorarne l'efficienza.
	 */
	protected List<ColliImballo> trovaColliImballo(TestataOrdini testata) {
		List<ColliImballo> colli = daoColli.trovaDaNumeroLista(testata.getNrLista());
		return colli;
	}

	/**
	 * Restituisce tutte le info sulle righe d'imballo effettuate.
	 * N.B. Protrebbe non essere necessario per alcuni clienti e può essere sovrascritto per migliorarne l'efficienza.
	 */
	protected List<RighiImballo> trovaRigheImballo(TestataOrdini testata) {
		List<RighiImballo> imballi = daoImballi.trovaDaNumeroLista(testata.getNrLista());
		return imballi;
	}

	/**
	 * Restiuisce tutte le info sulle righe dell'ordine.<br>
	 * N.B. Protrebbe non essere necessario per alcuni clienti e può essere sovrascritto per migliorarne l'efficienza.
	 */
	protected List<RighiOrdine> trovaRigheOrdine(TestataOrdini testata) {
		List<RighiOrdine> righe = daoRighe.trovaRigheDaIDOrdine(testata.getIdTestaSped());
		return righe;
	}

	/**
	 * Restituisce tutti gli ordini imballati e pronti per essere esportati.
	 */
	protected List<TestataOrdini> trovaOrdiniImballati() {
		List<TestataOrdini> testate = daoOrdini.trovaDaStato("ELAB");
		return testate;
	}

}

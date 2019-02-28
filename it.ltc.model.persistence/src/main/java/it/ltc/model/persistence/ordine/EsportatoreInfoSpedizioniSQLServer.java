package it.ltc.model.persistence.ordine;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import it.ltc.database.dao.legacy.TestataOrdiniDao;
import it.ltc.database.model.legacy.TestataOrdini;

/**
 * Esporta le info su una spedizione effettata.
 * @author Damiano
 *
 */
public abstract class EsportatoreInfoSpedizioniSQLServer {
	
	private static final Logger logger = Logger.getLogger(EsportatoreInfoSpedizioniSQLServer.class);
	
	protected final TestataOrdiniDao daoOrdini;
	
	protected final SimpleDateFormat sdf;
	
	public EsportatoreInfoSpedizioniSQLServer(String persistenceUnit) {
		daoOrdini = new TestataOrdiniDao(persistenceUnit);
		sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	}
	
	public void esportaInfoSpedizioni() {
		//Trovo tutti gli ordini spediti
		List<TestataOrdini> testate = trovaOrdiniSpediti();
		if (!testate.isEmpty())
			logger.info("Sono stati trovati " + testate.size() + " ordini pronti per l'esportazione dati sulla spedizione.");
		for (TestataOrdini testata : testate) {
			boolean generazione = esportaOrdine(testata);
			if (generazione) {
				boolean esportazione = aggiornaStatoOrdine(testata);
				if (esportazione)
					logger.info("Generazione info spedizioni completata per l'ordine ID " + testata.getIdTestaSped());
				else
					logger.error("Impossibile aggiornare lo stato di trasmissione info spedizioni dell'ordine ID " + testata.getIdTestaSped());
			}
		}
	}
	
	/**
	 * Aggiorna lo stato dell'ordine per indicare che le informazioni sulla spedizione sono state restituite al cliente.<br>
	 * Come default lo stato dell'ordine passa da SPED a FINE.
	 */
	protected boolean aggiornaStatoOrdine(TestataOrdini testata) {
		testata.setStato("FINE");
		return daoOrdini.aggiorna(testata) != null;
	}

	/**
	 * Esporta le informazioni sull'imballo dell'ordine per il cliente.<br>
	 * Questo metodo va implementato per ogni cliente.
	 */
	protected abstract boolean esportaOrdine(TestataOrdini testata);

	/**
	 * Restituisce tutti gli ordini spediti e pronti per essere esportati.<br>
	 * Come default lo stato dell'ordine dovrebbe essere SPED.
	 */
	protected List<TestataOrdini> trovaOrdiniSpediti() {
		List<TestataOrdini> testate = daoOrdini.trovaDaStato("SPED");
		return testate;
	}

}

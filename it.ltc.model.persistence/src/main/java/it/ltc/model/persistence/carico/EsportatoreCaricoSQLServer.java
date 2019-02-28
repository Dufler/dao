package it.ltc.model.persistence.carico;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import it.ltc.database.dao.legacy.PakiArticoloDao;
import it.ltc.database.dao.legacy.PakiTestaDao;
import it.ltc.database.model.legacy.PakiArticolo;
import it.ltc.database.model.legacy.PakiTesta;

/**
 * Classe astratta con utility base per trovare e esportare i carichi pronti.
 * @author Damiano
 *
 */
public abstract class EsportatoreCaricoSQLServer {
	
	private static final Logger logger = Logger.getLogger(EsportatoreCaricoSQLServer.class);
	
	protected final PakiTestaDao daoCarichi;
	protected final PakiArticoloDao daoRighe;
	
	protected final SimpleDateFormat sdf;
	
	public EsportatoreCaricoSQLServer(String persistenceUnit) {
		daoCarichi = new PakiTestaDao(persistenceUnit);
		daoRighe = new PakiArticoloDao(persistenceUnit);
		sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	}
	
	/**
	 * Esporta tutti i carichi pronti e ne aggiorna lo stato se l'esportazione va a buon fine.
	 */
	public void esportaCarichi() {
		//Trovo tutti i carichi pronti per essere esportati
		List<PakiTesta> carichi = trovaCarichiPronti();
		if (!carichi.isEmpty())
			logger.info("Sono stati trovati " + carichi.size() + " carichi pronti per l'esportazione dati.");
		//Recupero il contenuto per ognuno di questi e elaboro i file di riga
		for (PakiTesta carico : carichi) {
			List<PakiArticolo> righe = daoRighe.trovaRigheDaCarico(carico.getIdTestaPaki());
			//creo i files e li deposito nell'area FTP.
			boolean generazione = esportaCarico(carico, righe);
			if (generazione) {
				boolean esportazione = aggiornaStatoCarico(carico);
				if (esportazione)
					logger.info("Generazione completata per il carico ID " + carico.getIdTestaPaki());
				else
					logger.error("Impossibile aggiornare lo stato di trasmissione del carico ID " + carico.getIdTestaPaki());
			} else {
				logger.error("Impossibile generare il file di trasmissione del carico ID " + carico.getIdTestaPaki());
			}
		}		
	}
	
	/**
	 * Trova tutti i carichi pronti da esportare.<br>
	 * Le condizioni usualmente usate sono lo stato (stato = CHIUSO), la generazione dei movimenti di magazzino (generatoMov = SI) e lo stato di trasmissione al cliente del riscontro (flagtra = F)
	 * @return una lista di carichi pronti da esportare.
	 */
	protected List<PakiTesta> trovaCarichiPronti() {
		return daoCarichi.trovaCarichiChiusiDaEsportare();
	}
	
	/**
	 * Aggiorna lo stato del carico specificato in maniera da indicare che è stato trasmesso al cliente.
	 * @param carico il carico appena esportato e da aggiornare.
	 * @return l'esisto dell'operazione.
	 */
	protected boolean aggiornaStatoCarico(PakiTesta carico) {
		carico.setFlagTra("T");
		return daoCarichi.aggiorna(carico) != null;
	}
	
	/**
	 * Metodo da implementare che potrebbe produrre un output di qualche genere e ne restituisce l'esito.
	 * @param carico
	 * @param righe
	 * @return
	 */
	protected abstract boolean esportaCarico(PakiTesta carico, List<PakiArticolo> righe);

}

package it.ltc.model.persistence.saldi;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import it.ltc.database.dao.legacy.ArticoliDao;
import it.ltc.database.dao.legacy.MagaMovDao;
import it.ltc.database.dao.legacy.MagaSdDao;
import it.ltc.database.dao.legacy.MagazzinoDao;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;

/**
 * Classe che si occupa di esportare i saldi e i movimenti di magazzino.
 * @author Damiano
 *
 */
public abstract class EsportatoreSaldiMovimentiSQLServer {
	
	private static final Logger logger = Logger.getLogger(EsportatoreSaldiMovimentiSQLServer.class);
	
	protected final MagaSdDao daoSaldi;
	protected final MagaMovDao daoMovimenti;
	protected final ArticoliDao daoArticoli;
	protected final MagazzinoDao daoMagazzini;
	
	protected final SimpleDateFormat sdf;
	
	public EsportatoreSaldiMovimentiSQLServer(String persistenceUnit) {
		daoSaldi = new MagaSdDao(persistenceUnit);
		daoMovimenti = new MagaMovDao(persistenceUnit);
		daoArticoli = new ArticoliDao(persistenceUnit);
		daoMagazzini = new MagazzinoDao(persistenceUnit);
		sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	}
	
	public void esportaSaldi() {
		//Recupero i saldi
		List<MagaSd> saldi = trovaSaldi();
		//Li esporto
		boolean generazione = esportaSaldi(saldi);
		if (generazione)
			logger.info("Generazione completata per i saldi.");
		else
			logger.error("Impossibile esportare i saldi.");
	}
	
	/**
	 * Restituisce tutti i saldi esistenti per ogni prodotto in ogni magazzino.
	 */
	protected List<MagaSd> trovaSaldi() {
		List<MagaSd> saldi = daoSaldi.trovaTutti();
		return saldi;
	}
	
	/**
	 * Esporta i saldi.
	 */
	protected abstract boolean esportaSaldi(List<MagaSd> saldi);
	
	public void esportaMovimenti() {
		//Recupero i movimenti da esportare
		List<MagaMov> movimenti = trovaMovimenti();
		//Li esporto
		boolean generazione = esportaMovimenti(movimenti);
		if (generazione) {
			boolean esportazione = aggiornaStatoMovimenti(movimenti);
			if (esportazione)
				logger.info("Generazione completata per i movimenti.");
			else
				logger.error("Impossibile aggiornare lo stato dei movimenti trasmessi.");
		} else {
			logger.error("Impossibile esportare i movimenti.");
		}
	}
	
	protected List<MagaMov> trovaMovimenti() {
		List<MagaMov> movimenti = daoMovimenti.trovaMovimentiNonComunicati();
		return movimenti;
	}
	
	protected abstract boolean esportaMovimenti(List<MagaMov> movimenti);
	
	protected boolean aggiornaStatoMovimenti(List<MagaMov> movimenti) {
		boolean update = daoMovimenti.setStatoTrasmissione(movimenti, true);
		return update;
	}

}

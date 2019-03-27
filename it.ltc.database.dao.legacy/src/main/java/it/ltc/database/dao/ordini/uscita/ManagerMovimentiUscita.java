package it.ltc.database.dao.ordini.uscita;

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
import it.ltc.database.dao.legacy.RighiImballoDao;
import it.ltc.database.dao.legacy.RighiOrdineDao;
import it.ltc.database.model.legacy.MagaMov;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.RighiImballo;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.database.model.legacy.model.CausaliMovimento;

public class ManagerMovimentiUscita extends Dao {
	
	private static final Logger logger = Logger.getLogger(ManagerMovimentiUscita.class);
	
	protected final RighiOrdineDao daoRighe;
	protected final RighiImballoDao daoImballo;
	protected final MagaSdDao daoSaldi;
	protected final MagaMovDao daoMovimenti;

	public ManagerMovimentiUscita(String persistenceUnit) {
		super(persistenceUnit);
		daoRighe = new RighiOrdineDao(persistenceUnit);
		daoImballo = new RighiImballoDao(persistenceUnit);
		daoSaldi = new MagaSdDao(persistenceUnit);
		daoMovimenti = new MagaMovDao(persistenceUnit);	
	}
	
	public RisultatoGenerazioneMovimentiUscita elaboraMovimentiUscita(TestataOrdini testata) {
		RisultatoGenerazioneMovimentiUscita risultato = new RisultatoGenerazioneMovimentiUscita();
		//check sull'ordine
		checkOrdine(risultato, testata);
		//recupero le righe d'ordine e l'imballo
		List<RighiOrdine> righe = daoRighe.trovaRigheDaIDOrdine(testata.getIdTestaSped());
		logger.info("Sono stati trovate " + righe.size() + " righe d'ordine.");
		List<RighiImballo> imballi = daoImballo.trovaDaNumeroLista(testata.getNrLista());
		logger.info("Sono stati trovate " + imballi.size() + " righe d'imballo.");
		//Ne faccio una mappa per prodotto-quantità per l'ordinato e l'imballato
		HashMap<String, Integer> mappaRigheOrdinato = new HashMap<>();
		HashMap<String, Integer> mappaRigheImballato = new HashMap<>();
		for (RighiOrdine riga : righe) {
			String key = riga.getIdUnicoArt(); // + riga.getMagazzino(); //FIXME - Un giorno bisognerà prendere in considerazione anche il magazzino per evitare casini con ordini su magazzini multipli.
			//Ordinato
			int quantitàOrdinato = mappaRigheOrdinato.containsKey(key) ? mappaRigheOrdinato.get(key) : 0;
			quantitàOrdinato += riga.getQtaSpedizione(); 
			mappaRigheOrdinato.put(key, quantitàOrdinato);
			//Imballato
			int quantitàImballato = mappaRigheImballato.containsKey(key) ? mappaRigheImballato.get(key) : 0;
			quantitàImballato += riga.getQtaImballata();
			mappaRigheImballato.put(key, quantitàImballato);
		}
		//Ne faccio una mappa per prodotto-quantità dell'imballato effettivo
		HashMap<String, Integer> mappaImballi = new HashMap<>();
		for (RighiImballo imballo : imballi) {
			String key = imballo.getIdUniArticolo(); // + riga.getMagazzino(); //FIXME - Un giorno bisognerà prendere in considerazione anche il magazzino per evitare casini con ordini su magazzini multipli.
			int quantitàImballato = mappaImballi.containsKey(key) ? mappaImballi.get(key) : 0;
			quantitàImballato += imballo.getQtaImballata();
			mappaImballi.put(key, quantitàImballato);
		}
		logger.info("Mappaggio completato.");
		logger.info("Mappa righe ordinate: " + mappaRigheOrdinato.size() + " elementi");
		logger.info("Mappa righe imballate: " + mappaRigheImballato.size() + " elementi");
		logger.info("Mappa imballi: " + mappaImballi.size() + " elementi");
		//eseguo un controllo per verificare che l'imballato sia minore o uguale all'ordinato.
		checkImballatoMinoreUgualeOrdinato(risultato, mappaRigheOrdinato, mappaRigheImballato);
		//eseguo un controllo per verificare che le quantità imballate siano congruenti tra righiimballo e righiordine.
		checkImballatoCorrisponde(risultato, mappaRigheImballato, mappaImballi);
		//Se non si sono verificati problemi vado ad elaborare i movimenti e aggiorno l'ordine
		if (risultato.getProblemi().isEmpty())
			elaboraMovimenti(risultato, testata, righe);
		return risultato;
	}
	
	protected void checkOrdine(RisultatoGenerazioneMovimentiUscita risultato, TestataOrdini testata) {
		if (!testata.getGenMovUscita().equalsIgnoreCase("NO"))
			risultato.addProblema("I movimenti di uscita sono stati già elaborati.");
		if (!testata.getStato().equalsIgnoreCase("DIIB") && !testata.getStato().equalsIgnoreCase("COIB"))
			risultato.addProblema("Lo stato dell'ordine non è valido per poter generare i movimenti di uscita. (Stato: " + testata.getStato() + ")");
	}
	
	protected void checkImballatoMinoreUgualeOrdinato(RisultatoGenerazioneMovimentiUscita risultato, HashMap<String, Integer> mappaRigheOrdinato, HashMap<String, Integer> mappaRigheImballato) {
		if (mappaRigheImballato.size() > mappaRigheOrdinato.size())
			risultato.addProblema("Le quantità imballate dichiarate e quelle ordinate sono diverse.");
		for (String key : mappaRigheOrdinato.keySet()) {
			int quantitàOrdinata = mappaRigheOrdinato.get(key);
			int quantitàImballata = mappaRigheImballato.containsKey(key) ? mappaRigheImballato.get(key) : 0;
			if (quantitàImballata > quantitàOrdinata)
				risultato.addProblema("La quantità imballata è superiore alla quantità ordinata. (articolo: " + key + ")");
		}
	}
	
	protected void checkImballatoCorrisponde(RisultatoGenerazioneMovimentiUscita risultato, HashMap<String, Integer> mappaRigheImballato, HashMap<String, Integer> mappaImballi) {
		if (mappaRigheImballato.size() != mappaImballi.size())
			risultato.addProblema("Le quantità imballate dichiarate e quelle effettivamente imballate sono diverse.");
		for (String key : mappaRigheImballato.keySet()) {
			int quantitàImballata = mappaRigheImballato.get(key);
			int quantitàEffettivamenteImballata = mappaImballi.containsKey(key) ? mappaImballi.get(key) : 0;
			if (quantitàImballata != quantitàEffettivamenteImballata)
				risultato.addProblema("La quantità imballata e quella effettivamente imballata sono diverse. (articolo: " + key + ")");
		}
		logger.info("Controllo quantità imballate rispetto all'effettivo imballato completato con successo");
	}
	
	protected void elaboraMovimenti(RisultatoGenerazioneMovimentiUscita risultato, TestataOrdini testata, List<RighiOrdine> righe) {
		HashMap<String, MagaSd> saldi = new HashMap<>();
		List<MagaMov> movimenti = new LinkedList<>();
		for (RighiOrdine riga : righe) {
			//recupero le quantità necessarie ad elaborare il nuovo saldo e il movimento
			int quantitàOrdinata = riga.getQtaSpedizione();
			int quantitàImballata = riga.getQtaImballata();
			int quantitàDaRettificare = quantitàOrdinata - quantitàImballata;
			//recupero il saldo
			String keySaldo = riga.getIdUnicoArt() + "-" +  riga.getMagazzino();
			if (!saldi.containsKey(keySaldo)) {
				MagaSd saldo = daoSaldi.trovaDaArticoloEMagazzino(riga.getIdUnicoArt(), riga.getMagazzino());
				if (saldo == null)
					risultato.addProblema("Non esiste un saldo di magazzino. (articolo: " + riga.getIdUnicoArt() + ", magazzino: " + riga.getMagazzino() + ")");
				saldi.put(keySaldo, saldo);
			}
			MagaSd saldo = saldi.get(keySaldo);
			saldo.setImpegnato(saldo.getImpegnato() - quantitàImballata);
			saldo.setEsistenza(saldo.getEsistenza() - quantitàImballata);
			saldo.setTotOut(saldo.getTotOut() + quantitàImballata);
			//elaboro i movimenti
			MagaMov movimentoUscita = daoMovimenti.getNuovoMovimento(CausaliMovimento.IBO, testata.getNrLista(), testata.getIdTestaSped(), new Date(), saldo, riga.getIdUnicoArt(), riga.getMagazzino(), quantitàImballata);
			movimenti.add(movimentoUscita);
			if (quantitàDaRettificare > 0) {
				MagaMov movimentoRettifica = daoMovimenti.getNuovoMovimento(CausaliMovimento.REL, testata.getNrLista(), testata.getIdTestaSped(), new Date(), saldo, riga.getIdUnicoArt(), riga.getMagazzino(), quantitàDaRettificare);
				movimenti.add(movimentoRettifica);
				logger.info("Aggiunto movimento di rettifica a fronte di un imballo non presente.");
			}
			logger.info("Riga ID " + riga.getIdRigoOrdine() + " ha generato il movimento " + movimentoUscita.toString() + " e ha modificato il saldo " + saldo.toString());
		}
		//vado in scrittura sul DB se non sono presenti errori.
		if (risultato.getProblemi().isEmpty()) {
			Date dataGenerazione = new Date();
			EntityManager em = getManager();
			TestataOrdini ordine = em.find(TestataOrdini.class, testata.getIdTestaSped());
			EntityTransaction t = em.getTransaction();
			try {
				t.begin();
				//Testata
				ordine.setStato("ELAB");
				ordine.setGenMovUscita("SI");
				ordine.setDataGeneraUscita(dataGenerazione);
				em.merge(ordine);
				//Righe
				for (RighiOrdine riga : righe) {
					riga.setDatagenmov(dataGenerazione);
					em.merge(riga);
				}
				for (MagaSd saldo : saldi.values()) {
					em.merge(saldo);
				}
				for (MagaMov movimento : movimenti) {
					em.persist(movimento);
				}
				t.commit();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				risultato.addProblema(e.getMessage());
				if (t != null && t.isActive())
					t.rollback();
			} finally {
				em.close();
			}
		}
	}
	
}

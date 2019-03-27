package it.ltc.database.dao.ordini;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ColliCaricoDao;
import it.ltc.database.dao.legacy.ColliPackDao;
import it.ltc.database.dao.legacy.MagaSdDao;
import it.ltc.database.dao.legacy.RighiOrdineDao;
import it.ltc.database.dao.legacy.RighiUbicPreDao;
import it.ltc.database.dao.legacy.UbicazioniDao;
import it.ltc.database.dao.ordini.AssegnazioneProdotto.TipoAssegnazione;
import it.ltc.database.model.legacy.ColliCarico;
import it.ltc.database.model.legacy.ColliPack;
import it.ltc.database.model.legacy.MagaSd;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.Righiubicpre;
import it.ltc.database.model.legacy.Scorte;
import it.ltc.database.model.legacy.Scorte2;
import it.ltc.database.model.legacy.ScorteColli;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.database.model.legacy.Ubicazioni;

public class ManagerAssegnazione extends Dao {
	
	public enum StatoAssegnazioneRiga { PRELIEVO, SCORTA, NONUBICATO, NONPRESENTE, LEGACY }
	
	private static final Logger logger = Logger.getLogger(ManagerAssegnazione.class);
	
	private final SimpleDateFormat sdf;
	
	protected final ColliPackDao daoProdotti;
	protected final RighiOrdineDao daoRigheOrdine;
	protected final ColliCaricoDao daoColli;
	protected final UbicazioniDao daoUbicazioni;
	protected final RighiUbicPreDao daoAssegnazioni;
	protected final MagaSdDao daoSaldi;
	
	public ManagerAssegnazione(String persistenceUnit) {
		super(persistenceUnit);
		sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		daoProdotti = new ColliPackDao(persistenceUnit);
		daoRigheOrdine = new RighiOrdineDao(persistenceUnit);
		daoColli = new ColliCaricoDao(persistenceUnit);
		daoUbicazioni = new UbicazioniDao(persistenceUnit);
		daoAssegnazioni = new RighiUbicPreDao(persistenceUnit);
		daoSaldi = new MagaSdDao(persistenceUnit);
	}
	
	/**
	 * Prepara l'assegnazione dell'ordine passato come argomento restituendo una lista di oggetti d'assegnazione, uno per ogni riga.
	 * @param testata
	 * @return
	 */
	public AssegnazioneOrdine preparaAssegnazioneOrdine(TestataOrdini testata) {
		//Genero la stringa che identifica la sessione di lavoro
		String sessioneLavoro = sdf.format(new Date());
		testata.setSessioneLavoro(sessioneLavoro);
		logger.info("Preparo l'assegnazione per la lista " + testata.getNrLista() + ", sessione di lavoro: " + sessioneLavoro);
		AssegnazioneOrdine assegnazioneOrdine = new AssegnazioneOrdine(testata);
		eliminaRighiUbiPrePrecedenti(testata);
		//List<AssegnazioneProdotto> listaAssegnazione = new LinkedList<>();
		//Trovo le righe d'ordine da assegnare.
		List<RighiOrdine> righeDaAssegnare = daoRigheOrdine.trovaRigheDaAssegnare(testata.getIdTestaSped());
		logger.info("Sono state trovate " + righeDaAssegnare.size() + " righe da assegnare.");
		for (RighiOrdine riga : righeDaAssegnare) {
			logger.info("Tento l'assegnazione della riga " + riga.getNrRigo() + " con il prodotto " + riga.getCodiceArticolo() + ", unità richieste: " + riga.getQtadaubicare());
			//Creo il contenitore delle informazioni di assegnazione.
			AssegnazioneProdotto assegnazione = new AssegnazioneProdotto(riga);
			assegnazioneOrdine.aggiungiAssegnazione(assegnazione);
			//Controllo i colli pack esistenti dove posso trovare il prodotto.
			List<ColliPack> prodotti = daoProdotti.trovaProdottiDisponibiliInMagazzino(riga.getIdUnicoArt(), riga.getMagazzino());
			logger.info("Sono stati trovati " + prodotti.size() + " possibili ubicazioni per il prodotto ID " + riga.getIdUnicoArt() + " nel magazzino " + riga.getMagazzino());
			//Per ogni prodotto vado a controllare l'ubicazione.
			//Eseguo più giri, all'inizio prendo solo quelli che sono ubicati a prelievo, do priorità a quelli.
			//1) se il collo è ubicato a prelievo lo aggiungo alla assegnazione senza problemi.
			//2) se il collo è ubicato a scorta lo aggiungo alla lista di quelli ubicati a scorta e lo userò se non trovo di meglio.
			//3) se il collo non è ubicato lo aggiungo alla lista di quelli senza ubicazione e andrà a finire nella lista di quelli senza ubicazione.
			logger.info("Inizio valutando le ubicazioni a prelievo.");
			assegnaSuColliAPrelievo(prodotti, assegnazione, testata, riga);
			//Dopo aver assegnato sui prodotti a prelievo controllo se ne manca ancora un po.
			int quantitaAncoraNecessaria = riga.getQtadaubicare(); 
			//Controllo se è necessario ancora del prodotto per soddisfare la riga, provo a prenderlo dai colli a scorta.
			if (quantitaAncoraNecessaria > 0) {
				logger.info("Sono necessarie ancora " + quantitaAncoraNecessaria + " unità. Procedo con le ubicazioni a scorta.");
				quantitaAncoraNecessaria = assegnaSuColliAScorta(prodotti, assegnazione, testata, riga, sessioneLavoro, quantitaAncoraNecessaria);
			}
			//Controllo se è necessario ancora del prodotto per soddisfare la riga, provo a prenderlo dai colli non ubicati.
			if (quantitaAncoraNecessaria > 0) {
				logger.info("Sono necessarie ancora " + quantitaAncoraNecessaria + " unità. Procedo sul prodotto non ubicato.");
				quantitaAncoraNecessaria = assegnaSuColliNonUbicati(prodotti, assegnazione, testata, riga, sessioneLavoro, quantitaAncoraNecessaria);
			}
			//Se è necessario ancora del prodotto significa che non basta. Controllo la giacenza di magazzino e segnalo l'anomalia
			if (quantitaAncoraNecessaria > 0) {
				logger.info("Sono necessarie ancora " + quantitaAncoraNecessaria + " unità ma non c'è prodotto disponibile, segnalo l'anomalia.");
				segnalaProdottoNonPresente(assegnazione, testata, riga, sessioneLavoro);
			}
		}
		//Aggiorno i campi necessari sulla testata
		aggiornaValoriTestata(testata);
		return assegnazioneOrdine;
	}
	
	/**
	 * Elimina i righiubicpre (a SCORTA o NONUBICATO) generati da una precedente assegnazione sulla lista specificata.
	 */
	protected void eliminaRighiUbiPrePrecedenti(TestataOrdini testata) {
		List<Righiubicpre> righe = daoAssegnazioni.trovaDaLista(testata.getNrLista());
		for (Righiubicpre riga : righe) {
			StatoAssegnazioneRiga tipo;
			try {
				tipo = StatoAssegnazioneRiga.valueOf(riga.getTipoAssegnazione());
			} catch (Exception e) {
				tipo = null;
			}
			//Vanno lasciate solo le righe di prelievo
			if (tipo != null && !(tipo == StatoAssegnazioneRiga.PRELIEVO || tipo == StatoAssegnazioneRiga.LEGACY)) { 
				logger.info("Elimino la riga d'assegnazione precedente ID " + riga.getIdubica());
				Righiubicpre eliminato = daoAssegnazioni.elimina(riga);
				if (eliminato == null)
					logger.error("Impossibile eliminare la riga d'assegnazione ID " + riga.getIdubica());
			}
		}		
	}

	protected Ubicazioni generaUbicazioneFittizia() {
		Ubicazioni ubicazione = new Ubicazioni();
		ubicazione.setIdUbicazioni(0);
		ubicazione.setTipoUbica("SC");
		ubicazione.setArea("  ");
		ubicazione.setBox("  ");
		ubicazione.setCorsia("   ");
		ubicazione.setMagazzino("   ");
		ubicazione.setPiano("  ");
		ubicazione.setScaffale("   ");
		ubicazione.setKeyUbica("               ");
		return ubicazione;
	}
	
	protected Righiubicpre generaRigaPrelievo(ColliPack prodotto, ColliCarico collo, RighiOrdine riga, Ubicazioni ubicazione, TestataOrdini testata, int quantità, StatoAssegnazioneRiga tipoAssegnazione) {
		Righiubicpre rigaUbicazione = new Righiubicpre();
		rigaUbicazione.setIDcollipack(prodotto.getIdColliPack());
		rigaUbicazione.setIdArticolo(riga.getIdArticolo());
		rigaUbicazione.setIduniarticolo(prodotto.getCodiceArticolo());
		rigaUbicazione.setNrcollo(collo.getKeyColloCar());
		rigaUbicazione.setIdRigoOrdine(riga.getIdRigoOrdine());		
		rigaUbicazione.setMagazzino(riga.getMagazzino());		
		rigaUbicazione.setNrlista(riga.getNrLista());
		rigaUbicazione.setRaggliste(riga.getNrLista());
		rigaUbicazione.setQuantita(quantità);		
		rigaUbicazione.setSessioneLavoro(testata.getSessioneLavoro());
		rigaUbicazione.setIdTestataOrdine(testata.getIdTestaSped());
		rigaUbicazione.setTipoUbicazione(ubicazione.getTipoUbica());
		rigaUbicazione.setTipoAssegnazione(tipoAssegnazione.name());
		copiaUbicazione(rigaUbicazione, ubicazione);
		return rigaUbicazione;
	}
	
	protected Scorte generaScorta(ColliPack prodotto, RighiOrdine riga, Ubicazioni ubicazione, TestataOrdini testata, int quantitàRichiesta, int quantitàDisponibile) {
		Scorte scorta = new Scorte();
		scorta.setIduniarticolo(prodotto.getCodiceArticolo());
		scorta.setModello(prodotto.getCodiceArticolo());
		scorta.setMagazzino(riga.getMagazzino());
		scorta.setQtascorta(quantitàDisponibile);
		scorta.setQuantita(quantitàRichiesta);
		scorta.setSessioneLavoro(testata.getSessioneLavoro());
		scorta.setNote("QUANTITA' DA UBICARE: ");
		if (ubicazione != null)
			copiaUbicazione(scorta, ubicazione);
		return scorta;
	}
	
	protected Scorte2 generaScortaNonUbicata(ColliPack prodotto, ColliCarico collo, String sessioneLavoro, int quantitàDisponibile) {
		Scorte2 scortaNonUbicata = new Scorte2();
		scortaNonUbicata.setCollo(collo.getKeyColloCar());
		scortaNonUbicata.setIduniarticolo(prodotto.getCodiceArticolo());
		scortaNonUbicata.setQuantita(quantitàDisponibile);
		scortaNonUbicata.setSessioneLavoro(sessioneLavoro);
		return scortaNonUbicata;
	}
	
	protected ScorteColli generaColloScorta(ColliPack prodotto, ColliCarico collo, Ubicazioni ubicazione, String sessioneLavoro, int quantitàDisponibile) {
		ScorteColli colloScorta = new ScorteColli();
		colloScorta.setIduniarticolo(prodotto.getCodiceArticolo());
		colloScorta.setKeyColloCar(collo.getKeyColloCar());
		colloScorta.setSessioneLavoro(sessioneLavoro);
		colloScorta.setPezzi(quantitàDisponibile); //TODO - Verificare che vada bene questo valore.
		colloScorta.setUbicazione(ubicazione != null ? ubicazione.getKeyUbica() : null);
		return colloScorta;
	}
	
	protected void aggiornaValoriTestata(TestataOrdini testata) {
		double quantitàTotaleOrdinata = testata.getQtaTotaleSpedire();
		double quantitàTotaleAssegnata = testata.getQtaAssegnata();
		double percentualeAssegnazione = (quantitàTotaleAssegnata / quantitàTotaleOrdinata) * 100;
		testata.setPercAssegnata(percentualeAssegnazione);
		if (quantitàTotaleAssegnata < quantitàTotaleOrdinata) {
			testata.setStatoUbicazione("UP");
			testata.setFlag1("X");
			testata.setStampato("NO");
		} else {
			testata.setStatoUbicazione("UT");
			testata.setFlag1(" ");
			testata.setStato("ASSE");
			testata.setStampato("SI");
		}
	}
	
	protected void assegnaSuColliAPrelievo(List<ColliPack> prodotti, AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga) {
		Iterator<ColliPack> iteratorPrelievo = prodotti.iterator();
		while (iteratorPrelievo.hasNext()) {
			ColliPack prodotto = iteratorPrelievo.next();
			//Assegno la specifica riga sul determinato collipack, se il collipack finisce lo elimino dalla lista.
			boolean esaurito = assegnaSuColliAPrelievo(prodotto, assegnazione, testata, riga);
			if (esaurito)
				iteratorPrelievo.remove();
			//Se la quantità richiesta è scesa a 0 procedo con la prossima riga.
			if (riga.getQtadaubicare() == 0) {
				logger.info("La quantità da ubicare è 0, significa che per questa riga d'ordine ho finito.");
				break;
			} else if (riga.getQtadaubicare() < 0) {
				logger.error("La quantità da ubicare è scesa sotto 0.");
				break;
			}
		}
	}
	
	protected boolean assegnaSuColliAPrelievo(ColliPack prodotto, AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga) {
		boolean esaurito = false;
		logger.info("Esamino il collipack ID " + prodotto.getIdColliPack());
		// Trovo il collo e l'ubicazione corrispondente.
		ColliCarico collo = trovoColloProdotto(prodotto);
		Ubicazioni ubicazione = trovaUbicazioneCollo(collo);
		// Condizioni: Collo ubicato e Ubicazione a prelievo.
		if (collo != null && collo.getUbicato().equals("SI") && ubicazione != null && ubicazione.getTipoUbica().equals("PR")) {
			logger.info("Il collipack è ubicato su un punto a prelievo quindi procedo.");
			// Trovo quanti pezzi ci sono e li scalo dalla riga e dal collipack.
			int quantitàGiàAssegnata = testata.getQtaAssegnata();
			int quantitàRigaGiàAssegnata = riga.getQtaAssegnata();
			int quantitàRichiesta = riga.getQtadaubicare();
			int quantitàGiàImpegnata = prodotto.getQtaimpegnata();
			int quantitàOriginale = prodotto.getQta();
			int quantitàDisponibile = quantitàOriginale - quantitàGiàImpegnata;
			int quantitàEffettivamentePresa;
			// Se basta questa aggiorno gli oggetti e termino l'assegnazione della riga
			// altrimenti scalo quello che è possibile scalare.
			logger.info("Valuto le quantità: quantità richiesta: " + quantitàRichiesta + ", quantità disponibile: "	+ quantitàDisponibile);
			if (quantitàRichiesta < quantitàDisponibile) {
				// Aggiorno testata, riga e prodotto disponibile
				logger.info("Mi basta questo collipack.");
				testata.setQtaAssegnata(quantitàGiàAssegnata + quantitàRichiesta);
				riga.setQtadaubicare(0);
				riga.setQtaAssegnata(riga.getQtaAssegnata() + quantitàRichiesta);
				prodotto.setQtaimpegnata(quantitàGiàImpegnata + quantitàRichiesta);
				prodotto.setListaimp(testata.getNrLista());
				quantitàEffettivamentePresa = quantitàRichiesta;
			} else if (quantitàRichiesta == quantitàDisponibile) {
				logger.info("Uso tutto questo collipack.");
				testata.setQtaAssegnata(quantitàGiàAssegnata + quantitàRichiesta);
				riga.setQtadaubicare(0);
				riga.setQtaAssegnata(riga.getQtaAssegnata() + quantitàRichiesta);
				prodotto.setQtaimpegnata(quantitàGiàImpegnata + quantitàRichiesta);
				prodotto.setFlagimp("S");
				prodotto.setListaimp(testata.getNrLista());
				quantitàEffettivamentePresa = quantitàRichiesta;
			} else {
				// Aggiorno testata, riga e prodotto disponibile
				logger.info("Non mi basta questo collipack ma lo prendo comunque e lo rendo non disponibile.");
				testata.setQtaAssegnata(quantitàGiàAssegnata + quantitàDisponibile);
				riga.setQtadaubicare(quantitàRichiesta - quantitàDisponibile);
				riga.setQtaAssegnata(quantitàRigaGiàAssegnata + quantitàDisponibile);
				prodotto.setQtaimpegnata(quantitàGiàImpegnata + quantitàDisponibile);
				prodotto.setFlagimp("S");
				prodotto.setListaimp(testata.getNrLista());
				quantitàEffettivamentePresa = quantitàDisponibile;
			}
			// Creo la riga d'ubicazione per prelievo
			Righiubicpre rigaUbicazione = generaRigaPrelievo(prodotto, collo, riga, ubicazione, testata, quantitàEffettivamentePresa, StatoAssegnazioneRiga.PRELIEVO);
			// Aggiungo le info all'assegnazione.
			assegnazione.aggiungiRigaUbicazione(rigaUbicazione);
			assegnazione.aggiungiProdotto(prodotto);
			assegnazione.aggiungiColli(collo);
			assegnazione.aggiungiUbicazione(ubicazione);
			assegnazione.setTipo(TipoAssegnazione.NORMALE);
			// Copio le informazioni di ubicazione sulla riga, eventualmente sovrascrivendo.
			riga.setNrcollo(collo.getKeyColloCar());
			copiaUbicazione(riga, ubicazione);
			// Rimuovo l'elemento corrente dalle successive iterazioni della lista se non è
			// più disponibile.
			if (prodotto.getFlagimp().equals("S")) {
				logger.info("Rimuovo il collipack dalla lista dato che non è più disponibile.");
				// iteratorPrelievo.remove();
				esaurito = true;
			}
		} else {
			String tipoUbicazione = ubicazione != null ? ubicazione.getTipoUbica() : "NESSUNA";
			logger.info("Il collipack non è valido, passo al successivo. Ubicato: " + collo.getUbicato() + ", tipo: " + tipoUbicazione);
		}
		return esaurito;
	}
	
	protected int assegnaSuColliAScorta(List<ColliPack> prodotti, AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga, String sessioneLavoro, int quantitàRichiesta) {
		Iterator<ColliPack> iteratorScorte = prodotti.iterator();
		while (iteratorScorte.hasNext()) {
			ColliPack prodotto = iteratorScorte.next();
			logger.info("Esamino il collipack ID " + prodotto.getIdColliPack());
			//int quantitàRichiesta = riga.getQtadaubicare(); //Mi viene passata da fuori in modo che possa decrementarla e restituirla.
			//Trovo il collo e l'ubicazione corrispondente.
			ColliCarico collo = trovoColloProdotto(prodotto);
			Ubicazioni ubicazione = trovaUbicazioneCollo(collo);
			//Condizioni: Collo ubicato e Ubicazione a scorta.
			if (collo != null && collo.getUbicato().equals("SI") && ubicazione != null && ubicazione.getTipoUbica().equals("SC")) {
				logger.info("Il collipack è ubicato su un punto a scorta quindi procedo.");
				//Trovo quanti pezzi ci sono e vedo se bastano a coprire quelli richiesti
				int quantitàGiàImpegnata = prodotto.getQtaimpegnata();
				int quantitàOriginale = prodotto.getQta();
				int quantitàDisponibile = quantitàOriginale - quantitàGiàImpegnata;
				logger.info("Valuto le quantità: quantità richiesta: " + quantitàRichiesta + ", quantità disponibile: " + quantitàDisponibile);
				//FIXME - Forse qua non va bene, se la scorta non basta rispetto alla quantità richiesta cosa succede?
				Scorte scorta = generaScorta(prodotto, riga, ubicazione, testata, quantitàRichiesta, quantitàDisponibile);
				ScorteColli colloScorta = generaColloScorta(prodotto, collo, ubicazione, sessioneLavoro, quantitàDisponibile);
				//Creo la riga d'ubicazione per prelievo
				Righiubicpre rigaUbicazione = generaRigaPrelievo(prodotto, collo, riga, ubicazione, testata, quantitàRichiesta, StatoAssegnazioneRiga.SCORTA);
				//Aggiungo all'oggetto assegnazione gli oggetti scorta e scortacollo.
				assegnazione.aggiungiRigaUbicazione(rigaUbicazione);
				assegnazione.aggiungiScortaUbicata(scorta);
				assegnazione.aggiungiColloConScorte(colloScorta);
				assegnazione.setTipo(TipoAssegnazione.SCORTE);
				//Rimuovo l'elemento corrente dalle successive iterazioni della lista.
				iteratorScorte.remove();
				//Scalo la quantità richiesta di quella che è disponibile nella scorta.
				quantitàRichiesta -= quantitàDisponibile;
			} else {
				String tipoUbicazione = ubicazione != null ? ubicazione.getTipoUbica() : "NESSUNA";
				logger.info("Il collipack non è valido, passo al successivo. Ubicato: " + collo.getUbicato() + ", tipo: " + tipoUbicazione);
			}
			//Se la quantità richiesta è scesa a 0 procedo con la prossima riga.
			if (quantitàRichiesta == 0) {
				break;
			} else if (quantitàRichiesta < 0) {
				logger.error("La quantità da ubicare è scesa sotto 0.");
				break;
			}
		}
		return quantitàRichiesta;
	}
	
	protected int assegnaSuColliNonUbicati(List<ColliPack> prodotti, AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga, String sessioneLavoro, int quantitàRichiesta) {
		Iterator<ColliPack> iteratorNonUbicati = prodotti.iterator();
		while (iteratorNonUbicati.hasNext()) {
			ColliPack prodotto = iteratorNonUbicati.next();
			logger.info("Esamino il collipack ID " + prodotto.getIdColliPack());
			//int quantitàRichiesta = riga.getQtadaubicare(); //Mi viene passata da fuori in modo che possa decrementarla e restituirla.
			//Trovo il collo e l'ubicazione corrispondente.
			ColliCarico collo = trovoColloProdotto(prodotto);
			//Condizioni: Collo non ubicato.
			if (collo != null && collo.getUbicato().equals("NO")) {
				logger.info("Il collipack non è ubicato quindi procedo.");
				//Trovo quanti pezzi ci sono e vedo se bastano a coprire quelli richiesti
				int quantitàGiàImpegnata = prodotto.getQtaimpegnata();
				int quantitàOriginale = prodotto.getQta();
				int quantitàDisponibile = quantitàOriginale - quantitàGiàImpegnata;
				logger.info("Valuto le quantità: quantità richiesta: " + quantitàRichiesta + ", quantità disponibile: " + quantitàDisponibile);
				Ubicazioni ubicazione = generaUbicazioneFittizia();
				//Scorta
				Scorte scorta = generaScorta(prodotto, riga, ubicazione, testata, quantitàRichiesta, quantitàDisponibile);
				//Scorta non ubicata
				Scorte2 scortaNonUbicata = generaScortaNonUbicata(prodotto, collo, sessioneLavoro, quantitàDisponibile);
				//Collo scorta
				ScorteColli colloScorta = generaColloScorta(prodotto, collo, ubicazione, sessioneLavoro, quantitàDisponibile);
				//Creo la riga d'ubicazione per prelievo, non avrà indicazioni sull'ubicazione.
				Righiubicpre rigaUbicazione = generaRigaPrelievo(prodotto, collo, riga, ubicazione, testata, quantitàRichiesta, StatoAssegnazioneRiga.NONUBICATO);
				//Aggiungo all'oggetto assegnazione gli oggetti scorta e scortacollo.
				assegnazione.aggiungiRigaUbicazione(rigaUbicazione);
				assegnazione.aggiungiScortaUbicata(scorta);
				assegnazione.aggiungiScorteNonUbicate(scortaNonUbicata);
				assegnazione.aggiungiColloConScorte(colloScorta);
				assegnazione.setTipo(TipoAssegnazione.NON_UBICATA);
				//Rimuovo l'elemento corrente dalle successive iterazioni della lista. (Non dovrebbe servire a meno di non scambiare l'ordine)
				iteratorNonUbicati.remove();
				//Scalo la quantità richiesta di quella che è disponibile nella scorta.
				quantitàRichiesta -= quantitàDisponibile;
			} else {
				logger.info("Il collipack non è valido, passo al successivo. Ubicato: " + collo.getUbicato());
			}
			//Se la quantità richiesta è scesa a 0 procedo con la prossima riga.
			if (quantitàRichiesta <= 0) {
				logger.info("La quantità da ubicare è 0, significa che per questa riga d'ordine ho finito.");
				break;
			} else if (quantitàRichiesta < 0) {
				logger.error("La quantità da ubicare è scesa sotto 0.");
				break;
			}
		}
		return quantitàRichiesta;
	}
	
	protected void segnalaProdottoNonPresente(AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga, String sessioneLavoro) {
		//Righiubicpre rigaUbicazione = generaRigaPrelievo(prodotto, collo, riga, ubicazione, testata, riga.getQtadaubicare(), StatoAssegnazioneRiga.NONPRESENTE);
		Righiubicpre rigaUbicazione = new Righiubicpre();
		rigaUbicazione.setIDcollipack(0);
		rigaUbicazione.setIdArticolo(riga.getIdArticolo());
		rigaUbicazione.setIduniarticolo(riga.getIdUnicoArt());
		rigaUbicazione.setNrcollo("999999999");
		rigaUbicazione.setIdRigoOrdine(riga.getIdRigoOrdine());		
		rigaUbicazione.setMagazzino(riga.getMagazzino());		
		rigaUbicazione.setNrlista(riga.getNrLista());
		rigaUbicazione.setRaggliste(riga.getNrLista());
		rigaUbicazione.setQuantita(riga.getQtadaubicare());		
		rigaUbicazione.setSessioneLavoro(testata.getSessioneLavoro());
		rigaUbicazione.setIdTestataOrdine(testata.getIdTestaSped());
		rigaUbicazione.setTipoUbicazione("SC");
		rigaUbicazione.setTipoAssegnazione(StatoAssegnazioneRiga.NONPRESENTE.name());
		rigaUbicazione.setTipoUbicazione("SC");
		//Controllo i saldi per capire cosa non va
		MagaSd saldo = daoSaldi.trovaDaArticoloEMagazzino(riga.getIdUnicoArt(), riga.getMagazzino());
		String anomalia;
		if (saldo == null) {
			anomalia = "Il prodotto non ha disponibilità nel magazzino indicato.";
		} else if (saldo.getDisponibile() < 0) {
			anomalia = "Il prodotto presenta un disallineamento sulla disponibilità, va effettuato un controllo. (Disponibile: " + saldo.getDisponibile() + ")";
		} else if (saldo.getDisponibile() < riga.getQtadaubicare()) {
			anomalia = "Il prodotto non è sufficiente nel magazzino indicato. (Disponibile: " + saldo.getDisponibile() + ", Richiesto: " + riga.getQtadaubicare() + ")";
		} else {
			anomalia = "Il prodotto presenta un disallineamento tra disponibilità contabile e fisica, va effettuato un controllo.";
		}
		rigaUbicazione.setAnomalie(anomalia);
		//Aggiungo all'oggetto assegnazione gli oggetti scorta e scortacollo.
		assegnazione.aggiungiRigaUbicazione(rigaUbicazione);
		assegnazione.setTipo(TipoAssegnazione.NON_PRESENTE);
	}
	
	protected void copiaUbicazione(Righiubicpre riga, Ubicazioni ubicazione) {
		riga.setArea(ubicazione.getArea());
		riga.setBox(ubicazione.getBox());
		riga.setCorsia(ubicazione.getCorsia());
		riga.setPiano(ubicazione.getPiano());
		riga.setScaffale(ubicazione.getScaffale());
		riga.setUbicazione(ubicazione.getKeyUbica());
		riga.setTipoUbicazione(ubicazione.getTipoUbica());
	}
	
	protected void copiaUbicazione(RighiOrdine riga, Ubicazioni ubicazione) {
		riga.setArea(ubicazione.getArea());
		riga.setBox(ubicazione.getBox());
		riga.setCorsia(ubicazione.getCorsia());
		riga.setPiano(ubicazione.getPiano());
		riga.setScaffale(ubicazione.getScaffale());
		riga.setUbicazione(ubicazione.getKeyUbica());
		riga.setKeyUbiPre(ubicazione.getKeyUbica());
	}

	protected void copiaUbicazione(Scorte scorta, Ubicazioni ubicazione) {
		scorta.setArea(ubicazione.getArea());
		scorta.setBox(ubicazione.getBox());
		scorta.setCorsia(ubicazione.getCorsia());
		scorta.setPiano(ubicazione.getPiano());
		scorta.setScaffale(ubicazione.getScaffale());
		scorta.setUbicazione(ubicazione.getKeyUbica());
		scorta.setKeyUbicPre(ubicazione.getKeyUbica());
	}
	
	protected ColliCarico trovoColloProdotto(ColliPack prodotto) {
		String numeroCollo = prodotto != null ? prodotto.getNrIdColloPk() : "-";
		ColliCarico collo = daoColli.trovaDaCodice(numeroCollo);
		//Se il collo è stato cancellato non posso prenderlo.
		if (collo != null && !collo.getCancellato().equals("NO")) {
			collo = null;
		}
		return collo;
	}
	
	protected Ubicazioni trovaUbicazioneCollo(ColliCarico collo) {
		String codice = collo != null ? collo.getKeyUbicaCar() : "-";
		Ubicazioni ubicazione = daoUbicazioni.trovaDaCodice(codice);
		return ubicazione;
	}
	
	/**
	 * Scrive nel db la finalizzazione dell'assegnazione e ne restituisce l'esito.
	 * @param assegnazione
	 * @return
	 */
	public boolean assegnaOrdine(AssegnazioneOrdine assegnazioneOrdine) {
		boolean esito;		
		EntityManager em = getManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			//Cancellazione dati assegnazione precedente - FIXME controllare, per il momento viene fatta durante la preparazione all'assegnazione.
			//Aggiorno la testata
			if (!assegnazioneOrdine.getProdottiAssegnati().isEmpty()) {
				TestataOrdini testata = assegnazioneOrdine.getTestata();
				TestataOrdini entity = em.find(TestataOrdini.class, testata.getIdTestaSped());
				aggiornaValoriTestata(entity, testata);
				em.merge(entity);
			}
			//Aggiorno e inserisco tutto il resto
			for (AssegnazioneProdotto assegnazione : assegnazioneOrdine.getProdottiAssegnati()) {
				//Eseguo istruzioni in base al tipo d'assegnazione che ho fatto
				switch (assegnazione.getTipo()) {
					case NORMALE : 
					{
						//Inserisco le righe d'ubicazione
						for (Righiubicpre rigaUbicazione : assegnazione.getRigheUbicazione()) {
							em.persist(rigaUbicazione);
						}
						//Aggiorno collipack
						for (ColliPack prodotto : assegnazione.getProdotti()) {
							ColliPack entity = em.find(ColliPack.class, prodotto.getIdColliPack());
							aggiornaValoriColliPack(entity, prodotto);
							em.merge(entity);
						}
						//Aggiorno righe ordini
						RighiOrdine riga = assegnazione.getRiga();
						RighiOrdine entity = em.find(RighiOrdine.class, riga.getIdRigoOrdine());
						aggiornaValoriRigaOrdine(entity, riga);
						em.merge(entity);
					} break;
					case NON_UBICATA : 
					{
						//Inserisco le righe d'ubicazione
						for (Righiubicpre rigaUbicazione : assegnazione.getRigheUbicazione()) {
							em.persist(rigaUbicazione);
						}
						//Inserisco le scorte
						for (Scorte scorta : assegnazione.getScorteUbicate()) {
							em.persist(scorta);
						}
						//Inserisco le scorte non ubicate
						for (Scorte2 scortaNonUbicata : assegnazione.getScorteNonUbicate()) {
							em.persist(scortaNonUbicata);
						}
						//Inserisco i colli a scorta
						for (ScorteColli collo : assegnazione.getColliConScorte()) {
							em.persist(collo);
						}
					} break;
					case SCORTE : 
					{
						//Inserisco le righe d'ubicazione
						for (Righiubicpre rigaUbicazione : assegnazione.getRigheUbicazione()) {
							em.persist(rigaUbicazione);
						}
						//Inserisco le scorte
						for (Scorte scorta : assegnazione.getScorteUbicate()) {
							em.persist(scorta);
						}
						//Inserisco i colli a scorta
						for (ScorteColli collo : assegnazione.getColliConScorte()) {
							em.persist(collo);
						}
					} break;
					case NON_PRESENTE : {
						//Inserisco le righe d'ubicazione
						for (Righiubicpre rigaUbicazione : assegnazione.getRigheUbicazione()) {
							em.persist(rigaUbicazione);
						}
					}
				}
			}
			transaction.commit();
			esito = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			esito = false;
			if (transaction != null && transaction.isActive())
				transaction.rollback();
		} finally {
			em.close();
		}
		return esito;
	}
	
	protected void aggiornaValoriRigaOrdine(RighiOrdine entity, RighiOrdine riga) {
		logger.info("Aggiornamento righiordine");
		logger.info("Riga DB, q. ubicare: " + entity.getQtadaubicare() + ", q. assegnata: " + entity.getQtaAssegnata());
		logger.info("Riga Nuova, q. ubicare: " + riga.getQtadaubicare() + ", q. assegnata: " + riga.getQtaAssegnata());
		entity.setQtadaubicare(riga.getQtadaubicare());
		entity.setQtaAssegnata(riga.getQtaAssegnata());
		entity.setNrcollo(riga.getNrcollo());
		entity.setArea(riga.getArea());
		entity.setBox(riga.getBox());
		entity.setCorsia(riga.getCorsia());
		entity.setPiano(riga.getPiano());
		entity.setScaffale(riga.getScaffale());
		entity.setUbicazione(riga.getUbicazione());
		entity.setKeyUbiPre(riga.getKeyUbiPre());
	}

	protected void aggiornaValoriColliPack(ColliPack entity, ColliPack prodotto) {
		entity.setFlagimp(prodotto.getFlagimp());
		entity.setQta(prodotto.getQta());
		entity.setQtaimpegnata(prodotto.getQtaimpegnata());
	}

	protected void aggiornaValoriTestata(TestataOrdini entity, TestataOrdini testata) {
		entity.setQtaAssegnata(testata.getQtaAssegnata());
		entity.setPercAssegnata(testata.getPercAssegnata());
		entity.setSessioneLavoro(testata.getSessioneLavoro());
		entity.setStatoUbicazione(testata.getStatoUbicazione());
		entity.setFlag1(testata.getFlag1());
		entity.setStato(testata.getStato());
		entity.setStampato(testata.getStampato());
	}
	
	protected void assegnaSuColliSpecifici(List<ColliPack> prodotti, AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga) {
		Iterator<ColliPack> iteratorPrelievo = prodotti.iterator();
		while (iteratorPrelievo.hasNext()) {
			ColliPack prodotto = iteratorPrelievo.next();
			//Assegno la specifica riga sul determinato collipack, se il collipack finisce lo elimino dalla lista.
			boolean esaurito = assegnaSuColloSpecifico(prodotto, assegnazione, testata, riga);
			if (esaurito)
				iteratorPrelievo.remove();
			//Se la quantità richiesta è scesa a 0 procedo con la prossima riga.
			if (riga.getQtadaubicare() == 0) {
				logger.info("La quantità da ubicare è 0, significa che per questa riga d'ordine ho finito.");
				break;
			} else if (riga.getQtadaubicare() < 0) {
				logger.error("La quantità da ubicare è scesa sotto 0.");
				break;
			}
		}
	}
	
	protected boolean assegnaSuColloSpecifico(ColliPack prodotto, AssegnazioneProdotto assegnazione, TestataOrdini testata, RighiOrdine riga) {
		boolean esaurito = false;
		logger.info("Esamino il collipack ID " + prodotto.getIdColliPack());
		// Trovo il collo e l'ubicazione corrispondente.
		ColliCarico collo = trovoColloProdotto(prodotto);
		Ubicazioni ubicazione = trovaUbicazioneCollo(collo);
		if (ubicazione == null) {
			logger.info("Il collipack non è ubicato quindi ne creo una fittizia.");
			ubicazione = new Ubicazioni();
			ubicazione.setArea("  ");
			ubicazione.setBox("  ");
			ubicazione.setCorsia("   ");
			ubicazione.setIdUbicazioni(-1);
			ubicazione.setKeyUbica("NOUBICATO");
			ubicazione.setMagazzino("   ");
			ubicazione.setPiano("  ");
			ubicazione.setScaffale("   ");
			ubicazione.setTipoUbica("PR");
		}		
		// Trovo quanti pezzi ci sono e li scalo dalla riga e dal collipack.
		int quantitàGiàAssegnata = testata.getQtaAssegnata();
		int quantitàRigaGiàAssegnata = riga.getQtaAssegnata();
		int quantitàRichiesta = riga.getQtadaubicare();
		int quantitàGiàImpegnata = prodotto.getQtaimpegnata();
		int quantitàOriginale = prodotto.getQta();
		int quantitàDisponibile = quantitàOriginale - quantitàGiàImpegnata;
		int quantitàEffettivamentePresa;
		// Se basta questa aggiorno gli oggetti e termino l'assegnazione della riga
		// altrimenti scalo quello che è possibile scalare.
		logger.info("Valuto le quantità: quantità richiesta: " + quantitàRichiesta + ", quantità disponibile: "	+ quantitàDisponibile);
		if (quantitàRichiesta < quantitàDisponibile) {
			// Aggiorno testata, riga e prodotto disponibile
			logger.info("Mi basta questo collipack.");
			testata.setQtaAssegnata(quantitàGiàAssegnata + quantitàRichiesta);
			riga.setQtadaubicare(0);
			riga.setQtaAssegnata(riga.getQtaAssegnata() + quantitàRichiesta);
			prodotto.setQtaimpegnata(quantitàGiàImpegnata + quantitàRichiesta);
			prodotto.setListaimp(testata.getNrLista());
			quantitàEffettivamentePresa = quantitàRichiesta;
		} else if (quantitàRichiesta == quantitàDisponibile) {
			logger.info("Uso tutto questo collipack.");
			testata.setQtaAssegnata(quantitàGiàAssegnata + quantitàRichiesta);
			riga.setQtadaubicare(0);
			riga.setQtaAssegnata(riga.getQtaAssegnata() + quantitàRichiesta);
			prodotto.setQtaimpegnata(quantitàGiàImpegnata + quantitàRichiesta);
			prodotto.setFlagimp("S");
			prodotto.setListaimp(testata.getNrLista());
			quantitàEffettivamentePresa = quantitàRichiesta;
		} else {
			// Aggiorno testata, riga e prodotto disponibile
			logger.info("Non mi basta questo collipack ma lo prendo comunque e lo rendo non disponibile.");
			testata.setQtaAssegnata(quantitàGiàAssegnata + quantitàDisponibile);
			riga.setQtadaubicare(quantitàRichiesta - quantitàDisponibile);
			riga.setQtaAssegnata(quantitàRigaGiàAssegnata + quantitàDisponibile);
			prodotto.setQtaimpegnata(quantitàGiàImpegnata + quantitàDisponibile);
			prodotto.setFlagimp("S");
			prodotto.setListaimp(testata.getNrLista());
			quantitàEffettivamentePresa = quantitàDisponibile;
		}
		// Creo la riga d'ubicazione per prelievo
		Righiubicpre rigaUbicazione = generaRigaPrelievo(prodotto, collo, riga, ubicazione, testata, quantitàEffettivamentePresa, StatoAssegnazioneRiga.PRELIEVO);
		// Aggiungo le info all'assegnazione.
		assegnazione.aggiungiRigaUbicazione(rigaUbicazione);
		assegnazione.aggiungiProdotto(prodotto);
		assegnazione.aggiungiColli(collo);
		assegnazione.aggiungiUbicazione(ubicazione);
		assegnazione.setTipo(TipoAssegnazione.NORMALE);
		// Copio le informazioni di ubicazione sulla riga, eventualmente sovrascrivendo.
		riga.setNrcollo(collo.getKeyColloCar());
		copiaUbicazione(riga, ubicazione);
		// Rimuovo l'elemento corrente dalle successive iterazioni della lista se non è più disponibile.
		if (prodotto.getFlagimp().equals("S")) {
			logger.info("Rimuovo il collipack dalla lista dato che non è più disponibile.");
			esaurito = true;
		}
		return esaurito;
	}
	
}

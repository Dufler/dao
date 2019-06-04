package it.ltc.model.persistence.ordine;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.ColliImballoDao;
import it.ltc.database.dao.legacy.TestaCorrDao;
import it.ltc.database.dao.legacy.TestataOrdiniDao;
import it.ltc.database.model.legacy.ColliImballo;
import it.ltc.database.model.legacy.ColliPreleva;
import it.ltc.database.model.legacy.Destinatari;
import it.ltc.database.model.legacy.MittentiOrdine;
import it.ltc.database.model.legacy.RigaCorr;
import it.ltc.database.model.legacy.TestaCorr;
import it.ltc.database.model.legacy.TestataOrdini;
import it.ltc.database.model.legacy.model.StatoOrdine;
import it.ltc.model.interfaces.exception.CustomErrorCause;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.ordine.MContrassegno;
import it.ltc.model.interfaces.ordine.MInfoSpedizione;

public class ControllerInfoSpedizioneSQLServer extends Dao implements IControllerModel<MInfoSpedizione> {
	
	private static final Logger logger = Logger.getLogger(ControllerInfoSpedizioneSQLServer.class);
	
	protected final DecimalFormat df;
	protected final SimpleDateFormat meseGiorno;
	
	protected final TestataOrdiniDao daoTestataOrdine;
	protected final TestaCorrDao daoTestaCorr;
	protected final ColliImballoDao daoColliImballati;
	protected final ControllerIndirizziSQLServer controllerIndirizzi;

	public ControllerInfoSpedizioneSQLServer(String persistenceUnit) {
		super(persistenceUnit);
		
		df = new DecimalFormat("0000000");
		meseGiorno = new SimpleDateFormat("MMdd");
		
		daoTestataOrdine = new TestataOrdiniDao(persistenceUnit);
		daoTestaCorr = new TestaCorrDao(persistenceUnit);
		daoColliImballati = new ColliImballoDao(persistenceUnit);
		controllerIndirizzi = new ControllerIndirizziSQLServer(persistenceUnit);
	}

	@Override
	public void valida(MInfoSpedizione model) throws ModelValidationException {
		model.valida();
	}

	@Override
	public MInfoSpedizione inserisci(MInfoSpedizione model) throws ModelPersistenceException {
		// Recupero la lista degli ordini ed eseguo i controlli necessari.
		List<TestataOrdini> ordiniDaSpedire = checkOrdiniDaSpedire(model);
		// Controllo se esiste se ho già inserito le informazioni sulla spedizione.
		// Durante il check ho già controllato che IdTestaCorr sia identico per tutte
		// quindi mi basta controllare il primo
		// Se è uguale a 0, il valore di default, allora non ho ancora inserito un
		// testacorr corrispondente.
		boolean primoInserimento = ordiniDaSpedire.get(0).getIdTestaCorr() == 0;
		// La prima volta creo colliPreleva e un testacorr in cui combino le info delle
		// testateordini.
		// la prima volta aggiorno i testaordini con stato = "INSP" e campo = ID di
		// testacorr appena creato
		// Le successive vado in update su testacorr
		if (primoInserimento) {
			model = inserisciInfoSpedizione(ordiniDaSpedire, model);
		} else {
			model = aggiornaInfoSpedizione(ordiniDaSpedire, model);
		}
		return model;
	}

	@Override
	public MInfoSpedizione modifica(MInfoSpedizione model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MInfoSpedizione elimina(MInfoSpedizione model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<TestataOrdini> checkOrdiniDaSpedire(MInfoSpedizione spedizione) throws ModelPersistenceException {
		List<TestataOrdini> ordiniDaSpedire = new LinkedList<>();
		List<CustomErrorCause> problemiRiscontrati = new LinkedList<>();
		Set<String> riferimenti = spedizione.getRiferimentiOrdini();
		int idTestaCorr = -1;
		int idDestina = -1;
		// Recupero tutti gli ordini
		for (String riferimento : riferimenti) {
			TestataOrdini ordine = daoTestataOrdine.trovaDaRiferimento(riferimento);
			// Se non trovo corrispondenza o non sono in uno stato congruo
			// lancio un'eccezione.
			if (ordine == null) {
				CustomErrorCause problema = new CustomErrorCause(riferimento, "Non esiste alcun ordine a sistema con il riferimento indicato. (" + riferimento + ")");
				problemiRiscontrati.add(problema);
				continue; //Passo al riferimento successivo.
			} else {
				ordiniDaSpedire.add(ordine);
				StatoOrdine stato = ordine.getStatoOrdine();
				if (!(stato == StatoOrdine.ELAB || stato == StatoOrdine.INSP)) {
					CustomErrorCause problema = new CustomErrorCause(riferimento, "Non è possibile indicare informazioni per la spedizione dell'ordine indicato. (Riferimento: " + riferimento + ", Stato: " + stato.getNome() + ")");
					problemiRiscontrati.add(problema);
				}
			}
			// Controllo che il campo che le lega a testacorr sia vuoto per
			// tutti o identico per tutti
			if (idTestaCorr == -1) {
				idTestaCorr = ordine.getIdTestaCorr();
			} else if (idTestaCorr != ordine.getIdTestaCorr()) {
				CustomErrorCause problema = new CustomErrorCause(riferimento, "Non è possibile raggruppare ordini per cui sono già state indicate spedizioni diverse. (" + riferimento + ")");
				problemiRiscontrati.add(problema);
			}
			// Controllo che il destinatario sia lo stesso per tutti
			if (!spedizione.isForzaAccoppiamentoDestinatari()) {
				if (idDestina == -1) {
					idDestina = ordine.getIdDestina();
				} else if (idDestina != ordine.getIdDestina()) {
					CustomErrorCause problema = new CustomErrorCause(riferimento, "Non è possibile raggruppare in una sola spedizione ordini con destinatari diversi. (" + riferimento + ")");
					problemiRiscontrati.add(problema);
				}
			}
			//Controllo che lo stato del TestaCorr presente sia 0, passa a 1 quando vengono stampati i documenti
			if (idTestaCorr > 0) {
				TestaCorr testataSpedizione = daoTestaCorr.trovaDaID(idTestaCorr);
				if (testataSpedizione != null && testataSpedizione.getTrasmesso() != 0) {
					CustomErrorCause problema = new CustomErrorCause(riferimento, "I documenti relativi alla spedizione sono stati già stampati e allegati ai colli. Non è possibile aggiornare le informazioni sulla spedizione. (" + riferimento + ")");
					problemiRiscontrati.add(problema);
				}
			}
		}
		// Se ho trovato dei problemi sollevo l'eccezione altrimenti restituisco
		// le testate ordini trovate e proseguo con l'inserimento.
		if (!problemiRiscontrati.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder("Sono stati riscontrati problemi con gli ordini indicati: ");
			for (CustomErrorCause error : problemiRiscontrati) {
				errorMessage.append("\r\n");
				errorMessage.append(error.getCause());
			}
			throw new ModelPersistenceException(errorMessage.toString());
		}
		return ordiniDaSpedire;
	}
	
	protected void setInfoContrassegno(TestaCorr spedizione, MInfoSpedizione infoSpedizione) {
		MContrassegno infoContrassegno = infoSpedizione.getContrassegno();
		if (infoContrassegno != null) {
			spedizione.setCodBolla("4 ");
			spedizione.setContrassegno(infoContrassegno.getValore());
			spedizione.setTipoIncasso(infoContrassegno.getTipo().name());
			spedizione.setValutaIncasso(infoContrassegno.getValuta());
		} else {
			spedizione.setCodBolla("1 ");
			spedizione.setContrassegno(0.0);
			spedizione.setTipoIncasso("  ");
			spedizione.setValutaIncasso("EUR");
		}
	}
	
	protected void setInfoDestinatario(TestataOrdini ordine, TestaCorr spedizione) {
		Destinatari destinatario = controllerIndirizzi.trovaDestinatario(ordine.getIdDestina());
		if (destinatario == null)
			throw new ModelPersistenceException("Il destinatario per la spedizione non è stato trovato!");
		spedizione.setCap(destinatario.getCap());
		spedizione.setIndirizzo(destinatario.getIndirizzo());
		spedizione.setLocalita(destinatario.getLocalita());
		spedizione.setNazione(destinatario.getCodNaz()); //Ci vado a mettere la ISO2, TNT la vuole così.
		spedizione.setProvincia(destinatario.getProvincia());
		spedizione.setRagSocDest(destinatario.getRagSoc1());
		spedizione.setRagSocEst(destinatario.getRagSoc2());
	}
	
	protected void setInfoMittente(TestataOrdini ordine, TestaCorr spedizione) {
		MittentiOrdine mittente = controllerIndirizzi.trovaMittente(ordine.getIdMittente());
		if (mittente == null)
			throw new ModelPersistenceException("Il mittente per la spedizione non è stato trovato!");
		spedizione.setCapMitt(mittente.getCap());
		spedizione.setNazioneMitt(mittente.getNazione());
		spedizione.setRagSocMitt(mittente.getRagioneSociale());
	}
	
	protected void setInfoDocumento(TestaCorr spedizione, MInfoSpedizione infoSpedizione) {
		spedizione.setDocumentoRiferimento(infoSpedizione.getRiferimentoDocumento());
		spedizione.setDocumentoData(infoSpedizione.getDataDocumento());
		spedizione.setDocumentoTipo(infoSpedizione.getTipoDocumento());
	}
	
	protected void setInfoSpedizione(TestataOrdini ordine, TestaCorr spedizione, MInfoSpedizione infoSpedizione) {
		// Aggiorno i campi necessari
		spedizione.setCorriere(infoSpedizione.getCorriere());
		spedizione.setCodMittente(infoSpedizione.getCodiceCorriere());
		int dataConsegna = infoSpedizione.getDataConsegna() != null ? Integer.parseInt(meseGiorno.format(infoSpedizione.getDataConsegna())) : 0;
		spedizione.setDataConsegna(dataConsegna);
		spedizione.setDataConsegnaTassativa(infoSpedizione.getDataConsegna());
		String note = infoSpedizione.getNote() != null ? infoSpedizione.getNote() : "";
		String note1 = note.length() > 35 ? note.substring(0, 35) : note;
		String note2 = note.length() > 35 ? note.substring(35, note.length()) : "";
		spedizione.setNote1(note1);
		spedizione.setNote2(note2);
		spedizione.setServizio(infoSpedizione.getServizioCorriere());
		spedizione.setValoreMerce(infoSpedizione.getValoreDoganale());
		spedizione.setMittenteAlfa(ordine.getNrOrdine());
		spedizione.setPezzi(ordine.getPezzieffet());
		//Aggiunta recente per i ws su logica 2.0
		int trasmesso;
		if (infoSpedizione.isAbilitaPartenza()) {
			trasmesso = infoSpedizione.getRiferimentiOrdini().size() > 1 ? 2 : 1;
		} else {
			trasmesso = 0;
		}
		spedizione.setTrasmesso(trasmesso);
	}
	
	protected MInfoSpedizione aggiornaInfoSpedizione(List<TestataOrdini> ordiniDaSpedire, MInfoSpedizione infoSpedizione) {
		
		// Recupero la spedizione dal primo ordine, ho già controllato che siano tutti uguali o che abbiano forzato l'accoppiamento
		TestataOrdini primoOrdine = ordiniDaSpedire.get(0);
		EntityManager em = getManager();
		TestaCorr spedizione = em.find(TestaCorr.class, primoOrdine.getIdTestaCorr());
		
		// Aggiorno i campi necessari
		setInfoSpedizione(primoOrdine, spedizione, infoSpedizione);
		
		//Documento - Non lo faccio nella versione base
		setInfoDocumento(spedizione, infoSpedizione);

		// Contrassegno
		setInfoContrassegno(spedizione, infoSpedizione);
		
		//Aggiorno le info sul model
		infoSpedizione.setPeso(spedizione.getPeso());
		infoSpedizione.setVolume(spedizione.getVolume());
		infoSpedizione.setColli(spedizione.getNrColli());
		infoSpedizione.setPezzi(spedizione.getPezzi());

		// Vado in scrittura in maniera transazionale.
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.merge(spedizione);
			infoSpedizione.setId(spedizione.getIdTestaCor());
			transaction.commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			infoSpedizione = null;
		} finally {
			em.close();
		}
		return infoSpedizione;
	}
	
	protected MInfoSpedizione inserisciInfoSpedizione(List<TestataOrdini> ordiniDaSpedire, MInfoSpedizione infoSpedizione) {
		// Preparo le variabili che mi serviranno per andare in insert.
		TestaCorr spedizione = new TestaCorr();
		List<ColliPreleva> colliDaPrelevare = new LinkedList<>();
		List<RigaCorr> righeSpedizione = new LinkedList<>();
		int pezzi = 0;
		int colli = 0;
		double peso = 0.0;
		double volume = 0.0;
		// Recupero il mittente e il destinatario dal primo ordine, ho già
		// controllato che siano tutti uguali
		TestataOrdini primoOrdine = ordiniDaSpedire.get(0);
		
		// Imposto alcune info di testacorr (da fare solo all'inserimento)
		int progressivoSpedizione = daoTestaCorr.getProgressivoSpedizioneTestaCorr();
		spedizione.setMittenteNum(progressivoSpedizione);
		spedizione.setNrSpedi(progressivoSpedizione);
		
		//Info spedizione
		setInfoSpedizione(primoOrdine, spedizione, infoSpedizione);
		
		//Documento - Non lo faccio nella versione base
		setInfoDocumento(spedizione, infoSpedizione);

		// Contrassegno
		setInfoContrassegno(spedizione, infoSpedizione);

		//Destinatario
		setInfoDestinatario(primoOrdine, spedizione);

		//Mittente
		setInfoMittente(primoOrdine, spedizione);

		// Recupero le info necessarie da ogni ordine e aggiorno quelle che dovranno essere salvate
		for (TestataOrdini ordine : ordiniDaSpedire) {
			//Fix, secondo Antonio e Andrea il campo nr lista di testaCorr andrebbe riempito con uno qualsiasi dei nr lista delle testate.
			spedizione.setNrLista(ordine.getNrLista());
			//Aggiorno le info dell'ordine necessarie
			ordine.setStato("INSP");
			ordine.setCodCorriere(infoSpedizione.getCorriere());
			ordine.setCodiceClienteCorriere(infoSpedizione.getCodiceCorriere());
			ordine.setTipoTrasporto(infoSpedizione.getServizioCorriere());
			MContrassegno infoContrassegno = infoSpedizione.getContrassegno();
			if (infoContrassegno != null) {
				ordine.setTipoIncasso(infoContrassegno.getTipo().name());
				ordine.setValContrassegno(infoContrassegno.getValore());
			}
			// Recupero i ColliImballo corrispondenti
			List<ColliImballo> colliImballati = daoColliImballati.trovaDaNumeroLista(ordine.getNrLista());
			for (ColliImballo colloImballato : colliImballati) {
				// Creo il ColliPreleva corrispondente
				ColliPreleva colloDaPrelevare = creaColloDaPrelevare(ordine, colloImballato, infoSpedizione);
				colliDaPrelevare.add(colloDaPrelevare);
				// Creo la RigaCorr corrispondente
				RigaCorr rigaSpedizione = creaRigaSpedizione(ordine, colloImballato, infoSpedizione, progressivoSpedizione);
				righeSpedizione.add(rigaSpedizione);
				// Aggiungo per avere i totali di pezzi, peso e volume
				colli += 1;
				pezzi += colloImballato.getPezziCollo();
				peso += colloImballato.getPesoKg();
				volume += rigaSpedizione.getVolume();
			}
		}
		// Aggiorno le info su testacorr
		spedizione.setPeso(peso);
		spedizione.setVolume(volume);
		spedizione.setNrColli(colli);
		spedizione.setPezzi(pezzi);
		// aggiorno le info sul model
		infoSpedizione.setPeso(peso);
		infoSpedizione.setVolume(volume);
		infoSpedizione.setColli(colli);
		infoSpedizione.setPezzi(pezzi);
		// Vado in scrittura in maniera transazionale.
		EntityManager em = getManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			// Inserisco TestaCorr, RigaCorr e ColliPreleva
			em.persist(spedizione);
			for (RigaCorr riga : righeSpedizione) {
				em.persist(riga);
			}
			for (ColliPreleva collo : colliDaPrelevare) {
				em.persist(collo);
			}
			// Aggiorno le TestataOrdini con il testacorr creato e le altre info.
			for (TestataOrdini ordine : ordiniDaSpedire) {
				ordine.setIdTestaCorr(spedizione.getIdTestaCor());
				em.merge(ordine);
			}
			infoSpedizione.setId(spedizione.getIdTestaCor());
			transaction.commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			infoSpedizione = null;
		} finally {
			em.close();
		}
		return infoSpedizione;
	}
	
	protected ColliPreleva creaColloDaPrelevare(TestataOrdini ordine, ColliImballo colloImballato, MInfoSpedizione infoSpedizione) {
		ColliPreleva colloDaPrelevare = new ColliPreleva();
		colloDaPrelevare.setNrLista(ordine.getNrLista());
		colloDaPrelevare.setBarcodeCorriere(colloImballato.getBarCodeImb());
		colloDaPrelevare.setCodiceCorriere(infoSpedizione.getCorriere());
		colloDaPrelevare.setKeyColloPre(colloImballato.getKeyColloSpe());
//		colloDaPrelevare.setNrColloCliente(colloImballato.getNrIdCollo());
		colloDaPrelevare.setNrColloCliente(colloImballato.getNrRifCliente());
		return colloDaPrelevare;
	}
	
	protected RigaCorr creaRigaSpedizione(TestataOrdini ordine, ColliImballo colloImballato, MInfoSpedizione infoSpedizione, int progressivoSpedizione) {
		//Calcolo il volume corretto: secondo Antonio va diviso per 1 milione
		double volumeCollo = ((double) colloImballato.getVolume()) / 1000000.0;
		RigaCorr rigaSpedizione = new RigaCorr();
		rigaSpedizione.setCodRaggruppamento(progressivoSpedizione);
		rigaSpedizione.setFormato(colloImballato.getCodFormato());
		rigaSpedizione.setNrCollo(colloImballato.getNrIdCollo());
		//rigaSpedizione.setNrColloStr(df.format(progressivoSpedizione));
		rigaSpedizione.setNrColloStr(colloImballato.getBarCodeImb());
		rigaSpedizione.setNrLista(ordine.getNrLista());
		rigaSpedizione.setNrSpedi(progressivoSpedizione);
		rigaSpedizione.setPeso(colloImballato.getPesoKg());
		rigaSpedizione.setPezzi(colloImballato.getPezziCollo());
		rigaSpedizione.setVolume(volumeCollo);
		rigaSpedizione.setCodMittente(infoSpedizione.getCodiceCorriere());
		return rigaSpedizione;
	}

}

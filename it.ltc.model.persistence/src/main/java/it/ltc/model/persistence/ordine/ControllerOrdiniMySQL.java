package it.ltc.model.persistence.ordine;

import java.util.HashMap;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Commessa;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.ordine.MOrdine;

public class ControllerOrdiniMySQL extends Dao implements IControllerModel<MOrdine> {
	
	private static final Logger logger = Logger.getLogger(ControllerOrdiniMySQL.class);
	
	private static HashMap<Commessa, ControllerOrdiniMySQL> instances = new HashMap<Commessa, ControllerOrdiniMySQL>();
	
	private ControllerOrdiniMySQL(Commessa c) {
		super(c.getNomeRisorsa());
		logger.info("Istanziato Controller Ordini per la commessa: " + c.getNome());
	}
	
	public static ControllerOrdiniMySQL getInstance(Commessa c) {
		if (!instances.containsKey(c)) {
			ControllerOrdiniMySQL controller = new ControllerOrdiniMySQL(c);
			instances.put(c, controller);
		}
		return instances.get(c);
	}

	@Override
	public void valida(MOrdine model) throws ModelValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MOrdine inserisci(MOrdine model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MOrdine modifica(MOrdine model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MOrdine elimina(MOrdine model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void valida(MOrdine ordine) throws ModelValidationException {
//		logger.info("Comincio la validazione");
//		//Controllo sul riferimento cliente
//		Ordine filtroRiferimentoCliente = new Ordine();
//		filtroRiferimentoCliente.setIdCommessa(cliente.getId());
//		filtroRiferimentoCliente.setRiferimentoCliente(ordine.getRiferimentoordine());
//		Ordine matchRiferimentoCliente = managerOrdini.getEntity(filtroRiferimentoCliente);
//		if (matchRiferimentoCliente != null) {
//			throw new ModelValidationException("E' gia' stato inserito un ordine con lo stesso riferimento.");
//		}
//		//Controllo sul riferimento del documento fiscale, se presente
//		String documento = ordine.getRiferimentodocumentofiscale();
//		if (documento != null && !documento.isEmpty()) {
//			Ordine filtroRiferimentoDocumento = new Ordine();
//			filtroRiferimentoDocumento.setIdCommessa(cliente.getId());
//			filtroRiferimentoDocumento.setRiferimentoDocumentoFiscale(documento);
//			Ordine matchRiferimentoDocumento = managerOrdini.getEntity(filtroRiferimentoDocumento);
//			if (matchRiferimentoDocumento != null) {
//				throw new ModelValidationException("E' gia' stato inserito un ordine con lo stesso documento fiscale.");
//			}
//		}
//		//Controllo sul codice corriere, se presente
//		String codiceCorriere = ordine.getCodicecorriere();
//		if (codiceCorriere != null && !codiceCorriere.isEmpty()) {
//			CodiceClienteCorriere filtro = new CodiceClienteCorriere();
//			filtro.setCodiceCliente(codiceCorriere);
//			CodiceClienteCorriere corrispondenza = managerCodici.getEntity(filtro, true);
//			if (corrispondenza != null) {
//				if (corrispondenza.getEStato() == CodiceClienteCorriere.Stato.DISMESSO)
//					throw new ModelValidationException("Il codice corriere non è stato chiuso.");
//				if (!corrispondenza.getIdCommessa().equals(cliente.getId()))
//					throw new ModelValidationException("Il codice corriere non è utilizzabile.");
//			} else {
//				throw new ModelValidationException("Il codice corriere specificato non è stato trovato.");
//			}
//		}
//		//controllo sulla nazione
//		Nazione nazioneDestinatario = Nazione.getNazione(ordine.getDestinatario().getNazione());
//		if (nazioneDestinatario == null) {
//			throw new ModelValidationException("La nazione del destinatario non è valida.");
//		}
//		Nazione nazioneMittente = Nazione.getNazione(ordine.getMittente().getNazione());
//		if (nazioneMittente == null) {
//			throw new ModelValidationException("La nazione del mittente non è valida.");
//		} else {
//			if (!nazioneMittente.getUe()) {
//				Double valoreDoganale = ordine.getValoredoganale();
//				if (valoreDoganale == null || valoreDoganale <= 0)
//					throw new ModelValidationException("E' necessario inserire il valore doganale della merce se la spedizione e' diretta al di fuori dell'UE");
//			}
//		}
//		//Controllo sui prodotti
//		//TODO - manca la quantità
//		TipoIDProdotto tipo = TipoIDProdotto.valueOf(ordine.getTipoidentificazioneprodotti());
//		prodotti.clear();
//		switch (tipo) {
//			case BARCODE : checkPerBarcode(ordine); break;
//			case CHIAVE : checkPerChiave(ordine); break;
//			case MODELLOTAGLIA : checkPerModelloTaglia(ordine); break;
//		}
//		if (prodotti.isEmpty())
//			throw new ModelValidationException("E' necessario inserire almeno un prodotto nell'ordine.");
//		logger.info("Validazione terminata correttamente.");
//	}
//
//	private void checkPerModelloTaglia(MOrdine ordine) throws ModelValidationException {
//		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
//			Prodotto filtro = new Prodotto();
//			filtro.setTaglia(prodotto.getTaglia());
//			filtro.setCodiceModello(prodotto.getCodicemodello());
//			filtro.setIdCliente(cliente.getIdCliente());
//			Prodotto match = managerProdotti.getEntity(filtro);
//			if (match == null || match.getId() == null || match.getId() < 1)
//				throw new ModelValidationException("La combinazione modello-taglia: '" + prodotto.getCodicemodello() + "-" + prodotto.getTaglia() + "' non è stato trovata. L'ordine non sarà importato.");
//			else {
//				prodotto.setId(match.getId());
//				prodotti.add(prodotto);
//			}	
//		}	
//	}
//
//	private void checkPerChiave(MOrdine ordine) throws ModelValidationException {
//		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
//			Prodotto filtro = new Prodotto();
//			filtro.setCodificaCliente(prodotto.getChiave());
//			filtro.setIdCliente(cliente.getIdCliente());
//			Prodotto match = managerProdotti.getEntity(filtro);
//			if (match == null || match.getId() == null || match.getId() < 1)
//				throw new ModelValidationException("La chiave '" + prodotto.getChiave() + "' non e' stata trovata. L'ordine non sarà importato.");
//			else {
//				prodotto.setId(match.getId());
//				prodotti.add(prodotto);
//			}	
//		}	
//	}
//
//	private void checkPerBarcode(MOrdine ordine) throws ModelValidationException {
//		for (ProdottoOrdinato prodotto : ordine.getProdotti()) {
//			Prodotto filtro = new Prodotto();
//			filtro.setBarcode(prodotto.getBarcode());
//			filtro.setIdCliente(cliente.getIdCliente());
//			Prodotto match = managerProdotti.getEntity(filtro);
//			if (match == null || match.getId() == null || match.getId() < 1)
//				throw new ModelValidationException("Il barcode '" + prodotto.getBarcode() + "' non e' stato trovato. L'ordine non sarà importato.");
//			else {
//				prodotto.setId(match.getId());
//				prodotti.add(prodotto);
//			}	
//		}	
//	}
//
//	@Override
//	public int inserisci(MOrdine ordine) throws ModelPersistenceException {
//		logger.info("Comincio l'inserimento");
//		TransactionManager<Ordine> tManagerOrdine = new TransactionManager<Ordine>(Ordine.class, database);
//		TransactionManager<OrdineDettaglio> tManagerOrdineDettaglio = new TransactionManager<OrdineDettaglio>(OrdineDettaglio.class, database);
//		TransactionManager<Spedizione> tManagerSpedizione = new TransactionManager<Spedizione>(Spedizione.class, database);
//		TransactionManager<Indirizzo> tManagerIndirizzo = new TransactionManager<Indirizzo>(Indirizzo.class, database);
//		TransactionManager<Contrassegno> tManagerContrassegno = new TransactionManager<Contrassegno>(Contrassegno.class, database);
//		TransactionManager<Assicurazione> tManagerAssicurazione = new TransactionManager<Assicurazione>(Assicurazione.class, database);
//		TransactionManager<ParticolaritaSpedizione> tManagerParticolarita = new TransactionManager<ParticolaritaSpedizione>(ParticolaritaSpedizione.class, database);
//		boolean successo = true;
//		String errorMessage = null;
//		int idOrdine = -1;
//		try {
//			//Valorizzazione ordine
//			logger.info("Valorizzo l'ordine");
//			Ordine nuovoOrdine = new Ordine();
//			if (ordine.getDataconsegna() != null) {
//				try {
//					nuovoOrdine.setDataConsegna(sdf.parse(ordine.getDataconsegna()));
//				} catch (ParseException e) {
//					throw new ModelPersistenceException("La data di consegna indicata non è valida.");
//				}
//			}
//			nuovoOrdine.setDataRicezione(new Date());
//			nuovoOrdine.setIdCommessa(cliente.getId());
//			nuovoOrdine.setNote(ordine.getNote());
//			nuovoOrdine.setOrigine("WS Ordini");
//			nuovoOrdine.setPriorita(ordine.getPriorita());
//			nuovoOrdine.setRiferimentoCliente(ordine.getRiferimentoordine());
//			nuovoOrdine.setRiferimentoDocumentoFiscale(ordine.getRiferimentodocumentofiscale());
//			nuovoOrdine.setRiferimentoInterno("-");
//			nuovoOrdine.setStato("IMP"); //TODO - qui potrebbe cambiare se non c'è disponibilità di prodotti
//			nuovoOrdine.setTipo(ordine.getTipo());
//			idOrdine = tManagerOrdine.insertAndReturnID(nuovoOrdine);
//			boolean inserimentoOrdine = (idOrdine != -1);
//			//Valorizzazione dettagli
//			boolean inserimentoProdotti = true;
//			logger.info("valorizzo i prodotti");
//			for (ProdottoOrdinato prodotto : prodotti) {
//				OrdineDettaglio dettaglio = new OrdineDettaglio();
//				dettaglio.setIdOrdine(idOrdine);
//				dettaglio.setIdProdotto(prodotto.getId());
//				dettaglio.setMagazzino(prodotto.getMagazzino());
//				dettaglio.setQuantitaRichiesta(prodotto.getQuantita());
//				boolean inserimento = tManagerOrdineDettaglio.insert(dettaglio);
//				//boolean inserimento = managerDettagli.insert(dettaglio);
//				if (!inserimento) {
//					inserimentoProdotti = false;
//				}
//			}
//			//Valorizzazione indirizzi - TODO qui va raffinato per fargli recuperare un indirizzo, se esiste già
//			logger.info("valorizzo gli indirizzi");
//			//Nuovo inserimento destinatario
//			Indirizzo destinatario = getDestinatario(ordine);
//			Integer idDestinatario = destinatario.getId();
//			if (idDestinatario == null) {
//				idDestinatario = tManagerIndirizzo.insertAndReturnID(destinatario);
//			}
//			boolean inserimentoDestinatario = (idDestinatario != -1);
//			//Nuovo inserimento mittente
//			Indirizzo mittente = getMittente(ordine);
//			Integer idMittente = mittente.getId();
//			if (idMittente == null) {
//				idMittente = tManagerIndirizzo.insertAndReturnID(mittente);
//			}
//			boolean inserimentoMittente = (idMittente != -1);
//			//Fine nuovo inserimento mittente
//			//Valorizzazione spedizione
//			logger.info("Valorizzo la spedizione.");
//			Spedizione spedizione = new Spedizione();
//			boolean isAssicurato = (ordine.getAssicurazione() != null);
//			spedizione.setAssicurazione(isAssicurato);
//			spedizione.setCodiceCliente(getCodiceCliente(ordine));
//			boolean isContrassegno = (ordine.getContrassegno() != null);
//			spedizione.setContrassegno(isContrassegno);
//			spedizione.setIdCommessa(cliente.getId());
//			spedizione.setIdCorriere(getCorriere(ordine));
//			spedizione.setIndirizzoDestinazione(idDestinatario);
//			spedizione.setIndirizzoPartenza(idMittente);
//			spedizione.setIdDocumento(idOrdine);
//			spedizione.setNote(ordine.getNote());
//			boolean isParticolare = (ordine.getParticolarita() != null);
//			spedizione.setParticolarita(isParticolare);
//			spedizione.setPezzi(prodotti.size());
//			spedizione.setRiferimentoCliente(ordine.getRiferimentoordine());
//			spedizione.setServizio(ordine.getServiziocorriere());
//			spedizione.setStato("IMP"); //TODO - lo stato potrebbe essere diverso se non c'è disponibilità di prodotti.
//			spedizione.setValoreMerceDichiarato(ordine.getValoredoganale());
//			int idSpedizione = tManagerSpedizione.insertAndReturnID(spedizione);
//			boolean inserimentoSpedizione = (idSpedizione != -1);
//			//Valorizzazione contrassegno
//			if (isContrassegno) {
//				logger.info("valorizzo il contrassegno");
//				MContrassegno mContrassegno = ordine.getContrassegno();
//				Contrassegno contrassegno = new Contrassegno();
//				contrassegno.setIdSpedizione(idSpedizione);
//				contrassegno.setTipo(mContrassegno.getTipo());
//				contrassegno.setValore(mContrassegno.getValore());
//				contrassegno.setValuta("EUR");
//				tManagerContrassegno.insert(contrassegno);
//			}
//			//Valorizzazione assicurazione
//			if (isAssicurato) {
//				logger.info("valorizzo l'assicurazione");
//				MAssicurazione mAssicurazione = ordine.getAssicurazione();
//				Assicurazione assicurazione = new Assicurazione();
//				assicurazione.setIdSpedizione(idSpedizione);
//				assicurazione.setTipo(mAssicurazione.getTipo());
//				assicurazione.setValore(mAssicurazione.getValore());
//				assicurazione.setValuta("EUR");
//				tManagerAssicurazione.insert(assicurazione);
//			}
//			//Valorizzazione particolarita
//			if (isParticolare) {
//				logger.info("valorizzo le particolarita'");
//				//MParticolarita mParticolarita = ordine.getParticolarita();
//				ParticolaritaSpedizione particolarita = new ParticolaritaSpedizione();
//				particolarita.setIdSpedizione(idSpedizione);
//				particolarita.setAppuntamento(false);
//				tManagerParticolarita.insert(particolarita);
//			}
//			successo = inserimentoOrdine && inserimentoProdotti && inserimentoDestinatario && inserimentoMittente && inserimentoSpedizione;
//		} catch (ModelPersistenceException e) {
//			logger.error(e.getMessage(), e);
//			errorMessage = e.getMessage();
//			successo = false;
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			successo = false;
//		} finally {
//			//Commit
//			if (successo) {
//				logger.info("Commit sul DB");
//				tManagerOrdine.commit();
//				tManagerOrdineDettaglio.commit();
//				tManagerSpedizione.commit();
//				tManagerContrassegno.commit();
//				tManagerAssicurazione.commit();
//				tManagerParticolarita.commit();
//				logger.info("Ordine " + idOrdine + " inserito correttamente.");
//			} else {
//				tManagerOrdine.rollback();
//				tManagerOrdineDettaglio.rollback();
//				tManagerSpedizione.rollback();
//				tManagerContrassegno.rollback();
//				tManagerAssicurazione.rollback();
//				tManagerParticolarita.rollback();
//				logger.info("Rollback dell'ordine " + idOrdine + ".");
//			}
//		}
//		if (!successo) {
//			errorMessage = "L'inserimento dell'ordine è fallito: " + errorMessage;
//			logger.info(errorMessage);
//			throw new ModelPersistenceException(errorMessage);
//		}
//		return idOrdine;
//	}
//
//	private Integer getCorriere(MOrdine ordine) {
//		Corriere corriere = Corriere.valueOf(ordine.getCorriere().toUpperCase());
//		return corriere.getId();
//	}
//
//	private String getCodiceCliente(MOrdine ordine) {
//		String codiceCliente = null;
//		for (CodiceClienteCorriere codice : CodiceClienteCorriere.getListaCodiciCliente()) {
//			if (codice.getIdCommessa() == cliente.getId() && codice.getIdCorriere() == getCorriere(ordine)) {
//				codiceCliente = codice.getCodiceCliente();
//				break;
//			} 
//		}
//		return codiceCliente;
//	}
//
//	@Override
//	public boolean modifica(MOrdine ordine) throws ModelPersistenceException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean elimina(MOrdine ordine) throws ModelPersistenceException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	public LinkedList<MOrdineSintetico> getListaOrdini() {
//		LinkedList<MOrdineSintetico> lista = new LinkedList<MOrdineSintetico>();
//		Ordine filtro = new Ordine();
//		filtro.setIdCommessa(cliente.getId());
//		for (Ordine ordine : managerOrdini.getEntities(filtro)) {
//			MOrdineSintetico mOrdine = new MOrdineSintetico();
//			mOrdine.setDataordine(sdf.format(ordine.getDataRicezione()));
//			mOrdine.setRiferimentoordine(ordine.getRiferimentoCliente());
//			mOrdine.setStato(ordine.getStato());
//			lista.add(mOrdine);
//		}
//		return lista;
//	}
//
//	public MOrdine getOrdine(String riferimento) {
//		MOrdine mOrdine = null;
//		Ordine filtro = new Ordine();
//		filtro.setIdCommessa(cliente.getId());
//		filtro.setRiferimentoCliente(riferimento);
//		Ordine ordine = managerOrdini.getEntity(filtro, true);
//		if (ordine != null) {
//			mOrdine = new MOrdine();
//			mOrdine.setDataordine(sdf.format(ordine.getDataRicezione()));
//			mOrdine.setStato(ordine.getStato());
//			mOrdine.setTipo(ordine.getTipo());
//			mOrdine.setRiferimentoordine(ordine.getRiferimentoCliente());
//			String riferimentoDocumento = ordine.getRiferimentoDocumentoFiscale();
//			if (riferimentoDocumento != null && !riferimentoDocumento.isEmpty())
//				mOrdine.setRiferimentodocumentofiscale(riferimentoDocumento);
//			String note = ordine.getNote();
//			if (note != null && !note.isEmpty())
//				mOrdine.setNote(note);
//			Integer priorita = ordine.getPriorita();
//			if (priorita != null && priorita > 0)
//				mOrdine.setPriorita(priorita);
//			//Prodotti
//			LinkedList<ProdottoOrdinato> listaProdotti = new LinkedList<ProdottoOrdinato>();
//			OrdineDettaglio filtroDettagli = new OrdineDettaglio();
//			filtroDettagli.setIdOrdine(ordine.getId());
//			List<OrdineDettaglio> lista = managerDettagli.getEntities(filtroDettagli);
//			for (OrdineDettaglio dettaglio : lista) {
//				ProdottoOrdinato prodotto = new ProdottoOrdinato();
//				Prodotto filtroProdotto = new Prodotto();
//				filtroProdotto.setId(dettaglio.getIdProdotto());
//				Prodotto match = managerProdotti.getEntity(filtroProdotto, true);
//				if (match != null) {
//					prodotto.setId(match.getId());
//					prodotto.setBarcode(match.getBarcode());
//					prodotto.setChiave(match.getCodificaCliente());
//					prodotto.setCodicemodello(match.getCodiceModello());
//					prodotto.setTaglia(match.getTaglia());
//				}
//				prodotto.setMagazzino(dettaglio.getMagazzino());
//				prodotto.setQuantita(dettaglio.getQuantitaRichiesta());
//				listaProdotti.add(prodotto);
//			}
//			mOrdine.setProdotti(listaProdotti);
//			//Spedizione
//			Spedizione filtroSpedizione = new Spedizione();
//			filtroSpedizione.setIdDocumento(ordine.getId());
//			Spedizione spedizione = managerSpedizioni.getEntity(filtroSpedizione, true);
//			if (spedizione != null) {
//				Corriere corriere = Corriere.getCorrierebyID(spedizione.getIdCorriere());
//				mOrdine.setCorriere(corriere.name());
//				mOrdine.setServiziocorriere(spedizione.getServizio());
//				Double valoreDoganale = spedizione.getValoreMerceDichiarato();
//				if (valoreDoganale != null)
//					mOrdine.setValoredoganale(valoreDoganale);
//				String tracking = spedizione.getLetteraDiVettura();
//				if (tracking != null && !tracking.isEmpty())
//					mOrdine.setCodicetracking(tracking);
//				//Indirizzi
//				Indirizzo filtroDestinatario = new Indirizzo();
//				filtroDestinatario.setId(spedizione.getIndirizzoDestinazione());
//				Indirizzo destinatario = managerIndirizzi.getEntity(filtroDestinatario, true);
//				if (destinatario != null) {
//					MIndirizzo mDestinatario = new MIndirizzo();
//					mDestinatario.setCap(destinatario.getCap());
//					mDestinatario.setIndirizzo(destinatario.getIndirizzo());
//					mDestinatario.setLocalita(destinatario.getLocalita());
//					mDestinatario.setNazione(destinatario.getNazione());
//					mDestinatario.setProvincia(destinatario.getProvincia());
//					mDestinatario.setRagionesociale(destinatario.getRagioneSociale());
//					String email = destinatario.getEmail();
//					if (email != null && !email.isEmpty())
//						mDestinatario.setEmail(email);
//					String telefono = destinatario.getTelefono();
//					if (telefono != null && !telefono.isEmpty())
//						mDestinatario.setTelefono(telefono);
//					mOrdine.setDestinatario(mDestinatario);
//				}
//				Indirizzo filtroMittente = new Indirizzo();
//				filtroMittente.setId(spedizione.getIndirizzoPartenza());
//				Indirizzo mittente = managerIndirizzi.getEntity(filtroMittente, true);
//				if (mittente != null) {
//					MIndirizzo mMittente = new MIndirizzo();
//					mMittente.setCap(mittente.getCap());
//					mMittente.setIndirizzo(mittente.getIndirizzo());
//					mMittente.setLocalita(mittente.getLocalita());
//					mMittente.setNazione(mittente.getNazione());
//					mMittente.setProvincia(mittente.getProvincia());
//					mMittente.setRagionesociale(mittente.getRagioneSociale());
//					String email = mittente.getEmail();
//					if (email != null && !email.isEmpty())
//						mMittente.setEmail(email);
//					String telefono = mittente.getTelefono();
//					if (telefono != null && !telefono.isEmpty())
//						mMittente.setTelefono(telefono);
//					mOrdine.setMittente(mMittente);
//				}
//				//Contrassegno
//				if (spedizione.getContrassegno()) {
//					SpedizioneContrassegno filtroContrassegno = new SpedizioneContrassegno();
//					filtroContrassegno.setIdSpedizione(spedizione.getId());
//					SpedizioneContrassegno contrassegno = managerContrassegni.getEntity(filtroContrassegno, true);
//					if (contrassegno != null) {
//						MContrassegno c = new MContrassegno();
//						c.setTipo(contrassegno.getTipo());
//						c.setValore(contrassegno.getValore());
//						mOrdine.setContrassegno(c);
//					}
//				}
//				//assicurazione
//				if (spedizione.getAssicurazione()) {
//					SpedizioneAssicurazione filtroAssicurazione = new SpedizioneAssicurazione();
//					filtroAssicurazione.setIdSpedizione(spedizione.getId());
//					SpedizioneAssicurazione assicurazione = managerAssicurazioni.getEntity(filtroAssicurazione, true);
//					if (assicurazione != null) {
//						MAssicurazione a = new MAssicurazione();
//						a.setTipo(assicurazione.getTipo());
//						a.setValore(assicurazione.getValore());
//						mOrdine.setAssicurazione(a);
//					}
//				}
//			}
//			
//		}
//		return mOrdine;
//	}
//	
//	private Indirizzo getDestinatario(MOrdine ordine) {
//		MIndirizzo mDestinatario = ordine.getDestinatario();
//		Indirizzo destinatario = new Indirizzo();
//		destinatario.setCap(mDestinatario.getCap());
//		destinatario.setConsegnaGdo(false);
//		destinatario.setConsegnaAlPiano(false);
//		boolean privato = TipoOrdine.WES.toString().equals(ordine.getTipo());
//		destinatario.setConsegnaPrivato(privato);
//		destinatario.setIndirizzo(mDestinatario.getIndirizzo());
//		destinatario.setLocalita(mDestinatario.getLocalita());
//		destinatario.setNazione(mDestinatario.getNazione().toUpperCase());
//		destinatario.setProvincia(mDestinatario.getProvincia().toUpperCase());
//		destinatario.setRagioneSociale(mDestinatario.getRagionesociale());
//		destinatario.setTelefono(mDestinatario.getTelefono());
//		destinatario.setEmail(mDestinatario.getEmail());
//		Indirizzo esistente = managerIndirizzi.getEntity(destinatario);
//		if (esistente != null) {
//			destinatario = esistente;
//		}
//		return destinatario;
//	}
//	
//	private Indirizzo getMittente(MOrdine ordine) {
//		MIndirizzo mMittente = ordine.getMittente();
//		Indirizzo mittente = new Indirizzo();
//		mittente.setCap(mMittente.getCap());
//		mittente.setConsegnaGdo(false);
//		mittente.setConsegnaAlPiano(false);
//		mittente.setConsegnaPrivato(false);
//		mittente.setIndirizzo(mMittente.getIndirizzo());
//		mittente.setLocalita(mMittente.getLocalita());
//		mittente.setNazione(mMittente.getNazione().toUpperCase());
//		mittente.setProvincia(mMittente.getProvincia().toUpperCase());
//		mittente.setRagioneSociale(mMittente.getRagionesociale());
//		mittente.setTelefono(mMittente.getTelefono());
//		mittente.setEmail(mMittente.getEmail());
//		Indirizzo esistente = managerIndirizzi.getEntity(mittente);
//		if (esistente != null) {
//			mittente = esistente;
//		}
//		return mittente;
//	}

}

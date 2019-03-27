package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import it.ltc.database.model.legacy.model.StatoOrdine;

/**
 * The persistent class for the TestataOrdini database table.
 * 
 */
@Entity
@Table(name = "TestataOrdini")
//@NamedQuery(name = "TestataOrdini.findAll", query = "SELECT t FROM TestataOrdini t")
public class TestataOrdini implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSu");

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdTestaSped", unique = true, nullable = false)
	private int idTestaSped;

	// @Column(name="AggCorr", nullable=false, length=2)
	// private String aggCorr;

	// @Column(name="AggiornatoAS400", length=2)
	// private String aggiornatoAS400;

	@Column(name = "Annodoc")
	private Integer annodoc;

	@Column(name = "AnnoOrdine")
	private Integer annoOrdine;

	// @Column(name="AssUbica", length=2)
	// private String assUbica;

	// @Column(name="Barle", length=3)
	// private String barle;

	// @Column(name="Bloc1", length=1)
	// private String bloc1;

	// @Column(name="Bloccato", length=2)
	// private String bloccato;

	// @Column(name="Catena", length=2)
	// private String catena;

	// @Column(name="Cliparx", length=20)
	// private String cliparx;

	@Column(name = "CodCliente", length = 30, nullable=false)
	private String codCliente;

	/**
	 * Uso il valore di default in inserimento.
	 */
	@Column(name="CodCorriere", insertable=false, nullable=false, length=30)
	private String codCorriere;

	// @Column(name="CodFiliale", length=3)
	// private String codFiliale;

	@Column(name="CodiceClienteCorriere", length=30)
	private String codiceClienteCorriere;

	// @Column(name="CodiceMittente", nullable=false, length=10)
	// private String codiceMittente;

	@Column(name="Corriere", length=30)
	private String corriere;

	// @Column(name="DaRigenerare", length=2)
	// private String daRigenerare;

	@Column(name="DataArrivoFile", columnDefinition="datetime")
	private Date dataArrivoFile;

	@Column(name="DataConsegna", columnDefinition="datetime")
	private Date dataConsegna;

	@Column(name="DataCreazione", insertable=false, updatable=false)
	private Date dataCreazione;

	@Column(name="DataDoc", columnDefinition="datetime")
	private Date dataDoc;

	@Column(name="DataGeneraUscita")
	private Date dataGeneraUscita;

	// @Column(name="DataIni")
	// private Date dataIni;

	@Column(name = "DataOrdine")
	private Date dataOrdine;

	// @Column(name="DocPre", length=50)
	// private String docPre;

	// @Column(name="Email", length=80)
	// private String email;

	// @Column(name="Esito", length=6)
	// private String esito;

	// @Column(name="FileOut", length=50)
	// private String fileOut;

	/**
	 * Durante l'assegnazione viene impostato a "X" se è parziale, a " " se è completa.
	 */
	 @Column(name="Flag1", length=1, insertable=false, nullable=false, columnDefinition="char(1)")
	 private String flag1;

	 @Column(name="GenMovUscita", length=2, nullable=false)
	 private String genMovUscita;

	// @Column(name="Gruppo", length=1)
	// private String gruppo;

	// @Column(name="IdCliAtt")
	// private int idCliAtt;

	@Column(name = "IdDestina", nullable=false)
	private int idDestina;

	@Column(name = "IdTestaCorr", nullable=false)
	private int idTestaCorr;

	// @Column(nullable=false, length=50, insertable=false)
	// private String IDTracking;

	 @Column(name="Imballato", length=2, insertable=false)
	 private String imballato;

	// @Column(name="Importa", length=2)
	// private String importa;

	// @Column(name="Listaorigine", nullable=false, length=21)
	// private String listaorigine;

	// @Column(name="Magazzino", length=3)
	// private String magazzino;

	// @Column(length=10)
	// private String magdes;

	// @Column(name="Marchio")
	// private int marchio;

	// /**
	// * Valori possibili: 'SI' o 'NO' in base alla nazione del destinatario
	// */
	// @Column(name="MembroUE", length=2)
	// private String membroUE;

	@Column(name = "NomeFileArrivo", length = 50)
	private String nomeFileArrivo;

	// @Column(name="NomeFileUscita", length=30)
	// private String nomeFileUscita;

	@Column(name = "Note", length=250)
	private String note;

	// @Column(name="NrColliAutoEti")
	// private int nrColliAutoEti;

	@Column(name = "NrDoc", length = 40)
	private String nrDoc;

	@Column(name="NrLetteraVettura", length=30)
	private String nrLetteraVettura;

	@Column(name="NrLista", nullable=false, length=21)
	private String nrLista;

	@Column(name="NrListaArrivato")
	private Integer nrListaArrivato;

	/**
	 * Come RifOrdineCli
	 */
	@Column(name = "NrOrdine", length = 20)
	private String nrOrdine;

	/**
	 * PrePersist a 'WEBSERVICE'
	 */
	@Column(name = "Operatore", length = 50)
	private String operatore;

	// @Column(name="OraAggAS400")
	// private int oraAggAS400;

	// @Column(name="OraFine")
	// private int oraFine;

	// @Column(name="OraGeneraUscita")
	// private int oraGeneraUscita;

	// @Column(name="OraIni")
	// private int oraIni;

	// @Column(name="Ordineweb", length=20) //Ha un default
	// private String ordineweb;

	 @Column(name="PercAssegnata", nullable=false, insertable=false, columnDefinition="money")
	 private double percAssegnata;

	 @Column(name="PesoTotale", columnDefinition="money", nullable=false)
	 private double pesoTotale;

	 @Column(name="Pezzieffet", nullable=false)
	 private int pezzieffet;

	// @Column(length=20)
	// private String PONumber;

	// @Column(name="Postazione", length=20)
	// private String postazione;

	// @Column(name="Prelevato", length=2)
	// private String prelevato;

	// @Column(name="Prenotato", length=2)
	// private String prenotato;

	// @Column(name="PrenotatoDa", length=10)
	// private String prenotatoDa;

	@Column(name="Priorita", nullable=false)
	private int priorita;

	 @Column(name="QtaAssegnata", nullable=false, insertable= false)
	 private int qtaAssegnata;

	// @Column(name="Qtadiffimb")
	// private int qtadiffimb;

	// @Column(name="Qtaerrata")
	// private int qtaerrata;

	@Column(name="qtaimballata", nullable=false)
	private int qtaimballata;

	 @Column(name="Qtaprelevata", nullable=false)
	 private int qtaprelevata;

	@Column(name = "QtaTotaleSpedire", nullable=false)
	private int qtaTotaleSpedire;
	
	@Column(name="RagioneSocialeDestinatario", length=100)
	private String ragioneSocialeDestinatario;

	@Column(name = "Ragstampe", length = 20)
	private String ragstampe;

	// @Column(name="RegioneDest", length=40)
	// private String regioneDest;

	// @Column(name="Reso", nullable=false, length=2)
	// private String reso;

	// @Column(name="ResoMotivo", length=100)
	// private String resoMotivo;

	@Column(name = "RifOrdineCli", length=20, unique = true, nullable=false)
	private String rifOrdineCli;

	// private int seqstampa;

	// @Column(name="SessioneAss", nullable=false, length=50)
	// private String sessioneAss;

	 @Column(name="SessioneLavoro", length=30)
	 private String sessioneLavoro;

	// @Column(name="Spedizi", length=20)
	// private String spedizi;

	 @Column(name="Stampato", length=2, insertable=false)
	 private String stampato;

	/**
	 * I possibili stati sono:
	 * INSE: appena inserito, è il default. Rimangono appesi qui se non c'è disponibilita'
	 * ERRO: è stata tentata l'assegnazione ma è fallita (mancanza di prodotto)
	 * IMPO: ordine correttamente importato
	 * INIB: in fase di imballo
	 * COIB: imballo completato
	 * DIIB: differenze di imballo
	 * ELAB: termine
	 * INSP: In spedizione
	 * SPED: spedito
	 */
	@Column(name = "Stato", length = 4, nullable=false)
	private String stato;
	
	/**
	 * UT (tutto assegnato) o UP (assegnazione parziale)
	 */
	 @Column(name="StatoUbicazione", nullable=false, insertable=false, length=2, columnDefinition="char(2)")
	 private String statoUbicazione;

	// @Column(name="Tempo")
	// private int tempo;

	// @Column(name="TipoAssegnazione", nullable=false, length=50)
	// private String tipoAssegnazione;

	/**
	 * Rappresenta il tipo di documento che accompagnera' la merce in uscita.
	 */
	@Column(name="TipoDoc", length=20, nullable=false) // e' stato messo un default a DDT
	private String tipoDoc;

	// @Column(name="TipoDocumento", nullable=false, length=50)
	// private String tipoDocumento;

	// @Column(name="TipoGestione", nullable=false, length=30)
	// private String tipoGestione;

	@Column(name="TipoIncasso", nullable=false, length=2)
	private String tipoIncasso;

	/**
	 * Rappresenta la tipologia di ordine, è possibile trovare la lista completa dei tipi nella tabella TestataOrdiniTipo.
	 */
	@Column(name = "TipoOrdine", nullable = false, length = 30)
	private String tipoOrdine;

	// @Column(name="Tipostampa", length=1)
	// private String tipostampa;

	@Column(name = "TipoTrasporto", insertable = false, nullable = false, length = 10)
	private String tipoTrasporto;

	 @Column(name="TotaleColli", nullable=false)
	 private int totaleColli;

	@Column(name = "ValContrassegno", nullable = false, columnDefinition="money")
	private Double valContrassegno;

	@Column(name = "ValoreDoganale", columnDefinition="money")
	private Double valoreDoganale;
	
	@Column(name = "Volumetot", columnDefinition="money", nullable=false)
	private double volumetot;

	// @Column(name="Verificato", length=2)
	// private String verificato;

	@Column(name = "IdMittente", nullable=false)
	private int idMittente;

	public TestataOrdini() {}

	@PrePersist
	public void prePersist() {
		//Attendo un millisecondo per evitare liste duplicate.
		try { Thread.sleep(1); } catch (Exception e) { System.out.println("Impossibile fermare il thread per 1 ms.");}
		GregorianCalendar now = new GregorianCalendar();
		dataArrivoFile = new Date(now.getTimeInMillis());
		annoOrdine = now.get(Calendar.YEAR);
		annodoc = now.get(Calendar.YEAR);
		genMovUscita = "NO";
		// il numero di lista è necessario, se non c'è lo genero.
		if (nrLista == null || nrLista.isEmpty()) {
			nrLista = sdf.format(now.getTime());
		}
		// il raggruppamento stampe è necessario, se non c'è lo imposto uguale al numero di lista.
		if (ragstampe == null || ragstampe.isEmpty()) {
			ragstampe = nrLista;
		}
		nrListaArrivato = 0; // TODO, in teoria sarebbe come un autoincrement
		if (nomeFileArrivo == null || nomeFileArrivo.isEmpty()) nomeFileArrivo = sdf.format(now.getTime());
		// Se non ho specificato l'operatore assumo che sia un servizio.
		if (operatore == null || operatore.isEmpty()) operatore = "SERVIZIO";
		// Priorita', se non valorizzata imposto il default.
		if (priorita <= 0) priorita = 1;
		// Contrassegno
		if (tipoIncasso == null) tipoIncasso = "";
		if (valContrassegno == null) valContrassegno = 0.0;
		if (valoreDoganale == null)	valoreDoganale = 0.0;
		// Corriere, se null imposto a stringa vuota.
		if (corriere == null) corriere = "";
		// Codice cliente per il corriere, se null imposto a stringa vuota.
		if (codiceClienteCorriere == null) codiceClienteCorriere = "";
		if (stato == null) stato = "INSE";
		if (sessioneLavoro == null) sessioneLavoro = "";
		if (tipoDoc == null) tipoDoc = "ORDINE";
	}

	public int getIdTestaSped() {
		return this.idTestaSped;
	}

	public void setIdTestaSped(int idTestaSped) {
		this.idTestaSped = idTestaSped;
	}

	// public String getAggCorr() {
	// return this.aggCorr;
	// }
	//
	// public void setAggCorr(String aggCorr) {
	// this.aggCorr = aggCorr;
	// }
	//
	// public String getAggiornatoAS400() {
	// return this.aggiornatoAS400;
	// }
	//
	// public void setAggiornatoAS400(String aggiornatoAS400) {
	// this.aggiornatoAS400 = aggiornatoAS400;
	// }

	public Integer getAnnodoc() {
		return this.annodoc;
	}

	public void setAnnodoc(Integer annodoc) {
		this.annodoc = annodoc;
	}

	public Integer getAnnoOrdine() {
		return this.annoOrdine;
	}

	public void setAnnoOrdine(Integer annoOrdine) {
		this.annoOrdine = annoOrdine;
	}

	// public String getAssUbica() {
	// return this.assUbica;
	// }
	//
	// public void setAssUbica(String assUbica) {
	// this.assUbica = assUbica;
	// }
	//
	// public String getBarle() {
	// return this.barle;
	// }
	//
	// public void setBarle(String barle) {
	// this.barle = barle;
	// }
	//
	// public String getBloc1() {
	// return this.bloc1;
	// }
	//
	// public void setBloc1(String bloc1) {
	// this.bloc1 = bloc1;
	// }
	//
	// public String getBloccato() {
	// return this.bloccato;
	// }
	//
	// public void setBloccato(String bloccato) {
	// this.bloccato = bloccato;
	// }
	//
	// public String getCatena() {
	// return this.catena;
	// }
	//
	// public void setCatena(String catena) {
	// this.catena = catena;
	// }
	//
	// public String getCliparx() {
	// return this.cliparx;
	// }
	//
	// public void setCliparx(String cliparx) {
	// this.cliparx = cliparx;
	// }

	public String getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodCorriere() {
		return this.codCorriere;
	}

	public void setCodCorriere(String codCorriere) {
		this.codCorriere = codCorriere;
	}

	// public String getCodFiliale() {
	// return this.codFiliale;
	// }
	//
	// public void setCodFiliale(String codFiliale) {
	// this.codFiliale = codFiliale;
	// }

	public String getCodiceClienteCorriere() {
		return this.codiceClienteCorriere;
	}

	public void setCodiceClienteCorriere(String codiceClienteCorriere) {
		this.codiceClienteCorriere = codiceClienteCorriere;
	}

	// public String getCodiceMittente() {
	// return this.codiceMittente;
	// }
	//
	// public void setCodiceMittente(String codiceMittente) {
	// this.codiceMittente = codiceMittente;
	// }

	public String getCorriere() {
		return this.corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	// public String getDaRigenerare() {
	// return this.daRigenerare;
	// }
	//
	// public void setDaRigenerare(String daRigenerare) {
	// this.daRigenerare = daRigenerare;
	// }

	public Date getDataArrivoFile() {
		return this.dataArrivoFile;
	}

	public void setDataArrivoFile(Date dataArrivoFile) {
		this.dataArrivoFile = dataArrivoFile;
	}

	public Date getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	 public Date getDataCreazione() {
		 return this.dataCreazione;
	 }
	
	 public void setDataCreazione(Date dataCreazione) {
		 this.dataCreazione = dataCreazione;
	 }

	public Date getDataDoc() {
		return this.dataDoc;
	}

	public void setDataDoc(Date dataDoc) {
		this.dataDoc = dataDoc;
	}

	 public Date getDataGeneraUscita() {
		 return this.dataGeneraUscita;
	 }
	
	 public void setDataGeneraUscita(Date dataGeneraUscita) {
		 this.dataGeneraUscita = dataGeneraUscita;
	 }

	// public Date getDataIni() {
	// return this.dataIni;
	// }
	//
	// public void setDataIni(Date dataIni) {
	// this.dataIni = dataIni;
	// }

	public Date getDataOrdine() {
		return this.dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	// public String getDocPre() {
	// return this.docPre;
	// }
	//
	// public void setDocPre(String docPre) {
	// this.docPre = docPre;
	// }
	//
	// public String getEmail() {
	// return this.email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// public String getEsito() {
	// return this.esito;
	// }
	//
	// public void setEsito(String esito) {
	// this.esito = esito;
	// }

	// public String getFileOut() {
	// return this.fileOut;
	// }
	//
	// public void setFileOut(String fileOut) {
	// this.fileOut = fileOut;
	// }
	//
	 public String getFlag1() {
		 return this.flag1;
	 }
	
	 public void setFlag1(String flag1) {
		 this.flag1 = flag1;
	 }

	 public String getGenMovUscita() {
		 return this.genMovUscita;
	 }
	
	 public void setGenMovUscita(String genMovUscita) {
		 this.genMovUscita = genMovUscita;
	 }

	// public String getGruppo() {
	// return this.gruppo;
	// }
	//
	// public void setGruppo(String gruppo) {
	// this.gruppo = gruppo;
	// }
	//
	// public int getIdCliAtt() {
	// return this.idCliAtt;
	// }
	//
	// public void setIdCliAtt(int idCliAtt) {
	// this.idCliAtt = idCliAtt;
	// }

	public int getIdDestina() {
		return this.idDestina;
	}

	public void setIdDestina(int idDestina) {
		this.idDestina = idDestina;
	}

	// public String getIDTracking() {
	// return this.IDTracking;
	// }
	//
	// public void setIDTracking(String IDTracking) {
	// this.IDTracking = IDTracking;
	// }

	 public String getImballato() {
		 return this.imballato;
	 }
	
	 public void setImballato(String imballato) {
		 this.imballato = imballato;
	 }

	// public String getImporta() {
	// return this.importa;
	// }
	//
	// public void setImporta(String importa) {
	// this.importa = importa;
	// }

	// public String getListaorigine() {
	// return this.listaorigine;
	// }
	//
	// public void setListaorigine(String listaorigine) {
	// this.listaorigine = listaorigine;
	// }

	// public String getMagazzino() {
	// return this.magazzino;
	// }
	//
	// public void setMagazzino(String magazzino) {
	// this.magazzino = magazzino;
	// }
	//
	// public String getMagdes() {
	// return this.magdes;
	// }
	//
	// public void setMagdes(String magdes) {
	// this.magdes = magdes;
	// }

	// public int getMarchio() {
	// return this.marchio;
	// }
	//
	// public void setMarchio(int marchio) {
	// this.marchio = marchio;
	// }

	// public String getMembroUE() {
	// return this.membroUE;
	// }
	//
	// public void setMembroUE(String membroUE) {
	// this.membroUE = membroUE;
	// }

	public int getIdTestaCorr() {
		return idTestaCorr;
	}

	public void setIdTestaCorr(int idTestaCorr) {
		this.idTestaCorr = idTestaCorr;
	}

	public String getNomeFileArrivo() {
		return this.nomeFileArrivo;
	}

	public void setNomeFileArrivo(String nomeFileArrivo) {
		this.nomeFileArrivo = nomeFileArrivo;
	}
	//
	// public String getNomeFileUscita() {
	// return this.nomeFileUscita;
	// }
	//
	// public void setNomeFileUscita(String nomeFileUscita) {
	// this.nomeFileUscita = nomeFileUscita;
	// }

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	// public int getNrColliAutoEti() {
	// return this.nrColliAutoEti;
	// }
	//
	// public void setNrColliAutoEti(int nrColliAutoEti) {
	// this.nrColliAutoEti = nrColliAutoEti;
	// }

	public String getNrDoc() {
		return this.nrDoc;
	}

	public void setNrDoc(String nrDoc) {
		this.nrDoc = nrDoc;
	}

	public String getNrLetteraVettura() {
		return this.nrLetteraVettura;
	}

	public void setNrLetteraVettura(String nrLetteraVettura) {
		this.nrLetteraVettura = nrLetteraVettura;
	}

	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

	public Integer getNrListaArrivato() {
		return this.nrListaArrivato;
	}

	public void setNrListaArrivato(Integer nrListaArrivato) {
		this.nrListaArrivato = nrListaArrivato;
	}

	public String getNrOrdine() {
		return this.nrOrdine;
	}

	public void setNrOrdine(String nrOrdine) {
		this.nrOrdine = nrOrdine;
	}

	public String getOperatore() {
		return this.operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	//
	// public int getOraAggAS400() {
	// return this.oraAggAS400;
	// }
	//
	// public void setOraAggAS400(int oraAggAS400) {
	// this.oraAggAS400 = oraAggAS400;
	// }
	//
	// public int getOraFine() {
	// return this.oraFine;
	// }
	//
	// public void setOraFine(int oraFine) {
	// this.oraFine = oraFine;
	// }
	//
	// public int getOraGeneraUscita() {
	// return this.oraGeneraUscita;
	// }
	//
	// public void setOraGeneraUscita(int oraGeneraUscita) {
	// this.oraGeneraUscita = oraGeneraUscita;
	// }
	//
	// public int getOraIni() {
	// return this.oraIni;
	// }
	//
	// public void setOraIni(int oraIni) {
	// this.oraIni = oraIni;
	// }
	//
	// public String getOrdineweb() {
	// return this.ordineweb;
	// }
	//
	// public void setOrdineweb(String ordineweb) {
	// this.ordineweb = ordineweb;
	// }
	//
	 public double getPercAssegnata() {
		 return this.percAssegnata;
	 }
	
	 public void setPercAssegnata(double percAssegnata) {
		 this.percAssegnata = percAssegnata;
	 }
	
	 public Double getPesoTotale() {
		 return this.pesoTotale;
	 }
	
	 public void setPesoTotale(Double pesoTotale) {
		 this.pesoTotale = pesoTotale;
	 }

	 public int getPezzieffet() {
		 return this.pezzieffet;
	 }
	
	 public void setPezzieffet(int pezzieffet) {
		 this.pezzieffet = pezzieffet;
	 }

	// public String getPONumber() {
	// return this.PONumber;
	// }
	//
	// public void setPONumber(String PONumber) {
	// this.PONumber = PONumber;
	// }
	//
	// public String getPostazione() {
	// return this.postazione;
	// }
	//
	// public void setPostazione(String postazione) {
	// this.postazione = postazione;
	// }
	//
	// public String getPrelevato() {
	// return this.prelevato;
	// }
	//
	// public void setPrelevato(String prelevato) {
	// this.prelevato = prelevato;
	// }
	//
	// public String getPrenotato() {
	// return this.prenotato;
	// }
	//
	// public void setPrenotato(String prenotato) {
	// this.prenotato = prenotato;
	// }
	//
	// public String getPrenotatoDa() {
	// return this.prenotatoDa;
	// }
	//
	// public void setPrenotatoDa(String prenotatoDa) {
	// this.prenotatoDa = prenotatoDa;
	// }

	public int getPriorita() {
		return this.priorita;
	}

	public void setPriorita(int priorita) {
		this.priorita = priorita;
	}

	 public int getQtaAssegnata() {
		 return this.qtaAssegnata;
	 }
	
	 public void setQtaAssegnata(int qtaAssegnata) {
		 this.qtaAssegnata = qtaAssegnata;
	 }
	//
	// public int getQtadiffimb() {
	// return this.qtadiffimb;
	// }
	//
	// public void setQtadiffimb(int qtadiffimb) {
	// this.qtadiffimb = qtadiffimb;
	// }
	//
	// public int getQtaerrata() {
	// return this.qtaerrata;
	// }
	//
	// public void setQtaerrata(int qtaerrata) {
	// this.qtaerrata = qtaerrata;
	// }
	//
	public int getQtaimballata() {
		return this.qtaimballata;
	}

	public void setQtaimballata(int qtaimballata) {
		this.qtaimballata = qtaimballata;
	}
	
	 public int getQtaprelevata() {
		 return this.qtaprelevata;
	 }
	
	 public void setQtaprelevata(int qtaprelevata) {
		 this.qtaprelevata = qtaprelevata;
	 }

	public int getQtaTotaleSpedire() {
		return this.qtaTotaleSpedire;
	}

	public void setQtaTotaleSpedire(int qtaTotaleSpedire) {
		this.qtaTotaleSpedire = qtaTotaleSpedire;
	}

	public String getRagioneSocialeDestinatario() {
		return ragioneSocialeDestinatario;
	}

	public void setRagioneSocialeDestinatario(String ragioneSocialeDestinatario) {
		this.ragioneSocialeDestinatario = ragioneSocialeDestinatario;
	}

	public String getRagstampe() {
		return this.ragstampe;
	}

	public void setRagstampe(String ragstampe) {
		this.ragstampe = ragstampe;
	}

	// public String getRegioneDest() {
	// return this.regioneDest;
	// }
	//
	// public void setRegioneDest(String regioneDest) {
	// this.regioneDest = regioneDest;
	// }
	//
	// public String getReso() {
	// return this.reso;
	// }
	//
	// public void setReso(String reso) {
	// this.reso = reso;
	// }
	//
	// public String getResoMotivo() {
	// return this.resoMotivo;
	// }
	//
	// public void setResoMotivo(String resoMotivo) {
	// this.resoMotivo = resoMotivo;
	// }

	public String getRifOrdineCli() {
		return this.rifOrdineCli;
	}

	public void setRifOrdineCli(String rifOrdineCli) {
		this.rifOrdineCli = rifOrdineCli;
	}

	// public int getSeqstampa() {
	// return this.seqstampa;
	// }
	//
	// public void setSeqstampa(int seqstampa) {
	// this.seqstampa = seqstampa;
	// }
	//
	// public String getSessioneAss() {
	// return this.sessioneAss;
	// }
	//
	// public void setSessioneAss(String sessioneAss) {
	// this.sessioneAss = sessioneAss;
	// }
	//
	 public String getSessioneLavoro() {
		 return this.sessioneLavoro;
	 }
	
	 public void setSessioneLavoro(String sessioneLavoro) {
		 this.sessioneLavoro = sessioneLavoro;
	 }
	//
	// public String getSpedizi() {
	// return this.spedizi;
	// }
	//
	// public void setSpedizi(String spedizi) {
	// this.spedizi = spedizi;
	// }

	 public String getStampato() {
		 return this.stampato;
	 }
	
	 public void setStampato(String stampato) {
		 this.stampato = stampato;
	 }

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	/**
	 * Converte lo stato dell'ordine in un valore enum.
	 * @return
	 */
	public StatoOrdine getStatoOrdine() {
		StatoOrdine eStato;
		try {
			eStato = StatoOrdine.valueOf(stato);
		} catch (Exception e) {
			eStato = StatoOrdine.NONE;
		}
		return eStato;
	}

	 public String getStatoUbicazione() {
		 return this.statoUbicazione;
	 }
	
	 public void setStatoUbicazione(String statoUbicazione) {
		 this.statoUbicazione = statoUbicazione;
	 }
	//
	// public int getTempo() {
	// return this.tempo;
	// }
	//
	// public void setTempo(int tempo) {
	// this.tempo = tempo;
	// }
	//
	// public String getTipoAssegnazione() {
	// return this.tipoAssegnazione;
	// }
	//
	// public void setTipoAssegnazione(String tipoAssegnazione) {
	// this.tipoAssegnazione = tipoAssegnazione;
	// }

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	// public String getTipoDocumento() {
	// return this.tipoDocumento;
	// }
	//
	// public void setTipoDocumento(String tipoDocumento) {
	// this.tipoDocumento = tipoDocumento;
	// }
	//
	// public String getTipoGestione() {
	// return this.tipoGestione;
	// }
	//
	// public void setTipoGestione(String tipoGestione) {
	// this.tipoGestione = tipoGestione;
	// }

	public String getTipoIncasso() {
		return this.tipoIncasso;
	}

	public void setTipoIncasso(String tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

	public String getTipoOrdine() {
		return this.tipoOrdine;
	}

	public void setTipoOrdine(String tipoOrdine) {
		this.tipoOrdine = tipoOrdine;
	}

	// public String getTipostampa() {
	// return this.tipostampa;
	// }
	//
	// public void setTipostampa(String tipostampa) {
	// this.tipostampa = tipostampa;
	// }

	public String getTipoTrasporto() {
		return this.tipoTrasporto;
	}

	public void setTipoTrasporto(String tipoTrasporto) {
		this.tipoTrasporto = tipoTrasporto;
	}

	 public int getTotaleColli() {
		 return this.totaleColli;
	 }
	
	 public void setTotaleColli(int totaleColli) {
		 this.totaleColli = totaleColli;
	 }

	public Double getValContrassegno() {
		return this.valContrassegno;
	}

	public void setValContrassegno(Double valContrassegno) {
		this.valContrassegno = valContrassegno;
	}

	public Double getValoreDoganale() {
		return this.valoreDoganale;
	}

	public void setValoreDoganale(Double valoreDoganale) {
		this.valoreDoganale = valoreDoganale;
	}

	// public String getVerificato() {
	// return this.verificato;
	// }
	//
	// public void setVerificato(String verificato) {
	// this.verificato = verificato;
	// }

	public double getVolumetot() {
		return volumetot;
	}

	public void setVolumetot(double volumetot) {
		this.volumetot = volumetot;
	}

	public int getIdMittente() {
		return idMittente;
	}

	public void setIdMittente(int idMittente) {
		this.idMittente = idMittente;
	}

}
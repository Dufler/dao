package it.ltc.database.model.legacyltc;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import it.ltc.utility.miscellanea.time.DateConverter;


/**
 * The persistent class for the EventiDoc database table.
 * 
 */
@Entity
@NamedQuery(name="EventiDoc.findAll", query="SELECT e FROM EventiDoc e")
public class EventiDoc implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IdEvtDoc")
	private int idEvtDoc;

//	@Column(name="CodCostoUF")
//	private String codCostoUF;

	@Column(name="CodOpe")
	private int codOpe;

	/**
	 * Qui verrà inserito un valore che identifica il client da cui è stato generato l'evento.
	 */
	@Column(name="ComputerEVT", length=20)
	private String computerEVT;

//	@Column(name="Confermato")
//	private int confermato;

//	@Column(name="ConvDesc")
//	private String convDesc;

//	@Column(name="ConvEsc")
//	private String convEsc;

	@Column(name="DataFine")
	private Timestamp dataFine;

	@Column(name="DataInizio")
	private Timestamp dataInizio;

//	@Column(name="DataOraCreazione")
//	private Timestamp dataOraCreazione;

//	@Column(name="FlagElab")
//	private String flagElab;

//	@Column(name="ForzatoIni")
//	private String forzatoIni;

//	@Column(name="Giorni")
//	private int giorni;

	@Column(name="IdCosti")
	private int idCosti;

	@Column(name="IdDoc")
	private int idDoc;

	@Column(name="IdMittente")
	private int idMittente;

	@Column(name="IdSettore")
	private int idSettore;

	@Column(name="IdtipoAttività")
	private int idtipoAttività;

//	@Column(name="LavoroUF")
//	private int lavoroUF;

//	@Column(name="Motivo")
//	private String motivo;

//	@Column(name="MotivoUF")
//	private String motivoUF;

	@Column(name="Note")
	private String note;

//	@Column(name="NrDocCliente")
//	private int nrDocCliente;

	@Column(name="NrDocClienteS")
	private String nrDocClienteS;

	@Column(name="OraFine")
	private int oraFine;

//	@Column(name="OraIngAtt")
//	private int oraIngAtt;

	@Column(name="OraInizio")
	private int oraInizio;

//	@Column(name="OraUF")
//	private int oraUF;

//	@Column(name="OraUscAtt")
//	private int oraUscAtt;

//	@Column(name="QtaDiffimb")
//	private int qtaDiffimb;

//	private int qtaerrata;

	@Column(name="Quantità")
	private int quantità;

//	@Column(name="Ripartito")
//	private int ripartito;

	@Column(name="Secondi")
	private int secondi;

//	@Column(name="SecTras")
//	private int secTras;

//	@Column(name="SecUF")
//	private int secUF;

//	@Column(name="SecUtiRip")
//	private int secUtiRip;

//	@Column(name="Sessione")
//	private String sessione;

	/**
	 * INI o FIN
	 */
	@Column(name="StatoEvtDoc")
	private String statoEvtDoc;

	/**
	 * Durata dell'evento rappresentata come stringa nel formato hh:mm:ss
	 */
	private String TEmpoTras;

//	@Column(name="TipoEvtDoc")
//	private String tipoEvtDoc;

//	@Column(name="TipoUF")
//	private String tipoUF;

//	@Column(name="UltDescSos")
//	private String ultDescSos;
	
	/**
	 * Campo inserito per distringuere in maniera facile gli eventi di ingresso/uscita dagli altri.
	 */
	@Column(name="IngressoUscita", columnDefinition="CHAR", length=2)
	private String ingressoUscita;

	public EventiDoc() {}
	
	@PrePersist
	public void prePersist() {
		if (note == null)
			note = "";
		//Ripulisco l'ora, i minuti e i secondi dalla data di inizio e di fine e li scrivo sui campi ora come interi.
		if (dataInizio == null)
			dataInizio = new Timestamp(new Date().getTime());
		oraInizio = DateConverter.getOraComeIntero(dataInizio);
		dataInizio = DateConverter.ripulisciTimestap(dataInizio);
		if (dataFine != null) {
			oraFine = DateConverter.getOraComeIntero(dataFine);
			dataFine = DateConverter.ripulisciTimestap(dataFine);
			calcolaDurata();
		}
	}
	
	@PreUpdate
	public void preUpdate() {
		if (note == null)
			note = "";
		//Ripulisco l'ora, i minuti e i secondi dalla data di inizio e di fine e li scrivo sui campi ora come interi.
		if (dataFine != null) {
			oraFine = DateConverter.getOraComeIntero(dataFine);
			dataFine = DateConverter.ripulisciTimestap(dataFine);
			calcolaDurata();
		}
	}
	
	/**
	 * Calcola la durata in secondi tra la data d'inizio e di fine.
	 * Tale valore viene riportato nei campi "Secondi" e "TEmpoTras".
	 */
	private void calcolaDurata() {
		secondi = 0;
		TEmpoTras = null;
		if (dataInizio != null && dataFine != null) {
			GregorianCalendar giornoInizio = DateConverter.ottieniDataPrecisa(dataInizio, oraInizio);
			GregorianCalendar giornoFine = DateConverter.ottieniDataPrecisa(dataFine, oraFine);
			secondi = (int) ((giornoFine.getTimeInMillis() - giornoInizio.getTimeInMillis()) / 1000);
			Date tempoTrascorso = new Date();
			tempoTrascorso.setTime(secondi);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			TEmpoTras = sdf.format(tempoTrascorso);
		}
	}
	
//	private GregorianCalendar ottieniDataPrecisa(Timestamp data, int ore) {
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTimeInMillis(data.getTime());
//		String tempo = Integer.toString(ore);
//		int index = tempo.length() == 5 ? 1 : 2;
//		int ora = Integer.parseInt(tempo.substring(0, index));
//		int minuti = Integer.parseInt(tempo.substring(index, index + 2));
//		int secondi = Integer.parseInt(tempo.substring(index + 2));
//		gc.set(Calendar.HOUR_OF_DAY, ora);
//		gc.set(Calendar.MINUTE, minuti);
//		gc.set(Calendar.SECOND, secondi);
//		gc.set(Calendar.MILLISECOND, 0);
//		return gc;
//	}
//	
//	private int getOraComeIntero(Timestamp data) {
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTimeInMillis(data.getTime());
//		int ora = gc.get(Calendar.HOUR_OF_DAY);
//		int minuti = gc.get(Calendar.MINUTE);
//		int secondi = gc.get(Calendar.SECOND);
//		int totale = ora * 10000 + minuti * 100 + secondi;
//		return totale;
//	}
//	
//	private Timestamp ripulisciTimestap(Timestamp data) {
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.setTimeInMillis(data.getTime());
//		gc.set(Calendar.HOUR_OF_DAY, 0);
//		gc.set(Calendar.MINUTE, 0);
//		gc.set(Calendar.SECOND, 0);
//		gc.set(Calendar.MILLISECOND, 0);
//		return new Timestamp(gc.getTimeInMillis());
//	}

	public int getIdEvtDoc() {
		return this.idEvtDoc;
	}

	public void setIdEvtDoc(int idEvtDoc) {
		this.idEvtDoc = idEvtDoc;
	}

//	public String getCodCostoUF() {
//		return this.codCostoUF;
//	}
//
//	public void setCodCostoUF(String codCostoUF) {
//		this.codCostoUF = codCostoUF;
//	}

	public int getCodOpe() {
		return this.codOpe;
	}

	public void setCodOpe(int codOpe) {
		this.codOpe = codOpe;
	}

	public String getComputerEVT() {
		return this.computerEVT;
	}

	public void setComputerEVT(String computerEVT) {
		this.computerEVT = computerEVT;
	}

//	public int getConfermato() {
//		return this.confermato;
//	}
//
//	public void setConfermato(int confermato) {
//		this.confermato = confermato;
//	}
//
//	public String getConvDesc() {
//		return this.convDesc;
//	}
//
//	public void setConvDesc(String convDesc) {
//		this.convDesc = convDesc;
//	}
//
//	public String getConvEsc() {
//		return this.convEsc;
//	}
//
//	public void setConvEsc(String convEsc) {
//		this.convEsc = convEsc;
//	}

	public Timestamp getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

//	public Timestamp getDataOraCreazione() {
//		return this.dataOraCreazione;
//	}
//
//	public void setDataOraCreazione(Timestamp dataOraCreazione) {
//		this.dataOraCreazione = dataOraCreazione;
//	}
//
//	public String getFlagElab() {
//		return this.flagElab;
//	}
//
//	public void setFlagElab(String flagElab) {
//		this.flagElab = flagElab;
//	}
//
//	public String getForzatoIni() {
//		return this.forzatoIni;
//	}
//
//	public void setForzatoIni(String forzatoIni) {
//		this.forzatoIni = forzatoIni;
//	}
//
//	public int getGiorni() {
//		return this.giorni;
//	}
//
//	public void setGiorni(int giorni) {
//		this.giorni = giorni;
//	}

	public int getIdCosti() {
		return this.idCosti;
	}

	public void setIdCosti(int idCosti) {
		this.idCosti = idCosti;
	}

	public int getIdDoc() {
		return this.idDoc;
	}

	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}

	public int getIdMittente() {
		return this.idMittente;
	}

	public void setIdMittente(int idMittente) {
		this.idMittente = idMittente;
	}

	public int getIdSettore() {
		return this.idSettore;
	}

	public void setIdSettore(int idSettore) {
		this.idSettore = idSettore;
	}

	public int getIdtipoAttività() {
		return this.idtipoAttività;
	}

	public void setIdtipoAttività(int idtipoAttività) {
		this.idtipoAttività = idtipoAttività;
	}

//	public int getLavoroUF() {
//		return this.lavoroUF;
//	}
//
//	public void setLavoroUF(int lavoroUF) {
//		this.lavoroUF = lavoroUF;
//	}
//
//	public String getMotivo() {
//		return this.motivo;
//	}
//
//	public void setMotivo(String motivo) {
//		this.motivo = motivo;
//	}
//
//	public String getMotivoUF() {
//		return this.motivoUF;
//	}
//
//	public void setMotivoUF(String motivoUF) {
//		this.motivoUF = motivoUF;
//	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

//	public int getNrDocCliente() {
//		return this.nrDocCliente;
//	}
//
//	public void setNrDocCliente(int nrDocCliente) {
//		this.nrDocCliente = nrDocCliente;
//	}

	public String getNrDocClienteS() {
		return this.nrDocClienteS;
	}

	public void setNrDocClienteS(String nrDocClienteS) {
		this.nrDocClienteS = nrDocClienteS;
	}

	public int getOraFine() {
		return this.oraFine;
	}

	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}

//	public int getOraIngAtt() {
//		return this.oraIngAtt;
//	}
//
//	public void setOraIngAtt(int oraIngAtt) {
//		this.oraIngAtt = oraIngAtt;
//	}

	public int getOraInizio() {
		return this.oraInizio;
	}

	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}

//	public int getOraUF() {
//		return this.oraUF;
//	}
//
//	public void setOraUF(int oraUF) {
//		this.oraUF = oraUF;
//	}
//
//	public int getOraUscAtt() {
//		return this.oraUscAtt;
//	}
//
//	public void setOraUscAtt(int oraUscAtt) {
//		this.oraUscAtt = oraUscAtt;
//	}
//
//	public int getQtaDiffimb() {
//		return this.qtaDiffimb;
//	}
//
//	public void setQtaDiffimb(int qtaDiffimb) {
//		this.qtaDiffimb = qtaDiffimb;
//	}
//
//	public int getQtaerrata() {
//		return this.qtaerrata;
//	}
//
//	public void setQtaerrata(int qtaerrata) {
//		this.qtaerrata = qtaerrata;
//	}

	public int getQuantità() {
		return this.quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

//	public int getRipartito() {
//		return this.ripartito;
//	}
//
//	public void setRipartito(int ripartito) {
//		this.ripartito = ripartito;
//	}

	public int getSecondi() {
		return this.secondi;
	}

	public void setSecondi(int secondi) {
		this.secondi = secondi;
	}

//	public int getSecTras() {
//		return this.secTras;
//	}
//
//	public void setSecTras(int secTras) {
//		this.secTras = secTras;
//	}
//
//	public int getSecUF() {
//		return this.secUF;
//	}
//
//	public void setSecUF(int secUF) {
//		this.secUF = secUF;
//	}

//	public int getSecUtiRip() {
//		return this.secUtiRip;
//	}
//
//	public void setSecUtiRip(int secUtiRip) {
//		this.secUtiRip = secUtiRip;
//	}
//
//	public String getSessione() {
//		return this.sessione;
//	}
//
//	public void setSessione(String sessione) {
//		this.sessione = sessione;
//	}

	public String getStatoEvtDoc() {
		return this.statoEvtDoc;
	}

	public void setStatoEvtDoc(String statoEvtDoc) {
		this.statoEvtDoc = statoEvtDoc;
	}

	public String getTEmpoTras() {
		return this.TEmpoTras;
	}

	public void setTEmpoTras(String TEmpoTras) {
		this.TEmpoTras = TEmpoTras;
	}

	public String getIngressoUscita() {
		return ingressoUscita;
	}

	public void setIngressoUscita(String ingressoUscita) {
		this.ingressoUscita = ingressoUscita;
	}

//	public String getTipoEvtDoc() {
//		return this.tipoEvtDoc;
//	}
//
//	public void setTipoEvtDoc(String tipoEvtDoc) {
//		this.tipoEvtDoc = tipoEvtDoc;
//	}
//
//	public String getTipoUF() {
//		return this.tipoUF;
//	}
//
//	public void setTipoUF(String tipoUF) {
//		this.tipoUF = tipoUF;
//	}
//
//	public String getUltDescSos() {
//		return this.ultDescSos;
//	}
//
//	public void setUltDescSos(String ultDescSos) {
//		this.ultDescSos = ultDescSos;
//	}

}
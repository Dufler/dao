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

import it.ltc.utility.miscellanea.string.StringUtility;


/**
 * The persistent class for the RigaCorr database table.
 * 
 */
@Entity
@Table(name="RigaCorr")
//@NamedQuery(name="RigaCorr.findAll", query="SELECT r FROM RigaCorr r")
public class RigaCorr implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdColliCorr", unique=true, nullable=false)
	private int idColliCorr;

	@Column(name="AnnoSpe")
	private int annoSpe;

//	@Column(name="Annullato", length=1)
//	private String annullato;
//
	@Column(name="CodMittente", length=30)
	private String codMittente;

	/**
	 * Stesso valore di NrSpedi
	 */
	@Column(name="CodRaggruppamento", nullable=false)
	private int codRaggruppamento;

//	@Column(name="Creazione")
//	private Date creazione;

	/**
	 * Da ColliImballo prendere CodFormato
	 */
	@Column(name="Formato", length=3)
	private String formato;

	/**
	 * Da ColliImballo prendere NrIdCollo
	 */
	@Column(name="NrCollo")
	private int nrCollo;

	/**
	 * come per nrCollo ma con padding a sinistra di "0" per arrivare a 7 caratteri.
	 */
	@Column(name="NrColloStr", length=35)
	private String nrColloStr;

	@Column(name="NrLista", length=21)
	private String nrLista;

//	@Column(name="NrSerie")
//	private int nrSerie;

	/**
	 * Come su TestaCorr
	 */
	@Column(name="NrSpedi")
	private int nrSpedi;

//	@Column(name="OperatorePar")
//	private int operatorePar;

	/**
	 * Da ColliImballo
	 */
	@Column(name="Peso", columnDefinition="money")
	private Double peso;

	/**
	 * Da ColliImballo prendere pezziCollo
	 */
	@Column(name="Pezzi")
	private int pezzi;

	/**
	 * La compongo io al momento dell'inserimento.
	 */
	@Column(name="StringaBartolini", nullable=false, length=65, columnDefinition="char(65)")
	private String stringaBartolini;

//	@Column(name="TipoRecord", length=1)
//	private String tipoRecord;

	/**
	 * Da ColliImballo
	 */
	@Column(name="Volume", columnDefinition="money")
	private Double volume;

	public RigaCorr() {}
	
	@PrePersist
	public void prePersist() {
		GregorianCalendar today = new GregorianCalendar();
		annoSpe = today.get(Calendar.YEAR);
		stringaBartolini = generaStringaBartolini();
	}

	public int getIdColliCorr() {
		return this.idColliCorr;
	}

	public void setIdColliCorr(int idColliCorr) {
		this.idColliCorr = idColliCorr;
	}

//	public int getAnnoSpe() {
//		return this.annoSpe;
//	}
//
//	public void setAnnoSpe(int annoSpe) {
//		this.annoSpe = annoSpe;
//	}

//	public String getAnnullato() {
//		return this.annullato;
//	}
//
//	public void setAnnullato(String annullato) {
//		this.annullato = annullato;
//	}
//
	public String getCodMittente() {
		return this.codMittente;
	}

	public void setCodMittente(String codMittente) {
		this.codMittente = codMittente;
	}

	public int getCodRaggruppamento() {
		return this.codRaggruppamento;
	}

	public void setCodRaggruppamento(int codRaggruppamento) {
		this.codRaggruppamento = codRaggruppamento;
	}

//	public Date getCreazione() {
//		return this.creazione;
//	}
//
//	public void setCreazione(Date creazione) {
//		this.creazione = creazione;
//	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public int getNrCollo() {
		return this.nrCollo;
	}

	public void setNrCollo(int nrCollo) {
		this.nrCollo = nrCollo;
	}

	public String getNrColloStr() {
		return this.nrColloStr;
	}

	public void setNrColloStr(String nrColloStr) {
		this.nrColloStr = nrColloStr;
	}

	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

//	public int getNrSerie() {
//		return this.nrSerie;
//	}
//
//	public void setNrSerie(int nrSerie) {
//		this.nrSerie = nrSerie;
//	}

	public int getNrSpedi() {
		return this.nrSpedi;
	}

	public void setNrSpedi(int nrSpedi) {
		this.nrSpedi = nrSpedi;
	}

//	public int getOperatorePar() {
//		return this.operatorePar;
//	}
//
//	public void setOperatorePar(int operatorePar) {
//		this.operatorePar = operatorePar;
//	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public String getStringaBartolini() {
		return this.stringaBartolini;
	}

	public void setStringaBartolini(String stringaBartolini) {
		this.stringaBartolini = stringaBartolini;
	}

//	public String getTipoRecord() {
//		return this.tipoRecord;
//	}
//
//	public void setTipoRecord(String tipoRecord) {
//		this.tipoRecord = tipoRecord;
//	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {	
		this.volume = volume;
	}
	
	public String generaStringaBartolini() {
		StringUtility utility = new StringUtility();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		StringBuilder sb = new StringBuilder();
		sb.append(" "); //1 - flag annullamento
		sb.append(utility.getFormattedString(codMittente, 7, "0", true)); //7 - codice cliente mittente
		sb.append("026"); //3 - punto operativo partenza - TODO - va preso dalla tabella corrieri, modifica da fare.
		sb.append(sdf.format(new Date())); //4 - anno spedizione
		sb.append("00"); //2 - numero serie - TODO - in teoria 00 dovrebbe andare sempre bene, va verificato.
		sb.append(utility.getFormattedString(nrSpedi, 7)); //7 - numero spedizione
		sb.append(" "); //1 - tipo record
		sb.append(utility.getFormattedString(nrColloStr, 35)); //35 - barcode collo
		return sb.toString();
	}

}
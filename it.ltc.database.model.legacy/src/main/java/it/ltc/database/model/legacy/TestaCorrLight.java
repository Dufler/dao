package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TestaCorr")
public class TestaCorrLight implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdTestaCor", unique=true, nullable=false)
	private int idTestaCor;

	@Column(name="AnnoSpe")
	private int annoSpe;

	@Column(name="Cap", length=10)
	private String cap;

	/**
	 * Codice cliente presso il corriere.
	 */
	@Column(name="CodMittente", length=30)
	private String codMittente;

	@Column(name="Contrassegno", columnDefinition="money")
	private Double contrassegno;

	@Column(name="Corriere", length=30)
	private String corriere;

	@Column(name="DataGenerazione", columnDefinition="datetime", nullable=false)
	private Date dataGenerazione;

	@Column(name="DataSpe")
	private int dataSpe;

	@Column(name="Indirizzo", length=250)
	private String indirizzo;

	@Column(name="Localita", length=50)
	private String localita;

	@Column(name="MittenteAlfa", length=20, nullable=false)
	private String mittenteAlfa;

	@Column(name="MittenteNum", nullable=false)
	private int mittenteNum;

	@Column(name="Nazione", length=3)
	private String nazione;

	@Column(name="Note1", length=35)
	private String note1;

	@Column(name="Note2", length=35)
	private String note2;

	@Column(name="NrColli")
	private int nrColli;

	@Column(name="NrLista", length=21)
	private String nrLista;

	@Column(name="NrSerie")
	private int nrSerie;

	@Column(name="NrSpedi")
	private int nrSpedi;

	@Column(name="Peso", columnDefinition="money", nullable=false)
	private double peso;

	@Column(name="Pezzi", nullable=false)
	private int pezzi;

	@Column(name="Provincia", length=2)
	private String provincia;

	@Column(name="RagSocDest", length=100)
	private String ragSocDest;

	@Column(name="RagSocEst", length=50)
	private String ragSocEst;

	@Column(name="Servizio", length=3)
	private String servizio;

	@Column(name="TipoIncasso", length=2, columnDefinition="char(2)")
	private String tipoIncasso;

	@Column(name="Trasmesso", nullable=false)
	private int trasmesso;

	@Column(name="ValutaIncasso", length=3)
	private String valutaIncasso;

	@Column(name="Volume", columnDefinition="money", nullable=false)
	private double volume;

	public TestaCorrLight() {}

	public int getIdTestaCor() {
		return this.idTestaCor;
	}

	public void setIdTestaCor(int idTestaCor) {
		this.idTestaCor = idTestaCor;
	}

	public int getAnnoSpe() {
		return this.annoSpe;
	}

	public void setAnnoSpe(int annoSpe) {
		this.annoSpe = annoSpe;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodMittente() {
		return this.codMittente;
	}

	public void setCodMittente(String codMittente) {
		this.codMittente = codMittente;
	}
	
	public Double getContrassegno() {
		return this.contrassegno;
	}

	public void setContrassegno(Double contrassegno) {
		this.contrassegno = contrassegno;
	}

	public String getCorriere() {
		return this.corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	public Date getDataGenerazione() {
		return this.dataGenerazione;
	}

	public void setDataGenerazione(Date dataGenerazione) {
		this.dataGenerazione = dataGenerazione;
	}

	public int getDataSpe() {
		return this.dataSpe;
	}

	public void setDataSpe(int dataSpe) {
		this.dataSpe = dataSpe;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getMittenteAlfa() {
		return this.mittenteAlfa;
	}

	public void setMittenteAlfa(String mittenteAlfa) {
		this.mittenteAlfa = mittenteAlfa;
	}

	public int getMittenteNum() {
		return this.mittenteNum;
	}

	public void setMittenteNum(int mittenteNum) {
		this.mittenteNum = mittenteNum;
	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public int getNrColli() {
		return this.nrColli;
	}

	public void setNrColli(int nrColli) {
		this.nrColli = nrColli;
	}

	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

	public int getNrSerie() {
		return this.nrSerie;
	}

	public void setNrSerie(int nrSerie) {
		this.nrSerie = nrSerie;
	}

	public int getNrSpedi() {
		return this.nrSpedi;
	}

	public void setNrSpedi(int nrSpedi) {
		this.nrSpedi = nrSpedi;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRagSocDest() {
		return this.ragSocDest;
	}

	public void setRagSocDest(String ragSocDest) {
		this.ragSocDest = ragSocDest;
	}

	public String getRagSocEst() {
		return this.ragSocEst;
	}

	public void setRagSocEst(String ragSocEst) {
		this.ragSocEst = ragSocEst;
	}

	public String getServizio() {
		return this.servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public String getTipoIncasso() {
		return this.tipoIncasso;
	}

	public void setTipoIncasso(String tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}


	public int getTrasmesso() {
		return this.trasmesso;
	}

	public void setTrasmesso(int trasmesso) {
		this.trasmesso = trasmesso;
	}

	public String getValutaIncasso() {
		return this.valutaIncasso;
	}

	public void setValutaIncasso(String valutaIncasso) {
		this.valutaIncasso = valutaIncasso;
	}

	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

}

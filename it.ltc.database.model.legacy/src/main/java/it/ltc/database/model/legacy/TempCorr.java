package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;


/**
 * The persistent class for the TempCorr database table.
 * 
 */
@Entity
@Table(name="TempCorr")
@NamedQuery(name="TempCorr.findAll", query="SELECT t FROM TempCorr t")
public class TempCorr implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdTempCor", unique=true, nullable=false)
	private int idTempCor;

	@Column(name="CodCliente", length=30)
	private String codCliente;

	@Column(name="Codcorriere", length=10)
	private String codcorriere;

//	@Column(name="CodFiliale", length=3)
//	private String codFiliale;

	@Column(name="Creazione", nullable=false, columnDefinition="datetime")
	private Date creazione;

	@Column(name="DataDocu", columnDefinition="datetime")
	private Date dataDocu;

	@Column(name="Divisa", length=3)
	private String divisa;

	@Column(name="Generato", nullable=false, length=2, columnDefinition="char(2)")
	private String generato;

	@Column(name="Note", length=150)
	private String note;

	@Column(name="NrColli")
	private int nrColli;

	@Column(name="NrDoc", length=30)
	private String nrDoc;

	@Column(name="NrLista", length=21)
	private String nrLista;

	@Column(name="NrOrdine", length=30)
	private String nrOrdine;

	@Column(name="PesoKg", columnDefinition="money")
	private Double pesoKg;

//	@Column(name="Siglaweb", length=15)
//	private String siglaweb;

	@Column(name="TipoDocu", length=3)
	private String tipoDocu;

	@Column(name="TipoIncasso", length=2)
	private String tipoIncasso;

	@Column(name="ValContra", columnDefinition="money")
	private Double valContra;

	public TempCorr() {}
	
	@PrePersist
	public void prePersist() {
		if (generato == null || generato.isEmpty()) generato = "NO";
		creazione = new Date();
	}

	public int getIdTempCor() {
		return this.idTempCor;
	}

	public void setIdTempCor(int idTempCor) {
		this.idTempCor = idTempCor;
	}

	public String getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getCodcorriere() {
		return this.codcorriere;
	}

	public void setCodcorriere(String codcorriere) {
		this.codcorriere = codcorriere;
	}

//	public String getCodFiliale() {
//		return this.codFiliale;
//	}
//
//	public void setCodFiliale(String codFiliale) {
//		this.codFiliale = codFiliale;
//	}

	public Date getCreazione() {
		return this.creazione;
	}

	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}

	public Date getDataDocu() {
		return this.dataDocu;
	}

	public void setDataDocu(Date dataDocu) {
		this.dataDocu = dataDocu;
	}

	public String getDivisa() {
		return this.divisa;
	}

	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	public String getGenerato() {
		return this.generato;
	}

	public void setGenerato(String generato) {
		this.generato = generato;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNrColli() {
		return this.nrColli;
	}

	public void setNrColli(int nrColli) {
		this.nrColli = nrColli;
	}

	public String getNrDoc() {
		return this.nrDoc;
	}

	public void setNrDoc(String nrDoc) {
		this.nrDoc = nrDoc;
	}

	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

	public String getNrOrdine() {
		return this.nrOrdine;
	}

	public void setNrOrdine(String nrOrdine) {
		this.nrOrdine = nrOrdine;
	}

	public Double getPesoKg() {
		return this.pesoKg;
	}

	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	}

//	public String getSiglaweb() {
//		return this.siglaweb;
//	}
//
//	public void setSiglaweb(String siglaweb) {
//		this.siglaweb = siglaweb;
//	}

	public String getTipoDocu() {
		return this.tipoDocu;
	}

	public void setTipoDocu(String tipoDocu) {
		this.tipoDocu = tipoDocu;
	}

	public String getTipoIncasso() {
		return this.tipoIncasso;
	}

	public void setTipoIncasso(String tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

	public Double getValContra() {
		return this.valContra;
	}

	public void setValContra(Double valContra) {
		this.valContra = valContra;
	}

}
package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import it.ltc.utility.miscellanea.time.DateConverter;


/**
 * The persistent class for the MagaMov database table.
 * 
 */
@Entity
@Table(name="MagaMov")
@NamedQuery(name="MagaMov.findAll", query="SELECT m FROM MagaMov m")
public class MagaMov implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdMagaMov", unique=true, nullable=false)
	private int idMagaMov;

	@Column(name="Cancellato", nullable=false, length=2)
	private String cancellato;

	@Column(name="Causale", nullable=false, length=3)
	private String causale;

	@Column(name="CodMaga", nullable=false, length=3)
	private String codMaga;

	@Column(name="DataMovMag")
	private Timestamp dataMovMag;

	@Column(nullable=false)
	private int disponibilemov;

	@Column(name="DocCat", length=1)
	private String docCat;

	@Column(name="DocData")
	private Timestamp docData;

	@Column(name="DocNote", length=50)
	private String docNote;

	@Column(name="DocNr", length=40)
	private String docNr;

	@Column(name="DocTipo", length=3)
	private String docTipo;

	@Column(name="Esistenzamov", nullable=false)
	private int esistenzamov;

//	private int IDdocum;

	@Column(name="IdUniArticolo", nullable=false, length=25)
	private String idUniArticolo;

	@Column(nullable=false)
	private int impegnatomov;

	@Column(name="IncTotali", length=2)
	private String incTotali;

	@Column(name="KeyCollo", length=20)
	private String keyCollo;

	@Column(name="OraMovMag")
	private int oraMovMag;

	@Column(name="Quantita", nullable=false)
	private int quantita;

	@Column(name="Segno", length=1)
	private String segno;

	@Column(name="SegnoDis", length=1)
	private String segnoDis;

	@Column(name="SegnoEsi", length=1)
	private String segnoEsi;

	@Column(name="SegnoImp", length=1)
	private String segnoImp;

	@Column(name="Tipo", length=2)
	private String tipo;

//	@Column(name="TipoCollo", length=3)
//	private String tipoCollo;

	@Column(nullable=false, length=2)
	private String trasmesso;

	@Column(name="Utente", length=20)
	private String utente;

	public MagaMov() {}
	
	@PrePersist
	public void prePersist() {
		GregorianCalendar now = new GregorianCalendar();
		dataMovMag = new Timestamp(now.getTimeInMillis());
		oraMovMag = DateConverter.getOraComeIntero(dataMovMag);
		dataMovMag = DateConverter.ripulisciTimestap(dataMovMag);
		docData = DateConverter.ripulisciTimestap(dataMovMag);
		if (trasmesso == null) trasmesso = "NO";
		if (cancellato == null) cancellato = "NO";
		if (utente == null) utente = "WEBSERVICE";
	}

	public int getIdMagaMov() {
		return this.idMagaMov;
	}

	public void setIdMagaMov(int idMagaMov) {
		this.idMagaMov = idMagaMov;
	}

	public String getCancellato() {
		return this.cancellato;
	}

	public void setCancellato(String cancellato) {
		this.cancellato = cancellato;
	}

	public String getCausale() {
		return this.causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getCodMaga() {
		return this.codMaga;
	}

	public void setCodMaga(String codMaga) {
		this.codMaga = codMaga;
	}

	public Timestamp getDataMovMag() {
		return this.dataMovMag;
	}

	public void setDataMovMag(Timestamp dataMovMag) {
		this.dataMovMag = dataMovMag;
	}

	public int getDisponibilemov() {
		return this.disponibilemov;
	}

	public void setDisponibilemov(int disponibilemov) {
		this.disponibilemov = disponibilemov;
	}

	public String getDocCat() {
		return this.docCat;
	}

	public void setDocCat(String docCat) {
		this.docCat = docCat;
	}

	public Timestamp getDocData() {
		return this.docData;
	}

	public void setDocData(Timestamp docData) {
		this.docData = docData;
	}

	public String getDocNote() {
		return this.docNote;
	}

	public void setDocNote(String docNote) {
		this.docNote = docNote;
	}

	public String getDocNr() {
		return this.docNr;
	}

	public void setDocNr(String docNr) {
		this.docNr = docNr;
	}

	public String getDocTipo() {
		return this.docTipo;
	}

	public void setDocTipo(String docTipo) {
		this.docTipo = docTipo;
	}

	public int getEsistenzamov() {
		return this.esistenzamov;
	}

	public void setEsistenzamov(int esistenzamov) {
		this.esistenzamov = esistenzamov;
	}

//	public int getIDdocum() {
//		return this.IDdocum;
//	}
//
//	public void setIDdocum(int IDdocum) {
//		this.IDdocum = IDdocum;
//	}

	public String getIdUniArticolo() {
		return this.idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

	public int getImpegnatomov() {
		return this.impegnatomov;
	}

	public void setImpegnatomov(int impegnatomov) {
		this.impegnatomov = impegnatomov;
	}

	public String getIncTotali() {
		return this.incTotali;
	}

	public void setIncTotali(String incTotali) {
		this.incTotali = incTotali;
	}

	public String getKeyCollo() {
		return this.keyCollo;
	}

	public void setKeyCollo(String keyCollo) {
		this.keyCollo = keyCollo;
	}

	public int getOraMovMag() {
		return this.oraMovMag;
	}

	public void setOraMovMag(int oraMovMag) {
		this.oraMovMag = oraMovMag;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getSegno() {
		return this.segno;
	}

	public void setSegno(String segno) {
		this.segno = segno;
	}

	public String getSegnoDis() {
		return this.segnoDis;
	}

	public void setSegnoDis(String segnoDis) {
		this.segnoDis = segnoDis;
	}

	public String getSegnoEsi() {
		return this.segnoEsi;
	}

	public void setSegnoEsi(String segnoEsi) {
		this.segnoEsi = segnoEsi;
	}

	public String getSegnoImp() {
		return this.segnoImp;
	}

	public void setSegnoImp(String segnoImp) {
		this.segnoImp = segnoImp;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

//	public String getTipoCollo() {
//		return this.tipoCollo;
//	}
//
//	public void setTipoCollo(String tipoCollo) {
//		this.tipoCollo = tipoCollo;
//	}

	public String getTrasmesso() {
		return this.trasmesso;
	}

	public void setTrasmesso(String trasmesso) {
		this.trasmesso = trasmesso;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

}
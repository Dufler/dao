package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Destinatari database table.
 * 
 */
@Entity
@Table(name="Destinatari")
//@NamedQuery(name="Destinatari.findAll", query="SELECT d FROM Destinatari d")
public class Destinatari implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdDestina", unique=true, nullable=false)
	private int idDestina;

	@Column(name="Cap", length=10)
	private String cap;

//	@Column(nullable=false, length=2)
//	private String catalogo;

//	@Column(name="Categoria", length=2)
//	private String categoria;

	@Column(name="CodContabile", length=30)
	private String codContabile;

	@Column(name="CodDestina", length=30)
	private String codDestina;

//	@Column(name="CodDestinaN")
//	private int codDestinaN;

//	@Column(name="CodFiliale", length=3)
//	private String codFiliale;

	@Column(name="CodIso", length=3)
	private String codIso;

	@Column(name="CodNaz", length=3)
	private String codNaz;

	@Column(name="Email", length=100)
	private String email;

	@Column(name="Fax", length=50)
	private String fax;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

//	private int idContabile;

	@Column(name="Indirizzo", length=250)
	private String indirizzo;

	@Column(name="Località", length=40)
	private String localita;

	@Column(name="Nazione", length=50)
	private String nazione;

//	@Lob
//	@Column(name="Note")
//	private String note;

//	@Column(name="Priorass")
//	private int priorass;

	@Column(name="Provincia", length=20)
	private String provincia;

	@Column(name="RagSoc1", length=100)
	private String ragSoc1;

	@Column(name="RagSoc2", length=50)
	private String ragSoc2;

//	@Column(name="Riferimento", length=100)
//	private String riferimento;

	@Column(name="Tel", length=50)
	private String tel;

	/**
	 * "DES" se è un destinatario fisico (normale), "CON" se è un destinatario contabile.
	 */
	@Column(name="TipoDestina", length=4)
	private String tipoDestina;

	public Destinatari() {}
	
	@PrePersist
	public void prePersist() {
		if (codContabile == null) codContabile = "";
		if (codDestina == null) codDestina = sdf.format(new Date());
		if (tipoDestina == null) tipoDestina = "DES ";
	}

	public int getIdDestina() {
		return this.idDestina;
	}

	public void setIdDestina(int idDestina) {
		this.idDestina = idDestina;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

//	public String getCatalogo() {
//		return this.catalogo;
//	}
//
//	public void setCatalogo(String catalogo) {
//		this.catalogo = catalogo;
//	}
//
//	public String getCategoria() {
//		return this.categoria;
//	}
//
//	public void setCategoria(String categoria) {
//		this.categoria = categoria;
//	}

	public String getCodContabile() {
		return this.codContabile;
	}

	public void setCodContabile(String codContabile) {
		this.codContabile = codContabile;
	}

	public String getCodDestina() {
		return this.codDestina;
	}

	public void setCodDestina(String codDestina) {
		this.codDestina = codDestina;
	}

//	public int getCodDestinaN() {
//		return this.codDestinaN;
//	}
//
//	public void setCodDestinaN(int codDestinaN) {
//		this.codDestinaN = codDestinaN;
//	}

//	public String getCodFiliale() {
//		return this.codFiliale;
//	}
//
//	public void setCodFiliale(String codFiliale) {
//		this.codFiliale = codFiliale;
//	}

	public String getCodIso() {
		return this.codIso;
	}

	public void setCodIso(String codIso) {
		this.codIso = codIso;
	}

	public String getCodNaz() {
		return this.codNaz;
	}

	public void setCodNaz(String codNaz) {
		this.codNaz = codNaz;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

//	public int getIdAttCliente() {
//		return this.idAttCliente;
//	}
//
//	public void setIdAttCliente(int idAttCliente) {
//		this.idAttCliente = idAttCliente;
//	}
//
//	public int getIdContabile() {
//		return this.idContabile;
//	}
//
//	public void setIdContabile(int idContabile) {
//		this.idContabile = idContabile;
//	}

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

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

//	public String getNote() {
//		return this.note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}
//
//	public int getPriorass() {
//		return this.priorass;
//	}
//
//	public void setPriorass(int priorass) {
//		this.priorass = priorass;
//	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRagSoc1() {
		return this.ragSoc1;
	}

	public void setRagSoc1(String ragSoc1) {
		this.ragSoc1 = ragSoc1;
	}

	public String getRagSoc2() {
		return this.ragSoc2;
	}

	public void setRagSoc2(String ragSoc2) {
		this.ragSoc2 = ragSoc2;
	}

//	public String getRiferimento() {
//		return this.riferimento;
//	}
//
//	public void setRiferimento(String riferimento) {
//		this.riferimento = riferimento;
//	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTipoDestina() {
		return this.tipoDestina;
	}

	public void setTipoDestina(String tipoDestina) {
		this.tipoDestina = tipoDestina;
	}

}
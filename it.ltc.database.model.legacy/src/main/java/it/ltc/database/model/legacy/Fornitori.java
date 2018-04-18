package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Fornitori database table.
 * 
 */
@Entity
@Table(name="Fornitori")
@NamedQuery(name="Fornitori.findAll", query="SELECT f FROM Fornitori f")
public class Fornitori implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdFornitore", unique=true, nullable=false)
	private int idFornitore;

	@Column(name="Cap", length=15)
	private String cap;

//	@Column(length=50)
//	private String capc;

	@Column(name="Citta", length=30)
	private String citta;

//	@Column(length=50)
//	private String cittac;

//	@Column(name="CodFisc", length=16)
//	private String codFisc;

//	@Column(name="CodiceCont", length=50)
//	private String codiceCont;

//	@Column(name="CodiceDest", length=50)
//	private String codiceDest;

	@Column(name="CodiceFornitore", length=20)
	private String codiceFornitore;

	@Column(length=50)
	private String codnaz;

//	@Column(length=50)
//	private String codnazc;

	@Column(length=30)
	private String EMail;

	@Column(name="Fax", length=20)
	private String fax;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

//	@Column(name="IndiC", length=50)
//	private String indiC;

	@Column(name="Indirizzo", length=40)
	private String indirizzo;

	@Column(length=50)
	private String naz;

//	@Column(length=50)
//	private String nazc;

	@Column(name="Prov", length=2)
	private String prov;

//	@Column(length=50)
//	private String provc;

//	@Column(name="RagC", length=50)
//	private String ragC;

	@Column(name="RagSoc", length=50)
	private String ragSoc;

	@Column(name="Tel", length=20)
	private String tel;

	@Column(name="Tipodocumento", length=10)
	private String tipodocumento;

	public Fornitori() {
	}
	
	@PrePersist
	public void prePersist() {
		//Serve ad Andrea che si inserisca questo valore.
		tipodocumento = "CARICO";
	}

	public int getIdFornitore() {
		return this.idFornitore;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

//	public String getCapc() {
//		return this.capc;
//	}
//
//	public void setCapc(String capc) {
//		this.capc = capc;
//	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

//	public String getCittac() {
//		return this.cittac;
//	}
//
//	public void setCittac(String cittac) {
//		this.cittac = cittac;
//	}
//
//	public String getCodFisc() {
//		return this.codFisc;
//	}
//
//	public void setCodFisc(String codFisc) {
//		this.codFisc = codFisc;
//	}
//
//	public String getCodiceCont() {
//		return this.codiceCont;
//	}
//
//	public void setCodiceCont(String codiceCont) {
//		this.codiceCont = codiceCont;
//	}
//
//	public String getCodiceDest() {
//		return this.codiceDest;
//	}
//
//	public void setCodiceDest(String codiceDest) {
//		this.codiceDest = codiceDest;
//	}

	public String getCodiceFornitore() {
		return this.codiceFornitore;
	}

	public void setCodiceFornitore(String codiceFornitore) {
		this.codiceFornitore = codiceFornitore;
	}

	public String getCodnaz() {
		return this.codnaz;
	}

	public void setCodnaz(String codnaz) {
		this.codnaz = codnaz;
	}

//	public String getCodnazc() {
//		return this.codnazc;
//	}
//
//	public void setCodnazc(String codnazc) {
//		this.codnazc = codnazc;
//	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
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
//	public String getIndiC() {
//		return this.indiC;
//	}
//
//	public void setIndiC(String indiC) {
//		this.indiC = indiC;
//	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNaz() {
		return this.naz;
	}

	public void setNaz(String naz) {
		this.naz = naz;
	}

//	public String getNazc() {
//		return this.nazc;
//	}
//
//	public void setNazc(String nazc) {
//		this.nazc = nazc;
//	}

	public String getProv() {
		return this.prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

//	public String getProvc() {
//		return this.provc;
//	}
//
//	public void setProvc(String provc) {
//		this.provc = provc;
//	}
//
//	public String getRagC() {
//		return this.ragC;
//	}
//
//	public void setRagC(String ragC) {
//		this.ragC = ragC;
//	}

	public String getRagSoc() {
		return this.ragSoc;
	}

	public void setRagSoc(String ragSoc) {
		this.ragSoc = ragSoc;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

}
package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Fornitori database table.
 * 
 */
@Entity
@Table(name="Fornitori")
//@NamedQuery(name="Fornitori.findAll", query="SELECT f FROM Fornitori f")
public class Fornitori implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSu");

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdFornitore", unique=true, nullable=false)
	private int idFornitore;

	@Column(name="Cap", length=10)
	private String cap;

//	@Column(length=50)
//	private String capc;

	@Column(name="Citta", length=50)
	private String citta;

//	@Column(length=50)
//	private String cittac;

//	@Column(name="CodFisc", length=16)
//	private String codFisc;

//	@Column(name="CodiceCont", length=50)
//	private String codiceCont;

//	@Column(name="CodiceDest", length=50)
//	private String codiceDest;

	@Column(name="CodiceFornitore", length=20, nullable=false)
	private String codiceFornitore;

	@Column(length=3, nullable=false)
	private String codnaz;

//	@Column(length=50)
//	private String codnazc;

	@Column(length=100)
	private String EMail;

	@Column(name="Fax", length=20)
	private String fax;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

//	@Column(name="IndiC", length=50)
//	private String indiC;

	@Column(name="Indirizzo", length=250)
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

	@Column(name="RagSoc", length=50, nullable=false)
	private String ragSoc;

	@Column(name="Tel", length=20)
	private String tel;

	/**
	 * Può assumere 3 valori:<br>
	 * - CARICO : il default, indica un fornitore di produzione.<br>
	 * - RESO : indica che fornisce resi.<br>
	 * - INVENTARIO : fornitore "fittizio" usato per i carichi d'inventario.<br>
	 */
	@Column(name="Tipodocumento", length=10)
	private String tipodocumento;
	
	@Column(name="note", length=250)
	private String note;

	public Fornitori() {}
	
	@PrePersist
	public void prePersist() {
		//Serve ad Andrea che si inserisca questo valore.
		if (tipodocumento == null) tipodocumento = "CARICO";
		if (codiceFornitore == null) codiceFornitore = sdf.format(new Date());
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
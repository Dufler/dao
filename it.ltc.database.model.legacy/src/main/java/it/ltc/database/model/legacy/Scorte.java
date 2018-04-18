package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Scorte database table.
 * Dettaglio di tutti i prodotti che sono stati ubicati in posizioni a scorta.
 * 
 */
@Entity
@Table(name="Scorte")
@NamedQuery(name="Scorte.findAll", query="SELECT s FROM Scorte s")
public class Scorte implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdScorta", unique=true, nullable=false)
	private int idScorta;

	@Column(length=2)
	private String area;

	/**
	 * impostare valore in inserimento a blank
	 */
	@Column(name="BarcodeCassa", length=50)
	private String barcodeCassa;

	@Column(length=2)
	private String box;

	/**
	 * Se ce l'ho ubicato metto blank altrimenti metto il numero del collo non ubicato e scrivo un record su scorte2.
	 */
	@Column(length=15)
	private String collonoubi;

	@Column(name="Corsia", length=3)
	private String corsia;

//	@Column(name="DataCreazione")
//	private Timestamp dataCreazione;

	@Column(length=50)
	private String iduniarticolo;

//	@Column(name="IndicePercorrenza")
//	private int indicePercorrenza;

	/**
	 * impostare valore in inserimento a blank
	 */
	@Column(name="KeyCoordinata", length=20)
	private String keyCoordinata;

	/**
	 * impostare valore in inserimento a blank
	 */
	@Column(name="KeyMappa", length=20)
	private String keyMappa;

	/**
	 * Quando non lo trovo metto null qui e blank su area, box, ...
	 */
	@Column(name="KeyUbicPre", length=50)
	private String keyUbicPre;

	@Column(length=4)
	private String magazzino;

	/**
	 * Metterci l'ID univoco articolo.
	 */
	@Column(name="Modello", nullable=false, length=50)
	private String modello;

	/**
	 * Se non è stato ubicato mettere "QUANTITA' DA UBICARE: "
	 */
	@Column(name="Note", length=50)
	private String note;

	@Column(length=2)
	private String piano;

	/**
	 * La quantità che non è ubicata.
	 */
	private int qtanoubic;

	/**
	 * La quantità disponibile su quella determinata ubicazione
	 */
	private int qtascorta;

	/**
	 * La quantità richiesta ma da mettere solo sul primo record inserito.
	 */
	@Column(name="Quantita")
	private int quantita;

	@Column(length=3)
	private String scaffale;

	@Column(name="SessioneLavoro", nullable=false, length=50)
	private String sessioneLavoro;

	/**
	 * Da inserire come "SFUSO" nel pre persist
	 */
	@Column(name="Tipo", length=50)
	private String tipo;

//	@Column(name="TotaleElementi")
//	private int totaleElementi;
//
//	@Column(name="TotaleMetri")
//	private BigDecimal totaleMetri;

	@Column(length=50)
	private String ubicazione;

//	private int x;
//
//	private int y;

	public Scorte() {}

	public int getIdScorta() {
		return this.idScorta;
	}

	public void setIdScorta(int idScorta) {
		this.idScorta = idScorta;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBarcodeCassa() {
		return this.barcodeCassa;
	}

	public void setBarcodeCassa(String barcodeCassa) {
		this.barcodeCassa = barcodeCassa;
	}

	public String getBox() {
		return this.box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public String getCollonoubi() {
		return this.collonoubi;
	}

	public void setCollonoubi(String collonoubi) {
		this.collonoubi = collonoubi;
	}

	public String getCorsia() {
		return this.corsia;
	}

	public void setCorsia(String corsia) {
		this.corsia = corsia;
	}

//	public Timestamp getDataCreazione() {
//		return this.dataCreazione;
//	}
//
//	public void setDataCreazione(Timestamp dataCreazione) {
//		this.dataCreazione = dataCreazione;
//	}

	public String getIduniarticolo() {
		return this.iduniarticolo;
	}

	public void setIduniarticolo(String iduniarticolo) {
		this.iduniarticolo = iduniarticolo;
	}

//	public int getIndicePercorrenza() {
//		return this.indicePercorrenza;
//	}
//
//	public void setIndicePercorrenza(int indicePercorrenza) {
//		this.indicePercorrenza = indicePercorrenza;
//	}

	public String getKeyCoordinata() {
		return this.keyCoordinata;
	}

	public void setKeyCoordinata(String keyCoordinata) {
		this.keyCoordinata = keyCoordinata;
	}

	public String getKeyMappa() {
		return this.keyMappa;
	}

	public void setKeyMappa(String keyMappa) {
		this.keyMappa = keyMappa;
	}

	public String getKeyUbicPre() {
		return this.keyUbicPre;
	}

	public void setKeyUbicPre(String keyUbicPre) {
		this.keyUbicPre = keyUbicPre;
	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public String getModello() {
		return this.modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPiano() {
		return this.piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public int getQtanoubic() {
		return this.qtanoubic;
	}

	public void setQtanoubic(int qtanoubic) {
		this.qtanoubic = qtanoubic;
	}

	public int getQtascorta() {
		return this.qtascorta;
	}

	public void setQtascorta(int qtascorta) {
		this.qtascorta = qtascorta;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getScaffale() {
		return this.scaffale;
	}

	public void setScaffale(String scaffale) {
		this.scaffale = scaffale;
	}

	public String getSessioneLavoro() {
		return this.sessioneLavoro;
	}

	public void setSessioneLavoro(String sessioneLavoro) {
		this.sessioneLavoro = sessioneLavoro;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

//	public int getTotaleElementi() {
//		return this.totaleElementi;
//	}
//
//	public void setTotaleElementi(int totaleElementi) {
//		this.totaleElementi = totaleElementi;
//	}
//
//	public BigDecimal getTotaleMetri() {
//		return this.totaleMetri;
//	}
//
//	public void setTotaleMetri(BigDecimal totaleMetri) {
//		this.totaleMetri = totaleMetri;
//	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

//	public int getX() {
//		return this.x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}

//	public int getY() {
//		return this.y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

}
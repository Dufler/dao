package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


/**
 * The persistent class for the Righiubicpre database table.
 * 
 */
@Entity
@Table(name="Righiubicpre")
//@NamedQuery(name="Righiubicpre.findAll", query="SELECT r FROM Righiubicpre r")
public class Righiubicpre implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idubica;

	/**
	 * Descrizione dell'errore.
	 */
	@Column(name="Anomalie", length=200)
	private String anomalie;

	@Column(length=2, columnDefinition="char(2)")
	private String area;

//	@Column(name="BarcodeCassa", length=50)
//	private String barcodeCassa;

//	@Column(name="BarraEan", length=50)
//	private String barraEan;

	@Column(length=2, columnDefinition="char(2)")
	private String box;

	/*Particolarità di Cuccuini, fare classe a parte.*/
//	@Column(name="CodiceDoganale", nullable=false, length=50)
//	private String codiceDoganale;

	@Column(name="Corsia", length=3, columnDefinition="char(3)")
	private String corsia;

	/**
	 * Generare in automatico
	 **/
	@Column(name="dataassegnazione", columnDefinition="datetime")
	private Date dataassegnazione;

//	private Date dataubicaz;

	/**
	 * Se non lo trovo metto il valore di disponibile su magasd
	 */
	@Column(name="Disponibile", nullable=false)
	private int disponibile;

	/**
	 * Metto l'ID del collipack da usare. 0 quando non c'è corrispondenza.
	 */
	@Column(nullable=false)
	private int IDcollipack;

	/**
	 * Metto l'ID della riga d'ordine.
	 */
	@Column(name="IdRigoOrdine", nullable=false)
	private int idRigoOrdine;
	
	/**
	 * Metto l'ID della testata d'ordine.
	 */
	@Column(name="IdTestataOrdine", nullable=false)
	private int idTestataOrdine;
	
	@Column(name="IdArticolo", nullable=false)
	private int idArticolo;

	/**
	 * L'ID univoco dell'articolo.
	 */
	@Column(nullable=false, length=15, columnDefinition="varchar(15)")
	private String iduniarticolo;

//	@Column(name="IndicePercorrenza")
//	private int indicePercorrenza;

	/**
	 * non è da gestire, impostare a stringa vuota al momento dell'inserimento.
	 */
	@Column(name="KeyCoordinata", length=15)
	private String keyCoordinata;

	/**
	 * non è da gestire, impostare a stringa vuota al momento dell'inserimento.
	 */
	@Column(name="KeyMappa", length=10)
	private String keyMappa;

	/**
	 * codice del magazzino da cui ho trovato la disponibilità.
	 */
	@Column(name="MagaDisponibile", nullable=false, length=5)
	private String magaDisponibile;

	/**
	 * Lo stesso magazzino di righiordine e collipack.
	 */
	@Column(length=4, nullable=false)
	private String magazzino;

	/**
	 * NrIdColliPk di collipack e collicarico. Se non c'è prodotto metto qui il 999999999
	 */
	@Column(name="nrcollo", length=9, nullable=false)
	private String nrcollo;

	@Column(length=20)
	private String nrlista;

//	@Column(length=50)
//	private String OLDUbicazione;

	@Column(length=2, columnDefinition="char(2)")
	private String piano;

//	@Column(name="QtaImballata")
//	private int qtaImballata;

	/**
	 * Quantità che dovrò prelevare su quel collo.
	 */
	@Column(name="Quantita", nullable=false)
	private int quantita;

	/**
	 * come nrlista.
	 */
	@Column(length=20)
	private String raggliste;

	@Column(length=3, columnDefinition="char(3)")
	private String scaffale;

	/**
	 * Valore univoco per la sessione di assegnazione.
	 */
	@Column(name="SessioneLavoro", length=50)
	private String sessioneLavoro;

	/**
	 * Il tipo di aggregazione: SFUSO o CASSA, fissare a SFUSO come default in fase di inserimento.
	 */
	@Column(name="Tipo", length=50)
	private String tipo;

	/**
	 * PR o SC come riportato su Ubicazioni, in condizioni normali ci può finire solo PR. Nel caso del 999 ci metto SC.
	 */
	@Column(name="TipoUbicazione", nullable=false, length=2)
	private String tipoUbicazione;

//	@Column(name="TotaleElementi")
//	private int totaleElementi;

//	@Column(name="TotaleMetri")
//	private BigDecimal totaleMetri;

	/**
	 * KeyUbicaCar come su colliCarico.
	 */
	@Column(length=50)
	private String ubicazione;

	/**
	 * Impostare a 0 col prepersist
	 */
	@Column(nullable=false)
	private int x;

	/**
	 * Impostare a 0 col prepersist
	 */
	@Column(nullable=false)
	private int y;
	
	@Column(name="TipoAssegnazione", length=20, nullable=false)
	private String tipoAssegnazione;

	public Righiubicpre() {}
	
	@PrePersist
	public void prePersist() {
		if (magaDisponibile == null) magaDisponibile = "";
		if (area == null || area.isEmpty()) area = "  ";
		if (box == null || box.isEmpty()) box = "  ";
		if (corsia == null || corsia.isEmpty()) corsia = "   ";
		if (piano == null || piano.isEmpty()) piano = "  ";
		if (scaffale == null || scaffale.isEmpty()) scaffale = "   ";
		if (ubicazione == null) ubicazione = "";
		if (nrcollo == null) nrcollo = "999999999";
	}

	public int getIdubica() {
		return this.idubica;
	}

	public void setIdubica(int idubica) {
		this.idubica = idubica;
	}

	public String getAnomalie() {
		return this.anomalie;
	}

	public void setAnomalie(String anomalie) {
		this.anomalie = anomalie;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

//	public String getBarcodeCassa() {
//		return this.barcodeCassa;
//	}
//
//	public void setBarcodeCassa(String barcodeCassa) {
//		this.barcodeCassa = barcodeCassa;
//	}
//
//	public String getBarraEan() {
//		return this.barraEan;
//	}
//
//	public void setBarraEan(String barraEan) {
//		this.barraEan = barraEan;
//	}

	public String getBox() {
		return this.box;
	}

	public void setBox(String box) {
		this.box = box;
	}

//	public String getCodiceDoganale() {
//		return this.codiceDoganale;
//	}
//
//	public void setCodiceDoganale(String codiceDoganale) {
//		this.codiceDoganale = codiceDoganale;
//	}

	public String getCorsia() {
		return this.corsia;
	}

	public void setCorsia(String corsia) {
		this.corsia = corsia;
	}

	public Date getDataassegnazione() {
		return this.dataassegnazione;
	}

	public void setDataassegnazione(Date dataassegnazione) {
		this.dataassegnazione = dataassegnazione;
	}

//	public Date getDataubicaz() {
//		return this.dataubicaz;
//	}
//
//	public void setDataubicaz(Date dataubicaz) {
//		this.dataubicaz = dataubicaz;
//	}

	public int getDisponibile() {
		return this.disponibile;
	}

	public void setDisponibile(int disponibile) {
		this.disponibile = disponibile;
	}

	public int getIDcollipack() {
		return this.IDcollipack;
	}

	public void setIDcollipack(int IDcollipack) {
		this.IDcollipack = IDcollipack;
	}

	public int getIdRigoOrdine() {
		return this.idRigoOrdine;
	}

	public void setIdRigoOrdine(int idRigoOrdine) {
		this.idRigoOrdine = idRigoOrdine;
	}

	public int getIdTestataOrdine() {
		return idTestataOrdine;
	}

	public void setIdTestataOrdine(int idTestataOrdine) {
		this.idTestataOrdine = idTestataOrdine;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

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

	public String getMagaDisponibile() {
		return this.magaDisponibile;
	}

	public void setMagaDisponibile(String magaDisponibile) {
		this.magaDisponibile = magaDisponibile;
	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public String getNrcollo() {
		return this.nrcollo;
	}

	public void setNrcollo(String nrcollo) {
		this.nrcollo = nrcollo;
	}

	public String getNrlista() {
		return this.nrlista;
	}

	public void setNrlista(String nrlista) {
		this.nrlista = nrlista;
	}

//	public String getOLDUbicazione() {
//		return this.OLDUbicazione;
//	}
//
//	public void setOLDUbicazione(String OLDUbicazione) {
//		this.OLDUbicazione = OLDUbicazione;
//	}

	public String getPiano() {
		return this.piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

//	public int getQtaImballata() {
//		return this.qtaImballata;
//	}
//
//	public void setQtaImballata(int qtaImballata) {
//		this.qtaImballata = qtaImballata;
//	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getRaggliste() {
		return this.raggliste;
	}

	public void setRaggliste(String raggliste) {
		this.raggliste = raggliste;
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

	public String getTipoUbicazione() {
		return this.tipoUbicazione;
	}

	public void setTipoUbicazione(String tipoUbicazione) {
		this.tipoUbicazione = tipoUbicazione;
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

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getTipoAssegnazione() {
		return tipoAssegnazione;
	}

	public void setTipoAssegnazione(String tipoAssegnazione) {
		this.tipoAssegnazione = tipoAssegnazione;
	}

}
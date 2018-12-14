package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the RighiImballo database table.
 * 
 */
@Entity
@Table(name="RighiImballo")
//@NamedQuery(name="RighiImballo.findAll", query="SELECT r FROM RighiImballo r")
public class RighiImballo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idRighiImballo;

	@Column(name="CodBarre", length=50)
	private String codBarre;

	@Column(name="CodiceArticolo", length=50)
	private String codiceArticolo;

	@Column(name="DataImba", columnDefinition="datetime")
	private Date dataImba;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

//	@Column(name="IdDocumento")
//	private int idDocumento;

	@Column(name="IdUniArticolo", length=15, nullable=false, columnDefinition="varchar(15)")
	private String idUniArticolo;

	@Column(name="IdUnicoCollo")
	private int idUnicoCollo;

	@Column(name="KeyColloSpe", length=10, columnDefinition="char(10)")
	private String keyColloSpe;

//	@Column(name="NrCPX")
//	private int nrCPX;

	@Column(name="NrLista", length=21)
	private String nrLista;

	@Column(name="NrOrdine", length=20)
	private String nrOrdine;

	/**
	 * Progressivo del collo funzionale al cliente (a dispetto del nome)
	 */
	@Column(name="NrRifCliente")
	private int nrRifCliente;

	/**
	 * Mappa 1 a 1 con nrRigo su RighiOrdine
	 */
	@Column(name="NrRigoOrdine")
	private int nrRigoOrdine;

	/**
	 * pre persist a UNI
	 */
	@Column(name="Numerata", length=20)
	private String numerata;

	@Column(name="Operatore", length=50)
	private String operatore;

	@Column(name="OraImba")
	private int oraImba;

	/**
	 * Se sono pezzi singoli mettere 1, se invece è una cassa mettere il totale dei pezzi.
	 */
	@Column(name="Pezzicassa", nullable=false)
	private int pezzicassa;

	/**
	 * default a stringa vuota.
	 */
	@Column(length=20)
	private String PONumber;

	/**
	 * Fissato a 1.
	 */
	@Column(name="PosTaglia")
	private int posTaglia;

	@Column(name="Postazione", length=20)
	private String postazione;

	@Column(name="ProgCollo")
	private int progCollo;

	@Column(name="QtaImballata")
	private int qtaImballata;

//	@Column(name="RilPzEventi", length=2)
//	private String rilPzEventi;

	@Column(name="Seriale", length=50)
	private String seriale;

//	@Column(name="Stato", length=4)
//	private String stato;

	@Column(name="TipoRif", length=3, columnDefinition="char(3)")
	private String tipoRif;

	public RighiImballo() {}

	public int getIdRighiImballo() {
		return this.idRighiImballo;
	}

	public void setIdRighiImballo(int idRighiImballo) {
		this.idRighiImballo = idRighiImballo;
	}

	public String getCodBarre() {
		return this.codBarre;
	}

	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;
	}

	public String getCodiceArticolo() {
		return this.codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public Date getDataImba() {
		return this.dataImba;
	}

	public void setDataImba(Date dataImba) {
		this.dataImba = dataImba;
	}

//	public int getIdAttCliente() {
//		return this.idAttCliente;
//	}
//
//	public void setIdAttCliente(int idAttCliente) {
//		this.idAttCliente = idAttCliente;
//	}
//
//	public int getIdDocumento() {
//		return this.idDocumento;
//	}
//
//	public void setIdDocumento(int idDocumento) {
//		this.idDocumento = idDocumento;
//	}

	public String getIdUniArticolo() {
		return this.idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

	public int getIdUnicoCollo() {
		return this.idUnicoCollo;
	}

	public void setIdUnicoCollo(int idUnicoCollo) {
		this.idUnicoCollo = idUnicoCollo;
	}

	public String getKeyColloSpe() {
		return this.keyColloSpe;
	}

	public void setKeyColloSpe(String keyColloSpe) {
		this.keyColloSpe = keyColloSpe;
	}

//	public int getNrCPX() {
//		return this.nrCPX;
//	}
//
//	public void setNrCPX(int nrCPX) {
//		this.nrCPX = nrCPX;
//	}

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

	public int getNrRifCliente() {
		return this.nrRifCliente;
	}

	public void setNrRifCliente(int nrRifCliente) {
		this.nrRifCliente = nrRifCliente;
	}

	public int getNrRigoOrdine() {
		return this.nrRigoOrdine;
	}

	public void setNrRigoOrdine(int nrRigoOrdine) {
		this.nrRigoOrdine = nrRigoOrdine;
	}

	public String getNumerata() {
		return this.numerata;
	}

	public void setNumerata(String numerata) {
		this.numerata = numerata;
	}

	public String getOperatore() {
		return this.operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public int getOraImba() {
		return this.oraImba;
	}

	public void setOraImba(int oraImba) {
		this.oraImba = oraImba;
	}

	public int getPezzicassa() {
		return this.pezzicassa;
	}

	public void setPezzicassa(int pezzicassa) {
		this.pezzicassa = pezzicassa;
	}

	public String getPONumber() {
		return this.PONumber;
	}

	public void setPONumber(String PONumber) {
		this.PONumber = PONumber;
	}

	public int getPosTaglia() {
		return this.posTaglia;
	}

	public void setPosTaglia(int posTaglia) {
		this.posTaglia = posTaglia;
	}

	public String getPostazione() {
		return this.postazione;
	}

	public void setPostazione(String postazione) {
		this.postazione = postazione;
	}

	public int getProgCollo() {
		return this.progCollo;
	}

	public void setProgCollo(int progCollo) {
		this.progCollo = progCollo;
	}

	public int getQtaImballata() {
		return this.qtaImballata;
	}

	public void setQtaImballata(int qtaImballata) {
		this.qtaImballata = qtaImballata;
	}

//	public String getRilPzEventi() {
//		return this.rilPzEventi;
//	}
//
//	public void setRilPzEventi(String rilPzEventi) {
//		this.rilPzEventi = rilPzEventi;
//	}

	public String getSeriale() {
		return this.seriale;
	}

	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

//	public String getStato() {
//		return this.stato;
//	}
//
//	public void setStato(String stato) {
//		this.stato = stato;
//	}

	public String getTipoRif() {
		return this.tipoRif;
	}

	public void setTipoRif(String tipoRif) {
		this.tipoRif = tipoRif;
	}

}
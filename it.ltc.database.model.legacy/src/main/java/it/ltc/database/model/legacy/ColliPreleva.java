package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the ColliPreleva database table.
 * 
 */
@Entity
@Table(name="ColliPreleva")
//@NamedQuery(name="ColliPreleva.findAll", query="SELECT c FROM ColliPreleva c")
public class ColliPreleva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdColliPreleva", unique=true, nullable=false)
	private int idColliPreleva;

	@Column(name="Abilita", length=2, nullable=false, columnDefinition="char(2)")
	private String abilita;

//	@Column(name="Area", length=2)
//	private String area;

//	@Column(name="Bancale")
//	private int bancale;

	/**
	 * Da ColliImballo prendo barcodeImb
	 */
	@Column(name="BarcodeCorriere", length=50)
	private String barcodeCorriere;

//	@Column(name="Box", length=2)
//	private String box;

//	@Column(name="CambioInd", nullable=false, length=2)
//	private String cambioInd;

	/**
	 * Ci metto il codice del corriere (Es. BRT, TNT, ...)
	 */
	@Column(name="CodiceCorriere", nullable=false, length=50)
	private String codiceCorriere;

//	@Column(name="Corsia", length=2)
//	private String corsia;

//	@Column(name="DataCreazione")
//	private Date dataCreazione;

	/**
	 * Quando faccio la insert massiva devono avere tutte lo stesso valore. Nel valore indico solo la data con anno, mese e giorno tagliando via ore, minuti e secondi.
	 * Forse conviene creare un Date tramite SimpleDateFormat.parse() ?
	 */
	@Column(name="DataDistinta", columnDefinition="datetime")
	private Date dataDistinta;

//	@Column(name="DataDocCheckOut", nullable=false)
//	private Date dataDocCheckOut;

//	@Column(name="DataSped")
//	private Date dataSped;

//	@Column(name="Fattura", nullable=false, length=1)
//	private String fattura;

//	@Column(name="GenFile", length=2)
//	private String genFile;

	/**
	 * Data di oggi nel formato yyyyMMdd e concatenata da "00000"
	 */
	@Column(name="Gruppo", length=15)
	private String gruppo;

	/**
	 * KeyCollospe di colliImballo
	 */
	@Column(name="KeyColloPre", length=10, columnDefinition="char(10)")
	private String keyColloPre;

//	@Column(name="Magazzino", length=3)
//	private String magazzino;

//	@Column(name="Note", nullable=false, length=50)
//	private String note;

	/**
	 * NrIdCollo di colliImballo
	 */
	@Column(name="NrColloCliente")
	private int nrColloCliente;

	/**
	 * Ci metto l'intero ricavato da yyyyMMdd del momento in cui lo creo.
	 */
	@Column(name="NrDistinta")
	private int nrDistinta;
	
	@Column(name="NrLista", length=21)
	private String nrLista;

//	@Column(name="NrDocCheckOut", nullable=false)
//	private int nrDocCheckOut;

//	@Column(name="OraSped")
//	private int oraSped;

//	@Column(name="Piano", length=2)
//	private String piano;

	@Column(name="PONumber", length=20)
	private String poNumber;

//	@Column(name="Scaffale", length=3)
//	private String scaffale;

	@Column(name="Spedito", nullable=false, length=2, columnDefinition="char(2)")
	private String spedito;

//	@Column(name="Stato", nullable=false, length=50)
//	private String stato;

//	@Column(name="StpDelivery", nullable=false, length=50)
//	private String stpDelivery;

	/**
	 * Come il campo codiceCorriere
	 */
	@Column(name="Vet1", length=25, nullable=false)
	private String vet1;

	/**
	 * Impostare a " ", uno spazio.
	 */
	@Column(name="Vet2", length=25)
	private String vet2;
	
	@Column(name="Volume", columnDefinition="money", nullable=false)
	private double volume;
	
	@Column(name="Peso", columnDefinition="money", nullable=false)
	private double peso;

	@Column(name="Pezzi", nullable=false)
	private int pezzi;
	
	@Column(name="formato", length=3, nullable=false)
	private String formato;

	public ColliPreleva() {}
	
	@PrePersist
	public void prePersist() {
		setGruppo();
		vet1 = codiceCorriere;
		if (vet2 == null) vet2 = " ";
		if (abilita == null || abilita.isEmpty()) abilita = "NO";
		if (spedito == null || spedito.isEmpty()) spedito = "NO";
		if (poNumber == null) poNumber = "";
	}
	
	@PreUpdate
	public void preUpdate() {
		setGruppo();
	}
	
	/**
	 * Metodo richiamato durante l'insert e l'update per impostare in automatico i campi: dataDistinta, gruppo e nrDistinta con la data attuale.
	 */
	public void setGruppo() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(now);
		try {
			dataDistinta = new Date(sdf.parse(today).getTime());
		} catch (ParseException e) {
			dataDistinta = new Date(now.getTime());
		}
		gruppo = today + "00000" + vet1; //Ci aggiungo il codice del vettore.
		nrDistinta = Integer.parseInt(today);
	}

	public int getIdColliPreleva() {
		return this.idColliPreleva;
	}

	public void setIdColliPreleva(int idColliPreleva) {
		this.idColliPreleva = idColliPreleva;
	}

	public String getAbilita() {
		return this.abilita;
	}

	public void setAbilita(String abilita) {
		this.abilita = abilita;
	}
//
//	public String getArea() {
//		return this.area;
//	}
//
//	public void setArea(String area) {
//		this.area = area;
//	}
//
//	public int getBancale() {
//		return this.bancale;
//	}
//
//	public void setBancale(int bancale) {
//		this.bancale = bancale;
//	}

	public String getBarcodeCorriere() {
		return this.barcodeCorriere;
	}

	public void setBarcodeCorriere(String barcodeCorriere) {
		this.barcodeCorriere = barcodeCorriere;
	}

//	public String getBox() {
//		return this.box;
//	}
//
//	public void setBox(String box) {
//		this.box = box;
//	}

//	public String getCambioInd() {
//		return this.cambioInd;
//	}
//
//	public void setCambioInd(String cambioInd) {
//		this.cambioInd = cambioInd;
//	}

	public String getCodiceCorriere() {
		return this.codiceCorriere;
	}

	public void setCodiceCorriere(String codiceCorriere) {
		this.codiceCorriere = codiceCorriere;
	}

//	public String getCorsia() {
//		return this.corsia;
//	}
//
//	public void setCorsia(String corsia) {
//		this.corsia = corsia;
//	}

//	public Date getDataCreazione() {
//		return this.dataCreazione;
//	}
//
//	public void setDataCreazione(Date dataCreazione) {
//		this.dataCreazione = dataCreazione;
//	}

	public Date getDataDistinta() {
		return this.dataDistinta;
	}

	public void setDataDistinta(Date dataDistinta) {
		this.dataDistinta = dataDistinta;
	}

//	public Date getDataDocCheckOut() {
//		return this.dataDocCheckOut;
//	}
//
//	public void setDataDocCheckOut(Date dataDocCheckOut) {
//		this.dataDocCheckOut = dataDocCheckOut;
//	}
//
//	public Date getDataSped() {
//		return this.dataSped;
//	}
//
//	public void setDataSped(Date dataSped) {
//		this.dataSped = dataSped;
//	}
//
//	public String getFattura() {
//		return this.fattura;
//	}
//
//	public void setFattura(String fattura) {
//		this.fattura = fattura;
//	}
//
//	public String getGenFile() {
//		return this.genFile;
//	}
//
//	public void setGenFile(String genFile) {
//		this.genFile = genFile;
//	}

	public String getGruppo() {
		return this.gruppo;
	}

	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}

	public String getKeyColloPre() {
		return this.keyColloPre;
	}

	public void setKeyColloPre(String keyColloPre) {
		this.keyColloPre = keyColloPre;
	}

//	public String getMagazzino() {
//		return this.magazzino;
//	}
//
//	public void setMagazzino(String magazzino) {
//		this.magazzino = magazzino;
//	}
//
//	public String getNote() {
//		return this.note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}

	public int getNrColloCliente() {
		return this.nrColloCliente;
	}

	public void setNrColloCliente(int nrColloCliente) {
		this.nrColloCliente = nrColloCliente;
	}

	public int getNrDistinta() {
		return this.nrDistinta;
	}

	public void setNrDistinta(int nrDistinta) {
		this.nrDistinta = nrDistinta;
	}
	
	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

//	public int getNrDocCheckOut() {
//		return this.nrDocCheckOut;
//	}
//
//	public void setNrDocCheckOut(int nrDocCheckOut) {
//		this.nrDocCheckOut = nrDocCheckOut;
//	}
//
//	public int getOraSped() {
//		return this.oraSped;
//	}
//
//	public void setOraSped(int oraSped) {
//		this.oraSped = oraSped;
//	}

//	public String getPiano() {
//		return this.piano;
//	}
//
//	public void setPiano(String piano) {
//		this.piano = piano;
//	}

	public String getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

//	public String getScaffale() {
//		return this.scaffale;
//	}
//
//	public void setScaffale(String scaffale) {
//		this.scaffale = scaffale;
//	}

	public String getSpedito() {
		return this.spedito;
	}

	public void setSpedito(String spedito) {
		this.spedito = spedito;
	}
//
//	public String getStato() {
//		return this.stato;
//	}
//
//	public void setStato(String stato) {
//		this.stato = stato;
//	}
//
//	public String getStpDelivery() {
//		return this.stpDelivery;
//	}
//
//	public void setStpDelivery(String stpDelivery) {
//		this.stpDelivery = stpDelivery;
//	}

	public String getVet1() {
		return this.vet1;
	}

	public void setVet1(String vet1) {
		this.vet1 = vet1;
	}

	public String getVet2() {
		return this.vet2;
	}

	public void setVet2(String vet2) {
		this.vet2 = vet2;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getPezzi() {
		return pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

}
package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;

import it.ltc.utility.miscellanea.string.StringUtility;
import it.ltc.utility.miscellanea.time.DateConverter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * The persistent class for the ColliCarico database table.
 * 
 */
@Entity
@Table(name="ColliCarico")
@NamedQueries({
	@NamedQuery(name="ColliCarico.findAll", query="SELECT c FROM ColliCarico c"),
	@NamedQuery(name="ColliCarico.progressivoCollo", query="SELECT MAX(c.nrCollo) FROM ColliCarico c WHERE c.anno = :anno")
})
public class ColliCarico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdCollo", unique=true, nullable=false)
	private int idCollo;

	/**
	 * formato: yyyy
	 */
	@Column(name="Anno")
	private int anno;

//	@Column(name="Annulla", nullable=false)
//	private int annulla;

	/**
	 * default N, S quando viene aperto per rendere sfuso il prodotto all'interno.
	 */
	@Column(name="ApertoSfuso", length=1, nullable=false, insertable=false, columnDefinition="char(1)")
	private String apertoSfuso;

	/**
	 * CPX + KeyColloCar
	 */
	@Column(name="Barcode", length=12)
	private String barcode;

//	/**
//	 * default a NO nel prepersist. EDIT 28/09/2018 : insieme ad Andrea e Palma si è deciso di usare solo il campo cancellato.
//	 */
//	@Column(name="Bloccato", nullable=false, length=50)
//	private String bloccato;

	/**
	 * default a NO nel prepersist. Va a soppiantare il campo distrutto che non verrà più usato.
	 */
	@Column(name="Cancellato", length=2, columnDefinition="char(2)")
	private String cancellato;

	/**
	 * Giorno della creazione senza l'ora.
	 */
	@Column(name="DataCrea", updatable=false, columnDefinition="datetime")
	private Date dataCrea;

	/**
	 * Giorno della distruzione senza l'ora.
	 */
	@Column(name="DataDistruzione", columnDefinition="datetime")
	private Date dataDistruzione;

//	/**
//	 * Viene valorizzato quando viene prelevata la cassa per intero sparando il bollone esterno. Su Date è un char(10) e fa casino, siccome non è rilevante è stato commentato.
//	 */
//	@Column(name="DataRilImballo")
//	private Date dataRilImballo;

	/**
	 * Giorno in cui viene ubicata senza l'ora.
	 */
	@Column(name="DataUbica")
	private Date dataUbica;

//	@Column(name="DataUMod")
//	private Date dataUMod;

//	/**
//	 * Default a NO nel prepersist, EDIT 28/09/2018 : insieme ad Andrea e Palma si è deciso di usare solo il campo cancellato.
//	 */
//	@Column(name="Distrutto", length=2)
//	private String distrutto;

//	@Column(name="Fase", nullable=false, length=2)
//	private String fase;

	/**
	 * Default a 0, passa a 1 quando viene generato il carico.
	 */
	@Column(nullable=false, length=1, columnDefinition="char(1)")
	private String flagtc;
	
	//Il default su db è a SI, Andrea dice che non viene gestito.
//	@Column(nullable=false, length=2)
//	private String genPack;

	/**
	 * Barcode del cliente, potrebbe non esserci e in quel caso inserisco una stringa vuota.
	 */
	@Column(name="Id_Box", length=50)
	private String id_Box;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

	/**
	 * ID del pakitesta. Esiste una FK.
	 */
	@Column(name="IdDocumento", nullable=false)
	private int idDocumento;

	//Ha un default a NO, non viene usato.
//	@Column(name="Imballato", nullable=false, length=50)
//	private String imballato;

	/**
	 * Valore univoco fatto così: YY + padding di 0 + progressivo collo.
	 */
	@Column(name="KeyColloCar", length=9, nullable=false, updatable=false, columnDefinition="varchar(9)")
	private String keyColloCar;

	/**
	 * Non va usato in inserimento, viene messo un default a NOUBICATO.
	 */
	@Column(name="KeyUbicaCar", length=15, columnDefinition="char(15)")
	private String keyUbicaCar;

	/**
	 * Codifica del magazzino LTC
	 */
	@Column(name="Magazzino", length=10, nullable=false)
	private String magazzino;

	@Column(name="Note", length=100, insertable=false)
	private String note;

	/**
	 * Progressivo della tabella.
	 */
	@Column(name="NrCollo", updatable=false)
	private int nrCollo;

//	@Column(name="NrRiferimento")
//	private int nrRiferimento;

	/**
	 * Codice dell'operatore che ubica il collo.
	 */
	@Column(name="OpeUbica", insertable=false)
	private Integer opeUbica;

	/**
	 * Ora della creazione del collo.
	 */
	@Column(name="OraCrea", updatable=false)
	private int oraCrea;

	/**
	 * Ora della distruzione del collo.
	 */
	@Column(name="OraDistruzione")
	private Integer oraDistruzione;

	/**
	 * Ora dell'ubicazione del collo.
	 */
	@Column(name="OraUbica")
	private Integer oraUbica;

//	@Column(name="OraUMod")
//	private int oraUMod;

//	@Column(name="Peso", nullable=false)
//	private int peso;

	/**
	 * Default a N, passa a S quando viene imballata la cassa per intero.
	 */
	@Column(name="RilevataImballo", nullable=false, length=1, insertable=false)
	private String rilevataImballo;

	//TODO: Andrea deve verificare l'utilizzo
//	@Column(name="Sessione", length=50, insertable=false)
//	private String sessione;

	/**
	 * Default a stringa vuota. //TODO: Andrea deve verificare l'utilizzo
	 */
//	@Column(name="Sessione_Chk", nullable=false, length=50, insertable=false)
//	private String sessione_Chk;

	/**
	 * Valore di deafult sul db ATTE
	 */
	@Column(name="Stato", length=6, nullable=false)
	private String stato;

	/**
	 * Il tipo della cassa, se non c'è l'ho inserisco 'XXX' nel prepersist
	 */
	@Column(name="TipoCassa", nullable=false, length=20)
	private String tipoCassa;
	
//	@Column(name="TipoCollo", length=10)
//	private String tipoCollo;

	/**
	 * Default a NO, passa a SI quando viene ubicato.
	 */
	@Column(name="Ubicato", length=2, nullable=false, columnDefinition="char(2)")
	private String ubicato;

	/**
	 * Codice dell'operatore che ha distrutto il collo.
	 */
	@Column(name="UteDistruzione", length=50)
	private String uteDistruzione;

	/**
	 * Metto un default a stringa vuota.
	 */
	@Column(name="Utente", length=50, updatable=false)
	private String utente;

	/**
	 * Metto un default a stringa vuota.
	 */
	@Column(name="UtenteMod", length=50)
	private String utenteMod;

	/**
	 * Metto un default a stringa vuota.
	 */
	@Column(name="UtenteUbica", length=50)
	private String utenteUbica;

//	@Column(name="Volume", nullable=false)
//	private int volume;
	
	/**
	 * Valore transiente non salvato nel DB che deve essere iniettato prima dell'inserimento.<br>
	 * Serve a valorizzare correttamente il barcode del collo. 
	 */
	@Transient
	private String prefissoCollo;

	public ColliCarico() {
		prefissoCollo = "CPX";
	}
	
	@PrePersist
	public void prePersist() {
		StringUtility su = new StringUtility();
		anno = new GregorianCalendar().get(Calendar.YEAR);
		dataCrea = new Date();
		oraCrea = DateConverter.getOraComeIntero(dataCrea);
		dataCrea = DateConverter.ripulisciTimestap(dataCrea);
		String annoCollo = Integer.toString(anno).substring(2, 4);
		String progressivoCollo = su.getFormattedString(nrCollo, 7);
		keyColloCar = annoCollo + progressivoCollo;
		barcode = prefissoCollo + keyColloCar;
		if (id_Box == null)	id_Box = "";
		if (tipoCassa == null) tipoCassa = "XXX";
		if (utente == null) utente = "";
		if (apertoSfuso == null) apertoSfuso = "N";
		if (rilevataImballo == null) rilevataImballo = "N";
		if (stato == null) stato = "APERTO";
		//Controllo se mi è stata fornita un'ubicazione.
		if (keyUbicaCar == null || keyUbicaCar.isEmpty()) {
			keyUbicaCar = "NOUBICATO";
			ubicato = "NO";
		} else {
			ubicato = "SI";
		}		
//		bloccato = "NO";
		cancellato = "NO";
//		distrutto = "NO";
//		flagtc = "0";
		utenteMod = "";
		utenteUbica = "";
		uteDistruzione = "";			
	}

	public int getIdCollo() {
		return this.idCollo;
	}

	public void setIdCollo(int idCollo) {
		this.idCollo = idCollo;
	}

	public int getAnno() {
		return this.anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

//	public int getAnnulla() {
//		return this.annulla;
//	}
//
//	public void setAnnulla(int annulla) {
//		this.annulla = annulla;
//	}

	public String getApertoSfuso() {
		return this.apertoSfuso;
	}

	public void setApertoSfuso(String apertoSfuso) {
		this.apertoSfuso = apertoSfuso;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

//	public String getBloccato() {
//		return this.bloccato;
//	}
//
//	public void setBloccato(String bloccato) {
//		this.bloccato = bloccato;
//	}

	public String getCancellato() {
		return this.cancellato;
	}

	public void setCancellato(String cancellato) {
		this.cancellato = cancellato;
	}

	public Date getDataCrea() {
		return this.dataCrea;
	}

	public void setDataCrea(Date dataCrea) {
		this.dataCrea = dataCrea;
	}

	public Date getDataDistruzione() {
		return this.dataDistruzione;
	}

	public void setDataDistruzione(Date dataDistruzione) {
		this.dataDistruzione = dataDistruzione;
	}

//	public Date getDataRilImballo() {
//		return this.dataRilImballo;
//	}
//
//	public void setDataRilImballo(Date dataRilImballo) {
//		this.dataRilImballo = dataRilImballo;
//	}

	public Date getDataUbica() {
		return this.dataUbica;
	}

	public void setDataUbica(Date dataUbica) {
		this.dataUbica = dataUbica;
	}

//	public Date getDataUMod() {
//		return this.dataUMod;
//	}
//
//	public void setDataUMod(Date dataUMod) {
//		this.dataUMod = dataUMod;
//	}

//	public String getDistrutto() {
//		return this.distrutto;
//	}
//
//	public void setDistrutto(String distrutto) {
//		this.distrutto = distrutto;
//	}

//	public String getFase() {
//		return this.fase;
//	}
//
//	public void setFase(String fase) {
//		this.fase = fase;
//	}

	public String getFlagtc() {
		return this.flagtc;
	}

	public void setFlagtc(String flagtc) {
		this.flagtc = flagtc;
	}

//	public String getGenPack() {
//		return this.genPack;
//	}
//
//	public void setGenPack(String genPack) {
//		this.genPack = genPack;
//	}

	public String getId_Box() {
		return this.id_Box;
	}

	public void setId_Box(String id_Box) {
		this.id_Box = id_Box;
	}

//	public int getIdAttCliente() {
//		return this.idAttCliente;
//	}
//
//	public void setIdAttCliente(int idAttCliente) {
//		this.idAttCliente = idAttCliente;
//	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

//	public String getImballato() {
//		return this.imballato;
//	}
//
//	public void setImballato(String imballato) {
//		this.imballato = imballato;
//	}

	public String getKeyColloCar() {
		return this.keyColloCar;
	}

	public void setKeyColloCar(String keyColloCar) {
		this.keyColloCar = keyColloCar;
	}

	public String getKeyUbicaCar() {
		return this.keyUbicaCar;
	}

	public void setKeyUbicaCar(String keyUbicaCar) {
		this.keyUbicaCar = keyUbicaCar;
	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNrCollo() {
		return this.nrCollo;
	}

	public void setNrCollo(int nrCollo) {
		this.nrCollo = nrCollo;
	}

//	public int getNrRiferimento() {
//		return this.nrRiferimento;
//	}
//
//	public void setNrRiferimento(int nrRiferimento) {
//		this.nrRiferimento = nrRiferimento;
//	}

	public Integer getOpeUbica() {
		return this.opeUbica;
	}

	public void setOpeUbica(Integer opeUbica) {
		this.opeUbica = opeUbica;
	}

	public int getOraCrea() {
		return this.oraCrea;
	}

	public void setOraCrea(int oraCrea) {
		this.oraCrea = oraCrea;
	}

	public Integer getOraDistruzione() {
		return this.oraDistruzione;
	}

	public void setOraDistruzione(Integer oraDistruzione) {
		this.oraDistruzione = oraDistruzione;
	}

	public Integer getOraUbica() {
		return this.oraUbica;
	}

	public void setOraUbica(Integer oraUbica) {
		this.oraUbica = oraUbica;
	}

//	public int getOraUMod() {
//		return this.oraUMod;
//	}
//
//	public void setOraUMod(int oraUMod) {
//		this.oraUMod = oraUMod;
//	}
//
//	public int getPeso() {
//		return this.peso;
//	}
//
//	public void setPeso(int peso) {
//		this.peso = peso;
//	}

	public String getRilevataImballo() {
		return this.rilevataImballo;
	}

	public void setRilevataImballo(String rilevataImballo) {
		this.rilevataImballo = rilevataImballo;
	}

//	public String getSessione() {
//		return this.sessione;
//	}
//
//	public void setSessione(String sessione) {
//		this.sessione = sessione;
//	}
//
//	public String getSessione_Chk() {
//		return this.sessione_Chk;
//	}
//
//	public void setSessione_Chk(String sessione_Chk) {
//		this.sessione_Chk = sessione_Chk;
//	}
//
	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTipoCassa() {
		return this.tipoCassa;
	}

	public void setTipoCassa(String tipoCassa) {
		this.tipoCassa = tipoCassa;
	}

//	public String getTipoCollo() {
//		return this.tipoCollo;
//	}
//
//	public void setTipoCollo(String tipoCollo) {
//		this.tipoCollo = tipoCollo;
//	}

	public String getUbicato() {
		return this.ubicato;
	}

	public void setUbicato(String ubicato) {
		this.ubicato = ubicato;
	}

	public String getUteDistruzione() {
		return this.uteDistruzione;
	}

	public void setUteDistruzione(String uteDistruzione) {
		this.uteDistruzione = uteDistruzione;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getUtenteMod() {
		return this.utenteMod;
	}

	public void setUtenteMod(String utenteMod) {
		this.utenteMod = utenteMod;
	}

	public String getUtenteUbica() {
		return this.utenteUbica;
	}

	public void setUtenteUbica(String utenteUbica) {
		this.utenteUbica = utenteUbica;
	}

	public String getPrefissoCollo() {
		return prefissoCollo;
	}

	public void setPrefissoCollo(String prefissoCollo) {
		this.prefissoCollo = prefissoCollo;
	}

//	public int getVolume() {
//		return this.volume;
//	}
//
//	public void setVolume(int volume) {
//		this.volume = volume;
//	}

}
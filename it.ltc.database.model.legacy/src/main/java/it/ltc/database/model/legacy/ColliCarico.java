package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ColliCarico database table.
 * 
 */
@Entity
@Table(name="ColliCarico")
@NamedQuery(name="ColliCarico.findAll", query="SELECT c FROM ColliCarico c")
public class ColliCarico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdCollo", unique=true, nullable=false)
	private int idCollo;

	@Column(name="Anno")
	private int anno;

	@Column(name="Annulla", nullable=false)
	private int annulla;

	@Column(name="ApertoSfuso", length=1)
	private String apertoSfuso;

	@Column(name="Barcode", length=12)
	private String barcode;

	@Column(name="Bloccato", nullable=false, length=50)
	private String bloccato;

	@Column(name="Cancellato", length=2)
	private String cancellato;

	@Column(name="DataCrea")
	private Timestamp dataCrea;

	@Column(name="DataDistruzione")
	private Timestamp dataDistruzione;

	@Column(name="DataRilImballo")
	private Timestamp dataRilImballo;

	@Column(name="DataUbica")
	private Timestamp dataUbica;

	@Column(name="DataUMod")
	private Timestamp dataUMod;

	@Column(name="Distrutto", length=2)
	private String distrutto;

	@Column(name="Fase", nullable=false, length=2)
	private String fase;

	@Column(nullable=false, length=1)
	private String flagtc;

	@Column(nullable=false, length=2)
	private String genPack;

	@Column(name="Id_Box", length=50)
	private String id_Box;

	@Column(name="IdAttCliente")
	private int idAttCliente;

	@Column(name="IdDocumento")
	private int idDocumento;

	@Column(name="Imballato", nullable=false, length=50)
	private String imballato;

	@Column(name="KeyColloCar", length=9)
	private String keyColloCar;

	@Column(name="KeyUbicaCar", length=15)
	private String keyUbicaCar;

	@Column(name="Magazzino", length=10)
	private String magazzino;

	@Column(name="Note", length=100)
	private String note;

	@Column(name="NrCollo")
	private int nrCollo;

	@Column(name="NrRiferimento")
	private int nrRiferimento;

	@Column(name="OpeUbica")
	private int opeUbica;

	@Column(name="OraCrea")
	private int oraCrea;

	@Column(name="OraDistruzione")
	private int oraDistruzione;

	@Column(name="OraUbica")
	private int oraUbica;

	@Column(name="OraUMod")
	private int oraUMod;

	@Column(name="Peso", nullable=false)
	private int peso;

	@Column(name="RilevataImballo", nullable=false, length=1)
	private String rilevataImballo;

	@Column(name="Sessione", length=50)
	private String sessione;

	@Column(name="Sessione_Chk", nullable=false, length=50)
	private String sessione_Chk;

	@Column(name="Stato", length=6)
	private String stato;

	@Column(name="TipoCassa", nullable=false, length=50)
	private String tipoCassa;

	@Column(name="TipoCollo", length=10)
	private String tipoCollo;

	@Column(name="Ubicato", length=2)
	private String ubicato;

	@Column(name="UteDistruzione", length=20)
	private String uteDistruzione;

	@Column(name="Utente", length=20)
	private String utente;

	@Column(name="UtenteMod", length=20)
	private String utenteMod;

	@Column(name="UtenteUbica", nullable=false, length=20)
	private String utenteUbica;

	@Column(name="Volume", nullable=false)
	private int volume;

	public ColliCarico() {}

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

	public int getAnnulla() {
		return this.annulla;
	}

	public void setAnnulla(int annulla) {
		this.annulla = annulla;
	}

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

	public String getBloccato() {
		return this.bloccato;
	}

	public void setBloccato(String bloccato) {
		this.bloccato = bloccato;
	}

	public String getCancellato() {
		return this.cancellato;
	}

	public void setCancellato(String cancellato) {
		this.cancellato = cancellato;
	}

	public Timestamp getDataCrea() {
		return this.dataCrea;
	}

	public void setDataCrea(Timestamp dataCrea) {
		this.dataCrea = dataCrea;
	}

	public Timestamp getDataDistruzione() {
		return this.dataDistruzione;
	}

	public void setDataDistruzione(Timestamp dataDistruzione) {
		this.dataDistruzione = dataDistruzione;
	}

	public Timestamp getDataRilImballo() {
		return this.dataRilImballo;
	}

	public void setDataRilImballo(Timestamp dataRilImballo) {
		this.dataRilImballo = dataRilImballo;
	}

	public Timestamp getDataUbica() {
		return this.dataUbica;
	}

	public void setDataUbica(Timestamp dataUbica) {
		this.dataUbica = dataUbica;
	}

	public Timestamp getDataUMod() {
		return this.dataUMod;
	}

	public void setDataUMod(Timestamp dataUMod) {
		this.dataUMod = dataUMod;
	}

	public String getDistrutto() {
		return this.distrutto;
	}

	public void setDistrutto(String distrutto) {
		this.distrutto = distrutto;
	}

	public String getFase() {
		return this.fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public String getFlagtc() {
		return this.flagtc;
	}

	public void setFlagtc(String flagtc) {
		this.flagtc = flagtc;
	}

	public String getGenPack() {
		return this.genPack;
	}

	public void setGenPack(String genPack) {
		this.genPack = genPack;
	}

	public String getId_Box() {
		return this.id_Box;
	}

	public void setId_Box(String id_Box) {
		this.id_Box = id_Box;
	}

	public int getIdAttCliente() {
		return this.idAttCliente;
	}

	public void setIdAttCliente(int idAttCliente) {
		this.idAttCliente = idAttCliente;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getImballato() {
		return this.imballato;
	}

	public void setImballato(String imballato) {
		this.imballato = imballato;
	}

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

	public int getNrRiferimento() {
		return this.nrRiferimento;
	}

	public void setNrRiferimento(int nrRiferimento) {
		this.nrRiferimento = nrRiferimento;
	}

	public int getOpeUbica() {
		return this.opeUbica;
	}

	public void setOpeUbica(int opeUbica) {
		this.opeUbica = opeUbica;
	}

	public int getOraCrea() {
		return this.oraCrea;
	}

	public void setOraCrea(int oraCrea) {
		this.oraCrea = oraCrea;
	}

	public int getOraDistruzione() {
		return this.oraDistruzione;
	}

	public void setOraDistruzione(int oraDistruzione) {
		this.oraDistruzione = oraDistruzione;
	}

	public int getOraUbica() {
		return this.oraUbica;
	}

	public void setOraUbica(int oraUbica) {
		this.oraUbica = oraUbica;
	}

	public int getOraUMod() {
		return this.oraUMod;
	}

	public void setOraUMod(int oraUMod) {
		this.oraUMod = oraUMod;
	}

	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getRilevataImballo() {
		return this.rilevataImballo;
	}

	public void setRilevataImballo(String rilevataImballo) {
		this.rilevataImballo = rilevataImballo;
	}

	public String getSessione() {
		return this.sessione;
	}

	public void setSessione(String sessione) {
		this.sessione = sessione;
	}

	public String getSessione_Chk() {
		return this.sessione_Chk;
	}

	public void setSessione_Chk(String sessione_Chk) {
		this.sessione_Chk = sessione_Chk;
	}

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

	public String getTipoCollo() {
		return this.tipoCollo;
	}

	public void setTipoCollo(String tipoCollo) {
		this.tipoCollo = tipoCollo;
	}

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

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
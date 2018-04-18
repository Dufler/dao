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
 * The persistent class for the ColliImballo database table.
 * 
 */
@Entity
@Table(name="ColliImballo")
@NamedQuery(name="ColliImballo.findAll", query="SELECT c FROM ColliImballo c")
public class ColliImballo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idColliImballo;

//	@Column(name="Abilita", nullable=false, length=2)
//	private String abilita;

//	@Column(name="Anno")
//	private int anno;

	@Column(name="BarCodeImb", length=50)
	private String barCodeImb;

	/**
	 * Devo filtrare imponendo che sia 'NO'
	 */
	@Column(name="Cancellato", length=2)
	private String cancellato;

//	@Column(name="CaricoMag", length=1)
//	private String caricoMag;

//	@Column(name="CodCliente", length=30)
//	private String codCliente;

//	@Column(name="CodDestinazione", length=3)
//	private String codDestinazione;

	@Column(name="CodFormato", length=3)
	private String codFormato;

//	@Column(name="CodFornitore")
//	private int codFornitore;

//	@Column(name="ColloUoC", length=1)
//	private String colloUoC;

//	@Column(name="CtrlEtic", length=2)
//	private String ctrlEtic;

//	@Column(name="DataCrea")
//	private Timestamp dataCrea;

//	@Column(name="DataDocArrivo")
//	private Timestamp dataDocArrivo;

//	@Column(name="DataGen")
//	private Timestamp dataGen;

//	@Column(name="DataUbica")
//	private Timestamp dataUbica;

//	@Column(name="EticPiano", length=2)
//	private String eticPiano;

//	@Column(name="Fase", nullable=false, length=2)
//	private String fase;

//	@Column(name="Generato", length=2)
//	private String generato;

//	@Column(name="GenFile", length=2)
//	private String genFile;

//	@Column(name="GenMovMag", length=2)
//	private String genMovMag;

//	@Column(name="Id_Box", nullable=false, length=50)
//	private String id_Box;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

//	@Column(name="IdDestina")
//	private int idDestina;

	@Column(name="KeyColloSpe", nullable=false, length=10)
	private String keyColloSpe;

//	@Column(name="KeyUbica", length=15)
//	private String keyUbica;

//	@Column(name="MovApCo", nullable=false, length=2)
//	private String movApCo;

//	@Column(name="Note", length=100)
//	private String note;

//	@Column(name="NrCPX")
//	private int nrCPX;

//	@Column(name="NrDocArrivo", length=10)
//	private String nrDocArrivo;

	@Column(name="NrIdCollo", nullable=false)
	private int nrIdCollo;

	@Column(name="NrLista", length=21)
	private String nrLista;

//	@Column(name="NrRifCliente")
//	private int nrRifCliente;

//	@Column(name="NrSovraStp")
//	private int nrSovraStp;

//	@Column(name="OraCrea")
//	private int oraCrea;

//	@Column(name="OraGen")
//	private int oraGen;

//	@Column(name="OraUbica")
//	private int oraUbica;

//	@Column(name="Pancale", length=20)
//	private String pancale;

	@Column(name="PesoKg")
	private Double pesoKg;

	@Column(name="PezziCollo", nullable=false)
	private int pezziCollo;

//	private int progint;

//	@Column(name="ProgOrdine")
//	private int progOrdine;

//	@Column(name="ProgOrdineS", length=50)
//	private String progOrdineS;

//	@Column(name="RicUbi", length=1)
//	private String ricUbi;

//	@Column(name="RilMagAc", nullable=false, length=1)
//	private String rilMagAc;

//	@Column(name="RilpzEventi", nullable=false, length=2)
//	private String rilpzEventi;

//	@Column(name="Sessione", length=50)
//	private String sessione;

	@Column(name="Stato", length=2)
	private String stato;

//	@Column(name="Tara")
//	private int tara;

//	@Column(name="TipoRif", length=6)
//	private String tipoRif;

//	@Column(name="Ubicato", length=2)
//	private String ubicato;

//	@Column(name="Utente", length=20)
//	private String utente;

//	@Column(name="UtenteUbica", length=50)
//	private String utenteUbica;

	@Column(name="Volume", nullable=false)
	private int volume;

	public ColliImballo() {}

	public int getIdColliImballo() {
		return this.idColliImballo;
	}

	public void setIdColliImballo(int idColliImballo) {
		this.idColliImballo = idColliImballo;
	}

//	public String getAbilita() {
//		return this.abilita;
//	}
//
//	public void setAbilita(String abilita) {
//		this.abilita = abilita;
//	}
//
//	public int getAnno() {
//		return this.anno;
//	}
//
//	public void setAnno(int anno) {
//		this.anno = anno;
//	}

	public String getBarCodeImb() {
		return this.barCodeImb;
	}

	public void setBarCodeImb(String barCodeImb) {
		this.barCodeImb = barCodeImb;
	}

	public String getCancellato() {
		return this.cancellato;
	}

	public void setCancellato(String cancellato) {
		this.cancellato = cancellato;
	}

//	public String getCaricoMag() {
//		return this.caricoMag;
//	}
//
//	public void setCaricoMag(String caricoMag) {
//		this.caricoMag = caricoMag;
//	}
//
//	public String getCodCliente() {
//		return this.codCliente;
//	}
//
//	public void setCodCliente(String codCliente) {
//		this.codCliente = codCliente;
//	}
//
//	public String getCodDestinazione() {
//		return this.codDestinazione;
//	}
//
//	public void setCodDestinazione(String codDestinazione) {
//		this.codDestinazione = codDestinazione;
//	}

	public String getCodFormato() {
		return this.codFormato;
	}

	public void setCodFormato(String codFormato) {
		this.codFormato = codFormato;
	}

//	public int getCodFornitore() {
//		return this.codFornitore;
//	}
//
//	public void setCodFornitore(int codFornitore) {
//		this.codFornitore = codFornitore;
//	}
//
//	public String getColloUoC() {
//		return this.colloUoC;
//	}
//
//	public void setColloUoC(String colloUoC) {
//		this.colloUoC = colloUoC;
//	}
//
//	public String getCtrlEtic() {
//		return this.ctrlEtic;
//	}
//
//	public void setCtrlEtic(String ctrlEtic) {
//		this.ctrlEtic = ctrlEtic;
//	}
//
//	public Timestamp getDataCrea() {
//		return this.dataCrea;
//	}
//
//	public void setDataCrea(Timestamp dataCrea) {
//		this.dataCrea = dataCrea;
//	}
//
//	public Timestamp getDataDocArrivo() {
//		return this.dataDocArrivo;
//	}
//
//	public void setDataDocArrivo(Timestamp dataDocArrivo) {
//		this.dataDocArrivo = dataDocArrivo;
//	}
//
//	public Timestamp getDataGen() {
//		return this.dataGen;
//	}
//
//	public void setDataGen(Timestamp dataGen) {
//		this.dataGen = dataGen;
//	}
//
//	public Timestamp getDataUbica() {
//		return this.dataUbica;
//	}
//
//	public void setDataUbica(Timestamp dataUbica) {
//		this.dataUbica = dataUbica;
//	}
//
//	public String getEticPiano() {
//		return this.eticPiano;
//	}
//
//	public void setEticPiano(String eticPiano) {
//		this.eticPiano = eticPiano;
//	}
//
//	public String getFase() {
//		return this.fase;
//	}
//
//	public void setFase(String fase) {
//		this.fase = fase;
//	}
//
//	public String getGenerato() {
//		return this.generato;
//	}
//
//	public void setGenerato(String generato) {
//		this.generato = generato;
//	}
//
//	public String getGenFile() {
//		return this.genFile;
//	}
//
//	public void setGenFile(String genFile) {
//		this.genFile = genFile;
//	}
//
//	public String getGenMovMag() {
//		return this.genMovMag;
//	}
//
//	public void setGenMovMag(String genMovMag) {
//		this.genMovMag = genMovMag;
//	}
//
//	public String getId_Box() {
//		return this.id_Box;
//	}
//
//	public void setId_Box(String id_Box) {
//		this.id_Box = id_Box;
//	}
//
//	public int getIdAttCliente() {
//		return this.idAttCliente;
//	}
//
//	public void setIdAttCliente(int idAttCliente) {
//		this.idAttCliente = idAttCliente;
//	}
//
//	public int getIdDestina() {
//		return this.idDestina;
//	}
//
//	public void setIdDestina(int idDestina) {
//		this.idDestina = idDestina;
//	}

	public String getKeyColloSpe() {
		return this.keyColloSpe;
	}

	public void setKeyColloSpe(String keyColloSpe) {
		this.keyColloSpe = keyColloSpe;
	}

//	public String getKeyUbica() {
//		return this.keyUbica;
//	}
//
//	public void setKeyUbica(String keyUbica) {
//		this.keyUbica = keyUbica;
//	}
//
//	public String getMovApCo() {
//		return this.movApCo;
//	}
//
//	public void setMovApCo(String movApCo) {
//		this.movApCo = movApCo;
//	}
//
//	public String getNote() {
//		return this.note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}
//
//	public int getNrCPX() {
//		return this.nrCPX;
//	}
//
//	public void setNrCPX(int nrCPX) {
//		this.nrCPX = nrCPX;
//	}
//
//	public String getNrDocArrivo() {
//		return this.nrDocArrivo;
//	}
//
//	public void setNrDocArrivo(String nrDocArrivo) {
//		this.nrDocArrivo = nrDocArrivo;
//	}

	public int getNrIdCollo() {
		return this.nrIdCollo;
	}

	public void setNrIdCollo(int nrIdCollo) {
		this.nrIdCollo = nrIdCollo;
	}

	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

//	public int getNrRifCliente() {
//		return this.nrRifCliente;
//	}
//
//	public void setNrRifCliente(int nrRifCliente) {
//		this.nrRifCliente = nrRifCliente;
//	}
//
//	public int getNrSovraStp() {
//		return this.nrSovraStp;
//	}
//
//	public void setNrSovraStp(int nrSovraStp) {
//		this.nrSovraStp = nrSovraStp;
//	}
//
//	public int getOraCrea() {
//		return this.oraCrea;
//	}
//
//	public void setOraCrea(int oraCrea) {
//		this.oraCrea = oraCrea;
//	}
//
//	public int getOraGen() {
//		return this.oraGen;
//	}
//
//	public void setOraGen(int oraGen) {
//		this.oraGen = oraGen;
//	}
//
//	public int getOraUbica() {
//		return this.oraUbica;
//	}
//
//	public void setOraUbica(int oraUbica) {
//		this.oraUbica = oraUbica;
//	}
//
//	public String getPancale() {
//		return this.pancale;
//	}
//
//	public void setPancale(String pancale) {
//		this.pancale = pancale;
//	}
	
	public Double getPesoKg() {
		return this.pesoKg;
	}

	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	}

	public int getPezziCollo() {
		return this.pezziCollo;
	}

	public void setPezziCollo(int pezziCollo) {
		this.pezziCollo = pezziCollo;
	}

//	public int getProgint() {
//		return this.progint;
//	}
//
//	public void setProgint(int progint) {
//		this.progint = progint;
//	}
//
//	public int getProgOrdine() {
//		return this.progOrdine;
//	}
//
//	public void setProgOrdine(int progOrdine) {
//		this.progOrdine = progOrdine;
//	}
//
//	public String getProgOrdineS() {
//		return this.progOrdineS;
//	}
//
//	public void setProgOrdineS(String progOrdineS) {
//		this.progOrdineS = progOrdineS;
//	}
//
//	public String getRicUbi() {
//		return this.ricUbi;
//	}
//
//	public void setRicUbi(String ricUbi) {
//		this.ricUbi = ricUbi;
//	}
//
//	public String getRilMagAc() {
//		return this.rilMagAc;
//	}
//
//	public void setRilMagAc(String rilMagAc) {
//		this.rilMagAc = rilMagAc;
//	}
//
//	public String getRilpzEventi() {
//		return this.rilpzEventi;
//	}
//
//	public void setRilpzEventi(String rilpzEventi) {
//		this.rilpzEventi = rilpzEventi;
//	}
//
//	public String getSessione() {
//		return this.sessione;
//	}
//
//	public void setSessione(String sessione) {
//		this.sessione = sessione;
//	}
//
	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
//
//	public int getTara() {
//		return this.tara;
//	}
//
//	public void setTara(int tara) {
//		this.tara = tara;
//	}
//
//	public String getTipoRif() {
//		return this.tipoRif;
//	}
//
//	public void setTipoRif(String tipoRif) {
//		this.tipoRif = tipoRif;
//	}
//
//	public String getUbicato() {
//		return this.ubicato;
//	}
//
//	public void setUbicato(String ubicato) {
//		this.ubicato = ubicato;
//	}
//
//	public String getUtente() {
//		return this.utente;
//	}
//
//	public void setUtente(String utente) {
//		this.utente = utente;
//	}
//
//	public String getUtenteUbica() {
//		return this.utenteUbica;
//	}
//
//	public void setUtenteUbica(String utenteUbica) {
//		this.utenteUbica = utenteUbica;
//	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
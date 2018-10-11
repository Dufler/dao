package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;

import it.ltc.utility.miscellanea.time.DateConverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the pakiTesta database table.
 * 
 */
@Entity
@Table(name="pakiTesta")
//@NamedQuery(name="PakiTesta.findAll", query="SELECT p FROM PakiTesta p")
public class PakiTesta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idTestaPaki;

	/**
	 * Può assumere solo 2 valori: SI o NO, NO è il valore di default.
	 * Nel caso in cui sia a "SI" permette di poter riscontrare un numero superiore di pezzi rispetto al dichiarato. Non permette comunque di inserire prodotti non presenti.
	 */
	@Column(name="AbilitaEccedenze", nullable=false, length=2)
	private String abilitaEccedenze;
//
//	@Column(name="AbilitaGestione", nullable=false, length=10)
//	private String abilitaGestione;

	@Column(name="Anno", length=2)
	private String anno;

//	@Column(name="AttivaCasse", nullable=false, length=2)
//	private String attivaCasse;

//	@Column(name="AttivaRiscontro", length=10)
//	private String attivaRiscontro;

//	@Column(name="Blocco", length=2)
//	private String blocco;

//	@Column(name="CodCorriere", length=50)
//	private String codCorriere;

	@Column(name="CodFornitore", length=20)
	private String codFornitore;

//	@Column(name="Controllato", length=2)
//	private String controllato;

	@Column(name="Creazione", nullable=false, insertable=false)
	private Timestamp creazione;

	@Column(name="DataArrivo")
	private Timestamp dataArrivo;

	@Column(name="DataGenFile")
	private Timestamp dataGenFile;

	@Column(name="DataInizio")
	private Timestamp dataInizio;

	@Column(name="DataPaki")
	private Timestamp dataPaki;

//	@Column(name="DescCorriere", length=50)
//	private String descCorriere;

//	@Column(name="Esito", length=6)
//	private String esito;

	/**
	 * Indica lo stato di trasmissione delle info sul riscontro del carico al cliente:
	 * N: default, non trasmesso
	 * C: carico con ordine a fornitore, riscontro parziale di tutto l'acquistato ma va comunque trasmesso.
	 * F: ordine regolare lavorato e chiuso
	 * T: trasmesso al cliente, stato finale.
	 */
	@Column(name="FlagTra", length=1)
	private String flagTra;

	/**
	 * SI quando ce lo da il cliente, NO quando viene immesso dal customer care. Di default è SI.
	 */
	@Column(name="FlussoDichiarato", nullable=false, length=2)
	private String flussoDichiarato;

	@Column(name="GeneratoFile", length=2)
	private String generatoFile;

	@Column(name="GeneratoMov", length=2)
	private String generatoMov;

//	@Column(name="GeneratoPack", length=2)
//	private String generatoPack;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

	@Column(name="IdFornitore")
	private int idFornitore;

	@Column(name="Letto", length=50)
	private String letto;

//	@Column(name="NomeFileFlusso", length=50)
//	private String nomeFileFlusso;
	
	@Column(name="Note", length=250)
	private String note;

//	@Column(name="NrConfOrdAcq", length=50)
//	private String nrConfOrdAcq;

	/**
	 * Riferimento del documento con cui è arrivata la merce.
	 */
	@Column(name="NrDocInterno", length=40)
	private String nrDocInterno;

	/**
	 * Riferimento del cliente.
	 */
	@Column(name="NrPaki", length=30)
	private String nrPaki;

	@Column(name="OraGenFile")
	private Integer oraGenFile;

	@Column(name="OraInizio")
	private Integer oraInizio;

//	@Column(name="Prenotato", length=2)
//	private String prenotato;

	/**
	 * Quantità totale del riscontrato.
	 */
	@Column(name="QtaTotAre")
	private int qtaTotAre;

	/**
	 * Quantità totale del dichiarato.
	 */
	@Column(name="QtaTotAto")
	private int qtaTotAto;
//
//	@Column(name="QtaTotDif")
//	private int qtaTotDif;
//
//	@Column(name="QtaTotPersa")
//	private int qtaTotPersa;

	@Column(name="RagSocFor", length=50)
	private String ragSocFor;

//	@Column(name="SiglaDocOri", length=2)
//	private String siglaDocOri;

	@Column(name="Stagione", length=4)
	private String stagione;

	/**
	 * Gli stati possibili sono:
	 * INSERITO: lo stato di default appena viene inserito a sistema, significa che esiste sono il forma informatica.
	 * ARRIVATO: indica che il carico è arrivato presso i nostri magazzini
	 * IN_LAVORAZIONE: indica che il carico è in fase di riscontro
	 * LAVORATO: indica che il carico è stato completamente riscontrato, i movimenti di magazzino non sono stati ancora fatti.
	 * CHIUSO: indica che il carico è stato riscontrato e caricato a magazzino.
	 * ANNULLATO: indica che il carico è stato annullato e non verrà più gestito, rimane per fini storici o di controllo dentro i nostri sistemi.
	 */
	@Column(name="Stato", length=15)
	private String stato;

//	@Column(name="TipoCarico", length=50)
//	private String tipoCarico;

	/**
	 * Tipologia del carico, viene verificata tramite i valori presenti nella tabella pakiTestaTipo.
	 */
	@Column(name="Tipodocumento", nullable=false, length=20)
	private String tipodocumento;
	
	/**
	 * Tipo del documento che accompagna la merce in ingresso.
	 */
	@Column(name="TipoDoc", length=20)
	private String tipoDoc;

//	@Column(name="TipoGenFile", length=3)
//	private String tipoGenFile;

//	@Column(name="TipoMerce", nullable=false, length=50)
//	private String tipoMerce;

	/**
	 * Può assumere i valori: "INS" o "ART" dove "INS" permette di inserire nuovi prodotti in fase di riscontro e "ART", il default, non lo consente. 
	 */
	@Column(name="TipoPack", length=3)
	private String tipoPack;

//	@Column(name="TipoPacking", length=20)
//	private String tipoPacking;

//	@Column(name="TipoRiscontro", length=2)
//	private String tipoRiscontro;

	public PakiTesta() {}
	
	@PrePersist
	public void prePersist() {
		Date now = new Date();
		Timestamp stamp = new Timestamp(now.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		qtaTotAre = 0;
		//dataInizio = new Timestamp(now.getTime());
		//oraInizio = DateConverter.getOraComeIntero(dataInizio);
		//dataInizio = DateConverter.ripulisciTimestap(dataInizio);
		creazione = stamp;
		setDataOraInizio(stamp);
		anno = sdf.format(now);
		stato = "INSERITO";
		generatoMov = "NO";
		generatoFile = "NO";
		letto = "NO";
		flagTra = "N";
		if (flussoDichiarato == null) flussoDichiarato = "SI";
		if (stagione == null) stagione = "";
		if (tipoPack == null) tipoPack = "ART";
		if (abilitaEccedenze == null) abilitaEccedenze = "NO";
		if (stagione == null) stagione = "CO" + anno;
	}
	
	public void setDataOraInizio(Timestamp time) {
		if (time == null)
			time = new Timestamp(new Date().getTime());
		oraInizio = DateConverter.getOraComeIntero(time);
		dataInizio = DateConverter.ripulisciTimestap(time);
	}
	
	public void setDataOraGenerazione(Timestamp time) {
		if (time == null)
			time = new Timestamp(new Date().getTime());
		oraGenFile = DateConverter.getOraComeIntero(time);
		dataGenFile = DateConverter.ripulisciTimestap(time);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTestaPaki;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PakiTesta other = (PakiTesta) obj;
		if (idTestaPaki != other.idTestaPaki)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PakiTesta [idTestaPaki=" + idTestaPaki + ", codFornitore=" + codFornitore + ", creazione=" + creazione
				+ ", nrPaki=" + nrPaki + ", qtaTotAre=" + qtaTotAre + ", qtaTotAto=" + qtaTotAto + ", stato=" + stato
				+ ", tipodocumento=" + tipodocumento + "]";
	}

	public int getIdTestaPaki() {
		return this.idTestaPaki;
	}

	public void setIdTestaPaki(int idTestaPaki) {
		this.idTestaPaki = idTestaPaki;
	}

	public String getAbilitaEccedenze() {
		return this.abilitaEccedenze;
	}

	public void setAbilitaEccedenze(String abilitaEccedenze) {
		this.abilitaEccedenze = abilitaEccedenze;
	}
//
//	public String getAbilitaGestione() {
//		return this.abilitaGestione;
//	}
//
//	public void setAbilitaGestione(String abilitaGestione) {
//		this.abilitaGestione = abilitaGestione;
//	}

	public String getAnno() {
		return this.anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

//	public String getAttivaCasse() {
//		return this.attivaCasse;
//	}
//
//	public void setAttivaCasse(String attivaCasse) {
//		this.attivaCasse = attivaCasse;
//	}
//
//	public String getAttivaRiscontro() {
//		return this.attivaRiscontro;
//	}
//
//	public void setAttivaRiscontro(String attivaRiscontro) {
//		this.attivaRiscontro = attivaRiscontro;
//	}
//
//	public String getBlocco() {
//		return this.blocco;
//	}
//
//	public void setBlocco(String blocco) {
//		this.blocco = blocco;
//	}
//
//	public String getCodCorriere() {
//		return this.codCorriere;
//	}
//
//	public void setCodCorriere(String codCorriere) {
//		this.codCorriere = codCorriere;
//	}

	public String getCodFornitore() {
		return this.codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

//	public String getControllato() {
//		return this.controllato;
//	}
//
//	public void setControllato(String controllato) {
//		this.controllato = controllato;
//	}

	public Timestamp getCreazione() {
		return this.creazione;
	}

	public void setCreazione(Timestamp creazione) {
		this.creazione = creazione;
	}

	public Timestamp getDataArrivo() {
		return this.dataArrivo;
	}

	public void setDataArrivo(Timestamp dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public Timestamp getDataGenFile() {
		return this.dataGenFile;
	}

	public void setDataGenFile(Timestamp dataGenFile) {
		this.dataGenFile = dataGenFile;
	}

	public Timestamp getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataPaki() {
		return this.dataPaki;
	}

	public void setDataPaki(Timestamp dataPaki) {
		this.dataPaki = dataPaki;
	}

//	public String getDescCorriere() {
//		return this.descCorriere;
//	}
//
//	public void setDescCorriere(String descCorriere) {
//		this.descCorriere = descCorriere;
//	}
//
//	public String getEsito() {
//		return this.esito;
//	}
//
//	public void setEsito(String esito) {
//		this.esito = esito;
//	}
//
	public String getFlagTra() {
		return this.flagTra;
	}

	public void setFlagTra(String flagTra) {
		this.flagTra = flagTra;
	}

	public String getFlussoDichiarato() {
		return this.flussoDichiarato;
	}

	public void setFlussoDichiarato(String flussoDichiarato) {
		this.flussoDichiarato = flussoDichiarato;
	}

	public String getGeneratoFile() {
		return this.generatoFile;
	}

	public void setGeneratoFile(String generatoFile) {
		this.generatoFile = generatoFile;
	}

	public String getGeneratoMov() {
		return this.generatoMov;
	}

	public void setGeneratoMov(String generatoMov) {
		this.generatoMov = generatoMov;
	}
//
//	public String getGeneratoPack() {
//		return this.generatoPack;
//	}
//
//	public void setGeneratoPack(String generatoPack) {
//		this.generatoPack = generatoPack;
//	}
//
//	public int getIdAttCliente() {
//		return this.idAttCliente;
//	}
//
//	public void setIdAttCliente(int idAttCliente) {
//		this.idAttCliente = idAttCliente;
//	}

	public int getIdFornitore() {
		return this.idFornitore;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLetto() {
		return this.letto;
	}

	public void setLetto(String letto) {
		this.letto = letto;
	}
//
//	public String getNomeFileFlusso() {
//		return this.nomeFileFlusso;
//	}
//
//	public void setNomeFileFlusso(String nomeFileFlusso) {
//		this.nomeFileFlusso = nomeFileFlusso;
//	}
//
//	public String getNrConfOrdAcq() {
//		return this.nrConfOrdAcq;
//	}
//
//	public void setNrConfOrdAcq(String nrConfOrdAcq) {
//		this.nrConfOrdAcq = nrConfOrdAcq;
//	}
//
	public String getNrDocInterno() {
		return this.nrDocInterno;
	}

	public void setNrDocInterno(String nrDocInterno) {
		this.nrDocInterno = nrDocInterno;
	}

	public String getNrPaki() {
		return this.nrPaki;
	}

	public void setNrPaki(String nrPaki) {
		this.nrPaki = nrPaki;
	}

	public Integer getOraGenFile() {
		return this.oraGenFile;
	}

	public void setOraGenFile(Integer oraGenFile) {
		this.oraGenFile = oraGenFile;
	}

	public Integer getOraInizio() {
		return this.oraInizio;
	}

	public void setOraInizio(Integer oraInizio) {
		this.oraInizio = oraInizio;
	}
//
//	public String getPrenotato() {
//		return this.prenotato;
//	}
//
//	public void setPrenotato(String prenotato) {
//		this.prenotato = prenotato;
//	}

	public int getQtaTotAre() {
		return this.qtaTotAre;
	}

	public void setQtaTotAre(int qtaTotAre) {
		this.qtaTotAre = qtaTotAre;
	}

	public int getQtaTotAto() {
		return this.qtaTotAto;
	}

	public void setQtaTotAto(int qtaTotAto) {
		this.qtaTotAto = qtaTotAto;
	}
//
//	public int getQtaTotDif() {
//		return this.qtaTotDif;
//	}
//
//	public void setQtaTotDif(int qtaTotDif) {
//		this.qtaTotDif = qtaTotDif;
//	}
//
//	public int getQtaTotPersa() {
//		return this.qtaTotPersa;
//	}
//
//	public void setQtaTotPersa(int qtaTotPersa) {
//		this.qtaTotPersa = qtaTotPersa;
//	}

	public String getRagSocFor() {
		return this.ragSocFor;
	}

	public void setRagSocFor(String ragSocFor) {
		this.ragSocFor = ragSocFor;
	}

//	public String getSiglaDocOri() {
//		return this.siglaDocOri;
//	}
//
//	public void setSiglaDocOri(String siglaDocOri) {
//		this.siglaDocOri = siglaDocOri;
//	}

	public String getStagione() {
		return this.stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
//
//	public String getTipoCarico() {
//		return this.tipoCarico;
//	}
//
//	public void setTipoCarico(String tipoCarico) {
//		this.tipoCarico = tipoCarico;
//	}

	public String getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	
	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

//	public String getTipoGenFile() {
//		return this.tipoGenFile;
//	}
//
//	public void setTipoGenFile(String tipoGenFile) {
//		this.tipoGenFile = tipoGenFile;
//	}

//	public String getTipoMerce() {
//		return this.tipoMerce;
//	}
//
//	public void setTipoMerce(String tipoMerce) {
//		this.tipoMerce = tipoMerce;
//	}
//
	public String getTipoPack() {
		return this.tipoPack;
	}

	public void setTipoPack(String tipoPack) {
		this.tipoPack = tipoPack;
	}
//
//	public String getTipoPacking() {
//		return this.tipoPacking;
//	}
//
//	public void setTipoPacking(String tipoPacking) {
//		this.tipoPacking = tipoPacking;
//	}
//
//	public String getTipoRiscontro() {
//		return this.tipoRiscontro;
//	}
//
//	public void setTipoRiscontro(String tipoRiscontro) {
//		this.tipoRiscontro = tipoRiscontro;
//	}
	
	

}
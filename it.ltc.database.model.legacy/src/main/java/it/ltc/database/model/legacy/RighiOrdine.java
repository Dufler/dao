package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the RighiOrdine database table.
 * 
 */
@Entity
@Table(name="RighiOrdine")
@NamedQueries({
	@NamedQuery(name="RighiOrdine.totaleOrdinatoPerOrdine", query="SELECT SUM(p.qtaSpedizione) FROM RighiOrdine p WHERE p.idTestataOrdine = :ordine"),
	@NamedQuery(name="RighiOrdine.totaleImballatoPerOrdine", query="SELECT SUM(p.qtaImballata) FROM RighiOrdine p WHERE p.idTestataOrdine = :ordine"),
	@NamedQuery(name="RighiOrdine.totaleAssegnatoPerOrdine", query="SELECT SUM(p.qtaAssegnata) FROM RighiOrdine p WHERE p.idTestataOrdine = :ordine"),
	@NamedQuery(name="RighiOrdine.totaliPerOrdine", query="SELECT NEW it.ltc.database.model.legacy.model.TestataOrdiniTotali(SUM(p.qtaSpedizione), SUM(p.qtaImballata), SUM(p.qtaAssegnata)) FROM RighiOrdine p WHERE p.idTestataOrdine = :ordine")
})
public class RighiOrdine implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idRigoOrdine;
	
	@Column(name="IdTestataOrdine", nullable=false)
	private int idTestataOrdine;

	@Column(name="Area", length=2, columnDefinition="char(2)")
	private String area;

	@Column(name="BarraEAN", length=50)
	private String barraEAN;

	@Column(name="BarraUPC", length=50)
	private String barraUPC;

	@Column(name="Box", length=2, columnDefinition="char(2)")
	private String box;

//	@Column(name="CasseDaUbicare", nullable=false)
//	private int casseDaUbicare;

//	@Column(name="CodAssort", length=25)
//	private String codAssort;

	@Column(name="CodBarre", length=50)
	private String codBarre;

//	@Column(name="CodCliente", length=6)
//	private String codCliente;

//	@Column(name="Codice", length=23)
//	private String codice;

	@Column(name="CodiceArticolo", length=50)
	private String codiceArticolo;

	@Column(name="Colore", length=50)
	private String colore;

//	@Column(name="Compcassa", length=250)
//	private String compcassa;

	@Column(name="Composizione", length=100)
	private String composizione;

	@Column(name="Corsia", length=3, columnDefinition="char(3)")
	private String corsia;

//	@Column(name="Datagenmov")
//	private Date datagenmov;

	@Column(name="DataOrdine", columnDefinition="datetime")
	private Date dataOrdine;

	@Column(name="Descrizione", length=100)
	private String descrizione;

//	@Column(name="Esclusione", length=2)
//	private String esclusione;

//	@Column(name="FlagDisponibile", length=1)
//	private String flagDisponibile;

	@Column(name="IdDestina")
	private int idDestina;

//	@Column(nullable=false)
//	private int idrigo;

	@Column(name="IdUnicoArt", length=15, nullable=false, columnDefinition="varchar(15)")
	private String idUnicoArt;

	@Column(name="KeyUbiPre", length=15, columnDefinition="char(15)")
	private String keyUbiPre;

//	@Column(name="Listaorigine", nullable=false, length=21)
//	private String listaorigine;

	@Column(name="Magazzino", length=3, nullable=false, columnDefinition="char(3)")
	private String magazzino;

//	@Column(name="Note", length=200)
//	private String note;
	
	@Column(name="NoteCliente", length=30)
	private String noteCliente;

//	@Column(name="NrCasse")
//	private int nrCasse;

	@Column(name="Nrcollo", length=10, columnDefinition="char(10)")
	private String nrcollo;

	@Column(name="NrLista", length=21, nullable=false)
	private String nrLista;

	@Column(name="NrOrdine", length=20)
	private String nrOrdine;

	@Column(name="NrRigo")
	private int nrRigo;

	@Column(name="Numerata", length=20)
	private String numerata;

//	@Column(name="Pezzieffet", nullable=false)
//	private int pezzieffet;

	@Column(name="Piano", length=2, columnDefinition="char(2)")
	private String piano;

	@Column(length=20)
	private String PONumber;

//	@Column(name="Posizione")
//	private int posizione;

	@Column(name="QtaAssegnata", nullable=false)
	private int qtaAssegnata;

	@Column(name="Qtadaubicare", nullable=false)
	private int qtadaubicare;

	@Column(name="QtaImballata", nullable=false)
	private int qtaImballata;

//	@Column(name="QtaPerCassa")
//	private int qtaPerCassa;

//	@Column(name="QtaPrelevata")
//	private int qtaPrelevata;

	@Column(name="QtaSpedizione", nullable=false)
	private int qtaSpedizione;

	@Column(name="Ragstampe1", length=20)
	private String ragstampe1;

	@Column(name="Scaffale", length=3, columnDefinition="char(3)")
	private String scaffale;

//	@Column(name="Stampa", nullable=false, length=1)
//	private String stampa;

//	@Column(name="Stato", length=4)
//	private String stato;

	@Column(name="Taglia", length=20)
	private String taglia;

	@Column(name="Tipoord", length=30)
	private String tipoord;

	@Column(name="Ubicazione", length=100)
	private String ubicazione;
	
	@Column(name="IdArticolo", nullable=false)
	private int idArticolo;
	
	@Transient
	private List<String> seriali;

	public RighiOrdine() {}
	
	@PrePersist
	public void prePersist() {
		qtadaubicare = qtaSpedizione;
		qtaAssegnata = 0;
		numerata = "-";
		area = "  "; //2
		box = "  "; //2
		corsia = "   "; //3
		piano = "  "; //2
		scaffale = "   "; //3
		ubicazione = "";
		nrcollo = "0         ";
		if (PONumber == null) PONumber = "";
	}

	public int getIdRigoOrdine() {
		return this.idRigoOrdine;
	}

	public void setIdRigoOrdine(int idRigoOrdine) {
		this.idRigoOrdine = idRigoOrdine;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getIdTestataOrdine() {
		return idTestataOrdine;
	}

	public void setIdTestataOrdine(int idTestataOrdine) {
		this.idTestataOrdine = idTestataOrdine;
	}

	public String getBarraEAN() {
		return this.barraEAN;
	}

	public void setBarraEAN(String barraEAN) {
		this.barraEAN = barraEAN;
	}

	public String getBarraUPC() {
		return this.barraUPC;
	}

	public void setBarraUPC(String barraUPC) {
		this.barraUPC = barraUPC;
	}

	public String getBox() {
		return this.box;
	}

	public void setBox(String box) {
		this.box = box;
	}

//	public int getCasseDaUbicare() {
//		return this.casseDaUbicare;
//	}
//
//	public void setCasseDaUbicare(int casseDaUbicare) {
//		this.casseDaUbicare = casseDaUbicare;
//	}

//	public String getCodAssort() {
//		return this.codAssort;
//	}
//
//	public void setCodAssort(String codAssort) {
//		this.codAssort = codAssort;
//	}
//
	public String getCodBarre() {
		return this.codBarre;
	}

	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;
	}
//
//	public String getCodCliente() {
//		return this.codCliente;
//	}
//
//	public void setCodCliente(String codCliente) {
//		this.codCliente = codCliente;
//	}
//
//	public String getCodice() {
//		return this.codice;
//	}
//
//	public void setCodice(String codice) {
//		this.codice = codice;
//	}

	public String getCodiceArticolo() {
		return this.codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

//	public String getCompcassa() {
//		return this.compcassa;
//	}
//
//	public void setCompcassa(String compcassa) {
//		this.compcassa = compcassa;
//	}

	public String getComposizione() {
		return this.composizione;
	}

	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}

	public String getCorsia() {
		return this.corsia;
	}

	public void setCorsia(String corsia) {
		this.corsia = corsia;
	}
//
//	public Date getDatagenmov() {
//		return this.datagenmov;
//	}
//
//	public void setDatagenmov(Date datagenmov) {
//		this.datagenmov = datagenmov;
//	}

	public Date getDataOrdine() {
		return this.dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

//	public String getEsclusione() {
//		return this.esclusione;
//	}
//
//	public void setEsclusione(String esclusione) {
//		this.esclusione = esclusione;
//	}

//	public String getFlagDisponibile() {
//		return this.flagDisponibile;
//	}
//
//	public void setFlagDisponibile(String flagDisponibile) {
//		this.flagDisponibile = flagDisponibile;
//	}

	public int getIdDestina() {
		return this.idDestina;
	}

	public void setIdDestina(int idDestina) {
		this.idDestina = idDestina;
	}

//	public int getIdrigo() {
//		return this.idrigo;
//	}
//
//	public void setIdrigo(int idrigo) {
//		this.idrigo = idrigo;
//	}

	public String getIdUnicoArt() {
		return this.idUnicoArt;
	}

	public void setIdUnicoArt(String idUnicoArt) {
		this.idUnicoArt = idUnicoArt;
	}

	public String getKeyUbiPre() {
		return this.keyUbiPre;
	}

	public void setKeyUbiPre(String keyUbiPre) {
		this.keyUbiPre = keyUbiPre;
	}
//
//	public String getListaorigine() {
//		return this.listaorigine;
//	}
//
//	public void setListaorigine(String listaorigine) {
//		this.listaorigine = listaorigine;
//	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

//	public String getNote() {
//		return this.note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}
//
//	public int getNrCasse() {
//		return this.nrCasse;
//	}
//
//	public void setNrCasse(int nrCasse) {
//		this.nrCasse = nrCasse;
//	}
//
	public String getNrcollo() {
		return this.nrcollo;
	}

	public void setNrcollo(String nrcollo) {
		this.nrcollo = nrcollo;
	}

	public String getNoteCliente() {
		return noteCliente;
	}

	public void setNoteCliente(String noteCliente) {
		this.noteCliente = noteCliente;
	}

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

	public int getNrRigo() {
		return this.nrRigo;
	}

	public void setNrRigo(int nrRigo) {
		this.nrRigo = nrRigo;
	}

	public String getNumerata() {
		return this.numerata;
	}

	public void setNumerata(String numerata) {
		this.numerata = numerata;
	}

//	public int getPezzieffet() {
//		return this.pezzieffet;
//	}
//
//	public void setPezzieffet(int pezzieffet) {
//		this.pezzieffet = pezzieffet;
//	}

	public String getPiano() {
		return this.piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public String getPONumber() {
		return this.PONumber;
	}

	public void setPONumber(String PONumber) {
		this.PONumber = PONumber;
	}
//
//	public int getPosizione() {
//		return this.posizione;
//	}
//
//	public void setPosizione(int posizione) {
//		this.posizione = posizione;
//	}
//
	public int getQtaAssegnata() {
		return this.qtaAssegnata;
	}

	public void setQtaAssegnata(int qtaAssegnata) {
		this.qtaAssegnata = qtaAssegnata;
	}

	public int getQtadaubicare() {
		return this.qtadaubicare;
	}

	public void setQtadaubicare(int qtadaubicare) {
		this.qtadaubicare = qtadaubicare;
	}

	public int getQtaImballata() {
		return this.qtaImballata;
	}

	public void setQtaImballata(int qtaImballata) {
		this.qtaImballata = qtaImballata;
	}
//
//	public int getQtaPerCassa() {
//		return this.qtaPerCassa;
//	}
//
//	public void setQtaPerCassa(int qtaPerCassa) {
//		this.qtaPerCassa = qtaPerCassa;
//	}
//
//	public int getQtaPrelevata() {
//		return this.qtaPrelevata;
//	}
//
//	public void setQtaPrelevata(int qtaPrelevata) {
//		this.qtaPrelevata = qtaPrelevata;
//	}

	public int getQtaSpedizione() {
		return this.qtaSpedizione;
	}

	public void setQtaSpedizione(int qtaSpedizione) {
		this.qtaSpedizione = qtaSpedizione;
	}

	public String getRagstampe1() {
		return this.ragstampe1;
	}

	public void setRagstampe1(String ragstampe1) {
		this.ragstampe1 = ragstampe1;
	}

	public String getScaffale() {
		return this.scaffale;
	}

	public void setScaffale(String scaffale) {
		this.scaffale = scaffale;
	}

//	public String getStampa() {
//		return this.stampa;
//	}
//
//	public void setStampa(String stampa) {
//		this.stampa = stampa;
//	}

//	public String getStato() {
//		return this.stato;
//	}
//
//	public void setStato(String stato) {
//		this.stato = stato;
//	}

	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public List<String> getSeriali() {
		return seriali;
	}

	public void setSeriali(List<String> seriali) {
		this.seriali = seriali;
	}

	public String getTipoord() {
		return this.tipoord;
	}

	public void setTipoord(String tipoord) {
		this.tipoord = tipoord;
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

}
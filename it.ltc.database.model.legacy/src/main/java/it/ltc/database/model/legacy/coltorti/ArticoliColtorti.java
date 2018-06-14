//package it.ltc.database.model.legacy.coltorti;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
//import javax.persistence.NamedQuery;
//
//import it.ltc.database.model.legacy.Articoli;
//
//
///**
// * The persistent class for the Articoli database table.
// * 
// */
//@Entity
////@Table(name="Articoli")
//@DiscriminatorValue("not null")
//@NamedQuery(name="ArticoliColtorti.findAll", query="SELECT a FROM ArticoliColtorti a")
//public class ArticoliColtorti extends Articoli implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Column(name="ParticolaritaCliente", length=50)
//	private String particolarita;
//
//	public ArticoliColtorti() {}
//
//	public String getParticolarita() {
//		return particolarita;
//	}
//
//	public void setParticolarita(String particolarita) {
//		this.particolarita = particolarita;
//	}
//
//}
package it.ltc.database.model.legacy.coltorti;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the Articoli database table.
 * 
 */
@Entity
@Table(name="Articoli")
@NamedQuery(name="ArticoliColtorti.findAll", query="SELECT a FROM ArticoliColtorti a")
public class ArticoliColtorti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdArticolo", unique=true, nullable=false)
	private int idArticolo;

//	@Column(name="AggIdOld", nullable=false, length=2, insertable=false)
//	private String aggIdOld;

	@Column(name="AliquotaIVA")
	private BigDecimal aliquotaIVA;

	@Column(name="ArtH")
	private Integer artH;

	@Column(name="ArtL")
	private Integer artL;

	@Column(name="ArtPeso")
	private BigDecimal artPeso;

	@Column(name="ArtZ")
	private Integer artZ;

//	@Column(name="Associato", nullable=false, length=2, insertable=false)
//	private String associato;

	@Column(name="BarraEAN", length=50)
	private String barraEAN;

	@Column(name="BarraUPC", length=50)
	private String barraUPC;

	@Column(name="Categoria", nullable=false, length=150)
	private String categoria;

	@Column(name="CatMercDett", length=50)
	private String catMercDett;

	@Column(name="CatMercGruppo", nullable=false, length=50)
	private String catMercGruppo;

	@Column(name="CodArtInt")
	private int codArtInt;

	@Column(name="CodArtOld", length=50)
	private String codArtOld;

	@Column(name="CodArtStr", nullable=false, length=100)
	private String codArtStr;

	@Column(name="CodBarre", nullable=false, length=50)
	private String codBarre;

//	@Column(name="Collezione", length=1)
//	private String collezione;

	@Column(name="Colore", length=40)
	private String colore;

	@Column(name="Composizione", nullable=false, length=100)
	private String composizione;

//	@Column(name="ConfImballo")
//	private int confImballo;

//	@Column(name="DataAssBarcode")
//	private Timestamp dataAssBarcode;
//
//	@Column(name="DataAssCategoria")
//	private Timestamp dataAssCategoria;
//
//	@Column(name="DataAssCompos")
//	private Timestamp dataAssCompos;
//
//	@Column(name="DataInvent")
//	private Timestamp dataInvent;

	@Column(name="DataModifica")
	private Timestamp dataModifica;

//	@Column(name="Desc_Colore", length=100)
//	private String desc_Colore;

	@Column(name="DescAggiuntiva", nullable=false, length=100)
	private String descAggiuntiva;

	@Column(name="Descrizione", nullable=false, length=100)
	private String descrizione;

//	@Column(name="DescrizioneAgg", columnDefinition="NVARCHAR")
//	private String descrizioneAgg;

//	@Column(length=20)
//	private String desctipocassa;
//
//	@Column(name="Disponibile")
//	private int disponibile;
//
//	@Column(name="Esistenza")
//	private int esistenza;
//
//	@Column(name="FinePromo")
//	private Timestamp finePromo;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

	@Column(name="IdUniArticolo", nullable=false, length=15)
	private String idUniArticolo;

//	@Column(name="ImbH")
//	private int imbH;
//
//	@Column(name="ImbL")
//	private int imbL;
//
//	@Column(name="ImbPeso")
//	private BigDecimal imbPeso;
//
//	@Column(name="ImbVol")
//	private BigDecimal imbVol;
//
//	@Column(name="ImbZ")
//	private int imbZ;
//
//	@Column(name="Impegnato")
//	private int impegnato;
//
//	@Column(name="IniPromo")
//	private Timestamp iniPromo;

	@Column(name="Linea", nullable=false, length=50)
	private String linea;

	@Column(name="MadeIn", nullable=false, length=50)
	private String madeIn;

//	@Column(name="Marchio")
//	private int marchio;

//	@Column(length=30)
//	private String mercpro;

	@Column(name="Modello", nullable=false, length=40)
	private String modello;

//	@Column(name="Numerata", length=50)
//	private String numerata;

//	@Column(name="Obsoleto", nullable=false, length=20, insertable=false)
//	private String obsoleto;

//	@Column(name="OldIdUnicoArticolo", length=30)
//	private String oldIdUnicoArticolo;

//	@Column(name="OpeAssBarcode", length=10)
//	private String opeAssBarcode;

//	@Column(name="OpeAssComo", length=10)
//	private String opeAssComo;

//	@Column(name="PesoNetto")
//	private BigDecimal pesoNetto;

//	@Column(name="PezziCassa")
//	private int pezziCassa;

//	@Column(name="PezziCollo", nullable=false, insertable=false)
//	private int pezziCollo;

//	@Column(name="Pezzicomcas")
//	private int pezzicomcas;

//	@Column(name="Pubb", nullable=false, length=2, insertable=false)
//	private String pubb;

//	@Column(name="QtaConf")
//	private int qtaConf;

//	@Column(name="QtaImba", nullable=false, insertable=false)
//	private int qtaImba;

//	@Column(name="QtaInvent")
//	private int qtaInvent;

//	@Column(name="QtaInventa", nullable=false, insertable=false)
//	private int qtaInventa;

//	@Column(name="RipresaSaldo", nullable=false, length=2, insertable=false)
//	private String ripresaSaldo;

//	@Column(nullable=false, length=2, insertable=false)
//	private String saldoold;

//	@Column(name="ScortaMin")
//	private int scortaMin;

	@Column(name="Stagione", length=30)
	private String stagione;

	@Column(name="Taglia", nullable=false, length=20)
	private String taglia;

	@Column(name="TipoCassa", length=20)
	private String tipoCassa;

//	@Column(name="TotIn")
//	private int totIn;

//	@Column(name="TotOut")
//	private int totOut;

//	@Column(name="UbiPrelievo", length=20)
//	private String ubiPrelievo;

//	@Column(name="UbiScorta", length=50)
//	private String ubiScorta;

//	@Column(name="Um", length=10)
//	private String um;

//	@Column(name="UmPos")
//	private int umPos;

//	@Column(name="Utente", length=15)
//	private String utente;

//	@Column(name="ValAcq")
//	private BigDecimal valAcq;

//	@Column(name="ValPromo")
//	private BigDecimal valPromo;

	@Column(name="ValVen")
	private BigDecimal valVen;

//	@Column(name="Vecchia_Numerata", length=20)
//	private String vecchia_Numerata;

//	@Column(name="Volume")
//	private BigDecimal volume;
	
	@Column(name="note", length=250)
	private String note;
	
	@Column(name="ParticolaritaCliente", length=50)
	private String particolarita;

	public ArticoliColtorti() {}
	
	/**
	 * Setta alcuni valori non nullabili a default prima dell'inserimento.
	 */
	@PrePersist
	public void setDefaultValues() {
		if (madeIn == null)
			madeIn = "";
		if (descAggiuntiva == null)
			descAggiuntiva = "";
		if (composizione == null)
			composizione = "";
		dataModifica = new Timestamp(new Date().getTime());
	}
	
	/**
	 * Imposta automaticamente la data di modifica.
	 */
	@PreUpdate
	public void setUpdateTime() {
		dataModifica = new Timestamp(new Date().getTime());
	}

	public int getIdArticolo() {
		return this.idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

//	public String getAggIdOld() {
//		return this.aggIdOld;
//	}
//
//	public void setAggIdOld(String aggIdOld) {
//		this.aggIdOld = aggIdOld;
//	}

	public BigDecimal getAliquotaIVA() {
		return this.aliquotaIVA;
	}

	public void setAliquotaIVA(BigDecimal aliquotaIVA) {
		this.aliquotaIVA = aliquotaIVA;
	}

	public Integer getArtH() {
		return this.artH;
	}

	public void setArtH(Integer artH) {
		this.artH = artH;
	}

	public Integer getArtL() {
		return this.artL;
	}

	public void setArtL(Integer artL) {
		this.artL = artL;
	}

	public BigDecimal getArtPeso() {
		return this.artPeso;
	}

	public void setArtPeso(BigDecimal artPeso) {
		this.artPeso = artPeso;
	}

	public Integer getArtZ() {
		return this.artZ;
	}

	public void setArtZ(Integer artZ) {
		this.artZ = artZ;
	}

//	public String getAssociato() {
//		return this.associato;
//	}
//
//	public void setAssociato(String associato) {
//		this.associato = associato;
//	}

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

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCatMercDett() {
		return this.catMercDett;
	}

	public void setCatMercDett(String catMercDett) {
		this.catMercDett = catMercDett;
	}

	public String getCatMercGruppo() {
		return this.catMercGruppo;
	}

	public void setCatMercGruppo(String catMercGruppo) {
		this.catMercGruppo = catMercGruppo;
	}

	public int getCodArtInt() {
		return this.codArtInt;
	}

	public void setCodArtInt(int codArtInt) {
		this.codArtInt = codArtInt;
	}

	public String getCodArtOld() {
		return this.codArtOld;
	}

	public void setCodArtOld(String codArtOld) {
		this.codArtOld = codArtOld;
	}

	public String getCodArtStr() {
		return this.codArtStr;
	}

	public void setCodArtStr(String codArtStr) {
		this.codArtStr = codArtStr;
	}

	public String getCodBarre() {
		return this.codBarre;
	}

	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;
	}

//	public String getCollezione() {
//		return this.collezione;
//	}
//
//	public void setCollezione(String collezione) {
//		this.collezione = collezione;
//	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getComposizione() {
		return this.composizione;
	}

	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}

//	public int getConfImballo() {
//		return this.confImballo;
//	}
//
//	public void setConfImballo(int confImballo) {
//		this.confImballo = confImballo;
//	}
//
//	public Timestamp getDataAssBarcode() {
//		return this.dataAssBarcode;
//	}
//
//	public void setDataAssBarcode(Timestamp dataAssBarcode) {
//		this.dataAssBarcode = dataAssBarcode;
//	}
//
//	public Timestamp getDataAssCategoria() {
//		return this.dataAssCategoria;
//	}
//
//	public void setDataAssCategoria(Timestamp dataAssCategoria) {
//		this.dataAssCategoria = dataAssCategoria;
//	}
//
//	public Timestamp getDataAssCompos() {
//		return this.dataAssCompos;
//	}
//
//	public void setDataAssCompos(Timestamp dataAssCompos) {
//		this.dataAssCompos = dataAssCompos;
//	}
//
//	public Timestamp getDataInvent() {
//		return this.dataInvent;
//	}
//
//	public void setDataInvent(Timestamp dataInvent) {
//		this.dataInvent = dataInvent;
//	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

//	public String getDesc_Colore() {
//		return this.desc_Colore;
//	}
//
//	public void setDesc_Colore(String desc_Colore) {
//		this.desc_Colore = desc_Colore;
//	}

	public String getDescAggiuntiva() {
		return this.descAggiuntiva;
	}

	public void setDescAggiuntiva(String descAggiuntiva) {
		this.descAggiuntiva = descAggiuntiva;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

//	public String getDescrizioneAgg() {
//		return this.descrizioneAgg;
//	}
//
//	public void setDescrizioneAgg(String descrizioneAgg) {
//		this.descrizioneAgg = descrizioneAgg;
//	}

//	public String getDesctipocassa() {
//		return this.desctipocassa;
//	}
//
//	public void setDesctipocassa(String desctipocassa) {
//		this.desctipocassa = desctipocassa;
//	}
//
//	public int getDisponibile() {
//		return this.disponibile;
//	}
//
//	public void setDisponibile(int disponibile) {
//		this.disponibile = disponibile;
//	}
//
//	public int getEsistenza() {
//		return this.esistenza;
//	}
//
//	public void setEsistenza(int esistenza) {
//		this.esistenza = esistenza;
//	}
//
//	public Timestamp getFinePromo() {
//		return this.finePromo;
//	}
//
//	public void setFinePromo(Timestamp finePromo) {
//		this.finePromo = finePromo;
//	}
//
//	public int getIdAttCliente() {
//		return this.idAttCliente;
//	}
//
//	public void setIdAttCliente(int idAttCliente) {
//		this.idAttCliente = idAttCliente;
//	}

	public String getIdUniArticolo() {
		return this.idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

//	public int getImbH() {
//		return this.imbH;
//	}
//
//	public void setImbH(int imbH) {
//		this.imbH = imbH;
//	}
//
//	public int getImbL() {
//		return this.imbL;
//	}
//
//	public void setImbL(int imbL) {
//		this.imbL = imbL;
//	}
//
//	public BigDecimal getImbPeso() {
//		return this.imbPeso;
//	}
//
//	public void setImbPeso(BigDecimal imbPeso) {
//		this.imbPeso = imbPeso;
//	}
//
//	public BigDecimal getImbVol() {
//		return this.imbVol;
//	}
//
//	public void setImbVol(BigDecimal imbVol) {
//		this.imbVol = imbVol;
//	}
//
//	public int getImbZ() {
//		return this.imbZ;
//	}
//
//	public void setImbZ(int imbZ) {
//		this.imbZ = imbZ;
//	}
//
//	public int getImpegnato() {
//		return this.impegnato;
//	}
//
//	public void setImpegnato(int impegnato) {
//		this.impegnato = impegnato;
//	}
//
//	public Timestamp getIniPromo() {
//		return this.iniPromo;
//	}
//
//	public void setIniPromo(Timestamp iniPromo) {
//		this.iniPromo = iniPromo;
//	}

	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getMadeIn() {
		return this.madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

//	public int getMarchio() {
//		return this.marchio;
//	}
//
//	public void setMarchio(int marchio) {
//		this.marchio = marchio;
//	}
//
//	public String getMercpro() {
//		return this.mercpro;
//	}
//
//	public void setMercpro(String mercpro) {
//		this.mercpro = mercpro;
//	}

	public String getModello() {
		return this.modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

//	public String getNumerata() {
//		return this.numerata;
//	}
//
//	public void setNumerata(String numerata) {
//		this.numerata = numerata;
//	}
//
//	public String getObsoleto() {
//		return this.obsoleto;
//	}
//
//	public void setObsoleto(String obsoleto) {
//		this.obsoleto = obsoleto;
//	}

//	public String getOldIdUnicoArticolo() {
//		return this.oldIdUnicoArticolo;
//	}
//
//	public void setOldIdUnicoArticolo(String oldIdUnicoArticolo) {
//		this.oldIdUnicoArticolo = oldIdUnicoArticolo;
//	}

//	public String getOpeAssBarcode() {
//		return this.opeAssBarcode;
//	}
//
//	public void setOpeAssBarcode(String opeAssBarcode) {
//		this.opeAssBarcode = opeAssBarcode;
//	}
//
//	public String getOpeAssComo() {
//		return this.opeAssComo;
//	}
//
//	public void setOpeAssComo(String opeAssComo) {
//		this.opeAssComo = opeAssComo;
//	}
//
//	public BigDecimal getPesoNetto() {
//		return this.pesoNetto;
//	}
//
//	public void setPesoNetto(BigDecimal pesoNetto) {
//		this.pesoNetto = pesoNetto;
//	}
//
//	public int getPezziCassa() {
//		return this.pezziCassa;
//	}
//
//	public void setPezziCassa(int pezziCassa) {
//		this.pezziCassa = pezziCassa;
//	}
//
//	public int getPezziCollo() {
//		return this.pezziCollo;
//	}
//
//	public void setPezziCollo(int pezziCollo) {
//		this.pezziCollo = pezziCollo;
//	}
//
//	public int getPezzicomcas() {
//		return this.pezzicomcas;
//	}
//
//	public void setPezzicomcas(int pezzicomcas) {
//		this.pezzicomcas = pezzicomcas;
//	}
//
//	public String getPubb() {
//		return this.pubb;
//	}
//
//	public void setPubb(String pubb) {
//		this.pubb = pubb;
//	}
//
//	public int getQtaConf() {
//		return this.qtaConf;
//	}
//
//	public void setQtaConf(int qtaConf) {
//		this.qtaConf = qtaConf;
//	}
//
//	public int getQtaImba() {
//		return this.qtaImba;
//	}
//
//	public void setQtaImba(int qtaImba) {
//		this.qtaImba = qtaImba;
//	}
//
//	public int getQtaInvent() {
//		return this.qtaInvent;
//	}
//
//	public void setQtaInvent(int qtaInvent) {
//		this.qtaInvent = qtaInvent;
//	}
//
//	public int getQtaInventa() {
//		return this.qtaInventa;
//	}
//
//	public void setQtaInventa(int qtaInventa) {
//		this.qtaInventa = qtaInventa;
//	}
//
//	public String getRipresaSaldo() {
//		return this.ripresaSaldo;
//	}
//
//	public void setRipresaSaldo(String ripresaSaldo) {
//		this.ripresaSaldo = ripresaSaldo;
//	}
//
//	public String getSaldoold() {
//		return this.saldoold;
//	}
//
//	public void setSaldoold(String saldoold) {
//		this.saldoold = saldoold;
//	}
//
//	public int getScortaMin() {
//		return this.scortaMin;
//	}
//
//	public void setScortaMin(int scortaMin) {
//		this.scortaMin = scortaMin;
//	}

	public String getStagione() {
		return this.stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}

	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public String getTipoCassa() {
		return this.tipoCassa;
	}

	public void setTipoCassa(String tipoCassa) {
		this.tipoCassa = tipoCassa;
	}

//	public int getTotIn() {
//		return this.totIn;
//	}
//
//	public void setTotIn(int totIn) {
//		this.totIn = totIn;
//	}
//
//	public int getTotOut() {
//		return this.totOut;
//	}
//
//	public void setTotOut(int totOut) {
//		this.totOut = totOut;
//	}
//
//	public String getUbiPrelievo() {
//		return this.ubiPrelievo;
//	}
//
//	public void setUbiPrelievo(String ubiPrelievo) {
//		this.ubiPrelievo = ubiPrelievo;
//	}
//
//	public String getUbiScorta() {
//		return this.ubiScorta;
//	}
//
//	public void setUbiScorta(String ubiScorta) {
//		this.ubiScorta = ubiScorta;
//	}
//
//	public String getUm() {
//		return this.um;
//	}
//
//	public void setUm(String um) {
//		this.um = um;
//	}
//
//	public int getUmPos() {
//		return this.umPos;
//	}
//
//	public void setUmPos(int umPos) {
//		this.umPos = umPos;
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
//	public BigDecimal getValAcq() {
//		return this.valAcq;
//	}
//
//	public void setValAcq(BigDecimal valAcq) {
//		this.valAcq = valAcq;
//	}
//
//	public BigDecimal getValPromo() {
//		return this.valPromo;
//	}
//
//	public void setValPromo(BigDecimal valPromo) {
//		this.valPromo = valPromo;
//	}

	public BigDecimal getValVen() {
		return this.valVen;
	}

	public void setValVen(BigDecimal valVen) {
		this.valVen = valVen;
	}

//	public String getVecchia_Numerata() {
//		return this.vecchia_Numerata;
//	}
//
//	public void setVecchia_Numerata(String vecchia_Numerata) {
//		this.vecchia_Numerata = vecchia_Numerata;
//	}

//	public BigDecimal getVolume() {
//		return this.volume;
//	}
//
//	public void setVolume(BigDecimal volume) {
//		this.volume = volume;
//	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getParticolarita() {
		return particolarita;
	}

	public void setParticolarita(String particolarita) {
		this.particolarita = particolarita;
	}

	@Override
	public String toString() {
		return "Articolo [idArticolo=" + idArticolo + ", barraEAN=" + barraEAN + ", catMercDett=" + catMercDett
				+ ", codArtStr=" + codArtStr + ", codBarre=" + codBarre + ", descrizione=" + descrizione + "]";
	}

}
package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;


/**
 * The persistent class for the Articoli database table.
 * 
 */
@Entity
@Table(name="Articoli")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="idUniArticolo", discriminatorType = DiscriminatorType.STRING)
//@NamedQuery(name="Articoli.findAll", query="SELECT a FROM Articoli a")
public class Articoli implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdArticolo", unique=true, nullable=false)
	private int idArticolo;

//	@Column(name="AggIdOld", nullable=false, length=2, insertable=false)
//	private String aggIdOld;

//	@Column(name="AliquotaIVA")
//	private BigDecimal aliquotaIVA;

	@Column(name="ArtH")
	private Integer artH;

	@Column(name="ArtL")
	private Integer artL;

	@Column(name="ArtPeso", columnDefinition="money")
	private Double artPeso;

	@Column(name="ArtZ")
	private Integer artZ;

//	@Column(name="Associato", nullable=false, length=2, insertable=false)
//	private String associato;

	@Column(name="BarraEAN", length=50, columnDefinition="varchar(50)")
	private String barraEAN;

	@Column(name="BarraUPC", length=50, columnDefinition="varchar(50)")
	private String barraUPC;
	
	@Column(name="Cassa", nullable=false, length=10, columnDefinition="varchar(10)")
	private String cassa;

	/**
	 * Default a 'NONVALORIZZATO'
	 */
	@Column(name="Categoria", nullable=false, length=50, columnDefinition="varchar(50)")
	private String categoria;

	@Column(name="CatMercDett", length=50, columnDefinition="varchar(50)")
	private String catMercDett;

	@Column(name="CatMercGruppo", nullable=false, length=50, columnDefinition="varchar(50)")
	private String catMercGruppo;

//	@Column(name="CodArtInt")
//	private int codArtInt;

	@Column(name="CodArtOld", length=50, columnDefinition="varchar(50)")
	private String codArtOld;

	@Column(name="CodArtStr", nullable=false, length=50, columnDefinition="varchar(50)")
	private String codArtStr;

	@Column(name="CodBarre", nullable=false, length=50, columnDefinition="varchar(50)")
	private String codBarre;

//	@Column(name="Collezione", length=1)
//	private String collezione;

	@Column(name="Colore", length=50, columnDefinition="varchar(50)")
	private String colore;

	/**
	 * Default a 'NONVALORIZZATO'
	 */
	@Column(name="Composizione", nullable=false, length=100, columnDefinition="varchar(100)")
	private String composizione;

//	@Column(name="ConfImballo")
//	private int confImballo;

//	@Column(name="DataAssBarcode")
//	private Date dataAssBarcode;
//
//	@Column(name="DataAssCategoria")
//	private Date dataAssCategoria;
//
//	@Column(name="DataAssCompos")
//	private Date dataAssCompos;
//
//	@Column(name="DataInvent")
//	private Date dataInvent;

	/**
	 * Viene aggiornata automaticamente dal trigger alla modifica.
	 */
	@Column(name="DataModifica", nullable=false, columnDefinition="datetime")
	private Date dataModifica;

//	@Column(name="Desc_Colore", length=100)
//	private String desc_Colore;

	@Column(name="DescAggiuntiva", nullable=false, length=100, columnDefinition="varchar(100)")
	private String descAggiuntiva;

	@Column(name="Descrizione", nullable=false, length=100, columnDefinition="varchar(100)")
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
//	private Date finePromo;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

	@Column(name="IdUniArticolo", nullable=false, length=15, columnDefinition="varchar(15)")
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
//	private Date iniPromo;

	@Column(name="Linea", nullable=false, length=50, columnDefinition="varchar(50)")
	private String linea;

	/**
	 * Default a 'NONVALORIZZATO'
	 */
	@Column(name="MadeIn", nullable=false, length=50, columnDefinition="varchar(50)")
	private String madeIn;

//	@Column(name="Marchio")
//	private int marchio;

//	@Column(length=30)
//	private String mercpro;

	@Column(name="Modello", nullable=false, length=50, columnDefinition="varchar(50)")
	private String modello;

	/**
	 * Default a '001'
	 */
	@Column(name="Numerata", nullable=false, length=20, columnDefinition="varchar(20)")
	private String numerata;

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

	@Column(name="PezziCassa", nullable=false)
	private int pezziCassa;

//	@Column(name="PezziCollo", nullable=false, insertable=false)
//	private int pezziCollo;

//	@Column(name="Pezzicomcas")
//	private int pezzicomcas;

//	@Column(name="Pubb", nullable=false, length=2, insertable=false)
//	private String pubb;

	/**
	 * Default a 1
	 */
	@Column(name="QtaConf", nullable=false)
	private int qtaConf;

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

	@Column(name="Stagione", length=30, columnDefinition="varchar(30)")
	private String stagione;

	@Column(name="Taglia", nullable=false, length=20, columnDefinition="varchar(20)")
	private String taglia;

	@Column(name="TipoCassa", length=20, columnDefinition="varchar(20)")
	private String tipoCassa;

//	@Column(name="TotIn")
//	private int totIn;

//	@Column(name="TotOut")
//	private int totOut;

//	@Column(name="UbiPrelievo", length=20)
//	private String ubiPrelievo;

//	@Column(name="UbiScorta", length=50)
//	private String ubiScorta;

	@Column(name="Um", length=10, columnDefinition="varchar(10)")
	private String um;

	@Column(name="UmPos", nullable=false)
	private int umPos;

	@Column(name="Utente", length=50, columnDefinition="varchar(50)")
	private String utente;

//	@Column(name="ValAcq")
//	private BigDecimal valAcq;

//	@Column(name="ValPromo")
//	private BigDecimal valPromo;

	@Column(name="ValVen", columnDefinition="money")
	private Double valVen;

//	@Column(name="Vecchia_Numerata", length=20)
//	private String vecchia_Numerata;

//	@Column(name="Volume")
//	private BigDecimal volume;
	
	@Column(name="note", length=250, columnDefinition="varchar(250)")
	private String note;

	public Articoli() {}
	
	/**
	 * Setta alcuni valori non nullabili a default prima dell'inserimento.
	 */
	@PrePersist
	public void prePersist() {
		if (madeIn == null)	madeIn = "";
		if (descAggiuntiva == null)	descAggiuntiva = "";
		if (composizione == null) composizione = "";
		if (colore == null) colore = "";
		if (catMercDett == null) catMercDett = "";
		if (codArtOld == null) codArtOld = "";
		if (qtaConf <= 0) qtaConf = 1;
		if (pezziCassa <= 0) pezziCassa = 1;
		if (um == null) um = "Pz.";
		if (umPos < 1) umPos = 1;
		if (numerata == null) numerata = "001";
		if (utente == null) utente = "SERVIZIO";
		if (cassa == null || cassa.isEmpty()) cassa = "NO";
		//Controlli su barcode mancanti, qualcosa ci metto comunque.
		if (codBarre == null) codBarre = codArtStr;
		if (barraEAN == null) barraEAN = codBarre;
		if (barraUPC == null) barraUPC = codBarre;
		dataModifica = new Date();
	}
	
	/**
	 * Imposta automaticamente la data di modifica.
	 */
	@PreUpdate
	public void preUpdate() {
		if (madeIn == null)	madeIn = "";
		if (descAggiuntiva == null)	descAggiuntiva = "";
		if (composizione == null) composizione = "";
		if (colore == null) colore = "";
		if (catMercDett == null) catMercDett = "";
		if (codArtOld == null) codArtOld = "";
		if (qtaConf <= 0) qtaConf = 1;
		if (um == null) um = "Pz.";
		if (umPos < 1) umPos = 1;
		if (numerata == null) numerata = "001";
		if (utente == null) utente = "SERVIZIO";
		if (cassa == null || cassa.isEmpty()) cassa = "NO";
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

//	public BigDecimal getAliquotaIVA() {
//		return this.aliquotaIVA;
//	}
//
//	public void setAliquotaIVA(BigDecimal aliquotaIVA) {
//		this.aliquotaIVA = aliquotaIVA;
//	}

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

	public Double getArtPeso() {
		return this.artPeso;
	}

	public void setArtPeso(Double artPeso) {
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

	public String getCassa() {
		return cassa;
	}

	public void setCassa(String cassa) {
		this.cassa = cassa;
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

//	public int getCodArtInt() {
//		return this.codArtInt;
//	}
//
//	public void setCodArtInt(int codArtInt) {
//		this.codArtInt = codArtInt;
//	}

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
//	public Date getDataAssBarcode() {
//		return this.dataAssBarcode;
//	}
//
//	public void setDataAssBarcode(Date dataAssBarcode) {
//		this.dataAssBarcode = dataAssBarcode;
//	}
//
//	public Date getDataAssCategoria() {
//		return this.dataAssCategoria;
//	}
//
//	public void setDataAssCategoria(Date dataAssCategoria) {
//		this.dataAssCategoria = dataAssCategoria;
//	}
//
//	public Date getDataAssCompos() {
//		return this.dataAssCompos;
//	}
//
//	public void setDataAssCompos(Date dataAssCompos) {
//		this.dataAssCompos = dataAssCompos;
//	}
//
//	public Date getDataInvent() {
//		return this.dataInvent;
//	}
//
//	public void setDataInvent(Date dataInvent) {
//		this.dataInvent = dataInvent;
//	}

	public Date getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
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
//	public Date getFinePromo() {
//		return this.finePromo;
//	}
//
//	public void setFinePromo(Date finePromo) {
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
//	public Date getIniPromo() {
//		return this.iniPromo;
//	}
//
//	public void setIniPromo(Date iniPromo) {
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

	public String getNumerata() {
		return this.numerata;
	}

	public void setNumerata(String numerata) {
		this.numerata = numerata;
	}
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
	public int getPezziCassa() {
		return this.pezziCassa;
	}

	public void setPezziCassa(int pezziCassa) {
		this.pezziCassa = pezziCassa;
	}
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
	public int getQtaConf() {
		return this.qtaConf;
	}

	public void setQtaConf(int qtaConf) {
		this.qtaConf = qtaConf;
	}
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
	public String getUm() {
		return this.um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public int getUmPos() {
		return this.umPos;
	}

	public void setUmPos(int umPos) {
		this.umPos = umPos;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}
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

	public Double getValVen() {
		return this.valVen;
	}

	public void setValVen(Double valVen) {
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

	@Override
	public String toString() {
		return "Articolo [idArticolo=" + idArticolo + ", barraEAN=" + barraEAN + ", catMercDett=" + catMercDett
				+ ", codArtStr=" + codArtStr + ", codBarre=" + codBarre + ", descrizione=" + descrizione + "]";
	}

}
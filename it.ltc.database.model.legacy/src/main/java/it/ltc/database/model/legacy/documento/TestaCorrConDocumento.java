package it.ltc.database.model.legacy.documento;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import it.ltc.utility.miscellanea.string.StringUtility;


/**
 * The persistent class for the TestaCorr database table.
 * 
 */
@Entity
@Table(name="TestaCorr")
@NamedQueries({
	@NamedQuery(name="TestaCorrConDocumento.findAll", query="SELECT t FROM TestaCorrConDocumento t"),
	@NamedQuery(name="TestaCorrConDocumento.progressivoSpedizione", query="SELECT MAX(t.nrSpedi) FROM TestaCorrConDocumento t")
})
public class TestaCorrConDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdTestaCor", unique=true, nullable=false)
	private int idTestaCor;

	@Column(name="AnnoSpe")
	private int annoSpe;

//	@Column(name="Annullato", length=1)
//	private String annullato;

//	@Column(name="Bancali")
//	private int bancali;
//
//	@Column(name="Bancpre", length=10)
//	private String bancpre;

	@Column(name="Cap", length=9)
	private String cap;

	@Column(name="CapMitt", length=9)
	private String capMitt;

//	@Column(name="Chiusura1", length=2)
//	private String chiusura1;
//
//	@Column(name="Chiusura2", length=2)
//	private String chiusura2;

	@Column(name="CodBolla", length=2, columnDefinition="CHAR")
	private String codBolla;

	/**
	 * Codice cliente presso il corriere.
	 */
	@Column(name="CodMittente")
	private String codMittente;

//	@Column(name="CodRaggruppamento")
//	private int codRaggruppamento;
//
//	@Column(name="CodTariffa")
//	private int codTariffa;

	@Column(name="Contrassegno")
	private Double contrassegno;

	@Column(name="Corriere", length=50)
	private String corriere;

//	@Column(name="Creazione", nullable=false, insertable=false)
//	private Timestamp creazione;

	@Column(name="DataConsegna")
	private int dataConsegna;

//	@Column(name="DataConsegnaTassativa")
//	private Timestamp dataConsegnaTassativa;
//
//	@Column(name="DataEstrazione")
//	private Timestamp dataEstrazione;
//
//	@Column(name="DataGenerazione")
//	private Timestamp dataGenerazione;

	@Column(name="DataSpe")
	private int dataSpe;

//	@Column(name="Descrizione1", length=35)
//	private String descrizione1;
//
//	@Column(name="Descrizione2", length=35)
//	private String descrizione2;
	
	@Column(name="DocumentoBase64", columnDefinition="varbinary(MAX)", length=Integer.MAX_VALUE)
	private byte[] documentoBase64;
	
	@Column(name="DocumentoData", columnDefinition="datetime")
	private Date documentoData;
	
	@Column(name="DocumentoRiferimento", length=50)
	private String documentoRiferimento;
	
	@Column(name="DocumentoTipo", length=20)
	private String documentoTipo;

//	@Column(name="Estratto", nullable=false, length=2)
//	private String estratto;

//	@Column(name="EstrattoAssegno", nullable=false, length=2)
//	private String estrattoAssegno;

//	@Column(name="FermoDep", length=1)
//	private String fermoDep;

//	@Column(name="Gruppato", nullable=false, length=1)
//	private String gruppato;

//	@Column(name="IdCorriere")
//	private int idCorriere;

//	@Column(length=50)
//	private String IDTracking;

//	@Column(name="ImpoAss")
//	private BigDecimal impoAss;

	@Column(name="Indirizzo", length=35)
	private String indirizzo;

//	@Column(name="Inserito", nullable=false, insertable=false)
//	private int inserito;

	@Column(name="Localita", length=35)
	private String localita;

//	@Column(name="Magazzino", length=10)
//	private String magazzino;

//	@Column(name="Marchio")
//	private int marchio;

	@Column(name="MittenteAlfa", length=20)
	private String mittenteAlfa;

	@Column(name="MittenteNum")
	private int mittenteNum;

//	@Column(name="Modificato", nullable=false, length=1, insertable=false)
//	private String modificato;

//	@Column(name="NatMer", length=15)
//	private String natMer;

	@Column(name="Nazione", length=3)
	private String nazione;

	@Column(name="NazioneMitt", length=3)
	private String nazioneMitt;

//	@Column(name="NomeFileCor", length=30)
//	private String nomeFileCor;

	@Column(name="Note1", length=35)
	private String note1;

	@Column(name="Note2", length=35)
	private String note2;

//	@Column(length=9)
//	private String nplts;

	@Column(name="NrColli")
	private int nrColli;

	@Column(name="NrLista", length=21)
	private String nrLista;

//	@Column(name="NrModifica", nullable=false)
//	private int nrModifica;

//	@Column(name="NrSegnaFin")
//	private int nrSegnaFin;

//	@Column(name="NrSegnaIniz")
//	private int nrSegnaIniz;

	@Column(name="NrSerie")
	private int nrSerie;

	@Column(name="NrSpedi")
	private int nrSpedi;

//	@Column(name="OperatoreArr")
//	private int operatoreArr;

//	@Column(name="OperatorePar")
//	private int operatorePar;

//	@Column(name="OraCons")
//	private int oraCons;

//	@Column(name="Part1", length=1)
//	private String part1;

//	@Column(name="Part2", length=1)
//	private String part2;

//	@Column(name="PartCons", length=2)
//	private String partCons;

//	@Column(name="PartGia", length=2)
//	private String partGia;

//	@Column(name="Particolarita", length=2)
//	private String particolarita;

//	@Column(name="PartVarie", length=2)
//	private String partVarie;

	@Column(name="Peso")
	private Double peso;

	@Column(name="Pezzi", nullable=false)
	private int pezzi;

//	@Column(name="Priority", length=1)
//	private String priority;

	@Column(name="Provincia", length=2)
	private String provincia;

//	@Column(length=2)
//	private String qtaepal;

//	@Column(length=2)
//	private String qtaeur;

//	@Column(name="QtaFatt")
//	private BigDecimal qtaFatt;

	@Column(name="RagSocDest", length=35)
	private String ragSocDest;

	@Column(name="RagSocEst", length=35)
	private String ragSocEst;

	@Column(name="RagSocMitt", length=25)
	private String ragSocMitt;

//	@Column(name="Regione", length=50)
//	private String regione;

	@Column(name="Servizio", length=3)
	private String servizio;

//	@Column(name="Stato", nullable=false, length=30)
//	private String stato;

	@Column(name="StringaBartolini", nullable=false, length=511)
	private String stringaBartolini;

//	@Column(name="TassMitt", length=1)
//	private String tassMitt;

	@Column(name="Telefono", length=30)
	private String telefono;

//	@Column(name="Tipconsegna", length=1, columnDefinition="CHAR")
//	private String tipconsegna;

//	@Column(name="TipoCons", length=1, columnDefinition="CHAR")
//	private String tipoCons;

//	@Column(name="TipoDoc", length=3)
//	private String tipoDoc;

	@Column(name="TipoIncasso", length=2)
	private String tipoIncasso;

//	@Column(name="TraMerce", length=2)
//	private String traMerce;

	@Column(name="Trasmesso", nullable=false)
	private int trasmesso;

	@Column(name="ValoreMerce")
	private Double valoreMerce;

//	@Column(name="ValutaAss", length=3)
//	private String valutaAss;

	@Column(name="ValutaIncasso", length=3)
	private String valutaIncasso;

//	@Column(name="ValutaMerce", length=3)
//	private String valutaMerce;

	@Column(name="Volume")
	private Double volume;

//	@Column(name="VolumeOLD", nullable=false)
//	private BigDecimal volumeOLD;

//	@Column(name="ZonaCons")
//	private int zonaCons;

	public TestaCorrConDocumento() {}
	
	@PrePersist
	public void prePersist() {
		GregorianCalendar today = new GregorianCalendar();
		annoSpe = today.get(Calendar.YEAR);
		dataSpe = ((today.get(Calendar.MONTH) + 1) * 100) + today.get(Calendar.DAY_OF_MONTH);
		//descrizione1 = "";
		//descrizione2 = "";
		nrSerie = 11;
		//particolarita = "  ";
		//regione = "";
		trasmesso = 0;
		//priority = " ";
		stringaBartolini = generaStringaBartolini();
		if (valoreMerce == null)
			valoreMerce = 0.0;
	}

	public int getIdTestaCor() {
		return this.idTestaCor;
	}

	public void setIdTestaCor(int idTestaCor) {
		this.idTestaCor = idTestaCor;
	}

	public int getAnnoSpe() {
		return this.annoSpe;
	}

	public void setAnnoSpe(int annoSpe) {
		this.annoSpe = annoSpe;
	}

//	public String getAnnullato() {
//		return this.annullato;
//	}
//
//	public void setAnnullato(String annullato) {
//		this.annullato = annullato;
//	}
//
//	public int getBancali() {
//		return this.bancali;
//	}
//
//	public void setBancali(int bancali) {
//		this.bancali = bancali;
//	}
//
//	public String getBancpre() {
//		return this.bancpre;
//	}
//
//	public void setBancpre(String bancpre) {
//		this.bancpre = bancpre;
//	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCapMitt() {
		return this.capMitt;
	}

	public void setCapMitt(String capMitt) {
		this.capMitt = capMitt;
	}

//	public String getChiusura1() {
//		return this.chiusura1;
//	}
//
//	public void setChiusura1(String chiusura1) {
//		this.chiusura1 = chiusura1;
//	}
//
//	public String getChiusura2() {
//		return this.chiusura2;
//	}
//
//	public void setChiusura2(String chiusura2) {
//		this.chiusura2 = chiusura2;
//	}

	public String getCodBolla() {
		return this.codBolla;
	}

	public void setCodBolla(String codBolla) {
		this.codBolla = codBolla;
	}

	public String getCodMittente() {
		return this.codMittente;
	}

	public void setCodMittente(String codMittente) {
		this.codMittente = codMittente;
	}
//
//	public int getCodRaggruppamento() {
//		return this.codRaggruppamento;
//	}
//
//	public void setCodRaggruppamento(int codRaggruppamento) {
//		this.codRaggruppamento = codRaggruppamento;
//	}
//
//	public int getCodTariffa() {
//		return this.codTariffa;
//	}
//
//	public void setCodTariffa(int codTariffa) {
//		this.codTariffa = codTariffa;
//	}

	public Double getContrassegno() {
		return this.contrassegno;
	}

	public void setContrassegno(Double contrassegno) {
		this.contrassegno = contrassegno;
	}

	public String getCorriere() {
		return this.corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

//	public Timestamp getCreazione() {
//		return this.creazione;
//	}
//
//	public void setCreazione(Timestamp creazione) {
//		this.creazione = creazione;
//	}

	public int getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(int dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

//	public Timestamp getDataConsegnaTassativa() {
//		return this.dataConsegnaTassativa;
//	}
//
//	public void setDataConsegnaTassativa(Timestamp dataConsegnaTassativa) {
//		this.dataConsegnaTassativa = dataConsegnaTassativa;
//	}
//
//	public Timestamp getDataEstrazione() {
//		return this.dataEstrazione;
//	}
//
//	public void setDataEstrazione(Timestamp dataEstrazione) {
//		this.dataEstrazione = dataEstrazione;
//	}
//
//	public Timestamp getDataGenerazione() {
//		return this.dataGenerazione;
//	}
//
//	public void setDataGenerazione(Timestamp dataGenerazione) {
//		this.dataGenerazione = dataGenerazione;
//	}

	public int getDataSpe() {
		return this.dataSpe;
	}

	public void setDataSpe(int dataSpe) {
		this.dataSpe = dataSpe;
	}

//	public String getDescrizione1() {
//		return this.descrizione1;
//	}
//
//	public void setDescrizione1(String descrizione1) {
//		this.descrizione1 = descrizione1;
//	}
//
//	public String getDescrizione2() {
//		return this.descrizione2;
//	}
//
//	public void setDescrizione2(String descrizione2) {
//		this.descrizione2 = descrizione2;
//	}

//	public String getEstratto() {
//		return this.estratto;
//	}
//
//	public void setEstratto(String estratto) {
//		this.estratto = estratto;
//	}
//
//	public String getEstrattoAssegno() {
//		return this.estrattoAssegno;
//	}
//
//	public void setEstrattoAssegno(String estrattoAssegno) {
//		this.estrattoAssegno = estrattoAssegno;
//	}
//
//	public String getFermoDep() {
//		return this.fermoDep;
//	}
//
//	public void setFermoDep(String fermoDep) {
//		this.fermoDep = fermoDep;
//	}
//
//	public String getGruppato() {
//		return this.gruppato;
//	}
//
//	public void setGruppato(String gruppato) {
//		this.gruppato = gruppato;
//	}
//
//	public int getIdCorriere() {
//		return this.idCorriere;
//	}
//
//	public void setIdCorriere(int idCorriere) {
//		this.idCorriere = idCorriere;
//	}
//
//	public String getIDTracking() {
//		return this.IDTracking;
//	}
//
//	public void setIDTracking(String IDTracking) {
//		this.IDTracking = IDTracking;
//	}
//
//	public BigDecimal getImpoAss() {
//		return this.impoAss;
//	}
//
//	public void setImpoAss(BigDecimal impoAss) {
//		this.impoAss = impoAss;
//	}
	
	public byte[] getDocumentoBase64() {
		return documentoBase64;
	}

	public void setDocumentoBase64(byte[] documentoBase64) {
		this.documentoBase64 = documentoBase64;
	}

	public Date getDocumentoData() {
		return documentoData;
	}

	public void setDocumentoData(Date documentoData) {
		this.documentoData = documentoData;
	}

	public String getDocumentoRiferimento() {
		return documentoRiferimento;
	}

	public void setDocumentoRiferimento(String documentoRiferimento) {
		this.documentoRiferimento = documentoRiferimento;
	}

	public String getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

//	public int getInserito() {
//		return this.inserito;
//	}
//
//	public void setInserito(int inserito) {
//		this.inserito = inserito;
//	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

//	public String getMagazzino() {
//		return this.magazzino;
//	}
//
//	public void setMagazzino(String magazzino) {
//		this.magazzino = magazzino;
//	}
//
//	public int getMarchio() {
//		return this.marchio;
//	}
//
//	public void setMarchio(int marchio) {
//		this.marchio = marchio;
//	}

	public String getMittenteAlfa() {
		return this.mittenteAlfa;
	}

	public void setMittenteAlfa(String mittenteAlfa) {
		this.mittenteAlfa = mittenteAlfa;
	}

	public int getMittenteNum() {
		return this.mittenteNum;
	}

	public void setMittenteNum(int mittenteNum) {
		this.mittenteNum = mittenteNum;
	}

//	public String getModificato() {
//		return this.modificato;
//	}
//
//	public void setModificato(String modificato) {
//		this.modificato = modificato;
//	}
//
//	public String getNatMer() {
//		return this.natMer;
//	}
//
//	public void setNatMer(String natMer) {
//		this.natMer = natMer;
//	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getNazioneMitt() {
		return this.nazioneMitt;
	}

	public void setNazioneMitt(String nazioneMitt) {
		this.nazioneMitt = nazioneMitt;
	}

//	public String getNomeFileCor() {
//		return this.nomeFileCor;
//	}
//
//	public void setNomeFileCor(String nomeFileCor) {
//		this.nomeFileCor = nomeFileCor;
//	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

//	public String getNplts() {
//		return this.nplts;
//	}
//
//	public void setNplts(String nplts) {
//		this.nplts = nplts;
//	}

	public int getNrColli() {
		return this.nrColli;
	}

	public void setNrColli(int nrColli) {
		this.nrColli = nrColli;
	}

	public String getNrLista() {
		return this.nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}
//
//	public int getNrModifica() {
//		return this.nrModifica;
//	}
//
//	public void setNrModifica(int nrModifica) {
//		this.nrModifica = nrModifica;
//	}
//
//	public int getNrSegnaFin() {
//		return this.nrSegnaFin;
//	}
//
//	public void setNrSegnaFin(int nrSegnaFin) {
//		this.nrSegnaFin = nrSegnaFin;
//	}
//
//	public int getNrSegnaIniz() {
//		return this.nrSegnaIniz;
//	}
//
//	public void setNrSegnaIniz(int nrSegnaIniz) {
//		this.nrSegnaIniz = nrSegnaIniz;
//	}

	public int getNrSerie() {
		return this.nrSerie;
	}

	public void setNrSerie(int nrSerie) {
		this.nrSerie = nrSerie;
	}

	public int getNrSpedi() {
		return this.nrSpedi;
	}

	public void setNrSpedi(int nrSpedi) {
		this.nrSpedi = nrSpedi;
	}

//	public int getOperatoreArr() {
//		return this.operatoreArr;
//	}
//
//	public void setOperatoreArr(int operatoreArr) {
//		this.operatoreArr = operatoreArr;
//	}
//
//	public int getOperatorePar() {
//		return this.operatorePar;
//	}
//
//	public void setOperatorePar(int operatorePar) {
//		this.operatorePar = operatorePar;
//	}
//
//	public int getOraCons() {
//		return this.oraCons;
//	}
//
//	public void setOraCons(int oraCons) {
//		this.oraCons = oraCons;
//	}
//
//	public String getPart1() {
//		return this.part1;
//	}
//
//	public void setPart1(String part1) {
//		this.part1 = part1;
//	}
//
//	public String getPart2() {
//		return this.part2;
//	}
//
//	public void setPart2(String part2) {
//		this.part2 = part2;
//	}
//
//	public String getPartCons() {
//		return this.partCons;
//	}
//
//	public void setPartCons(String partCons) {
//		this.partCons = partCons;
//	}
//
//	public String getPartGia() {
//		return this.partGia;
//	}
//
//	public void setPartGia(String partGia) {
//		this.partGia = partGia;
//	}

//	public String getParticolarita() {
//		return this.particolarita;
//	}
//
//	public void setParticolarita(String particolarita) {
//		this.particolarita = particolarita;
//	}

//	public String getPartVarie() {
//		return this.partVarie;
//	}
//
//	public void setPartVarie(String partVarie) {
//		this.partVarie = partVarie;
//	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

//	public String getPriority() {
//		return this.priority;
//	}
//
//	public void setPriority(String priority) {
//		this.priority = priority;
//	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

//	public String getQtaepal() {
//		return this.qtaepal;
//	}
//
//	public void setQtaepal(String qtaepal) {
//		this.qtaepal = qtaepal;
//	}
//
//	public String getQtaeur() {
//		return this.qtaeur;
//	}
//
//	public void setQtaeur(String qtaeur) {
//		this.qtaeur = qtaeur;
//	}
//
//	public BigDecimal getQtaFatt() {
//		return this.qtaFatt;
//	}
//
//	public void setQtaFatt(BigDecimal qtaFatt) {
//		this.qtaFatt = qtaFatt;
//	}

	public String getRagSocDest() {
		return this.ragSocDest;
	}

	public void setRagSocDest(String ragSocDest) {
		this.ragSocDest = ragSocDest;
	}

	public String getRagSocEst() {
		return this.ragSocEst;
	}

	public void setRagSocEst(String ragSocEst) {
		this.ragSocEst = ragSocEst;
	}

	public String getRagSocMitt() {
		return this.ragSocMitt;
	}

	public void setRagSocMitt(String ragSocMitt) {
		this.ragSocMitt = ragSocMitt;
	}

//	public String getRegione() {
//		return this.regione;
//	}
//
//	public void setRegione(String regione) {
//		this.regione = regione;
//	}

	public String getServizio() {
		return this.servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

//	public String getStato() {
//		return this.stato;
//	}
//
//	public void setStato(String stato) {
//		this.stato = stato;
//	}
//
//	public String getStringaBartolini() {
//		return this.stringaBartolini;
//	}
//
//	public void setStringaBartolini(String stringaBartolini) {
//		this.stringaBartolini = stringaBartolini;
//	}
//
//	public String getTassMitt() {
//		return this.tassMitt;
//	}
//
//	public void setTassMitt(String tassMitt) {
//		this.tassMitt = tassMitt;
//	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

//	public String getTipconsegna() {
//		return this.tipconsegna;
//	}
//
//	public void setTipconsegna(String tipconsegna) {
//		this.tipconsegna = tipconsegna;
//	}
//
//	public String getTipoCons() {
//		return this.tipoCons;
//	}
//
//	public void setTipoCons(String tipoCons) {
//		this.tipoCons = tipoCons;
//	}
//
//	public String getTipoDoc() {
//		return this.tipoDoc;
//	}
//
//	public void setTipoDoc(String tipoDoc) {
//		this.tipoDoc = tipoDoc;
//	}

	public String getTipoIncasso() {
		return this.tipoIncasso;
	}

	public void setTipoIncasso(String tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

//	public String getTraMerce() {
//		return this.traMerce;
//	}
//
//	public void setTraMerce(String traMerce) {
//		this.traMerce = traMerce;
//	}

	public int getTrasmesso() {
		return this.trasmesso;
	}

	public void setTrasmesso(int trasmesso) {
		this.trasmesso = trasmesso;
	}

	public Double getValoreMerce() {
		return this.valoreMerce;
	}

	public void setValoreMerce(Double valoreMerce) {
		this.valoreMerce = valoreMerce;
	}

//	public String getValutaAss() {
//		return this.valutaAss;
//	}
//
//	public void setValutaAss(String valutaAss) {
//		this.valutaAss = valutaAss;
//	}
//
	public String getValutaIncasso() {
		return this.valutaIncasso;
	}

	public void setValutaIncasso(String valutaIncasso) {
		this.valutaIncasso = valutaIncasso;
	}
//
//	public String getValutaMerce() {
//		return this.valutaMerce;
//	}
//
//	public void setValutaMerce(String valutaMerce) {
//		this.valutaMerce = valutaMerce;
//	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

//	public BigDecimal getVolumeOLD() {
//		return this.volumeOLD;
//	}
//
//	public void setVolumeOLD(BigDecimal volumeOLD) {
//		this.volumeOLD = volumeOLD;
//	}
//
//	public int getZonaCons() {
//		return this.zonaCons;
//	}
//
//	public void setZonaCons(int zonaCons) {
//		this.zonaCons = zonaCons;
//	}
	
	public String generaStringaBartolini() {
		StringUtility utility = new StringUtility();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		StringBuilder sb = new StringBuilder();
		sb.append(" "); //1 - flag annullamento
		sb.append(utility.getFormattedString(codMittente, 7, "0", true)); //7 - codice cliente mittente
		sb.append("026"); //3 - punto operatore partenza - TODO - va preso dalla tabella corrieri, modifica da fare.
		sb.append(sdf.format(new Date())); //4 - anno spedizione + 4 - mese e giorno spedizione 
		sb.append("00"); //2 - numero serie - TODO - in teoria 00 dovrebbe andare sempre bene, va verificato.
		sb.append(utility.getFormattedString(nrSpedi, 7)); //7 - numero spedizione
		sb.append(codBolla); //2 - codice bolla
		sb.append("000"); //3 - punto operatore arrivo - dovrebbe essere fisso perchè non lo sappiamo
		sb.append(utility.getFormattedString(ragSocDest, 35)); //35 - ragione sociale
		sb.append(utility.getFormattedString(ragSocEst, 35)); //35 - ragione sociale estesa
		sb.append(utility.getFormattedString(indirizzo, 35)); //35 - indirizzo
		sb.append(utility.getFormattedString(cap, 9)); //9 - cap
		sb.append(utility.getFormattedString(localita, 35)); //35 - località
		sb.append(utility.getFormattedString(provincia, 2)); //2 - provincia
		sb.append(utility.getFormattedString(nazione, 3)); //3 - nazione
		sb.append("  "); //2 - primo giorno chiusura
		sb.append("  "); //2 - secondo giorno chiusura
		sb.append("100"); //3 - codice tariffa - fisso a 100
		sb.append(utility.getFormattedString(servizio, 1)); //1 - tipo servizio bolle
		sb.append(utility.getFormattedString(0.0, 13, 3)); //13 (3 decimali) - importo da assicurare
		sb.append("EUR"); //3 - divisa importo da assicurare
		sb.append(utility.getFormattedString("COLLI", 15)); //15 - natura merce
		sb.append(utility.getFormattedString(nrColli, 5)); //5 - colli
		sb.append(utility.getFormattedString(peso, 7, 1)); //7 (1 decimale) - peso
		sb.append(utility.getFormattedString(volume, 5, 3)); //5 (3 decimali) - volume
		sb.append(utility.getFormattedString(0.0, 13, 3)); //13 (3 decimali) - quantità da fatturare
		sb.append(utility.getFormattedString(contrassegno, 13, 3)); //13 (3 decimali) - valore contrassegno
		sb.append(utility.getFormattedString(tipoIncasso, 2)); //2 - tipo contrassegno
		sb.append("EUR"); //3 - divisa contrassegno
		sb.append("  "); //2 - particolarità contrassegno
		sb.append(utility.getFormattedString(mittenteNum, 15)); //15 - riferimento numerico
		sb.append(utility.getFormattedString(mittenteAlfa, 15)); //15 - riferimento alfabetico
		sb.append(utility.getFormattedString(0, 7)); //7 - segnacollo da
		sb.append(utility.getFormattedString(0, 7)); //7 - segnacollo a
		sb.append(" "); //1 - cumulativo colli
		sb.append(utility.getFormattedString(note1, 35)); //35 - note
		sb.append(utility.getFormattedString(note2, 35)); //35 - note aggiuntive
		sb.append("00"); //2 - zona consegna - fissato a 00
		sb.append("7Q"); //2 - codice trattamento merce - fissato a 7Q
		sb.append(" "); //1 - fermo deposito
		sb.append("00000000"); //8 - data consegna richiesta - non viene gestito.
		sb.append(" "); //1 - tipo consegna richiesta
		sb.append("0000"); //4 - ora consegna richiesta - non viene gestito.
		sb.append("  "); //2 - codice tassazione
		sb.append(" "); //1 - flag tassazione
		sb.append(utility.getFormattedString(0.0, 13, 3)); //13 (3 decimali) valore merce dichiarato
		sb.append("EUR"); //3 divisa valore merce
		sb.append("  "); //2 - particolarità consegna
		sb.append("  "); //2 - particolarità gianceza
		sb.append(" "); //2 - particolarità varie
		sb.append(" "); //1 - prima consegna particolare
		sb.append(" "); //1 - seconda consegna particolare
		sb.append(" "); //1 - codice sociale
		sb.append("000000000"); //9 - anticipata - fissato a 000000000
		sb.append(utility.getFormattedString("", 25)); //25 - ragione sociale mittente
		sb.append(utility.getFormattedString("", 9)); //9 - cap mittente
		sb.append("   "); //3 - nazione mittente
		if (sb.length() > 511) {
			sb.delete(511, sb.length());
			System.out.println("Anomalia generazione stringa. Lunghezza maggiore di 511 caratteri.");
		}
		return sb.toString();
	}

}
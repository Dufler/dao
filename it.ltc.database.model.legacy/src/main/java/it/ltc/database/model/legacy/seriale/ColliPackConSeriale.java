package it.ltc.database.model.legacy.seriale;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import it.ltc.utility.miscellanea.time.DateConverter;

@Entity
@Table(name="ColliPack")
public class ColliPackConSeriale implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdColliPack", unique=true, nullable=false)
	private int idColliPack;

//	@Column(name="Aperto", length=2)
//	private String aperto;

//	@Column(length=30)
//	private String bARCODECASPRE;

//	@Column(name="CassaStd", nullable=false, length=50, insertable=false)
//	private String cassaStd;

	@Column(name="CodArtStr", length=50, nullable=false)
	private String codArtStr;

	@Column(name="CodiceArticolo", length=15, nullable=false, columnDefinition="varchar(15)")
	private String codiceArticolo;

//	@Column(name="Controllata", nullable=false, length=2)
//	private String controllata;

	@Column(name="DataAgg")
	private Timestamp dataAgg;
//
//	@Column(name="DataMod")
//	private Timestamp dataMod;

	@Column(name="Descrizione", length=100)
	private String descrizione;

	@Column(name="Flagimp", nullable=false, length=1, columnDefinition="char(1)")
	private String flagimp;

//	@Column(name="Flagpre", nullable=false, length=1)
//	private String flagpre;

	@Column(name="Flagtc")
	private int flagtc;

	@Column(name="IdPakiarticolo", nullable=false)
	private int idPakiarticolo;

	@Column(name="IdTestaPaki", nullable=false)
	private int idTestaPaki;

//	@Column(name="ImpScortaSL", nullable=false, length=50)
//	private String impScortaSL;
//
//	@Column(name="Listaimp", nullable=false, length=20)
//	private String listaimp;
//
//	@Column(name="Listapre", length=20)
//	private String listapre;

	/**
	 * Riporta il campo NrPaki del packing list a cui è collegato
	 */
	@Column(name="Lotto", length=30)
	private String lotto;

	@Column(length=3, columnDefinition="CHAR")
	private String magazzino;

//	@Column(length=2)
//	private String marchio;
//
//	@Column(name="Note", length=50)
//	private String note;

	@Column(name="NrIdColloPk", length=9, nullable=false, columnDefinition="char(9)")
	private String nrIdColloPk;

//	@Column(name="NrRiferimento")
//	private int nrRiferimento;
//
	@Column(name="Operatore", length=50)
	private String operatore;

	@Column(name="OraAgg")
	private int oraAgg;

//	private int oramod;
//
//	@Column(name="Posizione")
//	private int posizione;

//	@Column(name="Prelevato", nullable=false, length=50)
//	private String prelevato;

	@Column(name="Qta")
	private int qta;

	@Column(nullable=false)
	private int qtaimpegnata;
//
//	private int qtaOrigine;
//
//	@Column(nullable=false)
//	private int qtascorta;
//
//	@Column(name="RilPzEventi", nullable=false, length=2)
//	private String rilPzEventi;
//
//	@Column(name="Stagcarico", length=20)
//	private String stagcarico;
//
	@Column(name="Taglia", length=20)
	private String taglia;
//
//	@Column(name="Trasferito", nullable=false, length=2147483647)
//	private String trasferito;
	
	@Column(name="Seriale", length=50)
	private String seriale;

	public ColliPackConSeriale() {}
	
	@PrePersist
	public void prePersist() {
		if (operatore == null) operatore = "WS";
		if (taglia == null) taglia = "";
		if (descrizione == null) descrizione = "";
		else if (descrizione.length() > 50) descrizione = descrizione.substring(0, 50);
		dataAgg = new Timestamp(new Date().getTime());
		oraAgg = DateConverter.getOraComeIntero(dataAgg);
		dataAgg = DateConverter.ripulisciTimestap(dataAgg);
	}
	
	@PreUpdate
	public void preUpdate() {
		if (operatore == null) operatore = "WS";
		if (taglia == null) taglia = "";
		if (descrizione == null) descrizione = "";
		else if (descrizione.length() > 50) descrizione = descrizione.substring(0, 50);
		dataAgg = new Timestamp(new Date().getTime());
		oraAgg = DateConverter.getOraComeIntero(dataAgg);
		dataAgg = DateConverter.ripulisciTimestap(dataAgg);
	}

	public int getIdColliPack() {
		return this.idColliPack;
	}

	public void setIdColliPack(int idColliPack) {
		this.idColliPack = idColliPack;
	}

//	public String getAperto() {
//		return this.aperto;
//	}
//
//	public void setAperto(String aperto) {
//		this.aperto = aperto;
//	}
//
//	public String getBARCODECASPRE() {
//		return this.bARCODECASPRE;
//	}
//
//	public void setBARCODECASPRE(String bARCODECASPRE) {
//		this.bARCODECASPRE = bARCODECASPRE;
//	}
//
//	public String getCassaStd() {
//		return this.cassaStd;
//	}
//
//	public void setCassaStd(String cassaStd) {
//		this.cassaStd = cassaStd;
//	}

	public String getCodArtStr() {
		return this.codArtStr;
	}

	public void setCodArtStr(String codArtStr) {
		this.codArtStr = codArtStr;
	}

	public String getCodiceArticolo() {
		return this.codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

//	public String getControllata() {
//		return this.controllata;
//	}
//
//	public void setControllata(String controllata) {
//		this.controllata = controllata;
//	}
//
	public Timestamp getDataAgg() {
		return this.dataAgg;
	}

	public void setDataAgg(Timestamp dataAgg) {
		this.dataAgg = dataAgg;
	}
//
//	public Timestamp getDataMod() {
//		return this.dataMod;
//	}
//
//	public void setDataMod(Timestamp dataMod) {
//		this.dataMod = dataMod;
//	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFlagimp() {
		return this.flagimp;
	}

	public void setFlagimp(String flagimp) {
		this.flagimp = flagimp;
	}

//	public String getFlagpre() {
//		return this.flagpre;
//	}
//
//	public void setFlagpre(String flagpre) {
//		this.flagpre = flagpre;
//	}
//
	public int getFlagtc() {
		return this.flagtc;
	}

	public void setFlagtc(int flagtc) {
		this.flagtc = flagtc;
	}

	public int getIdPakiarticolo() {
		return this.idPakiarticolo;
	}

	public void setIdPakiarticolo(int idPakiarticolo) {
		this.idPakiarticolo = idPakiarticolo;
	}

	public int getIdTestaPaki() {
		return this.idTestaPaki;
	}

	public void setIdTestaPaki(int idTestaPaki) {
		this.idTestaPaki = idTestaPaki;
	}

//	public String getImpScortaSL() {
//		return this.impScortaSL;
//	}
//
//	public void setImpScortaSL(String impScortaSL) {
//		this.impScortaSL = impScortaSL;
//	}
//
//	public String getListaimp() {
//		return this.listaimp;
//	}
//
//	public void setListaimp(String listaimp) {
//		this.listaimp = listaimp;
//	}
//
//	public String getListapre() {
//		return this.listapre;
//	}
//
//	public void setListapre(String listapre) {
//		this.listapre = listapre;
//	}

	public String getLotto() {
		return this.lotto;
	}

	public void setLotto(String lotto) {
		this.lotto = lotto;
	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

//	public String getMarchio() {
//		return this.marchio;
//	}
//
//	public void setMarchio(String marchio) {
//		this.marchio = marchio;
//	}
//
//	public String getNote() {
//		return this.note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}

	public String getNrIdColloPk() {
		return this.nrIdColloPk;
	}

	public void setNrIdColloPk(String nrIdColloPk) {
		this.nrIdColloPk = nrIdColloPk;
	}

//	public int getNrRiferimento() {
//		return this.nrRiferimento;
//	}
//
//	public void setNrRiferimento(int nrRiferimento) {
//		this.nrRiferimento = nrRiferimento;
//	}
//
	public String getOperatore() {
		return this.operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public int getOraAgg() {
		return this.oraAgg;
	}

	public void setOraAgg(int oraAgg) {
		this.oraAgg = oraAgg;
	}
//
//	public int getOramod() {
//		return this.oramod;
//	}
//
//	public void setOramod(int oramod) {
//		this.oramod = oramod;
//	}
//
//	public int getPosizione() {
//		return this.posizione;
//	}
//
//	public void setPosizione(int posizione) {
//		this.posizione = posizione;
//	}
//
//	public String getPrelevato() {
//		return this.prelevato;
//	}
//
//	public void setPrelevato(String prelevato) {
//		this.prelevato = prelevato;
//	}

	public int getQta() {
		return this.qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	public String getSeriale() {
		return seriale;
	}

	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

	public int getQtaimpegnata() {
		return this.qtaimpegnata;
	}

	public void setQtaimpegnata(int qtaimpegnata) {
		this.qtaimpegnata = qtaimpegnata;
	}
//
//	public int getQtaOrigine() {
//		return this.qtaOrigine;
//	}
//
//	public void setQtaOrigine(int qtaOrigine) {
//		this.qtaOrigine = qtaOrigine;
//	}
//
//	public int getQtascorta() {
//		return this.qtascorta;
//	}
//
//	public void setQtascorta(int qtascorta) {
//		this.qtascorta = qtascorta;
//	}
//
//	public String getRilPzEventi() {
//		return this.rilPzEventi;
//	}
//
//	public void setRilPzEventi(String rilPzEventi) {
//		this.rilPzEventi = rilPzEventi;
//	}
//
//	public String getStagcarico() {
//		return this.stagcarico;
//	}
//
//	public void setStagcarico(String stagcarico) {
//		this.stagcarico = stagcarico;
//	}
//
	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
//
//	public String getTrasferito() {
//		return this.trasferito;
//	}
//
//	public void setTrasferito(String trasferito) {
//		this.trasferito = trasferito;
//	}

}

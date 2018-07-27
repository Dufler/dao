package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the pakiArticolo database table.
 * 
 */
@Entity
@Table(name="pakiArticolo")
@NamedQueries({
	//@NamedQuery(name="PakiArticolo.findAll", query="SELECT p FROM PakiArticolo p"),
	@NamedQuery(name="PakiArticolo.totaleDichiaratoPerCarico", query="SELECT SUM(p.qtaPaki) FROM PakiArticolo p WHERE p.idPakiTesta = :carico"),
	@NamedQuery(name="PakiArticolo.totaleVerificatoPerCarico", query="SELECT SUM(p.qtaVerificata) FROM PakiArticolo p WHERE p.idPakiTesta = :carico")
})

public class PakiArticolo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idPakiArticolo;

	@Column(name="BarcodeCollo", length=50)
	private String barcodeCollo;

//	@Column(name="Caricato", length=1)
//	private String caricato;

	@Column(name="CodBarre", length=20)
	private String codBarre;

	/**
	 * NONE è il default, va messo a INSE nel caso in cui non era presente nel dichiarato.
	 */
	@Column(name="CodMotivo", length=4, updatable=false)
	private String codMotivo;

	@Column(name="CodUnicoArt", length=15)
	private String codUnicoArt;

//	@Column(name="Collo")
//	private int collo;

//	@Column(name="Controllato", length=1)
//	private String controllato;

//	@Column(name="DataAgg")
//	private Timestamp dataAgg;

	@Column(name="DataModifica")
	private Timestamp dataModifica;

//	@Column(name="Eccede")
//	private int eccede;

//	@Column(name="Flagmod", length=50)
//	private String flagmod;

//	@Column(name="IdAttCliente")
//	private int idAttCliente;

//	@Column(name="IdDestina")
//	private int idDestina;

	/**
	 * Non aggiornabile, come nrOrdineFor
	 */
	@Column(name="IdPakiTesta", nullable=false, updatable=false)
	private int idPakiTesta;

//	@Column(name="KeyColloCar", length=9)
//	private String keyColloCar;

	@Column(name="KeyUbicaCar", length=15)
	private String keyUbicaCar;

	@Column(name="KeyUbicaPre", length=15)
	private String keyUbicaPre;
	
	@Column(name="MadeIn", length=3)
	private String madeIn;

	@Column(name="Magazzino", length=10)
	private String magazzino;

	@Column(name="Magazzinoltc", length=5)
	private String magazzinoltc;

//	@Column(name="Manca")
//	private int manca;

//	@Column(name="NrCollo")
//	private int nrCollo;
//
//	@Column(name="NrColloFornitore")
//	private int nrColloFornitore;

	@Column(name="NrDispo", length=30)
	private String nrDispo;

	/**
	 * Non aggiornabile, come idPakiTesta. Rappresenta il riferimento al carico.
	 */
	@Column(name="NrOrdineFor", length=50, updatable=false)
	private String nrOrdineFor;

//	@Column(name="OraAgg")
//	private int oraAgg;
//
//	@Column(name="OraModifica")
//	private int oraModifica;

//	@Column(name="QtaColloStp")
//	private int qtaColloStp;

//	@Column(name="QtaImba")
//	private int qtaImba;

	@Column(name="QtaPaki")
	private int qtaPaki;

	@Column(name="QtaPreDoc")
	private int qtaPreDoc;

	@Column(name="QtaPrelevata")
	private int qtaPrelevata;

	@Column(name="QtaVerificata")
	private int qtaVerificata;

	@Column(name="RigaPacki")
	private int rigaPacki;

	@Column(name="Scelta", length=50)
	private String scelta;

	@Column(name="Stagcarico", length=10)
	private String stagcarico;

//	@Column(name="StpSovra", length=1)
//	private String stpSovra;

//	@Column(name="TipoMerce", length=50)
//	private String tipoMerce;

//	@Column(name="TipoUbi", length=2)
//	private String tipoUbi;

//	@Column(name="Ubicato", length=2)
//	private String ubicato;

	@Column(name="Utente", length=20)
	private String utente;
	
	@Column(name="CodArtStr", length=50)
	private String codArtStr;
	
	@Transient
	private List<String> seriali;

	public PakiArticolo() {}
	
	@PrePersist
	public void prePersist() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		keyUbicaCar = "";
		keyUbicaPre = "";
		qtaPreDoc = 0;
		qtaPrelevata = 0;
		qtaVerificata = 0;
		scelta = "";
		dataModifica = new Timestamp(now.getTime());
		if (utente == null) utente = "WSE";
		if (barcodeCollo == null || barcodeCollo.isEmpty())	barcodeCollo = Integer.toString(idPakiTesta);
		//Se non lo valorizzo metto il default.
		if (codMotivo == null) codMotivo = "NONE";
		if (nrOrdineFor == null) nrOrdineFor = "";
		if (stagcarico == null) stagcarico = "CO" + sdf.format(now);
	}
	
	@PreUpdate
	public void preUpdate() {
		dataModifica = new Timestamp(new Date().getTime());
		if (keyUbicaCar == null) keyUbicaCar = "";
		if (keyUbicaPre == null) keyUbicaPre = "";
		if (scelta == null) scelta = "";
	}

	public int getIdPakiArticolo() {
		return this.idPakiArticolo;
	}

	public void setIdPakiArticolo(int idPakiArticolo) {
		this.idPakiArticolo = idPakiArticolo;
	}

	public String getBarcodeCollo() {
		return this.barcodeCollo;
	}

	public void setBarcodeCollo(String barcodeCollo) {
		this.barcodeCollo = barcodeCollo;
	}

//	public String getCaricato() {
//		return this.caricato;
//	}
//
//	public void setCaricato(String caricato) {
//		this.caricato = caricato;
//	}

	public String getCodBarre() {
		return this.codBarre;
	}

	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;
	}

	public String getCodMotivo() {
		return this.codMotivo;
	}

	public void setCodMotivo(String codMotivo) {
		this.codMotivo = codMotivo;
	}

	public String getCodUnicoArt() {
		return this.codUnicoArt;
	}

	public void setCodUnicoArt(String codUnicoArt) {
		this.codUnicoArt = codUnicoArt;
	}

//	public int getCollo() {
//		return this.collo;
//	}
//
//	public void setCollo(int collo) {
//		this.collo = collo;
//	}
//
//	public String getControllato() {
//		return this.controllato;
//	}
//
//	public void setControllato(String controllato) {
//		this.controllato = controllato;
//	}
//
//	public Timestamp getDataAgg() {
//		return this.dataAgg;
//	}
//
//	public void setDataAgg(Timestamp dataAgg) {
//		this.dataAgg = dataAgg;
//	}

	public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

//	public int getEccede() {
//		return this.eccede;
//	}
//
//	public void setEccede(int eccede) {
//		this.eccede = eccede;
//	}
//
//	public String getFlagmod() {
//		return this.flagmod;
//	}
//
//	public void setFlagmod(String flagmod) {
//		this.flagmod = flagmod;
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

	public int getIdPakiTesta() {
		return this.idPakiTesta;
	}

	public void setIdPakiTesta(int idPakiTesta) {
		this.idPakiTesta = idPakiTesta;
	}

//	public String getKeyColloCar() {
//		return this.keyColloCar;
//	}
//
//	public void setKeyColloCar(String keyColloCar) {
//		this.keyColloCar = keyColloCar;
//	}

	public String getKeyUbicaCar() {
		return this.keyUbicaCar;
	}

	public void setKeyUbicaCar(String keyUbicaCar) {
		this.keyUbicaCar = keyUbicaCar;
	}

	public String getKeyUbicaPre() {
		return this.keyUbicaPre;
	}

	public void setKeyUbicaPre(String keyUbicaPre) {
		this.keyUbicaPre = keyUbicaPre;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public String getMagazzinoltc() {
		return this.magazzinoltc;
	}

	public void setMagazzinoltc(String magazzinoltc) {
		this.magazzinoltc = magazzinoltc;
	}

//	public int getManca() {
//		return this.manca;
//	}
//
//	public void setManca(int manca) {
//		this.manca = manca;
//	}
//
//	public int getNrCollo() {
//		return this.nrCollo;
//	}
//
//	public void setNrCollo(int nrCollo) {
//		this.nrCollo = nrCollo;
//	}
//
//	public int getNrColloFornitore() {
//		return this.nrColloFornitore;
//	}
//
//	public void setNrColloFornitore(int nrColloFornitore) {
//		this.nrColloFornitore = nrColloFornitore;
//	}

	public String getNrDispo() {
		return this.nrDispo;
	}

	public void setNrDispo(String nrDispo) {
		this.nrDispo = nrDispo;
	}

	public String getNrOrdineFor() {
		return this.nrOrdineFor;
	}

	public void setNrOrdineFor(String nrOrdineFor) {
		this.nrOrdineFor = nrOrdineFor;
	}

//	public int getOraAgg() {
//		return this.oraAgg;
//	}
//
//	public void setOraAgg(int oraAgg) {
//		this.oraAgg = oraAgg;
//	}
//
//	public int getOraModifica() {
//		return this.oraModifica;
//	}
//
//	public void setOraModifica(int oraModifica) {
//		this.oraModifica = oraModifica;
//	}
//
//	public int getQtaColloStp() {
//		return this.qtaColloStp;
//	}
//
//	public void setQtaColloStp(int qtaColloStp) {
//		this.qtaColloStp = qtaColloStp;
//	}
//
//	public int getQtaImba() {
//		return this.qtaImba;
//	}
//
//	public void setQtaImba(int qtaImba) {
//		this.qtaImba = qtaImba;
//	}

	public int getQtaPaki() {
		return this.qtaPaki;
	}

	public void setQtaPaki(int qtaPaki) {
		this.qtaPaki = qtaPaki;
	}

	public int getQtaPreDoc() {
		return this.qtaPreDoc;
	}

	public void setQtaPreDoc(int qtaPreDoc) {
		this.qtaPreDoc = qtaPreDoc;
	}

	public int getQtaPrelevata() {
		return this.qtaPrelevata;
	}

	public void setQtaPrelevata(int qtaPrelevata) {
		this.qtaPrelevata = qtaPrelevata;
	}

	public int getQtaVerificata() {
		return this.qtaVerificata;
	}

	public void setQtaVerificata(int qtaVerificata) {
		this.qtaVerificata = qtaVerificata;
	}

	public int getRigaPacki() {
		return this.rigaPacki;
	}

	public void setRigaPacki(int rigaPacki) {
		this.rigaPacki = rigaPacki;
	}

	public String getScelta() {
		return this.scelta;
	}

	public void setScelta(String scelta) {
		this.scelta = scelta;
	}

	public String getStagcarico() {
		return this.stagcarico;
	}

	public void setStagcarico(String stagcarico) {
		this.stagcarico = stagcarico;
	}
//
//	public String getStpSovra() {
//		return this.stpSovra;
//	}
//
//	public void setStpSovra(String stpSovra) {
//		this.stpSovra = stpSovra;
//	}
//
//	public String getTipoMerce() {
//		return this.tipoMerce;
//	}
//
//	public void setTipoMerce(String tipoMerce) {
//		this.tipoMerce = tipoMerce;
//	}
//
//	public String getTipoUbi() {
//		return this.tipoUbi;
//	}
//
//	public void setTipoUbi(String tipoUbi) {
//		this.tipoUbi = tipoUbi;
//	}
//
//	public String getUbicato() {
//		return this.ubicato;
//	}
//
//	public void setUbicato(String ubicato) {
//		this.ubicato = ubicato;
//	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getCodArtStr() {
		return codArtStr;
	}

	public void setCodArtStr(String codArtStr) {
		this.codArtStr = codArtStr;
	}

	public List<String> getSeriali() {
		return seriali;
	}

	public void setSeriali(List<String> seriali) {
		this.seriali = seriali;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPakiArticolo;
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
		PakiArticolo other = (PakiArticolo) obj;
		if (idPakiArticolo != other.idPakiArticolo)
			return false;
		return true;
	}

}
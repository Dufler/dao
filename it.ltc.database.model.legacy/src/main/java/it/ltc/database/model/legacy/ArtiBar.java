package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ArtiBar database table.
 * 
 */
@Entity
@Table(name="ArtiBar")
//@NamedQuery(name="ArtiBar.findAll", query="SELECT a FROM ArtiBar a")
public class ArtiBar implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdArtiBar", unique=true, nullable=false)
	private int idArtiBar;

//	@Column(name="Barra3", length=50)
//	private String barra3;
//
//	@Column(name="Barra4", length=50)
//	private String barra4;
//
//	@Column(name="Barra5", length=50)
//	private String barra5;
//
//	@Column(name="Barra6", length=50)
//	private String barra6;

	@Column(name="BarraEAN", length=50, columnDefinition="varchar(50)")
	private String barraEAN;

	@Column(name="BarraUPC", length=50, columnDefinition="varchar(50)")
	private String barraUPC;

	@Column(name="CodiceArticolo", length=50, columnDefinition="varchar(50)", nullable=false)
	private String codiceArticolo;

	/**
	 * Viene aggiornata automaticamente dal trigger dopo ogni modifica.
	 */
	@Column(name="DataAgg", nullable=false, columnDefinition="datetime")
	private Date dataAgg;

//	@Column(name="DataAss")
//	private Date dataAss;

	@Column(name="IdUniArticolo", length=15, columnDefinition="varchar(15)", nullable=false)
	private String idUniArticolo;

	@Column(name="Numerata", length=20, columnDefinition="varchar(20)")
	private String numerata;

	@Column(name="Posizione")
	private Integer posizione;

	@Column(name="Taglia", length=20, columnDefinition="varchar(20)")
	private String taglia;

//	@Column(name="TipoAss", length=1)
//	private String tipoAss;

	public ArtiBar() {}
	
	@PrePersist
	public void prePersist() {
		if (numerata == null) numerata = "001";
		if (posizione == null || posizione <= 0) posizione = 1;
		if (taglia == null) taglia = "UNI";
		dataAgg = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		if (numerata == null) numerata = "001";
		if (posizione == null || posizione <= 0) posizione = 1;
		if (taglia == null) taglia = "UNI";
	}

	public int getIdArtiBar() {
		return this.idArtiBar;
	}

	public void setIdArtiBar(int idArtiBar) {
		this.idArtiBar = idArtiBar;
	}

//	public String getBarra3() {
//		return this.barra3;
//	}
//
//	public void setBarra3(String barra3) {
//		this.barra3 = barra3;
//	}
//
//	public String getBarra4() {
//		return this.barra4;
//	}
//
//	public void setBarra4(String barra4) {
//		this.barra4 = barra4;
//	}
//
//	public String getBarra5() {
//		return this.barra5;
//	}
//
//	public void setBarra5(String barra5) {
//		this.barra5 = barra5;
//	}
//
//	public String getBarra6() {
//		return this.barra6;
//	}
//
//	public void setBarra6(String barra6) {
//		this.barra6 = barra6;
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

	public String getCodiceArticolo() {
		return this.codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public Date getDataAgg() {
		return this.dataAgg;
	}

	public void setDataAgg(Date dataAgg) {
		this.dataAgg = dataAgg;
	}

//	public Date getDataAss() {
//		return this.dataAss;
//	}
//
//	public void setDataAss(Date dataAss) {
//		this.dataAss = dataAss;
//	}

	public String getIdUniArticolo() {
		return this.idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

	public String getNumerata() {
		return this.numerata;
	}

	public void setNumerata(String numerata) {
		this.numerata = numerata;
	}

	public Integer getPosizione() {
		return this.posizione;
	}

	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}

	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

//	public String getTipoAss() {
//		return this.tipoAss;
//	}
//
//	public void setTipoAss(String tipoAss) {
//		this.tipoAss = tipoAss;
//	}

}
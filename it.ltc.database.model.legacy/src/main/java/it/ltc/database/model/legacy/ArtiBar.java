package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
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

	@Column(name="BarraEAN", length=50)
	private String barraEAN;

	@Column(name="BarraUPC", length=50)
	private String barraUPC;

	@Column(name="CodiceArticolo", length=100)
	private String codiceArticolo;

	@Column(name="DataAgg", nullable=false)
	private Timestamp dataAgg;

//	@Column(name="DataAss")
//	private Timestamp dataAss;

	@Column(name="IdUniArticolo", length=15)
	private String idUniArticolo;

//	@Column(name="Numerata", length=50)
//	private String numerata;
//
//	@Column(name="Posizione")
//	private int posizione;

	@Column(name="Taglia", length=20)
	private String taglia;

//	@Column(name="TipoAss", length=1)
//	private String tipoAss;

	public ArtiBar() {}
	
	@PrePersist
	public void prePersist() {
		dataAgg = new Timestamp(new Date().getTime());
	}
	
	@PreUpdate
	public void preUpdate() {
		dataAgg = new Timestamp(new Date().getTime());
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

	public Timestamp getDataAgg() {
		return this.dataAgg;
	}

	public void setDataAgg(Timestamp dataAgg) {
		this.dataAgg = dataAgg;
	}

//	public Timestamp getDataAss() {
//		return this.dataAss;
//	}
//
//	public void setDataAss(Timestamp dataAss) {
//		this.dataAss = dataAss;
//	}

	public String getIdUniArticolo() {
		return this.idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

//	public String getNumerata() {
//		return this.numerata;
//	}
//
//	public void setNumerata(String numerata) {
//		this.numerata = numerata;
//	}
//
//	public int getPosizione() {
//		return this.posizione;
//	}
//
//	public void setPosizione(int posizione) {
//		this.posizione = posizione;
//	}

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
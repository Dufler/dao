package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MagaSd database table.
 * 
 */
@Entity
@Table(name="MagaSd")
//@NamedQuery(name="MagaSd.findAll", query="SELECT m FROM MagaSd m")
public class MagaSd implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdMagaSd", unique=true, nullable=false)
	private int idMagaSd;

	@Column(name="CodMaga", nullable=false, length=3)
	private String codMaga;

//	@Column(name="DataInventario")
//	private Timestamp dataInventario;

	@Column(name="Disponibile", nullable=false)
	private int disponibile;

	@Column(name="Esistenza", nullable=false)
	private int esistenza;

	@Column(name="IdUniArticolo", nullable=false, length=25)
	private String idUniArticolo;

	@Column(name="Impegnato", nullable=false)
	private int impegnato;

//	@Column(name="Inventario", nullable=false)
//	private int inventario;

//	@Column(nullable=false)
//	private int tempdispo;

	@Column(name="TotIn", nullable=false)
	private int totIn;

	@Column(name="TotOut", nullable=false)
	private int totOut;

	public MagaSd() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMagaSd;
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
		MagaSd other = (MagaSd) obj;
		if (idMagaSd != other.idMagaSd)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MagaSd [codMaga=" + codMaga + ", disponibile=" + disponibile + ", esistenza=" + esistenza
				+ ", idUniArticolo=" + idUniArticolo + ", impegnato=" + impegnato + "]";
	}

	public int getIdMagaSd() {
		return this.idMagaSd;
	}

	public void setIdMagaSd(int idMagaSd) {
		this.idMagaSd = idMagaSd;
	}

	public String getCodMaga() {
		return this.codMaga;
	}

	public void setCodMaga(String codMaga) {
		this.codMaga = codMaga;
	}

//	public Timestamp getDataInventario() {
//		return this.dataInventario;
//	}
//
//	public void setDataInventario(Timestamp dataInventario) {
//		this.dataInventario = dataInventario;
//	}

	public int getDisponibile() {
		return this.disponibile;
	}

	public void setDisponibile(int disponibile) {
		this.disponibile = disponibile;
	}

	public int getEsistenza() {
		return this.esistenza;
	}

	public void setEsistenza(int esistenza) {
		this.esistenza = esistenza;
	}

	public String getIdUniArticolo() {
		return this.idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

	public int getImpegnato() {
		return this.impegnato;
	}

	public void setImpegnato(int impegnato) {
		this.impegnato = impegnato;
	}

//	public int getInventario() {
//		return this.inventario;
//	}
//
//	public void setInventario(int inventario) {
//		this.inventario = inventario;
//	}
//
//	public int getTempdispo() {
//		return this.tempdispo;
//	}
//
//	public void setTempdispo(int tempdispo) {
//		this.tempdispo = tempdispo;
//	}
//
	public int getTotIn() {
		return this.totIn;
	}

	public void setTotIn(int totIn) {
		this.totIn = totIn;
	}

	public int getTotOut() {
		return this.totOut;
	}

	public void setTotOut(int totOut) {
		this.totOut = totOut;
	}

}
package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Imballi database table.
 * 
 */
@Entity
@Table(name="Imballi")
//@NamedQuery(name="Imballi.findAll", query="SELECT i FROM Imballi i")
public class Imballi implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdImballo", unique=true, nullable=false)
	private int idImballo;

	/**
	 * SI o NO, può servire lato utente per presentare solo i formati da utilizzare al momento mantendo in archivio a NO quelli passati per motivi storici.
	 */
//	@Column(name="Attiva", nullable=false, length=2, , columnDefinition="char(2)")
//	private String attiva;

	@Column(name="CodImballo", length=2)
	private String codImballo;

	@Column(name="Descrizione", length=30)
	private String descrizione;

	@Column(name="H", nullable=false, columnDefinition="money")
	private double h;

	@Column(name="L", nullable=false, columnDefinition="money")
	private double l;

	@Column(name="PesoGr")
	private int pesoGr;

//	@Column(name="Trascodifica", nullable=false, length=30)
//	private String trascodifica;

	@Column(name="Volume")
	private int volume;

	@Column(name="Z", nullable=false, columnDefinition="money")
	private double z;

	public Imballi() {}

	public int getIdImballo() {
		return this.idImballo;
	}

	public void setIdImballo(int idImballo) {
		this.idImballo = idImballo;
	}

//	public String getAttiva() {
//		return this.attiva;
//	}
//
//	public void setAttiva(String attiva) {
//		this.attiva = attiva;
//	}

	public String getCodImballo() {
		return this.codImballo;
	}

	public void setCodImballo(String codImballo) {
		this.codImballo = codImballo;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getH() {
		return this.h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getL() {
		return this.l;
	}

	public void setL(double l) {
		this.l = l;
	}

	public int getPesoGr() {
		return this.pesoGr;
	}

	public void setPesoGr(int pesoGr) {
		this.pesoGr = pesoGr;
	}
//
//	public String getTrascodifica() {
//		return this.trascodifica;
//	}
//
//	public void setTrascodifica(String trascodifica) {
//		this.trascodifica = trascodifica;
//	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getZ() {
		return this.z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
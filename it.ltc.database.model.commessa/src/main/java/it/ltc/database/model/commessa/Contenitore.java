package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contenitore database table.
 * 
 */
@Entity
@NamedQuery(name="Contenitore.findAll", query="SELECT c FROM Contenitore c")
public class Contenitore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String sede;

	private String stato;

	private String ubicazione;

	public Contenitore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

}
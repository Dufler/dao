package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sede database table.
 * 
 */
@Entity
@Table(name="sede")
@NamedQuery(name="SedeUtenti.findAll", query="SELECT s FROM SedeUtenti s")
public class SedeUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String sede;

	public SedeUtenti() {}

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

}
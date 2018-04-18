package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ubicazione database table.
 * 
 */
@Entity
@Table(name="ubicazione")
@NamedQuery(name="Ubicazione.findAll", query="SELECT u FROM Ubicazione u")
public class Ubicazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String ubicazione;

	public Ubicazione() {
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

}
package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the operatore database table.
 * 
 */
@Entity
@Table(name="operatore")
@NamedQuery(name="Operatore.findAll", query="SELECT o FROM Operatore o")
public class Operatore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String username;

	public Operatore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
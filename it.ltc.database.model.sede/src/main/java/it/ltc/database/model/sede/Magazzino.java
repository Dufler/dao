package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the magazzino database table.
 * 
 */
@Entity
@Table(name="magazzino")
@NamedQuery(name="Magazzino.findAll", query="SELECT m FROM Magazzino m")
public class Magazzino implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="id_commessa")
	private int idCommessa;

	private String nome;

	public Magazzino() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
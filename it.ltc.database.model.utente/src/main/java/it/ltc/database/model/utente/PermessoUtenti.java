package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the permesso database table.
 * 
 */
@Entity
@Table(name="permesso")
@NamedQuery(name="PermessoUtenti.findAll", query="SELECT p FROM PermessoUtenti p")
public class PermessoUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String categoria;

	@Column(name="id_padre")
	private int idPadre;

	private String nome;

	public PermessoUtenti() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
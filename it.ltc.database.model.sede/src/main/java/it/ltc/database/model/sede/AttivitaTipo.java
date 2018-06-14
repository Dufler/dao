package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the attivita_tipo database table.
 * 
 */
@Entity
@Table(name="attivita_tipo")
@NamedQuery(name="AttivitaTipo.findAll", query="SELECT a FROM AttivitaTipo a")
public class AttivitaTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descrizione;

	private String nome;
	
	private String categoria;

	public AttivitaTipo() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
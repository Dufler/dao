package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_fase database table.
 * 
 */
@Entity
@Table(name="cdg_fase")
@NamedQuery(name="CdgFase.findAll", query="SELECT c FROM CdgFase c")
public class CdgFase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descrizione;

	private String nome;

	public CdgFase() {
	}

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

}
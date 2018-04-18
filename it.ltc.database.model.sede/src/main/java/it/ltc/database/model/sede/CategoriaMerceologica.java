package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categoria_merceologica database table.
 * 
 */
@Entity
@Table(name="categoria_merceologica")
@NamedQuery(name="CategoriaMerceologica.findAll", query="SELECT c FROM CategoriaMerceologica c")
public class CategoriaMerceologica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	private String descrizione;

	public CategoriaMerceologica() {
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
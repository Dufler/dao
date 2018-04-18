package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the categoria_merceologica database table.
 * 
 */
@Entity
@Table(name = "categoria_merceologica")
@NamedQuery(name = "CategoriaMerceologica.findAll", query = "SELECT c FROM CategoriaMerceologica c")
public class CategoriaMerceologica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, length = 20)
	private String nome;
	
	@Column(length = 100)
	private String descrizione;

	public CategoriaMerceologica() {}

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
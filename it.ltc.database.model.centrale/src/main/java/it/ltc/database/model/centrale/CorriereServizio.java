package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the corriere_servizio database table.
 * 
 */
@Entity
@Table(name="corriere_servizio")
@NamedQuery(name="CorriereServizio.findAll", query="SELECT c FROM CorriereServizio c")
public class CorriereServizio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CorriereServizioPK id;

	@Column(nullable=false, length=100)
	private String nome;

	public CorriereServizio() {
	}

	public CorriereServizioPK getId() {
		return this.id;
	}

	public void setId(CorriereServizioPK id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
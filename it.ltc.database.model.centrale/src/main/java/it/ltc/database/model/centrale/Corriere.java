package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the corriere database table.
 * 
 */
@Entity
@Table(name="corriere")
@NamedQuery(name="Corriere.findAll", query="SELECT c FROM Corriere c")
public class Corriere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=10)
	private String codifica;

	@Column(nullable=false, length=250)
	private String nome;

	public Corriere() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodifica() {
		return this.codifica;
	}

	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
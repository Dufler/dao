package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fattura_modello database table.
 * 
 */
@Entity
@Table(name="fattura_modello")
@NamedQuery(name="FatturaModello.findAll", query="SELECT f FROM FatturaModello f")
public class FatturaModello implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="id_padre")
	private int idPadre;

	@Column(nullable=true, columnDefinition="BLOB")
	private byte[] modello;

	@Column(nullable=false, length=45)
	private String nome;

	public FatturaModello() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public byte[] getModello() {
		return this.modello;
	}

	public void setModello(byte[] modello) {
		this.modello = modello;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
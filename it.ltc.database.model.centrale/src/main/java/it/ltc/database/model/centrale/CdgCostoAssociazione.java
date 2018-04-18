package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_costo_associazione database table.
 * 
 */
@Entity
@Table(name="cdg_costo_associazione")
@NamedQuery(name="CdgCostoAssociazione.findAll", query="SELECT c FROM CdgCostoAssociazione c")
public class CdgCostoAssociazione implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, precision=8, scale=5)
	private double costo;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false)
	private int sede;

	public CdgCostoAssociazione() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSede() {
		return this.sede;
	}

	public void setSede(int sede) {
		this.sede = sede;
	}

}
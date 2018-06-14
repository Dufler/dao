package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the coordinate_bancarie database table.
 * 
 */
@Entity
@Table(name="coordinate_bancarie")
@NamedQuery(name="CoordinateBancarie.findAll", query="SELECT c FROM CoordinateBancarie c")
public class CoordinateBancarie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(name="nome", nullable=false, length=45)
	private String nome;

	@Column(nullable=false, length=250)
	private String coordinate;

	@Column(nullable=false, length=250)
	private String ente;

	public CoordinateBancarie() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getEnte() {
		return this.ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

}
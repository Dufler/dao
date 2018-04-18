package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cdg_costi_ricavi_generici database table.
 * 
 */
@Entity
@Table(name="cdg_costi_ricavi_generici")
@NamedQuery(name="CdgCostiRicaviGenerici.findAll", query="SELECT c FROM CdgCostiRicaviGenerici c")
public class CdgCostiRicaviGenerici implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Driver { EQUO, COLLI, PEZZI, RICAVI, SPAZI, SPEDIZIONI, TEMPO }

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=1, columnDefinition="ENUM('EQUO', 'COLLI', 'PEZZI', 'RICAVI', 'SPAZI', 'SPEDIZIONI', 'TEMPO')")
	@Enumerated(EnumType.STRING)
	private Driver driver;

	@Column(nullable=false, length=100)
	private String nome;

	private Integer sede;

	public CdgCostiRicaviGenerici() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getSede() {
		return this.sede;
	}

	public void setSede(Integer sede) {
		this.sede = sede;
	}

}
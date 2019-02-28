package it.ltc.database.model.costanti;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regione database table.
 * 
 */
@Entity
@Table(name="regione")
@NamedQuery(name="Regione.findAll", query="SELECT r FROM Regione r")
public class Regione implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codice;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false, length=6)
	private String zona;

	public Regione() {
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

}
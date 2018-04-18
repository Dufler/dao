package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nazione database table.
 * 
 */
@Entity
@Table(name="nazione")
@NamedQuery(name="Nazione.findAll", query="SELECT n FROM Nazione n")
public class Nazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codice_iso_tre", unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codiceIsoTre;

	@Column(name="codice_iso_due", length=2, columnDefinition="CHAR")
	private String codiceIsoDue;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false)
	private boolean ue;

	@Column(length=5)
	private String zona;

	@Column(name="zona_tnt", nullable=false, length=1, columnDefinition="CHAR")
	private String zonaTnt;

	public Nazione() {
	}

	public String getCodiceIsoTre() {
		return this.codiceIsoTre;
	}

	public void setCodiceIsoTre(String codiceIsoTre) {
		this.codiceIsoTre = codiceIsoTre;
	}

	public String getCodiceIsoDue() {
		return this.codiceIsoDue;
	}

	public void setCodiceIsoDue(String codiceIsoDue) {
		this.codiceIsoDue = codiceIsoDue;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getUe() {
		return this.ue;
	}

	public void setUe(boolean ue) {
		this.ue = ue;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getZonaTnt() {
		return this.zonaTnt;
	}

	public void setZonaTnt(String zonaTnt) {
		this.zonaTnt = zonaTnt;
	}

}
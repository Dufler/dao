package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tracking_stato database table.
 * 
 */
@Entity
@Table(name="tracking_stato")
@NamedQuery(name="TrackingStato.findAll", query="SELECT t FROM TrackingStato t")
public class TrackingStato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codice;

	@Column(name="descrizione", columnDefinition="TINYTEXT")
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	public TrackingStato() {}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

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
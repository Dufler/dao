package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the feature database table.
 * 
 */
@Entity
@Table(name="feature")
@NamedQuery(name="FeatureUtenti.findAll", query="SELECT f FROM FeatureUtenti f")
public class FeatureUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable=false, length=250)
	private String featureid;
	
	@Column(nullable=false, length=250)
	private String nome;

	private String versione;

	public FeatureUtenti() {}

	public String getFeatureid() {
		return featureid;
	}

	public void setFeatureid(String featureid) {
		this.featureid = featureid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersione() {
		return this.versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}

}
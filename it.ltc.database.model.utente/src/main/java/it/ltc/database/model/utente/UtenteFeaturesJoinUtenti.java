package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_features_join database table.
 * 
 */
@Entity
@Table(name="utente_features_join")
@IdClass(UtenteFeaturesJoinPKUtenti.class)
@NamedQuery(name="UtenteFeaturesJoinUtenti.findAll", query="SELECT u FROM UtenteFeaturesJoinUtenti u")
public class UtenteFeaturesJoinUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtenteFeaturesJoinPKUtenti id;
	
	@Id
	@Column(updatable=false)
	private String utente;

	@Id
	private String feature;

	public UtenteFeaturesJoinUtenti() {}

//	public UtenteFeaturesJoinPKUtenti getId() {
//		return this.id;
//	}
//
//	public void setId(UtenteFeaturesJoinPKUtenti id) {
//		this.id = id;
//	}
	
	public String getUtente() {
		return this.utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public String getFeature() {
		return this.feature;
	}
	
	public void setFeature(String feature) {
		this.feature = feature;
	}

}
package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_features_join database table.
 * 
 */
@Entity
@Table(name="utente_features_join")
@IdClass(UtenteFeaturesJoinPK.class)
@NamedQuery(name="UtenteFeaturesJoin.findAll", query="SELECT u FROM UtenteFeaturesJoin u")
public class UtenteFeaturesJoin implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtenteFeaturesJoinPK id;
	
	@Id
	@Column(updatable=false, nullable=false, length=50)
	private String utente;

	@Id
	@Column(nullable=false, length=250)
	private String feature;

	public UtenteFeaturesJoin() {}

//	public UtenteFeaturesJoinPK getId() {
//		return this.id;
//	}
//
//	public void setId(UtenteFeaturesJoinPK id) {
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
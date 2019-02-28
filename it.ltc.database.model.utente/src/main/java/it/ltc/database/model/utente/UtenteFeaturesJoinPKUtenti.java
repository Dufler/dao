package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the utente_features_join database table.
 * 
 */
@Embeddable
public class UtenteFeaturesJoinPKUtenti implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private String utente;

	private String feature;

	public UtenteFeaturesJoinPKUtenti() {}
	
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UtenteFeaturesJoinPKUtenti)) {
			return false;
		}
		UtenteFeaturesJoinPKUtenti castOther = (UtenteFeaturesJoinPKUtenti)other;
		return 
			this.utente.equals(castOther.utente)
			&& this.feature.equals(castOther.feature);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.utente.hashCode();
		hash = hash * prime + this.feature.hashCode();
		
		return hash;
	}
	
}
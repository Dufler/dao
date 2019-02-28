package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the utente_features_join database table.
 * 
 */
@Embeddable
public class UtenteFeaturesJoinPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=50)
	private String utente;

	@Column(unique=true, nullable=false, length=250)
	private String feature;

	public UtenteFeaturesJoinPK() {}
	
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
		if (!(other instanceof UtenteFeaturesJoinPK)) {
			return false;
		}
		UtenteFeaturesJoinPK castOther = (UtenteFeaturesJoinPK)other;
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
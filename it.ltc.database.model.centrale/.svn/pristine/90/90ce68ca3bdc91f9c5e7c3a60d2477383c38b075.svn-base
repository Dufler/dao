package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tracking_stato_codifica_corriere database table.
 * 
 */
@Embeddable
public class TrackingStatoCodificaCorrierePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_corriere", insertable=false, updatable=false, unique=true, nullable=false)
	private int idCorriere;

	@Column(name="codifica_corriere", unique=true, nullable=false, length=5)
	private String codificaCorriere;

	public TrackingStatoCodificaCorrierePK() {
	}
	public int getIdCorriere() {
		return this.idCorriere;
	}
	public void setIdCorriere(int idCorriere) {
		this.idCorriere = idCorriere;
	}
	public String getCodificaCorriere() {
		return this.codificaCorriere;
	}
	public void setCodificaCorriere(String codificaCorriere) {
		this.codificaCorriere = codificaCorriere;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TrackingStatoCodificaCorrierePK)) {
			return false;
		}
		TrackingStatoCodificaCorrierePK castOther = (TrackingStatoCodificaCorrierePK)other;
		return 
			(this.idCorriere == castOther.idCorriere)
			&& this.codificaCorriere.equals(castOther.codificaCorriere);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCorriere;
		hash = hash * prime + this.codificaCorriere.hashCode();
		
		return hash;
	}
}
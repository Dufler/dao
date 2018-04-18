package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tracking database table.
 * 
 */
@Embeddable
public class TrackingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_spedizione", insertable=false, updatable=false, unique=true, nullable=false)
	private int idSpedizione;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String stato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_evento", unique=true, nullable=false)
	private java.util.Date data;

	public TrackingPK() {
	}
	public int getIdSpedizione() {
		return this.idSpedizione;
	}
	public void setIdSpedizione(int idOrdine) {
		this.idSpedizione = idOrdine;
	}
	public String getStato() {
		return this.stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public java.util.Date getData() {
		return this.data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TrackingPK)) {
			return false;
		}
		TrackingPK castOther = (TrackingPK)other;
		return 
			(this.idSpedizione == castOther.idSpedizione)
			&& this.stato.equals(castOther.stato)
			&& this.data.equals(castOther.data);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSpedizione;
		hash = hash * prime + this.stato.hashCode();
		hash = hash * prime + this.data.hashCode();
		
		return hash;
	}
}
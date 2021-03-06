package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the tracking database table.
 * 
 */
@Entity
@Table(name="tracking")
@NamedQuery(name="Tracking.findAll", query="SELECT t FROM Tracking t")
public class Tracking implements Serializable, Comparable<Tracking> {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrackingPK id;

	@Column(name="descrizione", columnDefinition="TEXT")
	private String descrizione;

	public Tracking() {}

	public TrackingPK getId() {
		return this.id;
	}

	public void setId(TrackingPK id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int compareTo(Tracking o) {
		int compare = 0;
		if (o != null) {
			Date d1 = getId().getData() != null ? getId().getData() : new Date();
			Date d2 = o.getId().getData() != null ? o.getId().getData() : new Date();
			compare = d1.compareTo(d2);
		}
		return compare;
	}

}
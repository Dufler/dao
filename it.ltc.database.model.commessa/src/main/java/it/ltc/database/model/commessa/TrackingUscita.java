package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tracking_uscita database table.
 * 
 */
@Entity
@Table(name="tracking_uscita")
@NamedQuery(name="TrackingUscita.findAll", query="SELECT t FROM TrackingUscita t")
public class TrackingUscita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrackingUscitaPK id;

	private String note;

	public TrackingUscita() {
	}

	public TrackingUscitaPK getId() {
		return this.id;
	}

	public void setId(TrackingUscitaPK id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tracking_ingresso database table.
 * 
 */
@Entity
@Table(name="tracking_ingresso")
@NamedQuery(name="TrackingIngresso.findAll", query="SELECT t FROM TrackingIngresso t")
public class TrackingIngresso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrackingIngressoPK id;

	private String note;

	public TrackingIngresso() {
	}

	public TrackingIngressoPK getId() {
		return this.id;
	}

	public void setId(TrackingIngressoPK id) {
		this.id = id;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
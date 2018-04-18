package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tracking_stato_codifica_corriere database table.
 * 
 */
@Entity
@Table(name="tracking_stato_codifica_corriere")
@NamedQuery(name="TrackingStatoCodificaCorriere.findAll", query="SELECT t FROM TrackingStatoCodificaCorriere t")
public class TrackingStatoCodificaCorriere implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrackingStatoCodificaCorrierePK id;

	@Column(name="descrizione", columnDefinition="TINYTEXT")
	private String descrizione;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String stato;

	public TrackingStatoCodificaCorriere() {
	}

	public TrackingStatoCodificaCorrierePK getId() {
		return this.id;
	}

	public void setId(TrackingStatoCodificaCorrierePK id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

}
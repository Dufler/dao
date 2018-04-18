package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tracking_uscita database table.
 * 
 */
@Embeddable
public class TrackingUscitaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_uscita", insertable=false, updatable=false)
	private int idUscita;

	@Column(name="evento_uscita", insertable=false, updatable=false)
	private String eventoUscita;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_evento")
	private java.util.Date dataEvento;

	public TrackingUscitaPK() {
	}
	public int getIdUscita() {
		return this.idUscita;
	}
	public void setIdUscita(int idUscita) {
		this.idUscita = idUscita;
	}
	public String getEventoUscita() {
		return this.eventoUscita;
	}
	public void setEventoUscita(String eventoUscita) {
		this.eventoUscita = eventoUscita;
	}
	public java.util.Date getDataEvento() {
		return this.dataEvento;
	}
	public void setDataEvento(java.util.Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TrackingUscitaPK)) {
			return false;
		}
		TrackingUscitaPK castOther = (TrackingUscitaPK)other;
		return 
			(this.idUscita == castOther.idUscita)
			&& this.eventoUscita.equals(castOther.eventoUscita)
			&& this.dataEvento.equals(castOther.dataEvento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUscita;
		hash = hash * prime + this.eventoUscita.hashCode();
		hash = hash * prime + this.dataEvento.hashCode();
		
		return hash;
	}
}
package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tracking_ingresso database table.
 * 
 */
@Embeddable
public class TrackingIngressoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_ingresso", insertable=false, updatable=false)
	private int idIngresso;

	@Column(name="evento_ingresso", insertable=false, updatable=false)
	private String eventoIngresso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_evento")
	private java.util.Date dataEvento;

	public TrackingIngressoPK() {
	}
	public int getIdIngresso() {
		return this.idIngresso;
	}
	public void setIdIngresso(int idIngresso) {
		this.idIngresso = idIngresso;
	}
	public String getEventoIngresso() {
		return this.eventoIngresso;
	}
	public void setEventoIngresso(String eventoIngresso) {
		this.eventoIngresso = eventoIngresso;
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
		if (!(other instanceof TrackingIngressoPK)) {
			return false;
		}
		TrackingIngressoPK castOther = (TrackingIngressoPK)other;
		return 
			(this.idIngresso == castOther.idIngresso)
			&& this.eventoIngresso.equals(castOther.eventoIngresso)
			&& this.dataEvento.equals(castOther.dataEvento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idIngresso;
		hash = hash * prime + this.eventoIngresso.hashCode();
		hash = hash * prime + this.dataEvento.hashCode();
		
		return hash;
	}
}
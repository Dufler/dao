package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cdg_evento_riepilogo database table.
 * 
 */
@Embeddable
public class CdgEventoRiepilogoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int evento;

	@Column(insertable=false, updatable=false)
	private int commessa;

	@Temporal(TemporalType.DATE)
	private java.util.Date giorno;

	public CdgEventoRiepilogoPK() {}
	
	public int getEvento() {
		return this.evento;
	}
	
	public void setEvento(int evento) {
		this.evento = evento;
	}
	
	public int getCommessa() {
		return this.commessa;
	}
	
	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}
	
	public java.util.Date getGiorno() {
		return this.giorno;
	}
	
	public void setGiorno(java.util.Date giorno) {
		this.giorno = giorno;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CdgEventoRiepilogoPK)) {
			return false;
		}
		CdgEventoRiepilogoPK castOther = (CdgEventoRiepilogoPK)other;
		return 
			(this.evento == castOther.evento)
			&& (this.commessa == castOther.commessa)
			&& this.giorno.equals(castOther.giorno);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.evento;
		hash = hash * prime + this.commessa;
		hash = hash * prime + this.giorno.hashCode();
		
		return hash;
	}
}
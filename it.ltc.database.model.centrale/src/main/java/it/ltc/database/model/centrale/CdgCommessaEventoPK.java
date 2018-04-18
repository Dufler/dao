package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cdg_commessa_evento database table.
 * 
 */
@Embeddable
public class CdgCommessaEventoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int evento;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int commessa;

	public CdgCommessaEventoPK() {
	}
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CdgCommessaEventoPK)) {
			return false;
		}
		CdgCommessaEventoPK castOther = (CdgCommessaEventoPK)other;
		return 
			(this.evento == castOther.evento)
			&& (this.commessa == castOther.commessa);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.evento;
		hash = hash * prime + this.commessa;
		
		return hash;
	}
}
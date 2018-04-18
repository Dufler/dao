package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cdg_pezzo_evento database table.
 * 
 */
@Embeddable
public class CdgPezzoEventoPK implements Serializable {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int pezzo;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int evento;

	public CdgPezzoEventoPK() {}
	
	public int getPezzo() {
		return this.pezzo;
	}
	
	public void setPezzo(int pezzo) {
		this.pezzo = pezzo;
	}
	
	public int getEvento() {
		return this.evento;
	}
	
	public void setEvento(int evento) {
		this.evento = evento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CdgPezzoEventoPK)) {
			return false;
		}
		CdgPezzoEventoPK castOther = (CdgPezzoEventoPK)other;
		return 
			(this.pezzo == castOther.pezzo)
			&& (this.evento == castOther.evento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pezzo;
		hash = hash * prime + this.evento;
		
		return hash;
	}
}
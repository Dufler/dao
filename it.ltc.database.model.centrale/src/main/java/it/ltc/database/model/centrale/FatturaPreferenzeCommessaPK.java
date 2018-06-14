package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the fattura_preferenze_commessa database table.
 * 
 */
@Embeddable
public class FatturaPreferenzeCommessaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int commessa;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int ambito;

	public FatturaPreferenzeCommessaPK() {}
	
	public int getCommessa() {
		return this.commessa;
	}
	
	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}
	
	public int getAmbito() {
		return this.ambito;
	}
	
	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FatturaPreferenzeCommessaPK)) {
			return false;
		}
		FatturaPreferenzeCommessaPK castOther = (FatturaPreferenzeCommessaPK)other;
		return 
			(this.commessa == castOther.commessa)
			&& (this.ambito == castOther.ambito);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.commessa;
		hash = hash * prime + this.ambito;	
		return hash;
	}
}
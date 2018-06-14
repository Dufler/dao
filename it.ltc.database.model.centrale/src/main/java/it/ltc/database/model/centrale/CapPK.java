package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cap database table.
 * 
 */
@Embeddable
public class CapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=5, columnDefinition="CHAR")
	private String cap;

	@Column(unique=true, nullable=false, length=200)
	private String localita;

	public CapPK() {}
	
	public String getCap() {
		return this.cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getLocalita() {
		return this.localita;
	}
	
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CapPK)) {
			return false;
		}
		CapPK castOther = (CapPK)other;
		return 
			this.cap.equals(castOther.cap)
			&& this.localita.equals(castOther.localita);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cap.hashCode();
		hash = hash * prime + this.localita.hashCode();
		
		return hash;
	}
}
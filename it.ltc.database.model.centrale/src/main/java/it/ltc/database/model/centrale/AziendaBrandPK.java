package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the azienda_brand database table.
 * 
 */
@Embeddable
public class AziendaBrandPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int brand;

	public AziendaBrandPK() {}
	
	public int getAzienda() {
		return this.azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}
	
	public int getBrand() {
		return this.brand;
	}
	
	public void setBrand(int brand) {
		this.brand = brand;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AziendaBrandPK)) {
			return false;
		}
		AziendaBrandPK castOther = (AziendaBrandPK)other;
		return 
			(this.azienda == castOther.azienda)
			&& (this.brand == castOther.brand);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.azienda;
		hash = hash * prime + this.brand;
		
		return hash;
	}
}
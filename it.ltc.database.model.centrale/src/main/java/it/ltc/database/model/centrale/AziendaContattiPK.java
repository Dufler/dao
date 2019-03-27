package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the azienda_contatti database table.
 * 
 */
@Embeddable
public class AziendaContattiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int contatto;

	public AziendaContattiPK() {}
	
	public AziendaContattiPK(AziendaContatti associazione) {
		this.azienda = associazione.getAzienda();
		this.contatto = associazione.getContatto();
	}
	
	public int getAzienda() {
		return this.azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}
	
	public int getContatto() {
		return this.contatto;
	}
	
	public void setContatto(int contatto) {
		this.contatto = contatto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AziendaContattiPK)) {
			return false;
		}
		AziendaContattiPK castOther = (AziendaContattiPK)other;
		return 
			(this.azienda == castOther.azienda)
			&& (this.contatto == castOther.contatto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.azienda;
		hash = hash * prime + this.contatto;
		
		return hash;
	}
}
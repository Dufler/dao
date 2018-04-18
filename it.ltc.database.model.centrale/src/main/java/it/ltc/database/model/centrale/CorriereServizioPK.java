package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the corriere_servizio database table.
 * 
 */
@Embeddable
public class CorriereServizioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codice;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int corriere;

	@Column(name="codifica_corriere", unique=true, nullable=false, length=10)
	private String codificaCorriere;

	public CorriereServizioPK() {
	}
	public String getCodice() {
		return this.codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public int getCorriere() {
		return this.corriere;
	}
	public void setCorriere(int corriere) {
		this.corriere = corriere;
	}
	public String getCodificaCorriere() {
		return this.codificaCorriere;
	}
	public void setCodificaCorriere(String codificaCorriere) {
		this.codificaCorriere = codificaCorriere;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CorriereServizioPK)) {
			return false;
		}
		CorriereServizioPK castOther = (CorriereServizioPK)other;
		return 
			this.codice.equals(castOther.codice)
			&& (this.corriere == castOther.corriere)
			&& this.codificaCorriere.equals(castOther.codificaCorriere);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codice.hashCode();
		hash = hash * prime + this.corriere;
		hash = hash * prime + this.codificaCorriere.hashCode();
		
		return hash;
	}
}
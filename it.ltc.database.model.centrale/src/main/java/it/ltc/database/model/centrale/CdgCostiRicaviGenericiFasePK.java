package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cdg_costi_ricavi_generici_fase database table.
 * 
 */
@Embeddable
public class CdgCostiRicaviGenericiFasePK implements Serializable {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int generico;

	//@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int fase;

	public CdgCostiRicaviGenericiFasePK() {}
	
	public int getGenerico() {
		return this.generico;
	}
	public void setGenerico(int generico) {
		this.generico = generico;
	}
	public int getFase() {
		return this.fase;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CdgCostiRicaviGenericiFasePK)) {
			return false;
		}
		CdgCostiRicaviGenericiFasePK castOther = (CdgCostiRicaviGenericiFasePK)other;
		return 
			(this.generico == castOther.generico)
			&& (this.fase == castOther.fase);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.generico;
		hash = hash * prime + this.fase;
		
		return hash;
	}
}
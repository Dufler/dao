package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the cdg_costi_ricavi_generici_fase database table.
 * 
 */
@Entity
@Table(name="cdg_costi_ricavi_generici_fase")
@IdClass(CdgCostiRicaviGenericiFasePK.class)
@NamedQuery(name="CdgCostiRicaviGenericiFase.findAll", query="SELECT c FROM CdgCostiRicaviGenericiFase c")
public class CdgCostiRicaviGenericiFase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable=false, unique=true, nullable=false)
	private int generico;

	@Id
	@Column(updatable=false, unique=true, nullable=false)
	private int fase;

	@Column(nullable=false, precision=10, scale=2)
	private double percentuale;

	public CdgCostiRicaviGenericiFase() {}

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

	public double getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}
	
	public CdgCostiRicaviGenericiFasePK getPK() {
		CdgCostiRicaviGenericiFasePK pk = new CdgCostiRicaviGenericiFasePK();
		pk.setFase(fase);
		pk.setGenerico(generico);
		return pk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fase;
		result = prime * result + generico;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CdgCostiRicaviGenericiFase other = (CdgCostiRicaviGenericiFase) obj;
		if (fase != other.fase)
			return false;
		if (generico != other.generico)
			return false;
		return true;
	}

}
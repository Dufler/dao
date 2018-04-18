package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_pezzo_fase database table.
 * 
 */
@Entity
@Table(name="cdg_pezzo_evento")
@NamedQuery(name="CdgPezzoEvento.findAll", query="SELECT c FROM CdgPezzoEvento c")
public class CdgPezzoEvento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CdgPezzoEventoPK id;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private double ricavo;

	public CdgPezzoEvento() {}

	public CdgPezzoEventoPK getId() {
		return this.id;
	}

	public void setId(CdgPezzoEventoPK id) {
		this.id = id;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

}
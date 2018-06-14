package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cdg_budget_percentuali_costo database table.
 * 
 */
@Embeddable
public class CdgBudgetPercentualiCostoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int budget;

	@Column(name="costo_generico", insertable=false, updatable=false, unique=true, nullable=false)
	private int costoGenerico;

	public CdgBudgetPercentualiCostoPK() {}
	
	public int getBudget() {
		return this.budget;
	}
	
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public int getCostoGenerico() {
		return this.costoGenerico;
	}
	
	public void setCostoGenerico(int costoGenerico) {
		this.costoGenerico = costoGenerico;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CdgBudgetPercentualiCostoPK)) {
			return false;
		}
		CdgBudgetPercentualiCostoPK castOther = (CdgBudgetPercentualiCostoPK)other;
		return 
			(this.budget == castOther.budget)
			&& (this.costoGenerico == castOther.costoGenerico);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.budget;
		hash = hash * prime + this.costoGenerico;
		
		return hash;
	}
}
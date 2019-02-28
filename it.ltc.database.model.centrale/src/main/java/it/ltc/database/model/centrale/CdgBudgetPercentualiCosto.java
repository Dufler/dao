package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_budget_percentuali_costo database table.
 * 
 */
@Entity
@Table(name="cdg_budget_percentuali_costo")
@IdClass(CdgBudgetPercentualiCostoPK.class)
@NamedQuery(name="CdgBudgetPercentualiCosto.findAll", query="SELECT c FROM CdgBudgetPercentualiCosto c")
public class CdgBudgetPercentualiCosto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable=false, unique=true, nullable=false)
	private int budget;

	@Id
	@Column(name="costo_generico", updatable=false, unique=true, nullable=false)
	private int costoGenerico;

	@Column(nullable=false, precision=10, scale=2)
	private double percentuale;

	public CdgBudgetPercentualiCosto() {}

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

	public double getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}
	
	public CdgBudgetPercentualiCostoPK getPK() {
		CdgBudgetPercentualiCostoPK pk = new CdgBudgetPercentualiCostoPK();
		pk.setBudget(budget);
		pk.setCostoGenerico(costoGenerico);
		return pk;
	}

}
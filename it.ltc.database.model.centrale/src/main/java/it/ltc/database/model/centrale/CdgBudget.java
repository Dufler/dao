package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cdg_budget database table.
 * 
 */
@Entity
@Table(name="cdg_budget")
@NamedQuery(name="CdgBudget.findAll", query="SELECT c FROM CdgBudget c")
public class CdgBudget implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int commessa;

	@Column(nullable=false, precision=10, scale=3)
	private double costo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_fine", nullable=false)
	private Date dataFine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_inizio", nullable=false)
	private Date dataInizio;

	@Column(length=250)
	private String descrizione;

	@Column(nullable=false, precision=10, scale=3)
	private double ricavo;

	public CdgBudget() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommessa() {
		return this.commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

}
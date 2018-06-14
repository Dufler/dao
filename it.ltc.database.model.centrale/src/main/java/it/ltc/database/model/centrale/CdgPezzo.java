package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.ltc.database.model.centrale.CdgPezzoEvento;


/**
 * The persistent class for the cdg_pezzo database table.
 * 
 */
@Entity
@Table(name="cdg_pezzo")
@NamedQuery(name="CdgPezzo.findAll", query="SELECT c FROM CdgPezzo c")
public class CdgPezzo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="categoria_merceologica", nullable=false, length=20)
	private String categoriaMerceologica;

	@Column(nullable=false)
	private int commessa;

	@Column(nullable=false, precision=10, scale=3)
	private double costo;

	@Column(nullable=false, precision=10, scale=3)
	private double ricavo;
	
	//Aggiunte
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private List<CdgPezzoEvento> spacchettamenti;

	public List<CdgPezzoEvento> getSpacchettamenti() {
		return spacchettamenti;
	}

	public void setSpacchettamenti(List<CdgPezzoEvento> spacchettamenti) {
		this.spacchettamenti = spacchettamenti;
	}
	//Fine

	public CdgPezzo() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaMerceologica() {
		return this.categoriaMerceologica;
	}

	public void setCategoriaMerceologica(String categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
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

	public double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

}
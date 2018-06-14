package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cdg_costi_ricavi_generici_date_valore database table.
 * 
 */
@Entity
@Table(name="cdg_costi_ricavi_generici_date_valore")
@NamedQuery(name="CdgCostiRicaviGenericiDateValore.findAll", query="SELECT c FROM CdgCostiRicaviGenericiDateValore c")
public class CdgCostiRicaviGenericiDateValore implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_effettiva", nullable=false)
	private Date dataEffettiva;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_fine", nullable=false)
	private Date dataFine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_inizio", nullable=false)
	private Date dataInizio;
	
	@Column(name="descrizione", length=250)
	private String descrizione;

	@Column(nullable=false)
	private int generico;
	
	private Integer sede;

	@Column(nullable=false, precision=10, scale=3)
	private double valore;

	public CdgCostiRicaviGenericiDateValore() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataEffettiva() {
		return this.dataEffettiva;
	}

	public void setDataEffettiva(Date dataEffettiva) {
		this.dataEffettiva = dataEffettiva;
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
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getGenerico() {
		return this.generico;
	}

	public void setGenerico(int generico) {
		this.generico = generico;
	}
	
	public Integer getSede() {
		return this.sede;
	}

	public void setSede(Integer sede) {
		this.sede = sede;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}
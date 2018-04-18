package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cdg_bilancio database table.
 * 
 */
@Entity
@Table(name="cdg_costi_ricavi_commesse")
@NamedQuery(name="CdgCostiRicaviCommesse.findAll", query="SELECT c FROM CdgCostiRicaviCommesse c")
public class CdgCostiRicaviCommesse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Tipo { INGRESSO, USCITA }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int commessa;

	@Temporal(TemporalType.DATE)
	@Column(name="data_emissione", nullable=false)
	private Date dataEmissione;

	@Column(nullable=false, length=1, columnDefinition="ENUM('INGRESSO', 'USCITA')")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@Column(nullable=false, precision=10, scale=3)
	private double valore;
	
	@Column(name="fase", nullable=false)
	private int fase;
	
	@Column(length=250)
	private String descrizione;

	public CdgCostiRicaviCommesse() {}

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

	public Date getDataEmissione() {
		return this.dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
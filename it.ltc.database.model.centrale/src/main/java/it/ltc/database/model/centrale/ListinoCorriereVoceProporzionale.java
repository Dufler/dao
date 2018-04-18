package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the listino_corriere_voce_proporzionale database table.
 * 
 */
@Entity
@Table(name="listino_corriere_voce_proporzionale")
@NamedQuery(name="ListinoCorriereVoceProporzionale.findAll", query="SELECT l FROM ListinoCorriereVoceProporzionale l")
public class ListinoCorriereVoceProporzionale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voce", unique=true, nullable=false)
	private int idVoce;

	@Column(precision=10, scale=3)
	private BigDecimal massimo;

	@Column(precision=10, scale=3)
	private BigDecimal minimo;

	@Column(nullable=false, precision=10, scale=3)
	private BigDecimal valore;

	public ListinoCorriereVoceProporzionale() {
	}

	public int getIdVoce() {
		return this.idVoce;
	}

	public void setIdVoce(int idVoce) {
		this.idVoce = idVoce;
	}

	public BigDecimal getMassimo() {
		return this.massimo;
	}

	public void setMassimo(BigDecimal massimo) {
		this.massimo = massimo;
	}

	public BigDecimal getMinimo() {
		return this.minimo;
	}

	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}

	public BigDecimal getValore() {
		return this.valore;
	}

	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}

}
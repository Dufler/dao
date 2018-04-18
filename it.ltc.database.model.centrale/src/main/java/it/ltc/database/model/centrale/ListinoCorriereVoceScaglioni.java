package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the listino_corriere_voce_scaglioni database table.
 * 
 */
@Entity
@Table(name="listino_corriere_voce_scaglioni")
@NamedQuery(name="ListinoCorriereVoceScaglioni.findAll", query="SELECT l FROM ListinoCorriereVoceScaglioni l")
public class ListinoCorriereVoceScaglioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ListinoCorriereVoceScaglioniPK id;

	@Column(nullable=false, precision=10, scale=3)
	private double valore;

	public ListinoCorriereVoceScaglioni() {
	}

	public ListinoCorriereVoceScaglioniPK getId() {
		return this.id;
	}

	public void setId(ListinoCorriereVoceScaglioniPK id) {
		this.id = id;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}
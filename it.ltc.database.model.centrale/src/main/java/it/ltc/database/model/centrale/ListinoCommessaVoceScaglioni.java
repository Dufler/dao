package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the listino_commessa_voce_scaglioni database table.
 * 
 */
@Entity
@Table(name="listino_commessa_voce_scaglioni")
@NamedQuery(name="ListinoCommessaVoceScaglioni.findAll", query="SELECT l FROM ListinoCommessaVoceScaglioni l")
public class ListinoCommessaVoceScaglioni implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ListinoCommessaVoceScaglioniPK id;

	@Column(nullable=false, precision=10, scale=3)
	private double valore;

	public ListinoCommessaVoceScaglioni() {
	}

	public ListinoCommessaVoceScaglioniPK getId() {
		return this.id;
	}

	public void setId(ListinoCommessaVoceScaglioniPK id) {
		this.id = id;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}
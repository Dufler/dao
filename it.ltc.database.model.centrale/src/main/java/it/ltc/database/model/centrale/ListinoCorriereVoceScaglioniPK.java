package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the listino_corriere_voce_scaglioni database table.
 * 
 */
@Embeddable
public class ListinoCorriereVoceScaglioniPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(name = "id_voce", insertable = false, updatable = false, unique = true, nullable = false)
	@Column(name = "id_voce", updatable = false, unique = true, nullable = false)
	private int idVoce;

	@Column(unique = true, nullable = false, precision = 10, scale = 3, columnDefinition = "DECIMAL(10,3)")
	private double inizio;

	@Column(unique = true, nullable = false, precision = 10, scale = 3, columnDefinition = "DECIMAL(10,3)")
	private double fine;

	public ListinoCorriereVoceScaglioniPK() {
	}

	public int getIdVoce() {
		return this.idVoce;
	}

	public void setIdVoce(int idVoce) {
		this.idVoce = idVoce;
	}

	public double getInizio() {
		return this.inizio;
	}

	public void setInizio(double inizio) {
		this.inizio = inizio;
	}

	public double getFine() {
		return this.fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(fine);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + idVoce;
		temp = Double.doubleToLongBits(inizio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListinoCorriereVoceScaglioniPK other = (ListinoCorriereVoceScaglioniPK) obj;
		if (Double.doubleToLongBits(fine) != Double.doubleToLongBits(other.fine))
			return false;
		if (idVoce != other.idVoce)
			return false;
		if (Double.doubleToLongBits(inizio) != Double.doubleToLongBits(other.inizio))
			return false;
		return true;
	}
}
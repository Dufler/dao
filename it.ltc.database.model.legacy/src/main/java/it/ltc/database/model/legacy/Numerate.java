package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Numerate")
public class Numerate implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdNumerata", unique=true, nullable=false)
	private int idNumerata;

	@Column(name="Codice", length=20, nullable=false)
	private String codice;
	
	@Column(name="Posizione", nullable=false)
	private int posizione;
	
	@Column(name="Taglia", length=20, nullable=false)
	private String taglia;
	
	public Numerate() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNumerata;
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
		Numerate other = (Numerate) obj;
		if (idNumerata != other.idNumerata)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Numerate [idNumerata=" + idNumerata + ", codice=" + codice + ", posizione=" + posizione + ", taglia="
				+ taglia + "]";
	}

	public int getIdNumerata() {
		return idNumerata;
	}

	public void setIdNumerata(int idNumerata) {
		this.idNumerata = idNumerata;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

}

package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Marchi")
public class Marchi implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdMarchio", unique=true, nullable=false)
	private int idMarchio;
	
	@Column(name="Codice", nullable=false)
	private int codice;
	
	@Column(name="Descrizione", length=30, nullable=false)
	private String descrizione;
	
	public Marchi() {}

	@Override
	public String toString() {
		return "Marchi [idMarchio=" + idMarchio + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMarchio;
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
		Marchi other = (Marchi) obj;
		if (idMarchio != other.idMarchio)
			return false;
		return true;
	}

	public int getIdMarchio() {
		return idMarchio;
	}

	public void setIdMarchio(int idMarchio) {
		this.idMarchio = idMarchio;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}

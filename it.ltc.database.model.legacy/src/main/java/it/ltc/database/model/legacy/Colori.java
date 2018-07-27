package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Colori database table.
 * 
 */
@Entity
@Table(name="Colori")
//@NamedQuery(name="Colori.findAll", query="SELECT c FROM Colori c")
public class Colori implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Codice", length=12)
	private String codice;

	@Column(name="Descrizione", length=50)
	private String descrizione;

	public Colori() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		Colori other = (Colori) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Colori [codice=" + codice + ", descrizione=" + descrizione + "]";
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
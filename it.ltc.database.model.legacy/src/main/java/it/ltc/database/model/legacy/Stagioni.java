package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Stagioni database table.
 * 
 */
@Entity
@Table(name="Stagioni")
public class Stagioni implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idStagione;

	@Column(name="Codice", length=10)
	private String codice;

	@Column(name="Descrizione", length=50)
	private String descrizione;

	public Stagioni() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStagione;
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
		Stagioni other = (Stagioni) obj;
		if (idStagione != other.idStagione)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stagioni [idStagione=" + idStagione + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

	public int getIdStagione() {
		return this.idStagione;
	}

	public void setIdStagione(int idStagione) {
		this.idStagione = idStagione;
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
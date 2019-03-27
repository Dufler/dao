package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sede database table.
 * 
 */
@Entity
@Table(name="sede")
@NamedQuery(name="SedeUtenti.findAll", query="SELECT s FROM SedeUtenti s")
public class SedeUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String sede;
	
	@Column(name="prefisso_collo", length=2, columnDefinition="CHAR")
	private String prefissoCollo;
	
	@Column(name="indirizzo_web", length=250)
	private String indirizzoWeb;

	public SedeUtenti() {}

	@Override
	public String toString() {
		return "SedeUtenti [id=" + id + ", sede=" + sede + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		SedeUtenti other = (SedeUtenti) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getPrefissoCollo() {
		return prefissoCollo;
	}

	public void setPrefissoCollo(String prefissoCollo) {
		this.prefissoCollo = prefissoCollo;
	}

	public String getIndirizzoWeb() {
		return indirizzoWeb;
	}

	public void setIndirizzoWeb(String indirizzoWeb) {
		this.indirizzoWeb = indirizzoWeb;
	}

}
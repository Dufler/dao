package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sede database table.
 * 
 */
@Entity
@Table(name="sede")
@NamedQuery(name="Sede.findAll", query="SELECT s FROM Sede s")
public class Sede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int indirizzo;

	@Column(name="prefisso_collo", length=2, columnDefinition="CHAR")
	private String prefissoCollo;

	@Column(nullable=false, length=50)
	private String sede;
	
	@Column(name="indirizzo_web", length=250)
	private String indirizzoWeb;

	public Sede() {}

	@Override
	public String toString() {
		return "Sede [id=" + id + ", sede=" + sede + "]";
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
		Sede other = (Sede) obj;
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

	public int getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(int indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPrefissoCollo() {
		return this.prefissoCollo;
	}

	public void setPrefissoCollo(String prefissoCollo) {
		this.prefissoCollo = prefissoCollo;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getIndirizzoWeb() {
		return indirizzoWeb;
	}

	public void setIndirizzoWeb(String indirizzoWeb) {
		this.indirizzoWeb = indirizzoWeb;
	}

}
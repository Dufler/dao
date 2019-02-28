package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="funzione")
@NamedQuery(name="Funzione.findAll", query="SELECT f FROM Funzione f")
public class Funzione implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=250)
	private String partid;
	
	@Column(nullable=false, length=250)
	private String nome;

	@Column(nullable=false, length=250)
	private String feature;
	
	@Column(nullable=false)
	private int permessoid;
	
	@Column(nullable=false, length=250)
	private String icona;
	
	public Funzione() {}

	public String getPartid() {
		return partid;
	}

	public void setPartid(String partid) {
		this.partid = partid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getPermessoid() {
		return permessoid;
	}

	public void setPermessoid(int permessoid) {
		this.permessoid = permessoid;
	}

	public String getIcona() {
		return icona;
	}

	public void setIcona(String icona) {
		this.icona = icona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partid == null) ? 0 : partid.hashCode());
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
		Funzione other = (Funzione) obj;
		if (partid == null) {
			if (other.partid != null)
				return false;
		} else if (!partid.equals(other.partid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funzione [partid=" + partid + ", nome=" + nome + ", permessoid=" + permessoid + "]";
	}
}

package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the feature database table.
 * 
 */
@Entity
@Table(name="feature")
@NamedQuery(name="Feature.findAll", query="SELECT f FROM Feature f")
public class Feature implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=250)
	private String featureid;
	
	@Column(unique=true, nullable=false, length=250)
	private String perspectiveid;
	
	@Column(nullable=false, length=250)
	private String nome;

	@Column(length=45)
	private String versione;
	
	@Column(nullable=false)
	private int permessoid;
	
	@Column(nullable=false, length=250)
	private String icona;
	
	@Column(nullable=false, length=45)
	private String colore;
	
	@Column(nullable=false)
	private int posizione;

	public Feature() {}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersione() {
		return this.versione;
	}

	public void setVersione(String versione) {
		this.versione = versione;
	}

	public String getPerspectiveid() {
		return perspectiveid;
	}

	public void setPerspectiveid(String perspectiveid) {
		this.perspectiveid = perspectiveid;
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

	public String getFeatureid() {
		return featureid;
	}

	public void setFeatureid(String featureid) {
		this.featureid = featureid;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((perspectiveid == null) ? 0 : perspectiveid.hashCode());
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
		Feature other = (Feature) obj;
		if (perspectiveid == null) {
			if (other.perspectiveid != null)
				return false;
		} else if (!perspectiveid.equals(other.perspectiveid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Feature [perspectiveid=" + perspectiveid + ", nome=" + nome + ", permessoid=" + permessoid + "]";
	}

}
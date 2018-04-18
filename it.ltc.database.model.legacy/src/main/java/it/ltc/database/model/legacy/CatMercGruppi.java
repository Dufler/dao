package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the Articoli database table.
 * 
 */
@Entity
@Table(name="CatMercGruppi")
@NamedQuery(name="CatMercGruppi.findAll", query="SELECT c FROM CatMercGruppi c")
public class CatMercGruppi implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdGruCatMer", unique=true, nullable=false)
	private int idGruCatMer;
	
	@Column(name="CodiceGruppo", length=20, nullable=false)
	private String categoria;
	
	@Column(name="Descrizione", length=50)
	private String descrizione;
	
	public CatMercGruppi() {}

	public int getIdGruCatMer() {
		return idGruCatMer;
	}

	public void setIdGruCatMer(int idGruCatMer) {
		this.idGruCatMer = idGruCatMer;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "CatMercGruppi [categoria=" + categoria + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idGruCatMer;
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
		CatMercGruppi other = (CatMercGruppi) obj;
		if (idGruCatMer != other.idGruCatMer)
			return false;
		return true;
	}

}

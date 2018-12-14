package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the Nazioni database table.
 * 
 */
@Entity
@Table(name="Nazioni")
//@NamedQuery(name="Nazioni.findAll", query="SELECT n FROM Nazioni n")
public class Nazioni implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CodIso", unique=true, nullable=false, length=3)
	private String codIso;

	@Column(name="Descrizione", nullable=false, length=50)
	private String descrizione;

	@Column(name="Membro", nullable=false, length=2)
	private String membro;
	
	@Column(name="CodificaCliente", length=10)
	private String codificaCliente;

	public Nazioni() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codIso == null) ? 0 : codIso.hashCode());
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
		Nazioni other = (Nazioni) obj;
		if (codIso == null) {
			if (other.codIso != null)
				return false;
		} else if (!codIso.equals(other.codIso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nazioni [codIso=" + codIso + ", descrizione=" + descrizione + ", membro=" + membro
				+ ", codificaCliente=" + codificaCliente + "]";
	}

	public String getCodIso() {
		return this.codIso;
	}

	public void setCodIso(String codIso) {
		this.codIso = codIso;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMembro() {
		return this.membro;
	}

	public void setMembro(String membro) {
		this.membro = membro;
	}

	public String getCodificaCliente() {
		return codificaCliente;
	}

	public void setCodificaCliente(String codificaCliente) {
		this.codificaCliente = codificaCliente;
	}

}
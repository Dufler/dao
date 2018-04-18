package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the azienda_contatti database table.
 * 
 */
@Entity
@Table(name="azienda_contatti")
@IdClass(AziendaContattiPK.class)
@NamedQuery(name="AziendaContatti.findAll", query="SELECT a FROM AziendaContatti a")
public class AziendaContatti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int contatto;

	public AziendaContatti() {}
	
	public int getAzienda() {
		return this.azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}
	
	public int getContatto() {
		return this.contatto;
	}
	
	public void setContatto(int contatto) {
		this.contatto = contatto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + azienda;
		result = prime * result + contatto;
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
		AziendaContatti other = (AziendaContatti) obj;
		if (azienda != other.azienda)
			return false;
		if (contatto != other.contatto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AziendaContatti [azienda=" + azienda + ", contatto=" + contatto + "]";
	}

}
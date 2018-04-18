package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the azienda_brand database table.
 * 
 */
@Entity
@Table(name="azienda_brand")
@IdClass(AziendaBrandPK.class)
@NamedQuery(name="AziendaBrand.findAll", query="SELECT a FROM AziendaBrand a")
public class AziendaBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int brand;

	public AziendaBrand() {}
	
	public int getAzienda() {
		return this.azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}
	
	public int getBrand() {
		return this.brand;
	}
	
	public void setBrand(int brand) {
		this.brand = brand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + azienda;
		result = prime * result + brand;
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
		AziendaBrand other = (AziendaBrand) obj;
		if (azienda != other.azienda)
			return false;
		if (brand != other.brand)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AziendaBrand [azienda=" + azienda + ", brand=" + brand + "]";
	}

}
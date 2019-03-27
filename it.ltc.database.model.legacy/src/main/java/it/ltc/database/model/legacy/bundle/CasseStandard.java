package it.ltc.database.model.legacy.bundle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CasseStandard")
@NamedQuery(name="CasseStandard.totalePezzi", query="SELECT SUM(c.quantitaProdotto) FROM CasseStandard c WHERE c.codiceCassa = :codice")
public class CasseStandard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
	@Column(name="CodiceCassa", nullable=false, length=20, columnDefinition="varchar(20)")
	private String codiceCassa;
	
	@Column(name="Taglia", nullable=false, length=20, columnDefinition="varchar(20)")
	private String taglia;
	
	@Column(name="Qta", nullable=false)
	private int quantitaProdotto;
	
	public CasseStandard() {}

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
		CasseStandard other = (CasseStandard) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CassaStandard [codiceCassa=" + codiceCassa + ", taglia=" + taglia + ", quantitaProdotto="
				+ quantitaProdotto + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodiceCassa() {
		return codiceCassa;
	}

	public void setCodiceCassa(String codiceCassa) {
		this.codiceCassa = codiceCassa;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public int getQuantitaProdotto() {
		return quantitaProdotto;
	}

	public void setQuantitaProdotto(int quantitaProdotto) {
		this.quantitaProdotto = quantitaProdotto;
	}

}

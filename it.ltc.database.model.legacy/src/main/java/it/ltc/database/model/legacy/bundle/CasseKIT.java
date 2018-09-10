package it.ltc.database.model.legacy.bundle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CasseKIT")
public class CasseKIT implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
	@Column(name="IdBundle")
	private String skuBundle;
	
	@Column(name="idUniArticolo")
	private String skuProdotto;
	
	@Column(name="qta")
	private int quantitaProdotto;
	
	public CasseKIT() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkuBundle() {
		return skuBundle;
	}

	public void setSkuBundle(String skuBundle) {
		this.skuBundle = skuBundle;
	}

	public String getSkuProdotto() {
		return skuProdotto;
	}

	public void setSkuProdotto(String skuProdotto) {
		this.skuProdotto = skuProdotto;
	}

	public Integer getQuantitaProdotto() {
		return quantitaProdotto;
	}

	public void setQuantitaProdotto(Integer quantitaProdotto) {
		this.quantitaProdotto = quantitaProdotto;
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
		CasseKIT other = (CasseKIT) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BUNDLE: [skuBundle=" + skuBundle + ", skuProdotto=" + skuProdotto + ", quantitaProdotto="
				+ quantitaProdotto + "]";
	}

}

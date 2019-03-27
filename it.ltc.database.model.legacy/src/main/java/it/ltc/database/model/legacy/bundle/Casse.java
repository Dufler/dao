package it.ltc.database.model.legacy.bundle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Casse")
public class Casse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
//	@Column(name="idUnivocoCassa", nullable=false, length=15)
//	private String idUnivocoCassa;
//	
//	@Column(name="idUnivocoArticolo", nullable=false, length=15)
//	private String idUnivocoProdotto;
	
	@Column(name="idCassa", nullable=false)
	private int idCassa;
	
	@Column(name="idProdotto", nullable=false)
	private int idProdotto;
	
	@Column(name="qta", nullable=false)
	private int quantitaProdotto;
	
	/**
	 * I tipi possibili sono: STANDARD, BUNDLE e NO.
	 */
	@Column(name="tipo", length=10, nullable=false)
	private String tipo;
	
	/**
	 * Usato solo per casse con prodotti che hanno tutti lo stesso modello. (es. calzature)
	 */
	@Column(name="modello", length=50)
	private String modello;
	
	/**
	 * Codice identificativo della tipologia di cassa con una conformazione standard. (es. calzature tipo cassa W8A = 1x36, 1x37, 2x38, 2x39, 1x40)
	 */
	@Column(name="codiceCassa", length=20)
	private String codiceCassa;
	
	public Casse() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdCassa() {
		return idCassa;
	}

	public void setIdCassa(int idCassa) {
		this.idCassa = idCassa;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

//	public String getIdUnivocoCassa() {
//		return idUnivocoCassa;
//	}
//
//	public void setIdUnivocoCassa(String idUnivocoCassa) {
//		this.idUnivocoCassa = idUnivocoCassa;
//	}
//
//	public String getIdUnivocoProdotto() {
//		return idUnivocoProdotto;
//	}
//
//	public void setIdUnivocoProdotto(String idUnivocoProdotto) {
//		this.idUnivocoProdotto = idUnivocoProdotto;
//	}

	public int getQuantitaProdotto() {
		return quantitaProdotto;
	}

	public void setQuantitaProdotto(int quantitaProdotto) {
		this.quantitaProdotto = quantitaProdotto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getCodiceCassa() {
		return codiceCassa;
	}

	public void setCodiceCassa(String codiceCassa) {
		this.codiceCassa = codiceCassa;
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
		Casse other = (Casse) obj;
		if (id != other.id)
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "BUNDLE: [ID cassa=" + idUnivocoCassa + ", ID Prodotto=" + idUnivocoProdotto + ", quantita="	+ quantitaProdotto + "]";
//	}
	
	@Override
	public String toString() {
		return "BUNDLE: [ID cassa=" + idCassa + ", ID Prodotto=" + idProdotto + ", quantita="	+ quantitaProdotto + "]";
	}

}

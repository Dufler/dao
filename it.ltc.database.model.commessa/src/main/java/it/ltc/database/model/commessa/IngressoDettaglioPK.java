package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ingresso_dettaglio database table.
 * 
 */
@Embeddable
public class IngressoDettaglioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_ingresso", insertable=false, updatable=false)
	private int idIngresso;

	@Column(name="id_prodotto", insertable=false, updatable=false)
	private int idProdotto;

	public IngressoDettaglioPK() {
	}
	public int getIdIngresso() {
		return this.idIngresso;
	}
	public void setIdIngresso(int idIngresso) {
		this.idIngresso = idIngresso;
	}
	public int getIdProdotto() {
		return this.idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IngressoDettaglioPK)) {
			return false;
		}
		IngressoDettaglioPK castOther = (IngressoDettaglioPK)other;
		return 
			(this.idIngresso == castOther.idIngresso)
			&& (this.idProdotto == castOther.idProdotto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idIngresso;
		hash = hash * prime + this.idProdotto;
		
		return hash;
	}
}
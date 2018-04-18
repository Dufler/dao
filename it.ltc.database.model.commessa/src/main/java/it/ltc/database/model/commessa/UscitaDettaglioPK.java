package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the uscita_dettaglio database table.
 * 
 */
@Embeddable
public class UscitaDettaglioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_uscita", insertable=false, updatable=false)
	private int idUscita;

	@Column(name="id_prodotto", insertable=false, updatable=false)
	private int idProdotto;

	@Column(insertable=false, updatable=false)
	private String magazzino;

	public UscitaDettaglioPK() {
	}
	public int getIdUscita() {
		return this.idUscita;
	}
	public void setIdUscita(int idUscita) {
		this.idUscita = idUscita;
	}
	public int getIdProdotto() {
		return this.idProdotto;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public String getMagazzino() {
		return this.magazzino;
	}
	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UscitaDettaglioPK)) {
			return false;
		}
		UscitaDettaglioPK castOther = (UscitaDettaglioPK)other;
		return 
			(this.idUscita == castOther.idUscita)
			&& (this.idProdotto == castOther.idProdotto)
			&& this.magazzino.equals(castOther.magazzino);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUscita;
		hash = hash * prime + this.idProdotto;
		hash = hash * prime + this.magazzino.hashCode();
		
		return hash;
	}
}
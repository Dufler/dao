package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the stoccaggio_movimento database table.
 * 
 */
@Embeddable
public class StoccaggioMovimentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_stoccaggio")
	private int idStoccaggio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_movimento")
	private java.util.Date dataMovimento;

	public StoccaggioMovimentoPK() {
	}
	public int getIdStoccaggio() {
		return this.idStoccaggio;
	}
	public void setIdStoccaggio(int idStoccaggio) {
		this.idStoccaggio = idStoccaggio;
	}
	public java.util.Date getDataMovimento() {
		return this.dataMovimento;
	}
	public void setDataMovimento(java.util.Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StoccaggioMovimentoPK)) {
			return false;
		}
		StoccaggioMovimentoPK castOther = (StoccaggioMovimentoPK)other;
		return 
			(this.idStoccaggio == castOther.idStoccaggio)
			&& this.dataMovimento.equals(castOther.dataMovimento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idStoccaggio;
		hash = hash * prime + this.dataMovimento.hashCode();
		
		return hash;
	}
}
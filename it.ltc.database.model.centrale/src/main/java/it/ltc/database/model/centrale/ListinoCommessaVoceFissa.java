package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the listino_commessa_voce_fissa database table.
 * 
 */
@Entity
@Table(name="listino_commessa_voce_fissa")
@NamedQuery(name="ListinoCommessaVoceFissa.findAll", query="SELECT l FROM ListinoCommessaVoceFissa l")
public class ListinoCommessaVoceFissa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voce", unique=true, nullable=false)
	private int idVoce;

	@Column(nullable=false, precision=10, scale=3)
	private BigDecimal valore;

	public ListinoCommessaVoceFissa() {}

	public int getIdVoce() {
		return this.idVoce;
	}

	public void setIdVoce(int idVoce) {
		this.idVoce = idVoce;
	}

	public BigDecimal getValore() {
		return this.valore;
	}

	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}

}
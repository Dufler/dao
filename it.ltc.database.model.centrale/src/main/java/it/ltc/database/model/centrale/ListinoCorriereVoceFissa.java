package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the listino_corriere_voce_fissa database table.
 * 
 */
@Entity
@Table(name="listino_corriere_voce_fissa")
@NamedQuery(name="ListinoCorriereVoceFissa.findAll", query="SELECT l FROM ListinoCorriereVoceFissa l")
public class ListinoCorriereVoceFissa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voce", unique=true, nullable=false)
	private int idVoce;

	@Column(nullable=false, precision=10, scale=3)
	private BigDecimal valore;

	public ListinoCorriereVoceFissa() {
	}

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
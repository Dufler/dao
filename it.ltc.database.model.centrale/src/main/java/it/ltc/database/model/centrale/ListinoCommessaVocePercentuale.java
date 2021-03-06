package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the listino_commessa_voce_percentuale database table.
 * 
 */
@Entity
@Table(name="listino_commessa_voce_percentuale")
@NamedQuery(name="ListinoCommessaVocePercentuale.findAll", query="SELECT l FROM ListinoCommessaVocePercentuale l")
public class ListinoCommessaVocePercentuale implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_voce", unique=true, nullable=false)
	private int idVoce;

	@Column(nullable=false, precision=10, scale=3)
	private BigDecimal valore;

	@Column(name="valore_massimo", precision=10, scale=3)
	private BigDecimal valoreMassimo;

	@Column(name="valore_minimo", precision=10, scale=3)
	private BigDecimal valoreMinimo;

	public ListinoCommessaVocePercentuale() {}

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

	public BigDecimal getValoreMassimo() {
		return this.valoreMassimo;
	}

	public void setValoreMassimo(BigDecimal valoreMassimo) {
		this.valoreMassimo = valoreMassimo;
	}

	public BigDecimal getValoreMinimo() {
		return this.valoreMinimo;
	}

	public void setValoreMinimo(BigDecimal valoreMinimo) {
		this.valoreMinimo = valoreMinimo;
	}

}
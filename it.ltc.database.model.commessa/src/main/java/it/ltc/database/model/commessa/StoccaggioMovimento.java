package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stoccaggio_movimento database table.
 * 
 */
@Entity
@Table(name="stoccaggio_movimento")
@NamedQuery(name="StoccaggioMovimento.findAll", query="SELECT s FROM StoccaggioMovimento s")
public class StoccaggioMovimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StoccaggioMovimentoPK id;

	private int quantita;

	@Column(name="tipo_movimento")
	private String tipoMovimento;

	public StoccaggioMovimento() {
	}

	public StoccaggioMovimentoPK getId() {
		return this.id;
	}

	public void setId(StoccaggioMovimentoPK id) {
		this.id = id;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getTipoMovimento() {
		return this.tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

}
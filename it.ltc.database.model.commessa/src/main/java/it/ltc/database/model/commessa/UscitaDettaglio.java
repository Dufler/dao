package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the uscita_dettaglio database table.
 * 
 */
@Entity
@Table(name="uscita_dettaglio")
@NamedQuery(name="UscitaDettaglio.findAll", query="SELECT u FROM UscitaDettaglio u")
public class UscitaDettaglio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UscitaDettaglioPK id;

	@Column(name="quantita_evasa")
	private int quantitaEvasa;

	@Column(name="quantita_richiesta")
	private int quantitaRichiesta;

	public UscitaDettaglio() {
	}

	public UscitaDettaglioPK getId() {
		return this.id;
	}

	public void setId(UscitaDettaglioPK id) {
		this.id = id;
	}

	public int getQuantitaEvasa() {
		return this.quantitaEvasa;
	}

	public void setQuantitaEvasa(int quantitaEvasa) {
		this.quantitaEvasa = quantitaEvasa;
	}

	public int getQuantitaRichiesta() {
		return this.quantitaRichiesta;
	}

	public void setQuantitaRichiesta(int quantitaRichiesta) {
		this.quantitaRichiesta = quantitaRichiesta;
	}

}
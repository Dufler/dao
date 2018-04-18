package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingresso_dettaglio database table.
 * 
 */
@Entity
@Table(name="ingresso_dettaglio")
@NamedQuery(name="IngressoDettaglio.findAll", query="SELECT i FROM IngressoDettaglio i")
public class IngressoDettaglio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngressoDettaglioPK id;

	@Column(name="quantita_letta")
	private int quantitaLetta;

	@Column(name="quantita_prevista")
	private int quantitaPrevista;

	public IngressoDettaglio() {
	}

	public IngressoDettaglioPK getId() {
		return this.id;
	}

	public void setId(IngressoDettaglioPK id) {
		this.id = id;
	}

	public int getQuantitaLetta() {
		return this.quantitaLetta;
	}

	public void setQuantitaLetta(int quantitaLetta) {
		this.quantitaLetta = quantitaLetta;
	}

	public int getQuantitaPrevista() {
		return this.quantitaPrevista;
	}

	public void setQuantitaPrevista(int quantitaPrevista) {
		this.quantitaPrevista = quantitaPrevista;
	}

}
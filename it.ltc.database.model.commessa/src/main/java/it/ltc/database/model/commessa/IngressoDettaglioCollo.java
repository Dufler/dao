package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingresso_dettaglio_collo database table.
 * 
 */
@Entity
@Table(name="ingresso_dettaglio_collo")
@NamedQuery(name="IngressoDettaglioCollo.findAll", query="SELECT i FROM IngressoDettaglioCollo i")
public class IngressoDettaglioCollo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String collo;

	@Column(name="id_ingresso")
	private int idIngresso;

	@Column(name="id_prodotto")
	private int idProdotto;

	@Column(name="quantita_stimata")
	private int quantitaStimata;

	@Column(name="quantita_verificata")
	private int quantitaVerificata;

	public IngressoDettaglioCollo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollo() {
		return this.collo;
	}

	public void setCollo(String collo) {
		this.collo = collo;
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

	public int getQuantitaStimata() {
		return this.quantitaStimata;
	}

	public void setQuantitaStimata(int quantitaStimata) {
		this.quantitaStimata = quantitaStimata;
	}

	public int getQuantitaVerificata() {
		return this.quantitaVerificata;
	}

	public void setQuantitaVerificata(int quantitaVerificata) {
		this.quantitaVerificata = quantitaVerificata;
	}

}
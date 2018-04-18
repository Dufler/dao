package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the uscita_dettaglio_collo database table.
 * 
 */
@Entity
@Table(name="uscita_dettaglio_collo")
@NamedQuery(name="UscitaDettaglioCollo.findAll", query="SELECT u FROM UscitaDettaglioCollo u")
public class UscitaDettaglioCollo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String collo;

	@Column(name="id_prodotto")
	private int idProdotto;

	@Column(name="id_uscita")
	private int idUscita;

	@Column(name="quantita_evasa")
	private int quantitaEvasa;

	@Column(name="quantita_richiesta")
	private int quantitaRichiesta;

	public UscitaDettaglioCollo() {
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

	public int getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getIdUscita() {
		return this.idUscita;
	}

	public void setIdUscita(int idUscita) {
		this.idUscita = idUscita;
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
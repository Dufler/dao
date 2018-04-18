package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Scorte2 database table.
 * Dettaglio di tutti i prodotti necessari a completare gli ordini ma che non sono stati ancora ubicati.
 * 
 */
@Entity
@Table(name="Scorte2")
@NamedQuery(name="Scorte2.findAll", query="SELECT s FROM Scorte2 s")
public class Scorte2 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdScorta", unique=true, nullable=false)
	private int idScorta;

	/**
	 * KeyColloCar di colli carico.
	 */
	@Column(name="Collo", length=15)
	private String collo;

	@Column(length=50)
	private String iduniarticolo;

	/**
	 * fissare a noubic.
	 */
	@Column(name="Note", length=50)
	private String note;

	/**
	 * La quantità non ubicata presente all'interno del collo non ubicato.
	 */
	@Column(name="Quantita")
	private int quantita;

	@Column(name="SessioneLavoro", nullable=false, length=50)
	private String sessioneLavoro;

	public Scorte2() {}

	public int getIdScorta() {
		return this.idScorta;
	}

	public void setIdScorta(int idScorta) {
		this.idScorta = idScorta;
	}

	public String getCollo() {
		return this.collo;
	}

	public void setCollo(String collo) {
		this.collo = collo;
	}

	public String getIduniarticolo() {
		return this.iduniarticolo;
	}

	public void setIduniarticolo(String iduniarticolo) {
		this.iduniarticolo = iduniarticolo;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getQuantita() {
		return this.quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getSessioneLavoro() {
		return this.sessioneLavoro;
	}

	public void setSessioneLavoro(String sessioneLavoro) {
		this.sessioneLavoro = sessioneLavoro;
	}

}
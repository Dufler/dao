package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the gadget_inviati database table.
 * 
 */
@Entity
@Table(name="crm_gadget_inviati")
@NamedQuery(name="GadgetInviati.findAll", query="SELECT g FROM GadgetInviati g")
public class GadgetInviati implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int azienda;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_invio", nullable=false)
	private Date dataInvio;

	@Column(nullable=false)
	private int gadget;

	@Column(length=250)
	private String note;

	@Column(nullable=false)
	private int quantita;

	public GadgetInviati() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAzienda() {
		return this.azienda;
	}

	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}

	public Date getDataInvio() {
		return this.dataInvio;
	}

	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	public int getGadget() {
		return this.gadget;
	}

	public void setGadget(int gadget) {
		this.gadget = gadget;
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

}
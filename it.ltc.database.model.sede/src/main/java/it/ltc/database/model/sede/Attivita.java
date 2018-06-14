package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the attivita database table.
 * 
 */
@Entity
@Table(name="attivita")
@NamedQuery(name="Attivita.findAll", query="SELECT a FROM Attivita a")
public class Attivita implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Priorita { BASSA, ALTA, URGENTE }

	@Id
	private int id;

	@Column(name="assegnato_da")
	private String assegnatoDa;

	private int commessa;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_fine")
	private Date dataFine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_inizio")
	private Date dataInizio;

	private String note;

	@Column(nullable=false, length=1, columnDefinition="ENUM('BASSA', 'ALTA', 'URGENTE')")
	@Enumerated(EnumType.STRING)
	private Priorita priorita;

	private String riferimento;

	private int tipo;

	private String utente;

	public Attivita() {}
	
	@PrePersist
	public void prePersist() {
		if (priorita == null)
			priorita = Priorita.BASSA;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssegnatoDa() {
		return this.assegnatoDa;
	}

	public void setAssegnatoDa(String assegnatoDa) {
		this.assegnatoDa = assegnatoDa;
	}

	public int getCommessa() {
		return this.commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Priorita getPriorita() {
		return this.priorita;
	}

	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}

	public String getRiferimento() {
		return this.riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

}
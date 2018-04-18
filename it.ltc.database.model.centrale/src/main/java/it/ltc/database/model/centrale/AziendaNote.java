package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the azienda_note database table.
 * 
 */
@Entity
@Table(name="azienda_note")
@NamedQuery(name="AziendaNote.findAll", query="SELECT a FROM AziendaNote a")
public class AziendaNote implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String autore;

	@Column(nullable=false)
	private int azienda;

	private Integer contatto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_nota", nullable=false)
	private Date dataNota;

	@Column(nullable=false, length=250)
	private String note;

	public AziendaNote() {}
	
	@PrePersist
	public void prePersist() {
		if (dataNota == null)
			dataNota = new Date();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAutore() {
		return this.autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public int getAzienda() {
		return this.azienda;
	}

	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}

	public Integer getContatto() {
		return this.contatto;
	}

	public void setContatto(Integer contatto) {
		this.contatto = contatto;
	}

	public Date getDataNota() {
		return this.dataNota;
	}

	public void setDataNota(Date dataNota) {
		this.dataNota = dataNota;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
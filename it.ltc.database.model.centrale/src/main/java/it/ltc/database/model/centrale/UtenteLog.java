package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the utente_log database table.
 * 
 */
@Entity
@Table(name="utente_log")
@NamedQuery(name="UtenteLog.findAll", query="SELECT u FROM UtenteLog u")
public class UtenteLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date data;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String evento;

	@Column(name="note", columnDefinition="TINYTEXT")
	private String note;

	@Column(nullable=false, length=50)
	private String utente;

	public UtenteLog() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEvento() {
		return this.evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

}
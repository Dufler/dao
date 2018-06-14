package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the contatto database table.
 * 
 */
@Entity
@Table(name="crm_contatto")
@NamedQuery(name="Contatto.findAll", query="SELECT c FROM Contatto c")
public class Contatto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=100)
	private String cognome;

//	@Temporal(TemporalType.DATE)
//	@Column(name="data_di_nascita")
//	private Date dataDiNascita;

	@Column(length=250)
	private String email;

	private Integer indirizzo;

	@Column(nullable=false, length=100)
	private String nome;

	@Column(nullable=false, length=100)
	private String ruolo;

	@Column(length=100)
	private String telefono;

	@Column(nullable=false, length=100)
	private String titolo;

	public Contatto() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

//	public Date getDataDiNascita() {
//		return this.dataDiNascita;
//	}
//
//	public void setDataDiNascita(Date dataDiNascita) {
//		this.dataDiNascita = dataDiNascita;
//	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Integer indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

}
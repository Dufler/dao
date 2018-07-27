package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the Nazioni database table.
 * 
 */
@Entity
@Table(name="Nazioni")
//@NamedQuery(name="Nazioni.findAll", query="SELECT n FROM Nazioni n")
public class Nazioni implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CodIso", unique=true, nullable=false, length=3)
	private String codIso;

	@Column(name="Descrizione", nullable=false, length=50)
	private String descrizione;

	@Column(name="Membro", nullable=false, length=2)
	private String membro;

	public Nazioni() {}

	public String getCodIso() {
		return this.codIso;
	}

	public void setCodIso(String codIso) {
		this.codIso = codIso;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getMembro() {
		return this.membro;
	}

	public void setMembro(String membro) {
		this.membro = membro;
	}

}
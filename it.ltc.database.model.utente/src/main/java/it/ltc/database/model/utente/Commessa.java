package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commessa database table.
 * 
 */
@Entity
@Table(name="commessa")
@NamedQuery(name="Commessa.findAll", query="SELECT c FROM Commessa c")
public class Commessa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descrizione;

	@Column(name="id_sede")
	private int idSede;

	private byte legacy;

	private String nome;

	@Column(name="nome_risorsa")
	private String nomeRisorsa;

	@Column(name="vecchio_db_indirizzo")
	private String vecchioDbIndirizzo;

	@Column(name="vecchio_db_nome")
	private String vecchioDbNome;

	public Commessa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdSede() {
		return this.idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public byte getLegacy() {
		return this.legacy;
	}

	public void setLegacy(byte legacy) {
		this.legacy = legacy;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeRisorsa() {
		return this.nomeRisorsa;
	}

	public void setNomeRisorsa(String nomeRisorsa) {
		this.nomeRisorsa = nomeRisorsa;
	}

	public String getVecchioDbIndirizzo() {
		return this.vecchioDbIndirizzo;
	}

	public void setVecchioDbIndirizzo(String vecchioDbIndirizzo) {
		this.vecchioDbIndirizzo = vecchioDbIndirizzo;
	}

	public String getVecchioDbNome() {
		return this.vecchioDbNome;
	}

	public void setVecchioDbNome(String vecchioDbNome) {
		this.vecchioDbNome = vecchioDbNome;
	}

}
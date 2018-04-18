package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=250)
	private String descrizione;

	@Column(name="id_cliente", nullable=false)
	private int idCliente;

	@Column(name="id_sede", nullable=false)
	private int idSede;

	@Column(nullable=false, length=100)
	private String nome;

	@Column(name="nome_risorsa", nullable=false, length=45, unique=true)
	private String nomeRisorsa;

	@Column(name="vecchio_db_indirizzo", length=45)
	private String vecchioDbIndirizzo;

	@Column(name="vecchio_db_nome", length=45)
	private String vecchioDbNome;
	
	@Column(name="legacy", nullable=false)
	private boolean legacy;
	
	public Commessa() {}
	
	@PrePersist
	public void prePersist() {
		if (nomeRisorsa == null)
			nomeRisorsa = nome;
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

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdSede() {
		return this.idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
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

	public boolean isLegacy() {
		return legacy;
	}

	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

}
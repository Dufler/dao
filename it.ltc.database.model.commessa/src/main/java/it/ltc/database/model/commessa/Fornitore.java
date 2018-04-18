package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fornitore database table.
 * 
 */
@Entity
@NamedQuery(name="Fornitore.findAll", query="SELECT f FROM Fornitore f")
public class Fornitore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_indirizzo")
	private int idIndirizzo;

	private String nome;

	private String note;

	@Column(name="riferimento_cliente")
	private String riferimentoCliente;

	public Fornitore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdIndirizzo() {
		return this.idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRiferimentoCliente() {
		return this.riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

}
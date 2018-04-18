package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the magazzino database table.
 * 
 */
@Entity
@NamedQuery(name="Magazzino.findAll", query="SELECT m FROM Magazzino m")
public class Magazzino implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nome;

	@Column(name="codifica_cliente")
	private String codificaCliente;

	private String descrizione;

	public Magazzino() {
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodificaCliente() {
		return this.codificaCliente;
	}

	public void setCodificaCliente(String codificaCliente) {
		this.codificaCliente = codificaCliente;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
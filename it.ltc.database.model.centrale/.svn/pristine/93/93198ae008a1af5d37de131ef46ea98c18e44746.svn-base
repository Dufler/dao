package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the spedizione_tipo database table.
 * 
 */
@Entity
@Table(name="spedizione_tipo")
@NamedQuery(name="SpedizioneTipo.findAll", query="SELECT s FROM SpedizioneTipo s")
public class SpedizioneTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	public SpedizioneTipo() {
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
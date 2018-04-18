package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the listino_corriere database table.
 * 
 */
@Entity
@Table(name="listino_corriere")
@NamedQuery(name="ListinoCorriere.findAll", query="SELECT l FROM ListinoCorriere l")
public class ListinoCorriere implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="descrizione", columnDefinition="TINYTEXT")
	private String descrizione;

	@Column(name="id_corriere", nullable=false)
	private int idCorriere;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false)
	private int tipo;

	public ListinoCorriere() {}

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

	public int getIdCorriere() {
		return this.idCorriere;
	}

	public void setIdCorriere(int idCorriere) {
		this.idCorriere = idCorriere;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
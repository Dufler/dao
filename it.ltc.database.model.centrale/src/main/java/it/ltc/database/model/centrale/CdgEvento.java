package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_evento database table.
 * 
 */
@Entity
@Table(name="cdg_evento")
@NamedQuery(name="CdgEvento.findAll", query="SELECT c FROM CdgEvento c")
public class CdgEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="categoria_merceologica", nullable=false, length=20)
	private String categoriaMerceologica;

	@Column(length=250)
	private String descrizione;

	@Column(nullable=false)
	private int fase;

	@Column(nullable=false, length=45)
	private String nome;

	public CdgEvento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaMerceologica() {
		return this.categoriaMerceologica;
	}

	public void setCategoriaMerceologica(String categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getFase() {
		return this.fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
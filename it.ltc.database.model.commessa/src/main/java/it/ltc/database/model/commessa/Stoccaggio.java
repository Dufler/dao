package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stoccaggio database table.
 * 
 */
@Entity
@NamedQuery(name="Stoccaggio.findAll", query="SELECT s FROM Stoccaggio s")
public class Stoccaggio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int disponibile;

	private int esistenza;

	@Column(name="id_contenitore")
	private int idContenitore;

	@Column(name="id_prodotto")
	private int idProdotto;

	private String magazzino;

	public Stoccaggio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisponibile() {
		return this.disponibile;
	}

	public void setDisponibile(int disponibile) {
		this.disponibile = disponibile;
	}

	public int getEsistenza() {
		return this.esistenza;
	}

	public void setEsistenza(int esistenza) {
		this.esistenza = esistenza;
	}

	public int getIdContenitore() {
		return this.idContenitore;
	}

	public void setIdContenitore(int idContenitore) {
		this.idContenitore = idContenitore;
	}

	public int getIdProdotto() {
		return this.idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

}
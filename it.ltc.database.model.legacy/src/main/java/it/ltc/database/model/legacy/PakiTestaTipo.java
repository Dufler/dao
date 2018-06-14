package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pakiTestaTipo database table.
 * 
 */
@Entity
@Table(name="pakiTestaTipo")
@NamedQuery(name="PakiTestaTipo.findAll", query="SELECT p FROM PakiTestaTipo p")
public class PakiTestaTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=30)
	private String codice;

	@Lob
	@Column(nullable=false)
	private String descrizione;

	public PakiTestaTipo() {}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
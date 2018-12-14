package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TestataOrdiniTipo database table.
 * 
 */
@Entity
@Table(name="TestataOrdiniTipo")
//@NamedQuery(name="TestataOrdiniTipo.findAll", query="SELECT t FROM TestataOrdiniTipo t")
public class TestataOrdiniTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=30)
	private String codice;

	@Lob
	@Column(nullable=false, columnDefinition="text")
	private String descrizione;

	@Column(nullable=false, length=10)
	private String tipo;

	public TestataOrdiniTipo() {}

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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
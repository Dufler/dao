package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fattura_modalita_pagamento database table.
 * 
 */
@Entity
@Table(name="fattura_modalita_pagamento")
@NamedQuery(name="FatturaModalitaPagamento.findAll", query="SELECT f FROM FatturaModalitaPagamento f")
public class FatturaModalitaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=5)
	private String codice;

	@Column(nullable=false, length=45)
	private String descrizione;

	public FatturaModalitaPagamento() {}

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
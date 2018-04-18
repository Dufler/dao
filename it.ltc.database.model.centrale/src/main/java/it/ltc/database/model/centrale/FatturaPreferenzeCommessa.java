package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fattura_preferenze_commessa database table.
 * 
 */
@Entity
@Table(name="fattura_preferenze_commessa")
@NamedQuery(name="FatturaPreferenzeCommessa.findAll", query="SELECT f FROM FatturaPreferenzeCommessa f")
public class FatturaPreferenzeCommessa implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FatturaPreferenzeCommessaPK id;

	@Column(name="descrizione_fattura", nullable=false, columnDefinition="TEXT")
	private String descrizioneFattura;
	
	@Column(name="layout", columnDefinition="TEXT")
	private String layout;

	@Column(name="modalita_pagamento", nullable=false, length=5)
	private String modalitaPagamento;
	
	@Column(name="coordinate_pagamento", nullable=false)
	private int coordinatePagamento;

	public FatturaPreferenzeCommessa() {}

	public FatturaPreferenzeCommessaPK getId() {
		return this.id;
	}

	public void setId(FatturaPreferenzeCommessaPK id) {
		this.id = id;
	}

	public String getDescrizioneFattura() {
		return this.descrizioneFattura;
	}

	public void setDescrizioneFattura(String descrizioneFattura) {
		this.descrizioneFattura = descrizioneFattura;
	}

	public String getModalitaPagamento() {
		return this.modalitaPagamento;
	}

	public void setModalitaPagamento(String modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public int getCoordinatePagamento() {
		return coordinatePagamento;
	}

	public void setCoordinatePagamento(int coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

}
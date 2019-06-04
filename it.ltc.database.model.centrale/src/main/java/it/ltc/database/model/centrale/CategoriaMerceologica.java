package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the categoria_merceologica database table.
 * 
 */
@Entity
@Table(name = "categoria_merceologica")
@IdClass(CategoriaMerceologicaPK.class)
//@NamedQuery(name = "CategoriaMerceologica.findAll", query = "SELECT c FROM CategoriaMerceologica c")
public class CategoriaMerceologica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum StatoCategoria { ATTIVO, DISATTIVO }

	@Id
	@Column(nullable = false, length = 20)
	private String nome;
	
	@Id
	@Column(nullable = false)
	private int commessa;
	
	@Column(length = 100)
	private String descrizione;
	
	@Column(nullable=false, length=1, columnDefinition="ENUM('ATTIVO', 'DISATTIVO')")
	@Enumerated(EnumType.STRING)
	private StatoCategoria stato;
	
	@Column(nullable = false)
	private int brand;

	public CategoriaMerceologica() {}
	
	@PrePersist
	public void prePersist() {
		if (stato == null) stato = StatoCategoria.ATTIVO;
	}
	
	public CategoriaMerceologicaPK getPK() {
		CategoriaMerceologicaPK pk = new CategoriaMerceologicaPK();
		pk.setCommessa(commessa);
		pk.setNome(nome);
		return pk;
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

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public StatoCategoria getStato() {
		return stato;
	}

	public void setStato(StatoCategoria stato) {
		this.stato = stato;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

}
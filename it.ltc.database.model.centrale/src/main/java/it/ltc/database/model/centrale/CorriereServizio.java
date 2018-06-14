package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the corriere_servizio database table.
 * 
 */
@Entity
@Table(name="corriere_servizio")
@IdClass(CorriereServizioPK.class)
@NamedQuery(name="CorriereServizio.findAll", query="SELECT c FROM CorriereServizio c")
public class CorriereServizio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codice;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int corriere;

	@Id
	@Column(name="codifica_corriere", unique=true, nullable=false, length=10)
	private String codificaCorriere;

	@Column(nullable=false, length=100)
	private String nome;

	public CorriereServizio() {}

	public CorriereServizioPK getPK() {
		CorriereServizioPK pk = new CorriereServizioPK();
		pk.setCodice(codice);
		pk.setCodificaCorriere(codificaCorriere);
		pk.setCorriere(corriere);
		return pk;
	}
	
	public String getCodice() {
		return this.codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public int getCorriere() {
		return this.corriere;
	}
	
	public void setCorriere(int corriere) {
		this.corriere = corriere;
	}
	
	public String getCodificaCorriere() {
		return this.codificaCorriere;
	}
	
	public void setCodificaCorriere(String codificaCorriere) {
		this.codificaCorriere = codificaCorriere;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
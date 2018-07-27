package it.ltc.database.model.legacyltc;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GestionaliConf database table.
 * 
 */
@Entity
@NamedQuery(name="GestionaliConf.findAll", query="SELECT g FROM GestionaliConf g")
public class GestionaliConf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	private int id;

//	@Column(name="Assimballopre")
//	private String assimballopre;

	@Column(name="CliDepProgGest")
	private int cliDepProgGest;

	@Column(name="IdAttivita")
	private int idAttivita;

	@Column(name="IdCosto")
	private int idCosto;

	@Column(name="IdDoc")
	private int idDoc;

	@Column(name="IdMittente")
	private int idMittente;

	@Column(name="IdSettore")
	private int idSettore;

//	@Column(name="TipoEvento")
//	private String tipoEvento;

	public GestionaliConf() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getAssimballopre() {
//		return this.assimballopre;
//	}
//
//	public void setAssimballopre(String assimballopre) {
//		this.assimballopre = assimballopre;
//	}

	public int getCliDepProgGest() {
		return this.cliDepProgGest;
	}

	public void setCliDepProgGest(int cliDepProgGest) {
		this.cliDepProgGest = cliDepProgGest;
	}

	public int getIdAttivita() {
		return this.idAttivita;
	}

	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}

	public int getIdCosto() {
		return this.idCosto;
	}

	public void setIdCosto(int idCosto) {
		this.idCosto = idCosto;
	}

	public int getIdDoc() {
		return this.idDoc;
	}

	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}

	public int getIdMittente() {
		return this.idMittente;
	}

	public void setIdMittente(int idMittente) {
		this.idMittente = idMittente;
	}

	public int getIdSettore() {
		return this.idSettore;
	}

	public void setIdSettore(int idSettore) {
		this.idSettore = idSettore;
	}

//	public String getTipoEvento() {
//		return this.tipoEvento;
//	}
//
//	public void setTipoEvento(String tipoEvento) {
//		this.tipoEvento = tipoEvento;
//	}

}
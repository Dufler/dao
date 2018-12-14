package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the Magazzini database table.
 * 
 */
@Entity
@Table(name="Magazzini")
//@NamedQuery(name="Magazzini.findAll", query="SELECT m FROM Magazzini m")
public class Magazzini implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdMagazzino", unique=true, nullable=false)
	private int idMagazzino;

//	@Column(name="CalcoloGiacenza", nullable=false, length=2)
//	private String calcoloGiacenza;

	@Column(name="CodiceMag", nullable=false, length=3, columnDefinition="char(3)")
	private String codiceMag;

	@Column(name="DesMag", nullable=false, length=40)
	private String desMag;

//	@Column(name="InvioGiacenza", nullable=false, length=2)
//	private String invioGiacenza;

	@Column(name="MagaCliente", nullable=false, length=10)
	private String magaCliente;

//	@Column(name="Scelta", nullable=false)
//	private int scelta;

//	@Column(name="Sequenza", length=1)
//	private String sequenza;

//	@Column(name="Sequenza1")
//	private int sequenza1;

//	@Column(name="StringaDiConnessione", length=150)
//	private String stringaDiConnessione;

//	@Column(name="Tipo", nullable=false, length=1)
//	private String tipo;

//	@Column(name="TipoAssegnazione", nullable=false, length=50)
//	private String tipoAssegnazione;

//	@Column(name="TipoGest", length=10)
//	private String tipoGest;

//	@Column(name="TipoOrdine", nullable=false, length=10)
//	private String tipoOrdine;

	public Magazzini() {}

	public int getIdMagazzino() {
		return this.idMagazzino;
	}

	public void setIdMagazzino(int idMagazzino) {
		this.idMagazzino = idMagazzino;
	}

//	public String getCalcoloGiacenza() {
//		return this.calcoloGiacenza;
//	}
//
//	public void setCalcoloGiacenza(String calcoloGiacenza) {
//		this.calcoloGiacenza = calcoloGiacenza;
//	}

	public String getCodiceMag() {
		return this.codiceMag;
	}

	public void setCodiceMag(String codiceMag) {
		this.codiceMag = codiceMag;
	}

	public String getDesMag() {
		return this.desMag;
	}

	public void setDesMag(String desMag) {
		this.desMag = desMag;
	}

//	public String getInvioGiacenza() {
//		return this.invioGiacenza;
//	}
//
//	public void setInvioGiacenza(String invioGiacenza) {
//		this.invioGiacenza = invioGiacenza;
//	}

	public String getMagaCliente() {
		return this.magaCliente;
	}

	public void setMagaCliente(String magaCliente) {
		this.magaCliente = magaCliente;
	}

//	public int getScelta() {
//		return this.scelta;
//	}
//
//	public void setScelta(int scelta) {
//		this.scelta = scelta;
//	}
//
//	public String getSequenza() {
//		return this.sequenza;
//	}
//
//	public void setSequenza(String sequenza) {
//		this.sequenza = sequenza;
//	}
//
//	public int getSequenza1() {
//		return this.sequenza1;
//	}
//
//	public void setSequenza1(int sequenza1) {
//		this.sequenza1 = sequenza1;
//	}
//
//	public String getStringaDiConnessione() {
//		return this.stringaDiConnessione;
//	}
//
//	public void setStringaDiConnessione(String stringaDiConnessione) {
//		this.stringaDiConnessione = stringaDiConnessione;
//	}
//
//	public String getTipo() {
//		return this.tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}
//
//	public String getTipoAssegnazione() {
//		return this.tipoAssegnazione;
//	}
//
//	public void setTipoAssegnazione(String tipoAssegnazione) {
//		this.tipoAssegnazione = tipoAssegnazione;
//	}
//
//	public String getTipoGest() {
//		return this.tipoGest;
//	}
//
//	public void setTipoGest(String tipoGest) {
//		this.tipoGest = tipoGest;
//	}
//
//	public String getTipoOrdine() {
//		return this.tipoOrdine;
//	}
//
//	public void setTipoOrdine(String tipoOrdine) {
//		this.tipoOrdine = tipoOrdine;
//	}

}
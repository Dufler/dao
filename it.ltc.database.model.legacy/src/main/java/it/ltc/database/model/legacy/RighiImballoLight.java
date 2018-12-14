package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RighiImballo")
public class RighiImballoLight implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idRighiImballo;
	
	@Column(name="CodBarre", length=50)
	private String codBarre;

	@Column(name="CodiceArticolo", length=50)
	private String codiceArticolo;
	
	@Column(name="IdUniArticolo", length=15, nullable=false, columnDefinition="varchar(15)")
	private String idUniArticolo;
	
	@Column(name="KeyColloSpe", length=10, columnDefinition="char(10)")
	private String keyColloSpe;
	
	@Column(name="NrLista", length=21)
	private String nrLista;

	@Column(name="NrOrdine", length=20)
	private String nrOrdine;
	
	@Column(name="NrRigoOrdine")
	private int nrRigoOrdine;
	
	@Column(name="QtaImballata")
	private int qtaImballata;
	
	@Column(name="Seriale", length=50)
	private String seriale;
	
	public RighiImballoLight() {}

	public int getIdRighiImballo() {
		return idRighiImballo;
	}

	public void setIdRighiImballo(int idRighiImballo) {
		this.idRighiImballo = idRighiImballo;
	}

	public String getCodBarre() {
		return codBarre;
	}

	public void setCodBarre(String codBarre) {
		this.codBarre = codBarre;
	}

	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getIdUniArticolo() {
		return idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}
	
	public String getKeyColloSpe() {
		return this.keyColloSpe;
	}

	public void setKeyColloSpe(String keyColloSpe) {
		this.keyColloSpe = keyColloSpe;
	}

	public String getNrLista() {
		return nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

	public String getNrOrdine() {
		return nrOrdine;
	}

	public void setNrOrdine(String nrOrdine) {
		this.nrOrdine = nrOrdine;
	}

	public int getNrRigoOrdine() {
		return nrRigoOrdine;
	}

	public void setNrRigoOrdine(int nrRigoOrdine) {
		this.nrRigoOrdine = nrRigoOrdine;
	}
	
	public int getQtaImballata() {
		return this.qtaImballata;
	}

	public void setQtaImballata(int qtaImballata) {
		this.qtaImballata = qtaImballata;
	}

	public String getSeriale() {
		return seriale;
	}

	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

}

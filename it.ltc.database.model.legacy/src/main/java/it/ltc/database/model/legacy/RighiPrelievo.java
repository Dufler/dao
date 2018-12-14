package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RighiPrelievo")
public class RighiPrelievo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(name="NrLista", length=21, nullable=false)
	private String nrLista;
	
	@Column(name="Operatore", length=50)
	private String operatore;
	
	@Column(name="IdUniArticolo", length=15, nullable=false)
	private String idUniArticolo;
	
	@Column(name="QtaPrelevata", nullable=false)
	private int qtaPrelevata;
	
	@Column(name="KeyColloCar", length=9, nullable=false, columnDefinition="char(9)")
	private String keyColloCar;
	
	public RighiPrelievo() {}

	@Override
	public String toString() {
		return "RighiPrelievo [id=" + id + ", nrLista=" + nrLista + ", operatore=" + operatore + ", idUniArticolo=" + idUniArticolo + ", qtaPrelevata=" + qtaPrelevata + ", keyColloCar=" + keyColloCar + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RighiPrelievo other = (RighiPrelievo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNrLista() {
		return nrLista;
	}

	public void setNrLista(String nrLista) {
		this.nrLista = nrLista;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public String getIdUniArticolo() {
		return idUniArticolo;
	}

	public void setIdUniArticolo(String idUniArticolo) {
		this.idUniArticolo = idUniArticolo;
	}

	public int getQtaPrelevata() {
		return qtaPrelevata;
	}

	public void setQtaPrelevata(int qtaPrelevata) {
		this.qtaPrelevata = qtaPrelevata;
	}

	public String getKeyColloCar() {
		return keyColloCar;
	}

	public void setKeyColloCar(String keyColloCar) {
		this.keyColloCar = keyColloCar;
	}
	
}

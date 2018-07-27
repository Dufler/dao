package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Corrieri database table.
 * 
 */
@Entity
@Table(name="Corrieri")
public class Corrieri implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdCorriere", unique=true, nullable=false)
	private int idCorriere;

	@Column(name="Codice", length=3)
	private String codice;

	@Column(name="CodiceCliente", length=50)
	private String codiceCliente;

	@Column(name="CodiceTariffa")
	private int codiceTariffa;

	@Column(name="Descrizione", length=50)
	private String descrizione;

	@Column(name="PuntoOperatorePartenza", length=10)
	private String puntoOperatorePartenza;

	public Corrieri() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCorriere;
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
		Corrieri other = (Corrieri) obj;
		if (idCorriere != other.idCorriere)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Corrieri [idCorriere=" + idCorriere + ", codice=" + codice + ", codiceCliente=" + codiceCliente + ", codiceTariffa=" + codiceTariffa + ", descrizione=" + descrizione + ", puntoOperatorePartenza=" + puntoOperatorePartenza + "]";
	}

	public int getIdCorriere() {
		return this.idCorriere;
	}

	public void setIdCorriere(int idCorriere) {
		this.idCorriere = idCorriere;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceCliente() {
		return this.codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public int getCodiceTariffa() {
		return this.codiceTariffa;
	}

	public void setCodiceTariffa(int codiceTariffa) {
		this.codiceTariffa = codiceTariffa;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getPuntoOperatorePartenza() {
		return this.puntoOperatorePartenza;
	}

	public void setPuntoOperatorePartenza(String puntoOperatorePartenza) {
		this.puntoOperatorePartenza = puntoOperatorePartenza;
	}

}
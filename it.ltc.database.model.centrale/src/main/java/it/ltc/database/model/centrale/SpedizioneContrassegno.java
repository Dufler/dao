package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the spedizione_contrassegno database table.
 * 
 */
@Entity
@Table(name="spedizione_contrassegno")
@NamedQuery(name="SpedizioneContrassegno.findAll", query="SELECT s FROM SpedizioneContrassegno s")
public class SpedizioneContrassegno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_spedizione", unique=true, nullable=false)
	private int idSpedizione;

	@Column(nullable=false)
	private boolean annullato;

	@Column(nullable=false, length=2, columnDefinition="CHAR")
	private String tipo;

	@Column(nullable=false, precision=10, scale=2)
	private Double valore;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String valuta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica", nullable=false, insertable=false)
	private Date dataUltimaModifica;

	public SpedizioneContrassegno() {}

	public int getIdSpedizione() {
		return this.idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public boolean getAnnullato() {
		return this.annullato;
	}

	public void setAnnullato(boolean annullato) {
		this.annullato = annullato;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValore() {
		return this.valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getValuta() {
		return this.valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}
	
	@Override
	public String toString() {
		return "Contrassegno [idSpedizione=" + idSpedizione + ", valore=" + valore + ", valuta=" + valuta + ", tipo=" + tipo + ", annullato=" + annullato + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSpedizione;
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
		SpedizioneContrassegno other = (SpedizioneContrassegno) obj;
		if (idSpedizione != other.idSpedizione)
			return false;
		return true;
	}

}
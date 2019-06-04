package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Delivery")
public class Delivery implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdDelivery", unique=true, nullable=false)
	private int idDelivery;
	
	@Column(name="dataCreazione", columnDefinition="datetime", insertable=false, nullable=false)
	private Date dataCreazione;
	
	@Column(name="utente", length=50, nullable=false)
	private String utente;
	
	@Column(name="corriere", length=20, nullable=false)
	private String corriere;
	
	@Column(name="totaleSpedizioni", nullable=false)
	private int totaleSpedizioni;
	
	@Column(name="totaleColli", nullable=false)
	private int totaleColli;
	
	@Column(name="totalePeso", columnDefinition="money", nullable=false)
	private double totalePeso;
	
	@Column(name="totaleVolume", columnDefinition="money", nullable=false)
	private double totaleVolume;
	
	public Delivery() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDelivery;
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
		Delivery other = (Delivery) obj;
		if (idDelivery != other.idDelivery)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Delivery [idDelivery=" + idDelivery + ", dataCreazione=" + dataCreazione + ", utente=" + utente + ", corriere=" + corriere + ", totaleSpedizioni=" + totaleSpedizioni + ", totalePeso=" + totalePeso + ", totaleVolume=" + totaleVolume + "]";
	}

	public int getIdDelivery() {
		return idDelivery;
	}

	public void setIdDelivery(int idDelivery) {
		this.idDelivery = idDelivery;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getCorriere() {
		return corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	public int getTotaleSpedizioni() {
		return totaleSpedizioni;
	}

	public void setTotaleSpedizioni(int totaleSpedizioni) {
		this.totaleSpedizioni = totaleSpedizioni;
	}

	public int getTotaleColli() {
		return totaleColli;
	}

	public void setTotaleColli(int totaleColli) {
		this.totaleColli = totaleColli;
	}

	public double getTotalePeso() {
		return totalePeso;
	}

	public void setTotalePeso(double totalePeso) {
		this.totalePeso = totalePeso;
	}

	public double getTotaleVolume() {
		return totaleVolume;
	}

	public void setTotaleVolume(double totaleVolume) {
		this.totaleVolume = totaleVolume;
	}

}

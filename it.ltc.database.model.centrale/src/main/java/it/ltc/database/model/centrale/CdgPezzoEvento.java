package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_pezzo_fase database table.
 * 
 */
@Entity
@Table(name="cdg_pezzo_evento")
@IdClass(CdgPezzoEventoPK.class)
@NamedQuery(name="CdgPezzoEvento.findAll", query="SELECT c FROM CdgPezzoEvento c")
public class CdgPezzoEvento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable=false, nullable=false)
	private int pezzo;

	@Id
	@Column(updatable=false, nullable=false)
	private int evento;

	@Column(nullable=false)
	private double costo;

	@Column(nullable=false)
	private double ricavo;

	public CdgPezzoEvento() {}

	public CdgPezzoEventoPK getPK() {
		CdgPezzoEventoPK pk = new CdgPezzoEventoPK();
		pk.setEvento(evento);
		pk.setPezzo(pezzo);
		return pk;
	}

	public int getPezzo() {
		return this.pezzo;
	}
	
	public void setPezzo(int pezzo) {
		this.pezzo = pezzo;
	}
	
	public int getEvento() {
		return this.evento;
	}
	
	public void setEvento(int evento) {
		this.evento = evento;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

}
package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_commessa_evento database table.
 * 
 */
@Entity
@Table(name="cdg_commessa_evento")
@IdClass(CdgCommessaEventoPK.class)
@NamedQuery(name="CdgCommessaEvento.findAll", query="SELECT c FROM CdgCommessaEvento c")
public class CdgCommessaEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(updatable=false, nullable=false)
	private int evento;

	@Id
	@Column(updatable=false, nullable=false)
	private int commessa;

	@Column(nullable=false)
	private int durata;

	public CdgCommessaEvento() {}
	
	public int getEvento() {
		return this.evento;
	}
	
	public void setEvento(int evento) {
		this.evento = evento;
	}
	
	public int getCommessa() {
		return this.commessa;
	}
	
	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public CdgCommessaEventoPK getPK() {
		CdgCommessaEventoPK pk = new CdgCommessaEventoPK();
		pk.setCommessa(commessa);
		pk.setEvento(evento);
		return pk;
	}

	public int getDurata() {
		return this.durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

}
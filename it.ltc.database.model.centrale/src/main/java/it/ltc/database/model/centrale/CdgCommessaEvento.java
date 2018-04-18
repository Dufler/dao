package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_commessa_evento database table.
 * 
 */
@Entity
@Table(name="cdg_commessa_evento")
@NamedQuery(name="CdgCommessaEvento.findAll", query="SELECT c FROM CdgCommessaEvento c")
public class CdgCommessaEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CdgCommessaEventoPK id;

	@Column(nullable=false)
	private int durata;

	public CdgCommessaEvento() {
	}

	public CdgCommessaEventoPK getId() {
		return this.id;
	}

	public void setId(CdgCommessaEventoPK id) {
		this.id = id;
	}

	public int getDurata() {
		return this.durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

}
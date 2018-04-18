package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_evento_riepilogo database table.
 * 
 */
@Entity
@Table(name="cdg_evento_riepilogo")
@NamedQuery(name="CdgEventoRiepilogo.findAll", query="SELECT c FROM CdgEventoRiepilogo c")
public class CdgEventoRiepilogo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CdgEventoRiepilogoPK id;

	@Column(name="durata_totale")
	private int durataTotale;

	private String operatore;

	private int pezzi;

	public CdgEventoRiepilogo() {}

	public CdgEventoRiepilogoPK getId() {
		return this.id;
	}

	public void setId(CdgEventoRiepilogoPK id) {
		this.id = id;
	}

	public int getDurataTotale() {
		return this.durataTotale;
	}

	public void setDurataTotale(int durataTotale) {
		this.durataTotale = durataTotale;
	}

	public String getOperatore() {
		return this.operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

}
package it.ltc.database.model.sede;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_evento_riepilogo database table.
 * 
 */
@Entity
@Table(name="cdg_evento_riepilogo")
@IdClass(CdgEventoRiepilogoPK.class)
@NamedQuery(name="CdgEventoRiepilogo.findAll", query="SELECT c FROM CdgEventoRiepilogo c")
public class CdgEventoRiepilogo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(insertable=false, updatable=false)
	private int evento;

	@Id
	@Column(insertable=false, updatable=false)
	private int commessa;

	@Id
	@Temporal(TemporalType.DATE)
	private java.util.Date giorno;

//	@EmbeddedId
//	private CdgEventoRiepilogoPK id;

	@Column(name="durata_totale")
	private int durataTotale;

	private String operatore;

	private int pezzi;

	public CdgEventoRiepilogo() {}
	
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
	
	public java.util.Date getGiorno() {
		return this.giorno;
	}
	
	public void setGiorno(java.util.Date giorno) {
		this.giorno = giorno;
	}
	
	public CdgEventoRiepilogoPK getPK() {
		CdgEventoRiepilogoPK pk = new CdgEventoRiepilogoPK();
		pk.setCommessa(commessa);
		pk.setEvento(evento);
		pk.setGiorno(giorno);
		return pk;
	}

//	public CdgEventoRiepilogoPK getId() {
//		return this.id;
//	}
//
//	public void setId(CdgEventoRiepilogoPK id) {
//		this.id = id;
//	}

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
package it.ltc.database.model.sede.json;

import java.util.Date;

public class CdgEventoRiepilogoJSON {
	
	private int evento;
	private int commessa;
	private Date giorno;
	private int durataTotale;
	private String operatore;
	private int pezzi;
	
	public CdgEventoRiepilogoJSON() {}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public Date getGiorno() {
		return giorno;
	}

	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}

	public int getDurataTotale() {
		return durataTotale;
	}

	public void setDurataTotale(int durataTotale) {
		this.durataTotale = durataTotale;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public int getPezzi() {
		return pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

}

package it.ltc.database.model.centrale.json;

public class VoceScaglioneJSON {
	
	private int idVoce;
	private double inizio;
	private double fine;
	private double valore;
	
	public VoceScaglioneJSON() {}

	public int getIdVoce() {
		return idVoce;
	}

	public void setIdVoce(int idVoce) {
		this.idVoce = idVoce;
	}

	public double getInizio() {
		return inizio;
	}

	public void setInizio(double inizio) {
		this.inizio = inizio;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public double getValore() {
		return valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}

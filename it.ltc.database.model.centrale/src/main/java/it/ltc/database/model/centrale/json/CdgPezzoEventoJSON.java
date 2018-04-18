package it.ltc.database.model.centrale.json;

public class CdgPezzoEventoJSON {
	
	private int pezzo;
	private int evento;
	private double costo;
	private double ricavo;
	
	public CdgPezzoEventoJSON() {}

	public int getPezzo() {
		return pezzo;
	}

	public void setPezzo(int pezzo) {
		this.pezzo = pezzo;
	}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int fase) {
		this.evento = fase;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getRicavo() {
		return ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

}

package it.ltc.database.dao.ordini.uscita;

import java.util.LinkedList;
import java.util.List;

public class RisultatoGenerazioneMovimentiUscita {
	
	private final List<String> problemi;
	
	public RisultatoGenerazioneMovimentiUscita() {
		this.problemi = new LinkedList<>();
	}
	
	public void addProblema(String problema) {
		problemi.add(problema);
	}

	public List<String> getProblemi() {
		return problemi;
	}

}

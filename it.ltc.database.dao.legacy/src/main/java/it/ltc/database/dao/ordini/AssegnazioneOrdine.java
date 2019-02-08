package it.ltc.database.dao.ordini;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.model.legacy.TestataOrdini;

public class AssegnazioneOrdine {
	
	private final TestataOrdini testata;
	private final List<AssegnazioneProdotto> prodottiAssegnati;
	
	public AssegnazioneOrdine(TestataOrdini testata) {
		this.testata = testata;
		this.prodottiAssegnati = new LinkedList<>();
	}
	
	public TestataOrdini getTestata() {
		return testata;
	}
	
	public List<AssegnazioneProdotto> getProdottiAssegnati() {
		return prodottiAssegnati;
	}
	
	public void aggiungiAssegnazione(AssegnazioneProdotto assegnazione) {
		prodottiAssegnati.add(assegnazione);
	}

}

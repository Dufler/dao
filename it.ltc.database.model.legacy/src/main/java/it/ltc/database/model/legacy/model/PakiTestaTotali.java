package it.ltc.database.model.legacy.model;

/**
 * Classe che mappa il risultato del conteggio della somma del dichiatato e riscontrato per un carico.
 * @author Damiano
 *
 */
public class PakiTestaTotali {
	
	private final Long totaleDichiarato;
	private final Long totaleRiscontrato;
	
	public PakiTestaTotali(Long totaleDichiarato, Long totaleRiscontrato) {
		this.totaleDichiarato = totaleDichiarato;
		this.totaleRiscontrato = totaleRiscontrato;
	}
	
	public PakiTestaTotali(Integer totaleDichiarato, Integer totaleRiscontrato) {
		this.totaleDichiarato = (long) totaleDichiarato;
		this.totaleRiscontrato = (long) totaleRiscontrato;
	}
	
	public int getTotaleDichiarato() {
		return totaleDichiarato != null ? totaleDichiarato.intValue() : -1;
	}
	
	public int getTotaleRiscontrato() {
		return totaleRiscontrato != null ? totaleRiscontrato.intValue() : -1;
	}

}

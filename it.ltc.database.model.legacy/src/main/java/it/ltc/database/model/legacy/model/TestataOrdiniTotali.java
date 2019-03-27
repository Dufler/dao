package it.ltc.database.model.legacy.model;

/**
 * Classe che mappa il risultato del conteggio della somma dell'ordinato, imballato ed assegnato per un ordine.
 * 
 * @author Damiano
 *
 */
public class TestataOrdiniTotali {

	private final Long totaleOrdinato;
	private final Long totaleImballato;
	private final Long totaleAssegnato;

	public TestataOrdiniTotali(Long totaleOrdinato, Long totaleImballato, Long totaleAssegnato) {
		this.totaleOrdinato = totaleOrdinato;
		this.totaleImballato = totaleImballato;
		this.totaleAssegnato = totaleAssegnato;
	}
	
	public TestataOrdiniTotali(Integer totaleOrdinato, Integer totaleImballato, Integer totaleAssegnato) {
		this.totaleOrdinato = (long) totaleOrdinato;
		this.totaleImballato = (long) totaleImballato;
		this.totaleAssegnato = (long) totaleAssegnato;
	}

	public int getTotaleOrdinato() {
		return totaleOrdinato != null ? totaleOrdinato.intValue() : 0;
	}

	public int getTotaleImballato() {
		return totaleImballato != null ? totaleImballato.intValue() : 0;
	}

	public int getTotaleAssegnato() {
		return totaleAssegnato != null ? totaleAssegnato.intValue() : 0;
	}

}

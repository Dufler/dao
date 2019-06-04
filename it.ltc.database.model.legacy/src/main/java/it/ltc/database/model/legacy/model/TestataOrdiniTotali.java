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
	private final Long totalePrelevato;
	
	private final Long totaleOrdinatoEffettivo;
	private final Long totaleImballatoEffettivo;

	public TestataOrdiniTotali(Long totaleOrdinato, Long totaleImballato, Long totaleAssegnato, Long totalePrelevato, Long totaleOrdinatoEffettivo, Long totaleImballatoEffettivo) {
		this.totaleOrdinato = totaleOrdinato;
		this.totaleImballato = totaleImballato;
		this.totaleAssegnato = totaleAssegnato;
		this.totalePrelevato = totalePrelevato;
		this.totaleOrdinatoEffettivo = totaleOrdinatoEffettivo;
		this.totaleImballatoEffettivo = totaleImballatoEffettivo;
	}
	
	public TestataOrdiniTotali(Integer totaleOrdinato, Integer totaleImballato, Integer totaleAssegnato, Integer totalePrelevato, Integer totaleOrdinatoEffettivo, Integer totaleImballatoEffettivo) {
		this.totaleOrdinato = (long) totaleOrdinato;
		this.totaleImballato = (long) totaleImballato;
		this.totaleAssegnato = (long) totaleAssegnato;
		this.totalePrelevato = (long) totalePrelevato;
		this.totaleOrdinatoEffettivo = (long) totaleOrdinatoEffettivo;
		this.totaleImballatoEffettivo = (long) totaleImballatoEffettivo;
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

	public int getTotalePrelevato() {
		return totalePrelevato != null ? totalePrelevato.intValue() : 0;
	}
	
	public int getTotaleOrdinatoEffettivo() {
		return totaleOrdinatoEffettivo != null ? totaleOrdinatoEffettivo.intValue() : 0;
	}
	
	public int getTotaleImballatoEffettivo() {
		return totaleImballatoEffettivo != null ? totaleImballatoEffettivo.intValue() : 0;
	}

}

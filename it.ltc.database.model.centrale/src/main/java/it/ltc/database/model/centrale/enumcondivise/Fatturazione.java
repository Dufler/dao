package it.ltc.database.model.centrale.enumcondivise;

/**
 * Definisce i possibili stati di fatturazione.
 * Lo stato di default, <code>NON_FATTURABILE</code>, è uno stato pozzo per gestire i casi limite, come le simulazioni, che non dovranno mai essere fatturate.
 * @author Damiano
 *
 */
public enum Fatturazione { 
	
	NON_FATTURABILE("Non fatturabile"),
	IN_DEFINIZIONE("Non ancora fatturabile"),
	FATTURABILE("Fatturabile"),
	FATTURATA("Gia' fatturata");
	
	private final String descrizione;
	
	private Fatturazione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}
}
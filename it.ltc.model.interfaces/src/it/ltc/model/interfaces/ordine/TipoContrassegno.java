package it.ltc.model.interfaces.ordine;

/**
 * Codifica le varie tipologie di contrassegno.
 * @author Damiano
 *
 */
public enum TipoContrassegno {

	BB("Assegno Bancario intestato al Corriere con manleva"),
	BM("Assegno Bancario intestato al Mittente"),
	CB("Assegno Circolare intestato al Corriere con manleva"),
	CM("Assegno Circolare intestato al Mittente"),
	OM("Assegno intestato al Mittente originale"),
	TM("Assegno Bancario intestato al Mittente Scadenziato"),
	TO("Assegno CC Scadenziato"),
	SC("Solo contanti"),
	NA("Non specificato");
	
	private final String descrizione;
	
	private TipoContrassegno(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return descrizione;
	}
	
}

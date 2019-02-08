package it.ltc.model.interfaces.ordine;

/**
 * Qui sono presenti tutte le codifiche dei corrieri
 * @author Damiano
 *
 */
public enum Corriere {
	
	BRT(1, "Bartolini"),
	TNT(2, "TNT"),
	GLS(3, "GLS"),
	UPS(4, "UPS"),
	DHL(5, "DHL"),
	RUT(6, "Rutilli"),
	FED(7, "FedEx"),
	LTC(11, "LTC"), 
	ALTRO(10, "Altro");
	
	private final int id;
	private final String nome;
	
	private Corriere(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
		return nome;
	}

	public static Corriere getCorrierebyID(int id) {
		Corriere corriere = null;
		for (Corriere c : Corriere.values()) {
			if (c.getId() == id) {
				corriere = c;
				break;
			}
		}
		return corriere;
	}

}

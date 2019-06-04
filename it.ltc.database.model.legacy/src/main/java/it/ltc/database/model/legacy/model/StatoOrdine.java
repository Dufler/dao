package it.ltc.database.model.legacy.model;

/**
 * Rappresenta i possibili stati di un ordine nel sistema legacy.
 * @author Damiano
 *
 */
public enum StatoOrdine {
	
	INSE("INSERITO", "Appena inserito, va ancora assegnato."),
	ERRO("ERRORE", "E' stata tentata l'assegnazione ma è fallita."),
	IMPO("PRONTO", "Ordine correttamente importato e pronto per l'assegnazione."),
	ASSE("ASSEGNATO", "Ordine assegnato e pronto per la lavorazione."),
	INCQ("CONTROLLO QUALITA'", "Controllo Qualità"),
	COCQ("FINE CONTROLLO QUALITA'", "Fine Controllo Qualità"),
	INPR("PRELIEVO", "Prelievo"),
	COPR("FINE PRELIEVO", "Fine Prelievo"),
	INIB("PICKING", "In fase di imballo."),
	COIB("IMBALLATO", "Imballo completato."),
	DIIB("IMBALLATO_CON_DIFFERENZE", "Differenze di imballo."),
	ELAB("PRONTO_PER_SPEDIZIONE", "Pronto per essere spedito."),
	INSP("IN_SPEDIZIONE", "In spedizione."),
	SPED("SPEDITO", "Spedito!"),
	FINE("CHIUSO", "Concluso!"),
	ANNU("ANNULLATO", "Annullato!"),
	ANNT("ANNULLATO", "Annullato!"), //Come sopra ma è stato notificato.
	NONE("NON_CODIFICATO", "Stato anomalo, non codificato");

	private final String nome;
	private final String descrizione;
	
	private StatoOrdine(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
}

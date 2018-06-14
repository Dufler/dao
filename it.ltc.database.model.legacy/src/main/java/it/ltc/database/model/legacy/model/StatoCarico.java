package it.ltc.database.model.legacy.model;

public enum StatoCarico {
	
	INSERITO("INSERITO", "Inserito"),
	ARRIVATO("ARRIVATO", "Arrivato"),
	IN_LAVORAZIONE("IN_LAVORAZIONE", "In lavorazione"),
	LAVORATO("LAVORATO", "Lavorato"),
	CHIUSO("CHIUSO", "Chiuso");
	
	private final String nome;
	private final String descrizione;
	
	private StatoCarico(String nome, String descrizione) {
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

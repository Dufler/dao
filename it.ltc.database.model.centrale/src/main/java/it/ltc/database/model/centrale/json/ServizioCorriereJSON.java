package it.ltc.database.model.centrale.json;

public class ServizioCorriereJSON {
	
	private String codice;
	private String nome;
	private int idCorriere;
	private String codificaCorriere;
	
	public ServizioCorriereJSON() {}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdCorriere() {
		return idCorriere;
	}

	public void setIdCorriere(int idCorriere) {
		this.idCorriere = idCorriere;
	}

	public String getCodificaCorriere() {
		return codificaCorriere;
	}

	public void setCodificaCorriere(String codificaCorriere) {
		this.codificaCorriere = codificaCorriere;
	}

}

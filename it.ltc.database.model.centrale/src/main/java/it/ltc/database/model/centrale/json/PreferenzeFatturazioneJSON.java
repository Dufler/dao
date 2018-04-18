package it.ltc.database.model.centrale.json;

public class PreferenzeFatturazioneJSON {
	
	private int commessa;
	private int ambito;
	private String descrizioneFattura;
	private String modalitaPagamento;
	private int coordinatePagamento;
	private String layout;
	
	public PreferenzeFatturazioneJSON() {}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public int getAmbito() {
		return ambito;
	}

	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}

	public String getDescrizioneFattura() {
		return descrizioneFattura;
	}

	public void setDescrizioneFattura(String descrizioneFattura) {
		this.descrizioneFattura = descrizioneFattura;
	}

	public String getModalitaPagamento() {
		return modalitaPagamento;
	}

	public void setModalitaPagamento(String modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public int getCoordinatePagamento() {
		return coordinatePagamento;
	}

	public void setCoordinatePagamento(int coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

}

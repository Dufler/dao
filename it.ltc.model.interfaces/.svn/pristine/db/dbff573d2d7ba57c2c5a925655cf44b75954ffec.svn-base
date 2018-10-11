package it.ltc.model.interfaces.ordine;

import it.ltc.model.interfaces.exception.ModelValidationException;

public class ProdottoOrdinatoPerModelloTaglia extends ProdottoOrdinato {

	private static final long serialVersionUID = 1L;
	
	private String codicemodello;
	private String taglia;
	
	public ProdottoOrdinatoPerModelloTaglia() {}
	
	@Override
	public void valida() throws ModelValidationException {
		super.valida();
		if (codicemodello == null || codicemodello.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare il codice del modello.");
		}
		if (taglia == null || taglia.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare la taglia.");
		}
	}

	public String getCodicemodello() {
		return codicemodello;
	}

	public void setCodicemodello(String codicemodello) {
		this.codicemodello = codicemodello;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

}

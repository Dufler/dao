package it.ltc.model.interfaces.ordine;

import it.ltc.model.interfaces.exception.ModelValidationException;

public class ProdottoOrdinatoPerChiave extends ProdottoOrdinato {

	private static final long serialVersionUID = 1L;
	
	private String chiave;
	
	public ProdottoOrdinatoPerChiave() {}
	
	@Override
	public void valida() throws ModelValidationException {
		super.valida();
		if (chiave == null || chiave.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare la chiave del prodotto.");
		}
	}

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

}

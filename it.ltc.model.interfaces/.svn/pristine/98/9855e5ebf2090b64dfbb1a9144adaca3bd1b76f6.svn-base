package it.ltc.model.interfaces.ordine;

import it.ltc.model.interfaces.exception.ModelValidationException;

public class ProdottoOrdinatoPerBarcode extends ProdottoOrdinato {

	private static final long serialVersionUID = 1L;
	
	private String barcode;
	
	public ProdottoOrdinatoPerBarcode() {}

	@Override
	public void valida() throws ModelValidationException {
		super.valida();
		if (barcode == null || barcode.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare un barcode.");
		}
	}
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}

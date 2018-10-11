package it.ltc.model.interfaces.ordine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MAssicurazione implements ModelInterface {

	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private double valore;
	
	public MAssicurazione() {}

	@Override
	public void valida() throws ModelValidationException {
		if (tipo == null || tipo.isEmpty())
			throw new ModelValidationException("Bisogna specificare un tipo di assicurazione.");
		if (valore <= 0)
			throw new ModelValidationException("Il valore assicurato specificato non è valido.");
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValore() {
		return valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}

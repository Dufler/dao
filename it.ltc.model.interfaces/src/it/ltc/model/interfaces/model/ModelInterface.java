package it.ltc.model.interfaces.model;

import java.io.Serializable;

import it.ltc.model.interfaces.exception.ModelValidationException;

public interface ModelInterface extends Serializable {
	
	public void valida() throws ModelValidationException;

}

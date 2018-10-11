package it.ltc.model.interfaces.exception;

public class ModelValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ModelValidationException(String errorMessage) {
		super(errorMessage);
	}

}
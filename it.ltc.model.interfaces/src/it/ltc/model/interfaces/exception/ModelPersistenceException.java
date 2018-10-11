package it.ltc.model.interfaces.exception;

public class ModelPersistenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ModelPersistenceException(String errorMessage) {
		super(errorMessage);
	}

}
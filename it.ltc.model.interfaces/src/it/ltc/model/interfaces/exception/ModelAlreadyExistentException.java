package it.ltc.model.interfaces.exception;

import java.util.List;

/**
 * Eccezione sollevata quando l'anagrafica del prodotto è già presente a sistema.
 * @author Damiano
 *
 */
public class ModelAlreadyExistentException extends ModelValidationException {

	private static final long serialVersionUID = 1L;

	public ModelAlreadyExistentException(String errorMessage) {
		this(errorMessage, null);
	}
	
	public ModelAlreadyExistentException(String errorMessage, List<CustomErrorCause> problems) {
		super(errorMessage, problems);
	}

}

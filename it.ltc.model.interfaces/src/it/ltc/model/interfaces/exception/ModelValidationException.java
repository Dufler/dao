package it.ltc.model.interfaces.exception;

import java.util.List;

/**
 * Eccezione sollevata durante la validazione del model.
 * @author Damiano
 *
 */
public class ModelValidationException extends ModelException {

	private static final long serialVersionUID = 1L;
	
	public ModelValidationException(String errorMessage) {
		this(errorMessage, null);
	}
	
	public ModelValidationException(String errorMessage, List<CustomErrorCause> problems) {
		super(errorMessage, problems);
	}

}
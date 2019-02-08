package it.ltc.model.interfaces.exception;

import java.util.List;

/**
 * Eccezione sollevata durante il salvataggio del model.
 * @author Damiano
 *
 */
public class ModelPersistenceException extends ModelException {

	private static final long serialVersionUID = 1L;
	
	public ModelPersistenceException(String errorMessage) {
		this(errorMessage, null);
	}
	
	public ModelPersistenceException(String errorMessage, List<CustomErrorCause> problems) {
		super(errorMessage, problems);
	}

}
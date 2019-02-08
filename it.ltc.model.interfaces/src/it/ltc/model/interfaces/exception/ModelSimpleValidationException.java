package it.ltc.model.interfaces.exception;

import java.util.List;

/**
 * Questo tipo di eccezione viene generato dentro i model semplici mentre vengono eseguiti i controlli più banali.
 * @author Damiano
 *
 */
public class ModelSimpleValidationException extends ModelValidationException {
	
	private static final long serialVersionUID = 1L;

	public ModelSimpleValidationException(String errorMessage) {
		this(errorMessage, null);
	}

	public ModelSimpleValidationException(String errorMessage, List<CustomErrorCause> problems) {
		super(errorMessage, problems);
	}

}

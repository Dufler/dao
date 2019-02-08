package it.ltc.model.interfaces.exception;

import java.util.List;

/**
 * Classe generica e astratta da non istanziare direttamente.<br>
 * Esistono diverse implementazioni che raffinano la tipologia di eccezione riscontrata durante la validazione e salvataggio del model. 
 * @author Damiano
 *
 */
public abstract class ModelException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final List<CustomErrorCause> problems;
	
	public ModelException(String errorMessage, List<CustomErrorCause> problems) {
		super(errorMessage);
		this.problems = problems;
	}
	
	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder(super.getMessage());
		if (problems != null) for (CustomErrorCause problem : problems) {
			message.append(problem.toString());
			message.append("\r\n");
		}
		return message.toString();
	}

}

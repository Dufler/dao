package it.ltc.model.interfaces.exception;

/**
 * Questa classe rappresenta una causa di errore.<br>
 * Il metodo toString è stato esteso per rappresentare un JSON.
 * @author Damiano
 *
 */
public class CustomErrorCause {
	
	private final String entity;
	private final String cause;
	
	public CustomErrorCause(String entity, String cause) {
		this.entity = entity;
		this.cause = cause;
	}

	public String getEntity() {
		return entity;
	}

	public String getCause() {
		return cause;
	}
	
	@Override
	public String toString() {
		return "entità : " + entity + ", problema: " + cause;
	}

}

package it.ltc.model.interfaces.exception;

/**
 * Eccezione sollevata quando l'anagrafica del prodotto è già presente a sistema.
 * @author Damiano
 *
 */
public class ProductAlreadyExistentException extends ModelValidationException {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistentException(String errorMessage) {
		super(errorMessage);
	}

}

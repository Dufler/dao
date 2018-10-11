package it.ltc.model.interfaces.model;

import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;

public interface IControllerModel<T extends ModelInterface> {
	
	public void valida (T model) throws ModelValidationException;
	
	public T inserisci (T model) throws ModelPersistenceException;
	
	public T modifica (T model) throws ModelPersistenceException;
	
	public T elimina (T model) throws ModelPersistenceException;

}

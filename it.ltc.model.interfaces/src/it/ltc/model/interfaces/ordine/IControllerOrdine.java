package it.ltc.model.interfaces.ordine;

import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;

public interface IControllerOrdine {

	public void valida (MOrdine ordine) throws ModelValidationException;
	
	public int inserisci (MOrdine ordine) throws ModelPersistenceException;
	
	public boolean modifica (MOrdine ordine) throws ModelPersistenceException;
	
	public boolean elimina (MOrdine ordine) throws ModelPersistenceException;
	
}

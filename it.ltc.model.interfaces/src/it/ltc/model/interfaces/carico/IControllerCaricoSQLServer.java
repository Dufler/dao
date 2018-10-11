package it.ltc.model.interfaces.carico;

import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;

public class IControllerCaricoSQLServer implements IControllerModel<MCarico> {

	@Override
	public void valida(MCarico model) throws ModelValidationException {
		// TODO Auto-generated method stub	
	}

	@Override
	public MCarico inserisci(MCarico model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MCarico modifica(MCarico model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MCarico elimina(MCarico model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}

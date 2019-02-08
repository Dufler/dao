package it.ltc.model.persistence.fornitore;

import it.ltc.database.dao.legacy.FornitoreDao;
import it.ltc.database.model.legacy.Fornitori;
import it.ltc.model.interfaces.exception.ModelAlreadyExistentException;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.fornitore.MFornitore;
import it.ltc.model.interfaces.indirizzo.MIndirizzo;
import it.ltc.model.interfaces.model.IControllerModel;

public class ControllerFornitoreSQLServer extends FornitoreDao implements IControllerModel<MFornitore> {

	public ControllerFornitoreSQLServer(String persistenceUnit) {
		super(persistenceUnit);
	}

	@Override
	public void valida(MFornitore model) throws ModelValidationException {
		model.valida();
		//Controllo che non sia già stato inserito un fornitore con lo stesso codice
		Fornitori esistente = trovaDaCodice(model.getCodice());
		if (esistente != null)
			throw new ModelAlreadyExistentException("E' già stato inserito un fornitore con lo stesso codice. (" + model.getCodice() + ")");
	}
	
	protected Fornitori serializza(MFornitore model) {
		Fornitori fornitore = new Fornitori();
		//Info fornitore
		fornitore.setIdFornitore(model.getId());
		fornitore.setCodiceFornitore(model.getCodice());
		fornitore.setRagSoc(model.getRagioneSociale());
		fornitore.setNote(model.getNote());
		fornitore.setTipodocumento(model.getTipo() != null ? model.getTipo().name() : "CARICO");
		//Info indirizzo
		MIndirizzo indirizzo = model.getIndirizzo();
		fornitore.setCap(indirizzo.getCap());
		fornitore.setCitta(indirizzo.getLocalita());
		fornitore.setCodnaz(indirizzo.getNazione());
		fornitore.setEMail(indirizzo.getEmail());
		fornitore.setFax(indirizzo.getTelefono());
		fornitore.setIndirizzo(indirizzo.getIndirizzo());
		fornitore.setNaz(indirizzo.getNazione());
		fornitore.setProv(indirizzo.getProvincia());
		fornitore.setTel(indirizzo.getTelefono());
		return fornitore;
	}
	
	protected MFornitore deserializza(Fornitori fornitore) {
		MFornitore model = new MFornitore();
		
		return model;
	}

	@Override
	public MFornitore inserisci(MFornitore model) throws ModelPersistenceException {
		Fornitori fornitore = serializza(model);
		fornitore = inserisci(fornitore);
		model = fornitore != null ? deserializza(fornitore) : null;
		if (model == null) throw new ModelPersistenceException("Impossibile inserire il fornitore.");
		return model;
	}

	@Override
	public MFornitore modifica(MFornitore model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MFornitore elimina(MFornitore model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}

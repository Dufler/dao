package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.Imballi;

public class ImballoDao extends ReadOnlyDao<Imballi> {

	public ImballoDao(String persistenceUnit) {
		super(persistenceUnit, Imballi.class);
	}
	
	public Imballi trovaDaID(int id) {
		Imballi entity = findByID(id);
		return entity;
	}

	public Imballi trovaDaCodice(String codice) {
		Imballi imballo = findOnlyOneEqualTo("codImballo", codice);
        return imballo;
	}

	public List<Imballi> trovaTutti() {
		List<Imballi> entities = findAll();
		return entities;
	}

}

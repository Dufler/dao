package it.ltc.database.dao.legacy;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.Imballi;

public class ImballoDao extends ReadOnlyDao<Imballi> {
	
	protected final HashMap<Integer, Imballi> mappaPerID;
	protected final HashMap<String, Imballi> mappaPerCodice;

	public ImballoDao(String persistenceUnit) {
		super(persistenceUnit, Imballi.class);
		
		mappaPerID = new HashMap<>();
		mappaPerCodice = new HashMap<>();
	}
	
	protected Imballi aggiungiMappe(Imballi imballo) {
		if (imballo != null) {
			mappaPerID.put(imballo.getIdImballo(), imballo);
			mappaPerCodice.put(imballo.getCodImballo(), imballo);
		}
		return imballo;
	}
	
	public Imballi trovaDaID(int id) {
		Imballi entity = mappaPerID.containsKey(id) ? mappaPerID.get(id) : aggiungiMappe(findByID(id));
		return entity;
	}

	public Imballi trovaDaCodice(String codice) {
		Imballi imballo = mappaPerCodice.containsKey(codice) ? mappaPerCodice.get(codice) : aggiungiMappe(findOnlyOneEqualTo("codImballo", codice));
        return imballo;
	}

	public List<Imballi> trovaTutti() {
		List<Imballi> entities = findAll();
		return entities;
	}

}

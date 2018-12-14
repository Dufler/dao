package it.ltc.database.dao.common;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.Corriere;

public class CorriereDao extends ReadOnlyDao<Corriere> {
	
	private final HashMap<Integer, Corriere> mappaPerID;
	private final HashMap<String, Corriere> mappaPerCodice;

	public CorriereDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CorriereDao(String persistenceUnit) {
		super(persistenceUnit, Corriere.class);
		this.mappaPerID = new HashMap<>();
		this.mappaPerCodice = new HashMap<>();
	}
	
	private Corriere aggiungiMappe(Corriere corriere) {
		if (corriere != null) {
			mappaPerID.put(corriere.getId(), corriere);
			mappaPerCodice.put(corriere.getCodifica(), corriere);
		}
		return corriere;
	}
	
	public Corriere trovaDaID(int id) {
		Corriere corriere = mappaPerID.containsKey(id) ? mappaPerID.get(id) : aggiungiMappe(findByID(id));
		return corriere;
	}
	
	public Corriere trovaDaCodice(String codice) {
		Corriere corriere = mappaPerCodice.containsKey(codice) ? mappaPerCodice.get(codice) : aggiungiMappe(findOnlyOneEqualTo("codifica", codice));
		return corriere;
	}
	
	public List<Corriere> trovaTutti() {
		List<Corriere> entities = findAll();
		return entities;
	}

}

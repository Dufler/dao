package it.ltc.database.dao.legacy;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.Marchi;

public class MarchiDao extends ReadOnlyDao<Marchi> {
	
	protected final HashMap<Integer, Marchi> mappaPerCodice;

	public MarchiDao(String persistenceUnit) {
		super(persistenceUnit, Marchi.class);
		mappaPerCodice = new HashMap<>();
	}
	
	private Marchi inserisciInMappe(Marchi entity) {
		if (entity != null) {
			mappaPerCodice.put(entity.getCodice(), entity);
		}
		return entity;
	}
	
	public Marchi trovaDaID(int id) {
		Marchi entity = findByID(id);
		return entity;
	}
	
	public Marchi trovaDaCodice(int codice) {
		Marchi entity = mappaPerCodice.containsKey(codice) ? mappaPerCodice.get(codice) : inserisciInMappe(findOnlyOneEqualTo("codice", codice));
		return entity;
	}
	
	public Marchi trovaDaDescrizione(String descrizione) {
		Marchi entity = findOnlyOneEqualTo("descrizione", descrizione);
		return entity;
	}
	
	public List<Marchi> trovaTutti() {
		List<Marchi> entities = findAll();
		return entities;
	}

}

package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.PakiTestaLogStato;

public class PakiTestaLogStatoDao extends ReadOnlyDao<PakiTestaLogStato> {

	public PakiTestaLogStatoDao(String persistenceUnit) {
		super(persistenceUnit, PakiTestaLogStato.class);
	}
	
	public List<PakiTestaLogStato> trovaStati(int idCarico) {
		List<PakiTestaLogStato> entities = findAllEqualTo("idTestaPaki", idCarico);
		return entities;
	}

}

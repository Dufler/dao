package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.CatMercGruppi;

public class CategoriaMerceologicaLegacyDao extends ReadOnlyDao<CatMercGruppi> {

	public CategoriaMerceologicaLegacyDao(String persistenceUnit) {
		super(persistenceUnit, CatMercGruppi.class);
	}
	
	public CatMercGruppi trovaDaCodice(String codice) {
		CatMercGruppi entity = findFirstOneEqualTo("categoria", codice);
        return entity;
	}
	
	public CatMercGruppi trovaDaID(int id) {
		CatMercGruppi entity = findByID(id);
		return entity;
	}
	
	public List<CatMercGruppi> trovaTutte() {
		List<CatMercGruppi> entities = findAll();
		return entities;
	}

}

package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.CategoriaMerceologica;

public class CategoriaMerceologicaDao extends ReadOnlyDao<CategoriaMerceologica> {

	public CategoriaMerceologicaDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CategoriaMerceologicaDao(String persistenceUnit) {
		super(persistenceUnit, CategoriaMerceologica.class);
	}
	
	public CategoriaMerceologica trovaDaNome(String nome) {
		CategoriaMerceologica feature = findByID(nome);
		return feature;
	}
	
	public List<CategoriaMerceologica> trovaTutte() {
		List<CategoriaMerceologica> entities = findAll();
		return entities;
	}

}

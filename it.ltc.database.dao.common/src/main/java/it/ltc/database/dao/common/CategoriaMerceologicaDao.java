package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.CategoriaMerceologica;
import it.ltc.database.model.centrale.CategoriaMerceologicaPK;

public class CategoriaMerceologicaDao extends CRUDDao<CategoriaMerceologica> {

	public CategoriaMerceologicaDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CategoriaMerceologicaDao(String persistenceUnit) {
		super(persistenceUnit, CategoriaMerceologica.class);
	}
	
	public CategoriaMerceologica trovaDaNome(String nome, int commessa) {
		CategoriaMerceologicaPK pk = new CategoriaMerceologicaPK();
		pk.setCommessa(commessa);
		pk.setNome(nome);
		CategoriaMerceologica feature = findByID(pk);
		return feature;
	}
	
	public List<CategoriaMerceologica> trovaTutte() {
		List<CategoriaMerceologica> entities = findAll();
		return entities;
	}
	
	public CategoriaMerceologica inserisci(CategoriaMerceologica categoria) {
		CategoriaMerceologica entity = insert(categoria);
		return entity;
	}
	
	public CategoriaMerceologica aggiorna(CategoriaMerceologica categoria) {
		CategoriaMerceologica entity = update(categoria, categoria.getPK());
		return entity;
	}
	
	public CategoriaMerceologica elimina(CategoriaMerceologica categoria) {
		CategoriaMerceologica entity = delete(categoria.getPK());
		return entity;
	}

	@Override
	protected void updateValues(CategoriaMerceologica oldEntity, CategoriaMerceologica entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setBrand(entity.getBrand());
		oldEntity.setStato(entity.getStato());
	}

}

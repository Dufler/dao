package it.ltc.database.dao.legacy;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.CatMercGruppi;

public class CategoriaMerceologicaLegacyDao extends CRUDDao<CatMercGruppi> {
	
	protected final HashMap<String, CatMercGruppi> mappaPerCodice;
	protected final HashMap<Integer, CatMercGruppi> mappaPerID;

	public CategoriaMerceologicaLegacyDao(String persistenceUnit) {
		super(persistenceUnit, CatMercGruppi.class);
		mappaPerCodice = new HashMap<>();
		mappaPerID = new HashMap<>();
	}
	
	@Override
	protected void updateValues(CatMercGruppi oldEntity, CatMercGruppi entity) {
		oldEntity.setCategoria(entity.getCategoria());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setStato(entity.getStato());
		oldEntity.setMarchio(entity.getMarchio());
	}
	
	protected void inserisciInMappe(CatMercGruppi entity) {
		if (entity != null) {
			mappaPerCodice.put(entity.getCategoria(), entity);
			mappaPerID.put(entity.getIdGruCatMer(), entity);
		}		
	}
	
	public CatMercGruppi trovaDaCodice(String codice) {
		if (!mappaPerCodice.containsKey(codice)) {
			CatMercGruppi entity = findOnlyOneEqualTo("categoria", codice);
			inserisciInMappe(entity);
		}		
        return mappaPerCodice.get(codice);
	}
	
	public CatMercGruppi trovaDaID(int id) {
		if (!mappaPerID.containsKey(id)) {
			CatMercGruppi entity = findByID(id);
			inserisciInMappe(entity);
		}		
		return mappaPerID.get(id);
	}
	
	public List<CatMercGruppi> trovaTutte() {
		List<CatMercGruppi> entities = findAll();
		return entities;
	}
	
	public CatMercGruppi inserisci(CatMercGruppi categoria) {
		CatMercGruppi entity = insert(categoria);
		return entity;
	}
	
	public CatMercGruppi aggiorna(CatMercGruppi categoria) {
		CatMercGruppi entity = update(categoria, categoria.getIdGruCatMer());
		return entity;
	}
	
	public CatMercGruppi elimina(CatMercGruppi categoria) {
		CatMercGruppi entity = delete(categoria.getIdGruCatMer());
		return entity;
	}

}

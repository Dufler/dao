package it.ltc.database.dao.shared.prodotti;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.legacy.CategoriaMerceologicaLegacyDao;
import it.ltc.database.model.legacy.CatMercGruppi;
import it.ltc.model.shared.dao.ICategoriaMerceologicaDao;
import it.ltc.model.shared.json.interno.CategoriaMerceologicaJSON;

public class CategoriaMerceologicaLegacyDaoImpl implements ICategoriaMerceologicaDao {
	
	private CategoriaMerceologicaLegacyDao dao;
	
	public CategoriaMerceologicaLegacyDaoImpl(String persistenceUnit) {
		dao = new CategoriaMerceologicaLegacyDao(persistenceUnit);
	}

	@Override
	public CategoriaMerceologicaJSON trovaDaNome(String nome) {
		CatMercGruppi entity = dao.trovaDaCodice(nome);
		CategoriaMerceologicaJSON json = serializza(entity);
		return json;
	}

	@Override
	public List<CategoriaMerceologicaJSON> trovaTutte() {
		List<CatMercGruppi> entities = dao.trovaTutte();
		List<CategoriaMerceologicaJSON> jsons = new LinkedList<>();
		for (CatMercGruppi entity : entities) {
			jsons.add(serializza(entity));
		}
		return jsons;
	}
	
	public CategoriaMerceologicaJSON serializza(CatMercGruppi entity) {
		CategoriaMerceologicaJSON json;
		if (entity != null) {
			json = new CategoriaMerceologicaJSON();
			json.setNome(entity.getCategoria());
			json.setDescrizione(entity.getDescrizione());
		} else {
			json = null;
		}
		return json;
	}

}

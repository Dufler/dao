package it.ltc.database.dao.shared.prodotti;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.common.CategoriaMerceologicaDao;
import it.ltc.database.model.centrale.CategoriaMerceologica;
import it.ltc.model.shared.dao.ICategoriaMerceologicaDao;
import it.ltc.model.shared.json.interno.CategoriaMerceologicaJSON;

public class CategoriaMerceologicaDaoImpl implements ICategoriaMerceologicaDao {
	
	private final CategoriaMerceologicaDao dao;
	
	public CategoriaMerceologicaDaoImpl() {
		dao = new CategoriaMerceologicaDao();
	}

	@Override
	public CategoriaMerceologicaJSON trovaDaNome(String nome) {
		CategoriaMerceologica entity = dao.trovaDaNome(nome);
		CategoriaMerceologicaJSON json = serializza(entity);
		return json;
	}

	@Override
	public List<CategoriaMerceologicaJSON> trovaTutte() {
		List<CategoriaMerceologica> entities = dao.trovaTutte();
		List<CategoriaMerceologicaJSON> jsons = new LinkedList<>();
		for (CategoriaMerceologica entity : entities) {
			jsons.add(serializza(entity));
		}
		return jsons;
	}
	
	protected CategoriaMerceologicaJSON serializza(CategoriaMerceologica entity) {
		CategoriaMerceologicaJSON json;
		if (entity != null) {
			json = new CategoriaMerceologicaJSON();
			json.setNome(entity.getNome());
			json.setDescrizione(entity.getDescrizione());
		} else {
			json = null;
		}
		return json;
	}

}

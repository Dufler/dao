package it.ltc.database.dao.shared.prodotti;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.common.CategoriaMerceologicaDao;
import it.ltc.database.dao.common.CommessaDao;
import it.ltc.database.dao.legacy.CategoriaMerceologicaLegacyDao;
import it.ltc.database.model.centrale.CategoriaMerceologica;
import it.ltc.database.model.centrale.CategoriaMerceologica.StatoCategoria;
import it.ltc.database.model.centrale.Commessa;
import it.ltc.database.model.legacy.CatMercGruppi;
import it.ltc.model.shared.dao.ICategoriaMerceologicaDao;
import it.ltc.model.shared.json.interno.CategoriaMerceologicaJSON;
import it.ltc.services.custom.exception.CustomException;

public class CategoriaMerceologicaDaoImpl implements ICategoriaMerceologicaDao {
	
	private final CategoriaMerceologicaDao dao;
	private final CommessaDao daoCommesse;
	
	public CategoriaMerceologicaDaoImpl() {
		dao = new CategoriaMerceologicaDao();
		daoCommesse = new CommessaDao();
	}

	@Override
	public CategoriaMerceologicaJSON trovaDaNome(String nome, int commessa) {
		CategoriaMerceologica entity = dao.trovaDaNome(nome, commessa);
		CategoriaMerceologicaJSON json = serializza(entity);
		return json;
	}

	@Override
	public List<CategoriaMerceologicaJSON> trovaTutti() {
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
			json.setCommessa(entity.getCommessa());
			json.setBrand(entity.getBrand());
			json.setStato(entity.getStato().name());
		} else {
			json = null;
		}
		return json;
	}
	
	protected CategoriaMerceologica serializza(CategoriaMerceologicaJSON json) {
		CategoriaMerceologica entity;
		if (json != null) {
			entity = new CategoriaMerceologica();
			entity.setNome(json.getNome());
			entity.setDescrizione(json.getDescrizione());
			entity.setCommessa(json.getCommessa());
			entity.setBrand(json.getBrand());
			entity.setStato(StatoCategoria.valueOf(json.getStato()));
		} else {
			entity = null;
		}
		return entity;
	}
	
	protected CategoriaMerceologicaJSON serializza(CatMercGruppi entity) {
		CategoriaMerceologicaJSON json;
		if (entity != null) {
			json = new CategoriaMerceologicaJSON();
			json.setNome(entity.getCategoria());
			json.setDescrizione(entity.getDescrizione());
			json.setBrand(entity.getMarchio());
			json.setStato(entity.getStato());
		} else {
			json = null;
		}
		return json;
	}
	
	protected CatMercGruppi deserializza(CategoriaMerceologicaJSON json, CatMercGruppi entity) {
		if (json != null) {
			//entity = new CatMercGruppi();
			entity.setCategoria(json.getNome());
			entity.setDescrizione(json.getDescrizione());
			entity.setMarchio(json.getBrand());
			entity.setStato(json.getStato());
		} else {
			entity = null;
		}
		return entity;
	}
	
	protected CategoriaMerceologicaLegacyDao istanziaDaoLegacy(int idCommessa) {
		Commessa commessa = daoCommesse.trovaDaID(idCommessa);
		if (commessa == null)
			throw new CustomException("Nessuna commessa corrispondente trovata (ID: " + idCommessa + ")");
		CategoriaMerceologicaLegacyDao dao = new CategoriaMerceologicaLegacyDao(commessa.getNomeRisorsa());
		return dao;
	}

	@Override
	public CategoriaMerceologicaJSON inserisci(CategoriaMerceologicaJSON categoria) {
		//Deserializzo
		CategoriaMerceologica entity = serializza(categoria);
		CatMercGruppi legacy = deserializza(categoria, new CatMercGruppi());
		//Creo il giusto dao in base alla commessa.
		CategoriaMerceologicaLegacyDao daoLegacy = istanziaDaoLegacy(categoria.getCommessa());
		//procedo all'inserimento di entrambe
		entity = dao.inserisci(entity);
		legacy = daoLegacy.inserisci(legacy);
		CategoriaMerceologicaJSON risposta = null;
		if (entity != null && legacy != null) {
			risposta = serializza(legacy);
			risposta.setCommessa(categoria.getCommessa());
		}
		return risposta;
	}

	@Override
	public CategoriaMerceologicaJSON aggiorna(CategoriaMerceologicaJSON categoria) {
		//Deserializzo
		CategoriaMerceologica entity = serializza(categoria);
		//Creo il giusto dao in base alla commessa.
		CategoriaMerceologicaLegacyDao daoLegacy = istanziaDaoLegacy(categoria.getCommessa());
		CatMercGruppi legacy = deserializza(categoria, daoLegacy.trovaDaCodice(categoria.getNome()));
		//procedo all'inserimento di entrambe
		entity = dao.aggiorna(entity);
		legacy = daoLegacy.aggiorna(legacy);
		CategoriaMerceologicaJSON risposta = null;
		if (entity != null && legacy != null) {
			risposta = serializza(legacy);
			risposta.setCommessa(categoria.getCommessa());
		}
		return risposta;
	}

	@Override
	public CategoriaMerceologicaJSON elimina(CategoriaMerceologicaJSON categoria) {
		//Deserializzo
		CategoriaMerceologica entity = serializza(categoria);
		//Creo il giusto dao in base alla commessa.
		CategoriaMerceologicaLegacyDao daoLegacy = istanziaDaoLegacy(categoria.getCommessa());
		CatMercGruppi legacy = deserializza(categoria, daoLegacy.trovaDaCodice(categoria.getNome()));
		//procedo all'inserimento di entrambe
		entity = dao.elimina(entity);
		legacy = daoLegacy.elimina(legacy);
		CategoriaMerceologicaJSON risposta = null;
		if (entity != null && legacy != null) {
			risposta = serializza(legacy);
			risposta.setCommessa(categoria.getCommessa());
		}
		return risposta;
	}

}

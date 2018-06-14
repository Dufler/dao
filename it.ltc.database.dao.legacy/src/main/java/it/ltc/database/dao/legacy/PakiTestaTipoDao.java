package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.PakiTestaTipo;
import it.ltc.model.shared.dao.ITipoCaricoDao;
import it.ltc.model.shared.json.cliente.TipoCaricoJSON;

public class PakiTestaTipoDao extends ReadOnlyDao<PakiTestaTipo> implements ITipoCaricoDao {

	public PakiTestaTipoDao(String persistenceUnit) {
		super(persistenceUnit, PakiTestaTipo.class);
	}
	
	public TipoCaricoJSON trovaDaCodice(String codice) {
		PakiTestaTipo entity = findByID(codice);
		TipoCaricoJSON json = serializza(entity);
		return json;
	}
	
	public List<TipoCaricoJSON> trovaTutti() {
		List<PakiTestaTipo> entities = findAll();
		List<TipoCaricoJSON> jsons = new LinkedList<>();
		if (entities != null)
		for (PakiTestaTipo entity : entities)
			jsons.add(serializza(entity));
		return jsons;
	}
	
	protected TipoCaricoJSON serializza(PakiTestaTipo tipo) {
		TipoCaricoJSON json;
		if (tipo != null) {
			json = new TipoCaricoJSON();
			json.setCodice(tipo.getCodice());
			json.setDescrizione(tipo.getDescrizione());
		} else {
			json = null;
		}
		return json;
	}

}

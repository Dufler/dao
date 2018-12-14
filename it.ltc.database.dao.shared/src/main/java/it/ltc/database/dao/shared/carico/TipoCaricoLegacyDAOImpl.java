package it.ltc.database.dao.shared.carico;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.legacy.PakiTestaTipoDao;
import it.ltc.database.model.legacy.PakiTestaTipo;
import it.ltc.model.shared.dao.ITipoCaricoDao;
import it.ltc.model.shared.json.cliente.TipoCaricoJSON;

public class TipoCaricoLegacyDAOImpl implements ITipoCaricoDao {
	
	private final PakiTestaTipoDao daoTipo;

	public TipoCaricoLegacyDAOImpl(String persistenceUnit) {
		daoTipo = new PakiTestaTipoDao(persistenceUnit);
	}

	public TipoCaricoJSON trovaDaCodice(String codice) {
		PakiTestaTipo entity = daoTipo.trovaDaCodice(codice);
		TipoCaricoJSON json = serializza(entity);
		return json;
	}
	
	public List<TipoCaricoJSON> trovaTutti() {
		List<PakiTestaTipo> entities = daoTipo.trovaTutti();
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
			String[] particolarita = tipo.getParticolarita() != null && !tipo.getParticolarita().isEmpty() ? tipo.getParticolarita().split(",") : null;
			List<Integer> varie = new LinkedList<>();
			for (String p : particolarita) {
				try { varie.add(Integer.parseInt(p)); } catch (Exception e) {}
			}
			json.setParticolarita(varie);
		} else {
			json = null;
		}
		return json;
	}

}

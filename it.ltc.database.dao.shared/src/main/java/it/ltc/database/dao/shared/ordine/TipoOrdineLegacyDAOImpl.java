package it.ltc.database.dao.shared.ordine;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.legacy.TestataOrdiniTipoDao;
import it.ltc.database.model.legacy.TestataOrdiniTipo;
import it.ltc.model.shared.dao.ITipoOrdineDao;
import it.ltc.model.shared.json.cliente.TipoOrdineJSON;

public class TipoOrdineLegacyDAOImpl implements ITipoOrdineDao {
	
	private final TestataOrdiniTipoDao daoTipi;
	
	public TipoOrdineLegacyDAOImpl(String persistenceUnit) {
		daoTipi = new TestataOrdiniTipoDao(persistenceUnit);
	}

	@Override
	public TipoOrdineJSON trovaDaCodice(String codice) {
		TestataOrdiniTipo entity = daoTipi.trovaTipoDaCodice(codice);
		return serializza(entity);
	}

	@Override
	public List<TipoOrdineJSON> trovaTutti() {
		List<TipoOrdineJSON> tipi = new LinkedList<>();
		List<TestataOrdiniTipo> entities = daoTipi.trovaTutti();
		for (TestataOrdiniTipo entity : entities) {
			TipoOrdineJSON tipo = serializza(entity);
			tipi.add(tipo);
		}
		return tipi;
	}
	
	protected TipoOrdineJSON serializza(TestataOrdiniTipo entity) {
		TipoOrdineJSON json = new TipoOrdineJSON();
		json.setCodice(entity.getCodice());
		json.setDescrizione(entity.getDescrizione());
		json.setTipo(entity.getTipo());
		return json;
	}

}

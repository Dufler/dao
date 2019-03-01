package it.ltc.database.dao.costanti;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.costanti.Valuta;

public class ValutaDao extends ReadOnlyDao<Valuta> {

	public ValutaDao() {
		super(LOCAL_COSTANTI_PERSISTENCE_UNIT_NAME, Valuta.class);
	}
	
	public Valuta trovaDaCodice(String codice) {
		Valuta valuta = findByID(codice);
		return valuta;
	}
	
	public List<Valuta> trovaTutte() {
		List<Valuta> entities = findAll();
		return entities;
	}

}

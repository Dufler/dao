package it.ltc.database.dao.costanti;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.costanti.Regione;

public class RegioneDao extends ReadOnlyDao<Regione> {

	public RegioneDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public RegioneDao(String persistenceUnit) {
		super(persistenceUnit, Regione.class);
	}
	
	/**
	 * Restituisce la regione con la sigla specificata utilizzando il datasource di default.
	 * La sigla è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La regione corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Regione trovaDaSigla(String sigla) {
		Regione regione = findByID(sigla);
		return regione;
	}
	
	/**
	 * Restituisce la lista di tutte le regioni esistenti utilizzando il datasource di default.
	 * @return La lista di tutte le regioni.
	 */
	public List<Regione> trovaTutte() {
		List<Regione> entities = findAll();
		return entities;
	}

}

package it.ltc.database.dao.costanti;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.costanti.Provincia;

/**
 * Dao per le province usabile da tutte le applicazioni web.
 * Usa il datasource di default per accedere ai dati.
 * @author Damiano
 *
 */
public class ProvinciaDao extends ReadOnlyDao<Provincia> {

	public ProvinciaDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public ProvinciaDao(String persistenceUnit) {
		super(persistenceUnit, Provincia.class);
	}
	
	/**
	 * Restituisce la provincia con la sigla specificata utilizzando il datasource di default.
	 * La sigla è la <code>Primary Key</code> per la tabella.
	 * @param sigla
	 * @return La provincia corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Provincia trovaDaSigla(String sigla) {
		Provincia provincia = findByID(sigla);
		return provincia;
	}
	
	/**
	 * Restituisce la lista di tutte le province esistenti utilizzando il datasource di default.
	 * @return La lista di tutte le province.
	 */
	public List<Provincia> trovaTutte() {
		List<Provincia> entities = findAll();
		entities.sort(null);
		return entities;
	}

}

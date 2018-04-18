package it.ltc.database.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jboss.logging.Logger;

/**
 * Classe che consente l'accesso ai dati presenti nel db.
 * @author Damiano
 *
 */
public abstract class Dao {
	
	private static final Logger logger = Logger.getLogger("Dao");
	
	public static final String LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME = "locale-centrale";
	public static final String DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME = "datasource-centrale";
	
	public static final String LOCAL_SEDE_PERSISTENCE_UNIT_NAME = "locale-sede";
	public static final String DATASOURCE_SEDE_PERSISTENCE_UNIT_NAME = "datasource-sede";
	
	protected final String persistenceUnit;
	protected EntityManager em;
	
	/**
	 * Costruttore di default.<br>
	 * La persistence unit passata come argomento fornisce indicazioni su quale db andare ad utilizzare.
	 * @param persistenceUnit identifica il nome della Persistence Unit così come definita nel file persistence.xml
	 */
	public Dao(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
		this.em = FactoryManager.getInstance().getFactory(persistenceUnit).createEntityManager();
	}
	
	/**
	 * Restituisce un oggetto <code>EntityManager</code> da utilizzare per eseguire l'accesso ai dati.<br>
	 * E' molto importante chiudere tramite il metodo <code>close</code> l'oggetto una volta concluso l'utilizzo.
	 * @return L'oggetto utilizzabile per l'accesso ai dati.
	 */
	protected EntityManager getManager() {
		if (em == null || !em.isOpen())
			em = FactoryManager.getInstance().getFactory(persistenceUnit).createEntityManager();
		return em;
	}
	
	/**
	 * Esegue le query nativa specificata.
	 * @param nativeQuery la String contenente codice SQL da eseguire.
	 * @return l'esito dell'operazione.
	 */
	protected boolean executeNativeQuery(String nativeQuery) {
		boolean result;
		EntityManager em = getManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			Query query = em.createNativeQuery(nativeQuery);
			transaction.begin();
			query.executeUpdate();
			transaction.commit();
			result = true;
		} catch (Exception e) {
			logger.error(e);
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			result = false;
		} finally {
			em.close();
		}
		return result;
	}

}

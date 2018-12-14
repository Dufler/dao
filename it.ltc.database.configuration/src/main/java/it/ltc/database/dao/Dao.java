package it.ltc.database.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * Classe che consente l'accesso ai dati presenti nel db.
 * @author Damiano
 *
 */
public abstract class Dao {
	
	private static final Logger logger = Logger.getLogger("Dao");
	
	public static final String LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME = "locale-centrale";
	public static final String LOCAL_UTENTE_PERSISTENCE_UNIT_NAME = "locale-utente";
	public static final String LOCAL_SEDE_PERSISTENCE_UNIT_NAME = "locale-sede";
	
	/**
	 * Stringa che rappresenta la persistence unit da usare così come viene definita nel file persistence.xml
	 */
	protected final String persistenceUnit;
	
	/**
	 * L'utente che compie le operazioni con il dao, in alcuni contesti potrebbe essere del tutto superfluo.<br>
	 * Un caso d'uso pratico è l'impostare il nome utente di chi ha creato un collo chiamando il web service.
	 */
	protected String utente;
	
	/**
	 * Costruttore di default.<br>
	 * La persistence unit passata come argomento fornisce indicazioni su quale db andare ad utilizzare.
	 * @param persistenceUnit identifica il nome della Persistence Unit così come definita nel file persistence.xml
	 */
	public Dao(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}
	
	/**
	 * Imposta il nome utente di chi effettua le operazioni sul dao.
	 * @param utente
	 */
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	/**
	 * Indica se il mappaggio dello schema è valido o meno.
	 * @return
	 */
	public boolean isSchemaValid() {
		EntityManager em = getManager();
		em.close();
		return true;
	}
	
	/**
	 * Restituisce un oggetto <code>EntityManager</code> da utilizzare per eseguire l'accesso ai dati.<br>
	 * E' molto importante chiudere tramite il metodo <code>close</code> l'oggetto una volta concluso l'utilizzo.
	 * @return L'oggetto utilizzabile per l'accesso ai dati.
	 */
	protected EntityManager getManager() {
		EntityManager em = FactoryManager.getInstance().getFactory(persistenceUnit).createEntityManager();
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
			for (StackTraceElement element : e.getStackTrace())
				logger.error(element);
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			result = false;
		} finally {
			em.close();
		}
		return result;
	}

}

package it.ltc.database.dao;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * Classe Factory che produce e memorizza <code>EntityManagerFactory</code> centralizzandoli.
 * Tramite questi oggetti è possibile creare <code>EntityManager</code> per accedere al db.
 * @author Damiano
 *
 */
public class FactoryManager {
	
	private static final Logger logger = Logger.getLogger("FactoryManager");
	
	private static FactoryManager instance;
	
	private final HashMap<String, EntityManagerFactory> factories;

	private FactoryManager() {
		factories = new HashMap<String, EntityManagerFactory>();
	}

	public static FactoryManager getInstance() {
		if (null == instance) {
			instance = new FactoryManager();
		}
		return instance;
	}
	
	/**
	 * Restituisce l'oggetto <code>EntityManagerFactory</code> a partire dalla persistence unit passata come argomento.
	 * @param persistenceUnitName il nome della persistence unit così come definito nel file persistence.xml.
	 * @return una Factory capace di creare oggetti <code>EntityManager</code>.
	 */
	public EntityManagerFactory getFactory(String persistenceUnitName) {
		EntityManagerFactory factory = factories.get(persistenceUnitName);
		if (factory == null || !factory.isOpen()) {
			factory = createFactory(persistenceUnitName);
		}		
		return factory;
	}
	
	private EntityManagerFactory createFactory(String persistenceUnitName) {
		logger.debug("Istanzio una nuova factory per la persistence unit: '" + persistenceUnitName + "'");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		factories.put(persistenceUnitName, emf);
		return emf;
	}

}

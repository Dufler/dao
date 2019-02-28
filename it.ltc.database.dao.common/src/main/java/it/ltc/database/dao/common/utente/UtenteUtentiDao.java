package it.ltc.database.dao.common.utente;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.UtenteCommessaJoinUtenti;
import it.ltc.database.model.utente.UtenteFeaturesJoinUtenti;
import it.ltc.database.model.utente.UtentePermessiJoinUtenti;
import it.ltc.database.model.utente.UtenteSedeJoinUtenti;
import it.ltc.database.model.utente.UtenteUtenti;

public class UtenteUtentiDao extends CRUDDao<UtenteUtenti> {
	
//	private static final Logger logger = Logger.getLogger("UtenteDao");
	
//	private static UtenteUtentiDao instance;
//
//	private UtenteUtentiDao() {
//		super(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME, UtenteUtenti.class);
//	}
//
//	public static UtenteUtentiDao getInstance() {
//		if (null == instance) {
//			instance = new UtenteUtentiDao();
//		}
//		return instance;
//	}
	
	public UtenteUtentiDao() {
		this(LOCAL_UTENTE_PERSISTENCE_UNIT_NAME);
	}
	
	public UtenteUtentiDao(String persistenceUnit) {
		super(persistenceUnit, UtenteUtenti.class);
	}
	
	public UtenteUtenti getUserByResource(String risorsa) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UtenteUtenti> criteria = cb.createQuery(UtenteUtenti.class);
        Root<UtenteUtenti> member = criteria.from(UtenteUtenti.class);
        Predicate condizioneRisorsa = cb.equal(member.get("risorsaTemporanea"), risorsa);
        Predicate condizioneTempo = cb.greaterThan(member.get("scadenzaRisorsa"), new Date());
        criteria.select(member).where(cb.and(condizioneRisorsa, condizioneTempo));
		List<UtenteUtenti> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		UtenteUtenti user = lista.isEmpty() ? null : lista.get(0);
        return user;
	}
	
	public UtenteUtenti getUserByUsername(String username) {
		UtenteUtenti user = getUserByUsername(username, false, false);
		return user;
	}
	
	/**
	 * Trova l'utente specificato in base all'username fornito. Se richiesto vengono caricati i dettagli della sua utenza.
	 * @param username l'username che identifica l'utente.
	 * @param details indica se devono essere caricati o meno i dettagli.
	 * @param refresh indica se forzare il ricaricamento dei dettagli o meno
	 * @return l'utente trovato, null se non esistente.
	 */
	public UtenteUtenti getUserByUsername(String username, boolean details, boolean refresh) {
		UtenteUtenti user = findByID(username);
		if (user != null && details) {
			// Inserisco le altre info: sedi, commesse, permessi e features (se necessario)
			if (refresh || user.getSedi() == null)
				user.setSedi(getSedi(user));
			if (refresh || user.getCommesse() == null)
				user.setCommesse(getCommesse(user));
			if (refresh || user.getPermessi() == null)
				user.setPermessi(getPermessi(user));
			if (refresh || user.getFeatures() == null)
				user.setFeatures(getFeatures(user));
		}
		return user;
	}

	private Set<Integer> getPermessi(UtenteUtenti user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtentePermessiJoinUtenti> criteria = cb.createQuery(UtentePermessiJoinUtenti.class);
		Root<UtentePermessiJoinUtenti> root = criteria.from(UtentePermessiJoinUtenti.class);
		criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
		List<UtentePermessiJoinUtenti> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<Integer> permessi = new HashSet<Integer>();
		for (UtentePermessiJoinUtenti entity : lista) {
			permessi.add(entity.getIdPermesso());
		}
		return permessi;
	}

	private Set<Integer> getCommesse(UtenteUtenti user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtenteCommessaJoinUtenti> criteria = cb.createQuery(UtenteCommessaJoinUtenti.class);
		Root<UtenteCommessaJoinUtenti> root = criteria.from(UtenteCommessaJoinUtenti.class);
		criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
		List<UtenteCommessaJoinUtenti> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<Integer> commesse = new HashSet<Integer>();
		for (UtenteCommessaJoinUtenti entity : lista) {
			commesse.add(entity.getIdCommessa());
		}
		return commesse;
	}

	private Set<Integer> getSedi(UtenteUtenti user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtenteSedeJoinUtenti> criteria = cb.createQuery(UtenteSedeJoinUtenti.class);
		Root<UtenteSedeJoinUtenti> root = criteria.from(UtenteSedeJoinUtenti.class);
		criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
		List<UtenteSedeJoinUtenti> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<Integer> sedi = new HashSet<Integer>();
		for (UtenteSedeJoinUtenti entity : lista) {
			sedi.add(entity.getIdSede());
		}
		return sedi;
	}
	
	private Set<String> getFeatures(UtenteUtenti user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtenteFeaturesJoinUtenti> criteria = cb.createQuery(UtenteFeaturesJoinUtenti.class);
		Root<UtenteFeaturesJoinUtenti> root = criteria.from(UtenteFeaturesJoinUtenti.class);
		criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
		List<UtenteFeaturesJoinUtenti> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<String> features = new HashSet<String>();
		for (UtenteFeaturesJoinUtenti entity : lista) {
			features.add(entity.getFeature());
		}
		return features;
	}
	
	/**
	 * Aggiorna le sedi legate all'utente.
	 * @param utente L'utente da aggiornare.
	 * @param sedi La lista di sedi legate all'utente.
	 * @return l'esito dell'operazione.
	 */
	protected boolean updateUserSedi(UtenteUtenti utente, Set<Integer> sedi) {
		boolean update;
		EntityManager updater = getManager();
		String username = utente.getUsername();
		UtenteUtenti user = updater.find(UtenteUtenti.class, username);
		if (user != null || sedi == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtenteSedeJoinUtenti> criteria = cb.createQuery(UtenteSedeJoinUtenti.class);
			Root<UtenteSedeJoinUtenti> root = criteria.from(UtenteSedeJoinUtenti.class);
			criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
			List<UtenteSedeJoinUtenti> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtenteSedeJoinUtenti toDelete : lista) {
					updater.remove(toDelete);
				}
				for (Integer sede : sedi) {
					UtenteSedeJoinUtenti toInsert = new UtenteSedeJoinUtenti();
					toInsert.setIdSede(sede);
					toInsert.setUtente(username);
					updater.persist(toInsert);
				}
				t.commit();
				update = true;
			} catch (Exception e) {
				if (t != null && t.isActive())
					t.rollback();
				update = false;
			} finally {
				updater.close();
			}
		} else {
			update = false;
			updater.close();
		}
		return update;
	}
	
	/**
	 * Aggiorna le commesse legate all'utente.
	 * @param utente L'utente da aggiornare.
	 * @param sedi La lista di commesse legate all'utente.
	 * @return l'esito dell'operazione.
	 */
	protected boolean updateUserCommesse(UtenteUtenti utente, Set<Integer> commesse) {
		boolean update;
		EntityManager updater = getManager();
		String username = utente.getUsername();
		UtenteUtenti user = updater.find(UtenteUtenti.class, username);
		if (user != null || commesse == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtenteCommessaJoinUtenti> criteria = cb.createQuery(UtenteCommessaJoinUtenti.class);
			Root<UtenteCommessaJoinUtenti> root = criteria.from(UtenteCommessaJoinUtenti.class);
			criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
			List<UtenteCommessaJoinUtenti> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtenteCommessaJoinUtenti toDelete : lista) {
					updater.remove(toDelete);
				}
				for (Integer commessa : commesse) {
					UtenteCommessaJoinUtenti toInsert = new UtenteCommessaJoinUtenti();
					toInsert.setIdCommessa(commessa);
					toInsert.setUtente(username);
					updater.persist(toInsert);
				}
				t.commit();
				update = true;
			} catch (Exception e) {
				if (t != null && t.isActive())
					t.rollback();
				update = false;
			} finally {
				updater.close();
			}
		} else {
			update = false;
			updater.close();
		}
		return update;
	}
	
	/**
	 * Aggiorna le feature legate all'utente.
	 * @param utente L'utente da aggiornare.
	 * @param sedi La lista di feature legate all'utente.
	 * @return l'esito dell'operazione.
	 */
	protected boolean updateUserFeature(UtenteUtenti utente, Set<String> features) {
		boolean update;
		EntityManager updater = getManager();
		String username = utente.getUsername();
		UtenteUtenti user = updater.find(UtenteUtenti.class, username);
		if (user != null || features == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtenteFeaturesJoinUtenti> criteria = cb.createQuery(UtenteFeaturesJoinUtenti.class);
			Root<UtenteFeaturesJoinUtenti> root = criteria.from(UtenteFeaturesJoinUtenti.class);
			criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
			List<UtenteFeaturesJoinUtenti> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtenteFeaturesJoinUtenti toDelete : lista) {
					updater.remove(toDelete);
				}
				for (String feature : features) {
					UtenteFeaturesJoinUtenti toInsert = new UtenteFeaturesJoinUtenti();
					toInsert.setFeature(feature);
					toInsert.setUtente(username);
					updater.persist(toInsert);
				}
				t.commit();
				update = true;
			} catch (Exception e) {
				if (t != null && t.isActive())
					t.rollback();
				update = false;
			} finally {
				updater.close();
			}
		} else {
			update = false;
			updater.close();
		}
		return update;
	}
	
	/**
	 * Aggiorna i permessi legati all'utente.
	 * @param utente L'utente da aggiornare.
	 * @param sedi La lista di permessi concessi all'utente.
	 * @return l'esito dell'operazione.
	 */
	protected boolean updateUserPermessi(UtenteUtenti utente, Set<Integer> permessi) {
		boolean update;
		//EntityManager updater = FactoryManager.getInstance().getFactory(LOCAL_PERSISTENCE_UNIT).createEntityManager();
		EntityManager updater = getManager();
		String username = utente.getUsername();
		UtenteUtenti user = updater.find(UtenteUtenti.class, username);
		if (user != null || permessi == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtentePermessiJoinUtenti> criteria = cb.createQuery(UtentePermessiJoinUtenti.class);
			Root<UtentePermessiJoinUtenti> root = criteria.from(UtentePermessiJoinUtenti.class);
			criteria.select(root).where(cb.equal(root.get("utente"), user.getUsername()));
			List<UtentePermessiJoinUtenti> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtentePermessiJoinUtenti toDelete : lista) {
					updater.remove(toDelete);
				}
				for (Integer permesso : permessi) {
					UtentePermessiJoinUtenti toInsert = new UtentePermessiJoinUtenti();
					toInsert.setIdPermesso(permesso);
					toInsert.setUtente(username);
					updater.persist(toInsert);
				}
				t.commit();
				update = true;
			} catch (Exception e) {
				if (t != null && t.isActive())
					t.rollback();
				update = false;
			} finally {
				updater.close();
			}
		} else {
			update = false;
			updater.close();
		}
		return update;
	}
	
	//TODO - Update commesse e features.
	
	/**
	 * Inserisce un nuovo utente a sistema.
	 * Le proprietà dell'utente vanno inserite in seguito.
	 * @param utente L'utente da inserire.
	 * @return Il nuovo utente, <code>null</code> se l'operazione fallisce.
	 */
	public UtenteUtenti inserisci(UtenteUtenti utente) {
		UtenteUtenti user = insert(utente);
		return user;
	}
	
	public UtenteUtenti aggiorna(UtenteUtenti utente) {
		UtenteUtenti user = update(utente, utente.getUsername());
		return user;
	}
	
	public UtenteUtenti elimina(UtenteUtenti utente) {
		UtenteUtenti user = delete(utente.getUsername());
		return user;
	}

	@Override
	protected void updateValues(UtenteUtenti user, UtenteUtenti info) {
		user.setCognome(info.getCognome());
		user.setEmail(info.getEmail());
		user.setNome(info.getNome());
		user.setRisorsaTemporanea(info.getRisorsaTemporanea());
		user.setScadenzaRisorsa(info.getScadenzaRisorsa());
		//Mi assicuro di aggiornare la password solo se viene fornita.
		if (info.getPassword() != null && !info.getPassword().isEmpty())
			user.setPassword(info.getPassword());
	}

	public List<UtenteUtenti> trovaTutti(boolean details) {
		List<UtenteUtenti> utenti = findAll();
		//Se sono necessari i dettagli li recupero.
		if (details) for (UtenteUtenti utente : utenti) {
			utente.setSedi(getSedi(utente));
			utente.setCommesse(getCommesse(utente));
			utente.setPermessi(getPermessi(utente));
			utente.setFeatures(getFeatures(utente));
		}
		return utenti;
	}

}

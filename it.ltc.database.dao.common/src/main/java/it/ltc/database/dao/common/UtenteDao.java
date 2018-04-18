package it.ltc.database.dao.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.utente.Utente;
import it.ltc.database.model.utente.UtenteCommessaJoin;
import it.ltc.database.model.utente.UtenteCommessaJoinPK;
import it.ltc.database.model.utente.UtenteFeaturesJoin;
import it.ltc.database.model.utente.UtenteFeaturesJoinPK;
import it.ltc.database.model.utente.UtentePermessiJoin;
import it.ltc.database.model.utente.UtentePermessiJoinPK;
import it.ltc.database.model.utente.UtenteSedeJoin;
import it.ltc.database.model.utente.UtenteSedeJoinPK;

public class UtenteDao extends CRUDDao<Utente> {
	
	private static final Logger logger = Logger.getLogger("UtenteDao");
	
	private static final String LOCAL_PERSISTENCE_UNIT = "locale-utente";
	
	private static UtenteDao instance;

	private UtenteDao() {
		super(LOCAL_PERSISTENCE_UNIT, Utente.class);
	}

	public static UtenteDao getInstance() {
		if (null == instance) {
			instance = new UtenteDao();
		}
		return instance;
	}
	
	public Utente getUserByUsername(String username) {
		Utente user = getUserByUsername(username, false, false);
		return user;
	}
	
	/**
	 * Trova l'utente specificato in base all'username fornito. Se richiesto vengono caricati i dettagli della sua utenza.
	 * @param username l'username che identifica l'utente.
	 * @param details indica se devono essere caricati o meno i dettagli.
	 * @param refresh indica se forzare il ricaricamento dei dettagli o meno
	 * @return l'utente trovato, null se non esistente.
	 */
	public Utente getUserByUsername(String username, boolean details, boolean refresh) {
		Utente user = findByID(username);
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

	private Set<Integer> getPermessi(Utente user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtentePermessiJoin> criteria = cb.createQuery(UtentePermessiJoin.class);
		Root<UtentePermessiJoin> root = criteria.from(UtentePermessiJoin.class);
		criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
		List<UtentePermessiJoin> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<Integer> commesse = new HashSet<Integer>();
		for (UtentePermessiJoin entity : lista) {
			commesse.add(entity.getId().getIdPermesso());
		}
		return commesse;
	}

	private Set<Integer> getCommesse(Utente user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtenteCommessaJoin> criteria = cb.createQuery(UtenteCommessaJoin.class);
		Root<UtenteCommessaJoin> root = criteria.from(UtenteCommessaJoin.class);
		criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
		List<UtenteCommessaJoin> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<Integer> commesse = new HashSet<Integer>();
		for (UtenteCommessaJoin entity : lista) {
			commesse.add(entity.getId().getIdCommessa());
		}
		return commesse;
	}

	private Set<Integer> getSedi(Utente user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtenteSedeJoin> criteria = cb.createQuery(UtenteSedeJoin.class);
		Root<UtenteSedeJoin> root = criteria.from(UtenteSedeJoin.class);
		criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
		List<UtenteSedeJoin> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<Integer> sedi = new HashSet<Integer>();
		for (UtenteSedeJoin entity : lista) {
			sedi.add(entity.getId().getIdSede());
		}
		return sedi;
	}
	
	private Set<String> getFeatures(Utente user) {
		// Query
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UtenteFeaturesJoin> criteria = cb.createQuery(UtenteFeaturesJoin.class);
		Root<UtenteFeaturesJoin> root = criteria.from(UtenteFeaturesJoin.class);
		criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
		List<UtenteFeaturesJoin> lista = em.createQuery(criteria).getResultList();
		em.close();
		// Build
		Set<String> sedi = new HashSet<String>();
		for (UtenteFeaturesJoin entity : lista) {
			sedi.add(entity.getId().getFeature());
		}
		return sedi;
	}
	
	/**
	 * Aggiorna le sedi legate all'utente.
	 * @param utente L'utente da aggiornare.
	 * @param sedi La lista di sedi legate all'utente.
	 * @return l'esito dell'operazione.
	 */
	protected boolean updateUserSedi(Utente utente, Set<Integer> sedi) {
		boolean update;
		EntityManager updater = getManager();
		String username = utente.getUsername();
		Utente user = updater.find(Utente.class, username);
		if (user != null || sedi == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtenteSedeJoin> criteria = cb.createQuery(UtenteSedeJoin.class);
			Root<UtenteSedeJoin> root = criteria.from(UtenteSedeJoin.class);
			criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
			List<UtenteSedeJoin> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtenteSedeJoin toDelete : lista) {
					updater.remove(toDelete);
				}
				for (Integer sede : sedi) {
					UtenteSedeJoin toInsert = new UtenteSedeJoin();
					UtenteSedeJoinPK pk = new UtenteSedeJoinPK();
					pk.setIdSede(sede);
					pk.setUtente(username);
					toInsert.setId(pk);
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
	protected boolean updateUserCommesse(Utente utente, Set<Integer> commesse) {
		boolean update;
		EntityManager updater = getManager();
		String username = utente.getUsername();
		Utente user = updater.find(Utente.class, username);
		if (user != null || commesse == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtenteCommessaJoin> criteria = cb.createQuery(UtenteCommessaJoin.class);
			Root<UtenteCommessaJoin> root = criteria.from(UtenteCommessaJoin.class);
			criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
			List<UtenteCommessaJoin> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtenteCommessaJoin toDelete : lista) {
					updater.remove(toDelete);
				}
				for (Integer commessa : commesse) {
					UtenteCommessaJoin toInsert = new UtenteCommessaJoin();
					UtenteCommessaJoinPK pk = new UtenteCommessaJoinPK();
					pk.setIdCommessa(commessa);
					pk.setUtente(username);
					toInsert.setId(pk);
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
	protected boolean updateUserFeature(Utente utente, Set<String> features) {
		boolean update;
		EntityManager updater = getManager();
		String username = utente.getUsername();
		Utente user = updater.find(Utente.class, username);
		if (user != null || features == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtenteFeaturesJoin> criteria = cb.createQuery(UtenteFeaturesJoin.class);
			Root<UtenteFeaturesJoin> root = criteria.from(UtenteFeaturesJoin.class);
			criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
			List<UtenteFeaturesJoin> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtenteFeaturesJoin toDelete : lista) {
					updater.remove(toDelete);
				}
				for (String feature : features) {
					UtenteFeaturesJoin toInsert = new UtenteFeaturesJoin();
					UtenteFeaturesJoinPK pk = new UtenteFeaturesJoinPK();
					pk.setFeature(feature);
					pk.setUtente(username);
					toInsert.setId(pk);
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
	protected boolean updateUserPermessi(Utente utente, Set<Integer> permessi) {
		boolean update;
		//EntityManager updater = FactoryManager.getInstance().getFactory(LOCAL_PERSISTENCE_UNIT).createEntityManager();
		EntityManager updater = getManager();
		String username = utente.getUsername();
		Utente user = updater.find(Utente.class, username);
		if (user != null || permessi == null) {
			//Recupero tutte le sedi attualmente presenti
			CriteriaBuilder cb = updater.getCriteriaBuilder();
			CriteriaQuery<UtentePermessiJoin> criteria = cb.createQuery(UtentePermessiJoin.class);
			Root<UtentePermessiJoin> root = criteria.from(UtentePermessiJoin.class);
			criteria.select(root).where(cb.equal(root.get("id").get("utente"), user.getUsername()));
			List<UtentePermessiJoin> lista = updater.createQuery(criteria).getResultList();
			//Avvio la transazione: cancello tutte le sedi attualmente presenti e poi inserisco tutte le nuove sedi
			EntityTransaction t = updater.getTransaction();
			try {
				t.begin();
				for (UtentePermessiJoin toDelete : lista) {
					updater.remove(toDelete);
				}
				for (Integer permesso : permessi) {
					UtentePermessiJoin toInsert = new UtentePermessiJoin();
					UtentePermessiJoinPK pk = new UtentePermessiJoinPK();
					pk.setIdPermesso(permesso);
					pk.setUtente(username);
					toInsert.setId(pk);
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
	protected Utente inserisci(Utente utente) {
		Utente user = insert(utente);
		return user;
	}
	
	protected Utente aggiorna(Utente utente) {
		Utente user = update(utente, utente.getUsername());
		return user;
	}
	
	protected Utente elimina(Utente utente) {
		Utente user = delete(utente.getUsername());
		return user;
	}

	@Override
	protected void updateValues(Utente user, Utente info) {
		logger.info("Aggiornamento dei dati personali dell'utente.");
		//Controllo se mi sono stati forniti nuovi dati, se si li aggiorno
		String cognome = info.getCognome();
		if (cognome != null && !cognome.isEmpty())
			user.setCognome(cognome);
		String nome = info.getNome(); 
		if (nome != null && !nome.isEmpty())
			user.setNome(nome);
		String email = info.getEmail();
		if (email != null && !email.isEmpty())
			user.setEmail(email);
		//Controllo se è stata fornita una password, se si la sostituisco.
		String nuovaPassword = info.getNuovaPassword();
		if (nuovaPassword != null && !nuovaPassword.isEmpty()) {
			user.setPassword(nuovaPassword);
			logger.info("Verrà aggiornata anche la password con: '" + nuovaPassword + "'");
		}
	}

	public List<Utente> trovaTutti(boolean details) {
		List<Utente> utenti = findAll();
		//Se sono necessari i dettagli li recupero.
		if (details) for (Utente utente : utenti) {
			utente.setSedi(getSedi(utente));
			utente.setCommesse(getCommesse(utente));
			utente.setPermessi(getPermessi(utente));
			utente.setFeatures(getFeatures(utente));
		}
		return utenti;
	}

}

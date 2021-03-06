package it.ltc.database.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CondizioneWhere.Condizione;

/**
 * Dao per leggere dal DB.
 * @author Damiano
 *
 * @param <T> La classe entity di cui si occupa il dao.
 */
public class ReadOnlyDao<T> extends Dao {
	
	private static final Logger logger = Logger.getLogger("ReadOnlyDao");
	
	protected final Class<T> c;

	public ReadOnlyDao(String persistenceUnit, Class<T> c) {
		super(persistenceUnit);
		this.c = c;
	}
	
	/**
	 * Restituisce tutte le entity esistenti.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected List<T> findAll() {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member);
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce l'unica entity trovata corrispondente alle condizioni specificate. 
	 * @return un'entity o <code>null</code> in caso di errori o se esiste più di un'entity corrispondente.
	 */
	protected T findJustOne(List<CondizioneWhere> conditions) {
		T entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        if (conditions.isEmpty()) {
	        	criteria.select(member);
	        } else {
	        	criteria.select(member).where(getConditions(conditions, cb, member));
	        }	        
	        List<T> lista = em.createQuery(criteria).getResultList();
			entity = lista.size() == 1 ? lista.get(0) : null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}
	
	/**
	 * Restituisce un'entity che corrisponde ai criteri specificati. Nel caso che più entity siano trovate viene restituita la prima.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected T findOne(List<CondizioneWhere> conditions) {
		T entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        if (conditions.isEmpty()) {
	        	criteria.select(member);
	        } else {
	        	criteria.select(member).where(getConditions(conditions, cb, member));
	        }	        
	        List<T> lista = em.createQuery(criteria).getResultList();
			entity = lista.isEmpty() ? null : lista.get(0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}
	
	/**
	 * Restituisce tutte le entity esistenti che corrispondono ai criteri specificati.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected List<T> findAll(List<CondizioneWhere> conditions) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        if (conditions.isEmpty()) {
	        	criteria.select(member);
	        } else {
	        	criteria.select(member).where(getConditions(conditions, cb, member));
	        }
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce tutte le entity esistenti che corrispondono ai criteri specificati.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected List<T> findAll(List<CondizioneWhere> conditions, int maxResults) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        if (conditions.isEmpty()) {
	        	criteria.select(member);
	        } else {
	        	criteria.select(member).where(getConditions(conditions, cb, member));
	        }
			lista = em.createQuery(criteria).setMaxResults(maxResults).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	protected List<T> findAll(List<CondizioneWhere> conditions, int maxResults, String orderColumn, boolean ascendant) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        if (conditions.isEmpty()) {
	        	criteria.select(member).orderBy(getOrder(orderColumn, cb, member, ascendant));
	        } else {
	        	criteria.select(member).where(getConditions(conditions, cb, member)).orderBy(getOrder(orderColumn, cb, member, ascendant));
	        }
			lista = em.createQuery(criteria).setMaxResults(maxResults).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	protected Order getOrder(String name, CriteriaBuilder cb, Root<T> member, boolean ascendant) {
		return ascendant ? cb.asc(member.get(name)) : cb.desc(member.get(name));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Predicate getConditions(List<CondizioneWhere> conditions, CriteriaBuilder cb, Root<T> member) {
		List<Predicate> predicatesAnd = new LinkedList<>();
		List<Predicate> predicatesOr = new LinkedList<>();
		for (CondizioneWhere condizione : conditions) {
			Predicate predicate;
			switch (condizione.getOperatore()) {
				case EQUAL : predicate = cb.equal(member.get(condizione.getColonna()), condizione.getValore()); break;
				case LIKE : predicate = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString() + "%"); break;
				case START_WITH : predicate = cb.like(member.get(condizione.getColonna()), condizione.getValore().toString() + "%"); break;
				case END_WITH : predicate = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString()); break;
				case GREATER : predicate = cb.greaterThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
				case GREATER_OR_EQUAL : predicate = cb.greaterThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
				case LESSER : predicate = cb.lessThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
				case LESSER_OR_EQUAL : predicate = cb.lessThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
				case NULL : predicate = cb.isNull(member.get(condizione.getColonna())); break;
				case NOT_NULL : predicate = cb.isNotNull(member.get(condizione.getColonna())); break;
				default : predicate = null;
			}
			if (predicate != null)
			if (condizione.getCondizione() == Condizione.AND) predicatesAnd.add(predicate); else predicatesOr.add(predicate);
		}
		Predicate predicate = null;
		if (!predicatesAnd.isEmpty()) {
			Predicate[] predicates = new Predicate[predicatesAnd.size()];
			predicates = predicatesAnd.toArray(predicates);
			predicate = cb.and(predicates);
		}
		if (!predicatesOr.isEmpty()) {
			Predicate[] predicates = new Predicate[predicatesOr.size()];
			predicates = predicatesOr.toArray(predicates);
			if (predicate == null) {
				predicate = cb.or(predicates);
			} else {
				Predicate predicateOr = cb.or(predicates);
				predicate = cb.and(predicate, predicateOr);
			}
		}
		return predicate;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	protected Predicate[] getConditions(List<CondizioneWhere> conditions, CriteriaBuilder cb, Root<T> member) {
//		Predicate[] predicates = new Predicate[conditions.size()];
//		int index = 0;
//		for (CondizioneWhere condizione : conditions) {
//			switch (condizione.getOperatore()) {
//				case EQUAL : predicates[index] = cb.equal(member.get(condizione.getColonna()), condizione.getValore()); break;
//				case LIKE : predicates[index] = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString() + "%"); break;
//				case START_WITH : predicates[index] = cb.like(member.get(condizione.getColonna()), condizione.getValore().toString() + "%"); break;
//				case END_WITH : predicates[index] = cb.like(member.get(condizione.getColonna()), "%" + condizione.getValore().toString()); break;
//				case GREATER : predicates[index] = cb.greaterThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
//				case GREATER_OR_EQUAL : predicates[index] = cb.greaterThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
//				case LESSER : predicates[index] = cb.lessThan(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
//				case LESSER_OR_EQUAL : predicates[index] = cb.lessThanOrEqualTo(member.get(condizione.getColonna()), (Comparable) condizione.getValore()); break;
//				case NULL : predicates[index] = cb.isNull(member.get(condizione.getColonna())); break;
//				case NOT_NULL : predicates[index] = cb.isNotNull(member.get(condizione.getColonna())); break;
//			}
//			index++;
//		}
//		return predicates;
//	}
	
	/**
	 * Restituisce tutte le entity esistenti che hanno una certa proprietà.
	 * @return una lista di entities o <code>null</code> in caso di errori.
	 */
	protected List<T> findAllEqualTo(String columnName, Object value) {
		List<T> lista;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get(columnName), value));
			lista = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			lista = null;
		} finally {
			em.close();
		}		
        return lista;
	}
	
	/**
	 * Restituisce la prima entity esistente che ha quella proprietà.<br>
	 * Il numero massimo di entity che verranno restituite è 1 anche se potrebbero corrispoderne di più.
	 * @return una entity o <code>null</code> in caso di errori o nessuna corrispondenza.
	 */
	protected T findFirstOneEqualTo(String columnName, Object value) {
		T entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get(columnName), value));
			List<T> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
			entity = lista.isEmpty() ? null : lista.get(0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}
	
	/**
	 * Restituisce la prima entity esistente che ha quella proprietà.<br>
	 * Il numero massimo di entity che verranno cercate è 1.
	 * @return una entity o <code>null</code> in caso di errori o nessuna corrispondenza.
	 */
	protected T findOnlyOneEqualTo(String columnName, Object value) {
		T entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = cb.createQuery(c);
	        Root<T> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get(columnName), value));
			List<T> lista = em.createQuery(criteria).setMaxResults(2).getResultList();
			entity = lista.size() == 1 ? lista.get(0) : null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}
	
	/**
	 * Cerca la specifica entity usando la chiave passata come argomento.
	 * @param id il valore di chiave che identifica la entity desiderata.
	 * @return la entity trovata o <code>null</code> se non ci sono corrispondenze o in caso di errori.
	 */
	protected T findByID(Object id) {
		EntityManager em = getManager();
		T entity;
		try {
			entity = em.find(c, id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			entity = null;
		} finally {
			em.close();
		}	
		return entity;
	}

}

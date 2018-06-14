package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Commessa;

/**
 * Dao per le commesse usabile da tutte le applicazioni web.
 * Usa il datasource di default per accedere ai dati.
 * @author Damiano
 *
 */
public class CommessaDao extends CRUDDao<Commessa> {

	public CommessaDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CommessaDao(String persistenceUnit) {
		super(persistenceUnit, Commessa.class);
	}
	
	/**
	 * Restituisce la commessa con il dato ID trovata utilizzando il datasource di default.
	 * L'ID è la <code>Primary Key</code> per la tabella.
	 * @param id l'ID della commessa.
	 * @return La commessa corrispondente, se presente, <code>null</code> altrimenti.
	 */
	public Commessa trovaDaID(int id) {
		Commessa commessa = findByID(id);
		return commessa;
	}
	
	/**
	 * Restituisce la commessa con il dato nome risorsa utilizzando il datasource di default.
	 * La colonna <code>nome_risorsa</code> ha un vincolo di unique.
	 * @param resource il nome della risorsa
	 * @return la commessa trovata, se presente, <code>null</code> altrimenti.
	 */
	public Commessa trovaDaRisorsa(String resource) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Commessa> criteria = cb.createQuery(Commessa.class);
		Root<Commessa> root = criteria.from(Commessa.class);
		criteria.select(root).where(cb.equal(root.get("nomeRisorsa"), resource));
		List<Commessa> lista = em.createQuery(criteria).getResultList();
		em.close();
		//Ci deve essere esattamente un risultato, altri casi sono considerati un errore.
		Commessa commessa = lista.size() == 1 ? lista.get(0) : null;
		return commessa;
	}
	
	/**
	 * Restituisce tutte le commesse esistenti.
	 * @return Una <code>List</code> che contiene tutte le commesse. 
	 */
	public List<Commessa> trovaTutte() {
		List<Commessa> commesse = findAll();
        return commesse;
	}
	
	public Commessa inserisci(Commessa commessa) {
		Commessa entity = insert(commessa);
		return entity;
	}
	
	public Commessa aggiorna(Commessa commessa) {
		Commessa entity = update(commessa, commessa.getId());
		return entity;
	}
	
	public Commessa elimina(Commessa commessa) {
		Commessa entity = delete(commessa.getId());
		return entity;
	}

	@Override
	protected void updateValues(Commessa oldEntity, Commessa entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setIdCliente(entity.getIdCliente());
		oldEntity.setIdSede(entity.getIdSede());
		oldEntity.setNome(entity.getNome());
	}

}

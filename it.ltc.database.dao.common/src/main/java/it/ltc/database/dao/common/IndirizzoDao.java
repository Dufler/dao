package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.common.model.CriteriUltimaModifica;
import it.ltc.database.model.centrale.Indirizzo;

public class IndirizzoDao extends CRUDDao<Indirizzo> {
	
	public IndirizzoDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public IndirizzoDao(String persistenceUnit) {
		super(persistenceUnit, Indirizzo.class);
	}
	
	public Indirizzo salvaIndirizzo(Indirizzo indirizzo) {
		//Controllo se l'indirizzo esiste già, se non esiste lo inserisco.
		Indirizzo esistente = trovaIndirizzo(indirizzo);
		if (esistente == null) {
			//Nel caso in cui mi hanno chiesto di salvare un indirizzo già presente a sistema (cioè con ID valorizzato) 
			//ma comunque abbastanza diverso dalla versione precedente ne creo uno nuovo.
			indirizzo.setId(0);
			esistente = insert(indirizzo);
		} else {
			//Se cambiano pochi dettagli (es. numero di telefono) aggiorno quello che serve e lo restituisco.
			indirizzo.setId(esistente.getId());
			esistente = aggiorna(indirizzo);
		}
		return esistente;
	}

	protected Indirizzo trovaIndirizzo(Indirizzo nuovoIndirizzo) {
		EntityManager em = getManager();
		//Controllo se ne ho già almeno un altro con le stesse caratteristiche.
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Indirizzo> criteria = cb.createQuery(Indirizzo.class);
        Root<Indirizzo> member = criteria.from(Indirizzo.class);
        Predicate likeRagioneSociale = cb.like(member.get("ragioneSociale"), "%" + nuovoIndirizzo.getRagioneSociale() + "%");
        Predicate likeIndirizzo = cb.like(member.get("indirizzo"), "%" + nuovoIndirizzo.getIndirizzo() + "%");
        Predicate likeLocalita = cb.like(member.get("localita"), "%" + nuovoIndirizzo.getLocalita() + "%");
        Predicate cap = cb.equal(member.get("cap"), nuovoIndirizzo.getCap());
        Predicate provincia = cb.equal(member.get("provincia"), nuovoIndirizzo.getProvincia());
        Predicate nazione = cb.equal(member.get("nazione"), nuovoIndirizzo.getNazione());
        criteria.select(member).where(cb.and(cap, provincia, nazione, likeRagioneSociale, likeIndirizzo, likeLocalita));
		List<Indirizzo> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		Indirizzo indirizzo = lista.isEmpty() ? null : lista.get(0);
		return indirizzo;
	}
	
	protected Indirizzo inserisci(Indirizzo indirizzo) {
		Indirizzo entity = insert(indirizzo);
		return entity;
	}
	
	protected Indirizzo aggiorna(Indirizzo indirizzo) {
		Indirizzo entity = update(indirizzo, indirizzo.getId());
		return entity;
	}

	public Indirizzo elimina(Indirizzo indirizzo) {
		Indirizzo entity = delete(indirizzo.getId());
		return entity;
	}
	
	public Indirizzo trovaDaID(int id) {
		Indirizzo indirizzo = findByID(id);
		return indirizzo;
	}

	public List<Indirizzo> trovaTutti() {
		List<Indirizzo> lista = findAll();
        return lista;
	}
	
	public List<Indirizzo> trovaDaUltimaModifica(CriteriUltimaModifica criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Indirizzo> criteria = cb.createQuery(Indirizzo.class);
        Root<Indirizzo> member = criteria.from(Indirizzo.class);
        criteria.select(member).where(cb.greaterThan(member.get("dataUltimaModifica"), criteri.getDataUltimaModifica()));
        List<Indirizzo> spedizioni = em.createQuery(criteria).getResultList();
        em.close();
        return spedizioni;
	}

	@Override
	protected void updateValues(Indirizzo entity, Indirizzo indirizzo) {
		if (indirizzo.getCap() != null && !indirizzo.getCap().isEmpty())
			entity.setCap(indirizzo.getCap());
		entity.setConsegnaAlPiano(indirizzo.getConsegnaAlPiano());
		entity.setConsegnaAppuntamento(indirizzo.getConsegnaAppuntamento());
		entity.setConsegnaGdo(indirizzo.getConsegnaGdo());
		entity.setConsegnaPrivato(indirizzo.getConsegnaPrivato());
		if (indirizzo.getEmail() != null && !indirizzo.getEmail().isEmpty())
			entity.setEmail(indirizzo.getEmail());
		if (indirizzo.getIndirizzo() != null && !indirizzo.getIndirizzo().isEmpty())
			entity.setIndirizzo(indirizzo.getIndirizzo());
		if (indirizzo.getLocalita() != null && !indirizzo.getLocalita().isEmpty())
			entity.setLocalita(indirizzo.getLocalita());
		if (indirizzo.getNazione() != null && !indirizzo.getNazione().isEmpty())
			entity.setNazione(indirizzo.getNazione());
		if (indirizzo.getProvincia() != null && !indirizzo.getProvincia().isEmpty())
			entity.setProvincia(indirizzo.getProvincia());
		if (indirizzo.getRagioneSociale() != null && !indirizzo.getRagioneSociale().isEmpty())
			entity.setRagioneSociale(indirizzo.getRagioneSociale());
		if (indirizzo.getTelefono() != null && !indirizzo.getTelefono().isEmpty())
			entity.setTelefono(indirizzo.getTelefono());
	}

}

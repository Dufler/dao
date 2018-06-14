package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.common.model.CriteriUltimaModifica;
import it.ltc.database.model.centrale.Cap;
import it.ltc.database.model.centrale.CapPK;

public class CapDao extends CRUDDao<Cap> {
	
	public CapDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public CapDao(String persistenceUnit) {
		super(persistenceUnit, Cap.class);
	}
	
	public List<Cap> trovaDaUltimaModifica(CriteriUltimaModifica criteri) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cap> criteria = cb.createQuery(Cap.class);
        Root<Cap> member = criteria.from(Cap.class);
        criteria.select(member).where(cb.greaterThan(member.get("dataUltimaModifica"), criteri.getDataUltimaModifica()));
        List<Cap> lista = em.createQuery(criteria).getResultList();
        em.close();
        return lista;
	}

	public Cap inserisci(Cap cap) {
		Cap entity = insert(cap);
		return entity;
	}

	public Cap aggiorna(Cap cap) {
		Cap entity = update(cap, cap.getPK());
		return entity;
	}

	public Cap elimina(Cap cap) {
		Cap entity = delete(cap.getPK());
		return entity;
	}

	public List<Cap> trovaTutti() {
		List<Cap> entities = findAll();
		return entities;
	}

	public Cap trovaDaCap(String cap) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cap> criteria = cb.createQuery(Cap.class);
        Root<Cap> member = criteria.from(Cap.class);
        criteria.select(member).where(cb.equal(member.get("cap"), cap));
		List<Cap> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
		em.close();
		Cap entity = lista.isEmpty() ? null : lista.get(0);
		return entity;
	}

	public Cap trovaDaCapELocalita(String cap, String town) {
		CapPK pk = new CapPK();
		pk.setCap(cap);
		pk.setLocalita(town);
		Cap entity = findByID(pk);
		return entity;
	}
	
	@Override
	protected void updateValues(Cap oldEntity, Cap entity) {
		oldEntity.setBrtDisagiate(entity.getBrtDisagiate());
		oldEntity.setBrtIsole(entity.getBrtIsole());
		oldEntity.setBrtZtl(entity.getBrtZtl());
		oldEntity.setProvincia(entity.getProvincia());
		oldEntity.setRegione(entity.getRegione());
		oldEntity.setTntOreDieci(entity.getTntOreDieci());
		oldEntity.setTntOreDodici(entity.getTntOreDodici());
	}

}

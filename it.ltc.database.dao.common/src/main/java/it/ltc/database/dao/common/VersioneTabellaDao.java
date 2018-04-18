package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.VersioneTabella;

public class VersioneTabellaDao extends Dao {
	
	private static VersioneTabellaDao instance;

	public VersioneTabellaDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public static VersioneTabellaDao getInstance() {
		if (null == instance) {
			instance = new VersioneTabellaDao();
		}
		return instance;
	}
	
	public VersioneTabella findByCodice(String tabella) {
		VersioneTabella valuta = em.find(VersioneTabella.class, tabella);
		return valuta;
	}
	
	public List<VersioneTabella> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VersioneTabella> criteria = cb.createQuery(VersioneTabella.class);
        Root<VersioneTabella> member = criteria.from(VersioneTabella.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

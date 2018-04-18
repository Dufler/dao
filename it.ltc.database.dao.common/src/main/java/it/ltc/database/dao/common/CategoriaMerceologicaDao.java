package it.ltc.database.dao.common;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.CategoriaMerceologica;

public class CategoriaMerceologicaDao extends Dao {
	
	private static CategoriaMerceologicaDao instance;

	private CategoriaMerceologicaDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static CategoriaMerceologicaDao getInstance() {
		if (null == instance) {
			instance = new CategoriaMerceologicaDao();
		}
		return instance;
	}
	
	public CategoriaMerceologica findByNome(String nome) {
		CategoriaMerceologica feature = em.find(CategoriaMerceologica.class, nome);
		return feature;
	}
	
	public List<CategoriaMerceologica> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategoriaMerceologica> criteria = cb.createQuery(CategoriaMerceologica.class);
        Root<CategoriaMerceologica> member = criteria.from(CategoriaMerceologica.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}

}

package it.ltc.database.dao.common;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.CorriereServizio;
import it.ltc.database.model.centrale.json.ServizioCorriereJSON;

public class CorriereServizioDao extends Dao {
	
	private static CorriereServizioDao instance;

	private CorriereServizioDao() {
		super(DATASOURCE_CENTRALE_PERSISTENCE_UNIT_NAME);
	}

	public static CorriereServizioDao getInstance() {
		if (null == instance) {
			instance = new CorriereServizioDao();
		}
		return instance;
	}
	
	public CorriereServizio findByID(String codice) {
		CorriereServizio servizio = em.find(CorriereServizio.class, codice);
		return servizio;
	}
	
	public List<CorriereServizio> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CorriereServizio> criteria = cb.createQuery(CorriereServizio.class);
        Root<CorriereServizio> member = criteria.from(CorriereServizio.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
	}
	
	public List<ServizioCorriereJSON> trovaTutti() {
		List<ServizioCorriereJSON> list = new LinkedList<>();
		for (CorriereServizio servizio : findAll()) {
			ServizioCorriereJSON json = new ServizioCorriereJSON();
			json.setCodice(servizio.getId().getCodice());
			json.setCodificaCorriere(servizio.getId().getCodificaCorriere());
			json.setIdCorriere(servizio.getId().getCorriere());
			json.setNome(servizio.getNome());
			list.add(json);
		}
		return list;
	}

}

package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.seriale.ColliPackConSeriale;

public class ColliPackSerialiDao extends CRUDDao<ColliPackConSeriale> {
	
	private static final Logger logger = Logger.getLogger("ColliPackSerialiDao");

	public ColliPackSerialiDao(String persistenceUnit) {
		super(persistenceUnit, ColliPackConSeriale.class);
	}

	@Override
	protected void updateValues(ColliPackConSeriale oldEntity, ColliPackConSeriale entity) {
		oldEntity.setCodArtStr(entity.getCodArtStr());
		oldEntity.setCodiceArticolo(entity.getCodiceArticolo());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setFlagimp(entity.getFlagimp());
		oldEntity.setFlagtc(entity.getFlagtc());
		oldEntity.setIdPakiarticolo(entity.getIdPakiarticolo());
		oldEntity.setIdTestaPaki(entity.getIdTestaPaki());
		oldEntity.setMagazzino(entity.getMagazzino());
		oldEntity.setNrIdColloPk(entity.getNrIdColloPk());
		oldEntity.setOperatore(entity.getOperatore());
		oldEntity.setQta(entity.getQta());
		oldEntity.setQtaimpegnata(entity.getQtaimpegnata());
		oldEntity.setTaglia(entity.getTaglia());
	}
	
	public List<ColliPackConSeriale> inserisci(List<ColliPackConSeriale> collipacks) {
		List<ColliPackConSeriale> entities = insert(collipacks);
		return entities;
	}
	
	public List<ColliPackConSeriale> elimina(List<ColliPackConSeriale> entities) {
		List<Object> keys = new LinkedList<>();
		for (ColliPackConSeriale entity : entities)
			keys.add(entity.getIdColliPack());
		List<ColliPackConSeriale> removedEntities = delete(keys);
		return removedEntities;
	}
	
	public ColliPackConSeriale aggiorna(ColliPackConSeriale prodotto) {
		ColliPackConSeriale entity = update(prodotto, prodotto.getIdColliPack());
		return entity;
	}
	
	public List<ColliPackConSeriale> trovaProdottiNelCarico(int idCarico) {
		List<ColliPackConSeriale> entities = findAllEqualTo("idTestaPaki", idCarico);
		return entities;
	}
	
	public boolean isProdottoPresenteInMagazzino(String sku) {
		ColliPackConSeriale entity = findOnlyOneEqualTo("codArtStr", sku);
        boolean presente = entity != null;
		return presente;
	}
	
	/**
	 * Trova il record di collipack con il seriale specificato, nel caso ce ne fosse più di uno restituisce il più recente.
	 * @param seriale
	 * @return
	 */
	public ColliPackConSeriale trovaSeriale(String seriale) {
		ColliPackConSeriale entity;
		EntityManager em = getManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<ColliPackConSeriale> criteria = cb.createQuery(c);
	        Root<ColliPackConSeriale> member = criteria.from(c);
	        criteria.select(member).where(cb.equal(member.get("seriale"), seriale)).orderBy(cb.desc(member.get("idColliPack")));
			List<ColliPackConSeriale> lista = em.createQuery(criteria).setMaxResults(1).getResultList();
			entity = lista.size() == 1 ? lista.get(0) : null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			entity = null;
		} finally {
			em.close();
		}		
        return entity;
	}

	public List<ColliPackConSeriale> trovaProdottiNelCollo(String keyColloCar) {
		List<ColliPackConSeriale> entities = findAllEqualTo("nrIdColloPk", keyColloCar);
		return entities;
	}

}

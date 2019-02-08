package it.ltc.database.dao.legacy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ArtiBar;

public class ArtibarDao extends CRUDDao<ArtiBar> {

	public ArtibarDao(String persistenceUnit) {
		super(persistenceUnit, ArtiBar.class);
	}
	
	public List<ArtiBar> trovaDaSKU(String sku) {
		List<ArtiBar> entities = findAllEqualTo("codiceArticolo", sku);
		return entities;
	}
	
	public List<ArtiBar> trovaDaIDUnivoco(String idUniArticolo) {
		List<ArtiBar> entities = findAllEqualTo("idUniArticolo", idUniArticolo);
		return entities;
	}
	
	public ArtiBar trovaDaBarcode(String barcode) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ArtiBar> criteria = cb.createQuery(ArtiBar.class);
        Root<ArtiBar> member = criteria.from(ArtiBar.class);
        criteria.select(member).where(cb.or(cb.equal(member.get("barraUPC"), barcode), cb.equal(member.get("barraEAN"), barcode)));
        List<ArtiBar> entities = em.createQuery(criteria).setMaxResults(1).getResultList();
        em.close();
        ArtiBar entity = entities.isEmpty() ? null : entities.get(0);
        return entity;
	}
	
	public ArtiBar inserisci(ArtiBar barcode) {
		ArtiBar entity = insert(barcode);
		return entity;
	}
	
	public ArtiBar aggiorna(ArtiBar barcode) {
		ArtiBar entity = update(barcode, barcode.getIdArtiBar());
		return entity;
	}
	
	public ArtiBar elimina(ArtiBar barcode) {
		ArtiBar entity = delete(barcode.getIdArtiBar());
		return entity;
	}

	@Override
	protected void updateValues(ArtiBar oldEntity, ArtiBar entity) {
		oldEntity.setBarraEAN(entity.getBarraEAN());
		oldEntity.setBarraUPC(entity.getBarraUPC());
		oldEntity.setCodiceArticolo(entity.getCodiceArticolo());
		oldEntity.setIdUniArticolo(entity.getIdUniArticolo());
		oldEntity.setTaglia(entity.getTaglia());
	}

}

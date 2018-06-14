package it.ltc.database.dao.legacy.coltorti;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.coltorti.ArticoliColtorti;
import it.ltc.model.shared.dao.IProdottoDao;

public class ArticoliColtortiDao extends CRUDDao<ArticoliColtorti> implements IProdottoDao<ArticoliColtorti> {
	
	public ArticoliColtortiDao(String persistenceUnit) {
		super(persistenceUnit, ArticoliColtorti.class);
	}
	
	public ArticoliColtorti trovaDaID(int id) {
		ArticoliColtorti entity = findByID(id);
		return entity;
	}
	
	@Override
	public List<ArticoliColtorti> trovaTutti() {
		List<ArticoliColtorti> entities = findAll();
		return entities;
	}
	
	public ArticoliColtorti inserisci(ArticoliColtorti articolo) {
		ArticoliColtorti entity = insert(articolo);
		return entity;
	}
	
	public ArticoliColtorti aggiorna(ArticoliColtorti articolo) {
		ArticoliColtorti entity = update(articolo, articolo.getIdArticolo());
		return entity;
	}
	
	public ArticoliColtorti elimina(ArticoliColtorti articolo) {
		ArticoliColtorti entity = delete(articolo.getIdArticolo());
		return entity;
	}
	
	public ArticoliColtorti trovaDaIDUnivoco(String idUnivoco) {
		ArticoliColtorti entity = findFirstOneEqualTo("idUniArticolo", idUnivoco);
		return entity;
	}
	
	@Override
	public ArticoliColtorti trovaDaSKU(String sku) {
		ArticoliColtorti entity = findFirstOneEqualTo("codArtStr", sku);
		return entity;
	}
	
	@Override
	public ArticoliColtorti trovaDaBarcode(String barcode) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ArticoliColtorti> criteria = cb.createQuery(ArticoliColtorti.class);
        Root<ArticoliColtorti> member = criteria.from(ArticoliColtorti.class);
        criteria.select(member).where(cb.or(cb.equal(member.get("codBarre"), barcode), cb.equal(member.get("barraEAN"), barcode)));
        List<ArticoliColtorti> articoli = em.createQuery(criteria).setMaxResults(1).getResultList();
        em.close();
        ArticoliColtorti articolo = articoli.isEmpty() ? null : articoli.get(0);
        return articolo;
	}

	@Override
	protected void updateValues(ArticoliColtorti oldEntity, ArticoliColtorti entity) {
		oldEntity.setArtH(entity.getArtH());
		oldEntity.setArtL(entity.getArtL());
		oldEntity.setArtZ(entity.getArtZ());
		oldEntity.setArtPeso(entity.getArtPeso());
		oldEntity.setBarraEAN(entity.getBarraEAN());
		oldEntity.setBarraUPC(entity.getBarraUPC());
		oldEntity.setCodBarre(entity.getCodBarre());
		oldEntity.setCategoria(entity.getCategoria());
		oldEntity.setCatMercDett(entity.getCatMercDett());
		oldEntity.setCatMercGruppo(entity.getCatMercGruppo());
		oldEntity.setCodArtInt(entity.getCodArtInt());
		oldEntity.setCodArtOld(entity.getCodArtOld());
		oldEntity.setCodArtStr(entity.getCodArtStr());
		oldEntity.setColore(entity.getColore());
		oldEntity.setComposizione(entity.getComposizione());
		oldEntity.setDescAggiuntiva(entity.getDescAggiuntiva());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setIdUniArticolo(entity.getIdUniArticolo());
		oldEntity.setLinea(entity.getLinea());
		oldEntity.setMadeIn(entity.getMadeIn());
		oldEntity.setModello(entity.getModello());
		oldEntity.setNote(entity.getNote());
		oldEntity.setStagione(entity.getStagione());
		oldEntity.setTaglia(entity.getTaglia());
		oldEntity.setTipoCassa(entity.getTipoCassa());
		oldEntity.setValVen(entity.getValVen());
		//Aggiungo qui le particolarità
		oldEntity.setParticolarita(entity.getParticolarita());
	}

}

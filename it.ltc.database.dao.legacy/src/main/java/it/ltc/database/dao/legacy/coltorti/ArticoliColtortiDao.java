package it.ltc.database.dao.legacy.coltorti;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.dao.CondizioneWhere.Operatore;
import it.ltc.database.model.legacy.coltorti.ArticoliColtorti;

public class ArticoliColtortiDao extends CRUDDao<ArticoliColtorti> {
	
	public ArticoliColtortiDao(String persistenceUnit) {
		super(persistenceUnit, ArticoliColtorti.class);
	}
	
	public ArticoliColtorti trovaDaID(int id) {
		ArticoliColtorti entity = findByID(id);
		return entity;
	}
	
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
		ArticoliColtorti entity = findOnlyOneEqualTo("idUniArticolo", idUnivoco);
		return entity;
	}
	
	public ArticoliColtorti trovaDaSKU(String sku) {
		ArticoliColtorti entity = findOnlyOneEqualTo("codArtStr", sku);
		return entity;
	}
	
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

	public List<ArticoliColtorti> trova(String sku, String modello, String stagione, String descrizione, int maxResults) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		if (sku != null && !sku.isEmpty())
			condizioni.add(new CondizioneWhere("codArtStr", sku, Operatore.LIKE));
		if (modello != null && !modello.isEmpty())
			condizioni.add(new CondizioneWhere("modello", modello, Operatore.LIKE));
		if (stagione != null && !stagione.isEmpty())
			condizioni.add(new CondizioneWhere("stagione", stagione));
		if (descrizione != null && !descrizione.isEmpty())
			condizioni.add(new CondizioneWhere("descrizione", descrizione, Operatore.LIKE));
		List<ArticoliColtorti> entities = findAll(condizioni, maxResults);
		return entities;
	}

	public List<ArticoliColtorti> trovaDaUltimaModifica(Date ultimaModifica) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("dataModifica", ultimaModifica, Operatore.GREATER));
		List<ArticoliColtorti> entities = findAll(condizioni);
        return entities;
	}

}

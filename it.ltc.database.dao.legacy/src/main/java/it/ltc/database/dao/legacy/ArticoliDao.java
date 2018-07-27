package it.ltc.database.dao.legacy;

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
import it.ltc.database.model.legacy.Articoli;

public class ArticoliDao extends CRUDDao<Articoli> {
	
	public ArticoliDao(String persistenceUnit) {
		super(persistenceUnit, Articoli.class);
	}
	
	public Articoli trovaDaID(int id) {
		Articoli entity = findByID(id);
		return entity;
	}

	public List<Articoli> trovaTutti() {
		List<Articoli> entities = findAll();
		return entities;
	}
	
	public Articoli inserisci(Articoli articolo) {
		Articoli entity = insert(articolo);
		return entity;
	}
	
	public Articoli aggiorna(Articoli articolo) {
		Articoli entity = update(articolo, articolo.getIdArticolo());
		return entity;
	}
	
	public Articoli elimina(Articoli articolo) {
		Articoli entity = delete(articolo.getIdArticolo());
		return entity;
	}
	
	public Articoli trovaDaIDUnivoco(String idUnivoco) {
		Articoli entity = findOnlyOneEqualTo("idUniArticolo", idUnivoco);
		return entity;
	}
	
	public Articoli trovaDaSKU(String sku) {
		Articoli entity = findOnlyOneEqualTo("codArtStr", sku);
		return entity;
	}
	
	public Articoli trovaDaBarcode(String barcode) {
		EntityManager em = getManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Articoli> criteria = cb.createQuery(Articoli.class);
        Root<Articoli> member = criteria.from(Articoli.class);
        criteria.select(member).where(cb.or(cb.equal(member.get("codBarre"), barcode), cb.equal(member.get("barraEAN"), barcode)));
        List<Articoli> articoli = em.createQuery(criteria).setMaxResults(1).getResultList();
        em.close();
        Articoli articolo = articoli.isEmpty() ? null : articoli.get(0);
        return articolo;
	}

	@Override
	protected void updateValues(Articoli oldEntity, Articoli entity) {
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
	}

	public List<Articoli> trova(String sku, String modello, String stagione, String descrizione, int maxResults) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		if (sku != null && !sku.isEmpty())
			condizioni.add(new CondizioneWhere("codArtStr", sku, Operatore.LIKE));
		if (modello != null && !modello.isEmpty())
			condizioni.add(new CondizioneWhere("modello", modello, Operatore.LIKE));
		if (stagione != null && !stagione.isEmpty())
			condizioni.add(new CondizioneWhere("stagione", stagione));
		if (descrizione != null && !descrizione.isEmpty())
			condizioni.add(new CondizioneWhere("descrizione", descrizione, Operatore.LIKE));
		List<Articoli> entities = findAll(condizioni, maxResults);
		return entities;
	}

	public List<Articoli> trovaDaUltimaModifica(Date ultimaModifica) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("dataModifica", ultimaModifica, Operatore.GREATER));
		List<Articoli> entities = findAll(condizioni);
        return entities;
	}

}

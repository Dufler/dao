package it.ltc.database.dao.legacy;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.dao.CondizioneWhere.Condizione;
import it.ltc.database.dao.CondizioneWhere.Operatore;
import it.ltc.database.model.legacy.Articoli;

public class ArticoliDao extends CRUDDao<Articoli> {
	
	protected final HashMap<Integer, Articoli> mappaPerID;
	protected final HashMap<String, Articoli> mappaPerIDUnivoco;
	protected final HashMap<String, Articoli> mappaPerCodiceArticolo;
	
	public ArticoliDao(String persistenceUnit) {
		super(persistenceUnit, Articoli.class);
		mappaPerID = new HashMap<>();
		mappaPerIDUnivoco = new HashMap<>();
		mappaPerCodiceArticolo = new HashMap<>();
	}
	
	protected void inserisciInMappe(Articoli entity) {
		if (entity != null) {
			mappaPerID.put(entity.getIdArticolo(), entity);
			mappaPerIDUnivoco.put(entity.getIdUniArticolo(), entity);
			mappaPerCodiceArticolo.put(entity.getCodArtStr(), entity);
		}
	}
	
	protected void rimuoveDaMappe(Articoli entity) {
		if (entity != null) {
			mappaPerID.remove(entity.getIdArticolo());
			mappaPerIDUnivoco.remove(entity.getIdUniArticolo());
			mappaPerCodiceArticolo.remove(entity.getCodArtStr());
		}		
	}
	
	public List<Articoli> trovaTutti() {
		List<Articoli> entities = findAll();
		return entities;
	}
	
	public Articoli trovaDaID(int id) {
		if (!mappaPerID.containsKey(id)) {
			Articoli entity = findByID(id);
			inserisciInMappe(entity);
		}		
		return mappaPerID.get(id);
	}
	
	public Articoli trovaDaIDUnivoco(String idUnivoco) {
		if (!mappaPerIDUnivoco.containsKey(idUnivoco)) {
			Articoli entity = findOnlyOneEqualTo("idUniArticolo", idUnivoco);
			inserisciInMappe(entity);
		}		
		return mappaPerIDUnivoco.get(idUnivoco);
	}
	
	public Articoli trovaDaSKU(String sku) {
		if (!mappaPerCodiceArticolo.containsKey(sku)) {
			Articoli entity = findOnlyOneEqualTo("codArtStr", sku);
			inserisciInMappe(entity);
		}		
		return mappaPerCodiceArticolo.get(sku);
	}
	
	public Articoli trovaDaSKUVecchio(String sku) {
		Articoli entity = findFirstOneEqualTo("codArtOld", sku);
		inserisciInMappe(entity);
		return entity;
	}
	
	/**
	 * Restituisce il primo articolo con modello e taglia corrispondenti a quanto passato come argomento.
	 */
	public Articoli trovaDaModelloETaglia(String modello, String taglia) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("modello", modello));
		condizioni.add(new CondizioneWhere("taglia", taglia));
		List<Articoli> entities = findAll(condizioni, 1);
		Articoli entity = entities.isEmpty() ? null : entities.get(0);
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
	
	public Articoli inserisci(Articoli articolo) {
		Articoli entity = insert(articolo);
		inserisciInMappe(entity);
		return entity;
	}
	
	public Articoli aggiorna(Articoli articolo) {
		Articoli entity = update(articolo, articolo.getIdArticolo());
		inserisciInMappe(entity);
		return entity;
	}
	
	public Articoli elimina(Articoli articolo) {
		Articoli entity = delete(articolo.getIdArticolo());
		rimuoveDaMappe(entity);
		return entity;
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
		//oldEntity.setCodArtInt(entity.getCodArtInt());
		oldEntity.setCodArtOld(entity.getCodArtOld());
		oldEntity.setCodArtStr(entity.getCodArtStr());
		oldEntity.setColore(entity.getColore());
		oldEntity.setComposizione(entity.getComposizione());
		oldEntity.setDescAggiuntiva(entity.getDescAggiuntiva());
		oldEntity.setDescrizione(entity.getDescrizione());
		//oldEntity.setIdUniArticolo(entity.getIdUniArticolo()); //Non va mai aggiornato, non ho trovato un solo caso in cui si possa o debba fare. Il resto delle tabelle non ha FK e non si aggiorna di conseguenza.
		oldEntity.setLinea(entity.getLinea());
		oldEntity.setMadeIn(entity.getMadeIn());
		oldEntity.setModello(entity.getModello());
		oldEntity.setNote(entity.getNote());
		oldEntity.setStagione(entity.getStagione());
		oldEntity.setTaglia(entity.getTaglia());
		oldEntity.setTipoCassa(entity.getTipoCassa());
		oldEntity.setValVen(entity.getValVen());
		oldEntity.setCassa(entity.getCassa());
	}

	public List<Articoli> trova(String sku, String modello, String stagione, String descrizione, int maxResults) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		if (sku != null && !sku.isEmpty())
			condizioni.add(new CondizioneWhere("codArtStr", sku, Operatore.LIKE, Condizione.OR));
		if (modello != null && !modello.isEmpty())
			condizioni.add(new CondizioneWhere("modello", modello, Operatore.LIKE, Condizione.OR));
		if (stagione != null && !stagione.isEmpty())
			condizioni.add(new CondizioneWhere("stagione", stagione));
		if (descrizione != null && !descrizione.isEmpty())
			condizioni.add(new CondizioneWhere("descrizione", descrizione, Operatore.LIKE, Condizione.OR));
		List<Articoli> entities = findAll(condizioni, maxResults);
		return entities;
	}

	public List<Articoli> trovaDaUltimaModifica(Date ultimaModifica) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("dataModifica", ultimaModifica, Operatore.GREATER, Condizione.AND));
		List<Articoli> entities = findAll(condizioni);
        return entities;
	}

}

package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.dao.CondizioneWhere.Condizione;
import it.ltc.database.dao.CondizioneWhere.Operatore;
import it.ltc.database.model.legacy.RighiOrdine;
import it.ltc.database.model.legacy.model.TestataOrdiniTotali;

public class RighiOrdineDao extends CRUDDao<RighiOrdine> {
	
	private static final Logger logger = Logger.getLogger("RighiOrdineDao");

	public RighiOrdineDao(String persistenceUnit) {
		super(persistenceUnit, RighiOrdine.class);
	}
	
	public RighiOrdine inserisci(RighiOrdine riga) {
		RighiOrdine entity = insert(riga);
		return entity;
	}
	
	public RighiOrdine aggiorna(RighiOrdine riga) {
		RighiOrdine entity = update(riga, riga.getIdRigoOrdine());
		return entity;
	}
	
	public RighiOrdine elimina(RighiOrdine riga) {
		RighiOrdine entity = delete(riga.getIdRigoOrdine());
		return entity;
	}
	
	public RighiOrdine trovaDaID(int id) {
		RighiOrdine entity = findByID(id);
		return entity;
	}
	
	/**
	 * Controlla se un certo prodotto è presente o meno fra gli ordini esistenti.
	 * @param sku il codice articolo del prodotto così come definito dal cliente.
	 * @return true se è presente, false altrimenti.
	 */
	public boolean isProdottoPresente(String sku) {
		RighiOrdine entity = findOnlyOneEqualTo("codiceArticolo", sku);
        boolean presente = entity != null;
		return presente;
	}
	
	/**
	 * Restituisce tutte le righe appartenenti all'ordine specificato. 
	 * @param idOrdine l'ID dell'ordine (IdTestaSped su TestataOrdini, idTestataOrdine su RighiOrdine)
	 * @return La lista di tutte le righe d'ordine.
	 */
	public List<RighiOrdine> trovaRigheDaIDOrdine(int idOrdine) {
		List<RighiOrdine> entities = findAllEqualTo("idTestataOrdine", idOrdine);
		return entities;
	}
	
	public List<RighiOrdine> trovaRigheSenzaInfoProdotto() {
		List<RighiOrdine> entities = findAllEqualTo("idArticolo", 0);
		return entities;
	}

	@Override
	protected void updateValues(RighiOrdine oldEntity, RighiOrdine entity) {
		
		oldEntity.setKeyUbiPre(entity.getKeyUbiPre());
		oldEntity.setUbicazione(entity.getUbicazione());
		oldEntity.setArea(entity.getArea());
		oldEntity.setCorsia(entity.getCorsia());
		oldEntity.setBox(entity.getBox());
		oldEntity.setPiano(entity.getPiano());
		oldEntity.setScaffale(entity.getScaffale());
		
		oldEntity.setBarraEAN(entity.getBarraEAN());
		oldEntity.setBarraUPC(entity.getBarraUPC());
		oldEntity.setCodBarre(entity.getCodBarre());
		oldEntity.setCodiceArticolo(entity.getCodiceArticolo());
		oldEntity.setColore(entity.getColore());
		oldEntity.setComposizione(entity.getComposizione());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setTaglia(entity.getTaglia());
		oldEntity.setIdUnicoArt(entity.getIdUnicoArt());
		oldEntity.setIdArticolo(entity.getIdArticolo());
		
		oldEntity.setDataOrdine(entity.getDataOrdine());
		oldEntity.setIdDestina(entity.getIdDestina());
		oldEntity.setIdTestataOrdine(entity.getIdTestataOrdine());
		oldEntity.setNrLista(entity.getNrLista());
		oldEntity.setNrOrdine(entity.getNrOrdine());
		oldEntity.setTipoord(entity.getTipoord());	
		oldEntity.setPONumber(entity.getPONumber());
		
		oldEntity.setNrcollo(entity.getNrcollo());
		oldEntity.setMagazzino(entity.getMagazzino());	
		oldEntity.setNrRigo(entity.getNrRigo());
		oldEntity.setNoteCliente(entity.getNoteCliente());
		
		oldEntity.setQtaAssegnata(entity.getQtaAssegnata());
		oldEntity.setQtadaubicare(entity.getQtadaubicare());
		oldEntity.setQtaImballata(entity.getQtaImballata());
		oldEntity.setQtaSpedizione(entity.getQtaSpedizione());	
	}

	public List<RighiOrdine> trovaRigheDaOrdineENumeroRiga(int idTestataOrdine, int nrRigo) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("idTestataOrdine", idTestataOrdine));
		condizioni.add(new CondizioneWhere("nrRigo", nrRigo));
		List<RighiOrdine> entities = findAll(condizioni);
		return entities;
	}

	public List<RighiOrdine> trovaRigheDaOrdineEProdotto(int idTestataOrdine, String idUnicoArt) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("idTestataOrdine", idTestataOrdine));
		condizioni.add(new CondizioneWhere("idUnicoArt", idUnicoArt));
		List<RighiOrdine> entities = findAll(condizioni);
		return entities;
	}
	
	/**
	 * A partire dall'ordine passato nell'argomento restituisce tutte le righe che non sono state ancora completamente assegnate.<br>
	 * Condizioni per la selezione: Quantità da ubicare > 0.
	 * @param testata
	 * @return
	 */
	public List<RighiOrdine> trovaRigheDaAssegnare(int idOrdine) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("idTestataOrdine", idOrdine));
		condizioni.add(new CondizioneWhere("qtadaubicare", 0, Operatore.GREATER, Condizione.AND));
		List<RighiOrdine> entities = findAll(condizioni);
		return entities;
	}
	
	public TestataOrdiniTotali calcolaTotali(int idOrdine) {
		TestataOrdiniTotali totale;
		EntityManager em = getManager();
		try {
			totale = em.createNamedQuery("RighiOrdine.totaliPerOrdine", TestataOrdiniTotali.class).setParameter("ordine", idOrdine).getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			totale = null;
		} finally {
			em.close();
		}
		return totale;
	}

}

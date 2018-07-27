package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.RighiOrdine;

public class RighiOrdineDao extends CRUDDao<RighiOrdine> {

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

}

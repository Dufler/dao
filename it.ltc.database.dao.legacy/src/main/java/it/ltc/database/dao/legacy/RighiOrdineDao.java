package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.RighiOrdine;

public class RighiOrdineDao extends CRUDDao<RighiOrdine> {

	public RighiOrdineDao(String persistenceUnit) {
		super(persistenceUnit, RighiOrdine.class);
	}
	
	/**
	 * Controlla se un certo prodotto è presente o meno fra gli ordini esistenti.
	 * @param sku il codice articolo del prodotto così come definito dal cliente.
	 * @return true se è presente, false altrimenti.
	 */
	public boolean isProdottoPresente(String sku) {
		RighiOrdine entity = findFirstOneEqualTo("codiceArticolo", sku);
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
		// TODO Auto-generated method stub
		
	}

}

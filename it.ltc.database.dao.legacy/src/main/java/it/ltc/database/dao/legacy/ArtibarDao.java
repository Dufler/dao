package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ArtiBar;

public class ArtibarDao extends CRUDDao<ArtiBar> {

	public ArtibarDao(String persistenceUnit) {
		super(persistenceUnit, ArtiBar.class);
	}
	
	public ArtiBar trovaDaSKU(String sku) {
        ArtiBar articolo = findFirstOneEqualTo("codiceArticolo", sku); //E' possibile che c'è ne sia più di uno.
		return articolo;
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

package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Nazioni;

public class NazioniDao extends CRUDDao<Nazioni> {

	public NazioniDao(String persistenceUnit) {
		super(persistenceUnit, Nazioni.class);
	}
	
	public Nazioni trovaDaCodiceISO(String iso) {
		Nazioni entity = findByID(iso);
		return entity;
	}
	
	public Nazioni trovaDaNome(String nome) {
		Nazioni entity = findFirstOneEqualTo("descrizione", nome);
		return entity;
	}
	
	public Nazioni inserisci(Nazioni nazione) {
		Nazioni entity = insert(nazione);
		return entity;
	}
	
	public Nazioni aggiorna(Nazioni nazione) {
		Nazioni entity = update(nazione, nazione.getCodIso());
		return entity;
	}
	
	public Nazioni elimina(Nazioni nazione) {
		Nazioni entity = delete(nazione.getCodIso());
		return entity;
	}

	@Override
	protected void updateValues(Nazioni oldEntity, Nazioni entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setMembro(entity.getMembro());
	}

}

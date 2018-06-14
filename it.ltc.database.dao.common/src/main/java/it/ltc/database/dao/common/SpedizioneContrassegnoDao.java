package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.SpedizioneContrassegno;

public class SpedizioneContrassegnoDao extends CRUDDao<SpedizioneContrassegno> {

	public SpedizioneContrassegnoDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public SpedizioneContrassegnoDao(String persistenceUnit) {
		super(persistenceUnit, SpedizioneContrassegno.class);
	}
	
	public SpedizioneContrassegno trovaDaID(int id) {
		SpedizioneContrassegno entity = findByID(id);
		return entity;
	}
	
	public SpedizioneContrassegno inserisci(SpedizioneContrassegno contrassegno) {
		SpedizioneContrassegno entity = insert(contrassegno);
		return entity;
	}
	
	public SpedizioneContrassegno aggiorna(SpedizioneContrassegno contrassegno) {
		SpedizioneContrassegno entity = update(contrassegno, contrassegno.getIdSpedizione());
		return entity;
	}
	
	public SpedizioneContrassegno elimina(SpedizioneContrassegno contrassegno) {
		SpedizioneContrassegno entity = delete(contrassegno.getIdSpedizione());
		return entity;
	}

	@Override
	protected void updateValues(SpedizioneContrassegno oldEntity, SpedizioneContrassegno entity) {
		oldEntity.setAnnullato(entity.getAnnullato());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setValore(entity.getValore());
		oldEntity.setValuta(entity.getValuta());
	}

}

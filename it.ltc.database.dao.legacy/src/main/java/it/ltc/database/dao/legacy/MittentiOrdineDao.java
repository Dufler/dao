package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.MittentiOrdine;

public class MittentiOrdineDao extends CRUDDao<MittentiOrdine> {

	public MittentiOrdineDao(String persistenceUnit) {
		super(persistenceUnit, MittentiOrdine.class);
	}
	
	public MittentiOrdine trovaDaID(int id) {
		MittentiOrdine entity = findByID(id);
		return entity;
	}
	
	public MittentiOrdine trovaIndirizzo(String ragioneSociale, String cap, String indirizzo, String localita, String nazione) {
		//Impongo le condizioni di ricerca
		List<CondizioneWhere> condizioni = new LinkedList<>();
		if (ragioneSociale != null && !ragioneSociale.isEmpty())
			condizioni.add(new CondizioneWhere("ragioneSociale", ragioneSociale));
		if (cap != null && !cap.isEmpty())
			condizioni.add(new CondizioneWhere("cap", cap));
		if (indirizzo != null && !indirizzo.isEmpty())
			condizioni.add(new CondizioneWhere("indirizzo", indirizzo));
		if (localita != null && !localita.isEmpty())
			condizioni.add(new CondizioneWhere("localita", localita));
		if (nazione != null && !nazione.isEmpty())
			condizioni.add(new CondizioneWhere("nazione", nazione));
		List<MittentiOrdine> corrispondenze = findAll(condizioni, 1);
		MittentiOrdine entity = corrispondenze.isEmpty() ? null : corrispondenze.get(0);
		return entity;
	}
	
	public MittentiOrdine inserisci(MittentiOrdine mittente) {
		MittentiOrdine entity = insert(mittente);
		return entity;
	}
	
	public MittentiOrdine aggiorna(MittentiOrdine mittente) {
		MittentiOrdine entity = update(mittente, mittente.getIdMittente());
		return entity;
	}
	
	public MittentiOrdine elimina(MittentiOrdine mittente) {
		MittentiOrdine entity = delete(mittente.getIdMittente());
		return entity;
	}

	@Override
	protected void updateValues(MittentiOrdine oldEntity, MittentiOrdine entity) {
		oldEntity.setCap(entity.getCap());
		oldEntity.setEmail(entity.getEmail());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setLocalita(entity.getLocalita());
		oldEntity.setNazione(entity.getNazione());
		oldEntity.setProvincia(entity.getProvincia());
		oldEntity.setRagioneSociale(entity.getRagioneSociale());
		oldEntity.setTelefono(entity.getTelefono());
	}

}

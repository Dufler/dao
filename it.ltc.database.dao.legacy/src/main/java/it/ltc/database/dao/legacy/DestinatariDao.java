package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.Destinatari;

public class DestinatariDao extends CRUDDao<Destinatari> {

	public DestinatariDao(String persistenceUnit) {
		super(persistenceUnit, Destinatari.class);
	}
	
	public Destinatari inserisci(Destinatari destinatario) {
		Destinatari entity = insert(destinatario);
		return entity;
	}
	
	public Destinatari aggiorna(Destinatari destinatario) {
		Destinatari entity = update(destinatario, destinatario.getIdDestina());
		return entity;
	}
	
	public Destinatari elimina(Destinatari destinatario) {
		Destinatari entity = delete(destinatario.getIdDestina());
		return entity;
	}
	
	public Destinatari trovaDaID(int id) {
		Destinatari entity = findByID(id);
		return entity;
	}
	
	public Destinatari trovaDaCodice(String codice) {
		Destinatari entity = findOnlyOneEqualTo("codDestina", codice);
		return entity;
	}
	
	public Destinatari trovaIndirizzo(String ragioneSociale, String cap, String indirizzo, String localita, String nazione) {
		//Impongo le condizioni di ricerca
		List<CondizioneWhere> condizioni = new LinkedList<>();
		if (ragioneSociale != null && !ragioneSociale.isEmpty())
			condizioni.add(new CondizioneWhere("ragSoc1", ragioneSociale));
		if (cap != null && !cap.isEmpty())
			condizioni.add(new CondizioneWhere("cap", cap));
		if (indirizzo != null && !indirizzo.isEmpty())
			condizioni.add(new CondizioneWhere("indirizzo", indirizzo));
		if (localita != null && !localita.isEmpty())
			condizioni.add(new CondizioneWhere("localita", localita));
		if (nazione != null && !nazione.isEmpty())
			condizioni.add(new CondizioneWhere("codIso", nazione));
		List<Destinatari> corrispondenze = findAll(condizioni, 1);
		Destinatari entity = corrispondenze.isEmpty() ? null : corrispondenze.get(0);
		return entity;
	}

	@Override
	protected void updateValues(Destinatari oldEntity, Destinatari entity) {
		oldEntity.setCap(entity.getCap());
		oldEntity.setCodContabile(entity.getCodContabile());
		oldEntity.setCodDestina(entity.getCodDestina());
		oldEntity.setCodIso(entity.getCodIso());
		oldEntity.setCodNaz(entity.getCodNaz());
		oldEntity.setEmail(entity.getEmail());
		oldEntity.setFax(entity.getFax());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setLocalita(entity.getLocalita());
		oldEntity.setNazione(entity.getNazione());
		oldEntity.setProvincia(entity.getProvincia());
		oldEntity.setRagSoc1(entity.getRagSoc1());
		oldEntity.setRagSoc2(entity.getRagSoc2());
		oldEntity.setTel(entity.getTel());
		oldEntity.setTipoDestina(entity.getTipoDestina());
	}

}

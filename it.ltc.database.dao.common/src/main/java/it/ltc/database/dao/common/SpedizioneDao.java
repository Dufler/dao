package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Spedizione;

public class SpedizioneDao extends CRUDDao<Spedizione> {

	public SpedizioneDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public SpedizioneDao(String persistenceUnit) {
		super(persistenceUnit, Spedizione.class);
	}
	
	public Spedizione trovaDaID(int id) {
		Spedizione entity = findByID(id);
		return entity;
	}
	
	public Spedizione trovaDaLetteraDiVettura(String letteraDiVettura) {
		Spedizione entity = findOnlyOneEqualTo("letteraDiVettura", letteraDiVettura);
		return entity;
	}
	
	public Spedizione inserisci(Spedizione spedizione) {
		Spedizione entity = insert(spedizione);
		return entity;
	}
	
	public Spedizione aggiorna(Spedizione spedizione) {
		Spedizione entity = update(spedizione, spedizione.getId());
		return entity;
	}
	
	public Spedizione elimina(Spedizione spedizione) {
		Spedizione entity = delete(spedizione.getId());
		return entity;
	}

	@Override
	protected void updateValues(Spedizione oldEntity, Spedizione entity) {
		oldEntity.setAssicurazione(entity.getAssicurazione());
		oldEntity.setCodiceCliente(entity.getCodiceCliente());
		oldEntity.setColli(entity.getColli());
		oldEntity.setContrassegno(entity.getContrassegno());
		oldEntity.setCosto(entity.getCosto());
		oldEntity.setDataPartenza(entity.getDataPartenza());
		oldEntity.setDatiCompleti(entity.getDatiCompleti());
		oldEntity.setFatturazione(entity.getFatturazione());
		oldEntity.setGiacenza(entity.getGiacenza());
		oldEntity.setIdCommessa(entity.getIdCommessa());
		oldEntity.setIdCorriere(entity.getIdCorriere());
		oldEntity.setIdDocumento(entity.getIdDocumento());
		oldEntity.setIndirizzoDestinazione(entity.getIndirizzoDestinazione());
		oldEntity.setIndirizzoPartenza(entity.getIndirizzoPartenza());
		oldEntity.setInRitardo(entity.getInRitardo());
		oldEntity.setLetteraDiVettura(entity.getLetteraDiVettura());
		oldEntity.setNote(entity.getNote());
		oldEntity.setParticolarita(entity.getParticolarita());
		oldEntity.setPeso(entity.getPeso());
		oldEntity.setPezzi(entity.getPezzi());
		oldEntity.setRagioneSocialeDestinatario(entity.getRagioneSocialeDestinatario());
		oldEntity.setRicavo(entity.getRicavo());
		oldEntity.setRiferimentoCliente(entity.getRiferimentoCliente());
		oldEntity.setRiferimentoMittente(entity.getRiferimentoMittente());
		oldEntity.setServizio(entity.getServizio());
		oldEntity.setStato(entity.getStato());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setValoreMerceDichiarato(entity.getValoreMerceDichiarato());
		oldEntity.setVolume(entity.getVolume());
	}

}

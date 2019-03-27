package it.ltc.database.dao.common;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.Azienda;

public class AziendaDao extends CRUDDao<Azienda> {

	public AziendaDao(String persistenceUnit) {
		super(persistenceUnit, Azienda.class);
	}
	
	public Azienda trovaDaID(int id) {
		Azienda entity = findByID(id);
		return entity;
	}
	
	public Azienda trovaDaRagioneSociale(String ragioneSociale) {
		Azienda entity = findFirstOneEqualTo("ragioneSociale", ragioneSociale);
		return entity;
	}
	
	public Azienda trovaDaPIVA(String piva) {
		Azienda entity = findFirstOneEqualTo("partitaIva", piva);
		return entity;
	}
	
	public Azienda inserisci(Azienda azienda) {
		Azienda entity = insert(azienda);
		return entity;
	}
	
	public Azienda aggiorna(Azienda azienda) {
		Azienda entity = update(azienda, azienda.getId());
		return entity;
	}
	
	public Azienda elimina(Azienda azienda) {
		Azienda entity = delete(azienda.getId());
		return entity;
	}
	
	@Override
	protected void updateValues(Azienda oldEntity, Azienda entity) {
		oldEntity.setAppetibile(entity.getAppetibile());
		oldEntity.setEmail(entity.getEmail());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setInTrattiva(entity.getInTrattiva());
		oldEntity.setPartitaIva(entity.getPartitaIva());
		oldEntity.setRagioneSociale(entity.getRagioneSociale());
		oldEntity.setSitoWeb(entity.getSitoWeb());
		oldEntity.setTelefono(entity.getTelefono());
		oldEntity.setTipoLogistica(entity.getTipoLogistica());
		oldEntity.setValutazione(entity.getValutazione());
		oldEntity.setDescrizione(entity.getDescrizione());
	}

}

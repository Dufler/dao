package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Fornitori;

public class FornitoreDao extends CRUDDao<Fornitori> {
	
	public enum TipoFornitori { CARICO, RESO, INVENTARIO, INTERNO }

	public FornitoreDao(String persistenceUnit) {
		super(persistenceUnit, Fornitori.class);
	}
	
	public Fornitori trovaPerID(int idFornitore) {
		Fornitori entity = findByID(idFornitore);
		return entity;
	}
	
	public Fornitori trovaDaCodice(String codiceFornitore) {
        Fornitori fornitore = findFirstOneEqualTo("codiceFornitore", codiceFornitore);
		return fornitore;
	}
	
	public Fornitori trovaDaTipo(TipoFornitori tipo) {
		if (tipo == null) throw new RuntimeException("E' necessario specificare un tipo di fornitore.");
        Fornitori fornitore = findFirstOneEqualTo("tipoDocumento", tipo.name());
		return fornitore;
	}
	
	public List<Fornitori> trovaTutte() {
		List<Fornitori> entities = findAll();
		return entities;
	}
	
	public Fornitori inserisci(Fornitori fornitore) {
		Fornitori entity = insert(fornitore);
		return entity;
	}
	
	public Fornitori aggiorna(Fornitori fornitore) {
		Fornitori entity = update(fornitore, fornitore.getIdFornitore());
		return entity;
	}
	
	public Fornitori elimina(Fornitori fornitore) {
		Fornitori entity = delete(fornitore.getIdFornitore());
		return entity;
	}
	
	@Override
	protected void updateValues(Fornitori oldEntity, Fornitori entity) {
		oldEntity.setRagSoc(entity.getRagSoc());
		oldEntity.setCap(entity.getCap());
		oldEntity.setCitta(entity.getCitta());
		oldEntity.setCodnaz(entity.getCodnaz());
		oldEntity.setProv(entity.getProv());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setNote(entity.getNote());
		oldEntity.setTipoDocumento(entity.getTipoDocumento());
		//Opzionali, se non indicati lascio quello che ho gia'.
		String email = entity.getEMail();
		if (email != null && !email.isEmpty())
			oldEntity.setEMail(email);
		String telefono = entity.getTel();
		if (telefono != null && !telefono.isEmpty()) {
			oldEntity.setTel(telefono);
			oldEntity.setFax(telefono);
		}
	}

}

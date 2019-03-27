package it.ltc.database.dao.common;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.centrale.Contatto;

public class ContattoDao extends CRUDDao<Contatto> {

	public ContattoDao(String persistenceUnit) {
		super(persistenceUnit, Contatto.class);
	}
	
	public Contatto trovaDaID(int id) {
		Contatto entity = findByID(id);
		return entity;
	}
	
	public Contatto trovaDaNomeCognome(String nome, String cognome) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("nome", nome));
		conditions.add(new CondizioneWhere("cognome", cognome));
		Contatto entity = findOne(conditions);
		return entity;
	}
	
	public Contatto inserisci(Contatto contatto) {
		Contatto entity = insert(contatto);
		return entity;
	}
	
	public Contatto aggiorna(Contatto contatto) {
		Contatto entity = update(contatto, contatto.getId());
		return entity;
	}
	
	public Contatto elimina(Contatto contatto) {
		Contatto entity = delete(contatto.getId());
		return entity;
	}

	@Override
	protected void updateValues(Contatto oldEntity, Contatto entity) {
		oldEntity.setCognome(entity.getCognome());
		oldEntity.setNome(entity.getNome());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setIndirizzo(entity.getIndirizzo());
		oldEntity.setRuolo(entity.getRuolo());
	}

}

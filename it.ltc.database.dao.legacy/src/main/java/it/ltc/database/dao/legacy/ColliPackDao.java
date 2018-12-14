package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.ColliPack;

public class ColliPackDao extends CRUDDao<ColliPack> {

	public ColliPackDao(String persistenceUnit) {
		super(persistenceUnit, ColliPack.class);
	}

	@Override
	protected void updateValues(ColliPack oldEntity, ColliPack entity) {
		oldEntity.setCodArtStr(entity.getCodArtStr());
		oldEntity.setCodiceArticolo(entity.getCodiceArticolo());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setFlagimp(entity.getFlagimp());
		oldEntity.setFlagtc(entity.getFlagtc());
		oldEntity.setIdPakiarticolo(entity.getIdPakiarticolo());
		oldEntity.setIdTestaPaki(entity.getIdTestaPaki());
		oldEntity.setMagazzino(entity.getMagazzino());
		oldEntity.setNrIdColloPk(entity.getNrIdColloPk());
		oldEntity.setOperatore(entity.getOperatore());
		oldEntity.setQta(entity.getQta());
		oldEntity.setQtaimpegnata(entity.getQtaimpegnata());
		oldEntity.setTaglia(entity.getTaglia());
	}
	
	public ColliPack aggiorna(ColliPack collipack) {
		ColliPack entity = update(collipack, collipack.getIdColliPack());
		return entity;
	}
	
	public List<ColliPack> inserisci(List<ColliPack> collipacks) {
		List<ColliPack> entities = insert(collipacks);
		return entities;
	}
	
	public List<ColliPack> elimina(List<ColliPack> entities) {
		List<Object> keys = new LinkedList<>();
		for (ColliPack entity : entities)
			keys.add(entity.getIdColliPack());
		List<ColliPack> removedEntities = delete(keys);
		return removedEntities;
	}
	
	public ColliPack trovaDaID(int id) {
		ColliPack entity = findByID(id);
		return entity;
	}
	
	public List<ColliPack> trovaProdottiNelCarico(int idCarico) {
		List<ColliPack> entities = findAllEqualTo("idTestaPaki", idCarico);
		return entities;
	}
	
	public List<ColliPack> trovaProdottiNellaRigaDiCarico(int idRigaCarico) {
		List<ColliPack> entities = findAllEqualTo("idPakiarticolo", idRigaCarico);
		return entities;
	}
	
	public List<ColliPack> trovaProdottiNelCollo(String keyColloCar) {
		List<ColliPack> entities = findAllEqualTo("nrIdColloPk", keyColloCar);
		return entities;
	}
	
	public boolean isProdottoPresenteInMagazzino(String sku) {
		ColliPack entity = findFirstOneEqualTo("codArtStr", sku);
        boolean presente = entity != null;
		return presente;
	}
	
	/**
	 * Seleziona tutti i ColliPack disponibili con il prodotto specificato nel magazzino specificato.
	 */
	public List<ColliPack> trovaProdottiDisponibiliInMagazzino(String idUnivocoArticolo, String magazzino) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("codiceArticolo", idUnivocoArticolo));
		condizioni.add(new CondizioneWhere("magazzino", magazzino));
		condizioni.add(new CondizioneWhere("flagimp", "N"));
		List<ColliPack> entities = findAll(condizioni);
		return entities;
	}


}

package it.ltc.database.dao.legacy;

import javax.persistence.EntityManager;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ColliCarico;

public class ColliCaricoDao extends CRUDDao<ColliCarico> {

	public ColliCaricoDao(String persistenceUnit) {
		super(persistenceUnit, ColliCarico.class);
	}
	
	public int getProgressivoNrCollo() {
		EntityManager em = getManager();
		int progressivo = getProgressivoNrCollo(em);
		return progressivo;
	}
	
	protected static synchronized int getProgressivoNrCollo(EntityManager em) {
		int progressivo;
		try {
			progressivo = em.createNamedQuery("ColliCarico.progressivoCollo", Integer.class).getSingleResult() + 1;
		} catch (Exception e) {
			progressivo = 1;
		} finally {
			em.close();
		}
		return progressivo;
	}

	@Override
	protected void updateValues(ColliCarico oldEntity, ColliCarico entity) {
		oldEntity.setAnno(entity.getAnno());
		oldEntity.setApertoSfuso(entity.getApertoSfuso());
		oldEntity.setBarcode(entity.getBarcode());
		oldEntity.setBloccato(entity.getBloccato());
		oldEntity.setCancellato(entity.getCancellato());
		oldEntity.setDataDistruzione(entity.getDataDistruzione());
		oldEntity.setDataRilImballo(entity.getDataRilImballo());
		oldEntity.setDataUbica(entity.getDataUbica());
		oldEntity.setDistrutto(entity.getDistrutto());
		oldEntity.setFlagtc(entity.getFlagtc());
		oldEntity.setId_Box(entity.getId_Box());
		oldEntity.setIdDocumento(entity.getIdDocumento());
		oldEntity.setKeyColloCar(entity.getKeyColloCar());
		oldEntity.setKeyUbicaCar(entity.getKeyUbicaCar());
		oldEntity.setMagazzino(entity.getMagazzino());
		oldEntity.setNote(entity.getNote());
		oldEntity.setNrCollo(entity.getNrCollo());
		oldEntity.setOpeUbica(entity.getOpeUbica());
		oldEntity.setOraDistruzione(entity.getOraDistruzione());
		oldEntity.setOraUbica(entity.getOraUbica());
		oldEntity.setRilevataImballo(entity.getRilevataImballo());
		oldEntity.setTipoCassa(entity.getTipoCassa());
		oldEntity.setUbicato(entity.getUbicato());
		oldEntity.setUteDistruzione(entity.getUteDistruzione());
		oldEntity.setUtente(entity.getUtente());
		oldEntity.setUtenteMod(entity.getUtenteMod());
		oldEntity.setUtenteUbica(entity.getUtenteUbica());
	}

	public ColliCarico trovaDaID(int id) {
		ColliCarico entity = findByID(id);
		return entity;
	}
	
	public ColliCarico inserisci(ColliCarico collo) {
		ColliCarico entity = insert(collo);
		return entity;
	}
	
	public ColliCarico aggiorna(ColliCarico collo) {
		ColliCarico entity = update(collo, collo.getIdCollo());
		return entity;
	}
	
	public ColliCarico elimina(ColliCarico collo) {
		ColliCarico entity = delete(collo.getIdCollo());
		return entity;
	}

}

package it.ltc.database.dao.legacy;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.ColliCarico;

public class ColliCaricoDao extends CRUDDao<ColliCarico> {
	
	protected final HashMap<String, ColliCarico> mappaPerCodice;
	protected final HashMap<Integer, ColliCarico> mappaPerID;

	public ColliCaricoDao(String persistenceUnit) {
		super(persistenceUnit, ColliCarico.class);
		mappaPerCodice = new HashMap<>();
		mappaPerID = new HashMap<>();
	}
	
	protected void inserisciInMappe(ColliCarico entity) {
		if (entity != null) {
			mappaPerCodice.put(entity.getKeyColloCar(), entity);
			mappaPerID.put(entity.getIdCollo(), entity);
		}
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
//		oldEntity.setBloccato(entity.getBloccato());
		oldEntity.setCancellato(entity.getCancellato());
		oldEntity.setDataDistruzione(entity.getDataDistruzione());
//		oldEntity.setDataRilImballo(entity.getDataRilImballo());
		oldEntity.setDataUbica(entity.getDataUbica());
//		oldEntity.setDistrutto(entity.getDistrutto());
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
		if (!mappaPerID.containsKey(id)) {
			ColliCarico entity = findByID(id);
			inserisciInMappe(entity);
		}
		return mappaPerID.get(id);
	}
	
	public ColliCarico trovaDaCodice(String codice) {
		if (!mappaPerCodice.containsKey(codice)) {
			ColliCarico entity = findOnlyOneEqualTo("keyColloCar", codice);
			inserisciInMappe(entity);
		}		
		return mappaPerCodice.get(codice);
	}
	
	public List<ColliCarico> trovaColliNonCancellati() {
		List<ColliCarico> entities = findAllEqualTo("cancellato", "NO");
		return entities;
	}
	
	public List<ColliCarico> trovaColliNelCarico(int idCarico) {
		List<ColliCarico> entities = findAllEqualTo("idDocumento", idCarico);
		return entities;
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

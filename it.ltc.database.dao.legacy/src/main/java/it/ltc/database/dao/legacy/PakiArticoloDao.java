package it.ltc.database.dao.legacy;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.PakiArticolo;

public class PakiArticoloDao extends CRUDDao<PakiArticolo> {
	
	private static final Logger logger = Logger.getLogger("PakiArticoloDao");

	public PakiArticoloDao(String persistenceUnit) {
		super(persistenceUnit, PakiArticolo.class);
	}
	
	public boolean isProdottoPresenteInCarichi(String sku) {
		PakiArticolo entity = findOnlyOneEqualTo("codArtStr", sku);
        boolean presente = entity != null;
		return presente;
	}
	
	public PakiArticolo trovaDaID(int id) {
		PakiArticolo entity = findByID(id);
		return entity;
	}
	
	public List<PakiArticolo> trovaRigheDaCarico(int idCarico) {
		List<PakiArticolo> lista = findAllEqualTo("idPakiTesta", idCarico);
        return lista;
	}
	
	public List<PakiArticolo> trovaRigheDaCaricoEProdotto(int idCarico, String idUnivocoProdotto) {
		CondizioneWhere condizioneCarico = new CondizioneWhere("idPakiTesta", idCarico);
		CondizioneWhere condizioneProdotto = new CondizioneWhere("codUnicoArt", idUnivocoProdotto);
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(condizioneCarico);
		condizioni.add(condizioneProdotto);
		List<PakiArticolo> lista = findAll(condizioni);
        return lista;
	}
	
	public List<PakiArticolo> trovaRigheDaCaricoENumeroRiga(int idCarico, int numeroRiga) {
		CondizioneWhere condizioneCarico = new CondizioneWhere("idPakiTesta", idCarico);
		CondizioneWhere condizioneRiga = new CondizioneWhere("rigaPacki", numeroRiga);
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(condizioneCarico);
		condizioni.add(condizioneRiga);
		List<PakiArticolo> lista = findAll(condizioni);
        return lista;
	}
	
	public List<PakiArticolo> trovaRigheDaCollo(String collo) {
		List<PakiArticolo> lista = findAllEqualTo("barcodeCollo", collo);
        return lista;
	}
	
	public int calcolaTotaleDichiaratoPerCarico(int idCarico) {
		int totale;
		EntityManager em = getManager();
		try {
			totale = em.createNamedQuery("PakiArticolo.totaleDichiaratoPerCarico", Long.class).setParameter("carico", idCarico).getSingleResult().intValue();
		} catch (Exception e) {
			logger.error(e);
			totale = -1;
		} finally {
			em.close();
		}
		return totale;
	}
	
	public int calcolaTotaleVerificatoPerCarico(int idCarico) {
		int totale;
		EntityManager em = getManager();
		try {
			totale = em.createNamedQuery("PakiArticolo.totaleVerificatoPerCarico", Long.class).setParameter("carico", idCarico).getSingleResult().intValue();
		} catch (Exception e) {
			logger.error(e);
			totale = -1;
		} finally {
			em.close();
		}
		return totale;
	}
	
	public PakiArticolo inserisci(PakiArticolo riga) {
		PakiArticolo entity = insert(riga);
		return entity;
	}
	
	public PakiArticolo aggiorna(PakiArticolo riga) {
		PakiArticolo entity = update(riga, riga.getIdPakiArticolo());
		return entity;
	}
	
	public List<PakiArticolo> aggiorna(List<PakiArticolo> righe) {
		HashMap<Object, PakiArticolo> entities = new HashMap<>();
		for (PakiArticolo riga : righe)
			entities.put(riga.getIdPakiArticolo(), riga);
		List<PakiArticolo> updatedEntities = update(entities);
		return updatedEntities;
	}
	
	public PakiArticolo elimina(PakiArticolo riga) {
		PakiArticolo entity = delete(riga.getIdPakiArticolo());
		return entity;
	}

	@Override
	protected void updateValues(PakiArticolo oldEntity, PakiArticolo entity) {
		oldEntity.setBarcodeCollo(entity.getBarcodeCollo());
		oldEntity.setCodArtStr(entity.getCodArtStr());
		oldEntity.setCodBarre(entity.getCodBarre());
		oldEntity.setCodMotivo(entity.getCodMotivo());
		oldEntity.setCodUnicoArt(entity.getCodUnicoArt());
		oldEntity.setDataModifica(new Timestamp(new Date().getTime()));
		//oldEntity.setIdPakiTesta(entity.getIdPakiTesta());
		oldEntity.setKeyUbicaCar(entity.getKeyUbicaCar());
		oldEntity.setKeyUbicaPre(entity.getKeyUbicaPre());
		oldEntity.setMadeIn(entity.getMadeIn());
		oldEntity.setMagazzino(entity.getMagazzino());
		oldEntity.setMagazzinoltc(entity.getMagazzinoltc());
		oldEntity.setNrDispo(entity.getNrDispo());
		//oldEntity.setNrOrdineFor(entity.getNrOrdineFor());
		oldEntity.setQtaPaki(entity.getQtaPaki());
		oldEntity.setQtaPreDoc(entity.getQtaPreDoc());
		oldEntity.setQtaPrelevata(entity.getQtaPrelevata());
		oldEntity.setQtaVerificata(entity.getQtaVerificata());
		oldEntity.setRigaPacki(entity.getRigaPacki());
		oldEntity.setScelta(entity.getScelta());
		oldEntity.setUtente(entity.getUtente());
	}

}

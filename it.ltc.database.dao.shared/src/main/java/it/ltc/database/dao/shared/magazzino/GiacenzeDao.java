package it.ltc.database.dao.shared.magazzino;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import it.ltc.database.dao.Dao;
import it.ltc.database.dao.legacy.MagazzinoDao;
import it.ltc.database.model.legacy.Magazzini;
import it.ltc.model.shared.json.cliente.InfoProdotto;
import it.ltc.services.custom.exception.CustomException;

public class GiacenzeDao extends Dao {
	
	private static final Logger logger = Logger.getLogger(GiacenzeDao.class);
	
	protected final MagazzinoDao daoMagazzini;

	public GiacenzeDao(String persistenceUnit) {
		super(persistenceUnit);
		daoMagazzini = new MagazzinoDao(persistenceUnit);
	}
	
	protected String getQueryBase() {
		String query = "SELECT a.codArtStr AS codiceArticolo, m.disponibile as disponibilità, m.esistenza as giacenza, m.codMaga as magazzino FROM MagaSd m JOIN Articoli a ON m.idUniArticolo = a.idUniArticolo";
		return query;
	}
	
	protected String getQueryPerMagazzino(Magazzini magazzino) {
		String query = "SELECT a.codArtStr AS codiceArticolo, m.disponibile as disponibilità, m.esistenza as giacenza, m.codMaga as magazzino FROM MagaSd m JOIN Articoli a ON m.idUniArticolo = a.idUniArticolo WHERE m.codMaga = '" + magazzino.getCodiceMag() + "'";
		return query;
	}
	
	protected InfoProdotto getInfoProdotto(Object[] arrayInfo) {
		InfoProdotto info = new InfoProdotto();
		info.setCodiceArticolo((String) arrayInfo[0]);
		info.setDisponibilità((int) arrayInfo[1]);
		info.setGiacenza((int) arrayInfo[2]);
		info.setMagazzino((String) arrayInfo[3]);
		return info;
	}
	
	@SuppressWarnings("unchecked")
	public List<InfoProdotto> getDisponibilita() {
		logger.info("Ottengo la disponibilità generale");
		EntityManager em = getManager();
		List<Object[]> results = em.createNativeQuery(getQueryBase()).getResultList();
		logger.debug("Query completata.");
		em.close();
		List<InfoProdotto> listaInfo = new LinkedList<InfoProdotto>();
		for (Object[] result : results) {
			InfoProdotto info = getInfoProdotto(result);
			listaInfo.add(info);
		}
		return listaInfo;
	}
	
	public List<InfoProdotto> getDisponibilitaPerCodiceMagazzinoCliente(String codiceCliente) {
		Magazzini magazzino = daoMagazzini.trovaDaCodificaCliente(codiceCliente);
		if (magazzino == null)
			throw new CustomException("Il magazzino richiesto non esiste. (" + codiceCliente + ")");
		return getDisponibilita(magazzino);
	}
	
	public List<InfoProdotto> getDisponibilitaPerCodiceMagazzinoLTC(String codiceLTC) {
		Magazzini magazzino = daoMagazzini.trovaDaCodiceLTC(codiceLTC);
		if (magazzino == null)
			throw new CustomException("Il magazzino richiesto non esiste. (" + codiceLTC + ")");
		return getDisponibilita(magazzino);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfoProdotto> getDisponibilita(Magazzini magazzino) {
		logger.info("Ottengo la disponibilità per il magazzino " + magazzino.getCodiceMag());
		EntityManager em = getManager();
		List<Object[]> results = em.createNativeQuery(getQueryPerMagazzino(magazzino)).getResultList();
		logger.debug("Query completata.");
		em.close();
		List<InfoProdotto> listaInfo = new LinkedList<InfoProdotto>();
		for (Object[] result : results) {
			InfoProdotto info = getInfoProdotto(result);
			listaInfo.add(info);
		}
		return listaInfo;
	}

}

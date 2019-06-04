package it.ltc.database.dao.legacy.bundle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.bundle.Casse;
import it.ltc.database.model.legacy.bundle.CasseStandard;

public class CasseStandardDao extends CRUDDao<CasseStandard> {
	
	private static final Logger logger = Logger.getLogger(CasseStandardDao.class);

	public CasseStandardDao(String persistenceUnit) {
		super(persistenceUnit, CasseStandard.class);
	}
	
	public CasseStandard trovaDaID(int id) {
		CasseStandard entity = findByID(id);
		return entity;
	}
	
	public List<CasseStandard> trovaTutti() {
		List<CasseStandard> entities = findAll();
		return entities;
	}
	
	public CasseStandard inserisci(CasseStandard elementoCassa) {
		CasseStandard entity = insert(elementoCassa);
		return entity;
	}
	
	public CasseStandard aggiorna(CasseStandard elementoCassa) {
		CasseStandard entity = update(elementoCassa, elementoCassa.getId());
		return entity;
	}
	
	public CasseStandard elimina(CasseStandard elementoCassa) {
		CasseStandard entity = delete(elementoCassa.getId());
		return entity;
	}
	
	public List<CasseStandard> trovaDaTipoCassa(String tipoCassa) {
		List<CasseStandard> entities = findAllEqualTo("codiceCassa", tipoCassa);
		return entities;
	}

	@Override
	protected void updateValues(CasseStandard oldEntity, CasseStandard entity) {
		oldEntity.setCodiceCassa(entity.getCodiceCassa());
		oldEntity.setQuantitaProdotto(entity.getQuantitaProdotto());
		oldEntity.setTaglia(entity.getTaglia());
	}
	
	public boolean salvaCassa(List<CasseStandard> composizioneCassa) {
		boolean salva;
		//controllo che gli elementi comuni siano uguali per tutti.
		checkComposizioneCassa(composizioneCassa);
		//Reupero gli elementi esistenti
		CasseStandard cassa = composizioneCassa.get(0);
		List<CasseStandard> esistenti = trovaDaTipoCassa(cassa.getCodiceCassa());
		List<CasseStandard> daAggiornare = new LinkedList<>();
		List<CasseStandard> daInserire = new LinkedList<>();
		//Elimino gli elementi esistenti e inserisco i nuovi
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			for (CasseStandard elemento : composizioneCassa) {
				//Cerco fra quelli esistenti se esiste già
				boolean presente = false;
				Iterator<CasseStandard> i = esistenti.iterator();
				while (i.hasNext()) {
					CasseStandard esistente = i.next();
					if (esistente.getCodiceCassa().equals(elemento.getCodiceCassa()) && esistente.getTaglia().equals(elemento.getTaglia())) {
						elemento.setId(esistente.getId());
						daAggiornare.add(elemento);
						i.remove();
						presente = true;
						break;
					}
				}
				//Se non esiste ancora lo metto fra quelli da inserire
				if (!presente)
					daInserire.add(elemento);
			}
			//inserisco, aggiorno ed elimino i singoli elementi della cassa
			for (CasseStandard elemento : daInserire)
				em.persist(elemento);
			for (CasseStandard elemento : daAggiornare)
				em.merge(elemento);
			for (CasseStandard esistente : esistenti)
				em.remove(em.contains(esistente) ? esistente : em.find(CasseStandard.class, esistente.getId()));
			t.commit();
			salva = true;
		} catch (Exception e) {
			salva = false;
			logger.error(e.getMessage(), e);
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return salva;
	}
	
	public boolean eliminaCassa(String codiceCassa) {
		boolean elimina;
		List<CasseStandard> esistenti = trovaDaTipoCassa(codiceCassa);
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			for (CasseStandard esistente : esistenti) {
				em.remove(em.contains(esistente) ? esistente : em.find(Casse.class, esistente.getId()));
			}
			t.commit();
			elimina = true;
		} catch (Exception e) {
			elimina = false;
			logger.error(e.getMessage(), e);
			if (t != null && t.isActive())
				t.rollback();
		} finally {
			em.close();
		}
		return elimina;
	}
	
	protected void checkComposizioneCassa(List<CasseStandard> composizioneCassa) {
		//Controllo che abbia almeno un elemento
		if (composizioneCassa.isEmpty()) throw new RuntimeException("La cassa deve contenere almeno un'elemento.");
		//Prendo il primo elemento come esempio.
		CasseStandard cassa = composizioneCassa.get(0);
		String codice = cassa.getCodiceCassa();
		//Tutti gli elementi devono avere gli stessi valori di codice cassa.
		for (CasseStandard elemento : composizioneCassa) {
			if (!elemento.getCodiceCassa().equals(codice))
				throw new RuntimeException("Il codice di cassa indicato non è lo stesso per tutti gli elementi della cassa.");
			//La taglia deve esserci
			if (elemento.getTaglia() == null || elemento.getTaglia().isEmpty())
				throw new RuntimeException("La taglia deve essere presente in tutti gli elementi della cassa.");
			//La quantità del prodotto deve essere maggiore di 0
			if (elemento.getQuantitaProdotto() < 1)
				throw new RuntimeException("La quantità deve essere presente in tutti gli elementi della cassa.");
		}
	}

}

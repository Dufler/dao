package it.ltc.database.dao.legacy.bundle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.Articoli;
import it.ltc.database.model.legacy.bundle.Casse;

public class CasseDao extends CRUDDao<Casse> {
	
	private static final Logger logger = Logger.getLogger(CasseDao.class);

	public CasseDao(String persistenceUnit) {
		super(persistenceUnit, Casse.class);
	}
	
	public Casse inserisci(Casse cassa) {
		Casse entity = insert(cassa);
		return entity;
	}
	
	public Casse aggiorna(Casse cassa) {
		Casse entity = update(cassa, cassa.getId());
		return entity;
	}
	
	public Casse elimina(Casse cassa) {
		Casse entity = delete(cassa.getId());
		return entity;
	}
	
	public List<Casse> trovaTutti() {
		List<Casse> entitites = findAll();
		return entitites;
	}
	
	public Casse trovaDaID(int id) {
		Casse entity = findByID(id);
		return entity;
	}
	
	public boolean salvaCassa(List<Casse> composizioneCassa) {
		boolean salva;
		//controllo che gli elementi comuni siano uguali per tutti.
		checkComposizioneCassa(composizioneCassa);
		//Reupero gli elementi esistenti
		Casse cassa = composizioneCassa.get(0);
		int idCassa = cassa.getIdCassa();
		List<Casse> esistenti = trovaDaCassa(idCassa);
		List<Casse> daAggiornare = new LinkedList<>();
		List<Casse> daInserire = new LinkedList<>();
		//Elimino gli elementi esistenti e inserisco i nuovi
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			int totalePezzi = 0;
			for (Casse elemento : composizioneCassa) {
				//Cerco fra quelli esistenti se esiste già
				boolean presente = false;
				Iterator<Casse> i = esistenti.iterator();
				while (i.hasNext()) {
					Casse esistente = i.next();
					if (esistente.getIdCassa() == elemento.getIdCassa() && esistente.getIdProdotto() == elemento.getIdProdotto()) {
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
				//aumento il totale dei pezzi
				totalePezzi += elemento.getQuantitaProdotto();
			}
			//inserisco, aggiorno ed elimino i singoli elementi della cassa
			for (Casse elemento : daInserire)
				em.persist(elemento);
			for (Casse elemento : daAggiornare)
				em.merge(elemento);
			for (Casse esistente : esistenti)
				em.remove(em.contains(esistente) ? esistente : em.find(Casse.class, esistente.getId()));
			//Aggiorno le info sull'anagrafica base del prodotto.
			Articoli anagraficaCassa = em.find(Articoli.class, idCassa);
			anagraficaCassa.setCassa(cassa.getTipo());
			anagraficaCassa.setTipoCassa(cassa.getCodiceCassa());
			anagraficaCassa.setPezziCassa(totalePezzi);
			em.merge(anagraficaCassa);
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
	
	public boolean eliminaCassa(int idCassa) {
		boolean elimina;
		List<Casse> esistenti = trovaDaCassa(idCassa);
		EntityManager em = getManager();
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			for (Casse esistente : esistenti) {
				em.remove(em.contains(esistente) ? esistente : em.find(Casse.class, esistente.getId()));
			}
			//Aggiorno le info sull'anagrafica base del prodotto.
			Articoli anagraficaCassa = em.find(Articoli.class, idCassa);
			anagraficaCassa.setCassa("NO");
			anagraficaCassa.setTipoCassa(null);
			anagraficaCassa.setPezziCassa(1);
			em.merge(anagraficaCassa);
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
	
	protected void checkComposizioneCassa(List<Casse> composizioneCassa) {
		//Controllo che abbia almeno un elemento
		if (composizioneCassa.isEmpty()) throw new RuntimeException("La cassa deve contenere almeno un'elemento.");
		//Prendo il primo elemento come esempio.
		Casse cassa = composizioneCassa.get(0);
		String modello = cassa.getModello();
		String tipo = cassa.getTipo();
		String codice = cassa.getCodiceCassa();
		int idCassa = cassa.getIdCassa();
		//Tutti gli elementi devono avere gli stessi valori.
		for (Casse elemento : composizioneCassa) {
			if (!elemento.getModello().equals(modello))
				throw new RuntimeException("Il modello indicato non è lo stesso per tutti gli elementi della cassa.");
			if (!elemento.getTipo().equals(tipo))
				throw new RuntimeException("Il tipo di cassa indicato non è lo stesso per tutti gli elementi della cassa.");
			if (!elemento.getCodiceCassa().equals(codice))
				throw new RuntimeException("Il codice di cassa indicato non è lo stesso per tutti gli elementi della cassa.");
			if (elemento.getIdCassa() != idCassa)
				throw new RuntimeException("L'ID cassa indicato non è lo stesso per tutti gli elementi della cassa.");
			//La quantità del prodotto deve essere maggiore di 0
			if (elemento.getQuantitaProdotto() < 1)
				throw new RuntimeException("La quantità di prodotto indicata non è valida. (ID Prodotto: " + elemento.getIdProdotto() + ", quantità: " + elemento.getQuantitaProdotto() + ")");
		}
	}
	
	public Casse trovaDaCassaEProdotto(String idUnivocoCassa, String idUnivocoProdotto) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("idUnivocoCassa", idUnivocoCassa));
		conditions.add(new CondizioneWhere("idUnivocoProdotto", idUnivocoProdotto));
		Casse entity = findJustOne(conditions);
		return entity;
	}
	
	public Casse trovaDaCassaEProdotto(int idCassa, int idProdotto) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("idCassa", idCassa));
		conditions.add(new CondizioneWhere("idProdotto", idProdotto));
		Casse entity = findJustOne(conditions);
		return entity;
	}
	
	public List<Casse> trovaDaCassa(String idUnivocoCassa) {
		List<Casse> entitites = findAllEqualTo("idUnivocoCassa", idUnivocoCassa);
		return entitites;
	}
	
	public List<Casse> trovaDaCassa(int idCassa) {
		List<Casse> entitites = findAllEqualTo("idCassa", idCassa);
		return entitites;
	}

	@Override
	protected void updateValues(Casse oldEntity, Casse entity) {
		oldEntity.setQuantitaProdotto(entity.getQuantitaProdotto());
		oldEntity.setIdUnivocoCassa(entity.getIdUnivocoCassa());
		oldEntity.setIdUnivocoProdotto(entity.getIdUnivocoProdotto());
		oldEntity.setIdCassa(entity.getIdCassa());
		oldEntity.setIdProdotto(entity.getIdProdotto());
		oldEntity.setCodiceCassa(entity.getCodiceCassa());
		oldEntity.setModello(entity.getModello());
		oldEntity.setTipo(entity.getTipo());
	}

}

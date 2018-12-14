package it.ltc.database.dao.legacy;

import java.util.HashMap;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Nazioni;

public class NazioniDao extends CRUDDao<Nazioni> {
	
	protected final HashMap<String, Nazioni> mappaPerISO;
	protected final HashMap<String, Nazioni> mappaPerDescrizione;
	protected final HashMap<String, Nazioni> mappaPerCodificaCliente;

	public NazioniDao(String persistenceUnit) {
		super(persistenceUnit, Nazioni.class);
		mappaPerISO = new HashMap<>();
		mappaPerDescrizione = new HashMap<>();
		mappaPerCodificaCliente = new HashMap<>();
	}
	
	protected void aggiungiAllaMappe(Nazioni nazione) {
		if (nazione != null) {
			mappaPerISO.put(nazione.getCodIso(), nazione);
			mappaPerDescrizione.put(nazione.getDescrizione(), nazione);
			if (nazione.getCodificaCliente() != null && !nazione.getCodificaCliente().isEmpty())
				mappaPerCodificaCliente.put(nazione.getCodificaCliente(), nazione);
		}
	}
	
	protected void rimuoviDalleMappe(Nazioni nazione) {
		if (nazione != null) {
			mappaPerISO.remove(nazione.getCodIso());
			mappaPerDescrizione.remove(nazione.getDescrizione());
			if (nazione.getCodificaCliente() != null && !nazione.getCodificaCliente().isEmpty())
				mappaPerCodificaCliente.remove(nazione.getCodificaCliente());
		}
	}
	
	public Nazioni trovaDaCodiceISO(String iso) {
		if (!mappaPerISO.containsKey(iso)) {
			Nazioni entity = findByID(iso);
			aggiungiAllaMappe(entity);
		}		
		return mappaPerISO.get(iso);
	}
	
	public Nazioni trovaDaNome(String nome) {
		if (!mappaPerDescrizione.containsKey(nome)) {
			Nazioni entity = findFirstOneEqualTo("descrizione", nome);
			aggiungiAllaMappe(entity);
		}		
		return mappaPerDescrizione.get(nome);
	}
	
	public Nazioni trovaDaCodificaCliente(String codifica) {
		if (!mappaPerCodificaCliente.containsKey(codifica)) {
			Nazioni entity = findFirstOneEqualTo("codificaCliente", codifica);
			aggiungiAllaMappe(entity);
		}		
		return mappaPerCodificaCliente.get(codifica);
	}
	
	public Nazioni inserisci(Nazioni nazione) {
		Nazioni entity = insert(nazione);
		aggiungiAllaMappe(entity);
		return entity;
	}
	
	public Nazioni aggiorna(Nazioni nazione) {
		Nazioni entity = update(nazione, nazione.getCodIso());
		aggiungiAllaMappe(entity);
		return entity;
	}
	
	public Nazioni elimina(Nazioni nazione) {
		Nazioni entity = delete(nazione.getCodIso());
		rimuoviDalleMappe(entity);
		return entity;
	}

	@Override
	protected void updateValues(Nazioni oldEntity, Nazioni entity) {
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setMembro(entity.getMembro());
		oldEntity.setCodificaCliente(entity.getCodificaCliente());
	}

}

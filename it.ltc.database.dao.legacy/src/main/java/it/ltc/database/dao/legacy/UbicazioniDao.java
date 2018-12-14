package it.ltc.database.dao.legacy;

import java.util.HashMap;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Ubicazioni;

public class UbicazioniDao extends CRUDDao<Ubicazioni> {
	
	private final HashMap<Integer, Ubicazioni> mappaPerID;
	private final HashMap<String, Ubicazioni> mappaPerCodice;

	public UbicazioniDao(String persistenceUnit) {
		super(persistenceUnit, Ubicazioni.class);
		mappaPerID = new HashMap<>();
		mappaPerCodice = new HashMap<>();
	}
	
	private Ubicazioni aggiungiMappa(Ubicazioni ubicazione) {
		if (ubicazione != null) {
			mappaPerID.put(ubicazione.getIdUbicazioni(), ubicazione);
			mappaPerCodice.put(ubicazione.getKeyUbica(), ubicazione);
		}
		return ubicazione;
	}

	@Override
	protected void updateValues(Ubicazioni oldEntity, Ubicazioni entity) {
		oldEntity.setArea(entity.getArea());
		oldEntity.setBox(entity.getBox());
		oldEntity.setCorsia(entity.getCorsia());
		//oldEntity.setKeyUbica(entity.getKeyUbica());
		oldEntity.setMagazzino(entity.getMagazzino());
		oldEntity.setPiano(entity.getPiano());
		oldEntity.setScaffale(entity.getScaffale());
		oldEntity.setTipoUbica(entity.getTipoUbica());
	}
	
	public Ubicazioni trovaDaID(int id) {
		Ubicazioni entity = mappaPerID.containsKey(id) ? mappaPerID.get(id) : aggiungiMappa(findByID(id));
		return entity;
	}
	
	public Ubicazioni trovaDaCodice(String codice) {
		Ubicazioni entity = mappaPerCodice.containsKey(codice) ? mappaPerCodice.get(codice) : aggiungiMappa(findOnlyOneEqualTo("keyUbica", codice));
		return entity;
	}
	
	public Ubicazioni inserisci(Ubicazioni ubicazione) {
		Ubicazioni entity = insert(ubicazione);
		return entity;
	}
	
	public Ubicazioni aggiorna(Ubicazioni ubicazione) {
		Ubicazioni entity = update(ubicazione, ubicazione.getIdUbicazioni());
		return entity;
	}
	
	public Ubicazioni elimina(Ubicazioni ubicazione) {
		Ubicazioni entity = delete(ubicazione.getIdUbicazioni());
		return entity;
	}

}

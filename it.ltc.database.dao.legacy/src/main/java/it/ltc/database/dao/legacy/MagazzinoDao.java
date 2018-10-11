package it.ltc.database.dao.legacy;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.Magazzini;

public class MagazzinoDao extends ReadOnlyDao<Magazzini> {
	
	protected final HashMap<Integer, Magazzini> mappaPerID;
	protected final HashMap<String, Magazzini> mappaPerCodiceLTC;
	protected final HashMap<String, Magazzini> mappaPerCodiceCliente;
	
	public MagazzinoDao(String persistenceUnit) {
		super(persistenceUnit, Magazzini.class);
		mappaPerID = new HashMap<>();
		mappaPerCodiceLTC = new HashMap<>();
		mappaPerCodiceCliente = new HashMap<>();
	}
	
	protected void inserisciInMappe(Magazzini entity) {
		if (entity != null) {
			mappaPerID.put(entity.getIdMagazzino(), entity);
			mappaPerCodiceLTC.put(entity.getCodiceMag(), entity);
			mappaPerCodiceCliente.put(entity.getMagaCliente(), entity);
		}
	}

	public Magazzini trovaDaID(int id) {
		if (!mappaPerID.containsKey(id)) {
			Magazzini magazzino = findByID(id);
			inserisciInMappe(magazzino);
		}		
		return mappaPerID.get(id);
	}

	public Magazzini trovaDaCodiceLTC(String codice) {
		if (!mappaPerCodiceLTC.containsKey(codice)) {
			Magazzini magazzino = findOnlyOneEqualTo("codiceMag", codice);
			inserisciInMappe(magazzino);
		}        
		return mappaPerCodiceLTC.get(codice);
	}

	public Magazzini trovaDaCodificaCliente(String codifica) {
		if (!mappaPerCodiceCliente.containsKey(codifica)) {
			Magazzini magazzino = findOnlyOneEqualTo("magaCliente", codifica);
			inserisciInMappe(magazzino);
		}        
		return mappaPerCodiceCliente.get(codifica);
	}

	public List<Magazzini> trovaTutti() {
        List<Magazzini> list = findAll();
		return list;
	}

}

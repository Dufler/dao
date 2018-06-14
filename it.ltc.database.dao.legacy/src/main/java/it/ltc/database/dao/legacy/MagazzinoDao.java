package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.Magazzini;

public class MagazzinoDao extends ReadOnlyDao<Magazzini> {
	
	public MagazzinoDao(String persistenceUnit) {
		super(persistenceUnit, Magazzini.class);
	}

	public Magazzini trovaDaID(int idMagazzino) {
		Magazzini magazzino = findByID(idMagazzino);
		return magazzino;
	}

	public Magazzini trovaDaCodiceLTC(String codice) {
        Magazzini magazzino = findFirstOneEqualTo("codiceMag", codice);
		return magazzino;
	}

	public Magazzini trovaDaCodificaCliente(String codifica) {
        Magazzini magazzino = findFirstOneEqualTo("magaCliente", codifica);
		return magazzino;
	}

	public List<Magazzini> trovaTutti() {
        List<Magazzini> list = findAll();
		return list;
	}

}

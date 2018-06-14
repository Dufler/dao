package it.ltc.database.dao.common;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.centrale.VersioneTabella;

public class VersioneTabellaDao extends ReadOnlyDao<VersioneTabella> {

	public VersioneTabellaDao() {
		super(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME, VersioneTabella.class);
	}
	
	public VersioneTabella trovaDaCodice(String tabella) {
		VersioneTabella versione = findByID(tabella);
		return versione;
	}
	
	public List<VersioneTabella> trovaTutte() {
		List<VersioneTabella> entities = findAll();
		return entities;
	}

}

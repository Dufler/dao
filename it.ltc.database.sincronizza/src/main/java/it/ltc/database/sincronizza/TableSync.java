package it.ltc.database.sincronizza;

import java.util.HashMap;

import org.apache.log4j.Logger;

public abstract class TableSync<M, S> {

	private static final Logger logger = Logger.getLogger(TableSync.class);
	
	protected abstract HashMap<Object, M> getMasterEntities();
	
	protected abstract HashMap<Object, S> getSlaveEntities();
	
	protected abstract boolean updateSlave(M master, S slave);
	
	protected abstract boolean insertSlave(M master);
	
	protected abstract boolean deleteSlave(S slave);
	
	public void syncronize() {
		logger.info("Avvio la procedura di sincronizzazione.");
		HashMap<Object, M> masterEntities = getMasterEntities();
		logger.info("Sono state recuperate " + masterEntities.size() + " entities master.");
		HashMap<Object, S> slaveEntities = getSlaveEntities();
		logger.info("Sono state recuperate " + slaveEntities.size() + " entities slave.");
		//Per ogni oggetto presente nella tabella master cerco di aggiornarlo nella tabella slave se presente o inserirlo se non lo trovo.
		for (Object masterKey : masterEntities.keySet()) {
			M master = masterEntities.get(masterKey);
			logger.info("Cerco il corrispondente per " + masterKey);
			S slave = slaveEntities.get(masterKey);
			if (slave != null) {
				logger.info("Procedo all'aggiornamento di " + slave);
				boolean update = updateSlave(master, slave);
				slaveEntities.remove(masterKey); //Lo rimuovo dalla mappa dopo aver aggiornato.
				if (!update)
					logger.error("Impossibile aggiornare " + slave);
			} else {
				logger.info("Procedo all'inserimento di " + master + " nella tabella slave.");
				boolean insert = insertSlave(master);
				if (!insert)
					logger.error("Impossibile inserire " + master + " nella tabella slave.");
			}
		}
		//Elimino gli oggetti slave che non sono presenti sulla tabella master.
		for (S slaveToDelete : slaveEntities.values()) {
			logger.info("Procedo all'eliminazione di " + slaveToDelete);
			boolean delete = deleteSlave(slaveToDelete);
			if (!delete)
				logger.error("Impossibile eliminare " + slaveToDelete + " nella tabella slave.");
		}
	}

}

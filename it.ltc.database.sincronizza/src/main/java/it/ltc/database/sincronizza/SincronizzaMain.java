package it.ltc.database.sincronizza;

import it.ltc.database.sincronizza.mysql.CommesseSync;
import it.ltc.database.sincronizza.mysql.FeatureSync;
import it.ltc.database.sincronizza.mysql.PermessiSync;
import it.ltc.database.sincronizza.mysql.SedeSync;
import it.ltc.database.sincronizza.mysql.UtenteFeatureSync;
import it.ltc.database.sincronizza.mysql.UtentiCommesseSync;
import it.ltc.database.sincronizza.mysql.UtentiPermessiSync;
import it.ltc.database.sincronizza.mysql.UtentiSediSync;
import it.ltc.database.sincronizza.mysql.UtentiSync;

public class SincronizzaMain {
	
	public static final String persistenceUnitMaster = "produzione";
	public static final String persistenceUnitSlave = "produzione-utenti-po";

	public static void main(String[] args) {
		FeatureSync fs = new FeatureSync(persistenceUnitMaster, persistenceUnitSlave);
		fs.syncronize();
		SedeSync ss = new SedeSync(persistenceUnitMaster, persistenceUnitSlave);
		ss.syncronize();
		CommesseSync cs = new CommesseSync(persistenceUnitMaster, persistenceUnitSlave);
		cs.syncronize();
		PermessiSync ps = new PermessiSync(persistenceUnitMaster, persistenceUnitSlave);
		ps.syncronize();
		UtentiSync us = new UtentiSync(persistenceUnitMaster, persistenceUnitSlave);
		us.syncronize();
		UtentiPermessiSync ups = new UtentiPermessiSync(persistenceUnitMaster, persistenceUnitSlave);
		ups.syncronize();
		UtentiCommesseSync ucs = new UtentiCommesseSync(persistenceUnitMaster, persistenceUnitSlave);
		ucs.syncronize();
		UtentiSediSync uss = new UtentiSediSync(persistenceUnitMaster, persistenceUnitSlave);
		uss.syncronize();
		UtenteFeatureSync ufs = new UtenteFeatureSync(persistenceUnitMaster, persistenceUnitSlave);
		ufs.syncronize();
	}

}

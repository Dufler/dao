package it.ltc.model.interfaces.esportatore;

import java.util.List;

import org.apache.log4j.Logger;

public abstract class EsportatoreFiles {
	
	private static final Logger logger = Logger.getLogger(EsportatoreFiles.class);
	
	protected final String pathFolderOut;
	protected final String pathFolderCopy;
	
	public EsportatoreFiles(String pathFolderOut, String pathFolderCopy) {
		this.pathFolderOut = pathFolderOut;
		this.pathFolderCopy = pathFolderCopy;
	}
	
	public void esporta() {
		try {
			//guardo se ci sono nuovi files da produrre
			List<RisultatoEsportazione> risultatiEsportazione = getRisultatiEsportazione();
			//Notifico quanto accaduto
			inviaReportEsportazione(risultatiEsportazione);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	protected abstract void inviaReportEsportazione(List<RisultatoEsportazione> risultatiEsportazione);

	protected abstract List<RisultatoEsportazione> getRisultatiEsportazione();

}

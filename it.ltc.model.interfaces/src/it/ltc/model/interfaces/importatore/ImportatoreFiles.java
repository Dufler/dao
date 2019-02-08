package it.ltc.model.interfaces.importatore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


public abstract class ImportatoreFiles {
	
	private static final Logger logger = Logger.getLogger(ImportatoreFiles.class);
	
	protected final String pathFolderIn;
	protected final String pathFolderError;
	protected final String pathFolderProcessed;
	protected final String regexFileName;
	
	protected final List<RisultatoImportazione> risultati;
	
	protected final SimpleDateFormat sdfDataOraElaborazione;
	
	public ImportatoreFiles(String pathFolderIn, String pathFolderError, String pathFolderProcessed, String regexFileName) {
		this.pathFolderIn = pathFolderIn;
		this.pathFolderError = pathFolderError;
		this.pathFolderProcessed = pathFolderProcessed;
		this.regexFileName = regexFileName;
		this.risultati = new LinkedList<>();
		this.sdfDataOraElaborazione = new SimpleDateFormat("yyMMddHHmmss");
	}
	
	public void importa() {
		try {
			//Svuoto il risultato delle precedenti importazioni.
			risultati.clear();
			//guardo se ci sono nuovi files da importare nella cartella IN
			List<File> filesDaImportare = getFilesDaImportare();
			for (File file : filesDaImportare) {
				//in base al tipo di file eseguo una diversa importazione.
				RisultatoImportazione importazione = importaFile(file);
				//Aggiungo il risultato alla lista
				risultati.add(importazione);
				//Sposto il file
				if (!importazione.isInErrore())
					spostaFile(file, pathFolderProcessed);
				else
					spostaFile(file, pathFolderError);
			}
			//Notifico quanto accaduto se è stato importato qualcosa.
			if (!risultati.isEmpty())
				inviaReportImportazione();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	protected boolean spostaFile(File fileConErrori, String pathFolder) {
		String nomeFile = fileConErrori.getName();
		String dataOraLavorazione = sdfDataOraElaborazione.format(new Date());
		File fileDaSpostare = new File(pathFolder + dataOraLavorazione + "_" + nomeFile);
		boolean spostato = fileConErrori.renameTo(fileDaSpostare);
		if (spostato) {
			logger.info("Spostato il file '" + nomeFile + "' in '" + pathFolder + "'");
		}
		return spostato;
	}
	
	protected List<File> getFilesDaImportare() {
		List<File> filesDaImportare = new LinkedList<>();
		File folder = new File(pathFolderIn);
		for (File file : folder.listFiles()) {
			if (accettaFile(file))
				filesDaImportare.add(file);
		}
		ordinaFiles(filesDaImportare);
		return filesDaImportare;
	}
		
	protected boolean accettaFile(File file) {
		boolean accept = false;
		if (file.isFile()) {
			accept = file.getName().matches(regexFileName);
		}
		return accept;
	}
	
	/**
	 * Metodo da implementare che stabilisce l'ordine con cui andare ad importare i files ricevuti.
	 */
	protected abstract void ordinaFiles(List<File> filesDaImportare);
	
	/**
	 * Metodo da implementare che esegue l'importazione del file.
	 */
	protected abstract RisultatoImportazione importaFile(File file) throws Exception;
	
	/**
	 * Metodo da implementare che invia una notifica sul risultato dell'importazione.<br>
	 * E' possibile sfruttare il contenuto della lista <b>RisultatoImportazione</b>.
	 */
	protected abstract void inviaReportImportazione();

}

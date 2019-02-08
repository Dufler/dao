package it.ltc.model.interfaces.importatore;

import java.util.List;

public abstract class RisultatoImportazioneStandard extends RisultatoImportazione {
	
	protected final ConfigurazioneMessaggiImportazioneStandard messaggi;
	
	protected final String nomeFile;
	
	protected final int totali;
	protected final int inseriti;
	protected final int giàPresenti;
	protected final List<String> erroreValidazione;
	protected final List<String> erroreGenerico;
	
	public RisultatoImportazioneStandard(ConfigurazioneMessaggiImportazioneStandard configurazione, String nomeFile, int totali, int inseriti, int giàPresenti, List<String> erroreValidazione, List<String> erroreGenerico) {
		this.messaggi = configurazione != null ? configurazione : new ConfigurazioneMessaggiImportazioneStandard();
		this.nomeFile = nomeFile;
		this.totali = totali;
		this.inseriti = inseriti;
		this.giàPresenti = giàPresenti;
		this.erroreValidazione = erroreValidazione;
		this.erroreGenerico = erroreGenerico;
	}

	@Override
	public boolean isInErrore() {
		boolean errore = erroreValidazione.size() > 0 || erroreGenerico.size() > 0 || inseriti == 0;
		return errore;
	}

	@Override
	public String getDescrizioneRisultato() {
		//Costruzione del risultato
		StringBuilder sb = new StringBuilder();
		sb.append(getIntro());
		//totali
		sb.append(getTotali());
		//effettivamente inserite
		sb.append(getInseriti());
		//già presenti a sistema
		sb.append(getGiaPresenti());
		//errori in validazione
		sb.append(getErroriInValidazione());
		//errori generici
		sb.append(getErroriGenerici());
		return sb.toString();
	}
	
	protected String getIntro() {
		StringBuilder sb = new StringBuilder(messaggi.getIntro());
		sb.append(nomeFile);
		sb.append("\r\n");
		return sb.toString();
	}
	
	protected String getTotali() {
		StringBuilder sb = new StringBuilder(messaggi.getTotali());
		sb.append(totali);
		sb.append("\r\n");
		return sb.toString();
	}
	
	protected String getInseriti() {
		StringBuilder sb = new StringBuilder();
		if (inseriti > 0) {
			sb.append(messaggi.getElementiInseriti());
			sb.append(inseriti);
			sb.append("\r\n");
		} else {
			sb.append(messaggi.getNessunNuovoElemento());
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	protected String getGiaPresenti() {
		StringBuilder sb = new StringBuilder();
		if (giàPresenti > 0) {
			sb.append(messaggi.getElementiGiaPresenti());
			sb.append(giàPresenti);
			sb.append("\r\n");
		}
		return sb.toString();
	}
	
	protected String getErroriInValidazione() {
		StringBuilder sb = new StringBuilder();
		if (erroreValidazione.size() > 0) {
			sb.append(messaggi.getErroriValidazione());
			sb.append(erroreValidazione.size());
			sb.append("\r\n");
			for (String errore : erroreValidazione) {
				sb.append(errore);
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
	
	protected String getErroriGenerici() {
		StringBuilder sb = new StringBuilder();
		if (erroreGenerico.size() > 0) {
			sb.append(messaggi.getErroriGenerici());
			sb.append(erroreGenerico.size());
			sb.append("\r\n");
			for (String errore : erroreGenerico) {
				sb.append(errore);
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}

}

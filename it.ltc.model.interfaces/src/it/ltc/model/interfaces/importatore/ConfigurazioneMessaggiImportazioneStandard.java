package it.ltc.model.interfaces.importatore;

public class ConfigurazioneMessaggiImportazioneStandard {
	
	public static final String DEFAULT_INTRO = "Risultato importazione del file: ";
	public static final String DEFAULT_ELEMENTI_TOTALI = "Elementi contenuti nel file: ";
	public static final String DEFAULT_ELEMENTI_INSERITI = "Nuovi elementi inseriti a sistema: ";
	public static final String DEFAULT_NESSUN_NUOVO_ELEMENTO = "Alert: nessun nuovo elemento inserito a sistema!";
	public static final String DEFAULT_GIA_PRESENTI = "Elementi già presenti a sistema: ";
	public static final String DEFAULT_ERRORI_VALIDAZIONE = "Elementi con errori in validazione: ";
	public static final String DEFAULT_ERRORI_GENERICI = "Elementi con errori generici: ";
	
	private String intro;
	private String totali;
	private String elementiInseriti;
	private String nessunNuovoElemento;
	private String elementiGiaPresenti;
	private String erroriValidazione;
	private String erroriGenerici;
	
	public ConfigurazioneMessaggiImportazioneStandard() {
		this(DEFAULT_INTRO, DEFAULT_ELEMENTI_TOTALI, DEFAULT_ELEMENTI_INSERITI, DEFAULT_NESSUN_NUOVO_ELEMENTO, DEFAULT_GIA_PRESENTI, DEFAULT_ERRORI_VALIDAZIONE, DEFAULT_ERRORI_GENERICI);
	}
	
	public ConfigurazioneMessaggiImportazioneStandard(String intro, String totali, String elementiInseriti,	String nessunNuovoElemento, String elementiGiaPresenti, String erroriValidazione, String erroriGenerici) {
		this.intro = intro;
		this.totali = totali;
		this.elementiInseriti = elementiInseriti;
		this.nessunNuovoElemento = nessunNuovoElemento;
		this.elementiGiaPresenti = elementiGiaPresenti;
		this.erroriValidazione = erroriValidazione;
		this.erroriGenerici = erroriGenerici;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTotali() {
		return totali;
	}

	public void setTotali(String totali) {
		this.totali = totali;
	}

	public String getElementiInseriti() {
		return elementiInseriti;
	}

	public void setElementiInseriti(String elementiInseriti) {
		this.elementiInseriti = elementiInseriti;
	}

	public String getNessunNuovoElemento() {
		return nessunNuovoElemento;
	}

	public void setNessunNuovoElemento(String nessunNuovoElemento) {
		this.nessunNuovoElemento = nessunNuovoElemento;
	}

	public String getElementiGiaPresenti() {
		return elementiGiaPresenti;
	}

	public void setElementiGiaPresenti(String elementiGiaPresenti) {
		this.elementiGiaPresenti = elementiGiaPresenti;
	}

	public String getErroriValidazione() {
		return erroriValidazione;
	}

	public void setErroriValidazione(String erroriValidazione) {
		this.erroriValidazione = erroriValidazione;
	}

	public String getErroriGenerici() {
		return erroriGenerici;
	}

	public void setErroriGenerici(String erroriGenerici) {
		this.erroriGenerici = erroriGenerici;
	}	

}

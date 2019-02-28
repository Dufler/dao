package it.ltc.model.interfaces.importatore;

public class RisultatoImportazioneErrore extends RisultatoImportazione {
	
	private final Exception e;
	
	public RisultatoImportazioneErrore(Exception e) {
		this.e = e;
	}

	@Override
	public boolean isInErrore() {
		return true;
	}

	@Override
	public String getDescrizioneRisultato() {
		return e.getMessage();
	}

}

package it.ltc.database.dao;

import java.util.LinkedList;
import java.util.List;

public class CriteriSelezione {
	
	public static final int DEFAULT_MAX_RESULT = -1;
	
	private final int maxRisultati;
	private final List<CondizioneWhere> condizioni;
	
	public CriteriSelezione(int maxRisultati) {
		this.maxRisultati = maxRisultati;
		this.condizioni = new LinkedList<>();
	}
	
	public CriteriSelezione() {
		this(DEFAULT_MAX_RESULT);
	}
	
	public void aggiungiCondizione(CondizioneWhere condizione) {
		condizioni.add(condizione);
	}
	
	public int getMaxRisultati() {
		return maxRisultati;
	}
	
	public List<CondizioneWhere> getCondizioni() {
		return condizioni;
	}

}

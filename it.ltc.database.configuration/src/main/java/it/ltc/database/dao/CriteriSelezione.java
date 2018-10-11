package it.ltc.database.dao;

import java.util.LinkedList;
import java.util.List;

public class CriteriSelezione {
	
	private int maxResult;
	private final List<CondizioneWhere> conditions;
	
	public CriteriSelezione() {
		this.maxResult = 0;
		this.conditions = new LinkedList<>();
	}
	
	

}

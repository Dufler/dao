package it.ltc.model.interfaces.ordine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MParticolarita implements ModelInterface {

	private static final long serialVersionUID = 1L;
	
	private String note;
	
	public MParticolarita() {}

	@Override
	public void valida() throws ModelValidationException {
		// TODO - Definire le Particolarità		
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

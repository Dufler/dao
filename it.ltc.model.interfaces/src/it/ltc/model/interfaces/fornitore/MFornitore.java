package it.ltc.model.interfaces.fornitore;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.indirizzo.MIndirizzo;
import it.ltc.model.interfaces.model.ModelInterface;

public class MFornitore implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codice;
	private String ragioneSociale;
	private String note;
	private TipoFornitore tipo;
	private MIndirizzo indirizzo;
	
	public MFornitore() {}
	
	@Override
	public void valida() throws ModelValidationException {
		//Ragione sociale
		if (ragioneSociale == null || ragioneSociale.isEmpty()) {
			throw new ModelValidationException("E' necessario inserire una ragione sociale per il fornitore.");
		} else if (ragioneSociale.length() > 50) {
			throw new ModelValidationException("La ragione sociale indicata per il fornitore è troppo lunga. (MAX 50 caratteri, " + ragioneSociale + ")");
		}
		//Codice
		if (codice != null && codice.length() > 20) {
			throw new ModelValidationException("Il codice di riferimento indicato per il fornitore è troppo lungo. (MAX 20 caratteri, " + codice + ")");
		}
		//Note
		if (note != null && note.length() > 250) {
			throw new ModelValidationException("Le note indicate per il fornitore sono troppo lunghe. (MAX 250 caratteri)");
		}
		//Indirizzo
		if (indirizzo == null) {
			throw new ModelValidationException("E' necessario indicare un indirizzo per il fornitore.");
		} else {
			indirizzo.valida();
		}
	}

	@Override
	public String toString() {
		return "MFornitore [id=" + id + ", codice=" + codice + ", ragioneSociale=" + ragioneSociale + ", note=" + note
				+ ", indirizzo=" + indirizzo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MFornitore other = (MFornitore) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public TipoFornitore getTipo() {
		return tipo;
	}

	public void setTipo(TipoFornitore tipo) {
		this.tipo = tipo;
	}

	public MIndirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(MIndirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

}

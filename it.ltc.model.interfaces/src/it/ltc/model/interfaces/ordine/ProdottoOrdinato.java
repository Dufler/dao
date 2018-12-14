package it.ltc.model.interfaces.ordine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProdottoOrdinato implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int numeroRiga;
	private String chiavelegacy;
	
	private int quantita;
	private String magazzinoCliente;
	private String magazzinoLTC;
	
	private String barcode;
	private String chiave;
	private String codicemodello;
	private String taglia;
	
	private String riferimentoCliente;
	private String note;
	
	public ProdottoOrdinato() {}
	
	@Override
	public void valida() throws ModelValidationException {
		if (quantita <= 0) {
			throw new ModelValidationException("La quantita' specificata non è valida.");
		}
		if (magazzinoCliente == null || magazzinoCliente.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare un magazzino cliente.");
		}
	}
	
	public void validaBarcode() throws ModelValidationException {
		if (barcode == null || barcode.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare un barcode.");
		}
	}
	
	public void validaChiave() throws ModelValidationException {
		if (chiave == null || chiave.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare la chiave del prodotto.");
		}
	}
	
	public void validaModelloTaglia() throws ModelValidationException {
		if (codicemodello == null || codicemodello.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare il codice del modello.");
		}
		if (taglia == null || taglia.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare la taglia.");
		}
	}
	
	public void valida(TipoIDProdotto tipo) throws ModelValidationException {
		valida();
		switch (tipo) {
			case BARCODE : validaBarcode(); break;
			case CHIAVE: validaChiave(); break;
			case MODELLOTAGLIA: validaModelloTaglia(); break;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroRiga() {
		return numeroRiga;
	}

	public void setNumeroRiga(int numeroRiga) {
		this.numeroRiga = numeroRiga;
	}

	public String getChiavelegacy() {
		return chiavelegacy;
	}

	public void setChiavelegacy(String chiavelegacy) {
		this.chiavelegacy = chiavelegacy;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public String getMagazzinoCliente() {
		return magazzinoCliente;
	}

	public void setMagazzinoCliente(String magazzinoCliente) {
		this.magazzinoCliente = magazzinoCliente;
	}

	public String getMagazzinoLTC() {
		return magazzinoLTC;
	}

	public void setMagazzinoLTC(String magazzinoLTC) {
		this.magazzinoLTC = magazzinoLTC;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}
	
	public String getCodicemodello() {
		return codicemodello;
	}

	public void setCodicemodello(String codicemodello) {
		this.codicemodello = codicemodello;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public String getRiferimentoCliente() {
		return riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((chiave == null) ? 0 : chiave.hashCode());
		result = prime * result + ((codicemodello == null) ? 0 : codicemodello.hashCode());
		result = prime * result + ((magazzinoCliente == null) ? 0 : magazzinoCliente.hashCode());
		result = prime * result + numeroRiga;
		result = prime * result + quantita;
		result = prime * result + ((taglia == null) ? 0 : taglia.hashCode());
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
		ProdottoOrdinato other = (ProdottoOrdinato) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (chiave == null) {
			if (other.chiave != null)
				return false;
		} else if (!chiave.equals(other.chiave))
			return false;
		if (codicemodello == null) {
			if (other.codicemodello != null)
				return false;
		} else if (!codicemodello.equals(other.codicemodello))
			return false;
		if (magazzinoCliente == null) {
			if (other.magazzinoCliente != null)
				return false;
		} else if (!magazzinoCliente.equals(other.magazzinoCliente))
			return false;
		if (numeroRiga != other.numeroRiga)
			return false;
		if (quantita != other.quantita)
			return false;
		if (taglia == null) {
			if (other.taglia != null)
				return false;
		} else if (!taglia.equals(other.taglia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdottoOrdinato [numeroRiga=" + numeroRiga + ", quantita=" + quantita + ", magazzino=" + magazzinoCliente
				+ ", barcode=" + barcode + ", chiave=" + chiave + ", codicemodello=" + codicemodello + ", taglia="
				+ taglia + ", riferimentoCliente=" + riferimentoCliente + ", note=" + note + "]";
	}

}

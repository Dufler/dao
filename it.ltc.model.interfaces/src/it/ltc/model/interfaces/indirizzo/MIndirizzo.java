package it.ltc.model.interfaces.indirizzo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MIndirizzo implements ModelInterface {

	private static final long serialVersionUID = 1L;
	
	protected int id;
	
	protected String codice;
	protected String ragioneSociale;
	protected String indirizzo;
	protected String cap;
	protected String localita;
	protected String provincia;
	protected String nazione;
	protected String telefono;
	protected String email;
	
	public MIndirizzo() {}

	@Override
	public void valida() throws ModelValidationException {
		//Ragione sociale
		if (ragioneSociale == null || ragioneSociale.isEmpty())
			throw new ModelValidationException("Bisogna specificare la ragione sociale.");
		else if (ragioneSociale.length() > 100) 
			throw new ModelValidationException("La ragione sociale specificata e' troppo lunga. (Max 100 caratteri)");
		//Indirizzo
		if (indirizzo == null || indirizzo.isEmpty())
			throw new ModelValidationException("Bisogna specificare l'indirizzo.");
		else if (indirizzo.length() > 250)
			throw new ModelValidationException("L'indirizzo specificato e' troppo lungo. (Max 250 caratteri)");
		//CAP
		if (cap == null || cap.isEmpty()) 
			throw new ModelValidationException("Bisogna specificare il cap.");
		else if (cap.length() < 5 || cap.length() > 10)
			throw new ModelValidationException("Il cap specificato non è valido.");
		//Localita
		if (localita == null || localita.isEmpty())
			throw new ModelValidationException("Bisogna specificare la localita'.");
		else if (localita.length() > 50)
			throw new ModelValidationException("La localita' specificata e' troppo lunga. (Max 40 caratteri)");
		//Provincia
		if (provincia == null || provincia.isEmpty())
			throw new ModelValidationException("Bisogna specificare la provincia.");
		else if (provincia.length() != 2)
			throw new ModelValidationException("La provincia non è valida. Es. PG. Indicare XX per l'estero.");
		//Nazione
		if (nazione == null || nazione.isEmpty())
			throw new ModelValidationException("Bisogna specificare la nazione.");
		else if (nazione.length() != 3)
			throw new ModelValidationException("La nazione specificata non è valida. Utilizzare il codice ISO a 3 caratteri. Es. ITA");
		//Telefono
		if (telefono != null && telefono.length() > 30)
			throw new ModelValidationException("Il numero di telefono specificato e' troppo lungo. (Max 30 caratteri) contattare il reparto IT di LTC");
		//Email
		if (email != null && email.length() > 100)
			throw new ModelValidationException("L'indirizzo email specificato e' troppo lungo. (Max 100 caratteri) contattare il reparto IT di LTC");
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
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getLocalita() {
		return localita;
	}
	
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getNazione() {
		return nazione;
	}
	
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cap == null) ? 0 : cap.hashCode());
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((localita == null) ? 0 : localita.hashCode());
		result = prime * result + ((nazione == null) ? 0 : nazione.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
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
		MIndirizzo other = (MIndirizzo) obj;
		if (cap == null) {
			if (other.cap != null)
				return false;
		} else if (!cap.equals(other.cap))
			return false;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (localita == null) {
			if (other.localita != null)
				return false;
		} else if (!localita.equals(other.localita))
			return false;
		if (nazione == null) {
			if (other.nazione != null)
				return false;
		} else if (!nazione.equals(other.nazione))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (ragioneSociale == null) {
			if (other.ragioneSociale != null)
				return false;
		} else if (!ragioneSociale.equals(other.ragioneSociale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MIndirizzo [codice=" + codice + ", ragionesociale=" + ragioneSociale + ", indirizzo=" + indirizzo
				+ ", cap=" + cap + ", localita=" + localita + ", provincia=" + provincia + ", nazione=" + nazione
				+ ", telefono=" + telefono + ", email=" + email + "]";
	}
	
}

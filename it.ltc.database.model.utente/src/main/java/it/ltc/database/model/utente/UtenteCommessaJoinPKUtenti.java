package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the utente_commessa_join database table.
 * 
 */
@Embeddable
public class UtenteCommessaJoinPKUtenti implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String utente;

	//@Column(name="id_commessa", insertable=false, updatable=false)
	private int idCommessa;

	public UtenteCommessaJoinPKUtenti() {}
	
	public String getUtente() {
		return this.utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public int getIdCommessa() {
		return this.idCommessa;
	}
	
	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UtenteCommessaJoinPKUtenti)) {
			return false;
		}
		UtenteCommessaJoinPKUtenti castOther = (UtenteCommessaJoinPKUtenti)other;
		return 
			this.utente.equals(castOther.utente)
			&& (this.idCommessa == castOther.idCommessa);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.utente.hashCode();
		hash = hash * prime + this.idCommessa;
		
		return hash;
	}
	
}
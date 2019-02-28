package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the utente_permessi_join database table.
 * 
 */
@Embeddable
public class UtentePermessiJoinPKUtenti implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private String utente;

	//@Column(name="id_permesso", insertable=false, updatable=false)
	private int idPermesso;

	public UtentePermessiJoinPKUtenti() {}
	
	public String getUtente() {
		return this.utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public int getIdPermesso() {
		return this.idPermesso;
	}
	
	public void setIdPermesso(int idPermesso) {
		this.idPermesso = idPermesso;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UtentePermessiJoinPKUtenti)) {
			return false;
		}
		UtentePermessiJoinPKUtenti castOther = (UtentePermessiJoinPKUtenti)other;
		return 
			this.utente.equals(castOther.utente)
			&& (this.idPermesso == castOther.idPermesso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.utente.hashCode();
		hash = hash * prime + this.idPermesso;
		
		return hash;
	}
	
}
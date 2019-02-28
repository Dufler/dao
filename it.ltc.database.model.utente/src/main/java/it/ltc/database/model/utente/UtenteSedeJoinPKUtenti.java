package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the utente_sede_join database table.
 * 
 */
@Embeddable
public class UtenteSedeJoinPKUtenti implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private String utente;

	//@Column(name="id_sede", insertable=false, updatable=false)
	private int idSede;

	public UtenteSedeJoinPKUtenti() {}
	
	public String getUtente() {
		return this.utente;
	}
	
	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public int getIdSede() {
		return this.idSede;
	}
	
	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UtenteSedeJoinPKUtenti)) {
			return false;
		}
		UtenteSedeJoinPKUtenti castOther = (UtenteSedeJoinPKUtenti)other;
		return 
			this.utente.equals(castOther.utente)
			&& (this.idSede == castOther.idSede);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.utente.hashCode();
		hash = hash * prime + this.idSede;
		
		return hash;
	}
	
}
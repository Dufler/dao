package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_sede_join database table.
 * 
 */
@Entity
@Table(name="utente_sede_join")
@NamedQuery(name="UtenteSedeJoin.findAll", query="SELECT u FROM UtenteSedeJoin u")
public class UtenteSedeJoin implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UtenteSedeJoinPK id;

	public UtenteSedeJoin() {
	}

	public UtenteSedeJoinPK getId() {
		return this.id;
	}

	public void setId(UtenteSedeJoinPK id) {
		this.id = id;
	}

}
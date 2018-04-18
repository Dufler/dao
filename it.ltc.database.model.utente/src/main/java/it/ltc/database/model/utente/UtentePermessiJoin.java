package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_permessi_join database table.
 * 
 */
@Entity
@Table(name="utente_permessi_join")
@NamedQuery(name="UtentePermessiJoin.findAll", query="SELECT u FROM UtentePermessiJoin u")
public class UtentePermessiJoin implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UtentePermessiJoinPK id;

	public UtentePermessiJoin() {
	}

	public UtentePermessiJoinPK getId() {
		return this.id;
	}

	public void setId(UtentePermessiJoinPK id) {
		this.id = id;
	}

}
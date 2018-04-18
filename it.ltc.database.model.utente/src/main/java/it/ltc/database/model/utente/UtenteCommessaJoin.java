package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_commessa_join database table.
 * 
 */
@Entity
@Table(name="utente_commessa_join")
@NamedQuery(name="UtenteCommessaJoin.findAll", query="SELECT u FROM UtenteCommessaJoin u")
public class UtenteCommessaJoin implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UtenteCommessaJoinPK id;

	public UtenteCommessaJoin() {}

	public UtenteCommessaJoinPK getId() {
		return this.id;
	}

	public void setId(UtenteCommessaJoinPK id) {
		this.id = id;
	}

}
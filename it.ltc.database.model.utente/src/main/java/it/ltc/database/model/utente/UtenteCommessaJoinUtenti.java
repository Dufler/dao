package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_commessa_join database table.
 * 
 */
@Entity
@Table(name="utente_commessa_join")
@IdClass(UtenteCommessaJoinPKUtenti.class)
@NamedQuery(name="UtenteCommessaJoinUtenti.findAll", query="SELECT u FROM UtenteCommessaJoinUtenti u")
public class UtenteCommessaJoinUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtenteCommessaJoinPKUtenti id;
	
	@Id
	private String utente;

	@Id
	@Column(name="id_commessa", updatable=false)
	private int idCommessa;

	public UtenteCommessaJoinUtenti() {}

//	public UtenteCommessaJoinPKUtenti getId() {
//		return this.id;
//	}
//
//	public void setId(UtenteCommessaJoinPKUtenti id) {
//		this.id = id;
//	}
	
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

}
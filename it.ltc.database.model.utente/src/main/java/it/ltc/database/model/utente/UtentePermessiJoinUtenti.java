package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_permessi_join database table.
 * 
 */
@Entity
@Table(name="utente_permessi_join")
@IdClass(UtentePermessiJoinPKUtenti.class)
@NamedQuery(name="UtentePermessiJoinUtenti.findAll", query="SELECT u FROM UtentePermessiJoinUtenti u")
public class UtentePermessiJoinUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtentePermessiJoinPKUtenti id;
	
	@Id
	@Column(updatable=false)
	private String utente;

	@Id
	@Column(name="id_permesso", updatable=false)
	private int idPermesso;

	public UtentePermessiJoinUtenti() {}

//	public UtentePermessiJoinPKUtenti getId() {
//		return this.id;
//	}
//
//	public void setId(UtentePermessiJoinPKUtenti id) {
//		this.id = id;
//	}
	
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

}
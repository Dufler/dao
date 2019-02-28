package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_sede_join database table.
 * 
 */
@Entity
@Table(name="utente_sede_join")
@IdClass(UtenteSedeJoinPKUtenti.class)
@NamedQuery(name="UtenteSedeJoinUtenti.findAll", query="SELECT u FROM UtenteSedeJoinUtenti u")
public class UtenteSedeJoinUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtenteSedeJoinPKUtenti id;
	
	@Id
	@Column(insertable=false, updatable=false)
	private String utente;

	@Id
	@Column(name="id_sede", insertable=false, updatable=false)
	private int idSede;

	public UtenteSedeJoinUtenti() {}

//	public UtenteSedeJoinPKUtenti getId() {
//		return this.id;
//	}
//
//	public void setId(UtenteSedeJoinPKUtenti id) {
//		this.id = id;
//	}
	
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

}
package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_sede_join database table.
 * 
 */
@Entity
@Table(name="utente_sede_join")
@IdClass(UtenteSedeJoinPK.class)
@NamedQuery(name="UtenteSedeJoin.findAll", query="SELECT u FROM UtenteSedeJoin u")
public class UtenteSedeJoin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(updatable=false, nullable=false, length=50)
	private String utente;

	@Id
	@Column(name="id_sede", updatable=false, nullable=false)
	private int idSede;

//	@EmbeddedId
//	private UtenteSedeJoinPK id;

	public UtenteSedeJoin() {}

//	public UtenteSedeJoinPK getId() {
//		return this.id;
//	}
//
//	public void setId(UtenteSedeJoinPK id) {
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
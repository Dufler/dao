package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_permessi_join database table.
 * 
 */
@Entity
@Table(name="utente_permessi_join")
@IdClass(UtentePermessiJoinPK.class)
@NamedQuery(name="UtentePermessiJoin.findAll", query="SELECT u FROM UtentePermessiJoin u")
public class UtentePermessiJoin implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtentePermessiJoinPK id;
	
	@Id
	@Column(updatable=false, nullable=false, length=50)
	private String utente;

	@Id
	@Column(name="id_permesso", updatable=false, nullable=false)
	private int idPermesso;

	public UtentePermessiJoin() {}

//	public UtentePermessiJoinPK getId() {
//		return this.id;
//	}
//
//	public void setId(UtentePermessiJoinPK id) {
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
package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_commessa_join database table.
 * 
 */
@Entity
@Table(name="utente_commessa_join")
@IdClass(UtenteCommessaJoinPK.class)
@NamedQuery(name="UtenteCommessaJoin.findAll", query="SELECT u FROM UtenteCommessaJoin u")
public class UtenteCommessaJoin implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private UtenteCommessaJoinPK id;
	
	@Id
	@Column(nullable=false, length=50)
	private String utente;

	@Id
	@Column(name="id_commessa", updatable=false, nullable=false)
	private int idCommessa;

	public UtenteCommessaJoin() {}

//	public UtenteCommessaJoinPK getId() {
//		return this.id;
//	}
//
//	public void setId(UtenteCommessaJoinPK id) {
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
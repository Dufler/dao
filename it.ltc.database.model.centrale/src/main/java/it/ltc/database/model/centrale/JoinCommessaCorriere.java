package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the join_commessa_corriere database table.
 * 
 */
@Entity
@Table(name="join_commessa_corriere")
@NamedQuery(name="JoinCommessaCorriere.findAll", query="SELECT j FROM JoinCommessaCorriere j")
public class JoinCommessaCorriere implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum StatoCodice {DEFAULT, ATTIVO, DISATTIVO, DISMESSO, PROVVISORIO, TEST}
	
	@Id
	@Column(name="codice_cliente", unique=true, nullable=false, length=50)
	private String codiceCliente;

	@Column(nullable=false)
	private int commessa;

	@Column(nullable=false)
	private int corriere;

	@Column(name="descrizione", columnDefinition="TINYTEXT")
	private String descrizione;

	@Column(nullable=false, length=1, columnDefinition="ENUM('DEFAULT', 'ATTIVO', 'DISATTIVO', 'DISMESSO', 'PROVVISORIO', 'TEST')")
	@Enumerated(EnumType.STRING)
	private StatoCodice stato;

	public JoinCommessaCorriere() {
	}

	public String getCodiceCliente() {
		return this.codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public int getCommessa() {
		return this.commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public int getCorriere() {
		return this.corriere;
	}

	public void setCorriere(int corriere) {
		this.corriere = corriere;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public StatoCodice getStato() {
		return this.stato;
	}

	public void setStato(StatoCodice stato) {
		this.stato = stato;
	}

}
package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MittentiOrdine database table.
 * 
 */
@Entity
@Table(name="MittentiOrdine")
//@NamedQuery(name="MittentiOrdine.findAll", query="SELECT m FROM MittentiOrdine m")
public class MittentiOrdine implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdMittente", unique=true, nullable=false)
	private int idMittente;

	@Column(name="Cap", nullable=false, length=10)
	private String cap;

	@Column(name="Email", length=100)
	private String email;

	@Column(name="Indirizzo", nullable=false, length=250)
	private String indirizzo;

	@Column(name="Localita", nullable=false, length=50)
	private String localita;

	@Column(name="Nazione", nullable=false, length=3)
	private String nazione;

	@Column(name="Provincia", nullable=false, length=2)
	private String provincia;

	@Column(name="RagioneSociale", nullable=false, length=100)
	private String ragioneSociale;

	@Column(name="Telefono", length=30)
	private String telefono;

	public MittentiOrdine() {}

	public int getIdMittente() {
		return this.idMittente;
	}

	public void setIdMittente(int idMittente) {
		this.idMittente = idMittente;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
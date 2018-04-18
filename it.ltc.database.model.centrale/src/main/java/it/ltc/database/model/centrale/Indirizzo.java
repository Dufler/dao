package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the indirizzo database table.
 * 
 */
@Entity
@Table(name="indirizzo")
@NamedQuery(name="Indirizzo.findAll", query="SELECT i FROM Indirizzo i")
public class Indirizzo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=10)
	private String cap;

	@Column(name="consegna_al_piano", nullable=false)
	private boolean consegnaAlPiano;

	@Column(name="consegna_appuntamento", nullable=false)
	private boolean consegnaAppuntamento;

	@Column(name="consegna_gdo", nullable=false)
	private boolean consegnaGdo;

	@Column(name="consegna_privato", nullable=false)
	private boolean consegnaPrivato;

	@Column(length=100)
	private String email;

	@Column(nullable=false, length=100)
	private String indirizzo;

	@Column(nullable=false, length=45)
	private String localita;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String nazione;

	@Column(nullable=false, length=2, columnDefinition="CHAR")
	private String provincia;

	@Column(name="ragione_sociale", nullable=false, length=200)
	private String ragioneSociale;

	@Column(length=30)
	private String telefono;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica", nullable=false, insertable=false)
	private Date dataUltimaModifica;

	public Indirizzo() {}
	
	public boolean quasiUguali(Indirizzo indirizzo) {
		String capIndirizzo = indirizzo.getCap();
		String localitaIndirizzo = indirizzo.getLocalita();
		boolean capUguali = cap.equals(capIndirizzo);
		boolean localitaUguali = localita.equals(localitaIndirizzo);
		boolean uguali = capUguali && localitaUguali;
		return uguali;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public boolean getConsegnaAlPiano() {
		return this.consegnaAlPiano;
	}

	public void setConsegnaAlPiano(boolean consegnaAlPiano) {
		this.consegnaAlPiano = consegnaAlPiano;
	}

	public boolean getConsegnaAppuntamento() {
		return this.consegnaAppuntamento;
	}

	public void setConsegnaAppuntamento(boolean consegnaAppuntamento) {
		this.consegnaAppuntamento = consegnaAppuntamento;
	}

	public boolean getConsegnaGdo() {
		return this.consegnaGdo;
	}

	public void setConsegnaGdo(boolean consegnaGdo) {
		this.consegnaGdo = consegnaGdo;
	}

	public boolean getConsegnaPrivato() {
		return this.consegnaPrivato;
	}

	public void setConsegnaPrivato(boolean consegnaPrivato) {
		this.consegnaPrivato = consegnaPrivato;
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
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

}
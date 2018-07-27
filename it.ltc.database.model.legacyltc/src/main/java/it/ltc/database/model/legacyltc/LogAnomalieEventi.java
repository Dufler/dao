package it.ltc.database.model.legacyltc;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the LogAnomalieEventi database table.
 * 
 */
@Entity
@NamedQuery(name="LogAnomalieEventi.findAll", query="SELECT l FROM LogAnomalieEventi l")
public class LogAnomalieEventi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="DataOraFineUE")
	private Timestamp dataOraFineUE;

	@Column(name="DataOraInizio")
	private Timestamp dataOraInizio;

	@Column(name="DataOraRegistrazione")
	private Timestamp dataOraRegistrazione;

	@Column(name="Dispositivo")
	private String dispositivo;

	@Column(name="Host")
	private String host;

	private int idCosto;

	private int idMittente;

	@Column(name="Letto")
	private int letto;

	@Column(name="Messaggio")
	private String messaggio;

	@Column(name="Tipo")
	private String tipo;

	@Column(name="Utente")
	private int utente;

	public LogAnomalieEventi() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDataOraFineUE() {
		return this.dataOraFineUE;
	}

	public void setDataOraFineUE(Timestamp dataOraFineUE) {
		this.dataOraFineUE = dataOraFineUE;
	}

	public Timestamp getDataOraInizio() {
		return this.dataOraInizio;
	}

	public void setDataOraInizio(Timestamp dataOraInizio) {
		this.dataOraInizio = dataOraInizio;
	}

	public Timestamp getDataOraRegistrazione() {
		return this.dataOraRegistrazione;
	}

	public void setDataOraRegistrazione(Timestamp dataOraRegistrazione) {
		this.dataOraRegistrazione = dataOraRegistrazione;
	}

	public String getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getIdCosto() {
		return this.idCosto;
	}

	public void setIdCosto(int idCosto) {
		this.idCosto = idCosto;
	}

	public int getIdMittente() {
		return this.idMittente;
	}

	public void setIdMittente(int idMittente) {
		this.idMittente = idMittente;
	}

	public int getLetto() {
		return this.letto;
	}

	public void setLetto(int letto) {
		this.letto = letto;
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getUtente() {
		return this.utente;
	}

	public void setUtente(int utente) {
		this.utente = utente;
	}

}
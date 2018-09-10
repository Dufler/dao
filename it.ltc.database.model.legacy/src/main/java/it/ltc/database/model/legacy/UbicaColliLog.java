package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;

import it.ltc.utility.miscellanea.time.DateConverter;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the UbicaColliLog database table.
 * 
 */
@Entity
@Table(name="UbicaColliLog")
//@NamedQuery(name="UbicaColliLog.findAll", query="SELECT u FROM UbicaColliLog u")
public class UbicaColliLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id", unique=true, nullable=false)
	private int id;

	@Column(name="DataUbicazione")
	private Timestamp dataUbicazione;

	@Column(name="KeyCollo", length=50)
	private String keyCollo;

	@Column(name="KeyUbbica", length=50)
	private String keyUbbica;

	@Column(name="OpeUbica", length=50)
	private String opeUbica;

	@Column(name="OraUbicazione")
	private int oraUbicazione;

	@Column(name="TipoCollo", length=50)
	private String tipoCollo;

	public UbicaColliLog() {}
	
	@PrePersist
	private void prePersist() {
		dataUbicazione = new Timestamp(new Date().getTime());
		oraUbicazione = DateConverter.getOraComeIntero(dataUbicazione);
		dataUbicazione = DateConverter.ripulisciTimestap(dataUbicazione);
	}
	
	@PreUpdate
	private void preUpdate() {
		dataUbicazione = new Timestamp(new Date().getTime());
		oraUbicazione = DateConverter.getOraComeIntero(dataUbicazione);
		dataUbicazione = DateConverter.ripulisciTimestap(dataUbicazione);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDataUbicazione() {
		return this.dataUbicazione;
	}

	public void setDataUbicazione(Timestamp dataUbicazione) {
		this.dataUbicazione = dataUbicazione;
	}

	public String getKeyCollo() {
		return this.keyCollo;
	}

	public void setKeyCollo(String keyCollo) {
		this.keyCollo = keyCollo;
	}

	public String getKeyUbbica() {
		return this.keyUbbica;
	}

	public void setKeyUbbica(String keyUbbica) {
		this.keyUbbica = keyUbbica;
	}

	public String getOpeUbica() {
		return this.opeUbica;
	}

	public void setOpeUbica(String opeUbica) {
		this.opeUbica = opeUbica;
	}

	public int getOraUbicazione() {
		return this.oraUbicazione;
	}

	public void setOraUbicazione(int oraUbicazione) {
		this.oraUbicazione = oraUbicazione;
	}

	public String getTipoCollo() {
		return this.tipoCollo;
	}

	public void setTipoCollo(String tipoCollo) {
		this.tipoCollo = tipoCollo;
	}

}
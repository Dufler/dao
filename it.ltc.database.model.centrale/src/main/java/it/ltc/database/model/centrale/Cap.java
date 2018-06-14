package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the cap database table.
 * 
 */
@Entity
@Table(name="cap")
@IdClass(CapPK.class)
@NamedQuery(name="Cap.findAll", query="SELECT c FROM Cap c")
public class Cap implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=5, columnDefinition="CHAR")
	private String cap;

	@Id
	@Column(unique=true, nullable=false, length=200)
	private String localita;

	@Column(name="brt_disagiate", nullable=false)
	private boolean brtDisagiate;

	@Column(name="brt_isole", nullable=false)
	private boolean brtIsole;

	@Column(name="brt_ztl", nullable=false)
	private boolean brtZtl;

	@Column(nullable=false, length=2, columnDefinition="CHAR")
	private String provincia;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String regione;

	@Column(name="tnt_ore_dieci", nullable=false)
	private boolean tntOreDieci;

	@Column(name="tnt_ore_dodici", nullable=false)
	private boolean tntOreDodici;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica", nullable=false, insertable=false)
	private Date dataUltimaModifica;

	public Cap() {}

	public CapPK getPK() {
		CapPK pk = new CapPK();
		pk.setCap(cap);
		pk.setLocalita(localita);
		return pk;
	}
	
	public String getCap() {
		return this.cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public String getLocalita() {
		return this.localita;
	}
	
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public boolean getBrtDisagiate() {
		return this.brtDisagiate;
	}

	public void setBrtDisagiate(boolean brtDisagiate) {
		this.brtDisagiate = brtDisagiate;
	}

	public boolean getBrtIsole() {
		return this.brtIsole;
	}

	public void setBrtIsole(boolean brtIsole) {
		this.brtIsole = brtIsole;
	}

	public boolean getBrtZtl() {
		return this.brtZtl;
	}

	public void setBrtZtl(boolean brtZtl) {
		this.brtZtl = brtZtl;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return this.regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public boolean getTntOreDieci() {
		return this.tntOreDieci;
	}

	public void setTntOreDieci(boolean tntOreDieci) {
		this.tntOreDieci = tntOreDieci;
	}

	public boolean getTntOreDodici() {
		return this.tntOreDodici;
	}

	public void setTntOreDodici(boolean tntOreDodici) {
		this.tntOreDodici = tntOreDodici;
	}
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

}
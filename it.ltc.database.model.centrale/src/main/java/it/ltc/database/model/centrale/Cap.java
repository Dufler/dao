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
@NamedQuery(name="Cap.findAll", query="SELECT c FROM Cap c")
public class Cap implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CapPK id;

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

	public CapPK getId() {
		return this.id;
	}

	public void setId(CapPK id) {
		this.id = id;
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
package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the versione_tabella database table.
 * 
 */
@Entity
@Table(name="versione_tabella")
@NamedQuery(name="VersioneTabella.findAll", query="SELECT v FROM VersioneTabella v")
public class VersioneTabella implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=45)
	private String tabella;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_versione", nullable=false)
	private Date dataVersione;

	public VersioneTabella() {}

	public String getTabella() {
		return this.tabella;
	}

	public void setTabella(String tabella) {
		this.tabella = tabella;
	}

	public Date getDataVersione() {
		return this.dataVersione;
	}

	public void setDataVersione(Date dataVersione) {
		this.dataVersione = dataVersione;
	}

}
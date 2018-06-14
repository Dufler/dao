package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the crm_tag_servizi_richiesti database table.
 * 
 */
@Entity
@Table(name="crm_tag_servizi_richiesti")
@IdClass(CrmTagServiziRichiestiPK.class)
@NamedQuery(name="CrmTagServiziRichiesti.findAll", query="SELECT c FROM CrmTagServiziRichiesti c")
public class CrmTagServiziRichiesti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Id
	@Column(unique=true, nullable=false, length=250)
	private String tag;

	public CrmTagServiziRichiesti() {}
	
	public int getAzienda() {
		return this.azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public CrmTagServiziRichiestiPK getPK() {
		CrmTagServiziRichiestiPK pk = new CrmTagServiziRichiestiPK();
		pk.setAzienda(azienda);
		pk.setTag(tag);
		return pk;
	}


}
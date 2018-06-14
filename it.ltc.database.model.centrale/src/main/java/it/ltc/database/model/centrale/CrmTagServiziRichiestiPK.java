package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the crm_tag_servizi_richiesti database table.
 * 
 */
@Embeddable
public class CrmTagServiziRichiestiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Column(unique=true, nullable=false, length=250)
	private String tag;

	public CrmTagServiziRichiestiPK() {}
	
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CrmTagServiziRichiestiPK)) {
			return false;
		}
		CrmTagServiziRichiestiPK castOther = (CrmTagServiziRichiestiPK)other;
		return 
			(this.azienda == castOther.azienda)
			&& this.tag.equals(castOther.tag);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.azienda;
		hash = hash * prime + this.tag.hashCode();
		
		return hash;
	}
}
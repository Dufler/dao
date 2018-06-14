package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the crm_tag_categoria_merceologica database table.
 * 
 */
@Entity
@Table(name="crm_tag_categoria_merceologica")
@IdClass(CrmTagCategoriaMerceologicaPK.class)
@NamedQuery(name="CrmTagCategoriaMerceologica.findAll", query="SELECT c FROM CrmTagCategoriaMerceologica c")
public class CrmTagCategoriaMerceologica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int azienda;

	@Id
	@Column(unique=true, nullable=false, length=250)
	private String tag;

	public CrmTagCategoriaMerceologica() {}
	
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

	public CrmTagCategoriaMerceologicaPK getPK() {
		CrmTagCategoriaMerceologicaPK pk = new CrmTagCategoriaMerceologicaPK();
		pk.setAzienda(azienda);
		pk.setTag(tag);
		return pk;
	}

}
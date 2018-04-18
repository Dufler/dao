package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_features_join database table.
 * 
 */
@Entity
@Table(name="utente_features_join")
@NamedQuery(name="UtenteFeaturesJoin.findAll", query="SELECT u FROM UtenteFeaturesJoin u")
public class UtenteFeaturesJoin implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UtenteFeaturesJoinPK id;

	public UtenteFeaturesJoin() {
	}

	public UtenteFeaturesJoinPK getId() {
		return this.id;
	}

	public void setId(UtenteFeaturesJoinPK id) {
		this.id = id;
	}

}
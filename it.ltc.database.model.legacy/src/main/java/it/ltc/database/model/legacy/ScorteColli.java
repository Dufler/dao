package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ScorteColli database table.
 * 
 */
@Entity
@Table(name="ScorteColli")
@NamedQuery(name="ScorteColli.findAll", query="SELECT s FROM ScorteColli s")
public class ScorteColli implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idScorteColli;

	@Column(length=50)
	private String iduniarticolo;

	@Column(name="KeyColloCar", length=50)
	private String keyColloCar;

	/**
	 * Il totale dei pezzi contenuti nel collo.
	 */
	@Column(name="Pezzi")
	private int pezzi;

	@Column(name="SessioneLavoro", length=50)
	private String sessioneLavoro;

	@Column(length=50)
	private String ubicazione;

	public ScorteColli() {}

	public int getIdScorteColli() {
		return this.idScorteColli;
	}

	public void setIdScorteColli(int idScorteColli) {
		this.idScorteColli = idScorteColli;
	}

	public String getIduniarticolo() {
		return this.iduniarticolo;
	}

	public void setIduniarticolo(String iduniarticolo) {
		this.iduniarticolo = iduniarticolo;
	}

	public String getKeyColloCar() {
		return this.keyColloCar;
	}

	public void setKeyColloCar(String keyColloCar) {
		this.keyColloCar = keyColloCar;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public String getSessioneLavoro() {
		return this.sessioneLavoro;
	}

	public void setSessioneLavoro(String sessioneLavoro) {
		this.sessioneLavoro = sessioneLavoro;
	}

	public String getUbicazione() {
		return this.ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

}
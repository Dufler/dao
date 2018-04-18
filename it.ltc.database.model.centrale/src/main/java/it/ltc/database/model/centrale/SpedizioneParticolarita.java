package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the spedizione_particolarita database table.
 * 
 */
@Entity
@Table(name="spedizione_particolarita")
@NamedQuery(name="SpedizioneParticolarita.findAll", query="SELECT s FROM SpedizioneParticolarita s")
public class SpedizioneParticolarita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_spedizione", unique=true, nullable=false)
	private int idSpedizione;

	@Column(nullable=false)
	private boolean appuntamento;

	public SpedizioneParticolarita() {
	}

	public int getIdSpedizione() {
		return this.idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public boolean getAppuntamento() {
		return this.appuntamento;
	}

	public void setAppuntamento(boolean appuntamento) {
		this.appuntamento = appuntamento;
	}

}
package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utente_log_evento database table.
 * 
 */
@Entity
@Table(name="utente_log_evento")
@NamedQuery(name="UtenteLogEvento.findAll", query="SELECT u FROM UtenteLogEvento u")
public class UtenteLogEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codice;

	@Column(nullable=false, length=45)
	private String descrizione;

	public UtenteLogEvento() {
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
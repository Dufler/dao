package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tipo_evento_uscita database table.
 * 
 */
@Entity
@Table(name="tipo_evento_uscita")
@NamedQuery(name="TipoEventoUscita.findAll", query="SELECT t FROM TipoEventoUscita t")
public class TipoEventoUscita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nome;

	private String descrizione;

	public TipoEventoUscita() {
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
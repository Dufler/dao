package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the spedizione_servizio database table.
 * 
 */
@Entity
@Table(name="spedizione_servizio")
@NamedQuery(name="SpedizioneServizio.findAll", query="SELECT s FROM SpedizioneServizio s")
public class SpedizioneServizio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=3, columnDefinition="CHAR")
	private String codice;

	@Column(name="descrizione", columnDefinition="TINYTEXT")
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	public SpedizioneServizio() {}

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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
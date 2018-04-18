package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the spedizione_contrassegno_tipo database table.
 * 
 */
@Entity
@Table(name="spedizione_contrassegno_tipo")
@NamedQuery(name="SpedizioneContrassegnoTipo.findAll", query="SELECT s FROM SpedizioneContrassegnoTipo s")
public class SpedizioneContrassegnoTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=2, columnDefinition="CHAR")
	private String codice;

	@Column(nullable=false, length=100)
	private String nome;

	public SpedizioneContrassegnoTipo() {}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
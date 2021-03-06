package it.ltc.database.model.utente;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the commessa database table.
 * 
 */
@Entity
@Table(name="commessa")
@NamedQuery(name="CommessaUtenti.findAll", query="SELECT c FROM CommessaUtenti c")
public class CommessaUtenti implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descrizione;

	@Column(name="id_sede")
	private int idSede;

	@Column(name="legacy", nullable=false)
	private boolean legacy;

	private String nome;

	@Column(name="nome_risorsa")
	private String nomeRisorsa;

	public CommessaUtenti() {}

	@Override
	public String toString() {
		return "CommessaUtenti [id=" + id + ", nome=" + nome + ", nomeRisorsa=" + nomeRisorsa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommessaUtenti other = (CommessaUtenti) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdSede() {
		return this.idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public boolean isLegacy() {
		return this.legacy;
	}

	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeRisorsa() {
		return this.nomeRisorsa;
	}

	public void setNomeRisorsa(String nomeRisorsa) {
		this.nomeRisorsa = nomeRisorsa;
	}

}
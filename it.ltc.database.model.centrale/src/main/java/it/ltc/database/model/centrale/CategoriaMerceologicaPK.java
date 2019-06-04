package it.ltc.database.model.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategoriaMerceologicaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private String nome;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int commessa;
	
	public CategoriaMerceologicaPK() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		CategoriaMerceologicaPK other = (CategoriaMerceologicaPK) obj;
		if (commessa != other.commessa)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}

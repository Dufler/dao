package it.ltc.database.model.legacy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pakiTestaLogStato")
public class PakiTestaLogStato implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(nullable=false)
	private int idTestaPaki;
	
	@Column(nullable=false, length=15)
	private String stato;
	
	@Column(nullable=false)
	private Date dataOra;
	
	public PakiTestaLogStato() {}

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
		PakiTestaLogStato other = (PakiTestaLogStato) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PakiTestaLogStato [idTestaPaki=" + idTestaPaki + ", stato=" + stato + ", dataOra=" + dataOra + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTestaPaki() {
		return idTestaPaki;
	}

	public void setIdTestaPaki(int idTestaPaki) {
		this.idTestaPaki = idTestaPaki;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

}

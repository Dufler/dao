package it.ltc.database.model.legacy.centrale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CorrieriPerCliente")
public class CorrieriPerCliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;
	
	@Column(name="Corriere", length=10, nullable=false)
	private String corriere;
	
	@Column(name="Cliente", length=50, nullable=false)
	private String cliente;
	
	@Column(name="CodiceCliente", length=20, nullable=false)
	private String codiceCliente;
	
	@Column(name="MailCorriere", length=500)
	private String mailCorriere;
	
	@Column(name="MailResponsabile", length=500)
	private String mailResponsabile;
	
	@Column(name="CartellaFile", length=250, nullable=false)
	private String cartellaFile;
	
	@Column(name="CartellaStorico", length=250, nullable=false)
	private String cartellaStorico;
	
	public CorrieriPerCliente() {}

	@Override
	public String toString() {
		return "CorrieriPerCliente [corriere=" + corriere + ", cliente=" + cliente + ", codiceCliente=" + codiceCliente
				+ "]";
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
		CorrieriPerCliente other = (CorrieriPerCliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorriere() {
		return corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getMailCorriere() {
		return mailCorriere;
	}

	public void setMailCorriere(String mailCorriere) {
		this.mailCorriere = mailCorriere;
	}

	public String getMailResponsabile() {
		return mailResponsabile;
	}

	public void setMailResponsabile(String mailResponsabile) {
		this.mailResponsabile = mailResponsabile;
	}

	public String getCartellaFile() {
		return cartellaFile;
	}

	public void setCartellaFile(String cartellaFile) {
		this.cartellaFile = cartellaFile;
	}

	public String getCartellaStorico() {
		return cartellaStorico;
	}

	public void setCartellaStorico(String cartellaStorico) {
		this.cartellaStorico = cartellaStorico;
	}

}

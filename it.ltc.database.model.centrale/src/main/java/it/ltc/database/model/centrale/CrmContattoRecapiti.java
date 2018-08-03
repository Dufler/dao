package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the crm_contatto_recapiti database table.
 * 
 */
@Entity
@Table(name="crm_contatto_recapiti")
//@NamedQuery(name="CrmContattoRecapiti.findAll", query="SELECT c FROM CrmContattoRecapiti c")
public class CrmContattoRecapiti implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Tipo { TELEFONO, EMAIL, SKYPE, ALTRO }

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int contatto;

	@Column(nullable=false, length=200)
	private String recapito;

	@Column(nullable=false, length=1, name="tipo", columnDefinition="ENUM('TELEFONO', 'EMAIL', 'SKYPE', 'ALTRO')")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	public CrmContattoRecapiti() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContatto() {
		return this.contatto;
	}

	public void setContatto(int contatto) {
		this.contatto = contatto;
	}

	public String getRecapito() {
		return this.recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
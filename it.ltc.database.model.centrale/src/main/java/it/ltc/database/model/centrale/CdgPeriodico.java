package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cdg_periodico database table.
 * 
 */
@Entity
@Table(name="cdg_periodico")
@NamedQuery(name="CdgPeriodico.findAll", query="SELECT c FROM CdgPeriodico c")
public class CdgPeriodico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Tipo { INGRESSO, USCITA }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=250)
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	@Column(nullable=false)
	private int periodo;

	@Column(nullable=false, length=1, columnDefinition="ENUM('INGRESSO', 'USCITA')")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@Column(nullable=false, precision=10, scale=3)
	private double valore;

	public CdgPeriodico() {
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}
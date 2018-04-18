package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_documento")
	private Date dataDocumento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ricezione")
	private Date dataRicezione;

	@Lob
	private byte[] dati;

	private String fonte;

	private String riferimento;

	private String tipo;

	public Documento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataDocumento() {
		return this.dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Date getDataRicezione() {
		return this.dataRicezione;
	}

	public void setDataRicezione(Date dataRicezione) {
		this.dataRicezione = dataRicezione;
	}

	public byte[] getDati() {
		return this.dati;
	}

	public void setDati(byte[] dati) {
		this.dati = dati;
	}

	public String getFonte() {
		return this.fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getRiferimento() {
		return this.riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
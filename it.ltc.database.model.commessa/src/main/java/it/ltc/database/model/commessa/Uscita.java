package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the uscita database table.
 * 
 */
@Entity
@NamedQuery(name="Uscita.findAll", query="SELECT u FROM Uscita u")
public class Uscita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte archiviato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_consegna")
	private Date dataConsegna;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ricezione")
	private Date dataRicezione;

	@Column(name="id_documento")
	private int idDocumento;

	private String note;

	@Column(name="pezzi_evasi")
	private int pezziEvasi;

	@Column(name="pezzi_richiesti")
	private int pezziRichiesti;

	private String priorita;

	@Column(name="riferimento_cliente")
	private String riferimentoCliente;

	@Column(name="riferimento_documento_fiscale")
	private String riferimentoDocumentoFiscale;

	private String stato;

	private String tipo;

	public Uscita() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getArchiviato() {
		return this.archiviato;
	}

	public void setArchiviato(byte archiviato) {
		this.archiviato = archiviato;
	}

	public Date getDataConsegna() {
		return this.dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Date getDataRicezione() {
		return this.dataRicezione;
	}

	public void setDataRicezione(Date dataRicezione) {
		this.dataRicezione = dataRicezione;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPezziEvasi() {
		return this.pezziEvasi;
	}

	public void setPezziEvasi(int pezziEvasi) {
		this.pezziEvasi = pezziEvasi;
	}

	public int getPezziRichiesti() {
		return this.pezziRichiesti;
	}

	public void setPezziRichiesti(int pezziRichiesti) {
		this.pezziRichiesti = pezziRichiesti;
	}

	public String getPriorita() {
		return this.priorita;
	}

	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}

	public String getRiferimentoCliente() {
		return this.riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public String getRiferimentoDocumentoFiscale() {
		return this.riferimentoDocumentoFiscale;
	}

	public void setRiferimentoDocumentoFiscale(String riferimentoDocumentoFiscale) {
		this.riferimentoDocumentoFiscale = riferimentoDocumentoFiscale;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
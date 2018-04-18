package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fattura_voce database table.
 * 
 */
@Entity
@Table(name="fattura_voce")
@NamedQuery(name="FatturaVoce.findAll", query="SELECT f FROM FatturaVoce f")
public class FatturaVoce implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="costo_unitario", precision=10, scale=3)
	private Double costoUnitario;

	@Column(name="id_ambito", nullable=false)
	private int idAmbito;

	@Column(name="id_commessa", nullable=false)
	private int idCommessa;

	@Column(name="id_documento", nullable=false)
	private int idDocumento;

	@Column(name="id_listino", nullable=false)
	private int idListino;

	@Column(name="id_riferimento")
	private int idRiferimento;

	@Column(name="id_sotto_ambito", nullable=false)
	private int idSottoAmbito;

	@Column(name="id_voce", nullable=false)
	private int idVoce;

	@Column(nullable=false, precision=10, scale=3)
	private Double importo;

	@Column(name="note", columnDefinition="TEXT")
	private String note;

	@Column(precision=10, scale=2)
	private Double quantita;

	public FatturaVoce() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getCostoUnitario() {
		return this.costoUnitario;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public int getIdAmbito() {
		return this.idAmbito;
	}

	public void setIdAmbito(int idAmbito) {
		this.idAmbito = idAmbito;
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdListino() {
		return this.idListino;
	}

	public void setIdListino(int idListino) {
		this.idListino = idListino;
	}

	public int getIdRiferimento() {
		return this.idRiferimento;
	}

	public void setIdRiferimento(int idRiferimento) {
		this.idRiferimento = idRiferimento;
	}

	public int getIdSottoAmbito() {
		return this.idSottoAmbito;
	}

	public void setIdSottoAmbito(int idSottoAmbito) {
		this.idSottoAmbito = idSottoAmbito;
	}

	public int getIdVoce() {
		return this.idVoce;
	}

	public void setIdVoce(int idVoce) {
		this.idVoce = idVoce;
	}

	public Double getImporto() {
		return this.importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getQuantita() {
		return this.quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

}
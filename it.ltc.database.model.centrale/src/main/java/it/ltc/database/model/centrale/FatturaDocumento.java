package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fattura_documento database table.
 * 
 */
@Entity
@Table(name="fattura_documento", uniqueConstraints={@UniqueConstraint(columnNames = {"numero_fattura" , "anno_fattura"})})
@NamedQuery(name="FatturaDocumento.findAll", query="SELECT f FROM FatturaDocumento f")
public class FatturaDocumento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Stato {GENERATA, APPROVATA, ARCHIVIATA}
	
	/**
	 * Enum che definisce le possibili aliquote IVA.
	 * @author Damiano
	 *
	 */
	public enum Iva {
		
		IVA10("IVA 10%", 10),
		IVA22("IVA 22%", 22),
		E146("Esente Art.146(1)(e)-Dir.2006/112EC", 0),
		EB("Art. 15 escluso", 0),
		ES("Art. 10 esente", 0),
		ES7("Esente art.9/IC dpr 633/72", 0),
		FVI("Fuori campo iva art.1 2 3", 0),
		NI7("Non impon.art.7-dpr633/72 26/10/72", 0),
		VLI("vendita clienti con lett. int.art.8 c.2", 0);
		
		private final String descrizione;
		private final double aliquota;
		
		private Iva(String descrizione, double aliquota) {
			this.descrizione = descrizione;
			this.aliquota = aliquota;
		}
		
		@Override
		public String toString() {
			return descrizione;
		}

		public double getAliquota() {
			return aliquota;
		}
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_generazione", nullable=false)
	private Date dataGenerazione;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_emissione", nullable=true)
	private Date dataEmissione;

	@Column(name="id_ambito", nullable=false)
	private int idAmbito;
	
	@Column(name="id_commessa", nullable=false)
	private int idCommessa;

	@Column(name="mese_anno", length=7, columnDefinition="CHAR")
	private String meseAnno;

	@Column(name="note", columnDefinition="MEDIUMTEXT")
	private String note;

	@Column(nullable=false, length=1, columnDefinition="ENUM('GENERATA', 'APPROVATA', 'ARCHIVIATA')")
	@Enumerated(EnumType.STRING)
	private Stato stato;

	@Column(name="utente_creatore", nullable=false, length=50)
	private String utenteCreatore;
	
	@Column(nullable=false, length=5, name="modalita_pagamento")
	private String modalitaPagamento;
	
	@Column(nullable=false, length=1, columnDefinition="ENUM('IVA10', 'IVA22', 'E146', 'EB', 'ES', 'ES7', 'FVI', 'NI7', 'VLI')")
	@Enumerated(EnumType.STRING)
	private Iva iva;
	
	@Column(name="coordinate_pagamento", insertable=false)
	private Integer coordinatePagamento;
	
	@Column(name="numero_fattura", nullable=false)
	private int numeroFattura;
	
	@Column(name="anno_fattura", nullable=false)
	private int annoFattura;
	
	@Column(name="descrizione_fattura", columnDefinition="TEXT")
	private String descrizioneFattura;

	public FatturaDocumento() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataGenerazione() {
		return this.dataGenerazione;
	}

	public void setDataGenerazione(Date dataGenerazione) {
		this.dataGenerazione = dataGenerazione;
	}

	public int getIdAmbito() {
		return this.idAmbito;
	}

	public void setIdAmbito(int idAmbito) {
		this.idAmbito = idAmbito;
	}

	public int getIdCommessa() {
		return idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public String getMeseAnno() {
		return this.meseAnno;
	}

	public void setMeseAnno(String meseAnno) {
		this.meseAnno = meseAnno;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Stato getStato() {
		return this.stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public String getUtenteCreatore() {
		return this.utenteCreatore;
	}

	public void setUtenteCreatore(String utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}

	public String getModalitaPagamento() {
		return modalitaPagamento;
	}

	public void setModalitaPagamento(String modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

	public Integer getCoordinatePagamento() {
		return coordinatePagamento;
	}

	public void setCoordinatePagamento(Integer coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public int getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(int numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public int getAnnoFattura() {
		return annoFattura;
	}

	public void setAnnoFattura(int annoFattura) {
		this.annoFattura = annoFattura;
	}

	public String getDescrizioneFattura() {
		return descrizioneFattura;
	}

	public void setDescrizioneFattura(String descrizioneFattura) {
		this.descrizioneFattura = descrizioneFattura;
	}

}
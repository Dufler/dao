package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ordine database table.
 * 
 */
@Entity
@Table(name="ordine")
@NamedQuery(name="Ordine.findAll", query="SELECT o FROM Ordine o")
public class Ordine implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum TipoOrdine {
		
		CAM("Campionario", "Ordine per il campionario."), 
		COR("Corriere", "Ritiro e spedizione di merce."), 
		PRN("Produzione (Senza spedizione)", "Ordine di produzione con spedizione curata dal cliente."), 
		PRS("Produzione", "Ordine di produzione con spedizione curata da LTC."), 
		S2S("Shop To Shop", "Ritiro presso il cliente e spedizione verso altro cliente."), 
		TRC("Trasferimento a cliente", "Trasferimento di merce da magazzino LTC a magazzino cliente."), 
		TRM("Trasferimento a magazzino", "Trasferimento di merce da magazzino LTC ad altro magazzino LTC."), 
		WEN("E-Commerce (Senza spedizione)", "Ordine e-commerce con spedizione curata dal cliente."), 
		WES("E-Commerce", "Ordine e-commerce con spedizione curata da LTC."), 
		XXX("Test", "Ordine inserito a fini di test.");
		
		private final String nome;
		private final String descrizione;
		
		private TipoOrdine(String nome, String descrizione) {
			this.nome = nome;
			this.descrizione = descrizione;
		}
		
		@Override
		public String toString() {
			return nome;
		}
		
		public String getDescrizione() {
			return descrizione;
		}
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private boolean archiviato;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_consegna")
	private Date dataConsegna;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ricezione", nullable=false)
	private Date dataRicezione;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultimo_stato")
	private Date dataUltimoStato;

	@Column(nullable=false)
	private boolean fatturazione;

	@Column(name="id_commessa", nullable=false)
	private int idCommessa;

	@Column(name="note", columnDefinition="TINYTEXT")
	private String note;

	@Column(nullable=false, length=45)
	private String origine;

	@Column(nullable=false)
	private int pezzi;

	@Column(nullable=false)
	private int priorita;

	@Column(name="riferimento_cliente", nullable=false, length=45)
	private String riferimentoCliente;

	@Column(name="riferimento_documento_fiscale", length=45)
	private String riferimentoDocumentoFiscale;

	@Column(name="riferimento_interno", nullable=false, length=45)
	private String riferimentoInterno;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String stato;

	@Column(nullable=false, length=1, columnDefinition="ENUM('CAM', 'COR', 'PRN', 'PRS', 'S2S', 'TRC', 'TRM', 'WEN', 'WES', 'XXX')")
	@Enumerated(EnumType.STRING)
	private TipoOrdine tipo;

	public Ordine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getArchiviato() {
		return this.archiviato;
	}

	public void setArchiviato(boolean archiviato) {
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

	public Date getDataUltimoStato() {
		return this.dataUltimoStato;
	}

	public void setDataUltimoStato(Date dataUltimoStato) {
		this.dataUltimoStato = dataUltimoStato;
	}

	public boolean getFatturazione() {
		return this.fatturazione;
	}

	public void setFatturazione(boolean fatturazione) {
		this.fatturazione = fatturazione;
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrigine() {
		return this.origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public int getPriorita() {
		return this.priorita;
	}

	public void setPriorita(int priorita) {
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

	public String getRiferimentoInterno() {
		return this.riferimentoInterno;
	}

	public void setRiferimentoInterno(String riferimentoInterno) {
		this.riferimentoInterno = riferimentoInterno;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public TipoOrdine getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoOrdine tipo) {
		this.tipo = tipo;
	}

}
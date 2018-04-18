package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the azienda database table.
 * 
 */
@Entity
@Table(name="azienda")
@NamedQuery(name="Azienda.findAll", query="SELECT a FROM Azienda a")
public class Azienda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Valutazione { DA_VALUTARE, AFFIDABILE, NON_AFFIDABILE }
	
	public enum TipoLogistica { INTERNA, ESTERNA, ENTRAMBE, NESSUNA }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private int appetibile;

	@Column(length=250)
	private String email;

	@Column(name="in_trattiva", nullable=false)
	private boolean inTrattiva;

	private Integer indirizzo;

	@Column(name="partita_iva", length=250)
	private String partitaIva;

	@Column(name="ragione_sociale", nullable=false, length=250)
	private String ragioneSociale;

	@Column(name="sito_web", length=250)
	private String sitoWeb;

	@Column(length=100)
	private String telefono;

	@Column(nullable=false, length=1, name="tipo_logistica", columnDefinition="ENUM('INTERNA', 'ESTERNA', 'ENTRAMBE', 'NESSUNA')")
	@Enumerated(EnumType.STRING)
	private TipoLogistica tipoLogistica;

	@Column(nullable=false, length=1, columnDefinition="ENUM('DA_VALUTARE', 'AFFIDABILE', 'NON_AFFIDABILE')")
	@Enumerated(EnumType.STRING)
	private Valutazione valutazione;

	public Azienda() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppetibile() {
		return this.appetibile;
	}

	public void setAppetibile(int appetibile) {
		this.appetibile = appetibile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getInTrattiva() {
		return this.inTrattiva;
	}

	public void setInTrattiva(boolean inTrattiva) {
		this.inTrattiva = inTrattiva;
	}

	public Integer getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(Integer indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getSitoWeb() {
		return this.sitoWeb;
	}

	public void setSitoWeb(String sitoWeb) {
		this.sitoWeb = sitoWeb;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoLogistica getTipoLogistica() {
		return this.tipoLogistica;
	}

	public void setTipoLogistica(TipoLogistica tipoLogistica) {
		this.tipoLogistica = tipoLogistica;
	}

	public Valutazione getValutazione() {
		return this.valutazione;
	}

	public void setValutazione(Valutazione valutazione) {
		this.valutazione = valutazione;
	}

}
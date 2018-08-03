package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the azienda database table.
 * 
 */
@Entity
@Table(name="crm_azienda")
@NamedQuery(name="Azienda.findAll", query="SELECT a FROM Azienda a")
public class Azienda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum Valutazione { DA_VALUTARE, AFFIDABILE, NON_AFFIDABILE }
	
	public enum TipoLogistica { INTERNA, ESTERNA, ENTRAMBE, NESSUNA }
	
	public enum Trattativa { NO, IN_TRATTATIVA, CLIENTE, EX_CLIENTE }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private int appetibile;

	@Column(length=250)
	private String email;

	@Column(name="in_trattiva", nullable=false, length=1, columnDefinition="ENUM('NO', 'IN_TRATTATIVA', 'CLIENTE', 'EX_CLIENTE')")
	@Enumerated(EnumType.STRING)
	private Trattativa inTrattiva;

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
	
	@Column(name="descrizione", columnDefinition="MEDIUMTEXT")
	private String descrizione;

	public Azienda() {}

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
		Azienda other = (Azienda) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Azienda [id=" + id + ", ragioneSociale=" + ragioneSociale + "]";
	}

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

	public Trattativa getInTrattiva() {
		return this.inTrattiva;
	}

	public void setInTrattiva(Trattativa inTrattiva) {
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
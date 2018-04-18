package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ingresso database table.
 * 
 */
@Entity
@NamedQuery(name="Ingresso.findAll", query="SELECT i FROM Ingresso i")
public class Ingresso implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum StatoIngresso {INSERITO, ARRIVATO, IN_LAVORAZIONE, LAVORATO, CHIUSO}
	
	public enum TipoIngresso {CARICO, RESO, CAMPIONARIO}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int colli;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_arrivo")
	private Date dataArrivo;

	@Column(name="id_documento")
	private int idDocumento;

	@Column(name="id_fornitore")
	private int idFornitore;

	@Column(name="pezzi_letti")
	private int pezziLetti;

	@Column(name="pezzi_stimati")
	private int pezziStimati;

	@Column(name="riferimento_cliente")
	private String riferimentoCliente;

	@Column(name="stato", length=1, columnDefinition="ENUM('INSERITO', 'ARRIVATO', 'IN_LAVORAZIONE', 'LAVORATO', 'CHIUSO')")
	@Enumerated(EnumType.STRING)
	private StatoIngresso stato;

	@Column(name="tipo", length=1, columnDefinition="ENUM('CARICO', 'RESO', 'CAMPIONARIO')")
	@Enumerated(EnumType.STRING)
	private TipoIngresso tipo;

	public Ingresso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColli() {
		return this.colli;
	}

	public void setColli(int colli) {
		this.colli = colli;
	}

	public Date getDataArrivo() {
		return this.dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdFornitore() {
		return this.idFornitore;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public int getPezziLetti() {
		return this.pezziLetti;
	}

	public void setPezziLetti(int pezziLetti) {
		this.pezziLetti = pezziLetti;
	}

	public int getPezziStimati() {
		return this.pezziStimati;
	}

	public void setPezziStimati(int pezziStimati) {
		this.pezziStimati = pezziStimati;
	}

	public String getRiferimentoCliente() {
		return this.riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public StatoIngresso getStato() {
		return this.stato;
	}

	public void setStato(StatoIngresso stato) {
		this.stato = stato;
	}

	public TipoIngresso getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoIngresso tipo) {
		this.tipo = tipo;
	}

}
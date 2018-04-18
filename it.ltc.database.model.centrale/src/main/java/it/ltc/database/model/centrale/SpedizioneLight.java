package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="spedizione")
@NamedQuery(name="SpedizioneLight.findAll", query="SELECT s FROM SpedizioneLight s")
public class SpedizioneLight implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Archiviazione {NO, SI, ELIMINATA}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private boolean contrassegno;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_partenza")
	private Date dataPartenza;
	
	@Column(nullable=false)
	private boolean giacenza;

	@Column(name="id_commessa", nullable=false)
	private int idCommessa;

	@Column(name="id_corriere")
	private int idCorriere;
	
	@Column(name="in_ritardo", nullable=false)
	private boolean inRitardo;
	
	@Column(name="riferimento_cliente", length=45)
	private String riferimentoCliente;
	
	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String stato;
	
	@Column(name="ragione_sociale_destinatario", length=45)
	private String ragioneSocialeDestinatario;
	
	@Column(nullable=false, length=1, columnDefinition="ENUM('NO', 'SI', 'ELIMINATA')")
	@Enumerated(EnumType.STRING)
	private Archiviazione archiviazione;
	
	public SpedizioneLight() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public boolean isGiacenza() {
		return giacenza;
	}

	public void setGiacenza(boolean giacenza) {
		this.giacenza = giacenza;
	}

	public int getIdCommessa() {
		return idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public int getIdCorriere() {
		return idCorriere;
	}

	public void setIdCorriere(int idCorriere) {
		this.idCorriere = idCorriere;
	}

	public boolean isInRitardo() {
		return inRitardo;
	}

	public void setInRitardo(boolean inRitardo) {
		this.inRitardo = inRitardo;
	}

	public String getRiferimentoCliente() {
		return riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getRagioneSocialeDestinatario() {
		return ragioneSocialeDestinatario;
	}

	public void setRagioneSocialeDestinatario(String ragioneSocialeDestinatario) {
		this.ragioneSocialeDestinatario = ragioneSocialeDestinatario;
	}

	public Archiviazione getArchiviazione() {
		return archiviazione;
	}

	public void setArchiviazione(Archiviazione archiviazione) {
		this.archiviazione = archiviazione;
	}

}

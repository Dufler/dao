package it.ltc.model.interfaces.ordine;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.indirizzo.MIndirizzo;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MOrdine implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	//private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private int id;
	
	private Date dataOrdine;
	private Date dataConsegna;
	private TipoOrdine tipo;
	private Integer priorita;
	private String note;
	private String riferimentoOrdine;
	
	private String stato;
	private MIndirizzo destinatario;
	private MIndirizzo mittente;
	private String corriere;
	private String codiceCorriere;
	private String servizioCorriere;
	private MContrassegno contrassegno;
	private MAssicurazione assicurazione;
	private MParticolarita particolarita;
	private TipoIDProdotto tipoIdentificazioneProdotti;
	private Double valoreDoganale;
	private String codiceTracking;
	
	private final List<ProdottoOrdinato> prodotti;
	
	private String riferimentoDocumento;
	private Date dataDocumento;
	private String tipoDocumento;
	
	public MOrdine() {
		prodotti = new LinkedList<>();
	}
	
	@Override
	public void valida() throws ModelValidationException {
		if (dataConsegna != null) {
			if (dataConsegna.before(new Date()))
				throw new ModelValidationException("La data indicata è precedente a oggi.");
		}
		if (tipo == null) {
			throw new ModelValidationException("Bisogna specificare un tipo di ordine.");
		} else if (tipo == TipoOrdine.PRN || tipo == TipoOrdine.WEN) {
			//Per gli ordini in cui la spedizione è curata dal cliente mi assicuro di ricevere il loro codice di fatturazione
			if (codiceCorriere == null || codiceCorriere.isEmpty())
				throw new ModelValidationException("Bisogna specificare il vostro codice corriere per gli ordini di questo tipo.");
		}
		if (tipoIdentificazioneProdotti == null)
			throw new ModelValidationException("Bisogna specificare un tipo di identificazione per i prodotti.");
		if (riferimentoOrdine == null || riferimentoOrdine.isEmpty())
			throw new ModelValidationException("Bisogna specificare un riferimento per l'ordine. Es. purchase order number");
		if (destinatario == null) {
			throw new ModelValidationException("Bisogna specificare un destinatario.");
		} else {
			destinatario.valida();
		}
		if (mittente == null) {
			throw new ModelValidationException("Bisogna specificare un mittente.");
		} else {
			mittente.valida();
		}
		if (corriere == null || corriere.isEmpty())
			throw new ModelValidationException("Bisogna specificare un corriere.");
		else {
			try {
				Corriere.valueOf(corriere.toUpperCase());
			} catch (IllegalArgumentException e) {
				//Il tipo di ordine non è valido
				String errorMessage = "Il corriere indicato non è valido. L'elenco completo dei corrieri è: ";
				for (Corriere corriere : Corriere.values()) {
					errorMessage += corriere + " ";
				}
				errorMessage = errorMessage.trim();
				throw new ModelValidationException(errorMessage);
			}
		}
		if (servizioCorriere == null || servizioCorriere.isEmpty())
			throw new ModelValidationException("Bisogna un tipo di servizio per la spedizione.");
		else {
			try {
				ServizioCorriere.valueOf(servizioCorriere.toUpperCase());
			} catch (IllegalArgumentException e) {
				//Il tipo di ordine non è valido
				String errorMessage = "Il servizio corriere indicato non è valido. L'elenco completo dei livelli di servizio è: ";
				for (ServizioCorriere servizio : ServizioCorriere.values()) {
					errorMessage += servizio + " ";
				}
				errorMessage = errorMessage.trim();
				throw new ModelValidationException(errorMessage);
			}
		}
		if (priorita != null) {
			if (priorita < 1 || priorita > 10)
				throw new ModelValidationException("La priorita' può assumere solo valori compresi fra 1 e 10.");
		}
		if (valoreDoganale != null && valoreDoganale <= 0) {
			throw new ModelValidationException("Il valore doganale non può essere minore o uguale a 0.00 euro");
		}
		if (contrassegno != null) {
			contrassegno.valida();
		}
		if (assicurazione != null) {
			assicurazione.valida();
		}
		if (particolarita != null) {
			particolarita.valida();
		}
		if (prodotti == null || prodotti.size() == 0) {
			throw new ModelValidationException("Bisogna elencare i prodotti.");
		} else {
			for (ProdottoOrdinato prodotto : prodotti) {
				prodotto.valida(tipoIdentificazioneProdotti);
			}
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}
	
	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	public Date getDataConsegna() {
		return dataConsegna;
	}
	
	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	
	public TipoOrdine getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoOrdine tipo) {
		this.tipo = tipo;
	}
	
	public Integer getPriorita() {
		return priorita;
	}
	
	public void setPriorita(Integer priorita) {
		this.priorita = priorita;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getRiferimentoOrdine() {
		return riferimentoOrdine;
	}
	
	public void setRiferimentoOrdine(String riferimentoOrdine) {
		this.riferimentoOrdine = riferimentoOrdine;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public MIndirizzo getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(MIndirizzo destinatario) {
		this.destinatario = destinatario;
	}
	
	public MIndirizzo getMittente() {
		return mittente;
	}
	
	public void setMittente(MIndirizzo mittente) {
		this.mittente = mittente;
	}
	
	public String getCorriere() {
		return corriere;
	}
	
	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}
	
	public String getCodiceCorriere() {
		return codiceCorriere;
	}

	public void setCodiceCorriere(String codiceCorriere) {
		this.codiceCorriere = codiceCorriere;
	}

	public String getServizioCorriere() {
		return servizioCorriere;
	}

	public void setServizioCorriere(String servizioCorriere) {
		this.servizioCorriere = servizioCorriere;
	}

	public MContrassegno getContrassegno() {
		return contrassegno;
	}
	
	public void setContrassegno(MContrassegno contrassegno) {
		this.contrassegno = contrassegno;
	}
	
	public MAssicurazione getAssicurazione() {
		return assicurazione;
	}
	
	public void setAssicurazione(MAssicurazione assicurazione) {
		this.assicurazione = assicurazione;
	}
	
	public MParticolarita getParticolarita() {
		return particolarita;
	}
	
	public void setParticolarita(MParticolarita particolarita) {
		this.particolarita = particolarita;
	}

	public TipoIDProdotto getTipoIdentificazioneProdotti() {
		return tipoIdentificazioneProdotti;
	}

	public void setTipoIdentificazioneProdotti(TipoIDProdotto tipoIdentificazioneProdotti) {
		this.tipoIdentificazioneProdotti = tipoIdentificazioneProdotti;
	}

	public List<ProdottoOrdinato> getProdotti() {
		return prodotti;
	}

	public Double getValoreDoganale() {
		return valoreDoganale;
	}

	public void setValoreDoganale(Double valoreDoganale) {
		this.valoreDoganale = valoreDoganale;
	}

	public String getCodiceTracking() {
		return codiceTracking;
	}

	public void setCodiceTracking(String codiceTracking) {
		this.codiceTracking = codiceTracking;
	}

	public String getRiferimentoDocumento() {
		return riferimentoDocumento;
	}

	public void setRiferimentoDocumento(String riferimentoDocumento) {
		this.riferimentoDocumento = riferimentoDocumento;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public void aggiungiProdotto(ProdottoOrdinato prodotto) {
		prodotti.add(prodotto);
	}
	
	public int getQuantitaTotaleDaSpedire() {
		int totale = 0;
		for (ProdottoOrdinato prodotto : prodotti)
			totale += prodotto.getQuantita();
		return totale;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinatario == null) ? 0 : destinatario.hashCode());
		result = prime * result + ((riferimentoOrdine == null) ? 0 : riferimentoOrdine.hashCode());
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
		MOrdine other = (MOrdine) obj;
		if (destinatario == null) {
			if (other.destinatario != null)
				return false;
		} else if (!destinatario.equals(other.destinatario))
			return false;
		if (riferimentoOrdine == null) {
			if (other.riferimentoOrdine != null)
				return false;
		} else if (!riferimentoOrdine.equals(other.riferimentoOrdine))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MOrdine [tipo=" + tipo + ", riferimentoOrdine=" + riferimentoOrdine + ", destinatario=" + destinatario	+ "]";
	}

}

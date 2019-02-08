package it.ltc.model.interfaces.ordine;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelSimpleValidationException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.indirizzo.MIndirizzo;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MOrdine implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	protected int id;
	
	protected Date dataOrdine;
	protected Date dataConsegna;
	protected String tipo;
	protected Integer priorita;
	protected String note;
	protected String riferimentoOrdine;
	
	protected String stato;
	protected MIndirizzo destinatario;
	protected MIndirizzo mittente;
	
	protected MInfoSpedizione infoSpedizione;
	
//	private String corriere;
//	private String codiceCorriere;
//	private String servizioCorriere;
//	private MContrassegno contrassegno;
//	private MAssicurazione assicurazione;
//	private Double valoreDoganale;
//	private String codiceTracking;
	
	protected MParticolarita particolarita;
	protected TipoIDProdotto tipoIdentificazioneProdotti;
	
	protected final List<ProdottoOrdinato> prodotti;
	
	protected String riferimentoDocumento;
	protected Date dataDocumento;
	protected String tipoDocumento;
	
	public MOrdine() {
		prodotti = new LinkedList<>();
	}
	
	@Override
	public void valida() throws ModelValidationException {
		//Riferimento ordine (può essere sovrascritto)
		validaRiferimento();
		//Data consegna
//		if (dataConsegna != null) {
//			if (dataConsegna.before(new Date())) throw new ModelValidationException("La data indicata per la consegna è precedente a oggi.");
//		}
		//Tipo ordine
		if (tipo == null || tipo.isEmpty()) {
			throw new ModelValidationException("Bisogna specificare un tipo di ordine.");
		}
		//Identificazione dei prodotti
		if (tipoIdentificazioneProdotti == null)
			throw new ModelValidationException("Bisogna specificare un tipo di identificazione per i prodotti.");
		
		//Destinatario
		if (destinatario == null) {
			throw new ModelValidationException("Bisogna specificare un destinatario.");
		}
		
		//Mittente
		if (mittente == null) {
			throw new ModelValidationException("Bisogna specificare un mittente.");
		}
		
		if (priorita != null) {
			if (priorita < 1 || priorita > 10)
				throw new ModelValidationException("La priorita' può assumere solo valori compresi fra 1 e 10.");
		}
		
		if (infoSpedizione == null) 
			throw new ModelValidationException("Non sono state inserite informazioni sulla spedizione.");
		else 
			infoSpedizione.valida();
		
		if (particolarita != null) {
			particolarita.valida();
		}
		if (prodotti == null || prodotti.size() == 0) {
			throw new ModelSimpleValidationException("Bisogna elencare i prodotti.");
		} else {
			for (ProdottoOrdinato prodotto : prodotti) {
				prodotto.valida(tipoIdentificazioneProdotti);
			}
		}
	}
	
	protected void validaRiferimento() throws ModelValidationException {
		if (riferimentoOrdine == null || riferimentoOrdine.isEmpty())
			throw new ModelValidationException("Bisogna specificare un riferimento per l'ordine. Es. purchase order number");
		else if (riferimentoDocumento.length() > 20) {
			throw new ModelValidationException("Il riferimento per l'ordine è troppo lungo (MAX 20 caratteri)");
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
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
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
	
//	public String getCorriere() {
//		return corriere;
//	}
//	
//	public void setCorriere(String corriere) {
//		this.corriere = corriere;
//	}
//	
//	public String getCodiceCorriere() {
//		return codiceCorriere;
//	}
//
//	public void setCodiceCorriere(String codiceCorriere) {
//		this.codiceCorriere = codiceCorriere;
//	}
//
//	public String getServizioCorriere() {
//		return servizioCorriere;
//	}
//
//	public void setServizioCorriere(String servizioCorriere) {
//		this.servizioCorriere = servizioCorriere;
//	}
//
//	public MContrassegno getContrassegno() {
//		return contrassegno;
//	}
//	
//	public void setContrassegno(MContrassegno contrassegno) {
//		this.contrassegno = contrassegno;
//	}
//	
//	public MAssicurazione getAssicurazione() {
//		return assicurazione;
//	}
//	
//	public void setAssicurazione(MAssicurazione assicurazione) {
//		this.assicurazione = assicurazione;
//	}
	
	public MInfoSpedizione getInfoSpedizione() {
		return infoSpedizione;
	}

	public void setInfoSpedizione(MInfoSpedizione infoSpedizione) {
		//Se mi hanno passato info valide vado ad aggiungere il riferimento di questo ordine.
		if (infoSpedizione != null) infoSpedizione.aggiungiRiferimentoOrdine(riferimentoOrdine);
		this.infoSpedizione = infoSpedizione;
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

//	public Double getValoreDoganale() {
//		return valoreDoganale;
//	}
//
//	public void setValoreDoganale(Double valoreDoganale) {
//		this.valoreDoganale = valoreDoganale;
//	}
//
//	public String getCodiceTracking() {
//		return codiceTracking;
//	}
//
//	public void setCodiceTracking(String codiceTracking) {
//		this.codiceTracking = codiceTracking;
//	}

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

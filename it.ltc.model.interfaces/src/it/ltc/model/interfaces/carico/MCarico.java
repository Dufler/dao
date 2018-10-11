package it.ltc.model.interfaces.carico;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;
import it.ltc.model.interfaces.ordine.ProdottoOrdinato;
import it.ltc.model.interfaces.ordine.TipoIDProdotto;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MCarico implements ModelInterface {

	private static final long serialVersionUID = 1L;
	
	//private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private int id;
	
	private Date dataCarico;
	private Date dataArrivo;
	private String stato;
	private String tipo;	
	private String riferimento;
	private String fornitore;
	
	private String riferimentoDocumento;
	private Date dataDocumento;
	private String tipoDocumento;
	
	private String tipoIdentificazioneProdotti;
	private List<ProdottoOrdinato> prodotti;
	private String note;

	@Override
	public void valida() throws ModelValidationException {
		if (dataCarico == null) {
			String errorMessage = "Va indicata una data per il carico.";
			throw new ModelValidationException(errorMessage);
		}
		if (tipo == null || tipo.isEmpty())
			throw new ModelValidationException("Bisogna specificare un tipo di carico.");
		else {
			//TODO - La lista completa deve essere ancora definita.
			try {
				TipoCarico t = TipoCarico.valueOf(tipo.toUpperCase());
				if (t == TipoCarico.RESO) {
					
				}
			} catch (IllegalArgumentException e) {
				//Il tipo di ordine non è valido
				String errorMessage = "Il tipo di carico indicato non è valido. L'elenco completo delle tipologie è: ";
				for (TipoCarico categoria : TipoCarico.values()) {
					errorMessage += categoria + " ";
				}
				errorMessage = errorMessage.trim();
				throw new ModelValidationException(errorMessage);
			}
		}
		if (tipoIdentificazioneProdotti == null || tipoIdentificazioneProdotti.isEmpty())
			throw new ModelValidationException("Bisogna specificare un tipo di identificazione per i prodotti.");
		else {
			//TODO - La lista completa deve essere ancora definita.
			try {
				TipoIDProdotto.valueOf(tipoIdentificazioneProdotti.toUpperCase());
			} catch (IllegalArgumentException e) {
				//Il tipo di ordine non è valido
				String errorMessage = "Il tipo di identificazione per i prodotti indicato non è valido. L'elenco completo delle tipologie è: ";
				for (TipoIDProdotto categoria : TipoIDProdotto.values()) {
					errorMessage += categoria + " ";
				}
				errorMessage = errorMessage.trim();
				throw new ModelValidationException(errorMessage);
			}
		}
		if (riferimento == null || riferimento.isEmpty())
			throw new ModelValidationException("Bisogna specificare un riferimento per il carico. Es. purchase order number");
		if (prodotti == null || prodotti.size() == 0) {
			throw new ModelValidationException("Bisogna elencare i prodotti.");
		} else {
			for (ProdottoOrdinato prodotto : prodotti) {
				prodotto.valida(TipoIDProdotto.valueOf(tipoIdentificazioneProdotti.toUpperCase()));
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataCarico() {
		return dataCarico;
	}

	public void setDataCarico(Date dataCarico) {
		this.dataCarico = dataCarico;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRiferimento() {
		return riferimento;
	}

	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
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

	public String getTipoIdentificazioneProdotti() {
		return tipoIdentificazioneProdotti;
	}

	public void setTipoIdentificazioneProdotti(String tipoIdentificazioneProdotti) {
		this.tipoIdentificazioneProdotti = tipoIdentificazioneProdotti;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public List<ProdottoOrdinato> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottoOrdinato> prodotti) {
		this.prodotti = prodotti;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPezziStimati() {
		int totale = 0;
		for (ProdottoOrdinato prodotto : prodotti) {
			totale += prodotto.getQuantita();
		}
		return totale;
	}

}

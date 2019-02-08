package it.ltc.model.interfaces.carico;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;
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
	
	private TipoIDProdotto tipoIdentificazioneProdotti;
	private final List<MRigaCarico> prodotti;
	private String note;
	
	public MCarico() {
		prodotti = new LinkedList<>();
	}

	@Override
	public void valida() throws ModelValidationException {
		if (dataCarico == null) {
			String errorMessage = "Va indicata una data per il carico.";
			throw new ModelValidationException(errorMessage);
		}
		if (tipo == null || tipo.isEmpty())
			throw new ModelValidationException("Bisogna specificare un tipo di carico.");
		if (tipoIdentificazioneProdotti == null)
			throw new ModelValidationException("Bisogna specificare un tipo di identificazione per i prodotti.");
		if (riferimento == null || riferimento.isEmpty())
			throw new ModelValidationException("Bisogna specificare un riferimento per il carico. Es. purchase order number");
		if (prodotti == null || prodotti.size() == 0) {
			throw new ModelValidationException("Bisogna elencare i prodotti.");
		} else {
			for (MRigaCarico prodotto : prodotti) {
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

	public TipoIDProdotto getTipoIdentificazioneProdotti() {
		return tipoIdentificazioneProdotti;
	}

	public void setTipoIdentificazioneProdotti(TipoIDProdotto tipoIdentificazioneProdotti) {
		this.tipoIdentificazioneProdotti = tipoIdentificazioneProdotti;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public List<MRigaCarico> getProdotti() {
		return prodotti;
	}

	public void aggiungiProdotto(MRigaCarico prodotto) {
		prodotti.add(prodotto);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPezziStimati() {
		int totale = 0;
		for (MRigaCarico prodotto : prodotti) {
			totale += prodotto.getQuantitaDichiarata();
		}
		return totale;
	}

}

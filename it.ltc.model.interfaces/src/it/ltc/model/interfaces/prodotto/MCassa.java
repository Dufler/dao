package it.ltc.model.interfaces.prodotto;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;
import it.ltc.model.interfaces.ordine.TipoIDProdotto;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MCassa implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	private final MProdotto cassa;
	private final String codiceCassa;
	private final Cassa tipoCassa;
	private final TipoIDProdotto tipoIdentificazione;
	private final HashMap<MProdotto, Integer> prodotti;
	
	public MCassa(MProdotto cassa, String codiceCassa, Cassa tipoCassa, TipoIDProdotto tipoIdentificazione) {
		this.cassa = cassa;
		this.codiceCassa = codiceCassa;
		this.tipoCassa = tipoCassa;
		this.tipoIdentificazione = tipoIdentificazione;
		this.prodotti = new HashMap<>();
	}
	
	public void aggiungiProdotto(MProdotto prodotto, int quantita) {
		prodotti.put(prodotto, quantita);
	}
	
	public HashMap<MProdotto, Integer> getProdotti() {
		return prodotti;
	}
	
	public MProdotto getCassa() {
		return cassa;
	}

	public String getCodiceCassa() {
		return codiceCassa;
	}

	public Cassa getTipoCassa() {
		return tipoCassa;
	}

	public TipoIDProdotto getTipoIdentificazione() {
		return tipoIdentificazione;
	}

	@Override
	public void valida() throws ModelValidationException {
		if (prodotti.isEmpty())
			throw new ModelValidationException("La cassa deve contenere almeno un prodotto.");
		for (MProdotto prodotto : prodotti.keySet()) {
			Integer quantita = prodotti.get(prodotto);
			if (quantita == null || quantita < 1)
				throw new ModelValidationException("La cassa deve contenere almeno un prodotto. (" + prodotto + ")");
		}		
	}

}

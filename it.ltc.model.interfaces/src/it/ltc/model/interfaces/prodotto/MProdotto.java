package it.ltc.model.interfaces.prodotto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MProdotto implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String chiaveCliente;
	private String codiceModello;
	private String barcode;
	private String taglia;
	private String colore;
	private String descrizione;
	private String composizione;
	private String brand;
	private String categoria;
	private String sottoCategoria;
	private String madeIn;
	private String stagione;
	private Integer peso;
	private Integer h;
	private Integer l;
	private Integer z;
	private Double valore;
	
	private String barcodeFornitore;
	private String skuFornitore;
	private String descrizioneAggiuntiva;
	private String note;
	private String cassa;

	public MProdotto() {}

	public void valida() throws ModelValidationException {
		if (chiaveCliente == null || chiaveCliente.isEmpty())
			throw new ModelValidationException("Bisogna specificare una chiave.");
		if (codiceModello == null || codiceModello.isEmpty())
			throw new ModelValidationException("Bisogna specificare un codice." + toString());
		if (barcode == null || barcode.isEmpty())
			throw new ModelValidationException("Bisogna specificare un barcode.");
		if (taglia == null || taglia.isEmpty())
			throw new ModelValidationException("Bisogna specificare una taglia.");
		if (descrizione == null || descrizione.isEmpty())
			throw new ModelValidationException("Bisogna specificare una descrizione.");
		if (brand == null || brand.isEmpty())
			throw new ModelValidationException("Bisogna specificare un brand.");
		if (categoria == null || categoria.isEmpty())
			throw new ModelValidationException("Bisogna specificare una categoria.");
		else {
			//TODO - fare un check sul valore passato
			//Deve essere uno fra: ABBIGLIAMENTO, CALZATURE, ...
			//La lista completa deve essere ancora definita.
			try {
				Categoria.valueOf(categoria.toUpperCase());
			} catch (IllegalArgumentException e) {
				//La categoria non è valida
				String errorMessage = "La categoria indicata non è valida. L'elenco completo delle categorie è: ";
				for (Categoria categoria : Categoria.values()) {
					errorMessage += categoria + " ";
				}
				errorMessage = errorMessage.trim();
				throw new ModelValidationException(errorMessage);
			}
		}
		if (stagione != null) {
			if (stagione.length() != 4) {
				throw new ModelValidationException("La stagione indicata non è valida. Es. AI17");
			} else {
				try {
					Stagione.valueOf(stagione.substring(0, 2));
					Integer.parseInt(stagione.substring(2));
				} catch (NumberFormatException e) {
					throw new ModelValidationException("L'anno indicato per la stagione non è valido. Es. AI17");
				} catch(IllegalArgumentException e) {
					//La stagione indicata non è valida
					String errorMessage = "La stagione indicata non è valida. L'elenco completo delle stagioni è: ";
					for (Stagione stagione : Stagione.values()) {
						errorMessage += stagione + " ";
					}
					errorMessage = errorMessage.trim();
					throw new ModelValidationException(errorMessage);
				}
			}
		}
		if (peso != null && peso <= 0)
			throw new ModelValidationException("Il peso specificato non è valido.");
		if (h != null && h <= 0)
			throw new ModelValidationException("L'altezza specificata non è valida.");
		if (l != null && l <= 0)
			throw new ModelValidationException("La lunghezza specificata non è valida.");
		if (z != null && z <= 0)
			throw new ModelValidationException("La profondità specificata non è valida.");
		if (valore != null && valore <= 0)
			throw new ModelValidationException("Il valore doganale specificato non è valido.");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChiaveCliente() {
		return chiaveCliente;
	}

	public void setChiaveCliente(String chiaveCliente) {
		this.chiaveCliente = chiaveCliente;
	}

	public String getCodiceModello() {
		return codiceModello;
	}

	public void setCodiceModello(String codiceModello) {
		this.codiceModello = codiceModello;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getComposizione() {
		return composizione;
	}

	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSottoCategoria() {
		return sottoCategoria;
	}

	public void setSottoCategoria(String sottoCategoria) {
		this.sottoCategoria = sottoCategoria;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getStagione() {
		return stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getL() {
		return l;
	}

	public void setL(Integer l) {
		this.l = l;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getBarcodeFornitore() {
		return barcodeFornitore;
	}

	public void setBarcodeFornitore(String barcodeFornitore) {
		this.barcodeFornitore = barcodeFornitore;
	}

	public String getSkuFornitore() {
		return skuFornitore;
	}

	public void setSkuFornitore(String skuFornitore) {
		this.skuFornitore = skuFornitore;
	}

	public String getDescrizioneAggiuntiva() {
		return descrizioneAggiuntiva;
	}

	public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
		this.descrizioneAggiuntiva = descrizioneAggiuntiva;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCassa() {
		return cassa;
	}

	public void setCassa(String cassa) {
		this.cassa = cassa;
	}

	@Override
	public String toString() {
		return "Prodotto [chiavecliente=" + chiaveCliente + ", codicemodello=" + codiceModello + ", barcode=" + barcode
				+ ", taglia=" + taglia + ", colore=" + colore + ", descrizione=" + descrizione + ", composizione="
				+ composizione + ", brand=" + brand + ", categoria=" + categoria + ", sottocategoria=" + sottoCategoria
				+ ", madeIn=" + madeIn + ", peso=" + peso + ", h=" + h + ", l=" + l + ", z=" + z + ", valore=" + valore
				+ "]";
	}
	
}
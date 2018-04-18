package it.ltc.database.model.commessa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the prodotto database table.
 * 
 */
@Entity
@NamedQuery(name="Prodotto.findAll", query="SELECT p FROM Prodotto p")
public class Prodotto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifica la tipologia di gestione del prodotto in base alla cassa.
	 * @author Damiano
	 *
	 */
	public enum TipoCassa { 
		/**
		 * Prodotto singolo, il default
		 */
		NO,
		/**
		 * Cassa composta da più prodotti singoli, arriva gia' cosi' e dovrebbe ripartire cosi'
		 */
		STANDARD,
		/**
		 * Insieme di prodotti singoli, arrivano sfusi e vanno composti in uscita.
		 */
		BUNDLE
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String barcode;

	@Column(nullable=false, length=1, columnDefinition ="ENUM('NO', 'STANDARD', 'BUNDLE')")
	@Enumerated(EnumType.STRING)
	private TipoCassa cassa;

	@Column(name="codice_modello")
	private String codiceModello;

	@Column(name="codifica_cliente")
	private String codificaCliente;

	private String colore;

	private String composizione;

	private String descrizione;

	private int h;

	@Column(name="brand", length=45)
	private String brand;

	@Column(name="categoria", length=20)
	private String categoria;

	private int l;

	@Column(name="made_in")
	private String madeIn;

	private int peso;

	@Column(name="sotto_categoria")
	private String sottoCategoria;

	private String taglia;

	private BigDecimal valore;

	private int z;

	public Prodotto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public TipoCassa getCassa() {
		return this.cassa;
	}

	public void setCassa(TipoCassa cassa) {
		this.cassa = cassa;
	}

	public String getCodiceModello() {
		return this.codiceModello;
	}

	public void setCodiceModello(String codiceModello) {
		this.codiceModello = codiceModello;
	}

	public String getCodificaCliente() {
		return this.codificaCliente;
	}

	public void setCodificaCliente(String codificaCliente) {
		this.codificaCliente = codificaCliente;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getComposizione() {
		return this.composizione;
	}

	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getH() {
		return this.h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getL() {
		return this.l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public String getMadeIn() {
		return this.madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getSottoCategoria() {
		return this.sottoCategoria;
	}

	public void setSottoCategoria(String sottoCategoria) {
		this.sottoCategoria = sottoCategoria;
	}

	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public BigDecimal getValore() {
		return this.valore;
	}

	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}

	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
	}

}
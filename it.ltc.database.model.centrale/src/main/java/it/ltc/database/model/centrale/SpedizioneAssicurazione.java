package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the spedizione_assicurazione database table.
 * 
 */
@Entity
@Table(name="spedizione_assicurazione")
@NamedQuery(name="SpedizioneAssicurazione.findAll", query="SELECT s FROM SpedizioneAssicurazione s")
public class SpedizioneAssicurazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_spedizione", unique=true, nullable=false)
	private int idSpedizione;

	@Column(nullable=false, length=15)
	private String tipo;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valore;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String valuta;

	public SpedizioneAssicurazione() {
	}

	public int getIdSpedizione() {
		return this.idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValore() {
		return this.valore;
	}

	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}

	public String getValuta() {
		return this.valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

}
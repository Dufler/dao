package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fattura_ambito database table.
 * 
 */
@Entity
@Table(name="fattura_ambito")
@NamedQuery(name="FatturaAmbito.findAll", query="SELECT f FROM FatturaAmbito f")
public class FatturaAmbito implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum CategoriaFattura {TRASPORTI, LOGISTICA, EXTRA, SIMULAZIONE, CORRIERI}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=1, columnDefinition="ENUM('TRASPORTI', 'LOGISTICA', 'EXTRA', 'SIMULAZIONE', 'CORRIERI')")
	@Enumerated(EnumType.STRING)
	private CategoriaFattura categoria;

	@Column(name="descrizione", columnDefinition="MEDIUMTEXT")
	private String descrizione;

	@Column(nullable=false, length=45)
	private String nome;

	public FatturaAmbito() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoriaFattura getCategoria() {
		return this.categoria;
	}

	public void setCategoria(CategoriaFattura categoria) {
		this.categoria = categoria;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
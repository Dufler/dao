package it.ltc.database.model.centrale;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fattura_sotto_ambito database table.
 * 
 */
@Entity
@Table(name="fattura_sotto_ambito")
@NamedQuery(name="FatturaSottoAmbito.findAll", query="SELECT f FROM FatturaSottoAmbito f")
public class FatturaSottoAmbito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="categoria_ambito", nullable=false, length=45)
	private String categoriaAmbito;

	@Column(name="descrizione", columnDefinition="MEDIUMTEXT")
	private String descrizione;

	@Column(name="id_ambito", nullable=false)
	private int idAmbito;

	@Column(nullable=false, length=100)
	private String nome;

	@Column(name="valore_ammesso", nullable=false)
	private boolean valoreAmmesso;

	public FatturaSottoAmbito() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaAmbito() {
		return this.categoriaAmbito;
	}

	public void setCategoriaAmbito(String categoriaAmbito) {
		this.categoriaAmbito = categoriaAmbito;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdAmbito() {
		return this.idAmbito;
	}

	public void setIdAmbito(int idAmbito) {
		this.idAmbito = idAmbito;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getValoreAmmesso() {
		return this.valoreAmmesso;
	}

	public void setValoreAmmesso(boolean valoreAmmesso) {
		this.valoreAmmesso = valoreAmmesso;
	}

}
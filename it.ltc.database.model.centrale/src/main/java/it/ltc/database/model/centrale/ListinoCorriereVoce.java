package it.ltc.database.model.centrale;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.ltc.database.model.centrale.json.VoceScaglioneJSON;

/**
 * The persistent class for the listino_corriere_voce database table.
 * 
 */
@Entity
@Table(name = "listino_corriere_voce")
@NamedQuery(name = "ListinoCorriereVoce.findAll", query = "SELECT l FROM ListinoCorriereVoce l")
public class ListinoCorriereVoce implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoCalcolo {
		FIS, PER, PRO, SCA, SCR, XXX
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "descrizione", columnDefinition = "TINYTEXT")
	private String descrizione;

	@Column(name = "id_listino", nullable = false)
	private int idListino;

	@Column(name = "id_sotto_ambito", nullable = false)
	private int idSottoAmbito;

	@Column(nullable = false, length = 250)
	private String nome;

	@Column(name = "strategia_calcolo", length = 45)
	private String strategiaCalcolo;

	@Column(name = "tipo_calcolo", nullable = false, length = 1, columnDefinition = "ENUM('FIS', 'PER', 'PRO', 'SCA', 'SCR', 'XXX')")
	@Enumerated(EnumType.STRING)
	private TipoCalcolo tipoCalcolo;

	@Column(name = "valore_sotto_ambito", length = 250)
	private String valoreSottoAmbito;

	// Aggiunte
	@Transient
	@JsonInclude(value = Include.NON_NULL)
	private ListinoCorriereVoceFissa fissa;

	public ListinoCorriereVoceFissa getFissa() {
		return this.fissa;
	}

	public void setFissa(ListinoCorriereVoceFissa fissa) {
		this.fissa = fissa;
	}

	@Transient
	@JsonInclude(value = Include.NON_NULL)
	private ListinoCorriereVocePercentuale percentuale;

	public ListinoCorriereVocePercentuale getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(ListinoCorriereVocePercentuale percentuale) {
		this.percentuale = percentuale;
	}

	@Transient
	@JsonInclude(value = Include.NON_NULL)
	private ListinoCorriereVoceProporzionale proporzionale;

	public ListinoCorriereVoceProporzionale getProporzionale() {
		return this.proporzionale;
	}

	public void setProporzionale(ListinoCorriereVoceProporzionale proporzionale) {
		this.proporzionale = proporzionale;
	}

	@Transient
	@JsonInclude(value = Include.NON_NULL)
	private ListinoCorriereVoceScaglioniRipetuti ripetuti;

	public ListinoCorriereVoceScaglioniRipetuti getRipetuti() {
		return this.ripetuti;
	}

	public void setRipetuti(ListinoCorriereVoceScaglioniRipetuti ripetuti) {
		this.ripetuti = ripetuti;
	}

	@Transient
	@JsonInclude(value = Include.NON_EMPTY)
	private List<VoceScaglioneJSON> scaglioni;

	public List<VoceScaglioneJSON> getScaglioni() {
		return this.scaglioni;
	}

	public void setScaglioni(List<VoceScaglioneJSON> scaglioni) {
		this.scaglioni = scaglioni;
	}
	// Fine

	public ListinoCorriereVoce() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdListino() {
		return this.idListino;
	}

	public void setIdListino(int idListino) {
		this.idListino = idListino;
	}

	public int getIdSottoAmbito() {
		return this.idSottoAmbito;
	}

	public void setIdSottoAmbito(int idSottoAmbito) {
		this.idSottoAmbito = idSottoAmbito;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStrategiaCalcolo() {
		return this.strategiaCalcolo;
	}

	public void setStrategiaCalcolo(String strategiaCalcolo) {
		this.strategiaCalcolo = strategiaCalcolo;
	}

	public TipoCalcolo getTipoCalcolo() {
		return this.tipoCalcolo;
	}

	public void setTipoCalcolo(TipoCalcolo tipoCalcolo) {
		this.tipoCalcolo = tipoCalcolo;
	}

	public String getValoreSottoAmbito() {
		return this.valoreSottoAmbito;
	}

	public void setValoreSottoAmbito(String valoreSottoAmbito) {
		this.valoreSottoAmbito = valoreSottoAmbito;
	}

}
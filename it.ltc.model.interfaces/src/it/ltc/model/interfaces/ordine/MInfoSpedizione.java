package it.ltc.model.interfaces.ordine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.ModelInterface;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MInfoSpedizione implements ModelInterface {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int pezzi;
	private int colli;
	private double peso;
	private double volume;
	
	private String note;
	private final Set<String> riferimentiOrdini;
	
	private String corriere;
	private String codiceCorriere;
	private String servizioCorriere;
	
	private MContrassegno contrassegno;
	private MAssicurazione assicurazione;
	private Double valoreDoganale;
	private String codiceTracking;
	
	private String riferimentoDocumento;
	private Date dataDocumento;
	private String tipoDocumento;
	
	private Date dataConsegna;
	
	private boolean forzaAccoppiamentoDestinatari;
	
	private boolean abilitaPartenza;
	
	public MInfoSpedizione() {
		riferimentiOrdini = new HashSet<>();
		tipoDocumento = "DDT";
		servizioCorriere = "DEF";
		dataDocumento = new Date();
	}

	@Override
	public void valida() throws ModelValidationException {
		//Riferimento degli ordini da spedire.
		if (riferimentiOrdini.isEmpty()) {
			throw new ModelValidationException("E' necessario indicare almeno un'ordine da spedire.");
		} else for (String riferimentoOrdine : riferimentiOrdini) {
			if (riferimentoOrdine == null || riferimentoOrdine.isEmpty()) {
				throw new ModelValidationException("E' necessario indicare un riferimento per l'ordine da spedire.");
			}
		}
		//Note
		if (note != null && note.length() > 60) {
			throw new ModelValidationException("Le note inserite per la spedizione sono troppo lunghe (MAX 60 caratteri)");
		}
		//Corriere
		if (corriere == null || corriere.isEmpty()) {
			throw new ModelValidationException("E' necessario indicare un corriere con cui spedire la merce.");
		}
		//Livello di servizio
		if (servizioCorriere == null || servizioCorriere.isEmpty())
			throw new ModelValidationException("Bisogna indicare un tipo di servizio per la spedizione.");
		else {
			try {
				ServizioCorriere.valueOf(servizioCorriere.toUpperCase());
			} catch (IllegalArgumentException e) {
				//Il tipo di ordine non è valido
				String errorMessage = "Il servizio corriere indicato non è valido. L'elenco completo dei livelli di servizio è: ";
				for (ServizioCorriere servizio : ServizioCorriere.values()) {
					errorMessage += servizio + " ";
				}
				errorMessage = errorMessage.trim();
				throw new ModelValidationException(errorMessage);
			}
		}
		//Documento
		if (tipoDocumento == null || tipoDocumento.isEmpty()) {
			throw new ModelValidationException("E' necessario indicare il tipo del documento che accompagnerà la merce.");
		}
		if (dataDocumento == null) {
			throw new ModelValidationException("E' necessario indicare la data del documento che accompagnerà la merce.");
		}
		//Valore doganale
		if (valoreDoganale != null && valoreDoganale < 0) {
			throw new ModelValidationException("Il valore doganale non può essere minore di 0.00 euro");
		}
		//Contrassegno
		if (contrassegno != null) contrassegno.valida();
		//Assicurazione
		if (assicurazione != null) assicurazione.valida();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPezzi() {
		return pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public int getColli() {
		return colli;
	}

	public void setColli(int colli) {
		this.colli = colli;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<String> getRiferimentiOrdini() {
		return riferimentiOrdini;
	}

	public void aggiungiRiferimentoOrdine(String riferimentoOrdine) {
		riferimentiOrdini.add(riferimentoOrdine);
	}

	public String getCorriere() {
		return corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	public String getCodiceCorriere() {
		return codiceCorriere;
	}

	public void setCodiceCorriere(String codiceCorriere) {
		this.codiceCorriere = codiceCorriere;
	}

	public String getServizioCorriere() {
		return servizioCorriere;
	}

	public void setServizioCorriere(String servizioCorriere) {
		this.servizioCorriere = servizioCorriere;
	}

	public MContrassegno getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(MContrassegno contrassegno) {
		this.contrassegno = contrassegno;
	}

	public MAssicurazione getAssicurazione() {
		return assicurazione;
	}

	public void setAssicurazione(MAssicurazione assicurazione) {
		this.assicurazione = assicurazione;
	}

	public Double getValoreDoganale() {
		return valoreDoganale;
	}

	public void setValoreDoganale(Double valoreDoganale) {
		this.valoreDoganale = valoreDoganale;
	}

	public String getCodiceTracking() {
		return codiceTracking;
	}

	public void setCodiceTracking(String codiceTracking) {
		this.codiceTracking = codiceTracking;
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

	public boolean isForzaAccoppiamentoDestinatari() {
		return forzaAccoppiamentoDestinatari;
	}

	public void setForzaAccoppiamentoDestinatari(boolean forzaAccoppiamentoDestinatari) {
		this.forzaAccoppiamentoDestinatari = forzaAccoppiamentoDestinatari;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getRiferimentoDocumento() {
		return riferimentoDocumento;
	}

	public void setRiferimentoDocumento(String riferimentoDocumento) {
		this.riferimentoDocumento = riferimentoDocumento;
	}

	public boolean isAbilitaPartenza() {
		return abilitaPartenza;
	}

	public void setAbilitaPartenza(boolean abilitaPartenza) {
		this.abilitaPartenza = abilitaPartenza;
	}

}

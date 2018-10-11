package it.ltc.database.model.legacy.model;

/**
 * Enum con tutte le causali possibili di movimento.
 * @author Damiano
 *
 */
public enum CausaliMovimento {
	
	CPK("Carico", 1, 1, 0, "SI", 1, 0, "+", "+", "N"),
	SPE("Scarico errori", -1, -1, 0, "SI", 0, 1, "-", "-", "N"),
	RES("Carico da reso", 1, 1, 0, "SI", 1, 0, "+", "+", "N"),
	CPR("Rettifica carico", 1, 1, 0, "SI", 1, 0, "+", "+", "N"),
	//RIO("Rettifica impegno ordine"),
	//ORD("Ordine"),
	IOS("Impegno ordine", 0, -1, 1, "NO", 0, 0, "N", "-", "+"),
	REO("Cancellazione ordine", 0, 1, -1, "NO", 0, 0, "N", "+", "-"),
	IBO("Imballo ordine", -1, 0, -1, "SI", 0, 1, "-", "N", "-"),
	REL("Rettifica non spedito", 0, 1, -1, "NO", 0, 0, "N", "+", "-");
	
	private final String descrizione;
	
	private final int moltiplicatoreEsistenza;
	private final int moltiplicatoreDisponibile;
	private final int moltiplicatoreImpegnato;
	
	private final String incrementoTotali;
	private final int totaleIn;
	private final int totaleOut;
	
	private final String segnoEsistenza;
	private final String segnoDisponibile;
	private final String segnoImpegnato;
	
	private final String categoriaDocumento; //L'equivalente di doccat, 1 carattere.
	private final String tipoDocumento; //l'equivalente di doctipo, 3 caratteri.
	private final String tipoMovimento; //l'equivaliente di tipo, 2 caratteri.
	
	private CausaliMovimento(String descrizione, int e, int d, int i, String incremento, int in, int out, String se, String sd, String si) {
		this.descrizione = descrizione;
		this.moltiplicatoreEsistenza = e;
		this.moltiplicatoreDisponibile = d;
		this.moltiplicatoreImpegnato = i;
		this.incrementoTotali = incremento;
		this.totaleIn = in;
		this.totaleOut = out;
		this.segnoEsistenza = se;
		this.segnoDisponibile = sd;
		this.segnoImpegnato = si;
		this.categoriaDocumento = "_";
		this.tipoDocumento = "___";
		this.tipoMovimento = "__";
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public int getMoltiplicatoreEsistenza() {
		return moltiplicatoreEsistenza;
	}

	public int getMoltiplicatoreDisponibile() {
		return moltiplicatoreDisponibile;
	}
	
	public int getMoltiplicatoreImpegnato() {
		return moltiplicatoreImpegnato;
	}

	public String getIncrementoTotali() {
		return incrementoTotali;
	}

	public int getTotaleIn() {
		return totaleIn;
	}

	public int getTotaleOut() {
		return totaleOut;
	}

	public String getSegnoEsistenza() {
		return segnoEsistenza;
	}

	public String getSegnoDisponibile() {
		return segnoDisponibile;
	}

	public String getSegnoImpegnato() {
		return segnoImpegnato;
	}

	public String getCategoriaDocumento() {
		return categoriaDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	@Override
	public String toString() {
		return descrizione;
	}
	
}

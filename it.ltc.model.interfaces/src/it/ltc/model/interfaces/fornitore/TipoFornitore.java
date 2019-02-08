package it.ltc.model.interfaces.fornitore;

/**
 * Indica i possibili valori che il campo tipodocumento sulla tabella Fornitori può assumere.<br>
 * - CARICO : il default, indica un fornitore di produzione.<br>
 * - RESO : indica che fornisce resi.<br>
 * - INVENTARIO : fornitore "fittizio" usato per i carichi d'inventario.<br>
 * @author Damiano
 *
 */
public enum TipoFornitore {
	
	CARICO,
	RESO,
	INVENTARIO;

}

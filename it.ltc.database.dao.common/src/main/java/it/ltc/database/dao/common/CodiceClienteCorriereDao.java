package it.ltc.database.dao.common;

import java.util.HashMap;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.centrale.JoinCommessaCorriere;

public class CodiceClienteCorriereDao extends CRUDDao<JoinCommessaCorriere> {
	
	private final HashMap<String, JoinCommessaCorriere> mappaPerCodice;

	public CodiceClienteCorriereDao() {
		this(LOCAL_CENTRALE_PERSISTENCE_UNIT_NAME);
	}
	
	public CodiceClienteCorriereDao(String persistenceUnit) {
		super(persistenceUnit, JoinCommessaCorriere.class);
		this.mappaPerCodice = new HashMap<>();
	}
	
	private JoinCommessaCorriere aggiungiMappe(JoinCommessaCorriere codice) {
		if (codice != null) {
			mappaPerCodice.put(codice.getCodiceCliente(), codice);
		}
		return codice;
	}
	
	public JoinCommessaCorriere trovaDaCodice(String codice) {
		JoinCommessaCorriere entity = mappaPerCodice.containsKey(codice) ? mappaPerCodice.get(codice) : aggiungiMappe(findByID(codice));
		return entity;
	}
	
	public List<JoinCommessaCorriere> trovaTutti() {
		List<JoinCommessaCorriere> lista = findAll();
        return lista;
	}
	
	public JoinCommessaCorriere inserisci(JoinCommessaCorriere codice) {
		JoinCommessaCorriere entity = insert(codice);
		return entity;
	}

	public JoinCommessaCorriere aggiorna(JoinCommessaCorriere codice) {
		JoinCommessaCorriere entity = update(codice, codice.getCodiceCliente());
		return entity;
	}
	
	public JoinCommessaCorriere elimina(JoinCommessaCorriere codice) {
		JoinCommessaCorriere entity = delete(codice.getCodiceCliente());
		return entity;
	}
	
	@Override
	protected void updateValues(JoinCommessaCorriere oldEntity, JoinCommessaCorriere entity) {
		oldEntity.setCommessa(entity.getCommessa());
		oldEntity.setCorriere(entity.getCorriere());
		oldEntity.setDescrizione(entity.getDescrizione());
		oldEntity.setStato(entity.getStato());
	}

}

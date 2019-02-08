package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.PakiTesta;

public class PakiTestaDao extends CRUDDao<PakiTesta> {

	public PakiTestaDao(String persistenceUnit) {
		super(persistenceUnit, PakiTesta.class);
	}
	
	public PakiTesta trovaDaID(int id) {
		PakiTesta entity = findByID(id);
		return entity;
	}
	
	public List<PakiTesta> trovaCarichiChiusiDaEsportare() {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("stato", "CHIUSO"));
		condizioni.add(new CondizioneWhere("generatoMov", "SI"));
		condizioni.add(new CondizioneWhere("flagTra", "F"));
		List<PakiTesta> entities = findAll(condizioni);
		return entities;
	}
	
	public List<PakiTesta> trovaCarichiSenzaQuantita() {
		List<PakiTesta> entities = findAllEqualTo("qtaTotAto", 0);
		return entities;
	}
	
	public List<PakiTesta> trovaDaStato(String stato) {
		List<PakiTesta> entities = findAllEqualTo("stato", stato);
		return entities;
	}
	
	public PakiTesta trovaDaRiferimento(String riferimento) {
		PakiTesta entity = findOnlyOneEqualTo("nrPaki", riferimento);
		return entity;
	}
	
	public List<PakiTesta> trovaDaFornitore(String fornitore) {
		List<PakiTesta> entities = findAllEqualTo("codFornitore", fornitore);
		return entities;
	}
	
	public PakiTesta esisteFornitore(String fornitore) {
		PakiTesta entity = findFirstOneEqualTo("codFornitore", fornitore);
		return entity;
	}
	
	public PakiTesta inserisci(PakiTesta testata) {
		PakiTesta entity = insert(testata);
		return entity;
	}
	
	public PakiTesta aggiorna(PakiTesta testata) {
		PakiTesta entity = update(testata, testata.getIdTestaPaki());
		return entity;
	}
	
	public PakiTesta elimina(PakiTesta testata) {
		PakiTesta entity = delete(testata.getIdTestaPaki());
		return entity;
	}

	@Override
	protected void updateValues(PakiTesta oldEntity, PakiTesta entity) {
		//Faccio la modifica sullo stato solo in casi semplice, potrebbe essere necessario fare dei movimenti di magazzino o aggiornare altri campi.
		if (!"INSERITO".equals(oldEntity.getStato()) && !"CHIUSO".equals(entity.getStato()))
			oldEntity.setStato(entity.getStato());
		oldEntity.setAbilitaEccedenze(entity.getAbilitaEccedenze());
		//oldEntity.setAnno(entity.getAnno());
		oldEntity.setCodFornitore(entity.getCodFornitore());
		oldEntity.setDataArrivo(entity.getDataArrivo());
		oldEntity.setDataInizio(entity.getDataInizio());
		oldEntity.setDataPaki(entity.getDataPaki());
		oldEntity.setIdFornitore(entity.getIdFornitore());
		oldEntity.setNote(entity.getNote());
		oldEntity.setNrDocInterno(entity.getNrDocInterno());
		//oldEntity.setNrPaki(entity.getNrPaki());
		oldEntity.setRagSocFor(entity.getRagSocFor());
		oldEntity.setStagione(entity.getStagione());
		oldEntity.setTipoDoc(entity.getTipoDoc());
		oldEntity.setTipodocumento(entity.getTipodocumento());
		oldEntity.setTipoPack(entity.getTipoPack());
		oldEntity.setQtaTotAre(entity.getQtaTotAre());
		oldEntity.setQtaTotAto(entity.getQtaTotAto());
		oldEntity.setFlagTra(entity.getFlagTra());
	}

}

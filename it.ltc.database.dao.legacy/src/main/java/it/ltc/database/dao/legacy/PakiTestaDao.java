package it.ltc.database.dao.legacy;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.PakiTesta;

public class PakiTestaDao extends CRUDDao<PakiTesta> {

	public PakiTestaDao(String persistenceUnit) {
		super(persistenceUnit, PakiTesta.class);
	}
	
	public PakiTesta trovaDaID(int id) {
		PakiTesta entity = findByID(id);
		return entity;
	}
	
	public PakiTesta trovaDaRiferimento(String riferimento) {
		PakiTesta entity = findFirstOneEqualTo("nrPaki", riferimento);
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
		oldEntity.setStato(entity.getStato());
	}

//	@Override
//	public CaricoJSON trovaDaID(int id, boolean dettagliato) {
//		
//		return null;
//	}
//
//	@Override
//	public List<IngressoJSON> trovaCorrispondenti(IngressoJSON filtro) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}

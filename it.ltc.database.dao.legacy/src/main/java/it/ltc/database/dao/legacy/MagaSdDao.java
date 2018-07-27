package it.ltc.database.dao.legacy;

import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.model.legacy.MagaSd;

public class MagaSdDao extends CRUDDao<MagaSd> {

	public MagaSdDao(String persistenceUnit) {
		super(persistenceUnit, MagaSd.class);
	}
	
	public List<MagaSd> trovaTuttiPerMagazzino(String magazzino) {
		List<MagaSd> entities = findAllEqualTo("codMaga", magazzino);
		return entities;
	}
	
	public List<MagaSd> trovaTuttiPerArticolo(String codiceUnivocoArticolo) {
		List<MagaSd> entities = findAllEqualTo("idUniArticolo", codiceUnivocoArticolo);
		return entities;
	}
	
	public MagaSd trovaDaArticoloEMagazzino(String codiceUnivocoArticolo, String magazzino) {
		List<CondizioneWhere> condizioni = new LinkedList<>();
		condizioni.add(new CondizioneWhere("idUniArticolo", codiceUnivocoArticolo));
		condizioni.add(new CondizioneWhere("codMaga", magazzino));
		List<MagaSd> saldi = findAll(condizioni, 2);
		MagaSd saldo = saldi.size() == 1 ? saldi.get(0) : null;
		return saldo;
	}

	@Override
	protected void updateValues(MagaSd oldEntity, MagaSd entity) {
		oldEntity.setCodMaga(entity.getCodMaga());
		oldEntity.setDisponibile(entity.getDisponibile());
		oldEntity.setEsistenza(entity.getEsistenza());
		oldEntity.setImpegnato(entity.getImpegnato());
		oldEntity.setIdUniArticolo(entity.getIdUniArticolo());
	}

}

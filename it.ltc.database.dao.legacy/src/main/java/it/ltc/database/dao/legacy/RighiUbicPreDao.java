package it.ltc.database.dao.legacy;

import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.Righiubicpre;

public class RighiUbicPreDao extends CRUDDao<Righiubicpre> {

	public RighiUbicPreDao(String persistenceUnit) {
		super(persistenceUnit, Righiubicpre.class);
	}
	
	public Righiubicpre elimina(Righiubicpre riga) {
		Righiubicpre entity = delete(riga.getIdubica());
		return entity;
	}
	
	public Righiubicpre trovaDaID(int id) {
		Righiubicpre entity = findByID(id);
		return entity;
	}
	
	public List<Righiubicpre> trovaDaLista(String lista) {
		List<Righiubicpre> entities = findAllEqualTo("nrlista", lista);
		return entities;
	}

	@Override
	protected void updateValues(Righiubicpre oldEntity, Righiubicpre entity) {
		oldEntity.setAnomalie(entity.getAnomalie());
		oldEntity.setArea(entity.getArea());
		oldEntity.setBox(entity.getBox());
		oldEntity.setCorsia(entity.getCorsia());
		oldEntity.setDataassegnazione(entity.getDataassegnazione());
		oldEntity.setDisponibile(entity.getDisponibile());
		oldEntity.setIDcollipack(entity.getIDcollipack());
		oldEntity.setIdRigoOrdine(entity.getIdRigoOrdine());
		oldEntity.setIdTestataOrdine(entity.getIdTestataOrdine());
		oldEntity.setIduniarticolo(entity.getIduniarticolo());
		oldEntity.setKeyMappa(entity.getKeyMappa());
		oldEntity.setKeyCoordinata(entity.getKeyCoordinata());
		oldEntity.setMagaDisponibile(entity.getMagaDisponibile());
		oldEntity.setMagazzino(entity.getMagazzino());
		oldEntity.setNrcollo(entity.getNrcollo());
		oldEntity.setNrlista(entity.getNrlista());
		oldEntity.setPiano(entity.getPiano());
		oldEntity.setQuantita(entity.getQuantita());
		oldEntity.setRaggliste(entity.getRaggliste());
		oldEntity.setScaffale(entity.getScaffale());
		oldEntity.setSessioneLavoro(entity.getSessioneLavoro());
		oldEntity.setTipo(entity.getTipo());
		oldEntity.setTipoUbicazione(entity.getTipoUbicazione());
		oldEntity.setX(entity.getX());
		oldEntity.setY(entity.getY());
	}

}

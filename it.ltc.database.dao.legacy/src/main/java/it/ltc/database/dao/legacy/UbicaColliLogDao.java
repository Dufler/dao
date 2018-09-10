package it.ltc.database.dao.legacy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.dao.CondizioneWhere;
import it.ltc.database.dao.CondizioneWhere.Condizione;
import it.ltc.database.dao.CondizioneWhere.Operatore;
import it.ltc.database.model.legacy.UbicaColliLog;

public class UbicaColliLogDao extends CRUDDao<UbicaColliLog> {

	public UbicaColliLogDao(String persistenceUnit) {
		super(persistenceUnit, UbicaColliLog.class);
	}

	@Override
	protected void updateValues(UbicaColliLog oldEntity, UbicaColliLog entity) {
		oldEntity.setDataUbicazione(entity.getDataUbicazione());
		oldEntity.setOraUbicazione(entity.getOraUbicazione());
		oldEntity.setKeyCollo(entity.getKeyCollo());
		oldEntity.setKeyUbbica(entity.getKeyUbbica());
		oldEntity.setOpeUbica(entity.getOpeUbica());
	}
	
	public UbicaColliLog trovaDaID(int id) {
		UbicaColliLog entity = findByID(id);
		return entity;
	}
	
	public List<UbicaColliLog> trovaPerCollo(String collo) {
		List<UbicaColliLog> entities = findAllEqualTo("keyCollo", collo);
		return entities;
	}
	
	public List<UbicaColliLog> trovaPerUbicazione(String ubicazione) {
		List<UbicaColliLog> entities = findAllEqualTo("keyUbbica", ubicazione);
		return entities;
	}
	
	public List<UbicaColliLog> trovaPerOperatoreEData(String operatore, Date dataInizio, Date dataFine) {
		List<CondizioneWhere> conditions = new LinkedList<>();
		conditions.add(new CondizioneWhere("opeUbica", operatore));
		conditions.add(new CondizioneWhere("dataUbicazione", dataInizio, Operatore.GREATER_OR_EQUAL, Condizione.AND));
		conditions.add(new CondizioneWhere("dataUbicazione", dataFine, Operatore.LESSER_OR_EQUAL, Condizione.AND));
		List<UbicaColliLog> entities = findAll(conditions);
		return entities;
	}
	
	public UbicaColliLog inserisci(UbicaColliLog log) {
		UbicaColliLog entity = insert(log);
		return entity;
	}
	
	public UbicaColliLog aggiorna(UbicaColliLog log) {
		UbicaColliLog entity = update(log, log.getId());
		return entity;
	}
	
	public UbicaColliLog elimina(UbicaColliLog log) {
		UbicaColliLog entity = delete(log.getId());
		return entity;
	}

}

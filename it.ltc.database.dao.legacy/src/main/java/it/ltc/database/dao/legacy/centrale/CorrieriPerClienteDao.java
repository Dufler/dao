package it.ltc.database.dao.legacy.centrale;

import java.util.List;

import it.ltc.database.dao.ReadOnlyDao;
import it.ltc.database.model.legacy.centrale.CorrieriPerCliente;

public class CorrieriPerClienteDao extends ReadOnlyDao<CorrieriPerCliente> {

	public CorrieriPerClienteDao(String persistenceUnit) {
		super(persistenceUnit, CorrieriPerCliente.class);
	}
	
	public List<CorrieriPerCliente> trovaCodici() {
		List<CorrieriPerCliente> entities = findAll();
		return entities;
	}

}

package it.ltc.database.dao.legacy;

import javax.persistence.EntityManager;

import it.ltc.database.dao.CRUDDao;
import it.ltc.database.model.legacy.TestaCorr;

public class TestaCorrDao extends CRUDDao<TestaCorr> {

	public TestaCorrDao(String persistenceUnit) {
		super(persistenceUnit, TestaCorr.class);
	}
	
	public int getProgressivoSpedizioneTestaCorr() {
		EntityManager em = getManager();
		int progressivo = getProgressivoSpedizioneTestaCorr(em);
		return progressivo;
	}
	
	protected static synchronized int getProgressivoSpedizioneTestaCorr(EntityManager em) {
		int progressivo;
		try {
			progressivo = em.createNamedQuery("TestaCorr.progressivoSpedizione", Integer.class).getSingleResult() + 1;
		} catch (Exception e) {
			progressivo = 1;
		} finally {
			em.close();
		}
		return progressivo;
	}

	@Override
	protected void updateValues(TestaCorr oldEntity, TestaCorr entity) {
		// TODO Auto-generated method stub
		
	}

}

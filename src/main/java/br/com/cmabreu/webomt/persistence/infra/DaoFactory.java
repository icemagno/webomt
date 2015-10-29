package br.com.cmabreu.webomt.persistence.infra;

import org.hibernate.Session;

public class DaoFactory<T> {
	
	private static final int CONTEXT = PersistenceContext.HIBERNATE;
	
	public IDao<T> getDao(Session session, Class<T> classe) {
		switch (CONTEXT) {

			case PersistenceContext.HIBERNATE:
				return new HibernateDAO<T>(session, classe); 

			default:
				return null;
		}
	}

}

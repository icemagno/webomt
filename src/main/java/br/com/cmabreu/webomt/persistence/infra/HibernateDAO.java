package br.com.cmabreu.webomt.persistence.infra;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;

import br.com.cmabreu.webomt.misc.LogManager;
import br.com.cmabreu.webomt.misc.Logger;
import br.com.cmabreu.webomt.persistence.exceptions.DeleteException;
import br.com.cmabreu.webomt.persistence.exceptions.InsertException;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.exceptions.UpdateException;

public class HibernateDAO<T> implements IDao<T>  {
	private Class<T> classe;
	private Session session;
	private Logger logger = LogManager.getLogger( this.getClass().getName() );
	private String sqlDLL;
	private boolean globalWithCommit;
	private long startTime;    
	private long estimatedTime;	
	
	public HibernateDAO(Session session, Class<T> classe) {
		logger.debug("open DAO for entity " + classe.getSimpleName() );
		this.session = session;
		this.classe = classe;
		startTime = System.nanoTime();
	}
	
	private void postClose() {
		//
	}

	public int insertDO(T objeto) throws InsertException {
		logger.debug("insert");
		int res = -1;
		try { 
			res = (Integer)session.save(objeto);
		} catch (HibernateException e) {
			throw new InsertException ( e.getMessage() );
		}
		if ( res == -1 ) {
			throw new InsertException("Unknown error by insert in " + this.classe.getSimpleName() );
		}
		estimatedTime = System.nanoTime() - startTime;
		logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
		postClose();
		return res;		
	}


	public int getCount(String tableName, String criteria) throws Exception {
		logger.debug("get count " + tableName + " " + criteria );
        Query query = session.createSQLQuery( "select count(*) from " + tableName + " " + criteria );
        Integer retorno = ( (BigInteger)query.uniqueResult() ).intValue();
		estimatedTime = System.nanoTime() - startTime;
		logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
		postClose();
        return retorno;
	}

	
	public List<?> genericAccess(String hql) throws Exception {
		logger.debug("access");
        Query query = session.createSQLQuery(hql);
        //Each row is a list of properties in the query
        List<?> rows = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		estimatedTime = System.nanoTime() - startTime;
		logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
		postClose();
        return rows;
	}


	public void executeQuery(String hql, boolean withCommit) throws Exception {
		logger.debug("query");
		sqlDLL = hql;
		globalWithCommit = withCommit;
		session.doWork(new Work() {
		    @Override
		    public void execute(Connection connection) throws SQLException {
		    	Statement st = connection.createStatement();
		    	String sql = sqlDLL;
		    	st.execute(sql);
		    	if( globalWithCommit ) {
			    	try {
			    		connection.commit();
			    	} catch ( Exception ignored ) {
			    		logger.error( ignored.getMessage() );
			    	} finally {
				    	try { st.close(); } catch ( Exception e ) { logger.error( e.getMessage() );}
			    		connection.close();
			    	}
		    	}
				logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
				postClose();
		    }
		});
		
	}
	
	
	public void deleteDO(T objeto) throws DeleteException {
		logger.debug("delete");
		try {
			session.delete(objeto);
		} catch (HibernateException e) {
			throw new DeleteException( e.getMessage() );
		} 				
		estimatedTime = System.nanoTime() - startTime;
		logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
		postClose();
	}
	

	public void updateDO(T objeto) throws UpdateException {
		logger.debug("update");
		try {
			session.saveOrUpdate(objeto);
		} catch (HibernateException e) {
			throw new UpdateException( e.getMessage() );
		} 			
		estimatedTime = System.nanoTime() - startTime;
		logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
		postClose();
	}


	@SuppressWarnings("unchecked")
	public List<T> getList(String criteria) throws NotFoundException {
		logger.debug("list");
		try {
			List<T> retorno;
			retorno = (List<T>)session.createSQLQuery(criteria).addEntity(this.classe).list(); 
			if ( retorno.size() == 0 ) {
				logger.debug("empty list");
				throw new NotFoundException("Not found: " + this.classe);
			}
			estimatedTime = System.nanoTime() - startTime;
			logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
			postClose();
			return retorno;
		} catch (HibernateException e) {
			throw new NotFoundException( e.getMessage() );
		} 		
	}


	@SuppressWarnings("unchecked")
	public T getDO(int id) throws NotFoundException {
		logger.debug("retrieve");
		Object objeto = session.get(classe, id);
		if ( objeto == null ){
			throw new  NotFoundException(classe.getSimpleName() + ": ID " + id + " not found.");
		}
		estimatedTime = System.nanoTime() - startTime;
		logger.debug("DAO finished in " + estimatedTime / 1000000000.0 + " seconds");
		postClose();
		return (T)objeto;		
	}


}

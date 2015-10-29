package br.com.cmabreu.webomt.persistence.repository;

import java.util.List;

import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.entity.User;
import br.com.cmabreu.webomt.persistence.exceptions.DatabaseConnectException;
import br.com.cmabreu.webomt.persistence.exceptions.InsertException;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.exceptions.UpdateException;
import br.com.cmabreu.webomt.persistence.infra.DaoFactory;
import br.com.cmabreu.webomt.persistence.infra.IDao;

public class FederationRepository extends BasicRepository {

	public FederationRepository() throws DatabaseConnectException {
		super();
		logger.debug("init");
	}

	public List<Federation> getList( User owner ) throws NotFoundException {
		logger.debug("get list" );
		DaoFactory<Federation> df = new DaoFactory<Federation>();
		IDao<Federation> fm = df.getDao(this.session, Federation.class);
		List<Federation> federations = null;
		try {
			String sql = "select * from federations";
			if ( owner != null ) {
				sql = "select * from federations where id_user = " + owner.getIdUser(); 				
			}
			
			federations = fm.getList( sql );
		} catch (Exception e) {
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done: " + federations.size() + " federations.");
		return federations;
	}
	
	public int insertFederation(Federation federation) throws InsertException {
		logger.debug("insert");
		DaoFactory<Federation> df = new DaoFactory<Federation>();
		IDao<Federation> fm = df.getDao(this.session, Federation.class);
		try {
			fm.insertDO(federation);
			commit();
		} catch (Exception e) {
			logger.error( e.getMessage() );
			rollBack();
			closeSession();
			throw new InsertException(e.getMessage());
		}
		closeSession();
		logger.debug("done");
		return federation.getIdFederation();
	}

	
	public void insertFederationList( List<Federation> federations ) throws InsertException {
		logger.debug("insert");
		DaoFactory<Federation> df = new DaoFactory<Federation>();
		IDao<Federation> fm = df.getDao(this.session, Federation.class);
		try {
			for ( Federation federation : federations ) {
				fm.insertDO(federation);
			}
			commit();
		} catch (Exception e) {
			logger.error( e.getMessage() );
			rollBack();
			closeSession();
			throw new InsertException(e.getMessage());
		}
		closeSession();
		logger.debug("done");
	}
	
	public Federation getFederation(int idFederation) throws NotFoundException {
		logger.debug("retrieve");
		DaoFactory<Federation> df = new DaoFactory<Federation>();
		IDao<Federation> fm = df.getDao(this.session, Federation.class);
		Federation federation = null;
		try {
			federation = fm.getDO(idFederation);
		} catch ( Exception e ) {
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done");
		return federation;
	}

	
	public void updateFederation( Federation federation ) throws UpdateException, NotFoundException {
		logger.debug("update");
		DaoFactory<Federation> df = new DaoFactory<Federation>();
		IDao<Federation> fm = df.getDao(this.session, Federation.class);
		Federation oldFederation = fm.getDO( federation.getIdFederation() );

		oldFederation.setDescription( federation.getDefinitionFile() );
		
		try {
			fm.updateDO(oldFederation);
			commit();
		} catch ( Exception e ) {
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done");
		
	}
}

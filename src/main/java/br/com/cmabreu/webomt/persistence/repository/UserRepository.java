package br.com.cmabreu.webomt.persistence.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.cmabreu.webomt.misc.UserType;
import br.com.cmabreu.webomt.persistence.entity.User;
import br.com.cmabreu.webomt.persistence.exceptions.DatabaseConnectException;
import br.com.cmabreu.webomt.persistence.exceptions.DeleteException;
import br.com.cmabreu.webomt.persistence.exceptions.InsertException;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.exceptions.UpdateException;
import br.com.cmabreu.webomt.persistence.infra.DaoFactory;
import br.com.cmabreu.webomt.persistence.infra.IDao;

public class UserRepository extends BasicRepository {

	public UserRepository() throws DatabaseConnectException {
		super();
		logger.debug("init");
	}

	public Set<User> getList() throws NotFoundException {
		logger.debug("get list" );
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		Set<User> users = null;
		try {
			users = new HashSet<User>( fm.getList("select * from users ") );
		} catch ( Exception e ) {
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done: " + users.size() + " users.");
		return users;
	}

	public List<User> getList( UserType type ) throws NotFoundException {
		logger.debug("get list by type " + type.toString() );
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		List<User> users = null;
		try {
			users = new ArrayList<User>( fm.getList("select * from users where type = '" + type + "'") );
		} catch ( Exception e ) {
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done: " + users.size() + " users.");
		return users;
	}
	
	public User getUserByName( String userName ) throws NotFoundException {
		logger.debug("get user by name " + userName );
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		List<User> users = null;
		try {
			users = fm.getList("select * from users where username = '" + userName + "'" );
		} catch ( Exception e ) {
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done");
		return users.get(0);
	}

	public User login( String loginName, String password ) throws NotFoundException {
		logger.debug("login " + loginName );
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		List<User> users = null;
		try {
			users = fm.getList("select * from users where loginname = '" + loginName + "' and password = '" + password + "'" );
		} catch ( Exception e ) {
			logger.debug( e.getMessage() );
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("allowed");
		return users.get(0);
	}
	
	public void updateUser( User user ) throws UpdateException {
		logger.debug("update");
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		try {
			fm.updateDO(user);
			commit();
		} catch (UpdateException e) {
			logger.error( e.getMessage() );
			rollBack();
			closeSession();
			throw e;
		}
		closeSession();
		logger.debug("done");
	}
	
	public User insertUser(User user) throws InsertException {
		logger.debug("insert");
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		
		try {
			fm.insertDO(user);
			commit();
		} catch (InsertException e) {
			rollBack();
			closeSession();
			logger.error( e.getMessage() );
			throw e;
		}
		closeSession();
		logger.debug("done");
		return user;
	}
	

	public User getUser(int idUser) throws NotFoundException {
		logger.debug("get " + idUser + "...");
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		User user = null;
		try {
			user = fm.getDO(idUser);
		} catch ( Exception e ) {
			closeSession();		
			throw e;
		} 
		closeSession();		
		logger.debug("done: " + user.getFullName() );
		return user;
	}
	

	public void deleteUser(User user) throws DeleteException {
		logger.debug("delete" );
		DaoFactory<User> df = new DaoFactory<User>();
		IDao<User> fm = df.getDao(this.session, User.class);
		try {
			fm.deleteDO(user);
			commit();
		} catch (DeleteException e) {
			rollBack();
			closeSession();
			logger.error( e.getMessage() );
			throw e;			
		}
		logger.debug("done");
		closeSession();
	}	
	
}

package br.com.cmabreu.webomt.persistence.services;

import java.security.MessageDigest;
import java.util.List;
import java.util.Set;

import br.com.cmabreu.webomt.misc.LogManager;
import br.com.cmabreu.webomt.misc.Logger;
import br.com.cmabreu.webomt.misc.UserType;
import br.com.cmabreu.webomt.persistence.entity.User;
import br.com.cmabreu.webomt.persistence.exceptions.DatabaseConnectException;
import br.com.cmabreu.webomt.persistence.exceptions.DeleteException;
import br.com.cmabreu.webomt.persistence.exceptions.InsertException;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.exceptions.UpdateException;
import br.com.cmabreu.webomt.persistence.repository.UserRepository;

public class UserService {
	private UserRepository rep;
	private Logger logger = LogManager.getLogger( this.getClass().getName() );

	public UserService() throws DatabaseConnectException {
		this.rep = new UserRepository();
	}

	private String convertPassword( byte[] password ) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] digest = md.digest( password );
		
		String result = "";
		for (int i=0; i < digest.length; i++) {
			result +=
				Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
	
	public void updateUser(User user) throws UpdateException {
		User oldUser;
		String sha1Password = "";
		
		try {
			oldUser = rep.getUser( user.getIdUser() );
			oldUser.setFullName( user.getFullName() );
			if ( ( user.getPassword() != null ) && ( !user.getPassword().equals("") ) ) {
				sha1Password = convertPassword( user.getPassword().getBytes() );
				oldUser.setPassword( sha1Password );
			}
		} catch ( Exception e) {
			throw new UpdateException( e.getMessage() );
		}
		
		oldUser.setLoginName( user.getLoginName() );
		oldUser.setType( user.getType() );
		oldUser.setUserMail( user.getUserMail() );
		
		rep.newTransaction();
		rep.updateUser(oldUser);
		
		try {
			MailService ms = new MailService();
			ms.notifyUserChange(user);
		} catch ( Exception e ) {
			throw new UpdateException( e.getMessage() );
		}
		
	}	

	public User login( String loginName, String password ) throws Exception {
		String sha1Password = convertPassword( password.getBytes() );
		return rep.login( loginName, sha1Password);
	}
	
	public User getUser(int idUser) throws NotFoundException{
		return rep.getUser(idUser);
	}

	public void newTransaction() {
		if ( !rep.isOpen() ) {
			rep.newTransaction();
		}
	}
	
	public User insertUser(User user) throws InsertException {
		try {
			String sha1Password = convertPassword( user.getPassword().getBytes() );
			user.setPassword( sha1Password );
		} catch ( Exception e ) {
			throw new InsertException( e.getMessage() );
		}

		User expRet = rep.insertUser( user );
		return expRet ;
	}	

	public User requestAccess( String fullName, String username, String password, String mailAddress ) throws InsertException {
		User user = new User();
		user.setFullName( fullName );
		user.setLoginName( username );
		user.setPassword( password );
		user.setUserMail( mailAddress );
		
		try {
			String sha1Password = convertPassword( user.getPassword().getBytes() );
			user.setPassword( sha1Password );
			user.setType( UserType.NEW );
			rep.insertUser( user );
			
			MailService ms = new MailService();
			ms.sendUserRequest(user);
			
		} catch ( Exception e ) {
			throw new InsertException( e.getMessage() );
		}
		
		return user;
	}	
	
	public void deleteUser( int idUser ) throws DeleteException {
		try {
			User user = rep.getUser(idUser);
			rep.newTransaction();
			rep.deleteUser(user);
		} catch (NotFoundException e) {
			logger.error( e.getMessage() );
			throw new DeleteException( e.getMessage() );
		}
	}

	public Set<User> getList( ) throws NotFoundException {
		return rep.getList( );
	}

	public List<User> getList( UserType type ) throws NotFoundException {
		return rep.getList( type );
	}
	
	
}

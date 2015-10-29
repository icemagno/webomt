package br.com.cmabreu.webomt.persistence.services;

import java.util.List;

import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.entity.User;
import br.com.cmabreu.webomt.persistence.exceptions.DatabaseConnectException;
import br.com.cmabreu.webomt.persistence.exceptions.InsertException;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.exceptions.UpdateException;
import br.com.cmabreu.webomt.persistence.repository.FederationRepository;

public class FederationService {
	private FederationRepository rep;
	
	public FederationService() throws DatabaseConnectException {
		this.rep = new FederationRepository();
	}
	
	public void newTransaction() {
		if( !rep.isOpen() ) {
			rep.newTransaction();
		}
	}

	public int insertFederation(Federation federation) throws InsertException {
		return rep.insertFederation( federation );
	}
	
	public void insertFederationList( List<Federation> federations ) throws InsertException {
		rep.insertFederationList( federations );
	}
	
	public List<Federation> getList( User owner ) throws NotFoundException {
		List<Federation> federations = rep.getList( owner );
		return federations;
	}

	public Federation getFederation( int idFederation ) throws NotFoundException {
		return rep.getFederation(idFederation);
	}

	
	public void updateFederation( Federation federation ) throws UpdateException, NotFoundException {
		rep.updateFederation(federation);
	}

}

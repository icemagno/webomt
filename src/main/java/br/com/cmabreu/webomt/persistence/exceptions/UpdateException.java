package br.com.cmabreu.webomt.persistence.exceptions;

public class UpdateException extends PersistenceException {
	private static final long serialVersionUID = 1L;

	public UpdateException( String message ) {
		super(message);
	}
	
}

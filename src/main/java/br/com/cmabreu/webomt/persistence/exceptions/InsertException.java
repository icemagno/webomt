package br.com.cmabreu.webomt.persistence.exceptions;

public class InsertException extends PersistenceException {
	private static final long serialVersionUID = 1L;
	
	public InsertException(String message){
		super(message);
	}
	
}

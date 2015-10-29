package br.com.cmabreu.webomt.misc;

public class DatabaseConnectionItem {
	private String table;
	private String mode;
	private String granted;
	private String query;
	private String tuples;
	
	public DatabaseConnectionItem(String tuples, String table, String mode, String granted, String query) {
		this.table = table;
		this.mode = mode;
		this.granted = granted;
		this.query = query;
		this.tuples = tuples;
	}

	public String getTable() {
		return table;
	}

	public String getMode() {
		return mode;
	}

	public String getGranted() {
		return granted;
	}

	public String getQuery() {
		return query;
	}
	
	public String getTuples() {
		return tuples;
	}
}

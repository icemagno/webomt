package br.com.cmabreu.webomt.misc;

public class DatabaseInfo {
	private String Host; 
	private String Db; 
	private String User; 
	private String Password; 
	private String Port; 

	public DatabaseInfo(String host, String db, String user, String password, String port) {
		Host = host;
		Db = db;
		User = user;
		Password = password;
		Port = port;
	}
	
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	public String getDb() {
		return Db;
	}
	public void setDb(String db) {
		Db = db;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	
	
}

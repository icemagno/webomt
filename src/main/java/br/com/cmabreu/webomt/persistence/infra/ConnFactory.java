package br.com.cmabreu.webomt.persistence.infra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConnFactory {
	private static SessionFactory factory;
	private static String myClass = "br.com.cmabreu.webomt.persistence.infra.ConnFactory";
	

	private static void doLog( String s ) {
		System.out.println(myClass + " " + s);
	}

	public static Session getSession() {
		if ( factory == null ) {
			
			try { 
				doLog("starting Hibernate");
				
				Configuration cfg1 = new Configuration();
				cfg1.configure("hibernate.cfg.xml");
				
				StandardServiceRegistryBuilder serviceRegistryBuilder1 = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder1.applySettings( cfg1.getProperties() );
				ServiceRegistry serviceRegistry1 = serviceRegistryBuilder1.build();	
				
				factory = cfg1.buildSessionFactory(serviceRegistry1);			
				
			} catch (Throwable ex) { 
				doLog("fail: " + ex.getMessage() );  
				return null;
			}
		} 
		
		Session session = factory.openSession();
		return session;
	}

	
}

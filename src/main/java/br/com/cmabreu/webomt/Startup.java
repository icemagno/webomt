package br.com.cmabreu.webomt;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Startup implements ServletContextListener {
    
	@Override
    public void contextInitialized(ServletContextEvent event) {
		System.out.println("INIT");
    	ServletContext context = event.getServletContext();
    	System.setProperty("rootPath", context.getRealPath("/") );
	}
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}

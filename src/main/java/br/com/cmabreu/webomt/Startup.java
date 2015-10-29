package br.com.cmabreu.webomt;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.cmabreu.webomt.misc.PathFinder;

@WebListener
public class Startup implements ServletContextListener {
    
	@Override
    public void contextInitialized(ServletContextEvent event) {
		System.out.println("INIT");
    	ServletContext context = event.getServletContext();
    	System.setProperty("rootPath", context.getRealPath("/") );
    	
    	
    	try {
			new File( PathFinder.getInstance().getPath() +  "/foms/" ).mkdirs();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
	}
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}

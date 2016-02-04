package br.com.cmabreu.webomt;

import hla.rti1516e.CallbackModel;
import hla.rti1516e.RTIambassador;
import hla.rti1516e.RtiFactory;
import hla.rti1516e.RtiFactoryFactory;
import hla.rti1516e.exceptions.FederationExecutionAlreadyExists;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.misc.UserType;
import br.com.cmabreu.webomt.persistence.entity.User;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.services.UserService;

@WebListener
public class Startup implements ServletContextListener {

	protected static void setEnv(Map<String, String> newenv) {
		try {
			Class<?> processEnvironmentClass = Class
					.forName("java.lang.ProcessEnvironment");
			Field theEnvironmentField = processEnvironmentClass
					.getDeclaredField("theEnvironment");
			theEnvironmentField.setAccessible(true);
			Map<String, String> env = (Map<String, String>) theEnvironmentField
					.get(null);
			env.putAll(newenv);
			Field theCaseInsensitiveEnvironmentField = processEnvironmentClass
					.getDeclaredField("theCaseInsensitiveEnvironment");
			theCaseInsensitiveEnvironmentField.setAccessible(true);
			Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField
					.get(null);
			cienv.putAll(newenv);
		} catch (NoSuchFieldException e) {
			try {
				Class[] classes = Collections.class.getDeclaredClasses();
				Map<String, String> env = System.getenv();
				for (Class cl : classes) {
					if ("java.util.Collections$UnmodifiableMap".equals(cl
							.getName())) {
						Field field = cl.getDeclaredField("m");
						field.setAccessible(true);
						Object obj = field.get(env);
						Map<String, String> map = (Map<String, String>) obj;
						map.clear();
						map.putAll(newenv);
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	private void checkUser() throws Exception {
		UserService us = new UserService();
		try {
			us.getList().size();
		} catch (NotFoundException ignored ) {
			// No users found. We need an Admin!
			User usr = new User();
			usr.setFullName("System Administrator");
			usr.setLoginName("admin");
			usr.setType( UserType.ADMIN );
			usr.setPassword("admin");
			usr.setUserMail("no.mail@localhost");
			us.newTransaction();
			us.insertUser(usr);
		}		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("INIT");
		
		try {
			checkUser();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			Map<String, String> newenv = new HashMap<String, String>();
			newenv.put("RTI_RID_FILE", PathFinder.getInstance().getPath() + "/rti.RID" );
			setEnv( newenv );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("getting factory...");
			RtiFactory factory = RtiFactoryFactory.getRtiFactory();

			System.out.println("starting RTI...");
			RTIambassador rtiamb = factory.getRtiAmbassador();

			System.out.println("starting Ambassador...");
			MyAmbassador myAmb = new MyAmbassador();
			System.out.println("connecting to the RTI...");
			rtiamb.connect(myAmb, CallbackModel.HLA_IMMEDIATE);
			
			System.out.println("connected to the RTI: " + rtiamb.getHLAversion());
			
			
			try	{
				URL[] modules = new URL[]{
					    (new File(PathFinder.getInstance().getPath() + "/foms/HLAstandardMIM.xml")).toURI().toURL()
				};
				
				rtiamb.createFederationExecution( "ExampleFederation", modules );
				System.out.println( "Created Federation" );
			} catch( FederationExecutionAlreadyExists exists ) {
				System.out.println( "Didn't create federation, it already existed" );
			}			
			
			
			
			
			System.out.println("Done.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(PathFinder.getInstance().getPath());
			new File(PathFinder.getInstance().getPath() + "/foms/").mkdirs();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}

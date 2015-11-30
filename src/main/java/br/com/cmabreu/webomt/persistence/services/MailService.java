package br.com.cmabreu.webomt.persistence.services;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.cmabreu.webomt.misc.LogManager;
import br.com.cmabreu.webomt.misc.Logger;
import br.com.cmabreu.webomt.misc.UserType;
import br.com.cmabreu.webomt.persistence.entity.User;

public class MailService {
	private Logger logger = LogManager.getLogger( this.getClass().getName() );
	

	public void sendUserRequest( User user ) throws Exception {
		logger.debug("new user request " + user.getFullName() );
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "A new user is requesting access to Sagitarii system.<br>";
        msgBody += "Full Name: " + user.getFullName() + "<br>";
        msgBody += "Login Name: " + user.getLoginName() + "<br>";
        msgBody += "Mail Address: " + user.getUserMail() + "<br>";

        try {
        	MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sagitarii@eic.cefet-rj.br", "Sagitarii Mail Service"));
            
            UserService us = new UserService();
            List<User> users = us.getList( UserType.ADMIN );
            
            for ( User admin : users ) {
            	msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(admin.getUserMail(), admin.getFullName() ));
            }
            
            msg.setSubject("User Access Request: "+ user.getFullName() );
            msg.setText(msgBody, "utf-8", "html");
            Transport.send(msg);

        } catch ( Exception e) {
			logger.error( e.getMessage() );
		}
	}
	

	public void notifyUserChange( User user ) throws Exception {
		logger.debug("send user change notification " + user.getFullName() );
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "An administrator updated your data.<br>";
        msgBody += "Full Name: " + user.getFullName() + "<br>";
        msgBody += "Login Name: " + user.getLoginName() + "<br>";
        msgBody += "Mail Address: " + user.getUserMail() + "<br>";
        msgBody += "Type: " + user.getType() + "<br>";

        try {
        	MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sagitarii@eic.cefet-rj.br", "Sagitarii Mail Service"));
            
            UserService us = new UserService();
            List<User> users = us.getList( UserType.ADMIN );
            
            for ( User admin : users ) {
            	msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(admin.getUserMail(), admin.getFullName() ));
            }
            
            msg.setSubject("Your user data was updated");
            msg.setText(msgBody, "utf-8", "html");
            Transport.send(msg);

        } catch ( Exception e) {
			logger.error( e.getMessage() );
		}
	}
	
	
}

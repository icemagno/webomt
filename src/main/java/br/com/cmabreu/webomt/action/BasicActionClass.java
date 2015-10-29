package br.com.cmabreu.webomt.action;

import br.com.cmabreu.webomt.persistence.entity.User;

import com.opensymphony.xwork2.ActionContext;

public class BasicActionClass {
	private User loggedUser;
	
	public User getLoggedUser() {
		loggedUser = (User)ActionContext.getContext().getSession().get("loggedUser");
		return loggedUser;
	}	
	
	public void setMessageText(String messageText) {
		messageText = messageText.replaceAll("[\n\r]", "");
		ActionContext.getContext().getSession().put("messageText", messageText );
	}

	public String getMessageText() {
		String messageText = (String)ActionContext.getContext().getSession().get("messageText");
		setMessageText("");
		return messageText;
	}

	public BasicActionClass() {
		
	}

	
}

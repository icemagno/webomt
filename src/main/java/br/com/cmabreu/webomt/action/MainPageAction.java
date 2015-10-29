
package br.com.cmabreu.webomt.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.exceptions.DatabaseConnectException;
import br.com.cmabreu.webomt.persistence.exceptions.NotFoundException;
import br.com.cmabreu.webomt.persistence.services.FederationService;

@Action (value = "mainPage", results = { @Result (location = "mainPage.jsp", name = "ok") } ) 

@ParentPackage("default")
public class MainPageAction extends BasicActionClass {
	
	private List<Federation> federationList;

	public String execute () {
		try {
			FederationService fs = new FederationService();
			federationList = fs.getList();
		} catch (DatabaseConnectException | NotFoundException e) {
		}

		return "ok";
	}

	public List<Federation> getFederationList() {
		return federationList;
	}

}

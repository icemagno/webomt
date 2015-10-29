
package br.com.cmabreu.webomt.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;

@Action (value = "doLogout", results = { @Result (type="redirect", location = "${destiny}", name = "ok") } ) 

@ParentPackage("default")
public class DoLogOutAction extends BasicActionClass {
	private String destiny;
	
	public String execute () {
		destiny = "index";
		ActionContext.getContext().getSession().put("loggedUser", null);
		return "ok";
	}

	public String getDestiny() {
		return destiny;
	}

}


package br.com.cmabreu.webomt.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.services.FederationService;

@Action (value = "editFomFile", results = { @Result (location = "editfomfile.jsp", name = "ok") },
interceptorRefs= { @InterceptorRef("seguranca")	 }) 

@ParentPackage("default")
public class EditFomFileAction extends BasicActionClass {
	private int idFederation;
	private Federation federation;
	private String fomFile;

	public String execute () {
		
		try {
			FederationService fs = new FederationService();
			federation = fs.getFederation(idFederation);
			
			String fomFilePath = PathFinder.getInstance().getPath() + "/foms/" + federation.getSerial() + "/" + federation.getDefinitionFile();
			fomFile = PathFinder.readFile(fomFilePath);		
			
		} catch ( Exception e ) {
			setMessageText( "Error: " + e.getMessage() );
		}
		
		return "ok";
	}

	public void setIdFederation(int idFederation) {
		this.idFederation = idFederation;
	}

	public int getIdFederation() {
		return idFederation;
	}
	
	public Federation getFederation() {
		return federation;
	}
	
	public String getFomFile() {
		return fomFile;
	}
}

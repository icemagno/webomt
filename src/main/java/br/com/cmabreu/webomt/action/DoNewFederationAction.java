
package br.com.cmabreu.webomt.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.services.FederationService;

@Action (value = "doNewFederation", results = { @Result (type="redirect", location = "federations", name = "ok") } ) 

@ParentPackage("default")
public class DoNewFederationAction extends BasicActionClass {
	private Federation federation;
	private String description;
	private File fomFile;
	private String fomFileContentType;
	private String fomFileFileName;
	private String destPath;
	   
	public String execute () {

		if ( fomFileContentType == null ) {
			setMessageText("You must provide a XML Federation Definition file.");
			return "ok";
		}
		
		if ( !fomFileContentType.equals("text/xml") ) {
			setMessageText("The uploaded definition file must be a XML file.");
			return "ok";
		}
		
		if ( federation.getName() == null ) {
			setMessageText("You must give a name to this Federation.");
			return "ok";
		}
		
		
		try {
			destPath = PathFinder.getInstance().getPath() +  "/foms/" + federation.getName() + "/";
	     	File destFile  = new File(destPath, fomFileFileName);
	    	FileUtils.copyFile(fomFile, destFile);	     	 
		
	    	federation.setDefinitionFile(fomFileFileName);
	    	federation.setDescription( description );
			FederationService fs = new FederationService();
			fs.insertFederation(federation);
			
		} catch ( Exception e ) {
			setMessageText("Error : " + e.getMessage() );
		}
		
		return "ok";
	}

	public void setFederation(Federation federation) {
		this.federation = federation;
	}

	public void setFomFile(File fomFile) {
		this.fomFile = fomFile;
	}

	public void setFomFileContentType(String fomFileContentType) {
		this.fomFileContentType = fomFileContentType;
	}

	public void setFomFileFileName(String fomFileFileName) {
		this.fomFileFileName = fomFileFileName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}

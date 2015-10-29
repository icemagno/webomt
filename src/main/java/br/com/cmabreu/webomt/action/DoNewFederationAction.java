
package br.com.cmabreu.webomt.action;

import java.io.File;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.ieee1516e.ObjectModelType;
import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.parser.FOMObjectCreator;
import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.services.FederationService;
import br.com.cmabreu.webomt.wrapper.Helper;

@Action (value = "doNewFederation", results = { @Result (type="redirect", location = "${dest}", name = "ok"),
		@Result (type="redirect", location = "mainPage", name = "error")} ) 

@ParentPackage("default")
public class DoNewFederationAction extends BasicActionClass {
	private File fomFile;
	private String fomFileContentType;
	private String fomFileFileName;
	private String dest;
	private String fedVisible;
	
	public String execute () {

		if ( fomFileContentType == null ) {
			setMessageText("You must provide a XML Federation Definition file.");
			return "ok";
		}
		
		if ( !fomFileContentType.equals("text/xml") ) {
			setMessageText("The uploaded definition file must be a XML file.");
			return "ok";
		}
		
		
		try {
			
			String serial = UUID.randomUUID().toString().replace("-", "").substring(0,9);
			
			String destPath = PathFinder.getInstance().getPath() +  "/foms/" + serial + "/";
	     	File destFile  = new File(destPath, fomFileFileName);
	    	FileUtils.copyFile(fomFile, destFile);	     	 
		
//	    	System.out.println( destPath );
	    	
			FOMObjectCreator foc = new FOMObjectCreator();
			String fomFile = destPath + fomFileFileName;
			ObjectModelType omt = foc.generate(fomFile);
	    	
			Federation federation = new Federation();
			if ( omt.getModelIdentification().getGlyph() != null ) {
				byte[] bytes = omt.getModelIdentification().getGlyph().getValue();
				federation.setGlyph( DatatypeConverter.printBase64Binary( bytes ) );
			}	    	
	    	federation.setDefinitionFile( fomFileFileName );
	    	federation.setDescription( Helper.fromNonEmptyString( omt.getModelIdentification().getDescription() ) );
	    	federation.setSerial(serial);
	    	federation.setName( Helper.fromNonEmptyString( omt.getModelIdentification().getName() ) );
	    	federation.setOwner( getLoggedUser() );
    		federation.setVisible( fedVisible.equals("on") );
	    	
			FederationService fs = new FederationService();
			int idFederation = fs.insertFederation(federation);
			
			dest = "showFomFile?idFederation=" + idFederation;
			
			return "ok";
			
		} catch ( Exception e ) {
			setMessageText("Error : " + e.getMessage() );
			return "error";
		}
		
		
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

	public String getDest() {
		return dest;
	}
	
	public void setFedVisible(String fedVisible) {
		this.fedVisible = fedVisible;
	}
	
}


package br.com.cmabreu.webomt.action;

import java.io.PrintWriter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.services.FederationService;

@Action (value = "saveFomFile", results = { @Result (type="redirect", location = "federations", name = "ok") } ) 

@ParentPackage("default")
public class SaveFomFileAction extends BasicActionClass {
	private int idFederation;
	private String code;
	
	public String execute () {
		
		try {
			FederationService fs = new FederationService();
			Federation federation = fs.getFederation(idFederation);
			String fomFilePath = PathFinder.getInstance().getPath() + "/foms/" + federation.getName() + "/" + federation.getDefinitionFile();
			
			PrintWriter out = new PrintWriter( fomFilePath ); 		
			
			if( out != null ) {
				out.print( code );
				out.close();
				setMessageText( federation.getDefinitionFile() + " FOM saved." );
			}
			
		} catch ( Exception e ) {
			setMessageText( "Error: " + e.getMessage() );
		}
		
		return "ok";
	}

	public void setIdFederation(int idFederation) {
		this.idFederation = idFederation;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}


package br.com.cmabreu.webomt.action;

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.ieee1516e.ObjectModelType;
import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.parser.FOMObjectCreator;
import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.services.FederationService;
import br.com.cmabreu.webomt.wrapper.FomObject;
import br.com.cmabreu.webomt.wrapper.FomWrapper;

@Action (value = "showFomFile", results = { @Result (location = "fomfile.jsp", name = "ok"),
		@Result (location = "fomfileAlt.jsp", name = "alternate") } , 
		interceptorRefs= { @InterceptorRef("seguranca")	 } )  

@ParentPackage("default")
public class ShowFomFileAction extends BasicActionClass {
	private int idFederation;
	private Federation federation;
	private ObjectModelType omt;
	private String glyphImage;
	private List<FomObject> objects;
	private String pageType;

	
	public String execute () {
		
		String retorno = "ok";
		if( pageType != null && pageType.equals("alternative")){
			retorno = "alternate";
		}
		
		try {
			
			FederationService fs = new FederationService();
			federation = fs.getFederation(idFederation);
			
			FOMObjectCreator foc = new FOMObjectCreator();
			String fomFile = PathFinder.getInstance().getPath() + "/foms/" + federation.getSerial() + "/" + federation.getDefinitionFile();

			omt = foc.generate(fomFile);
			
			FomWrapper fw = new FomWrapper( omt );
			objects = fw.getAllObjects();
			
			if ( omt.getModelIdentification().getGlyph() != null ) {
				byte[] bytes = omt.getModelIdentification().getGlyph().getValue();
				glyphImage = DatatypeConverter.printBase64Binary( bytes );
			}			
			
		} catch ( Exception e ) {
			if ( e.getMessage() != null ) {
				setMessageText( "Error: " + e.getMessage() );
			} else {
				setMessageText( "Error: Cannot parse FOM file. Verify XML namespace (http://standards.ieee.org/IEEE1516-2010)." );
			}
		}

		return retorno;
	}

	public void setIdFederation(int idFederation) {
		this.idFederation = idFederation;
	}

	public Federation getFederation() {
		return federation;
	}

	public ObjectModelType getOmt() {
		return omt;
	}
	
	public String getGlyphImage() {
		return glyphImage;
	}
	
	public List<FomObject> getObjects() {
		return objects;
	}
	
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
}

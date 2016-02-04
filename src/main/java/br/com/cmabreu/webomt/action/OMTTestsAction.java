
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

@Action (value = "omtTests", results = { @Result (location = "fomfile.jsp", name = "ok"),
		@Result (location = "fomfileAlt.jsp", name = "alternate") } , 
		interceptorRefs= { @InterceptorRef("seguranca")	 } )  

@ParentPackage("default")
public class OMTTestsAction extends BasicActionClass {
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
			
			// TESTE INCLUSAO OBJETO
			/*
			ObjectsType ot = omt.getObjects();
			List<Attribute> attr = new ArrayList<Attribute>();
			
			Attribute attr1 = Helper.createAttribute("Attr1", "HLAunicodeString", UpdateEnumerations.STATIC,
					OwnershipEnumerations.NO_TRANSFER, "NA", SharingEnumerations.PUBLISH_SUBSCRIBE, "HLAreliable",
					OrderEnumerations.RECEIVE, "Meu atributo de teste");
			
			attr.add( attr1 );
			
			ObjectClass myObject = Helper.createObject("MyObject","Semantics", SharingEnumerations.PUBLISH_SUBSCRIBE, attr);

			try {
				ObjectClass myObj = Helper.getObject( ot.getObjectClass(), "Car");
				myObj.getObjectClass().add( myObject );
			} catch ( Exception e ) {
			
			}
			
			
			
			// TESTE INCLUSAO INTERAÇÂO
			InteractionsType it = omt.getInteractions();
			InteractionClass ic = it.getInteractionClass();

			Parameter param1 = Helper.createParameter("Param1", "HLAunicodeString", "Semantica param1");
			Parameter param2 = Helper.createParameter("Param2", "HLAunicodeString", "Semantica param2");
			
			List<Parameter> params = new ArrayList<Parameter>();
			params.add(param1);
			params.add(param2);
			params.add(param2);
			params.add(param2);
			params.add(param2);
			
			InteractionClass newIc = Helper.createInteraction("Criado Manualmente", SharingEnumerations.PUBLISH_SUBSCRIBE,
					"HLAreliable", OrderEnumerations.RECEIVE, "Teste de semantica", params );
			
			ic.getInteractionClass().add( newIc );
			

			foc.saveFOMFile( omt, "d:/teste.xml" );
			*/
			
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

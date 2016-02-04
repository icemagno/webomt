
package br.com.cmabreu.webomt.action;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.cmabreu.webomt.ieee1516e.ObjectModelType;
import br.com.cmabreu.webomt.misc.PathFinder;
import br.com.cmabreu.webomt.parser.FOMObjectCreator;
import br.com.cmabreu.webomt.persistence.entity.Federation;
import br.com.cmabreu.webomt.persistence.services.FederationService;
import br.com.cmabreu.webomt.wrapper.FomAttribute;
import br.com.cmabreu.webomt.wrapper.FomObject;
import br.com.cmabreu.webomt.wrapper.FomWrapper;

@Action (value = "generate", results = { @Result (location = "fomfile.jsp", name = "ok"),
		@Result (location = "fomfileAlt.jsp", name = "alternate") } , 
		interceptorRefs= { @InterceptorRef("seguranca")	 } )  

@ParentPackage("default")
public class GenerateClassesAction extends BasicActionClass {
	private int idFederation;
	private Federation federation;
	private ObjectModelType omt;
	private String glyphImage;
	private List<FomObject> objects;
	private String pageType;

	private String javalizer( String value ) {
		return Character.toLowerCase(value.charAt(0)) + value.substring(1);
	}
	
	private String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.forName("UTF-8") );
	}	
	
	/*
	private void createFederation( String path, String federationName ) throws Exception {
		String templateFile = PathFinder.getInstance().getPath() + "/templates/FederateTemplate.txt";
		String content = readFile( templateFile );
		
		String target = path + "/" + federationName + ".java";
		System.out.println( target );
		FileUtils.writeStringToFile(new File(target), content);
	}
	*/

	private void createObject( String path, FomObject object ) throws Exception {
		String objectName = object.getName();
		String templateFile = PathFinder.getInstance().getPath() + "/templates/ObjectTemplate.txt";
		String content = readFile( templateFile );
		
		content = content.replace("#OBJ_NAME#", objectName);
		
		StringBuilder sbAtt = new StringBuilder();
		StringBuilder sbGtr = new StringBuilder();
		StringBuilder sbStr = new StringBuilder();
		for( FomAttribute fat : object.getAttributes() ) {
			String attName = javalizer( fat.getName() );
			sbAtt.append("\tprivate " + fat.getDataType() + " " + attName +";\n");
			sbGtr.append("\tpublic void get"+fat.getName()+"() {\n"); 
			sbGtr.append("\t\treturn this."+attName+";\n\t}\n\n");
			sbStr.append("\tpublic void set"+fat.getName()+"("+fat.getDataType()+" "+ attName+") {\n"); 
			sbStr.append("\t\tthis."+attName+" = " + attName + ";\n\t}\n\n");
		}    

		content = content.replace("#OBJ_ATTRIBUTES#", sbAtt.toString() );
		content = content.replace("#OBJ_GETTERS#", sbGtr.toString() );
		content = content.replace("#OBJ_SETTERS#", sbStr.toString() );
		
		String target = path + "/" + objectName + ".java";
		System.out.println( target );
		FileUtils.writeStringToFile(new File(target), content);
	}
	
	
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

			
			System.out.println("---------- " + federation.getName() + " ----------");
			String path = PathFinder.getInstance().getPath() + "/" + federation.getName();
			File fPath = new File( path );
			fPath.mkdirs();
			
			for ( FomObject fob : objects ) {
				System.out.println( " >> " +  fob.getName() );
				createObject( path, fob );
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

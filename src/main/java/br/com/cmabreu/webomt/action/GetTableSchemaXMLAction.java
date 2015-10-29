
package br.com.cmabreu.webomt.action;

import java.io.ByteArrayInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@Action(value="getTableSchemaXML", results= {  
	    @Result(name="ok", type="stream", params = {
                "contentType", "text/xml",
                "inputName", "fileInputStream",
                "contentDisposition", "attachment;filename=\"${fileName}\"",
                "bufferSize", "1024"
        }) }
) 

@ParentPackage("default")
public class GetTableSchemaXMLAction extends BasicActionClass {
	private String fileName;
	private ByteArrayInputStream fileInputStream;
	private String tableName;
	
	public String execute () {
		
		try {
			
			//fileInputStream = rs.getTableSchemaXML( tableName );
	        //fileName = tableName + ".xml";
		} catch ( Exception e ) {
            //
		}
		
		return "ok";
	}

	
	public String getFileName() {
		return fileName;
	}
	
	public ByteArrayInputStream getFileInputStream() {
		return fileInputStream;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}

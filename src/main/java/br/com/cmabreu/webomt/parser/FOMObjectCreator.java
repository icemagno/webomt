package br.com.cmabreu.webomt.parser;

/**
 * 	HLA FOM Object Creator 1.0.3		 	 06/10/2014
 *  Author: Carlos Magno Abreu - magno.mabreu@gmail.com 
 *  
 *  This class will receive a XML FOM ( HLA Evolved standard ) and
 *  generate all Java objects from this FOM.
 *  
 *  Java classes generated using JAXB and based on IEEE1516-DIF-2010.xsd schema from
 *  http://standards.ieee.org/downloads/1516/1516.2-2010/IEEE1516-DIF-2010.xsd
 *  
 *  > xjc -p ieee1516e IEEE1516-DIF-2010.xsd
 *  
 *  All your FOM files MUST respect this namespaces:
 *  
 *  <objectModel xsi:schemaLocation="http://standards.ieee.org/IEEE1516-2010 
 *  	http://standards.ieee.org/downloads/1516/1516.2-2010/IEEE1516-DIF-2010.xsd" 
 *  	xmlns="http://standards.ieee.org/IEEE1516-2010" 
 *  	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
 *
 *	Added XmlRootElement annotation to ObjectModelType "by hand". You can't save if you don't do this. 
 *
 */

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import br.com.cmabreu.webomt.ieee1516e.ObjectModelType;

public class FOMObjectCreator {

	public void saveFOMFile( ObjectModelType objectModelType, String xmlFile ) {
		try {
			File file = new File( xmlFile );
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectModelType.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(objectModelType, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}		
	}

	public ObjectModelType generate( String fomFile ) throws Exception {
		File file = new File( fomFile );
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectModelType.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		FileInputStream inputStream = new FileInputStream( file );
		Source source = new StreamSource(inputStream);
		JAXBElement<ObjectModelType> root = jaxbUnmarshaller.unmarshal(source, ObjectModelType.class);
		ObjectModelType objectModel = root.getValue();			
		return objectModel;
	}

}

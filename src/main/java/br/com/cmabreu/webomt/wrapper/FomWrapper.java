package br.com.cmabreu.webomt.wrapper;

/**
 * This class is a wrapper to simplify the JAXB FOM structure.
 */

import java.util.ArrayList;
import java.util.List;

import br.com.cmabreu.webomt.ieee1516e.DataTypesType;
import br.com.cmabreu.webomt.ieee1516e.ObjectClass;
import br.com.cmabreu.webomt.ieee1516e.ObjectModelType;
import br.com.cmabreu.webomt.ieee1516e.ObjectsType;

public class FomWrapper {
	private ObjectModelType objectModel;
	private FomObject fomObjectRoot;
	private List<FomObject> allObjects;

	private FomDataTypes dataTypes;
	
	public FomWrapper( ObjectModelType objectModel ) {
		this.objectModel = objectModel;
		allObjects = new ArrayList<FomObject>();
		collectObjects();
		collectDataTypes();
		
		//objectModel.getDimensions()
		//objectModel.getInteractions()
		//objectModel.getTransportations()
		//objectModel.getModelIdentification()
		
	}
	
	private void collectDataTypes() {
		DataTypesType dtt = objectModel.getDataTypes();
		if ( dtt != null ) {
			dataTypes = new FomDataTypes(dtt);
		}

	}
	
	
	private void addToObjectCollection( FomObject object  ) {
		allObjects.add( object );
		for ( FomObject child : object.getChildren()  ) {
			addToObjectCollection( child );
		}
	}	
	
	private void collectObjects() {
		if ( objectModel != null ) {
			ObjectsType ot = objectModel.getObjects();
			if ( ot != null ) {
				ObjectClass objectClass = ot.getObjectClass();
				fomObjectRoot = new FomObject(objectClass);
				addToObjectCollection( fomObjectRoot );
			}
		}
	}
	
	public FomObject getFomObject() {
		return fomObjectRoot;
	}
	
	public FomDataTypes getDataTypes() {
		return dataTypes;
	}
	
	public List<FomObject> getAllObjects() {
		return allObjects;
	}
	
}

package br.com.cmabreu.webomt.wrapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cmabreu.webomt.ieee1516e.Attribute;
import br.com.cmabreu.webomt.ieee1516e.ObjectClass;

public class FomObject {
	private ObjectClass object;
	private List<FomObject> children;
	private String fullyQualifiedName;
	private String name;
	private List<FomAttribute> attributes;
	private String semantics;
	private String sharing;
	
	private void init( ObjectClass objectClass ) {
		this.object = objectClass;
		children = new ArrayList<FomObject>();
		attributes = new ArrayList<FomAttribute>();
		name = objectClass.getName().getValue();
		
		if ( objectClass.getSharing() != null ) {
			sharing = objectClass.getSharing().getValue().toString();
		}
		
		if( objectClass.getSemantics() != null ) {
			semantics = objectClass.getSemantics().getValue();
		}
		
		for ( Attribute attr : objectClass.getAttribute() ) {
			FomAttribute newAttribute = new FomAttribute( attr );
			attributes.add( newAttribute );
		}

	}
	
	public FomObject( ObjectClass objectClass ) {
		init( objectClass );
		fullyQualifiedName = name;
		
		for ( ObjectClass child : objectClass.getObjectClass() ) {
			FomObject newObject = new FomObject( child, name );
			children.add( newObject );
		}
	}

	
	public FomObject( ObjectClass objectClass, String parentName ) {
		init( objectClass );
		fullyQualifiedName = parentName + "." + name;
		
		for ( ObjectClass child : objectClass.getObjectClass() ) {
			FomObject newObject = new FomObject( child, fullyQualifiedName );
			children.add( newObject );
		}
	}
	
	
	public List<FomObject> getChildren() {
		return children;
	}
	
	public ObjectClass getObject() {
		return object;
	}

	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}
	
	public String getName() {
		return name;
	}
	
	public List<FomAttribute> getAttributes() {
		return attributes;
	}
	
	public String getSemantics() {
		return semantics;
	}
	
	public String getSharing() {
		return sharing;
	}
	
}

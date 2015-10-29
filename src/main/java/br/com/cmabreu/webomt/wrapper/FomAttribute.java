package br.com.cmabreu.webomt.wrapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cmabreu.webomt.ieee1516e.Attribute;
import br.com.cmabreu.webomt.ieee1516e.ReferenceType;

public class FomAttribute {
	private String name;
	private String dataType;
	private Attribute attribute;
	private List<String> dimensions;
	private String ownerShip;
	private String order;
	private String semantics;
	private String sharing;
	private String transportation;
	private String updateCondition;
	private String updateType;

	public FomAttribute( Attribute attribute ) {
		this.attribute = attribute;
		name = attribute.getName().getValue();
		dataType = attribute.getDataType().getValue();
		dimensions = new ArrayList<String>();
		order = attribute.getOrder().getValue().toString();
		ownerShip = attribute.getOwnership().getValue().toString();
		
		if ( attribute.getUpdateType() != null ) {
			updateType = attribute.getUpdateType().getValue().toString();
		}
		if ( attribute.getUpdateCondition() != null ) {
			updateCondition = attribute.getUpdateCondition().getValue();
		}
		if ( attribute.getTransportation() != null ) {
			transportation = attribute.getTransportation().getValue();
		}
		if ( attribute.getSharing() != null ) {
			sharing = attribute.getSharing().getValue().toString();
		}
		if ( attribute.getSemantics() != null ) {
			semantics = attribute.getSemantics().getValue();
		}
		if ( attribute.getDimensions() != null ) {
			for ( ReferenceType dim : attribute.getDimensions().getDimension() ) {
				dimensions.add( dim.getValue() );
			}
		}
	}
	
	public Attribute getAttribute() {
		return attribute;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public List<String> getDimensions() {
		return dimensions;
	}
	
	public String getOrder() {
		return order;
	}
	
	public String getOwnerShip() {
		return ownerShip;
	}
	
	public String getSemantics() {
		return semantics;
	}
	
	public String getSharing() {
		return sharing;
	}
	
	public String getTransportation() {
		return transportation;
	}
	
	public String getUpdateCondition() {
		return updateCondition;
	}
	
	public String getUpdateType() {
		return updateType;
	}
}

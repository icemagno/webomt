package br.com.cmabreu.webomt.wrapper;

import java.util.List;

import br.com.cmabreu.webomt.ieee1516e.Attribute;
import br.com.cmabreu.webomt.ieee1516e.IdentifierType;
import br.com.cmabreu.webomt.ieee1516e.InteractionClass;
import br.com.cmabreu.webomt.ieee1516e.ObjectClass;
import br.com.cmabreu.webomt.ieee1516e.OrderEnumerations;
import br.com.cmabreu.webomt.ieee1516e.OwnershipEnumerations;
import br.com.cmabreu.webomt.ieee1516e.OwnershipType;
import br.com.cmabreu.webomt.ieee1516e.Parameter;
import br.com.cmabreu.webomt.ieee1516e.ReferenceType;
import br.com.cmabreu.webomt.ieee1516e.SharingEnumerations;
import br.com.cmabreu.webomt.ieee1516e.SharingType;
import br.com.cmabreu.webomt.ieee1516e.UpdateEnumerations;
import br.com.cmabreu.webomt.ieee1516e.UpdateType;

public class Helper {

	public static InteractionClass createInteraction(String name, SharingEnumerations sharing, String transportation,
			OrderEnumerations order, String semantics, List<Parameter> parameters) {
		InteractionClass newIc = new InteractionClass();
		newIc.setName( getIdentifierType( name ) );
		newIc.setSharing( getSharingType( sharing ) );
		newIc.setTransportation( Helper.getReferenceType( transportation ) );
		newIc.setOrder( Helper.getOrderType( order ) );
		newIc.setSemantics( Helper.getString( semantics ) );
		newIc.getParameter().addAll( parameters );
		return newIc;
	}
	
	public static Attribute createAttribute(String name, String referenceType, UpdateEnumerations updateType,
			OwnershipEnumerations ownership, String updateCondition, SharingEnumerations sharing, String transportation,
			OrderEnumerations order, String semantics ) {
		Attribute attr = new Attribute();
		attr.setName( Helper.getIdentifierType( name ) );
		attr.setDataType( Helper.getReferenceType( referenceType ) );
		attr.setUpdateType( Helper.getUpdateType( updateType ) );
		attr.setUpdateCondition( Helper.getString( updateCondition ) );
		attr.setOwnership( Helper.getOwnershipType( ownership ) );
		attr.setSharing( Helper.getSharingType( sharing ) );
		attr.setTransportation( Helper.getReferenceType( transportation ) );
		attr.setOrder( Helper.getOrderType( order ) );
		attr.setSemantics( Helper.getString( semantics ) );
		return attr;
	}
	
	public static ObjectClass createObject( String name, String semantics, SharingEnumerations sharingType, 
			List<Attribute> attributes) {
		ObjectClass obj = new ObjectClass();
		obj.setName( Helper.getIdentifierType( name ) );
		obj.setSemantics( Helper.getString( semantics) );
		obj.setSharing( Helper.getSharingType( sharingType ) );
		obj.getAttribute().addAll( attributes );
		return obj;
	}
	
	public static Parameter createParameter( String name, String dataType, String semantics ) {
		Parameter param = new Parameter();
		param.setName( Helper.getIdentifierType( name ) );
		param.setDataType( Helper.getReferenceType( dataType ) );
		param.setSemantics( Helper.getString( semantics ) );
		return param;
	}
	
	public static SharingType getSharingType( SharingEnumerations sharingType) {
		SharingType st = new SharingType();
		st.setValue( sharingType );
		return st;
	}
	
	public static String fromIdentifierType( IdentifierType id ) {
		return id.getValue();
	}
	
	public static IdentifierType getIdentifierType( String value ) {
		IdentifierType idt = new IdentifierType();
		idt.setValue( value );
		return idt;
	}
	
	public static br.com.cmabreu.webomt.ieee1516e.String getString( String value ) {
		br.com.cmabreu.webomt.ieee1516e.String semantic = new br.com.cmabreu.webomt.ieee1516e.String();
		semantic.setValue( value );
		return semantic;
	}
	
	public static ReferenceType getReferenceType( String value ) {
		ReferenceType rt = new ReferenceType();
		rt.setValue( value );
		return rt;
	}
	
	public static UpdateType getUpdateType( UpdateEnumerations value ) {
		UpdateType ut = new UpdateType();
		ut.setValue( value );
		return ut;
	}
	
	public static OwnershipType getOwnershipType( OwnershipEnumerations value ) {
		OwnershipType ot = new OwnershipType();
		ot.setValue( value );
		return ot;
	}
	
	public static ObjectClass getObject( ObjectClass root, String name ) {
		ObjectClass result = null;
		for ( ObjectClass oc : root.getObjectClass() ) {
			if ( fromIdentifierType( oc.getName() ).equals( name ) ) return oc;
			result = getObject( oc, name );
			if ( result != null ) return result;
		}
		return null;
	}
	
	public static br.com.cmabreu.webomt.ieee1516e.OrderType getOrderType( OrderEnumerations value ) {
		br.com.cmabreu.webomt.ieee1516e.OrderType val = new  br.com.cmabreu.webomt.ieee1516e.OrderType();
		val.setValue( value );
		return val;
	}	
	
}

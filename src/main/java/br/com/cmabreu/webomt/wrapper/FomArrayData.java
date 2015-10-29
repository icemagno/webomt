package br.com.cmabreu.webomt.wrapper;

import br.com.cmabreu.webomt.ieee1516e.ArrayDataTypesType.ArrayData;

public class FomArrayData {
	private String name;
	private String type;
	private ArrayData arrayData;
	private String cardinality;
	private String encoding;
	private String semantics;
	
	public FomArrayData( ArrayData arrayData ) {
		this.arrayData = arrayData;
		name = arrayData.getName().getValue();
		type = arrayData.getDataType().getValue(); 
		cardinality = arrayData.getCardinality().getValue();
		encoding = arrayData.getEncoding().getValue();
		if ( arrayData.getSemantics() != null ) {
			semantics = arrayData.getSemantics().getValue();
		}
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public ArrayData getArrayData() {
		return arrayData;
	}
	
	public String getCardinality() {
		return cardinality;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public String getSemantics() {
		return semantics;
	}
}

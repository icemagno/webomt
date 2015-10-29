package br.com.cmabreu.webomt.wrapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cmabreu.webomt.ieee1516e.DataTypesType;
import br.com.cmabreu.webomt.ieee1516e.ArrayDataTypesType.ArrayData;
import br.com.cmabreu.webomt.ieee1516e.BasicDataRepresentationsType.BasicData;
import br.com.cmabreu.webomt.ieee1516e.EnumeratedDataTypesType.EnumeratedData;
import br.com.cmabreu.webomt.ieee1516e.FixedRecordDataTypesType.FixedRecordData;
import br.com.cmabreu.webomt.ieee1516e.SimpleDataTypesType.SimpleData;
import br.com.cmabreu.webomt.ieee1516e.VariantRecordDataTypesType.VariantRecordData;

public class FomDataTypes {
	private DataTypesType dataTypes;
	private List<FomArrayData> arrayData;
	private List<FomBasicData> basicData;
	private List<FomEnumeratedData> enumeratedData;
	
	public FomDataTypes( DataTypesType dtt ) {
		this.dataTypes = dtt;
		arrayData = new ArrayList<FomArrayData>();
		basicData = new ArrayList<FomBasicData>();
		enumeratedData = new ArrayList<FomEnumeratedData>();
		
		if ( dtt.getFixedRecordDataTypes() != null ) {
			for ( FixedRecordData frd : dtt.getFixedRecordDataTypes().getFixedRecordData() ) {
				
			}
		}
		
		if ( dtt.getSimpleDataTypes() != null ) {
			for ( SimpleData sd : dtt.getSimpleDataTypes().getSimpleData() ) {
				
			}
		}
		
		if( dtt.getVariantRecordDataTypes() != null ) {
			for ( VariantRecordData vrd : dtt.getVariantRecordDataTypes().getVariantRecordData() ) {
				
			}
		}
		
		if ( dtt.getEnumeratedDataTypes() != null ) {
			for ( EnumeratedData ed : dtt.getEnumeratedDataTypes().getEnumeratedData() ) {
				FomEnumeratedData fed = new FomEnumeratedData( ed );
				enumeratedData.add( fed );
			}
		}
		
		if ( dtt.getBasicDataRepresentations() != null ) {
			for ( BasicData bd :  dtt.getBasicDataRepresentations().getBasicData() ) {
				FomBasicData fbd = new FomBasicData( bd ); 
				basicData.add(fbd);
			}
		}
		
		if ( dtt.getArrayDataTypes() != null ) {
			for ( ArrayData ad : dtt.getArrayDataTypes().getArrayData() ) {
				FomArrayData fad = new FomArrayData(ad);
				arrayData.add(fad);
			}
		}
		
		
	}

	
	public DataTypesType getDataTypes() {
		return dataTypes;
	}
	
	public List<FomArrayData> getArrayData() {
		return arrayData;
	}
	
	public List<FomBasicData> getBasicData() {
		return basicData;
	}
	
	public List<FomEnumeratedData> getEnumeratedData() {
		return enumeratedData;
	}
}

package br.com.cmabreu.webomt.wrapper;

import java.util.ArrayList;
import java.util.List;

import br.com.cmabreu.webomt.ieee1516e.EnumeratedDataType.Enumerator;
import br.com.cmabreu.webomt.ieee1516e.EnumeratedDataTypesType.EnumeratedData;

public class FomEnumeratedData {
	private EnumeratedData enumeratedData;
	private String name;
	private String representation;
	private String semantics;
	private List<FomEnumerator> enumerators;
	
	public FomEnumeratedData( EnumeratedData ed ) {
		this.enumeratedData = ed;
		name = ed.getName().getValue();
		representation = ed.getRepresentation().getValue();
		semantics = ed.getSemantics().getValue();
		enumerators = new ArrayList<FomEnumerator>();
		for ( Enumerator en : ed.getEnumerator() ) {
			FomEnumerator fen = new FomEnumerator( en );
			enumerators.add( fen );
		}
	}
	
	
	public EnumeratedData getEnumeratedData() {
		return enumeratedData;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRepresentation() {
		return representation;
	}
	
	public String getSemantics() {
		return semantics;
	}
	
	public List<FomEnumerator> getEnumerators() {
		return enumerators;
	}
}

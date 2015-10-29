package br.com.cmabreu.webomt.wrapper;

import br.com.cmabreu.webomt.ieee1516e.EnumeratedDataType.Enumerator;

public class FomEnumerator {
	private String name;
	private Enumerator enumerator;
	//private List<String> values;
	private String value;
	
	public FomEnumerator( Enumerator enumerator ) {
		this.enumerator = enumerator;
		name = enumerator.getName().getValue();
		value = enumerator.getValue().get(0).getValue();

		//this.values = new ArrayList<String>();
		/*
		for ( ieee1516e.String val : enumerator.getValue() ) {
			values.add( val.getValue() );
		}
		*/
	}

	public String getName() {
		return name;
	}

	/*
	public List<String> getValues() {
		return values;
	}
	*/
	
	public String getValue() {
		return value;
	}

	public Enumerator getEnumerator() {
		return enumerator;
	}
	
	

}

package br.com.cmabreu.webomt.wrapper;

import java.math.BigInteger;

import br.com.cmabreu.webomt.ieee1516e.BasicDataRepresentationsType.BasicData;

public class FomBasicData {
	private BasicData basicData;
	private String name;
	private String encoding;
	private String endian;
	private String interpretation;
	private BigInteger size;
	
	public FomBasicData( BasicData bd) {
		this.basicData = bd;
		name = bd.getName().getValue();
		encoding = bd.getEncoding().getValue();
		endian = bd.getEndian().getValue().toString();
		interpretation = bd.getInterpretation().getValue();
		size = bd.getSize().getValue();
	}
	
	public BasicData getBasicData() {
		return basicData;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public String getEndian() {
		return endian;
	}
	
	public String getInterpretation() {
		return interpretation;
	}
	
	public BigInteger getSize() {
		return size;
	}
}

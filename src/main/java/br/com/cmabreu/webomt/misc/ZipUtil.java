package br.com.cmabreu.webomt.misc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.bind.DatatypeConverter;

public class ZipUtil {
	
	public static String toHexString( byte[] array ) {
	    return DatatypeConverter.printHexBinary(array);
	}
	
	public static byte[] toByteArray( String s ) {
	    return DatatypeConverter.parseHexBinary(s);
	}	
	
	public static byte[] compress(String string) {
		byte[] compressed = null;
		try {
		    ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
		    GZIPOutputStream gos = new GZIPOutputStream(os);
		    gos.write(string.getBytes());
		    gos.close();
		    compressed = os.toByteArray();
		    os.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	    return compressed;
	}

	public static String decompress(byte[] compressed) {
		StringBuilder string = new StringBuilder();
		try {
		    final int BUFFER_SIZE = 32;
		    ByteArrayInputStream is = new ByteArrayInputStream(compressed);
		    GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
		    byte[] data = new byte[BUFFER_SIZE];
		    int bytesRead;
		    while ((bytesRead = gis.read(data)) != -1) {
		        string.append(new String(data, 0, bytesRead));
		    }
		    gis.close();
		    is.close();
		} catch ( Exception e) {
			e.printStackTrace();
		}
	    return string.toString();
	}
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.06 at 09:06:12 AM BRT 
//


package br.com.cmabreu.webomt.ieee1516e;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fixedRecordEncodingEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fixedRecordEncodingEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HLAfixedRecord"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fixedRecordEncodingEnumeration")
@XmlEnum
public enum FixedRecordEncodingEnumeration {

    @XmlEnumValue("HLAfixedRecord")
    HL_AFIXED_RECORD("HLAfixedRecord");
    private final java.lang.String value;

    FixedRecordEncodingEnumeration(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static FixedRecordEncodingEnumeration fromValue(java.lang.String v) {
        for (FixedRecordEncodingEnumeration c: FixedRecordEncodingEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

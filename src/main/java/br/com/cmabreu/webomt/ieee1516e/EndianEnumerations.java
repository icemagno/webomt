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
 * <p>Java class for endianEnumerations.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="endianEnumerations">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Big"/>
 *     &lt;enumeration value="Little"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "endianEnumerations")
@XmlEnum
public enum EndianEnumerations {

    @XmlEnumValue("Big")
    BIG("Big"),
    @XmlEnumValue("Little")
    LITTLE("Little");
    private final java.lang.String value;

    EndianEnumerations(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static EndianEnumerations fromValue(java.lang.String v) {
        for (EndianEnumerations c: EndianEnumerations.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

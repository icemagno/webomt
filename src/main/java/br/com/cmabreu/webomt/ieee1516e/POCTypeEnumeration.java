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
 * <p>Java class for POCTypeEnumeration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="POCTypeEnumeration">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Primary author"/>
 *     &lt;enumeration value="Contributor"/>
 *     &lt;enumeration value="Proponent"/>
 *     &lt;enumeration value="Sponsor"/>
 *     &lt;enumeration value="Release authority"/>
 *     &lt;enumeration value="Technical POC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "POCTypeEnumeration")
@XmlEnum
public enum POCTypeEnumeration {

    @XmlEnumValue("Primary author")
    PRIMARY_AUTHOR("Primary author"),
    @XmlEnumValue("Contributor")
    CONTRIBUTOR("Contributor"),
    @XmlEnumValue("Proponent")
    PROPONENT("Proponent"),
    @XmlEnumValue("Sponsor")
    SPONSOR("Sponsor"),
    @XmlEnumValue("Release authority")
    RELEASE_AUTHORITY("Release authority"),
    @XmlEnumValue("Technical POC")
    TECHNICAL_POC("Technical POC");
    private final java.lang.String value;

    POCTypeEnumeration(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static POCTypeEnumeration fromValue(java.lang.String v) {
        for (POCTypeEnumeration c: POCTypeEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

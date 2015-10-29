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
 * <p>Java class for ownershipEnumerations.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ownershipEnumerations">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Divest"/>
 *     &lt;enumeration value="Acquire"/>
 *     &lt;enumeration value="DivestAcquire"/>
 *     &lt;enumeration value="NoTransfer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ownershipEnumerations")
@XmlEnum
public enum OwnershipEnumerations {

    @XmlEnumValue("Divest")
    DIVEST("Divest"),
    @XmlEnumValue("Acquire")
    ACQUIRE("Acquire"),
    @XmlEnumValue("DivestAcquire")
    DIVEST_ACQUIRE("DivestAcquire"),
    @XmlEnumValue("NoTransfer")
    NO_TRANSFER("NoTransfer");
    private final java.lang.String value;

    OwnershipEnumerations(java.lang.String v) {
        value = v;
    }

    public java.lang.String value() {
        return value;
    }

    public static OwnershipEnumerations fromValue(java.lang.String v) {
        for (OwnershipEnumerations c: OwnershipEnumerations.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

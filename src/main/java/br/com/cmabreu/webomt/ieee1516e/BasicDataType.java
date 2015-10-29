//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.06 at 09:06:12 AM BRT 
//


package br.com.cmabreu.webomt.ieee1516e;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * <p>Java class for basicDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="basicDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://standards.ieee.org/IEEE1516-2010}IdentifierType"/>
 *         &lt;element name="size" type="{http://standards.ieee.org/IEEE1516-2010}Size" minOccurs="0"/>
 *         &lt;element name="interpretation" type="{http://standards.ieee.org/IEEE1516-2010}String" minOccurs="0"/>
 *         &lt;element name="endian" type="{http://standards.ieee.org/IEEE1516-2010}endianType" minOccurs="0"/>
 *         &lt;element name="encoding" type="{http://standards.ieee.org/IEEE1516-2010}String" minOccurs="0"/>
 *         &lt;any namespace='##other' minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://standards.ieee.org/IEEE1516-2010}commonAttributes"/>
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basicDataType", propOrder = {
    "name",
    "size",
    "interpretation",
    "endian",
    "encoding",
    "any"
})
@XmlSeeAlso({
    br.com.cmabreu.webomt.ieee1516e.BasicDataRepresentationsType.BasicData.class
})
public class BasicDataType {

    @XmlElement(required = true)
    protected IdentifierType name;
    protected Size size;
    protected br.com.cmabreu.webomt.ieee1516e.String interpretation;
    protected EndianType endian;
    protected br.com.cmabreu.webomt.ieee1516e.String encoding;
    @XmlAnyElement(lax = true)
    protected Object any;
    @XmlAttribute(name = "idtag")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String idtag;
    @XmlAnyAttribute
    private Map<QName, java.lang.String> otherAttributes = new HashMap<QName, java.lang.String>();

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     */
    public void setName(IdentifierType value) {
        this.name = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link Size }
     *     
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link Size }
     *     
     */
    public void setSize(Size value) {
        this.size = value;
    }

    /**
     * Gets the value of the interpretation property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.cmabreu.webomt.ieee1516e.String }
     *     
     */
    public br.com.cmabreu.webomt.ieee1516e.String getInterpretation() {
        return interpretation;
    }

    /**
     * Sets the value of the interpretation property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.cmabreu.webomt.ieee1516e.String }
     *     
     */
    public void setInterpretation(br.com.cmabreu.webomt.ieee1516e.String value) {
        this.interpretation = value;
    }

    /**
     * Gets the value of the endian property.
     * 
     * @return
     *     possible object is
     *     {@link EndianType }
     *     
     */
    public EndianType getEndian() {
        return endian;
    }

    /**
     * Sets the value of the endian property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndianType }
     *     
     */
    public void setEndian(EndianType value) {
        this.endian = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link br.com.cmabreu.webomt.ieee1516e.String }
     *     
     */
    public br.com.cmabreu.webomt.ieee1516e.String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link br.com.cmabreu.webomt.ieee1516e.String }
     *     
     */
    public void setEncoding(br.com.cmabreu.webomt.ieee1516e.String value) {
        this.encoding = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

    /**
     * Gets the value of the idtag property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getIdtag() {
        return idtag;
    }

    /**
     * Sets the value of the idtag property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setIdtag(java.lang.String value) {
        this.idtag = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, java.lang.String> getOtherAttributes() {
        return otherAttributes;
    }

}

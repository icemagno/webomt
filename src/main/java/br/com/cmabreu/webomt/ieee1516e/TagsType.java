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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * <p>Java class for tagsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tagsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updateReflectTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
 *         &lt;element name="sendReceiveTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
 *         &lt;element name="deleteRemoveTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
 *         &lt;element name="divestitureRequestTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
 *         &lt;element name="divestitureCompletionTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
 *         &lt;element name="acquisitionRequestTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
 *         &lt;element name="requestUpdateTag" type="{http://standards.ieee.org/IEEE1516-2010}tagType" minOccurs="0"/>
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
@XmlType(name = "tagsType", propOrder = {
    "updateReflectTag",
    "sendReceiveTag",
    "deleteRemoveTag",
    "divestitureRequestTag",
    "divestitureCompletionTag",
    "acquisitionRequestTag",
    "requestUpdateTag",
    "any"
})
public class TagsType {

    protected TagType updateReflectTag;
    protected TagType sendReceiveTag;
    protected TagType deleteRemoveTag;
    protected TagType divestitureRequestTag;
    protected TagType divestitureCompletionTag;
    protected TagType acquisitionRequestTag;
    protected TagType requestUpdateTag;
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
     * Gets the value of the updateReflectTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getUpdateReflectTag() {
        return updateReflectTag;
    }

    /**
     * Sets the value of the updateReflectTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setUpdateReflectTag(TagType value) {
        this.updateReflectTag = value;
    }

    /**
     * Gets the value of the sendReceiveTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getSendReceiveTag() {
        return sendReceiveTag;
    }

    /**
     * Sets the value of the sendReceiveTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setSendReceiveTag(TagType value) {
        this.sendReceiveTag = value;
    }

    /**
     * Gets the value of the deleteRemoveTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getDeleteRemoveTag() {
        return deleteRemoveTag;
    }

    /**
     * Sets the value of the deleteRemoveTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setDeleteRemoveTag(TagType value) {
        this.deleteRemoveTag = value;
    }

    /**
     * Gets the value of the divestitureRequestTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getDivestitureRequestTag() {
        return divestitureRequestTag;
    }

    /**
     * Sets the value of the divestitureRequestTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setDivestitureRequestTag(TagType value) {
        this.divestitureRequestTag = value;
    }

    /**
     * Gets the value of the divestitureCompletionTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getDivestitureCompletionTag() {
        return divestitureCompletionTag;
    }

    /**
     * Sets the value of the divestitureCompletionTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setDivestitureCompletionTag(TagType value) {
        this.divestitureCompletionTag = value;
    }

    /**
     * Gets the value of the acquisitionRequestTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getAcquisitionRequestTag() {
        return acquisitionRequestTag;
    }

    /**
     * Sets the value of the acquisitionRequestTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setAcquisitionRequestTag(TagType value) {
        this.acquisitionRequestTag = value;
    }

    /**
     * Gets the value of the requestUpdateTag property.
     * 
     * @return
     *     possible object is
     *     {@link TagType }
     *     
     */
    public TagType getRequestUpdateTag() {
        return requestUpdateTag;
    }

    /**
     * Sets the value of the requestUpdateTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagType }
     *     
     */
    public void setRequestUpdateTag(TagType value) {
        this.requestUpdateTag = value;
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
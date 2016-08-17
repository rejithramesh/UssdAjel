
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HasTrialProductResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="trailProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trailStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="trailEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="responseMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hasTrialProductResult",
    "trailProductName",
    "trailStartDate",
    "trailEndDate",
    "responseCode",
    "responseMessage"
})
@XmlRootElement(name = "HasTrialProductResponse")
public class HasTrialProductResponse {

    @XmlElement(name = "HasTrialProductResult")
    protected boolean hasTrialProductResult;
    protected String trailProductName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar trailStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar trailEndDate;
    protected int responseCode;
    protected String responseMessage;

    /**
     * Gets the value of the hasTrialProductResult property.
     * 
     */
    public boolean isHasTrialProductResult() {
        return hasTrialProductResult;
    }

    /**
     * Sets the value of the hasTrialProductResult property.
     * 
     */
    public void setHasTrialProductResult(boolean value) {
        this.hasTrialProductResult = value;
    }

    /**
     * Gets the value of the trailProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrailProductName() {
        return trailProductName;
    }

    /**
     * Sets the value of the trailProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailProductName(String value) {
        this.trailProductName = value;
    }

    /**
     * Gets the value of the trailStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTrailStartDate() {
        return trailStartDate;
    }

    /**
     * Sets the value of the trailStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTrailStartDate(XMLGregorianCalendar value) {
        this.trailStartDate = value;
    }

    /**
     * Gets the value of the trailEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTrailEndDate() {
        return trailEndDate;
    }

    /**
     * Sets the value of the trailEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTrailEndDate(XMLGregorianCalendar value) {
        this.trailEndDate = value;
    }

    /**
     * Gets the value of the responseCode property.
     * 
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     */
    public void setResponseCode(int value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the responseMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Sets the value of the responseMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

}


package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for USSDOffer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="USSDOffer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OfferID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OfferCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OfferDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "USSDOffer", propOrder = {
    "offerID",
    "offerCode",
    "offerDisplayName"
})
public class USSDOffer {

    @XmlElement(name = "OfferID")
    protected int offerID;
    @XmlElement(name = "OfferCode")
    protected String offerCode;
    @XmlElement(name = "OfferDisplayName")
    protected String offerDisplayName;

    /**
     * Gets the value of the offerID property.
     * 
     */
    public int getOfferID() {
        return offerID;
    }

    /**
     * Sets the value of the offerID property.
     * 
     */
    public void setOfferID(int value) {
        this.offerID = value;
    }

    /**
     * Gets the value of the offerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferCode() {
        return offerCode;
    }

    /**
     * Sets the value of the offerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferCode(String value) {
        this.offerCode = value;
    }

    /**
     * Gets the value of the offerDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferDisplayName() {
        return offerDisplayName;
    }

    /**
     * Sets the value of the offerDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferDisplayName(String value) {
        this.offerDisplayName = value;
    }

}

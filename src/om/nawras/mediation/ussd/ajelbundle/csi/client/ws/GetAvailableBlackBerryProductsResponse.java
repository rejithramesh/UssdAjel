
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="GetAvailableBlackBerryProductsResult" type="{http://nawras.om/2011/2}ArrayOfUSSDProduct" minOccurs="0"/>
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
    "getAvailableBlackBerryProductsResult",
    "responseCode",
    "responseMessage"
})
@XmlRootElement(name = "GetAvailableBlackBerryProductsResponse")
public class GetAvailableBlackBerryProductsResponse {

    @XmlElement(name = "GetAvailableBlackBerryProductsResult")
    protected ArrayOfUSSDProduct getAvailableBlackBerryProductsResult;
    protected int responseCode;
    protected String responseMessage;

    /**
     * Gets the value of the getAvailableBlackBerryProductsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUSSDProduct }
     *     
     */
    public ArrayOfUSSDProduct getGetAvailableBlackBerryProductsResult() {
        return getAvailableBlackBerryProductsResult;
    }

    /**
     * Sets the value of the getAvailableBlackBerryProductsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUSSDProduct }
     *     
     */
    public void setGetAvailableBlackBerryProductsResult(ArrayOfUSSDProduct value) {
        this.getAvailableBlackBerryProductsResult = value;
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

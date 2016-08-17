
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="eventSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ussdlanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notificationlanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "eventSource",
    "ussdlanguage",
    "notificationlanguage",
    "application",
    "password"
})
@XmlRootElement(name = "ChangePrepaidPreferredLanguage")
public class ChangePrepaidPreferredLanguage {

    protected String eventSource;
    protected String ussdlanguage;
    protected String notificationlanguage;
    protected String application;
    protected String password;

    /**
     * Gets the value of the eventSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventSource() {
        return eventSource;
    }

    /**
     * Sets the value of the eventSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventSource(String value) {
        this.eventSource = value;
    }

    /**
     * Gets the value of the ussdlanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUssdlanguage() {
        return ussdlanguage;
    }

    /**
     * Sets the value of the ussdlanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUssdlanguage(String value) {
        this.ussdlanguage = value;
    }

    /**
     * Gets the value of the notificationlanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationlanguage() {
        return notificationlanguage;
    }

    /**
     * Sets the value of the notificationlanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationlanguage(String value) {
        this.notificationlanguage = value;
    }

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}

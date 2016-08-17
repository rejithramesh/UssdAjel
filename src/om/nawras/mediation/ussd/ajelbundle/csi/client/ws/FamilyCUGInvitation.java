
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for FamilyCUGInvitation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FamilyCUGInvitation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://nawras.om/2011/2}BusinessBaseOfFamilyCUGInvitation">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SentBySubscriptionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SentByMSISDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SentToSubscriptionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SentToMSISDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SentOn" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusUpdatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SysRevokeReasonID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SysRevokedDueTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FamilyCUGInvitation", propOrder = {
    "id",
    "sentBySubscriptionID",
    "sentByMSISDN",
    "sentToSubscriptionID",
    "sentToMSISDN",
    "sentOn",
    "status",
    "statusUpdatedOn",
    "sysRevokeReasonID",
    "sysRevokedDueTo",
    "notes"
})
public class FamilyCUGInvitation
    extends BusinessBaseOfFamilyCUGInvitation
{

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "SentBySubscriptionID")
    protected int sentBySubscriptionID;
    @XmlElement(name = "SentByMSISDN")
    protected String sentByMSISDN;
    @XmlElement(name = "SentToSubscriptionID")
    protected int sentToSubscriptionID;
    @XmlElement(name = "SentToMSISDN")
    protected String sentToMSISDN;
    @XmlElement(name = "SentOn", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sentOn;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "StatusUpdatedOn", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusUpdatedOn;
    @XmlElement(name = "SysRevokeReasonID")
    protected int sysRevokeReasonID;
    @XmlElement(name = "SysRevokedDueTo")
    protected String sysRevokedDueTo;
    @XmlElement(name = "Notes")
    protected String notes;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the sentBySubscriptionID property.
     * 
     */
    public int getSentBySubscriptionID() {
        return sentBySubscriptionID;
    }

    /**
     * Sets the value of the sentBySubscriptionID property.
     * 
     */
    public void setSentBySubscriptionID(int value) {
        this.sentBySubscriptionID = value;
    }

    /**
     * Gets the value of the sentByMSISDN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentByMSISDN() {
        return sentByMSISDN;
    }

    /**
     * Sets the value of the sentByMSISDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentByMSISDN(String value) {
        this.sentByMSISDN = value;
    }

    /**
     * Gets the value of the sentToSubscriptionID property.
     * 
     */
    public int getSentToSubscriptionID() {
        return sentToSubscriptionID;
    }

    /**
     * Sets the value of the sentToSubscriptionID property.
     * 
     */
    public void setSentToSubscriptionID(int value) {
        this.sentToSubscriptionID = value;
    }

    /**
     * Gets the value of the sentToMSISDN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSentToMSISDN() {
        return sentToMSISDN;
    }

    /**
     * Sets the value of the sentToMSISDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSentToMSISDN(String value) {
        this.sentToMSISDN = value;
    }

    /**
     * Gets the value of the sentOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSentOn() {
        return sentOn;
    }

    /**
     * Sets the value of the sentOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSentOn(XMLGregorianCalendar value) {
        this.sentOn = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusUpdatedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusUpdatedOn() {
        return statusUpdatedOn;
    }

    /**
     * Sets the value of the statusUpdatedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusUpdatedOn(XMLGregorianCalendar value) {
        this.statusUpdatedOn = value;
    }

    /**
     * Gets the value of the sysRevokeReasonID property.
     * 
     */
    public int getSysRevokeReasonID() {
        return sysRevokeReasonID;
    }

    /**
     * Sets the value of the sysRevokeReasonID property.
     * 
     */
    public void setSysRevokeReasonID(int value) {
        this.sysRevokeReasonID = value;
    }

    /**
     * Gets the value of the sysRevokedDueTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysRevokedDueTo() {
        return sysRevokedDueTo;
    }

    /**
     * Sets the value of the sysRevokedDueTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysRevokedDueTo(String value) {
        this.sysRevokedDueTo = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

}


package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AddOnBalanceDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddOnBalanceDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://nawras.om/2011/2}BusinessBaseOfAddOnBalanceDetails">
 *       &lt;sequence>
 *         &lt;element name="Msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TariffId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProductSequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RemainingVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TotalVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ConsumedVolume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddOnBalanceDetails", propOrder = {
    "msisdn",
    "productId",
    "tariffId",
    "productSequence",
    "startDate",
    "endDate",
    "remainingVolume",
    "totalVolume",
    "consumedVolume"
})
public class AddOnBalanceDetails
    extends BusinessBaseOfAddOnBalanceDetails
{

    @XmlElement(name = "Msisdn")
    protected String msisdn;
    @XmlElement(name = "ProductId")
    protected int productId;
    @XmlElement(name = "TariffId")
    protected int tariffId;
    @XmlElement(name = "ProductSequence")
    protected int productSequence;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "RemainingVolume", required = true)
    protected BigDecimal remainingVolume;
    @XmlElement(name = "TotalVolume", required = true)
    protected BigDecimal totalVolume;
    @XmlElement(name = "ConsumedVolume", required = true)
    protected BigDecimal consumedVolume;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     */
    public void setProductId(int value) {
        this.productId = value;
    }

    /**
     * Gets the value of the tariffId property.
     * 
     */
    public int getTariffId() {
        return tariffId;
    }

    /**
     * Sets the value of the tariffId property.
     * 
     */
    public void setTariffId(int value) {
        this.tariffId = value;
    }

    /**
     * Gets the value of the productSequence property.
     * 
     */
    public int getProductSequence() {
        return productSequence;
    }

    /**
     * Sets the value of the productSequence property.
     * 
     */
    public void setProductSequence(int value) {
        this.productSequence = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the remainingVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRemainingVolume() {
        return remainingVolume;
    }

    /**
     * Sets the value of the remainingVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRemainingVolume(BigDecimal value) {
        this.remainingVolume = value;
    }

    /**
     * Gets the value of the totalVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    /**
     * Sets the value of the totalVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalVolume(BigDecimal value) {
        this.totalVolume = value;
    }

    /**
     * Gets the value of the consumedVolume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getConsumedVolume() {
        return consumedVolume;
    }

    /**
     * Sets the value of the consumedVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setConsumedVolume(BigDecimal value) {
        this.consumedVolume = value;
    }

}

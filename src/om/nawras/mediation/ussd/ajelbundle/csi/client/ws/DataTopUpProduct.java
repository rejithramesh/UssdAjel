
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataTopUpProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataTopUpProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TopUpProductID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TopUpTariffID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TopUpName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OTCCharge" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DataProductID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DataTariffID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataTopUpProduct", propOrder = {
    "topUpProductID",
    "topUpTariffID",
    "topUpName",
    "otcCharge",
    "dataProductID",
    "dataTariffID"
})
public class DataTopUpProduct {

    @XmlElement(name = "TopUpProductID")
    protected int topUpProductID;
    @XmlElement(name = "TopUpTariffID")
    protected int topUpTariffID;
    @XmlElement(name = "TopUpName")
    protected String topUpName;
    @XmlElement(name = "OTCCharge", required = true)
    protected BigDecimal otcCharge;
    @XmlElement(name = "DataProductID")
    protected int dataProductID;
    @XmlElement(name = "DataTariffID")
    protected int dataTariffID;

    /**
     * Gets the value of the topUpProductID property.
     * 
     */
    public int getTopUpProductID() {
        return topUpProductID;
    }

    /**
     * Sets the value of the topUpProductID property.
     * 
     */
    public void setTopUpProductID(int value) {
        this.topUpProductID = value;
    }

    /**
     * Gets the value of the topUpTariffID property.
     * 
     */
    public int getTopUpTariffID() {
        return topUpTariffID;
    }

    /**
     * Sets the value of the topUpTariffID property.
     * 
     */
    public void setTopUpTariffID(int value) {
        this.topUpTariffID = value;
    }

    /**
     * Gets the value of the topUpName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopUpName() {
        return topUpName;
    }

    /**
     * Sets the value of the topUpName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopUpName(String value) {
        this.topUpName = value;
    }

    /**
     * Gets the value of the otcCharge property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOTCCharge() {
        return otcCharge;
    }

    /**
     * Sets the value of the otcCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOTCCharge(BigDecimal value) {
        this.otcCharge = value;
    }

    /**
     * Gets the value of the dataProductID property.
     * 
     */
    public int getDataProductID() {
        return dataProductID;
    }

    /**
     * Sets the value of the dataProductID property.
     * 
     */
    public void setDataProductID(int value) {
        this.dataProductID = value;
    }

    /**
     * Gets the value of the dataTariffID property.
     * 
     */
    public int getDataTariffID() {
        return dataTariffID;
    }

    /**
     * Sets the value of the dataTariffID property.
     * 
     */
    public void setDataTariffID(int value) {
        this.dataTariffID = value;
    }

}

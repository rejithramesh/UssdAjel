
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://nawras.om/2011/2}UndoableBase">
 *       &lt;sequence>
 *         &lt;element name="BrokenRulesCollection" type="{http://nawras.om/2011/2}ArrayOfBrokenRule" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessBase", propOrder = {
    "brokenRulesCollection"
})
@XmlSeeAlso({
    BusinessBaseOfFamilyCUGInvitation.class,
    BusinessBaseOfAddOnBalanceDetails.class
})
public abstract class BusinessBase
    extends UndoableBase
{

    @XmlElement(name = "BrokenRulesCollection")
    protected ArrayOfBrokenRule brokenRulesCollection;

    /**
     * Gets the value of the brokenRulesCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBrokenRule }
     *     
     */
    public ArrayOfBrokenRule getBrokenRulesCollection() {
        return brokenRulesCollection;
    }

    /**
     * Sets the value of the brokenRulesCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBrokenRule }
     *     
     */
    public void setBrokenRulesCollection(ArrayOfBrokenRule value) {
        this.brokenRulesCollection = value;
    }

}

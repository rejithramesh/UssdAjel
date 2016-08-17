
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBrokenRule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBrokenRule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrokenRule" type="{http://nawras.om/2011/2}BrokenRule" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBrokenRule", propOrder = {
    "brokenRule"
})
public class ArrayOfBrokenRule {

    @XmlElement(name = "BrokenRule", nillable = true)
    protected List<BrokenRule> brokenRule;

    /**
     * Gets the value of the brokenRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brokenRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrokenRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BrokenRule }
     * 
     * 
     */
    public List<BrokenRule> getBrokenRule() {
        if (brokenRule == null) {
            brokenRule = new ArrayList<BrokenRule>();
        }
        return this.brokenRule;
    }

}

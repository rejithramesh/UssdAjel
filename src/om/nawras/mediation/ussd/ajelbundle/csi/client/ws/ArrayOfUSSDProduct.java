
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfUSSDProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfUSSDProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USSDProduct" type="{http://nawras.om/2011/2}USSDProduct" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfUSSDProduct", propOrder = {
    "ussdProduct"
})
public class ArrayOfUSSDProduct {

    @XmlElement(name = "USSDProduct", nillable = true)
    protected List<USSDProduct> ussdProduct;

    /**
     * Gets the value of the ussdProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ussdProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUSSDProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link USSDProduct }
     * 
     * 
     */
    public List<USSDProduct> getUSSDProduct() {
        if (ussdProduct == null) {
            ussdProduct = new ArrayList<USSDProduct>();
        }
        return this.ussdProduct;
    }

}

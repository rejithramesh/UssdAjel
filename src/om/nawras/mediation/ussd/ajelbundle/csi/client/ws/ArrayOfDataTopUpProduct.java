
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDataTopUpProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDataTopUpProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataTopUpProduct" type="{http://nawras.om/2011/2}DataTopUpProduct" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDataTopUpProduct", propOrder = {
    "dataTopUpProduct"
})
public class ArrayOfDataTopUpProduct {

    @XmlElement(name = "DataTopUpProduct", nillable = true)
    protected List<DataTopUpProduct> dataTopUpProduct;

    /**
     * Gets the value of the dataTopUpProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataTopUpProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataTopUpProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataTopUpProduct }
     * 
     * 
     */
    public List<DataTopUpProduct> getDataTopUpProduct() {
        if (dataTopUpProduct == null) {
            dataTopUpProduct = new ArrayList<DataTopUpProduct>();
        }
        return this.dataTopUpProduct;
    }

}

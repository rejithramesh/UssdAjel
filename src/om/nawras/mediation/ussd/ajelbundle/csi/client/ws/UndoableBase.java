
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UndoableBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UndoableBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://nawras.om/2011/2}BindableBase">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UndoableBase")
@XmlSeeAlso({
    BusinessBase.class
})
public abstract class UndoableBase
    extends BindableBase
{


}

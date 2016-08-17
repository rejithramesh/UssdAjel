
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VoucherTransactionAction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VoucherTransactionAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Commit"/>
 *     &lt;enumeration value="RollBack"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VoucherTransactionAction")
@XmlEnum
public enum VoucherTransactionAction {

    @XmlEnumValue("Commit")
    COMMIT("Commit"),
    @XmlEnumValue("RollBack")
    ROLL_BACK("RollBack");
    private final String value;

    VoucherTransactionAction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VoucherTransactionAction fromValue(String v) {
        for (VoucherTransactionAction c: VoucherTransactionAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

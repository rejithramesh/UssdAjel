
package om.nawras.mediation.ussd.ajelbundle.csi.client.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.7-b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PostpaidService", targetNamespace = "http://nawras.om/2011/2", wsdlLocation = "http://10.1.105.23/USSDservice_smsbalcheck/PostpaidService.asmx?wsdl")
public class PostpaidService
    extends Service
{

    private final static URL POSTPAIDSERVICE_WSDL_LOCATION;
    private final static WebServiceException POSTPAIDSERVICE_EXCEPTION;
    private final static QName POSTPAIDSERVICE_QNAME = new QName("http://nawras.om/2011/2", "PostpaidService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.1.105.23/USSDservice_smsbalcheck/PostpaidService.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        POSTPAIDSERVICE_WSDL_LOCATION = url;
        POSTPAIDSERVICE_EXCEPTION = e;
    }

    public PostpaidService() {
        super(__getWsdlLocation(), POSTPAIDSERVICE_QNAME);
    }


    public PostpaidService(URL wsdlLocation) {
        super(wsdlLocation, POSTPAIDSERVICE_QNAME);
    }


    public PostpaidService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }



    /**
     * 
     * @return
     *     returns PostpaidServiceSoap
     */
    @WebEndpoint(name = "PostpaidServiceSoap")
    public PostpaidServiceSoap getPostpaidServiceSoap() {
        return super.getPort(new QName("http://nawras.om/2011/2", "PostpaidServiceSoap"), PostpaidServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PostpaidServiceSoap
     */
    @WebEndpoint(name = "PostpaidServiceSoap")
    public PostpaidServiceSoap getPostpaidServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://nawras.om/2011/2", "PostpaidServiceSoap"), PostpaidServiceSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns PostpaidServiceSoap
     */
    @WebEndpoint(name = "PostpaidServiceSoap12")
    public PostpaidServiceSoap getPostpaidServiceSoap12() {
        return super.getPort(new QName("http://nawras.om/2011/2", "PostpaidServiceSoap12"), PostpaidServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PostpaidServiceSoap
     */
    @WebEndpoint(name = "PostpaidServiceSoap12")
    public PostpaidServiceSoap getPostpaidServiceSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://nawras.om/2011/2", "PostpaidServiceSoap12"), PostpaidServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (POSTPAIDSERVICE_EXCEPTION!= null) {
            throw POSTPAIDSERVICE_EXCEPTION;
        }
        return POSTPAIDSERVICE_WSDL_LOCATION;
    }

}

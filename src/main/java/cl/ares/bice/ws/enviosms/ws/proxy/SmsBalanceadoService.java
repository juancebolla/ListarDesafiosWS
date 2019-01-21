
package cl.ares.bice.ws.enviosms.ws.proxy;

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
 * JAX-WS RI 2.2.10-b140319.1121
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "SmsBalanceadoService", targetNamespace = "http://ws.sms.bice.cl/",
                  wsdlLocation =
                  "http://edusa:8011/PORTAL/SMS/smsBalanceado/#%7Bhttp%3A%2F%2Fws.sms.bice.cl%2F%7DSmsBalanceadoService?wsdl")
public class SmsBalanceadoService extends Service {

    private final static URL SMSBALANCEADOSERVICE_WSDL_LOCATION;
    private final static WebServiceException SMSBALANCEADOSERVICE_EXCEPTION;
    private final static QName SMSBALANCEADOSERVICE_QNAME = new QName("http://ws.sms.bice.cl/", "SmsBalanceadoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url =
                new URL("http://edusa:8011/PORTAL/SMS/smsBalanceado/#%7Bhttp%3A%2F%2Fws.sms.bice.cl%2F%7DSmsBalanceadoService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SMSBALANCEADOSERVICE_WSDL_LOCATION = url;
        SMSBALANCEADOSERVICE_EXCEPTION = e;
    }

    public SmsBalanceadoService() {
        super(__getWsdlLocation(), SMSBALANCEADOSERVICE_QNAME);
    }

    public SmsBalanceadoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SMSBALANCEADOSERVICE_QNAME);
        //super(__getWsdlLocation(), SMSBALANCEADOSERVICE_QNAME, features);
    }

    public SmsBalanceadoService(URL wsdlLocation) {
        super(wsdlLocation, SMSBALANCEADOSERVICE_QNAME);
    }

    public SmsBalanceadoService(URL wsdlLocation, WebServiceFeature... features) {
        super(__getWsdlLocation(), SMSBALANCEADOSERVICE_QNAME);
        //super(wsdlLocation, SMSBALANCEADOSERVICE_QNAME, features);
    }

    public SmsBalanceadoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SmsBalanceadoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(__getWsdlLocation(), SMSBALANCEADOSERVICE_QNAME);
        //super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SmsBalanceado
     */
    @WebEndpoint(name = "SmsBalanceadoPort")
    public SmsBalanceado getSmsBalanceadoPort() {
        return super.getPort(new QName("http://ws.sms.bice.cl/", "SmsBalanceadoPort"), SmsBalanceado.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SmsBalanceado
     */
    @WebEndpoint(name = "SmsBalanceadoPort")
    public SmsBalanceado getSmsBalanceadoPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.sms.bice.cl/", "SmsBalanceadoPort"), SmsBalanceado.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SMSBALANCEADOSERVICE_EXCEPTION != null) {
            throw SMSBALANCEADOSERVICE_EXCEPTION;
        }
        return SMSBALANCEADOSERVICE_WSDL_LOCATION;
    }

}
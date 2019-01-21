
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the cl.ares.bice.ws.enviosms.ws.types package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EnviaSmsRespTO_QNAME = new QName("http://ws.sms.bice.cl/", "EnviaSmsRespTO");
    private final static QName _RecargaBalanceoRespTO_QNAME =
        new QName("http://ws.sms.bice.cl/", "RecargaBalanceoRespTO");
    private final static QName _EstadoResp_QNAME = new QName("http://ws.sms.bice.cl/", "EstadoResp");
    private final static QName _EnviarSMSResponse_QNAME = new QName("http://ws.sms.bice.cl/", "enviarSMSResponse");
    private final static QName _RecargaBalanceoSMS_QNAME = new QName("http://ws.sms.bice.cl/", "recargaBalanceoSMS");
    private final static QName _EnviarSMS_QNAME = new QName("http://ws.sms.bice.cl/", "enviarSMS");
    private final static QName _RecargaBalanceoSMSResponse_QNAME =
        new QName("http://ws.sms.bice.cl/", "recargaBalanceoSMSResponse");
    private final static QName _EnviaSmsTO_QNAME = new QName("http://ws.sms.bice.cl/", "EnviaSmsTO");
    private final static QName _RecargaBalanceoTO_QNAME = new QName("http://ws.sms.bice.cl/", "RecargaBalanceoTO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.ares.bice.ws.enviosms.ws.types
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecargaBalanceoTO }
     *
     */
    public RecargaBalanceoTO createRecargaBalanceoTO() {
        return new RecargaBalanceoTO();
    }

    /**
     * Create an instance of {@link EnviaSmsTO }
     *
     */
    public EnviaSmsTO createEnviaSmsTO() {
        return new EnviaSmsTO();
    }

    /**
     * Create an instance of {@link RecargaBalanceoSMSResponse }
     *
     */
    public RecargaBalanceoSMSResponse createRecargaBalanceoSMSResponse() {
        return new RecargaBalanceoSMSResponse();
    }

    /**
     * Create an instance of {@link EnviarSMS }
     *
     */
    public EnviarSMS createEnviarSMS() {
        return new EnviarSMS();
    }

    /**
     * Create an instance of {@link RecargaBalanceoSMS }
     *
     */
    public RecargaBalanceoSMS createRecargaBalanceoSMS() {
        return new RecargaBalanceoSMS();
    }

    /**
     * Create an instance of {@link EnviarSMSResponse }
     *
     */
    public EnviarSMSResponse createEnviarSMSResponse() {
        return new EnviarSMSResponse();
    }

    /**
     * Create an instance of {@link EstadoResp }
     *
     */
    public EstadoResp createEstadoResp() {
        return new EstadoResp();
    }

    /**
     * Create an instance of {@link RecargaBalanceoRespTO }
     *
     */
    public RecargaBalanceoRespTO createRecargaBalanceoRespTO() {
        return new RecargaBalanceoRespTO();
    }

    /**
     * Create an instance of {@link EnviaSmsRespTO }
     *
     */
    public EnviaSmsRespTO createEnviaSmsRespTO() {
        return new EnviaSmsRespTO();
    }

    /**
     * Create an instance of {@link OperadoresCursorRespTO }
     *
     */
    public OperadoresCursorRespTO createOperadoresCursorRespTO() {
        return new OperadoresCursorRespTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviaSmsRespTO }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "EnviaSmsRespTO")
    public JAXBElement<EnviaSmsRespTO> createEnviaSmsRespTO(EnviaSmsRespTO value) {
        return new JAXBElement<EnviaSmsRespTO>(_EnviaSmsRespTO_QNAME, EnviaSmsRespTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecargaBalanceoRespTO }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "RecargaBalanceoRespTO")
    public JAXBElement<RecargaBalanceoRespTO> createRecargaBalanceoRespTO(RecargaBalanceoRespTO value) {
        return new JAXBElement<RecargaBalanceoRespTO>(_RecargaBalanceoRespTO_QNAME, RecargaBalanceoRespTO.class, null,
                                                      value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstadoResp }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "EstadoResp")
    public JAXBElement<EstadoResp> createEstadoResp(EstadoResp value) {
        return new JAXBElement<EstadoResp>(_EstadoResp_QNAME, EstadoResp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarSMSResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "enviarSMSResponse")
    public JAXBElement<EnviarSMSResponse> createEnviarSMSResponse(EnviarSMSResponse value) {
        return new JAXBElement<EnviarSMSResponse>(_EnviarSMSResponse_QNAME, EnviarSMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecargaBalanceoSMS }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "recargaBalanceoSMS")
    public JAXBElement<RecargaBalanceoSMS> createRecargaBalanceoSMS(RecargaBalanceoSMS value) {
        return new JAXBElement<RecargaBalanceoSMS>(_RecargaBalanceoSMS_QNAME, RecargaBalanceoSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarSMS }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "enviarSMS")
    public JAXBElement<EnviarSMS> createEnviarSMS(EnviarSMS value) {
        return new JAXBElement<EnviarSMS>(_EnviarSMS_QNAME, EnviarSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecargaBalanceoSMSResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "recargaBalanceoSMSResponse")
    public JAXBElement<RecargaBalanceoSMSResponse> createRecargaBalanceoSMSResponse(RecargaBalanceoSMSResponse value) {
        return new JAXBElement<RecargaBalanceoSMSResponse>(_RecargaBalanceoSMSResponse_QNAME,
                                                           RecargaBalanceoSMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviaSmsTO }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "EnviaSmsTO")
    public JAXBElement<EnviaSmsTO> createEnviaSmsTO(EnviaSmsTO value) {
        return new JAXBElement<EnviaSmsTO>(_EnviaSmsTO_QNAME, EnviaSmsTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecargaBalanceoTO }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://ws.sms.bice.cl/", name = "RecargaBalanceoTO")
    public JAXBElement<RecargaBalanceoTO> createRecargaBalanceoTO(RecargaBalanceoTO value) {
        return new JAXBElement<RecargaBalanceoTO>(_RecargaBalanceoTO_QNAME, RecargaBalanceoTO.class, null, value);
    }

}

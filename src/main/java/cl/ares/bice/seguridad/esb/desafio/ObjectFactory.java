
package cl.ares.bice.seguridad.esb.desafio;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the cl.bice.seguridad.esb.desafio package.
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

    private final static QName _CrearDesafioResponse_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "CrearDesafioResponse");
    private final static QName _AutentificarRequest_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "AutentificarRequest");
    private final static QName _AutentificarResponse_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "AutentificarResponse");
    private final static QName _ListarDesafiosRequest_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "ListarDesafiosRequest");
    private final static QName _ListarDesafiosResponse_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "ListarDesafiosResponse");
    private final static QName _CrearDesafioRequest_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "CrearDesafioRequest");
    private final static QName _SolicitudDesafioRequest_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "SolicitudDesafioRequest");
    private final static QName _SolicitudDesafioResponse_QNAME =
        new QName("http://www.bice.cl/seguridad/esb/desafio", "SolicitudDesafioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.bice.seguridad.esb.desafio
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RespuestaDesafioType }
     *
     */
    public RespuestaDesafioType createRespuestaDesafioType() {
        return new RespuestaDesafioType();
    }

    /**
     * Create an instance of {@link AutentificarRequestType }
     *
     */
    public AutentificarRequestType createAutentificarRequestType() {
        return new AutentificarRequestType();
    }

    /**
     * Create an instance of {@link ListarDesafiosRequestType }
     *
     */
    public ListarDesafiosRequestType createListarDesafiosRequestType() {
        return new ListarDesafiosRequestType();
    }

    /**
     * Create an instance of {@link AutentificarResponseType }
     *
     */
    public AutentificarResponseType createAutentificarResponseType() {
        return new AutentificarResponseType();
    }

    /**
     * Create an instance of {@link SolicitudDesafioType }
     *
     */
    public SolicitudDesafioType createSolicitudDesafioType() {
        return new SolicitudDesafioType();
    }

    /**
     * Create an instance of {@link CrearDesafioRequestType }
     *
     */
    public CrearDesafioRequestType createCrearDesafioRequestType() {
        return new CrearDesafioRequestType();
    }

    /**
     * Create an instance of {@link ListarDesafiosResponseType }
     *
     */
    public ListarDesafiosResponseType createListarDesafiosResponseType() {
        return new ListarDesafiosResponseType();
    }

    /**
     * Create an instance of {@link DesafiosType }
     *
     */
    public DesafiosType createDesafiosType() {
        return new DesafiosType();
    }

    /**
     * Create an instance of {@link DesafioType }
     *
     */
    public DesafioType createDesafioType() {
        return new DesafioType();
    }

    /**
     * Create an instance of {@link CoordenadaType }
     *
     */
    public CoordenadaType createCoordenadaType() {
        return new CoordenadaType();
    }

    /**
     * Create an instance of {@link EstadoType }
     *
     */
    public EstadoType createEstadoType() {
        return new EstadoType();
    }

    /**
     * Create an instance of {@link CoordenadasType }
     *
     */
    public CoordenadasType createCoordenadasType() {
        return new CoordenadasType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RespuestaDesafioType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "CrearDesafioResponse")
    public JAXBElement<RespuestaDesafioType> createCrearDesafioResponse(RespuestaDesafioType value) {
        return new JAXBElement<RespuestaDesafioType>(_CrearDesafioResponse_QNAME, RespuestaDesafioType.class, null,
                                                     value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutentificarRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "AutentificarRequest")
    public JAXBElement<AutentificarRequestType> createAutentificarRequest(AutentificarRequestType value) {
        return new JAXBElement<AutentificarRequestType>(_AutentificarRequest_QNAME, AutentificarRequestType.class, null,
                                                        value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutentificarResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "AutentificarResponse")
    public JAXBElement<AutentificarResponseType> createAutentificarResponse(AutentificarResponseType value) {
        return new JAXBElement<AutentificarResponseType>(_AutentificarResponse_QNAME, AutentificarResponseType.class,
                                                         null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarDesafiosRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "ListarDesafiosRequest")
    public JAXBElement<ListarDesafiosRequestType> createListarDesafiosRequest(ListarDesafiosRequestType value) {
        return new JAXBElement<ListarDesafiosRequestType>(_ListarDesafiosRequest_QNAME, ListarDesafiosRequestType.class,
                                                          null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarDesafiosResponseType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "ListarDesafiosResponse")
    public JAXBElement<ListarDesafiosResponseType> createListarDesafiosResponse(ListarDesafiosResponseType value) {
        return new JAXBElement<ListarDesafiosResponseType>(_ListarDesafiosResponse_QNAME,
                                                           ListarDesafiosResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearDesafioRequestType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "CrearDesafioRequest")
    public JAXBElement<CrearDesafioRequestType> createCrearDesafioRequest(CrearDesafioRequestType value) {
        return new JAXBElement<CrearDesafioRequestType>(_CrearDesafioRequest_QNAME, CrearDesafioRequestType.class, null,
                                                        value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitudDesafioType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "SolicitudDesafioRequest")
    public JAXBElement<SolicitudDesafioType> createSolicitudDesafioRequest(SolicitudDesafioType value) {
        return new JAXBElement<SolicitudDesafioType>(_SolicitudDesafioRequest_QNAME, SolicitudDesafioType.class, null,
                                                     value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RespuestaDesafioType }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://www.bice.cl/seguridad/esb/desafio", name = "SolicitudDesafioResponse")
    public JAXBElement<RespuestaDesafioType> createSolicitudDesafioResponse(RespuestaDesafioType value) {
        return new JAXBElement<RespuestaDesafioType>(_SolicitudDesafioResponse_QNAME, RespuestaDesafioType.class, null,
                                                     value);
    }

}

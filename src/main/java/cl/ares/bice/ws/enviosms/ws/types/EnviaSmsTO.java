
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enviaSmsTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="enviaSmsTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canalOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hostName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rutCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCelularDestino" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviaSmsTO", propOrder = {
         "canalOrigen", "hostName", "rutCliente", "mensaje", "numeroCelularDestino" })
public class EnviaSmsTO {

    @XmlElement(required = true)
    protected String canalOrigen;
    @XmlElement(required = true)
    protected String hostName;
    @XmlElement(required = true)
    protected String rutCliente;
    @XmlElement(required = true)
    protected String mensaje;
    protected long numeroCelularDestino;

    /**
     * Gets the value of the canalOrigen property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCanalOrigen() {
        return canalOrigen;
    }

    /**
     * Sets the value of the canalOrigen property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCanalOrigen(String value) {
        this.canalOrigen = value;
    }

    /**
     * Gets the value of the hostName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets the value of the hostName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHostName(String value) {
        this.hostName = value;
    }

    /**
     * Gets the value of the rutCliente property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRutCliente() {
        return rutCliente;
    }

    /**
     * Sets the value of the rutCliente property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRutCliente(String value) {
        this.rutCliente = value;
    }

    /**
     * Gets the value of the mensaje property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the numeroCelularDestino property.
     *
     */
    public long getNumeroCelularDestino() {
        return numeroCelularDestino;
    }

    /**
     * Sets the value of the numeroCelularDestino property.
     *
     */
    public void setNumeroCelularDestino(long value) {
        this.numeroCelularDestino = value;
    }

}

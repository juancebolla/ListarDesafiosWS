
package cl.ares.bice.seguridad.esb.desafio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SolicitudDesafioType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SolicitudDesafioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UsuarioId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoDesafio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudDesafioType", propOrder = { "usuarioId", "tipoCliente", "ipCliente", "tipoDesafio" })
public class SolicitudDesafioType {

    @XmlElement(name = "UsuarioId", required = true)
    protected String usuarioId;
    @XmlElement(name = "TipoCliente", required = true)
    protected String tipoCliente;
    @XmlElement(name = "IPCliente", required = true)
    protected String ipCliente;
    @XmlElement(name = "TipoDesafio", required = true)
    protected String tipoDesafio;

    /**
     * Gets the value of the usuarioId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUsuarioId() {
        return usuarioId;
    }

    /**
     * Sets the value of the usuarioId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsuarioId(String value) {
        this.usuarioId = value;
    }

    /**
     * Gets the value of the tipoCliente property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Sets the value of the tipoCliente property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoCliente(String value) {
        this.tipoCliente = value;
    }

    /**
     * Gets the value of the ipCliente property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIPCliente() {
        return ipCliente;
    }

    /**
     * Sets the value of the ipCliente property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIPCliente(String value) {
        this.ipCliente = value;
    }

    /**
     * Gets the value of the tipoDesafio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoDesafio() {
        return tipoDesafio;
    }

    /**
     * Sets the value of the tipoDesafio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoDesafio(String value) {
        this.tipoDesafio = value;
    }

}

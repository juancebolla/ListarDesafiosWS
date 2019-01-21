
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoResp complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="estadoResp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glsEstado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estadoResp", propOrder = { "codEstado", "glsEstado" })
public class EstadoResp {

    protected String codEstado;
    protected String glsEstado;

    /**
     * Gets the value of the codEstado property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodEstado() {
        return codEstado;
    }

    /**
     * Sets the value of the codEstado property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodEstado(String value) {
        this.codEstado = value;
    }

    /**
     * Gets the value of the glsEstado property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGlsEstado() {
        return glsEstado;
    }

    /**
     * Sets the value of the glsEstado property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGlsEstado(String value) {
        this.glsEstado = value;
    }

}

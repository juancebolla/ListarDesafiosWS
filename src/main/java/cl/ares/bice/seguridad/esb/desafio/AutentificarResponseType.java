
package cl.ares.bice.seguridad.esb.desafio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AutentificarResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AutentificarResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Estado" type="{http://www.bice.cl/seguridad/esb/desafio}EstadoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutentificarResponseType", propOrder = { "estado" })
public class AutentificarResponseType {

    @XmlElement(name = "Estado", required = true)
    protected EstadoType estado;

    /**
     * Gets the value of the estado property.
     *
     * @return
     *     possible object is
     *     {@link EstadoType }
     *
     */
    public EstadoType getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     *
     * @param value
     *     allowed object is
     *     {@link EstadoType }
     *
     */
    public void setEstado(EstadoType value) {
        this.estado = value;
    }

}

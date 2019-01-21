package cl.ares.bice.seguridad.esb.desafio;

import cl.ares.bice.ws.servicio.EstadoType;
import cl.ares.bice.ws.servicio.DesafiosType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListarDesafiosResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ListarDesafiosResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Estado" type="{http://www.bice.cl/seguridad/esb/desafio}EstadoType"/>
 *         &lt;element name="Desafios" type="{http://www.bice.cl/seguridad/esb/desafio}DesafiosType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListarDesafiosResponseType", propOrder = { "estado", "desafios" })
public class ListarDesafiosResponseType {

    @XmlElement(name = "Estado", required = true)
    protected EstadoType estado;
    @XmlElement(name = "Desafios")
    protected DesafiosType desafios;

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

    /**
     * Gets the value of the desafios property.
     *
     * @return
     *     possible object is
     *     {@link DesafiosType }
     *
     */
    public DesafiosType getDesafios() {
        return desafios;
    }

    /**
     * Sets the value of the desafios property.
     *
     * @param value
     *     allowed object is
     *     {@link DesafiosType }
     *
     */
    public void setDesafios(DesafiosType value) {
        this.desafios = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-257 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:29:09 PM GMT 
//


package cl.ares.bice.seguridad.sfa.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObtenerCelularResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObtenerCelularResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Estado" type="{http://www.bice.cl/sms}EstadoSmsType"/>
 *         &lt;element name="Telefono" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObtenerCelularResponseType", namespace = "http://www.bice.cl/sms", propOrder = {
    "estado",
    "telefono"
})
public class ObtenerCelularResponseType {

    @XmlElement(name = "Estado", namespace = "http://www.bice.cl/sms", required = true)
    protected EstadoSmsType estado;
    @XmlElement(name = "Telefono", namespace = "http://www.bice.cl/sms")
    protected Long telefono;

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoSmsType }
     *     
     */
    public EstadoSmsType getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoSmsType }
     *     
     */
    public void setEstado(EstadoSmsType value) {
        this.estado = value;
    }

    /**
     * Gets the value of the telefono property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTelefono() {
        return telefono;
    }

    /**
     * Sets the value of the telefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTelefono(Long value) {
        this.telefono = value;
    }

}

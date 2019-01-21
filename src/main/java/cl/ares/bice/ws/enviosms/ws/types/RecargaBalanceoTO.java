
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recargaBalanceoTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="recargaBalanceoTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canalOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recargaBalanceoTO", propOrder = { "canalOrigen" })
public class RecargaBalanceoTO {

    @XmlElement(required = true)
    protected String canalOrigen;

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

}

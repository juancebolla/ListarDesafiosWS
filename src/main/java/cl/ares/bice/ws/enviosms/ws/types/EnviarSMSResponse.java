
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enviarSMSResponse complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="enviarSMSResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.sms.bice.cl/}enviaSmsRespTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarSMSResponse", propOrder = { "_return" })
public class EnviarSMSResponse {

    @XmlElement(name = "return")
    protected EnviaSmsRespTO _return;

    /**
     * Gets the value of the return property.
     *
     * @return
     *     possible object is
     *     {@link EnviaSmsRespTO }
     *
     */
    public EnviaSmsRespTO getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value
     *     allowed object is
     *     {@link EnviaSmsRespTO }
     *
     */
    public void setReturn(EnviaSmsRespTO value) {
        this._return = value;
    }

}

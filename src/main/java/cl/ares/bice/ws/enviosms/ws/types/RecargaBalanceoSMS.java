
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recargaBalanceoSMS complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="recargaBalanceoSMS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://ws.sms.bice.cl/}recargaBalanceoTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recargaBalanceoSMS", propOrder = { "request" })
public class RecargaBalanceoSMS {

    protected RecargaBalanceoTO request;

    /**
     * Gets the value of the request property.
     *
     * @return
     *     possible object is
     *     {@link RecargaBalanceoTO }
     *
     */
    public RecargaBalanceoTO getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     *
     * @param value
     *     allowed object is
     *     {@link RecargaBalanceoTO }
     *
     */
    public void setRequest(RecargaBalanceoTO value) {
        this.request = value;
    }

}

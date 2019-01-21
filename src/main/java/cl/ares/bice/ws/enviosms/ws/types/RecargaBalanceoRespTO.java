
package cl.ares.bice.ws.enviosms.ws.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recargaBalanceoRespTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="recargaBalanceoRespTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estadoResp" type="{http://ws.sms.bice.cl/}estadoResp" minOccurs="0"/>
 *         &lt;element name="fechaUltimaModif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="porcentDistrib" type="{http://ws.sms.bice.cl/}operadoresCursorRespTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recargaBalanceoRespTO", propOrder = { "estadoResp", "fechaUltimaModif", "porcentDistrib" })
public class RecargaBalanceoRespTO {

    protected EstadoResp estadoResp;
    @XmlElement(required = true)
    protected String fechaUltimaModif;
    @XmlElement(nillable = true)
    protected List<OperadoresCursorRespTO> porcentDistrib;

    /**
     * Gets the value of the estadoResp property.
     *
     * @return
     *     possible object is
     *     {@link EstadoResp }
     *
     */
    public EstadoResp getEstadoResp() {
        return estadoResp;
    }

    /**
     * Sets the value of the estadoResp property.
     *
     * @param value
     *     allowed object is
     *     {@link EstadoResp }
     *
     */
    public void setEstadoResp(EstadoResp value) {
        this.estadoResp = value;
    }

    /**
     * Gets the value of the fechaUltimaModif property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaUltimaModif() {
        return fechaUltimaModif;
    }

    /**
     * Sets the value of the fechaUltimaModif property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaUltimaModif(String value) {
        this.fechaUltimaModif = value;
    }

    /**
     * Gets the value of the porcentDistrib property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the porcentDistrib property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPorcentDistrib().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperadoresCursorRespTO }
     *
     *
     */
    public List<OperadoresCursorRespTO> getPorcentDistrib() {
        if (porcentDistrib == null) {
            porcentDistrib = new ArrayList<OperadoresCursorRespTO>();
        }
        return this.porcentDistrib;
    }

}

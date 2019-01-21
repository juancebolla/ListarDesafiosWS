
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for enviaSmsRespTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="enviaSmsRespTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estadoResp" type="{http://ws.sms.bice.cl/}estadoResp" minOccurs="0"/>
 *         &lt;element name="codOperAsignado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="glsOperAsignado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMsgSalida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviaSmsRespTO", propOrder = {
         "estadoResp", "codOperAsignado", "glsOperAsignado", "idMsgSalida", "fechaRegistro" })
public class EnviaSmsRespTO {

    protected EstadoResp estadoResp;
    protected String codOperAsignado;
    protected String glsOperAsignado;
    protected String idMsgSalida;
    protected String fechaRegistro;

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
     * Gets the value of the codOperAsignado property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodOperAsignado() {
        return codOperAsignado;
    }

    /**
     * Sets the value of the codOperAsignado property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodOperAsignado(String value) {
        this.codOperAsignado = value;
    }

    /**
     * Gets the value of the glsOperAsignado property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGlsOperAsignado() {
        return glsOperAsignado;
    }

    /**
     * Sets the value of the glsOperAsignado property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGlsOperAsignado(String value) {
        this.glsOperAsignado = value;
    }

    /**
     * Gets the value of the idMsgSalida property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdMsgSalida() {
        return idMsgSalida;
    }

    /**
     * Sets the value of the idMsgSalida property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdMsgSalida(String value) {
        this.idMsgSalida = value;
    }

    /**
     * Gets the value of the fechaRegistro property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the value of the fechaRegistro property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaRegistro(String value) {
        this.fechaRegistro = value;
    }

}

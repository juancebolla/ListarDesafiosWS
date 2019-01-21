
package cl.ares.bice.ws.enviosms.ws.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operadoresCursorRespTO complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="operadoresCursorRespTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fec_ingreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fec_modif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nom_servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porcen_carga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operadoresCursorRespTO", propOrder = {
         "fecIngreso", "fecModif", "idServicio", "nomServicio", "porcenCarga" })
public class OperadoresCursorRespTO {

    @XmlElement(name = "fec_ingreso")
    protected String fecIngreso;
    @XmlElement(name = "fec_modif")
    protected String fecModif;
    @XmlElement(name = "id_servicio")
    protected String idServicio;
    @XmlElement(name = "nom_servicio")
    protected String nomServicio;
    @XmlElement(name = "porcen_carga")
    protected String porcenCarga;

    /**
     * Gets the value of the fecIngreso property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFecIngreso() {
        return fecIngreso;
    }

    /**
     * Sets the value of the fecIngreso property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFecIngreso(String value) {
        this.fecIngreso = value;
    }

    /**
     * Gets the value of the fecModif property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFecModif() {
        return fecModif;
    }

    /**
     * Sets the value of the fecModif property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFecModif(String value) {
        this.fecModif = value;
    }

    /**
     * Gets the value of the idServicio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdServicio() {
        return idServicio;
    }

    /**
     * Sets the value of the idServicio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdServicio(String value) {
        this.idServicio = value;
    }

    /**
     * Gets the value of the nomServicio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNomServicio() {
        return nomServicio;
    }

    /**
     * Sets the value of the nomServicio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNomServicio(String value) {
        this.nomServicio = value;
    }

    /**
     * Gets the value of the porcenCarga property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPorcenCarga() {
        return porcenCarga;
    }

    /**
     * Sets the value of the porcenCarga property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPorcenCarga(String value) {
        this.porcenCarga = value;
    }

}

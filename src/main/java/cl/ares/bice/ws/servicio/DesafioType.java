package cl.ares.bice.ws.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DesafioType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DesafioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoDesafio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Identificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Grupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Coordenadas" type="{http://www.bice.cl/seguridad/esb/desafio}CoordenadasType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DesafioType", propOrder = { "tipoDesafio", "identificador", "grupo", "aliasUsuario", "codigoQA", "online", "coordenadas" })
public class DesafioType {

    @XmlElement(name = "TipoDesafio", required = true)
    protected String tipoDesafio;
    @XmlElement(name = "Identificador", required = true)
    protected String identificador;
    @XmlElement(name = "Grupo")
    protected String grupo;
    @XmlElement(name="AliasUsuario")
    protected String aliasUsuario;
    @XmlElement(name="CodigoQA")
    protected String codigoQA;
    @XmlElement(name="OnLine")
    protected String online;
    @XmlElement(name = "Coordenadas")
    protected CoordenadasType coordenadas;


    public DesafioType() {
    }

    public DesafioType(String tipoDesafio){
        this.tipoDesafio = tipoDesafio;
    }

    public DesafioType(String tipoDesafio, String identificador){
        this.tipoDesafio = tipoDesafio;
        this.identificador = identificador;
    }

    /**
     * Gets the value of the tipoDesafio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoDesafio() {
        return tipoDesafio;
    }

    /**
     * Sets the value of the tipoDesafio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoDesafio(String value) {
        this.tipoDesafio = value;
    }

    /**
     * Gets the value of the identificador property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

    /**
     * Gets the value of the grupo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * Sets the value of the grupo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGrupo(String value) {
        this.grupo = value;
    }

    /**
     * @param aliasUsuario
     */
    public void setAliasUsuario(String aliasUsuario) {
        this.aliasUsuario = aliasUsuario;
    }

    /**
     * @return
     */
    public String getAliasUsuario() {
        return aliasUsuario;
    }

    /**
     * Gets the value of the coordenadas property.
     *
     * @return
     *     possible object is
     *     {@link CoordenadasType }
     *
     */
    public void setCodigoQA(String codigoQA) {
        this.codigoQA = codigoQA;
    }

    public String getCodigoQA() {
        return codigoQA;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getOnline() {
        return online;
    }

    public void setCoordenadas(CoordenadasType coordenadas) {
        this.coordenadas = coordenadas;
    }

    public CoordenadasType getCoordenadas() {
        return coordenadas;
    }
}

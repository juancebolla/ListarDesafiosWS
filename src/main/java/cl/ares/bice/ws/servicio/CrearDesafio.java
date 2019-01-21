package cl.ares.bice.ws.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearDesafio",
         propOrder = { "usuarioId", "canalOrigen", "tipoCliente", "ipCliente", "tipoDesafio",
                       "mensajeUsuario", "mensajeDesafio", "machineSecret" })
public class CrearDesafio{

    @XmlElement(name = "UsuarioId", required = true)
    protected String usuarioId;
    @XmlElement(name = "CanalOrigen", required = true)
    protected String canalOrigen;
    @XmlElement(name = "TipoCliente", required = true)
    protected String tipoCliente;
    @XmlElement(name = "IPCliente", required = true)
    protected String ipCliente;
    @XmlElement(name = "TipoDesafio", required = true)
    protected String tipoDesafio;
    @XmlElement(name = "MensajeUsuario", required = true)
    protected String mensajeUsuario;
    @XmlElement(name = "MensajeDesafio", required = true)
    protected MensajeDesafioType mensajeDesafio;
    @XmlElement(name = "MachineSecret")
    protected MachineSecret machineSecret;

    /**
     * Gets the value of the usuarioId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUsuarioId() {
        return usuarioId;
    }

    /**
     * Sets the value of the usuarioId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsuarioId(String value) {
        this.usuarioId = value;
    }

    /**
     * Gets the value of the tipoCliente property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * Sets the value of the tipoCliente property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoCliente(String value) {
        this.tipoCliente = value;
    }

    /**
     * Gets the value of the ipCliente property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIPCliente() {
        return ipCliente;
    }

    /**
     * Sets the value of the ipCliente property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIPCliente(String value) {
        this.ipCliente = value;
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

    public void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeDesafio(MensajeDesafioType mensajeDesafio) {
        this.mensajeDesafio = mensajeDesafio;
    }

    public MensajeDesafioType getMensajeDesafio() {
        return mensajeDesafio;
    }

    public void setMachineSecret(MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }

    public MachineSecret getMachineSecret() {
        return machineSecret;
    }
}

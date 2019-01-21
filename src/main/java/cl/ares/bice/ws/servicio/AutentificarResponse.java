package cl.ares.bice.ws.servicio;

import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutentificarResponse", propOrder = { "estado", "machineSecret" })
public class AutentificarResponse {

    @XmlElement(name = "Estado", required = true)
    protected EstadoType estado;
    @XmlElement(name = "MachineSecret")
    protected MachineSecret machineSecret;

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

    public void setMachineSecret(MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }

    public MachineSecret getMachineSecret() {
        return machineSecret;
    }
}

package cl.ares.bice.ws.servicio;

import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearDesafioResponse", propOrder = { "estado", "desafios", "machineSecret" })
public class CrearDesafioResponse {

        @XmlElement(name = "Estado", required = true)
        protected EstadoType estado;
        @XmlElement(name = "Desafios")
        protected DesafiosType desafios;
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

        /**
         * Gets the value of the desafios property.
         *
         * @return
         *     possible object is
         *     {@link DesafiosType }
         *
         */
        public DesafiosType getDesafios() {
            return desafios;
        }

        /**
         * Sets the value of the desafios property.
         *
         * @param value
         *     allowed object is
         *     {@link DesafiosType }
         *
         */
        public void setDesafios(DesafiosType value) {
            this.desafios = value;
        }

/*        public CrearDesafioResponse() {
            log.debug("CrearDesafioResponse");
            EstadoType estado = new EstadoType();
            if(new Random().nextBoolean()){
            log.debug("CrearDesafioResponse - OK");
                estado.setCodigo("0");
                estado.setGlosa("OK");
                this.setEstado(estado);
            } else {
                log.debug("CrearDesafioResponse - NOOK");
                estado.setCodigo("1");
                estado.setGlosa("No OK");
                this.setEstado(estado);
            }
        }*/

    public void setMachineSecret(MachineSecret machineSecret) {
        this.machineSecret = machineSecret;
    }

    public MachineSecret getMachineSecret() {
        return machineSecret;
    }
}

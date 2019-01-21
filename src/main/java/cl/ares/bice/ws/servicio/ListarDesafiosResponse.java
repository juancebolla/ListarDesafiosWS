package cl.ares.bice.ws.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListarDesafiosResponse", propOrder = {"estado", "desafios"})
public class ListarDesafiosResponse {
    @XmlElement(name = "Estado", required = true)
    private EstadoType estado;
    @XmlElement(name = "Desafios")
    private DesafiosType desafios;

    public void setEstado(EstadoType estado) {
        this.estado = estado;
    }

    public EstadoType getEstado() {
        return estado;
    }

    public void setDesafios(DesafiosType desafios) {
        this.desafios = desafios;
    }

    public DesafiosType getDesafios() {
        return desafios;
    }
}

package cl.ares.bice.ws.servicio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MensajeDesafioType", propOrder = { "atributoMensaje" })
public class MensajeDesafioType {
    @XmlElement(name = "AtributoMensaje", required = true)
    protected List<AtributoMensajeType> atributoMensaje;

    public void setAtributoMensaje(List<AtributoMensajeType> atributoMensaje) {
        this.atributoMensaje = atributoMensaje;
    }

    public List<AtributoMensajeType> getAtributoMensaje() {
        return atributoMensaje;
    }
}

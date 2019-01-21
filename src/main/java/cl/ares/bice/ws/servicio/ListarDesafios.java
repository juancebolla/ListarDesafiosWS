package cl.ares.bice.ws.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListarDesafios", propOrder = {
         "usuarioId", "tipoCliente" })
public class ListarDesafios {
    @XmlElement(name = "UsuarioId", required = true)
    protected String usuarioId;
    @XmlElement(name = "TipoCliente", required = true)
    protected String tipoCliente;

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }
}

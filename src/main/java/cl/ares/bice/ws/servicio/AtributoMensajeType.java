package cl.ares.bice.ws.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AtributoMensajeType", propOrder = { "nombreCampo", "valorCampo" })
public class AtributoMensajeType {
    @XmlElement(name = "NombreCampo", required = true)
    protected String nombreCampo;
    @XmlElement(name = "ValorCampo", required = true)
    protected String valorCampo;

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    public String getValorCampo() {
        return valorCampo;
    }
}

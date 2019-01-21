package cl.ares.bice.seguridad.sfa.modelo;

import java.util.Calendar;


public class Enrolamiento {

    private String IDMensaje;
    private String NombreCliente;
    private String NumeroTelefono;
    private Calendar FechaRegistro;
    private String EstadoOperacion;

    public String getIDMensaje() {
        return IDMensaje;
    }

    public void setIDMensaje(String IDMensaje) {
        this.IDMensaje = IDMensaje;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        NumeroTelefono = numeroTelefono;
    }

    public Calendar getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getEstadoOperacion() {
        return EstadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        EstadoOperacion = estadoOperacion;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer();
        String appender1 = "][";
        String appender2 = ":";
        return buff.append("[").
                append("IDMensaje").append(appender2).
                append(IDMensaje).append(appender1).
                append("NombreCliente").append(appender2).
                append(NombreCliente).append(appender1).
                append("NumeroTelefono").append(appender2).
                append(NumeroTelefono).append(appender1).
                append("FechaRegistro").append(appender2).
                append(FechaRegistro).append(appender1).
                append("EstadoOperacion").append(appender2).
                append(EstadoOperacion).append("]").
                toString();
    }
}


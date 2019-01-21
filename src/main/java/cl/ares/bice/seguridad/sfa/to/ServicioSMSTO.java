package cl.ares.bice.seguridad.sfa.to;

/**
 * Representa los datos de un Servicio SMS.
 * 
 * Registro de Versiones:
 * <ul>
 * <li>15/04/2014 Miguel Garcia H. (TInet): Creacion de la clase.
 * </ul>
 * 
 * @author Miguel Garcia H. (TInet)
 */
public class ServicioSMSTO {

    /**
     * Identificador de Servicio.
     */
    private String idServicio;

    /**
     * Identificador del Servicio siguiente en caso de falla.
     */
    private String idServicioFailover;

    public String getIdServicio() {
        return idServicio;
    }

    public String getIdServicioFailover() {
        return idServicioFailover;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public void setIdServicioFailover(String idServicioFailover) {
        this.idServicioFailover = idServicioFailover;
    }

    @Override
    public String toString() {
        return "ServicioSMSTO [idServicio=" + idServicio
            + ", idServicioFailover=" + idServicioFailover + "]";
    }

}

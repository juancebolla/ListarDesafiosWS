package cl.ares.bice.seguridad.sfa.exception;

/**
 * Clase Exception para el uso de Servicios de Envio SMS.
 * 
 * Registro de Versiones:
 * <ul>
 * <li>15/04/2014 Miguel Garcia H. (TInet): Creacion de la clase.
 * </ul>
 * 
 * @author Miguel Garcia H. (TInet)
 */
public class ServicioSMSException extends Exception {

    /**
     * Identificador para serializacion.
     */
    private static final long serialVersionUID = 1L;

    public ServicioSMSException() {
    }

    public ServicioSMSException(String message) {
        super(message);
    }

    public ServicioSMSException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicioSMSException(Throwable cause) {
        super(cause);
    }

}

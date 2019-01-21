package cl.ares.bice.seguridad.sfa.adapter;

import cl.ares.bice.seguridad.sfa.exception.ServicioSMSException;
import cl.ares.bice.seguridad.sfa.to.ServicioSMSTO;

/**
 * Interfaz que encapsula un Servicio de Mensajeria SMS.
 * 
 * Registro de Versiones:
 * <ul>
 * <li>15/04/2014 Miguel Garcia H. (TInet): Creacion de la interfaz.
 * </ul>
 * 
 * @author Miguel Garcia H. (TInet)
 */
public interface ServiciosSMSAdapter {

    /**
     * Envia enviar un mensaje SMS a un telefon celular.
     * 
     * @param numeroTelefono
     *            Numero telefonico destino.
     * @param mensaje
     *            Contenido del mensaje SMS.
     * @throws ServicioSMSException
     *             En caso de Error con servicio.
     */
    void enviarSms(long numeroTelefono, String mensaje)
        throws ServicioSMSException;

    /**
     * Obtiene un Identificador de Servicio SMS.
     * 
     * @return Objeto con identificador de servicio.
     */
    ServicioSMSTO getIdServicio();

    /**
     * Setea un Identificador de Servicio SMS.
     * 
     * @param servicioSMS
     *            TO de servicioSMS.
     */
    void setIdServicio(ServicioSMSTO servicioSMS);
}

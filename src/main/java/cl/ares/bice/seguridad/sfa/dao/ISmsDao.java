package cl.ares.bice.seguridad.sfa.dao;

import cl.ares.bice.seguridad.sfa.exception.ServicioSMSException;
import cl.ares.bice.seguridad.sfa.modelo.EstadoRegistroSms;
import cl.ares.bice.seguridad.sfa.to.ServicioSMSTO;

import java.math.BigInteger;

/**
 * Interfaz DAO que encapsula los Servicios del DAO SMS.
 *
 * Registro de Versiones:
 * <ul>
 * <li>1.0 Banco BICE: Creacion de la clase.
 * <li>1.1 15/04/2014 Miguel Garcia (TInet): Se agregan los metodos
 * {@link #obtenerServicioSMS()} y {@link #registrarEnvioSMS()}.
 * </ul>
 *
 * @author Banco BICE
 * @version 1.1
 */
public interface ISmsDao {

    void registrarDiarioSms(String idSms, String idcliente, BigInteger telefono,
        EstadoRegistroSms preinscrito);

    /**
     * Metodo que registra el uso de un Servicio SMS.
     * 
     */
    void registrarEnvioSMS(long numero, String mensajeSMS, String fechaEnvio,
        String fechaRespuesta, String codigoEntrega)
        throws ServicioSMSException;

    /**
     * Metodo que resuelve el identificador del Servicio SMS que se debe
     * utilizar.
     * 
     * @return Identificador del Servicio SMS.
     */
    public ServicioSMSTO obtenerServicioSMS() throws ServicioSMSException;
}

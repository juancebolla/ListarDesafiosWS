package cl.ares.bice.seguridad.sfa.factory;

import cl.ares.bice.seguridad.sfa.adapter.ServiciosSMSAdapter;

/**
 * Interfaz que implmeneta el patron de diseno 
 *
 * @author Miguel Garcia H. (TInet)
 */
public interface ServicioSMSFactory {

    /**
     * Metodo que retorna un Adapter para Envio de SMS.
     *
     * @return Adapter asociado al Servicio SMS.
     */
    ServiciosSMSAdapter crearAdapter(); 
}

package cl.ares.bice.seguridad.sfa.adapter;

import cl.ares.bice.seguridad.sfa.exception.ServicioSMSException;

import java.math.BigInteger;

/**
 * Interfaz que encapsula un Servicio de SMS Spincorp. Esta interfaz puede
 * definir servicios propios de Spincorp.
 *
 * Registro de Versiones:
 * <ul>
 * <li>Banco BICE: Creacion de la clase.
 * <li>15/04/2014 Miguel Garcia H (TInet): Se modifica la jerarquia de la
 * clase para que extienda de la interfaz ServiciosSMSAdapter.
 * </ul>
 *
 * @author Banco BICE.
 *
 */
public interface ISmsAdapter extends ServiciosSMSAdapter {

    static final int OK = 0;
    static final int TELEFONO_INVALIDO = 1;
    static final int TELEFONO_NO_INSCRITO = 2;

    public void enviarSms(long numFono, String Msg) throws ServicioSMSException;

    public long[] obtenerCelulares(String rut);

    public String registrarUsuario(String idcliente, BigInteger telefono);

    public void eliminaRegistroUsuario(String idcliente, BigInteger telefono);

    public int estadoTelefono(BigInteger telefono);
}

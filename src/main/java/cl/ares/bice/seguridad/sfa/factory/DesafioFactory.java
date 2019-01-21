package cl.ares.bice.seguridad.sfa.factory;

import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;
import cl.ares.bice.seguridad.sfa.servicio.SfaException;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.MensajeDesafioType;

import java.util.Map;

public class DesafioFactory {

    private Map<TipoDispositivo, IDesafioStrategy> strategies;
    private DefaultDesafioStrategy defaultStrategy = new DefaultDesafioStrategy();


    public void setDefaultStrategy(DefaultDesafioStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    public void setStrategies(Map<TipoDispositivo, IDesafioStrategy> strategies){
        this.strategies = strategies;
    }

    public DesafiosType crearDesafio(String usuarioId, String grupo, String tipoDispositivo)
        throws SfaException {
        if(!strategies.containsKey(tipoDispositivo))
            return defaultStrategy.crear(usuarioId, grupo, tipoDispositivo);
        return strategies.get(tipoDispositivo).crear(usuarioId, grupo, tipoDispositivo);
    }

    public DesafiosType crearDesafio(String usuarioId, String grupo, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje, cl.ares.bice.ws.servicio.MachineSecret machineSecret)
        throws SfaException {
        if(!strategies.containsKey(tipoDispositivo))
            return defaultStrategy.crear(usuarioId, grupo, ip, tipoDispositivo, Canal, mensajeUsuario, mensaje, machineSecret);
        return strategies.get(tipoDispositivo).crear(usuarioId, grupo, ip, tipoDispositivo, Canal, mensajeUsuario, mensaje, machineSecret);
    }
}

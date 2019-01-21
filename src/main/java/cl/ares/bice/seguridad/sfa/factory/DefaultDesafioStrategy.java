package cl.ares.bice.seguridad.sfa.factory;

import cl.ares.bice.seguridad.sfa.adapter.ISfaAdapter;
import cl.ares.bice.seguridad.sfa.servicio.SfaException;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.MensajeDesafioType;

public class DefaultDesafioStrategy implements IDesafioStrategy {

    ISfaAdapter sfaAdapter;


    public void setSfaAdapter(ISfaAdapter sfaAdapter) {
        this.sfaAdapter = sfaAdapter;
    }

    public DesafiosType crear(String usuarioId, String grupo, String tipoDispositivo) throws SfaException {
        return sfaAdapter.crearDesafio(usuarioId, grupo, tipoDispositivo);
    }

    public DesafiosType crear(String usuarioId, String grupo, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje, cl.ares.bice.ws.servicio.MachineSecret machineSecret) throws SfaException {
        return sfaAdapter.crearDesafio(usuarioId, grupo, ip, tipoDispositivo, Canal, mensajeUsuario, mensaje);
    }
}

package cl.ares.bice.seguridad.sfa.factory;

import cl.ares.bice.seguridad.sfa.servicio.SfaException;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.MachineSecret;
import cl.ares.bice.ws.servicio.MensajeDesafioType;

public interface IDesafioStrategy {

    public DesafiosType crear(String usuarioId, String grupo, String tipoDispositivo) throws SfaException;

    public DesafiosType crear(String usuarioId, String grupo, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje, MachineSecret machineSecret) throws SfaException;
}

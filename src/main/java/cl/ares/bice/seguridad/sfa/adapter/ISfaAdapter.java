package cl.ares.bice.seguridad.sfa.adapter;

import cl.ares.bice.seguridad.sfa.modelo.Otp;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;

import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioInvalido;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioBloqueadoException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoExisteException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoSolicitoDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSinDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSuspendidoException;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.MensajeDesafioType;

import java.util.List;

public interface ISfaAdapter {

    public List<Dispositivo> dipositivosDelUsuario(String usuarioId, String grupo)
            throws UsuarioNoExisteException, UsuarioBloqueadoException, UsuarioSuspendidoException;

    public void autentificar(String usuarioId, String grupo, String respuestaDesafio, String ip, String tipoDispositivo)
            throws UsuarioNoSolicitoDesafioException, RespuestaDesafioInvalido, UsuarioBloqueadoException, UsuarioNoExisteException, UsuarioSuspendidoException;

    public DesafiosType crearDesafio(Dispositivo dispositvo) throws UsuarioBloqueadoException, UsuarioSuspendidoException;

    public DesafiosType crearDesafio(String usuarioId, String grupo, String tipoDispositivo)
            throws UsuarioBloqueadoException, UsuarioSuspendidoException;

    public DesafiosType crearDesafio(String usuarioId, String grupo, String ip, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje)
            throws UsuarioBloqueadoException, UsuarioSuspendidoException, UsuarioSinDesafioException;

    public Otp obtenerOtp(String usuarioId, String grupo);

    public void desbloquearUsuario(String usuarioId, String grupo);
}

package cl.ares.bice.seguridad.sfa.servicio;

import cl.ares.bice.seguridad.sfa.modelo.Desafio;
import cl.ares.bice.seguridad.sfa.modelo.TipoDispositivo;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.seguridad.sfa.modelo.Enrolamiento;

import cl.ares.bice.ws.servicio.MensajeDesafioType;

import java.util.List;

public interface IDesafioServicio {

    public static final String BANCA_PERSONAS = "bancapersonas";
    public static final String BANCA_EMPRESAS = "bancaempresas";

    public List<Desafio> solicitarDesafios(String usuarioId, String grupo, String ip, String tipoDispositivo)
            throws SfaException;

    public Desafio crearDesafio(String usuarioId, String grupo, String tipoDispositivo)
            throws SfaException;

    public Desafio crearDesafio(String usuarioId, String grupo, String tipoDispositivo, String Canal, String mensajeUsuario, MensajeDesafioType mensaje)
            throws SfaException;

    public List<Dispositivo> listarDispositvos(String usuarioId, String grupo)
            throws UsuarioNoExisteException, UsuarioBloqueadoException, UsuarioSinDesafioException, UsuarioSuspendidoException;

    public void autentificar(String usuarioId, String grupo, String respuestaDesafio, String ip, String tipoDispositivo)
            throws UsuarioNoSolicitoDesafioException, RespuestaDesafioInvalido, UsuarioBloqueadoException,
                    UsuarioNoExisteException, UsuarioSuspendidoException;

    public void desbloquearUsuario(String usuarioId, String grupo);

    public void ingresoEnrolamientoSMS(Enrolamiento enrolamiento);

    public void actualizarEnrolamientoSMS(String idMensaje, String estadoOperacion);
}

package cl.ares.bice.seguridad.sfa.ws;

import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioAutenticado;
import cl.ares.bice.ws.servicio.EstadoType;
import cl.ares.bice.seguridad.sfa.servicio.RespuestaDesafioInvalido;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioBloqueadoException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoExisteException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioNoSolicitoDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSinDesafioException;
import cl.ares.bice.seguridad.sfa.servicio.UsuarioSuspendidoException;

public class DesafioEstados {
    public final static String CODIGO_OK = "0";
    public final static String GLOSA_OK = "[ESTADO_OK]";


    // Errores 10000 - 10999 son de desafio
    public final static String COD_ERROR_SISTEMA = "9";
    public final static String GLOSA_ERROR_SISTEMA = "[ERROR_SISTEMA]";

    public final static String COD_USUARIO_NOEXISTE = "10001";
    public final static String GLOSA_USUARIO_NOEXISTE = "[USUARIO_NOEXISTE]";

    public final static String COD_USUARIO_BLOQUEADO = "10002";
    public final static String GLOSA_USUARIO_BLOQUEADO = "[USUARIO_BLOQUEADO]";

    public final static String COD_USUARIO_SINDESAFIOS = "10003";
    public final static String GLOSA_USUARIO_SINDESAFIOS = "[USUARIO_SINDESAFIOS]";

    public static final String COD_DESAFIO_INVALIDO  = "10004";
    public static final String GLOSA_DESAFIO_INVALIDO = "[DESAFIO_INVALIDO]";

    public static final String COD_NOSOLICITO_DESAFIO  = "10005";
    public static final String GLOSA_NOSOLICITO_DESAFIO = "[NOSOLICITO_DESAFIO]";

    public static final String COD_USUARIO_SUSPENDIDO  = "10006";
    public static final String GLOSA_USUARIO_SUSPENDIDO = "[USUARIO_SUSPENDIDO]";
    
    public static final String COD_DESAFIO_AUTENTICADO = "111";
    public static final String GLOSA_DESAFIO_AUTENTICADO = "[AUTENTICADO_OK]";

    public static EstadoType ok() {
        return crearEstado(CODIGO_OK, GLOSA_OK, "");
    }

    public static EstadoType usuarioNoExiste(String msg) {
        return crearEstado(COD_USUARIO_NOEXISTE, GLOSA_USUARIO_NOEXISTE, msg);
    }

    public static EstadoType usuarioBloqueado(String msg) {
        return crearEstado(COD_USUARIO_BLOQUEADO, GLOSA_USUARIO_BLOQUEADO, msg);
    }

    public static EstadoType usuarioSinDesafios(String msg) {
        return crearEstado(COD_USUARIO_SINDESAFIOS, GLOSA_USUARIO_SINDESAFIOS, msg);
    }

    private static EstadoType crearEstado(String cod, String glosa, String msg) {
        EstadoType estado = new EstadoType();
        estado.setCodigo(cod);
        if(msg != null && msg.length() != 0)
            estado.setGlosa(glosa + ":" + msg);
        else
            estado.setGlosa(glosa);    
        return estado;
    }

    public static EstadoType errorSistema(String msg) {
        return crearEstado(COD_ERROR_SISTEMA, GLOSA_ERROR_SISTEMA, msg);
    }

    public static EstadoType createEstadoError(Throwable e){
        if(e instanceof UsuarioNoExisteException)
            return usuarioNoExiste(e.getMessage());
        if(e instanceof UsuarioBloqueadoException)
            return usuarioBloqueado(e.getMessage());
        if(e instanceof UsuarioSinDesafioException)
            return usuarioSinDesafios(e.getMessage());
        if(e instanceof UsuarioNoSolicitoDesafioException)
            return usuarioNoSolicitoDesafio(e.getMessage());
        if(e instanceof UsuarioSuspendidoException)
            return usuarioSuspendido(e.getMessage());
        if(e instanceof RespuestaDesafioInvalido)
            return respuestaDesafioInvalido(e.getMessage());
        if(e instanceof RespuestaDesafioAutenticado)
            return respuestaDesafioAutenticado(e.getMessage());

        return errorSistema(e.getMessage()); 
    }

    private static EstadoType usuarioSuspendido(String msg) {
        return crearEstado(COD_USUARIO_SUSPENDIDO, GLOSA_USUARIO_SUSPENDIDO, msg);
    }

    private static EstadoType respuestaDesafioInvalido(String msg) {
        return crearEstado(COD_DESAFIO_INVALIDO, GLOSA_DESAFIO_INVALIDO, msg);
    }
    
    private static EstadoType respuestaDesafioAutenticado(String msg) {
        return crearEstado(COD_DESAFIO_AUTENTICADO, GLOSA_DESAFIO_AUTENTICADO, msg);
    }

    private static EstadoType usuarioNoSolicitoDesafio(String msg) {
        return crearEstado(COD_NOSOLICITO_DESAFIO, GLOSA_NOSOLICITO_DESAFIO, msg);
    }

}

package cl.ares.bice.seguridad.sfa.servicio;

public class UsuarioSuspendidoException extends SfaException {

    public UsuarioSuspendidoException() {
    }

    public UsuarioSuspendidoException(String message) {
        super(message);
    }

    public UsuarioSuspendidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioSuspendidoException(Throwable cause) {
        super(cause);
    }
}

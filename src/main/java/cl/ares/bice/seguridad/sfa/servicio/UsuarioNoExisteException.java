package cl.ares.bice.seguridad.sfa.servicio;

public class UsuarioNoExisteException extends SfaException{


    public UsuarioNoExisteException(String message) {
        super(message);
    }
}

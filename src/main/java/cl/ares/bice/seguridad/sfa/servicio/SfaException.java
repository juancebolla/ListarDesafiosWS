package cl.ares.bice.seguridad.sfa.servicio;

public class SfaException extends Exception{


    public SfaException() {
    }

    public SfaException(String message) {
        super(message);
    }

    public SfaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SfaException(Throwable cause) {
        super(cause);
    }
}

package cl.ares.bice.seguridad.sfa.servicio;

public class ErrorEnvioSmsException extends SfaException{


    public ErrorEnvioSmsException() {
    }

    public ErrorEnvioSmsException(String message) {
        super(message);
    }

    public ErrorEnvioSmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorEnvioSmsException(Throwable cause) {
        super(cause);
    }
}

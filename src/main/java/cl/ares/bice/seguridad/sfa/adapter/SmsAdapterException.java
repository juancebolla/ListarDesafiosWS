package cl.ares.bice.seguridad.sfa.adapter;

public class SmsAdapterException extends RuntimeException{


    public SmsAdapterException() {
    }

    public SmsAdapterException(String message) {
        super(message);
    }

    public SmsAdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsAdapterException(Throwable cause) {
        super(cause);
    }
}

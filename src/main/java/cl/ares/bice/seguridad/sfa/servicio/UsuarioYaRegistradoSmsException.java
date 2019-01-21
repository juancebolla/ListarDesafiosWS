package cl.ares.bice.seguridad.sfa.servicio;

public class UsuarioYaRegistradoSmsException extends RuntimeException{

    long celular;

    public UsuarioYaRegistradoSmsException(long celular) {
        this.celular = celular;
    }
}

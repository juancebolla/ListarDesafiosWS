package cl.ares.bice.seguridad.sfa.servicio;

public interface ISmsServicio {

    public long obtenerCelular(String rut);

    public void enviarSms(long telefono, String mensajeSms);

    public void registrarUsuario(String idcliente, long telefono);

    public void eliminaRegistroUsuario(String idcliente, long telefono);

    public boolean estaTelefonoRegistrado(long telefono);

    long[] obtenerCelularesInscritos(String idcliente);

}

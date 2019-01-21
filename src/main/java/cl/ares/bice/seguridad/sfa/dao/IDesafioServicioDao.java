package cl.ares.bice.seguridad.sfa.dao;

import cl.ares.bice.seguridad.sfa.modelo.Enrolamiento;


public interface IDesafioServicioDao {

    public void ingresoEnrolamientoSMS(Enrolamiento enrolamiento);

    public void actualizarEnrolamientoSMS(String idMensaje, String estadoOperacion);

}

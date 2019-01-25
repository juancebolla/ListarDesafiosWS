package cl.ares.bice.seguridad.sfa.ws;

import cl.ares.bice.seguridad.esb.desafio.ListarDesafiosResponseType;
import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;

import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.servicio.DesafiosType;

import java.util.List;

public class ListarDesafiosBuilder extends DesafioBuilder{

    public static ListarDesafiosResponseType crearRespuesta(List<Dispositivo> dispositivos){
        ListarDesafiosResponseType response = new ListarDesafiosResponseType();
        response.setEstado(DesafioEstados.ok());
        response.setDesafios(new DesafiosType());

        for(Dispositivo d : dispositivos){
            response.getDesafios().getDesafio().add(crearDesafioType(d));
        }

        return response;
    }

    public static DesafioType crearDesafioType(Dispositivo d){
        DesafioType dt = new DesafioType();
        dt.setTipoDesafio(d.getTipo());
        dt.setIdentificador(d.getIdentificador());
        //dt.setGrupo(d.getGrupo());

        return dt;
    }

}

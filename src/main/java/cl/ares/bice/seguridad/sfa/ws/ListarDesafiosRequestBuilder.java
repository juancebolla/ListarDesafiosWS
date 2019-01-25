package cl.ares.bice.seguridad.sfa.ws;

import cl.ares.bice.seguridad.sfa.modelo.Dispositivo;
import cl.ares.bice.ws.servicio.DesafioType;
import cl.ares.bice.ws.servicio.DesafiosType;
import cl.ares.bice.ws.servicio.ListarDesafiosResponse;

import java.util.List;

public class ListarDesafiosRequestBuilder extends DesafioBuilder{

    public static ListarDesafiosResponse crearRespuesta(List<Dispositivo> dispositivos){
        ListarDesafiosResponse response = new ListarDesafiosResponse();
        response.setEstado(DesafioEstados.ok());
        response.setDesafios(new DesafiosType());

        for(Dispositivo d : dispositivos){
            response.getDesafios().getDesafio().add(crearDesafioType(d));
        }

        return response;
    }

    public static DesafioType crearDesafioType(Dispositivo d){
        DesafioType dt = new DesafioType();
    	try{
        dt.setTipoDesafio(d.getTipo());
        dt.setIdentificador(d.getIdentificador());
        dt.setGrupo(d.getGrupo());
        dt.setAliasUsuario(d.getAliasUsuario());
    	if(d.getOnline())
            dt.setOnline("true");
        else
            dt.setOnline("false");

        return dt;
    	}catch(Exception e){
    		e.printStackTrace();
            return dt;
    	}
    }

}
